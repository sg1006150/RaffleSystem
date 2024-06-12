<template>
  <div>
    <el-input
      v-model="search"
      placeholder="搜索关键字"
      clearable
      style="margin-bottom: 20px; width: 300px;"
    ></el-input>
    <el-dialog v-model="addDialogVisible" ><add @cancel="handleDialogClose" @done="handleDone"></add></el-dialog>
    <el-button type="primary" @click="performSearch" style="margin-left: 10px; margin-bottom:20px">搜索</el-button>
    <el-button type="primary" style="margin-bottom: 20px; margin-left: 200px" @click="addDialogVisible=true">新增奖品</el-button>
    <el-table :data="filteredData" style="width: 100%">
      <el-table-column fixed prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="名称" width="120" />
      <el-table-column prop="picdirectory"label="图片" width="120">
        <template #default="scope">
          <img :src="scope.row.image" alt="Image" style="width: 50px; height: 50px;" />
        </template>
      </el-table-column>
      <el-table-column prop="price" label="价格" width="120" />
      <el-table-column prop="addedby" label="添加人" width="120" />
      <el-table-column fixed="right" label="Operations" width="160">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleClick(scope.row)">
            编辑
          </el-button>
          <el-button link type="primary" size="small" @click="deletePrize(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import {ref, computed, onBeforeMount} from 'vue';
import { useRouter } from 'vue-router';
import add from './SettingPrize.vue';
import {ElMessage}from 'element-plus'
import request from "@/utils/request";
const addDialogVisible=ref(false)
const search = ref('');
const searchText = ref('');
const router = useRouter();
const usertype=ref('')
const choseId=ref({})
const handleDialogClose=()=>{
  addDialogVisible.value=false
}
const tableData = ref([]);
const handleDone=()=>{
  addDialogVisible.value=false
  getPrize()
}
onBeforeMount(()=>{
  request.get('/getCurrentUser').then(response=>{
        if(response.data.success){
          usertype.value=response.data.data.type
          getPrize()
        }
      }
  ).catch(error=>{
    ElMessage.error(error.message)
  })
})
const filteredData = computed(() => {
  if (!searchText.value) {
    return tableData.value;
  }
  return tableData.value.filter((item) =>
    Object.values(item).some(val => val.toString().toLowerCase().includes(searchText.value.toLowerCase()))
  );
});

const handleClick = (row) => {
  console.log('edit', row);
};

const performSearch = () => {
  searchText.value = search.value;
};
const getPrize=()=> {
  if (usertype.value === 'admin') {
    request.get("/getAllPrize").then(response => {
      if (response.data.success) {
        tableData.value = response.data.data
      } else {
        ElMessage.error(response.data.message)
      }
    })
        .catch(error => {
          ElMessage.error(error.message)
        })
  } else if (usertype.value === 'user') {
    request.get("/getPrizeByUser").then(response => {
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
const deletePrize = (row) => {
  const index = tableData.value.indexOf(row);
  choseId.value=tableData.value[index]

  request.delete('/deletePrize',{data:choseId.value}).then(response=>{
        if(response.data.success){
          ElMessage.success(response.data.message)
          getPrize()
        }else {
          ElMessage.error(response.data.message)
        }
      }
  ).catch(error=>{
    ElMessage.error(error.message)
  })

};
</script>

<style scoped>
/* 你的样式 */
</style>
