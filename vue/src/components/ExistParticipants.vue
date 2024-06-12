<template>
  <div>
    <el-input
      v-model="search"
      placeholder="搜索关键字"
      clearable
      style="margin-bottom: 20px; width: 300px;"
    ></el-input>
    <el-dialog v-model="addDialogVisible"><add @done="handleAdded"></add></el-dialog>
    <el-dialog v-model="updateDialogVisible"><update :Data="selectedData" @done="handleUpdate"></update></el-dialog>
    <el-button type="primary" @click="performSearch" style="margin-left: 10px; margin-bottom:20px">搜索</el-button>
    <el-button type="primary" style="margin-bottom: 20px; margin-left: 875px" @click="addDialogVisible=true">新增用户</el-button>
    <el-table :data="filteredData" style="width: 100%" :key="itemKey">
      <el-table-column fixed prop="id" label="UID" width="80" />
      <el-table-column prop="name" label="姓名" width="120" />
      <el-table-column prop="phone" label="电话号码" width="150" />
      <el-table-column prop="addedby" label="创建人" width="200" />
      <el-table-column fixed="right" label="Operations" width="160">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="editUser(scope.row)">
            编辑
          </el-button>
          <el-button link type="primary" size="small" @click="deleteUser(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import {ref, computed, onBeforeMount} from 'vue';
import request from "@/utils/request";
import {ElMessage} from "element-plus";
import add from './AddParticipant.vue';
import update from './UpdateParticipant.vue';
const search = ref('');
const searchText = ref('');
const tableData = ref([]);
const addDialogVisible = ref(false);
const selectedData = ref(null);
const handleAdded=()=> {
  addDialogVisible.value = false
  getUsers()
}
const handleUpdate=()=>{
  updateDialogVisible.value=false
  getUsers()
}
const updateDialogVisible=ref(false)
const itemKey = Math.random()
const usertype=ref('user')
const choseUser=ref({
  id:'',
  addedby:'',
  name:'',
  phone:''
})
const filteredData = computed(() => {
  if (!searchText.value) {
    return tableData.value;
  }
  return tableData.value.filter((item) =>
    Object.values(item).some(val => val.toString().toLowerCase().includes(searchText.value.toLowerCase()))
  );
});
onBeforeMount(()=>{
  getCurrentUser()
})
const handleClick = (row) => {
  console.log('edit', row);
};
const getCurrentUser=()=>{
  request.get('/getCurrentUser').then(response=>{
        if(response.data.success){
          usertype.value=response.data.data.type
          getUsers()
        }
      }
  ).catch(error=>{
    ElMessage.error(error.message)
  })
}
const performSearch = () => {
  searchText.value = search.value;
};
const editUser = (row) => {
  selectedData.value = { ...row };
  updateDialogVisible.value = true;
};

const deleteUser = (row) => {
  const index = tableData.value.indexOf(row);

  choseUser.value=tableData.value[index]

  request.delete('/deleteParticipant',{data:choseUser.value}).then(response=>{
        if(response.data.success){
          ElMessage.success(response.data.message)
          getUsers()
        }else {
          ElMessage.error(response.data.message)
        }
      }
  ).catch(error=>{
    ElMessage.error(error.message)
  })

};
const getUsers=()=>{
  if(usertype.value==='admin') {
    request.get("/getAllParticipants").then(response => {
      if (response.data.success) {
        tableData.value = response.data.data
      } else {
        ElMessage.error(response.data.message)
      }
    })
        .catch(error => {
          ElMessage.error(error.message)
        })
  }
  else if(usertype.value==='user'){
    request.get("/getParticipantsByUser").then(response => {
      if (response.data.success) {
        tableData.value = response.data.data
      } else {
        ElMessage.error(response.data.message)
      }
    })
        .catch(error => {
          ElMessage.error(error.message)
        })
  }
}
</script>
