import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '../views/Login.vue'
import Register from'../views/Register.vue'
import Manage from '../views/Manage.vue'
import SetPWD from '../views/SetPWD.vue'
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'about',
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
    component: Manage
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
