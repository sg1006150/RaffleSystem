<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column fixed prop="id" label="ID" width="50" />
    <el-table-column prop="username" label="Name" width="120" />
    <el-table-column prop="phone" label="Phone" width="120" />
    <el-table-column prop="type" label="Type" width="120" />
    <el-table-column prop="email" label="EMail" width="600" />
    <el-table-column fixed="right" label="Operations" width="120">
      <template #default>
        <el-button link type="primary" size="small" @click="handleClick">
          Detail
        </el-button>
        <el-button link type="primary" size="small">Edit</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>
import {ElMessage} from "element-plus";
import request from '../utils/request';
import {onBeforeMount, ref} from "vue";

onBeforeMount(()=>{
  getAllUser()
})
const handleClick = () => {
  console.log('click')
}
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
const tableData = ref([])

</script>
