<template>
  <div>
    <el-input
      v-model="search"
      placeholder="搜索关键字"
      clearable
      style="margin-bottom: 20px; width: 300px;"
    ></el-input>
    <el-button type="primary" @click="performSearch" style="margin-left: 10px; margin-bottom:20px">搜索</el-button>
    <el-button type="primary" @click="addRow" style="margin-bottom: 20px; margin-left: 480px">新增用户</el-button>
    <el-table :data="filteredData" style="width: 100%">
      <el-table-column fixed prop="uid" label="UID" width="80" />
      <el-table-column prop="name" label="用户名" width="120" />
      <el-table-column prop="password" label="密码" width="270" />
      <el-table-column prop="phone" label="电话号码" width="150" />
      <el-table-column prop="email" label="电子邮箱" width="200" />
      <el-table-column fixed="right" label="Operations" width="160">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleClick(scope.row)">
            编辑
          </el-button>
          <el-button link type="primary" size="small" @click="disableUser(scope.row)">
            禁用
          </el-button>
          <el-button link type="primary" size="small" @click="deleteUser(scope.row)">
            删除
          </el-button>
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
    uid: '111',
    name: 'Tom',
    password: 'Zby521qq@58',
    phone: '1234567890',
    email: 'berry1219@outlook.com',
  },
  {
    uid: '112',
    name: 'Tom',
    password: 'aaaaaaaaaaaaaaaa',
    phone: '1234567890',
    email: '1293419515@qq.com',
  },
  {
    uid: '113',
    name: 'Tom',
    password: '@@@@@@@@@@@@@@@@',
    phone: '8612345678901',
    email: 'Los Angeles',
  },
  {
    uid: '114',
    name: 'Berry',
    password: '################',
    phone: '1234567890',
    email: 'Los Angeles',
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

const handleClick = (row) => {
  console.log('edit', row);
};

const performSearch = () => {
  searchText.value = search.value;
};

const addRow = () => {
  const newRow = {
    uid: (tableData.value.length > 0 ? parseInt(tableData.value[0].uid) + 1 : 1).toString(),
    name: '',
    password: '',
    phone: '',
    email: '',
  };
  tableData.value.unshift(newRow);
};

const disableUser = (row) => {
  console.log('disable', row);
};

const deleteUser = (row) => {
  const index = tableData.value.indexOf(row);
  if (index !== -1) {
    tableData.value.splice(index, 1);
  }
};
</script>
