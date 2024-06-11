<template>
    <el-form :model="formData">
        <el-form-item label="奖品名称:" label-width="100">
            <el-input v-model="formData.prizeName" style="width: 20vw"></el-input>
        </el-form-item>
        <el-form-item label="奖品数量数量:" label-width="100">
            <el-input v-model="formData.prizeAmount" placeholder="请输入奖品数量" style="width: 20vw"></el-input>
        </el-form-item>
        <el-form-item label="奖品图片:" label-width="100">
            <el-upload
                    :http-request="httpRequest"
                    multiple
                    :show-file-list="true"
                    list-type="picture-card"
            ><el-icon><Plus /></el-icon>
            </el-upload>
        </el-form-item>
        <div style="margin-left: 100px">
            <el-button @click="emitCancel">取消</el-button>
            <el-button type="primary" @click="onBtn">添加</el-button>
        </div>
    </el-form>
</template>
 
<script setup>
import {ref, reactive} from "vue";
import axios from "axios";
import { Plus } from '@element-plus/icons-vue'
import {ElMessage}from 'element-plus'
 const emit=defineEmits(['cancel'])
const formData = reactive({
    prizeName: '',
    prizeAmount: '',
});
 const emitCancel=()=>{
    emit('cancel')
    ElMessage('click')
 }
//定义一个响应式数组用来接收图片
const fileList = ref([])
 
//自定义函数用来覆盖原有的XHR行为（默认提交行为）
function httpRequest(option) {
//将图片存到数组中
    fileList.value.push(option)
}
 
async function onBtn() {
    let dataForm = new FormData();
    dataForm.append('prizeName', formData.prizeName)
    dataForm.append('prizeAmount', formData.prizeAmount)
 
//将图片的二进制通过表单的形式发送到后台
    fileList.value.forEach((it,index)=>{
        dataForm.append('filename',it.file)
    })
    await axios({
        method: 'POST',
        url: '',
        data: dataForm,
//设置请求参数的规则
        headers: {
            "Content-Type": "multipart/form-data"
        }
    }).then(response => {
        console.log(response.data)
    })
}
</script>