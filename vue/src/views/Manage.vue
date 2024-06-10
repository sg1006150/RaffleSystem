<template>
<el-container>
  <el-aside width="200px"><Aside></Aside></el-aside>
  <el-container>
  <el-header height="100px"><Header :username="user.username"></Header></el-header>
  <el-main><SettingPrize></SettingPrize></el-main>
</el-container>
</el-container>

</template>

<style>

</style>

<script setup>
import Aside from "@/components/Sidebar.vue";
import Header from "@/components/Head.vue";
import LotteryRules from "@/components/LotteryRules.vue"
import ManageUsers from "@/components/ManageUsers.vue"
import SettingRule from "@/components/SettingRule.vue"
import ManagePrizes from "@/components/ManagePrizes.vue"
import SettingPrize from "@/components/SettingPrize.vue"
import {ref,onBeforeMount} from 'vue';
import request from'../utils/request'
import {ElMessage} from "element-plus";
const user=ref(
      {
        type:'admin',
        username:''
      }
  );
onBeforeMount(()=>{
  request.get('/getCurrentUser').then(response=>{
    if(response.data.success){
      user.value.username=response.data.data.username
      user.value.type=response.data.data.type
    }
      }
  ).catch(error=>{
    ElMessage.error(error.message)
  })
})
</script>
