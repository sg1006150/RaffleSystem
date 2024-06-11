<template>
  <div>
    <el-input
      v-model="search"
      placeholder="搜索关键字"
      clearable
      style="margin-bottom: 20px; width: 300px;"
    ></el-input>
    <el-button type="primary" @click="performSearch" style="margin-left: 10px; margin-bottom:20px">搜索</el-button>
    <el-table :data="filteredData" style="width: 100%">
      <el-table-column fixed prop="id" label="UID" width="80" />
      <el-table-column prop="username" label="用户名" width="120" />
      <el-table-column prop="phone" label="电话号码" width="150" />
      <el-table-column prop="email" label="创建人" width="200" />
      <el-table-column fixed="right" label="Operations" width="160">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleClick(scope.row)">
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

const search = ref('');
const searchText = ref('');
const tableData = ref([]);

const filteredData = computed(() => {
  if (!searchText.value) {
    return tableData.value;
  }
  return tableData.value.filter((item) =>
    Object.values(item).some(val => val.toString().toLowerCase().includes(searchText.value.toLowerCase()))
  );
});
onBeforeMount(()=>{
  getAllUser()
})
const handleClick = (row) => {
  console.log('edit', row);
};

const performSearch = () => {
  searchText.value = search.value;
};

const deleteUser = (row) => {
  const index = tableData.value.indexOf(row);
  if (index !== -1) {
    tableData.value.splice(index, 1);
  }

};
const getAllUser=()=>{
  request.get("/getAllUsers").then(response=>{
    if(response.data.success){
      tableData.value=response.data.data
    }
    else{
      ElMessage.error(response.data.message)
    }
  })
      .catch(error=>{
        ElMessage.error(error.message)
      })
}
</script>
