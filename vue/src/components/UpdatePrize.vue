<template>
    <el-form :model="formData">
        <el-form-item label="奖品名称:" label-width="100">
            <el-input v-model="Data.name" style="width: 20vw"></el-input>
        </el-form-item>
        <el-form-item label="奖品价格:" label-width="100">
            <el-input v-model="Data.price" placeholder="请输入奖品价格" style="width: 20vw"></el-input>
        </el-form-item>
        <el-form-item label="奖品图片:" label-width="100">
            <el-upload
                    :http-request="httpRequest"
                    :show-file-list="true"
                    list-type="picture-card"
                    action="http://localhost:8080/upload"
                    :onremove="handleRemove"
            ><el-icon><Plus /></el-icon>
            </el-upload>
        </el-form-item>
        <div style="margin-left: 100px">
            <el-button @click="emitCancel">取消</el-button>
            <el-button type="primary" @click="onBtn">修改</el-button>
        </div>
    </el-form>
</template>
 
<script setup>
import {ref, reactive, onBeforeMount, watch} from "vue";
import request from '../utils/request'
import { Plus } from '@element-plus/icons-vue'
import {ElMessage}from 'element-plus'
const emit=defineEmits(['cancel','done'])
const props = defineProps({
  Data: {
    type: Object,
    required: true,
  }
});

const Data = ref({ ...props.Data });

watch(props.Data, (newVal) => {
  Data.value = { ...newVal };
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
const getCurrentUser=()=>{
  request.get('/getCurrentUser').then(response=>{
        if(response.data.success){
          Data.value.addedby=response.data.data.username
        }
      }
  ).catch(error=>{
    ElMessage.error(error.message)
  })
}
onBeforeMount(()=>{
  getCurrentUser()
})
async function onBtn() {
   if (Data.value.name === '' || Data.value.price === '') {
     ElMessage.error("输入数据不合法");
     return;
   }

   // 判断是否有文件要上传
   if (fileList.value.length > 0) {
      let dataForm = new FormData();
      fileList.value.forEach((it, index) => {
          dataForm.append('file', it.file);
      });

      try {
          const uploadResponse = await request.post('/upload', dataForm);
          if (uploadResponse.data.success) {
              Data.value.picdirectory = uploadResponse.data.data;
          } else {
              ElMessage.error(uploadResponse.data.message);
              return;
          }
      } catch (error) {
          ElMessage.error(error.message);
          return;
      }
   } else {
      Data.value.picdirectory = null;  // 设置图片目录为 null
   }

   // 进行添加奖品的操作
   try {
       const addResponse = await request.post('/updatePrize', Data.value);
       if (addResponse.data.success) {
           ElMessage.success("修改成功");
           emit("done");
       } else {
           ElMessage.error(addResponse.data.message);
       }
   } catch (error) {
       ElMessage.error(error.message);
   }
}
</script>