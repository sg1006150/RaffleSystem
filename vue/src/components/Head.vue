
<template>
  <el-page-header @back="goBack">

    <template #content style="flex: 1">
    <p style="float: left">欢迎 {{props.username}}!</p>

    </template>
    <template #extra>
      <el-dropdown style="float: right" @command="handleCommand">
        <span class="el-dropdown-link">
          <el-icon><Setting /></el-icon> Settings<el-icon class="el-icon--right"><arrow-down /></el-icon>
        </span>
    <template #dropdown >
      <el-dropdown-menu>
        <el-dropdown-item command="logout"><el-icon><SwitchButton /></el-icon>logout</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
    </template>
  </el-page-header>

</template>

<script setup>
import {defineProps, onBeforeMount,onMounted} from 'vue';
import {ElMessage} from "element-plus";
import router from '@/router'
const props=defineProps(['username','type'])
const handleCommand=(command)=>{
  if(command==="logout"){
    logout()
  }
}
const logout=()=>{
  localStorage.removeItem("token")
  router.push('/login')
}
</script>

<style scoped>
.avatar{
  width: 30px;
  border-radius: 50%;
  position: relative;
  top: 10px;
  right: 5px;
}
</style>
