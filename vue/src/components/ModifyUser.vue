<template>
  <div style="padding: 20px;">
    <el-steps :active="active" direction="vertical">
      <el-step>
        <template #title>修改用户信息</template>
        <template #description>
          <el-form>
            <el-form-item label="用户名称：" style="width: 300px">
              <el-input v-model="nameSetting" placeholder="请输入用户名称" />
            </el-form-item>
          </el-form>
        </template>
      </el-step>
      
      <el-step>
        <template #description>
          <el-form>
            <el-form-item label="电话号码：" style="width: 300px">
              <el-input v-model="phoneSetting" placeholder="请输入电话号码" />
            </el-form-item>
          </el-form>
        </template>
      </el-step>
    </el-steps>
    <div style="text-align: center; margin-top: 20px;">
      <el-button type="primary" @click="save">保存</el-button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { ElMessage } from 'element-plus';

const active = ref(0);
const nameSetting = ref('');
const phoneSetting = ref('');

const save = () => {
  console.log('保存', {
    nameSetting: nameSetting.value,
    participantSetting: participantSetting.value,
    prizes: prizes.value,
    modeSetting: modeSetting.value,
    brief: brief.value
  });
  ElMessage.success('保存成功');
};
</script>

<style scoped>
.el-steps__description {
  width: 100%;
}
</style>
