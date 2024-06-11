<!--
 * 左侧滑动-菜单栏
 *
 * @Author: ShanZhu
 * @Date: 2023-11-11
-->
<template>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
      @open="handleOpen"
      @close="handleClose"
      router
  >
    <h1 style="text-align: center;margin-top: 10px">抽奖系统</h1>
    <el-menu-item index="/manage/rules">
      <el-icon><icon-menu /></el-icon>
      <template #title>抽奖管理</template>
    </el-menu-item>
    <el-sub-menu index="2">
      <template #title>
        <el-icon><location /></el-icon>
        <span>抽奖人员</span>
      </template>
      <el-menu-item-group>
        <template #title><span></span></template>
        <el-menu-item index="1-1">扫码人员</el-menu-item>
        <el-menu-item index="1-2">已有人员</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
    <el-menu-item index="3">
      <el-icon><document /></el-icon>
      <template #title>抽奖奖品</template>
    </el-menu-item>
    <el-menu-item v-if="props.type==='admin'" index="/manage/user">
      <el-icon><setting /></el-icon>
      <template #title>管理客户</template>
    </el-menu-item>
    <el-menu-item index="5">
      <el-icon><setting /></el-icon>
      <template #title>算法维护</template>
    </el-menu-item>
  </el-menu>
</template>
<script setup>
import {defineProps,ref} from "vue";
const props=defineProps(['type'])
const activeIndex = ref('/manage/rules')
</script>
<script>
import request from "@/utils/request";
import router from "@/router";
export default {
  name: "Aside",
  methods: {
    router() {
      return router
    }
  },
  props: {
    isCollapse: Boolean,
  },

  /*初始化数据*/
  data() {
    return {
      role: 'admin',
      /*菜单打开状态*/
      menuFlags: {
        userMenu: false,
        fileMenu: false,
        avatarMenu: false,
        goodMenu: false,
        carouselMenu: false,
        orderMenu: false,
        categoryMenu: false,
        incomeChartMenu: false,
        incomeRankMenu: false,
      }
    }
  },

  computed: {
    userGroup: function () {
      return this.menuFlags.userMenu
    },
    fileGroup: function () {
      return this.menuFlags.fileMenu || this.menuFlags.avatarMenu
    },
    GoodGroup: function () {
      return this.menuFlags.goodMenu || this.menuFlags.orderMenu || this.menuFlags.categoryMenu || this.menuFlags.carouselMenu
    },
    incomeGroup: function () {
      return this.menuFlags.incomeChartMenu || this.menuFlags.incomeRankMenu
    }
  },
  created() {
    /*获取用户角色*/
        /*只有管理员有权限*/
        if (this.role === 'admin') {
          this.menuFlags.userMenu = true
          this.menuFlags.fileMenu = true
          this.menuFlags.avatarMenu = true
          this.menuFlags.categoryMenu = true
          this.menuFlags.goodMenu = true
          this.menuFlags.carouselMenu = true
          this.menuFlags.orderMenu = true
          this.menuFlags.incomeChartMenu = true
          this.menuFlags.incomeRankMenu = true
      }
  }
}
</script>

