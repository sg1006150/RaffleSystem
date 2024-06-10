<template>
  <div>
    <el-input
      v-model="search"
      placeholder="搜索关键字"
      clearable
      style="margin-bottom: 20px; width: 300px;"
    ></el-input>
    <el-button type="primary" @click="performSearch" style="margin-left: 10px; margin-bottom:20px">搜索</el-button>
    <el-button type="primary" @click="addRow" style="margin-bottom: 20px; margin-left: 875px">新增规则</el-button>
    <el-table :data="filteredData" border style="width: 100%">
      <el-table-column fixed prop="No" label="编号" width="80" />
      <el-table-column prop="rulename" label="规则名称" width="200" />
      <el-table-column prop="create_time" label="创建时间" width="120" />
      <el-table-column prop="creator" label="创建人" width="120" />
      <el-table-column prop="sector" label="所属部门" width="150" />
      <el-table-column prop="summary" label="简介" width="600" />
      <el-table-column fixed="right" label="操作" width="120">
        <template #default>
          <el-button link type="primary" size="small" @click="handleClick">
            编辑
          </el-button>
          <el-button link type="primary" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const search = ref('');
const searchText = ref('');
const tableData = ref([
  {
    No: '20',
    rulename: 'Tom',
    create_time: '2024-6-10',
    creator: 'Los Angeles',
    sector: 'No. 189, Grove St, Los Angeles',
    summary: 'CA 90036',
    tag: 'Home',
  },
  {
    No: '19',
    rulename: 'Berry',
    create_time: '2024-6-10',
    creator: 'Los Angeles',
    sector: 'No. 189, Grove St, Los Angeles',
    summary: 'CA 90036',
    tag: 'Office',
  },
  {
    No: '18',
    rulename: 'Tom',
    create_time: '2024-6-10',
    creator: 'Los Angeles',
    sector: 'No. 189, Grove St, Los Angeles',
    summary: 'CA 90036',
    tag: 'Home',
  },
  {
    No: '17',
    rulename: 'Tom',
    create_time: '2024-6-10',
    creator: 'Los Angeles',
    sector: 'No. 189, Grove St, Los Angeles',
    summary: 'CA 90036',
    tag: 'Office',
  },
]);

const filteredData = computed(() => {
  if (!searchText.value) {
    return tableData.value;
  }
  return tableData.value.filter((item) =>
    Object.values(item).some(val => val.toString().toLowerCase().includes(searchText.value.toLowerCase()))
  );
});

const handleClick = () => {
  console.log('click');
};

const performSearch = () => {
  searchText.value = search.value;
};

const addRow = () => {
  const newRow = {
    No: '',
    rulename: '',
    create_time: '',
    creator: '',
    sector: '',
    summary: '',
    tag: ''
  };
  tableData.value.push(newRow);
};
</script>
