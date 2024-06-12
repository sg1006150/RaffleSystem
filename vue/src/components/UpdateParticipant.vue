<template>
  <div style="padding: 20px;">
    <el-steps :active="active" direction="vertical">
      <el-step>
        <template #title>修改用户</template>
        <template #description>
          <el-form>
            <el-form-item label="用户名称：" style="width: 300px">
              <el-input v-model="Data.name" placeholder="请输入用户名称" />
            </el-form-item>
          </el-form>
        </template>
      </el-step>

      <el-step>
        <template #description>
          <el-form>
            <el-form-item label="电话号码：" style="width: 300px">
              <el-input v-model="Data.phone" placeholder="请输入电话号码" />
            </el-form-item>
          </el-form>
        </template>
      </el-step>
    </el-steps>
    <div style="text-align: center; margin-top: 20px;">
      <el-button type="primary" @click="save">提交</el-button>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue"; // 确保从 'vue' 中导入了 watch
import { ElMessage } from 'element-plus';
import request from '../utils/request';

const active = ref(0);
const emit = defineEmits(['done']);

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

const save = () => {
  if (Data.value.name === '' || Data.value.phone === '') {
    ElMessage.error("数据非法");
    return;
  }
  request.get("/getCurrentUser").then(response => {
    if (response.data.success) {
      Data.value.addedby = response.data.data.username;
      request.post("/updateParticipant", Data.value).then(response => {
        if (response.data.success) {
          ElMessage.success("修改成功");
          emit('done');
        } else {
          ElMessage.error(response.data.message);
        }
      }).catch(error => {
        ElMessage.error(error.message);
      });
    } else {
      ElMessage.error(response.data.message);
    }
  }).catch(error => {
    ElMessage.error(error.message);
  });
};
</script>

<style scoped>
.el-steps__description {
  width: 100%;
}
</style>
