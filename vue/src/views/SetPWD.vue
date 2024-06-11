<template>
  <div class="background">
    <div class="mainbox">
      <el-form ref="psdform" :model="form" :rules="rules" label-width="auto" style="margin-left:0px">
        <div style="text-align: center;font-size: 35px;font-weight: bold;margin-bottom: 30px">更新信息</div>
        <el-form-item label="用户名:" prop="userName" >
          <el-input
              v-model="form.userName"
              placeholder="输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item label="新密码:" prop="newPassword" >
          <el-input
            :type="newPsdtype"
            v-model="form.newPassword"
            placeholder="8-16位密码，区分大小写"
          ></el-input>
          <div class="warningtext" style="margin-top: 3px;line-height: 20px">
            密码应由8-16位数字、字母、符号组成。请不要使用容易被猜到的密码 密码分数{{passwordscore}}
          </div>
          
        </el-form-item>
        <el-form-item label="密码强度:" >
        <div class="intensity" style="margin-bottom: 0px;margin-left: 5px;display: flex">
            <span class="line" :class="[level.includes('low') ? 'low' : '']" style="flex: 1"></span>
            <span class="line" :class="[level.includes('middle') ? 'middle' : '']" style="flex: 1"></span>
            <span class="line" :class="[level.includes('high') ? 'high' : '']" style="flex: 1"></span>
        </div>
        </el-form-item>
        <el-form-item label="确认密码:" prop="confirmPassword">
          <el-input
            :type="confirmPsdtype"
            v-model="form.confirmPassword"
            placeholder="确认密码"
            class="confirm-password-input"
          ></el-input>
        </el-form-item>
        <el-form-item
          prop="email"
          label="电子邮箱:"
          :rules="[
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
          ]"
        >
          <el-input v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="验证码:" prop="code">
          <el-input v-model="form.code" placeholder="请输入验证码"></el-input>
        </el-form-item>
        <div class="code-container">
            <canvas id="checkCodeCanvas" class="code" width="150" height="60"></canvas>
            <a href="javascript:void(0)" class="change-code" @click.prevent="createCode" style="color: #42b983">看不清,换一张</a>
          </div>
        <el-button type="primary" class="submit-button" @click="handleSubmit">确定</el-button>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import {ref}from 'vue'
