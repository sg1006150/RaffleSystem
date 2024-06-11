<template>
  <div style="padding: 20px;">
    <el-steps :active="active" direction="vertical">
      <el-step>
        <template #title>规则设置</template>
        <template #description>
          <el-form>
            <el-form-item label="规则名称：" style="width: 300px">
              <el-input v-model="nameSetting" placeholder="请输入规则名称" />
            </el-form-item>
          </el-form>
        </template>
      </el-step>
      
      <el-step>
        <template #title>抽奖设置</template>
        <template #description>
          <el-form>
            <el-form-item label="人员设定：" style="width: 300px">
              <el-select v-model="participantSetting" placeholder="请选择">
                <el-option label="现场扫码报名" value="现场扫码报名"></el-option>
                <el-option label="已导入的抽奖人员" value="已导入的抽奖人员"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </template>
      </el-step>
      
      <el-step>
        <template #title>抽奖奖品设置</template>
        <template #description>
          <el-form>
            <el-form-item label="奖品设定：">
              <div v-for="(prize, index) in prizes" :key="index" style="display: flex; align-items: center; margin-bottom: 10px;">
                <el-select v-model="prize.level" placeholder="奖项" style="margin-right: 10px; width: 200px">
                  <el-option label="一等奖" value="一等奖"></el-option>
                  <el-option label="二等奖" value="二等奖"></el-option>
                  <el-option label="三等奖" value="三等奖"></el-option>
                </el-select>
                <el-input-number v-model="prize.quantity" :min="1" label="数量" style="margin-right: 10px; width: 100px"></el-input-number>
                <el-select v-model="prize.item" placeholder="奖品" style="margin-right: 10px; width: 200px">
                  <el-option label="Iphone13" value="Iphone13"></el-option>
                  <el-option label="Macbook Pro" value="Macbook Pro"></el-option>
                  <el-option label="AirPods" value="AirPods"></el-option>
                </el-select>
                <el-button type="text" @click="removePrize(index)">删除</el-button>
              </div>
              
            </el-form-item>
            <el-button type="primary" @click="addPrize" style="margin-left: 82px; margin-top:-30px">添加</el-button>
          </el-form>
        </template>
      </el-step>
      
      <el-step>
        <template #title>抽奖模式设置</template>
        <template #description>
          <el-form>
            <el-form-item label="模式设定：">
              <el-select v-model="modeSetting" placeholder="请选择" style="width: 300px">
                <el-option label="确定奖项后，随机抽取获奖人" value="确定奖项后，随机抽取获奖人"></el-option>
                <el-option label="确定奖项后，随机抽取获奖人员，但排除已获奖人员" value="确定奖项后，随机抽取获奖人员，但排除已获奖人员"></el-option>
                <el-option label="随机抽取获奖人员后，随机抽取奖品" value="随机抽取获奖人员后，随机抽取奖品"></el-option>
                <el-option label="随机抽取获奖人员后，随机抽取奖品，但排除已抽取完毕奖品" 
                           value="随机抽取获奖人员后，随机抽取奖品，但排除已抽取完毕奖品"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </template>
      </el-step>

      <el-step>
        <template #title>简介设置</template>
        <template #description>
          <el-form>
            <el-form-item label="简介设定：" style="width: 380px">
              <el-input
                v-model="brief"
                :autosize="{ minRows: 2, maxRows: 6 }"
                type="textarea"
                placeholder="请输入简介"
              />
            </el-form-item>
          </el-form>
        </template>
      </el-step>
    </el-steps>
    <div style="text-align: center; margin-top: 20px;">
      <el-button type="primary" @click="save">保存</el-button>
      <el-button>暂存</el-button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { ElMessage } from 'element-plus';

const active = ref(0);
const nameSetting = ref('');
const participantSetting = ref('');
const prizes = ref([
  { level: '', quantity: 1, item: '' }
]);
const modeSetting = ref('');
const brief = ref(''); // 确保 v-model 变量初始化

const addPrize = () => {
  prizes.value.push({ level: '', quantity: 1, item: '' });
};

const removePrize = (index) => {
  if (prizes.value.length > 1) {
    prizes.value.splice(index, 1);
  } else {
    ElMessage.warning('至少要有一个奖品');
  }
};

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
