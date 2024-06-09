
<template>
  <el-page-header @back="goBack">
    <template #content>
      <p>欢迎{{props.username}}</p>
    </template>
  </el-page-header>

</template>

<script setup>
import {defineProps, onBeforeMount,onMounted} from 'vue';
import {ElMessage} from "element-plus";
const props=defineProps(['username','type'])

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
