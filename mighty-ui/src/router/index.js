import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'
import Login from '@/views/login/Login'
import Main from '@/views/Main'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    // 런타임에 import 시 rendering 지연 발생 할 수 있음.
    // component: () => import(/* webpackChunkName: "login" */ '../views/Login.vue')
    component: Login
  },
  {
    path: '/main',
    name: 'Main',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "main" */ '../views/Main.vue')
    component: Main
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// router.beforeEach((to, from, next) => {

//   console.log('from: ', from);
//   console.log('to: ', to);
//   console.log('isLogin: ', store.state.isLogIn);

//   if (from.name === 'Main' && store.state.isLogIn === true) {
//     next(false);
//   } else {
//     next();
//   } 
// })

export default router
