<template>
    <div class="background">
        <div class="mainbox">
            <div style="flex: 1;display: flex;align-items: center;justify-content: center">
                <el-form :inline="true" :model="registerFrom" class="demo-form-inline">
                  <div style="text-align: center;font-size: 35px;font-weight: bold;margin-bottom: 30px">注册账号</div>
                    <div style="display: flex; align-items: center;margin-bottom: 20px">
                        <el-form-item label="手机号" style="margin-right: 0;">
                            <el-select v-model="registerFrom.region" style="width: 70px;margin-right: 10px">
                                <el-option label="86" value="86"></el-option>
                                <el-option label="87" value="87"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="" style="flex: 1;">
                            <el-input v-model="registerFrom.phone" placeholder="请输入手机号码"></el-input>
                        </el-form-item>
                    </div>
                    <div style="display: flex; align-items: center;">
                        <el-form-item label="验证码">
                            <el-input v-model="registerFrom.code" placeholder="请输入验证码"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="send" :disabled="!isSend" style="min-width: 100px; min-height: 30px;">{{ sendmsg }}</el-button>
                        </el-form-item>
                    </div>
                    <div class="registersubmit" style="display: flex; flex-direction: column; align-items: center; margin-top: 40px;">
                        <div style="display: flex; align-items: center; font-size:10px">
                            <el-checkbox v-model="registerFrom.agree" label="注册即表示您已阅读并同意" name="type"></el-checkbox>
                            <el-button type="text" @click="open" style="font-size: 14px;color: #42b983">《用户使用协议》</el-button>
                        </div>
                        <el-button type="primary" style="width:50%; margin-top: 20px;" @click="onSubmit">注册</el-button>
                    </div>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
import { ElMessage } from 'element-plus';
import axios from "axios";
import router from "@/router";
import request from'../utils/request';
export default {
  name: 'Register',
  data() {
    return {
      registerFrom: {
        phone: '',
        code: '',
        region: '86',
        agree: false//复选框的绑定
      },
      isSend: true,
      sendmsg: '发送短信',
      timer: null, // 用于保存定时器ID

    }
  },
  methods: {
    send() {
      if (!this.registerFrom.phone) {
        ElMessage.error("请填写手机号后获取验证码！")
      } else if (/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.registerFrom.phone) == false) {
        ElMessage.error("请填写正确的手机号后获取验证码！");
      } else {
        // 模拟 API 请求
        setTimeout(() => {
          ElMessage.success("验证码发送成功！");
          this.isSend = false;
          let countdown = 60;
          this.sendmsg = `${countdown}s`;

          // 清除之前的计时器
          if (this.timer) {
            clearInterval(this.timer);
          }

          this.timer = setInterval(() => {
            countdown--;
            this.sendmsg = `${countdown}s`;

            if (countdown === 0) {
              clearInterval(this.timer);
              this.isSend = true;
              this.sendmsg = "重新发送";
            }
          }, 1000);
        }, 500);
      }
    },
    onSubmit() {
      // 注册提交逻辑
      if (!this.registerFrom.agree) {
            ElMessage.error("没有选择用户使用协议");
            return;
        }
      else if(/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.registerFrom.phone) == false) {
        ElMessage.error("请填写正确的手机号！");
      }
      else
      {
        request.post('/register', this.transmitdata(this.registerFrom))
            .then(response => {
              if(response.data.success){
                ElMessage('Successed...Redirecting to login page.')
                setTimeout(() => {
                  router.push('/login');
                }, 2000);

              }
              else if(!response.data.success){
                ElMessage.error(response.data.message)
              }
            })
            .catch(error => {
              console.log(error);
              alert(error.message);
            });
      }
      console.log("注册提交");
    },
    transmitdata(formdata){
      return{
        phone:formdata.phone,
        password:formdata.phone
      }
    },
    open() {
        this.$confirm('欢迎申请使用石家庄友曼网络科技有限公司旗下游戏媒体“游民星空”提供的服务。请您（或代称“用户”）仔细阅读以下全部内容。如用户不同意本服务条款任意内容，请勿注册或使用游民服务。如用户通过进入注册程序并勾选“我同意游民星空帐号服务条款”，即表示用户与游民星空已达成协议，自愿接受本服务条款的所有内容。此后，用户不得以未阅读本服务条款内容作任何形式的抗辩', '用户使用协议', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(() => {
          this.registerFrom.agree = true; // 勾选复选框
        }).catch(() => {
                    
        });
      }
  },
  beforeDestroy() {
    // 清除计时器以避免内存泄漏
    if (this.timer) {
      clearInterval(this.timer);
    }
  },
  
}
</script>

<style scoped>
.background {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: cornflowerblue;
  margin: 0;
  padding: 0;
}
.mainbox {
  height: 50%;
  display: flex;
  width: 30%;
  background-color: aliceblue;
  border-radius: 5px;
}
</style>
