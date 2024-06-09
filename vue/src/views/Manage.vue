<template>
<el-container>
  <el-aside width="200px"><Aside></Aside></el-aside>
  <el-container>
  <el-header height="100px"><Header></Header></el-header>
</el-container>
</el-container>

</template>

<style>
.el-header {
  background-color: #ffffff;
  color: #000000;
  line-height: 60px;
}


/*.bk {
  width: 100%;
  !*项目背景图*!
  background: url("@/resource/img/back.jpg") center center no-repeat;
  background-size: 100% 100%;
}*/

</style>

<script>
import Aside from "@/components/Sidebar.vue";
import Header from "@/components/Head.vue";

export default {
  //页面初始配置
  data() {
    return {
      user: {},
      isCollapse: false,
      sideWidth: 250,
      collapseIcon: "el-icon-s-fold",
      collapseTitle: "收缩",
    };
  },
  //组件
  components: {
    Aside,
    Header,
  },

  //方法
  methods: {
    //折叠方法
    handleCollapse() {
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        //点击收缩按钮
        this.sideWidth = 64;
        this.collapseIcon = "el-icon-s-unfold";
        this.collapseTitle = "展开";
      } else {
        //点击展开按钮
        this.sideWidth = 250;
        this.collapseIcon = "el-icon-s-fold";
        this.collapseTitle = "收缩";
      }
    },
    //获取用户信息方法
    getUser() {
      let username = localStorage.getItem("user")
          ? JSON.parse(localStorage.getItem("user")).username
          : "";
      if (username) {
        //请求用户信息
        this.request.get("/userinfo/" + username).then((res) => {
          //赋值用户信息
          this.user = res.data;
        });
      }
    },
  },
  created() {
    this.getUser();
  },
};
</script>
