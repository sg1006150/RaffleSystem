<template>
  <div>
    <el-input
      v-model="search"
      placeholder="搜索关键字"
      clearable
      style="margin-bottom: 20px; width: 300px;"
    ></el-input>
    <el-dialog v-model="addDialogVisible"><add @cancel="handleDialogClose"></add></el-dialog>
    <el-button type="primary" @click="performSearch" style="margin-left: 10px; margin-bottom:20px">搜索</el-button>
    <el-button type="primary" style="margin-bottom: 20px; margin-left: 875px" @click="addDialogVisible=true">新增规则</el-button>
    <el-dialog v-model="addDialogLotteryVisible"><add2 @cancel="handleDialogClose"></add2></el-dialog>
    <el-button 
      type="primary" 
      style="margin-top: -95px; margin-left: 800px" 
      @click="addDialogLotteryVisible=true"
      :disabled="!selectedRow"
    >抽奖</el-button>
    <el-table :data="filteredData" border style="width: 100%">
      <el-table-column label="选择" width="60">
        <template #default="{ row }">
          <el-checkbox :model-value="selectedRow === row.No" @change="handleCheckboxChange(row.No)"></el-checkbox>
        </template>
      </el-table-column>
      <el-table-column prop="No" label="编号" width="80" />
      <el-table-column prop="rulename" label="规则名称" width="200" />
      <el-table-column prop="create_time" label="创建时间" width="120" />
      <el-table-column prop="creator" label="创建人" width="120" />
      <el-table-column prop="sector" label="所属部门" width="150" />
      <el-table-column prop="summary" label="简介" width="600" />
      <el-table-column fixed="right" label="操作" width="120">
        <template #default="{ row }">
          <el-button link type="primary" size="small" @click="handleClick">编辑</el-button>
          <el-button link type="primary" size="small" @click="deleteRow(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import add from './SettingRule.vue';
import add2 from './Lottery.vue';
import { ElMessageBox, ElMessage } from 'element-plus';

const addDialogVisible = ref(false);
const addDialogLotteryVisible = ref(false);
const search = ref('');
const searchText = ref('');
const router = useRouter();
const handleDialogClose = () => {
  addDialogVisible.value = false;
  addDialogLotteryVisible.value = false;
};
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
const selectedRow = ref(null); // 选中的行

const handleCheckboxChange = (rowNo) => {
  if (selectedRow.value === rowNo) {
    selectedRow.value = null; // 取消选择
  } else {
    selectedRow.value = rowNo; // 选择新的行
  }
};

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

const deleteRow = (row) => {
  ElMessageBox.confirm(
    '您确定要删除这条规则吗？', 
    '警告', 
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    const index = tableData.value.findIndex(item => item.No === row.No);
    if (index !== -1) {
      tableData.value.splice(index, 1);
      ElMessage({
        message: '删除成功',
        type: 'success',
      });
    }
  }).catch(() => {
    
  });
};

const performSearch = () => {
  searchText.value = search.value;
};
</script>

<style scoped>
</style>
