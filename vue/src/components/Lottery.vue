<template>  
  <div>  
    <el-card>  
      <h2>{{ lotteryName }}</h2>
      <el-form>  
        <el-form-item label="奖项">  
          <el-select v-model="selectedPrize" placeholder="请选择奖项" style="width:300px">  
            <el-option  
              v-for="item in prizes"  
              :key="item.id"  
              :label="`${item.name} - ${item.prizeName}`"
              :value="item.id" 
            ></el-option>  
          </el-select>  
        </el-form-item>  
        <el-form-item label="抽奖得主">  
          <div v-for="(winner, index) in winners" :key="index">  
            <p>{{ winner.prizeName }} 获得者: <input type="text" v-model="winner.name"></p>  
          </div>   
        </el-form-item>  
        <el-form-item>  
          <el-button type="primary" @click="drawLottery">抽奖</el-button>  
        </el-form-item>  
      </el-form>  
    </el-card>  
  </div>  
</template>  
  
<script>  
import { ref, onMounted } from 'vue';  
  
export default {  
  name: 'LotteryPage',  
  setup() {  
    const lotteryName = ref('Berry的抽奖'); 
    const prizes = ref([  
      { id: 1, name: '一等奖', prizeName: 'iPhone 13' },  
      { id: 2, name: '二等奖', prizeName: 'Macbook' },  
      { id: 3, name: '三等奖', prizeName: 'airpods' }
    ]); 
    const selectedPrize = ref(null); // 用户选择的奖项  
    const winners = ref([]); // 抽奖得主和奖品，初始为空  
  
    // 抽奖逻辑（简化版，仅作示例）  
    function drawLottery() {  
      console.log('Selected Prize ID:', selectedPrize.value); // 调试日志
      winners.value = []; // 每次抽奖前清空 winners 数组
      if (selectedPrize.value === 1) {  
        winners.value.push({ name: '张三' }); // 假设有两个一等奖获奖者
        winners.value.push({ name: '张八' });
      } else if (selectedPrize.value === 2) {  
        winners.value.push({ name: '李四' }); // 假设只有一个二等奖
      } else if (selectedPrize.value === 3) {
        winners.value.push({ name: '王五' }); // 假设只有一个三等奖
      }
      console.log('Winners List:', winners.value); // 调试日志
    }  
  
    return {  
      lotteryName,  
      prizes,  
      selectedPrize,  
      winners,  
      drawLottery,  
    };  
  },  
};  
</script>  
  
<style scoped>  

</style>
