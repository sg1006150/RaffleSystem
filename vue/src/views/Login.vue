<script setup>
  import {ref} from 'vue';
  import axios from 'axios';
  import { ElMessage } from 'element-plus'

  const user = ref({
  username: '',
  password: ''
});

const login = () => {
  axios.post('http://localhost:8080/login', user.value)
      .then(response => {
        if(response.data.success){
          ElMessage('Successed')
        }
        else if(!response.data.success){
          ElMessage.error(response.data.message)
        }
      })
      .catch(error => {
        console.log(error);
        alert(error.message().message);
      });

}

</script>

<template>
  <div class="background">
    <div class="mainbox">
      <div style="flex: 1;display: flex;align-items: center;justify-content: center">
        <el-form v-model="user" style="width:80%">
          <div style="font-size: 30px;font-weight: bold;text-align: center;margin-bottom: 10px">Login</div>
          <el-text type="info" style="margin-left: 5px;">info</el-text>
      <el-form-item prop="username" style="margin-top: 5px">
        <el-input placeholder="请输入账号" v-model="user.username"></el-input>
      </el-form-item>
      <div style="display: flex">
        <el-text type="info" style="margin-left: 5px;flex: 1;text-align: left">password</el-text>
        <span style="color: #42b983;cursor: pointer;font-size: 10px;text-align:right" >forgot?</span>
      </div>

      <el-form-item prop="password" style="margin-top: 5px">
        <el-input show-password placeholder="请输入密码" v-model="user.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="login" size="large" style="width: 100%">登录</el-button>
      </el-form-item>
      <el-text type="info">还没有账号？请 <span style="color: #42b983 ;cursor: pointer">注册</span> </el-text>
    </el-form>
    </div>
  </div>
</div>
</template>

<style scoped>
.background
{
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: cornflowerblue;
  margin : 0;
  padding : 0;
}
.mainbox
{
  height: 60%;
  display: flex;
  width: 20%;
  background-color: aliceblue;
  border-radius: 5px;
}
</style>