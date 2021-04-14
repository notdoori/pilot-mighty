import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'
import Login from '@/views/login/Login'
import Main from '@/views/Main'
import users from '@/views/user/users';
import Groups from '@/views/user/Groups';
import Authority from '@/views/user/Authority';
import Inquiry1 from '@/views/Inquiry/Inquiry1';
import Inquiry2 from '@/views/Inquiry/Inquiry2';

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
    component: Main,
    children : [  // nested routes
      // {path: 'authority', component: Authority},
      // {path: 'groups', component: Groups},
      // {path: 'users', component: users},
      // {path: 'inquiry1', component: Inquiry1},
      // {path: 'inquiry2', component: Inquiry2}
      {path: 'M0001', component: Authority},
      {path: 'M0002', component: Groups},
      {path: 'M0003', component: users},
      {path: 'M0021', component: Inquiry1},
      {path: 'M0022', component: Inquiry2}
    ]
  },
  
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