import request from '../utils/request'
import {ElMessage} from "element-plus";
const currentUser=ref(
    {
      phone:'',
      email:'',
      password:'',
      username:''
    }
);
</script>
<script>
import {ElMessage} from "element-plus";
import request from "../utils/request";
import router from "@/router";
export default {
  name: 'ForgotPWD',
  data() {
    return {
      form: {
        newPassword: '',
        confirmPassword: '',
        code: '',
        email: '',
        phone:'',
        userName:''
      },
      rules: {
        newPassword: [
          { required: true, validator: this.checkPassword, trigger: 'change' }
        ],
        userName:[{required:true,message: '请输入用户名', trigger: 'change'}],
        confirmPassword: [
          {
            required: true,
            validator: this.checkConfirmPassword,
            trigger: 'blur'
          }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        code: [
          { required: true, validator: this.checkCode, trigger: 'blur' }
        ]
      },
      level: [],
      newPsdtype: 'password', // 初始化 newPsdtype 属性
      confirmPsdtype: 'password', // 初始化 confirmPsdtype 属性
      codeValue: '', // 添加 code 变量
      codetype: 'text', // 初始化 codetype 属性
      passwordscore:0
    }
  },
  mounted() {
    this.createCode(); // 页面加载时生成验证码
    this.getCurUser();
  },
  methods: {
    // 校验密码
    checkPassword(rule, value, callback) {
      this.calculateScore()
      this.level = [];
      if (!value) {
        return callback(new Error('密码不能为空'));
      }
      if (value.length < 8) {
        return callback(new Error('密码不少于8位'));
      }
      if (value.length > 16) {
        return callback(new Error('密码不大于16位'));
      }
      // 校验是数字
      const regex1 = /^\d+$/;
      // 校验字母
      const regex2 = /^[A-Za-z]+$/;
      // 校验符号
      const regex3 = /^[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、]+$/;
      if (regex1.test(value) || regex2.test(value) || regex3.test(value)) {
        this.level.push('low');
      } else if (/^[A-Za-z\d]+$/.test(value)) {
        this.level.push('low');
        this.level.push('middle');
      } else if (/^[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、\d]+$/.test(value)) {
        this.level.push('low');
        this.level.push('middle');
      } else if (/^[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、A-Za-z]+$/.test(value)) {
        this.level.push('low');
        this.level.push('middle');
      } else if (/^[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、A-Za-z\d]+$/.test(value)) {
        this.level.push('low');
        this.level.push('middle');
        this.level.push('high');
      }
      return callback();
    },
    // 确认密码
    checkConfirmPassword(rule, value, callback) {
      if (!value) {
        return callback(new Error('请输入确认密码'));
      }
      if (value != this.form.newPassword) {
        return callback(new Error('两次密码输入不一致，请重新输入'));
      }
      return callback();
    },
    checkCode(rule, value, callback) {
      if (!value) {
        return callback(new Error('请输入验证码'));
      }
      else if (value.toUpperCase() !== this.codeValue) {
        callback(new Error('验证码错误'));
      } else {
        callback();
      }
    },
    createCode() {
      this.codeValue = '';
      const codeLength = 4; // 验证码的长度
      const checkCodeCanvas = document.getElementById("checkCodeCanvas");
      const ctx = checkCodeCanvas.getContext("2d");
      ctx.clearRect(0, 0, checkCodeCanvas.width, checkCodeCanvas.height); // 清除画布

      ctx.fillStyle = "#A7ECFD"; // 背景颜色
      ctx.fillRect(0, 0, checkCodeCanvas.width, checkCodeCanvas.height);

      const selectChar = [2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

      for (let i = 0; i < codeLength; i++) {
        const charIndex = Math.floor(Math.random() * selectChar.length);
        const char = selectChar[charIndex];
        this.codeValue += char;

        // 设置随机颜色
        ctx.fillStyle = this.getRandomColor();
        // 设置随机字体大小
        ctx.font = `${Math.floor(Math.random() * 10 + 30)}px Arial`;
        // 设置随机旋转角度
        const angle = Math.random() * Math.PI / 4 - Math.PI / 8;
        ctx.save();
        ctx.translate(30 * i + 15, 30);
        ctx.rotate(angle);
        ctx.fillText(char, -10, 10);
        ctx.restore();
      }
      this.form.code = ''; // 重置输入框的值
    },
    calculateScore(){
      let score=0;
      let password=this.form.newPassword
      if(password.length===0){
        score=0;
      }
      else if (password.length <= 4) {
        score += 5;
      } else if (password.length <= 7) {
        score += 10;
      } else {
        score += 25;
      }
      const hasLowerCase = /[a-z]/.test(password);
      const hasUpperCase = /[A-Z]/.test(password);
      if (!hasLowerCase && !hasUpperCase) {
        score += 0; // 没有字母
      } else if (hasLowerCase && hasUpperCase) {
        score += 20; // 大小写混合字母
      } else {
        score += 10; // 全是大写或全是小写字母
      }
      const digitCount = (password.match(/\d/g) || []).length;
      if (digitCount === 0) {
        score += 0;
      } else if (digitCount >= 1) {
        score += 10;
      }
      this.passwordscore=score
    },
    handleSubmit() {
      this.$refs.psdform.validate((valid) => {
        if (valid) {
          if(this.form.newPassword===this.form.phone){
            ElMessage.error("新密码不能与手机号一致!")
            return
          }
          request.post('/updateinform',this.reformatData()).then(
              response=>{
                if(response.data.success)
                {
                  ElMessage.success("Success...redircting to login page")
                  setTimeout(() => {
                    router.push('/login');
                  }, 1000);
                }
                else
                {
                  ElMessage.error(response.data.message)
                }
              }
          ).catch(error=>{
            ElMessage.error(error.message)
          })
        } else {
          return false;
        }
      });
    },
    getRandomColor() {
      const letters = '0123456789ABCDEF';
      let color = '#';
      for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
      }
      return color;
    },
    reformatData(){
      return {
        password:this.form.newPassword,
        email:this.form.email,
        phone:this.form.phone,
        username:this.form.userName
      }

    },
    getCurUser(){
        request.get('getCurrentUser').then(response=>{
              if(response.data.success)
              {
                this.form.phone=response.data.data.phone
                ElMessage(this.form.phone)
              }
              else
              {
                ElMessage("Something Wrong")
              }
            }
        ).catch(error=>{
          ElMessage(error.message)
        })
      }
  }
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
  width: 27%;
  background-color: aliceblue;
  border-radius: 5px;
  padding: 20px;
}
.warningtext {
  color: #858383;
  font-size: 12px;
  margin-top: -10px;
  text-align: left;
  margin-left: 0px;
}
.warningtext.code-warningtext {
  text-align: left;
  margin-left: 120px; /* Align with the input field */
}
.intensity {
  display: flex; /* 设置强度容器为弹性布局 */
  align-items: center; /* 垂直居中 */
  margin-left: 50px; 
  margin-bottom:30px;
}
.intensity .psdText {
  font-size: 14px;
  color: rgb(96, 98, 102);
  margin-right: 15px;
  
}
.intensity .line {
  display: inline-block;
  width: 100px;
  height: 4px;
  background: #d8d8d8;
  border-radius: 3px;
  margin-right: 8px;
}
.intensity .line.low {
  background: #f4664a;
}
.intensity .line.middle {
  background: #ffb700;
}
.intensity .line.high {
  background: #2cbb79;
}
.confirm-password-input {
  border-color: blue; /* Make the confirm password input blue */
}
.code {
  font-family: Arial, 宋体;
  font-style: italic;
  color: rgb(42, 104, 185);
  border: 0;
  padding: 2px 3px;
  letter-spacing: 3px;
  font-weight: bolder;
  width: 150px; 
  height: 60px; 
  font-size: 30px;
}
.code-container {
  display: flex;
  align-items: center;
  margin-top: 10px;
  margin-left: 118px;
}
.change-code {
  margin-left: 10px;
  margin-top: 48px;
  font-size: 12px;
  text-decoration:none;
}
.submit-button {
  width: 40%;
  margin-top: 20px;
}
</style>