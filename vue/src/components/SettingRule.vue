<template>
  <div style="padding: 20px;">
    <el-steps :active="active" direction="vertical">
      <el-step>
        <template #title>规则设置</template>
        <template #description>
          <el-form>
            <el-form-item label="规则名称：" style="width: 300px">
              <el-input v-model="rule.rulename" placeholder="请输入规则名称" />
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
            <el-form-item label="人员选择" style="width: 300px;">
              <el-select placeholder="选择参与人员">
                <el-option v-for="(participant,index) in allParticipants"  :key="index" :label="participant.name"></el-option>
              </el-select>
            </el-form-item>
            <el-button>添加</el-button>
          </el-form>
        </template>
      </el-step>
      
      <el-step>
        <template #title>抽奖奖品设置</template>
        <template #description>
          <el-form>
            <el-form-item label="奖品设定：">
              <div v-for="(rule_prize, index) in rule_prizes" :key="index" style="display: flex; align-items: center; margin-bottom: 10px;">
                <el-select v-model="rule_prize.prize_level" placeholder="奖项" style="margin-right: 10px; width: 200px">
                  <el-option label="一等奖" value="一等奖"></el-option>
                  <el-option label="二等奖" value="二等奖"></el-option>
                  <el-option label="三等奖" value="三等奖"></el-option>
                </el-select>
                <el-input-number v-model="rule_prize.count" :min="1" label="数量" style="margin-right: 10px; width: 100px"></el-input-number>
                <el-select v-model="rule_prize.prize_id" placeholder="奖品" style="margin-right: 10px; width: 200px">
                  <el-option v-for="(p,index) in prize" :key="index" :label="p.name" :value="p.id"></el-option>
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
                v-model="rule.description"
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
import {onBeforeMount, ref} from "vue";
import { ElMessage } from 'element-plus';
import request from'../utils/request'
const active = ref(0);
const rule_prizes=ref([])
const rule_participants=ref([])
const prize=ref([])
const usertype=ref()
const allParticipants=ref([])
const rule_participant=ref({
  participant_id:'',
  rule_id:''
})
onBeforeMount(()=>{
  getCurrentUser()
})
const getCurrentUser=()=>{
  request.get('/getCurrentUser').then(response=>{
        if(response.data.success){
          rule.value.createdby=response.data.data.username
          usertype.value=response.data.data.type
          getPrize()
          getParticipant()
        }
      }
  ).catch(error=>{
    ElMessage.error(error.message)
  })
}
const getParticipant=()=>{
  if (usertype.value === 'admin') {
    request.get("/getAllParticipants").then(response => {
      if (response.data.success) {
        allParticipants.value = response.data.data
      } else {
        ElMessage.error(response.data.message)
      }
    })
        .catch(error => {
          ElMessage.error(error.message)
        })
  } else if (usertype.value === 'user') {
    request.get("/getParticipantsByUser").then(response => {
      if (response.data.success) {
        allParticipants.value = response.data.data
      } else {
        ElMessage.error(response.data.message)
      }
    })
        .catch(error => {
          ElMessage.error(error.message)
        })
  }
}// 确保 v-model 变量初始化
const getPrize=()=> {
  if (usertype.value === 'admin') {
    request.get("/getAllPrize").then(response => {
      if (response.data.success) {
        prize.value = response.data.data
        getPrizeRules()
      } else {
        ElMessage.error(response.data.message)
      }
    })
        .catch(error => {
          ElMessage.error(error.message)
        })
  } else if (usertype.value === 'user') {
    request.get("/getPrizeByUser").then(response => {
      if (response.data.success) {
        prize.value = response.data.data
      } else {
        ElMessage.error(response.data.message)
      }
    })
        .catch(error => {
          ElMessage.error(error.message)
        })
  }
}
const rule=ref({
  rulename:'',
  createdby:'',
  description:'',
  istemporary:1,
  id:''
})
const addPrize = () => {rule_prizes.value.push({
  prize_id:'',
      rule_id:'',
      prize_level:'',
      count:''})
};

const removePrize = (index) => {
  if (prizes.value.length > 1) {
    prizes.value.splice(index, 1);
  } else {
    ElMessage.warning('至少要有一个奖品');
  }
};
const save = () => {
  request.post("/addRule",rule.value).then(response=>{
    if(response.data.success){
      rule.value.id=response.data.data.id
    }
    else{
      ElMessage.error(response.data.message)
    }
  })
      .catch(error=>{
        ElMessage.error(error.message)
      })
};
const getPrizeRules=()=>{
    request.get("/get").then(response=>{
      if(response.data.success){
        rule_prizes.value=response.data.data
      }
      else{
        ElMessage.error(response.data.message)
      }
    })
        .catch(error=>{
          ElMessage.error(error.message)
        })
  }
</script>

<style scoped>
.el-steps__description {
  width: 100%;
}
</style>
