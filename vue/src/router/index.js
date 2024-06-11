import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '../views/Login.vue'
import Register from'../views/Register.vue'
import Manage from '../views/Manage.vue'
import SetPWD from '../views/SetPWD.vue'
import ManageUsers from '../components/ManageUsers.vue'
import LotteryRules from '../components/LotteryRules.vue'
import SettingPrize from '../components/SettingPrize.vue'
import SettingRule from'../components/SettingRule.vue'
import ManagePrizes from '../components/ManagePrizes.vue'
import ExistUsers from '../components/ExistUsers.vue'
import QRUsers from '../components/QRUsers.vue'
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/manage',
    redirect: '/manage/rules'
  },
  {
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component:Login
  },
  {
    path:'/register',
    name:'register',
    component:Register
  },
  {
    path: '/manage',
    name: 'manage',
    component: Manage,
    children:[{
          path:'user',
          component:ManageUsers
        },
      {
        path:'rules',
        component: LotteryRules
      },
      {
        path: 'prize',
        component:ManagePrizes,
        children:[{
          path: 'add',
          component: SettingPrize
        }]
      },
      {
        path: 'exist',
        component: ExistUsers
      },
      {
        path:'qruser',
        component: QRUsers
      }]
  },
  {
    path: '/setpwd',
    name: 'setpwd',
    component: SetPWD,
    meta: { requiresReferrer: true }
  }

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router