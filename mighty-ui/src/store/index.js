import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: null,
    accessToken: null,
    isLogIn: false,
  },
  mutations: {
    // 로그인 성공
    LoginSuccess(state, payload) {
      state.user = payload.USERID;
      state.accessToken = payload.ACCESSTOKEN;
      state.isLogIn = true;
    },
    // 로그인 실패
    LoginFailure(state) {
      state.user = null;
      state.accessToken = null;
      state.isLogIn = false;
    }
  },
  actions: {
    actRouteMove(context, targetRouteName) {
      if (router.currentRoute.name !== targetRouteName) {
        console.log('cur: ', router.currentRoute.name, ', target: ', targetRouteName);
        router.push({name: targetRouteName});
      }
    },
    actLogin({dispatch, commit}, payload) {
      axios.post('http://127.0.0.1:8080/api/users/login', payload)
       .then(response => {

          //console.log(response);
        
          let Id = response.data.USERID;
          let Token = response.data.ACCESSTOKEN;

          if (Id !== null && Token !== null) {

            // sessionStorage.setItem("user_id", Id);
            // sessionStorage.setItem("access_token", Token);
            //dispatch('addSessionStorage', {Id, Token});
            dispatch('addLocalStorage', {Id, Token});

            commit('LoginSuccess', response.data);
            //router.push({name: 'Main'});
            dispatch('actRouteMove', 'Main');
          }

       })
       .catch(error => {
         console.log(error);
         //console.log(error.response.data);
         alert('아이디와 비밀번호를 확인 하세요.');
         commit('LoginFailure');
       });
    },
    actLogout(context) {
      console.log('Logout');

      //let userId = sessionStorage.getItem("user_id");
      //let token = sessionStorage.getItem("access_token");
      let userId = localStorage.getItem("user_id");
      let token = localStorage.getItem("access_token");

      axios.post('http://127.0.0.1:8080/api/users/logout', {userId:userId, token:token})
       .then(response => {
        //context.dispatch('clearlocalStorage');
        context.dispatch('clearLocalStorage');
        context.commit('LoginFailure');
        //router.push({name: 'Login'});
        context.dispatch('actRouteMove', 'Login');
       })
       .catch(error => {
        //context.dispatch('clearSessionStorage');
        context.dispatch('clearLocalStorage');
        context.commit('LoginFailure');
        //router.push({name: 'Login'});
        context.dispatch('actRouteMove', 'Login');
       });
    },
    actLogoutYN(context) {
      console.log('LogoutYN');

      if (confirm('로그아웃 하시겠습니까?') === false) {
        return;
      }

      //let userId = sessionStorage.getItem("user_id");
      //let token = sessionStorage.getItem("access_token");
      let userId = localStorage.getItem("user_id");
      let token = localStorage.getItem("access_token");

      axios.post('http://127.0.0.1:8080/api/users/logout', {userId:userId, token:token})
       .then(response => {
        //context.dispatch('clearlocalStorage');
        context.dispatch('clearLocalStorage');
        context.commit('LoginFailure');
        //router.push({name: 'Login'});
        context.dispatch('actRouteMove', 'Login');
       })
       .catch(error => {
        //context.dispatch('clearSessionStorage');
        context.dispatch('clearLocalStorage');
        context.commit('LoginFailure');
        //router.push({name: 'Login'});
        context.dispatch('actRouteMove', 'Login');
       });
    },
    actCheckLogin(context) {
      // let Token = sessionStorage.getItem("access_token");
      // let userId = sessionStorage.getItem("user_id");
      let Token = localStorage.getItem("access_token");
      let userId = localStorage.getItem("user_id");

      if (Token === null) {
        context.commit('LoginFailure');
        return;
      }

      //console.log('Token: ', Token);

      axios.post('http://127.0.0.1:8080/api/users/check', {userId: userId, token: Token})
      .then(response => {
        
        //console.log(response);

        if (response.status === 200) {
          console.log("Success 200");

          let Id = response.data.USERID;
          let Token = response.data.ACCESSTOKEN;

          console.log('New Token: ', Token);
          
          context.dispatch('addLocalStorage', {Id, Token});

          context.commit('LoginSuccess', response.data);
          //router.push({name: 'Main'});
          context.dispatch('actRouteMove', 'Main');
        }
      })
      .catch(error => {
        if (error.response) {
          console.log(error.response);
          if (error.response.status === 403) {
            context.dispatch('actLogout');
          } else {
            //router.push({name: 'Login'});
            context.dispatch('actRouteMove', 'Login');
          }
        }
      })
    },
    addSessionStorage(context, info) {
      console.log('addSessionStorage info: ', info);
      sessionStorage.setItem('user_id', info.Id);
      sessionStorage.setItem('access_token', info.Token);
    },
    clearSessionStorage() {
      console.log('clearSessionStorage');
      sessionStorage.removeItem('user_id');
      sessionStorage.removeItem('access_token');
    },
    addLocalStorage(context, info) {
      console.log('addlocalStorage info: ', info);
      localStorage.setItem('user_id', info.Id);
      localStorage.setItem('access_token', info.Token);
    },
    clearLocalStorage() {
      console.log('clearlocalStorage');
      localStorage.removeItem('user_id');
      localStorage.removeItem('access_token');
    }
  },
  modules: {
  }
})
