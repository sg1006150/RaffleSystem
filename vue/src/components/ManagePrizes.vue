<template>
  <div>
    <el-input
      v-model="search"
      placeholder="搜索关键字"
      clearable
      style="margin-bottom: 20px; width: 300px;"
    ></el-input>
    <el-button type="primary" @click="performSearch" style="margin-left: 10px; margin-bottom:20px">搜索</el-button>
    <el-button type="primary" style="margin-bottom: 20px; margin-left: 200px" index="/manage/prize/add">新增奖品</el-button>
    <el-table :data="filteredData" style="width: 100%">
      <el-table-column fixed prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="名称" width="120" />
      <el-table-column label="图片" width="120">
        <template #default="scope">
          <img :src="scope.row.image" alt="Image" style="width: 50px; height: 50px;" />
        </template>
      </el-table-column>
      <el-table-column prop="amount" label="数量" width="120" />
      <el-table-column fixed="right" label="Operations" width="160">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleClick(scope.row)">
            编辑
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
    id: '111',
    name: 'Tom',
    amount: '2',
    image: '../assets/logo.png',
  },
  {
    id: '112',
    name: 'Tom',
    amount: '2',
    image: '../assets/logo.png',
  },
  {
    id: '113',
    name: 'Tom',
    amount: '2',
    image: '../assets/logo.png',
  },
  {
    id: '114',
    name: 'Berry',
    amount: '2',
    image: '../assets/logo.png',
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

// const addRow = () => {
//   const newRow = {
//     uid: (tableData.value.length > 0 ? parseInt(tableData.value[0].uid) + 1 : 1).toString(),
//     name: '',
//     password: '',
//     phone: '',
//     email: '',
//     image: 'path/to/newImage.jpg', // 新增行时需要设置一个默认图片路径
//   };
//   tableData.value.unshift(newRow);
// };

const deleteUser = (row) => {
  const index = tableData.value.indexOf(row);
  if (index !== -1) {
    tableData.value.splice(index, 1);
  }
};
</script>

<style scoped>
/* 你的样式 */
</style>
