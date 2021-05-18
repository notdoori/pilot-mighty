import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'
import MainStore from '@/store/MainStore'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: null,
        accessToken: null,
        isLogIn: false,
        language: null,
    },
    mutations: {
        // 로그인 성공
        LoginSuccess(state, payload) {
            // console.log(state);
            console.log(payload);

            state.user = payload.USERID;
            state.accessToken = payload.ACCESSTOKEN;
            state.isLogIn = true;
            state.language = payload.LANGUAGE;
        },
        // 로그인 실패
        LoginFailure(state) {
            state.user = null;
            state.accessToken = null;
            state.isLogIn = false;
            state.language = null;
        }
    },
    actions: {
        actRouteMove(context, targetRouteName) {
            if (router.currentRoute.name !== targetRouteName) {
                console.log('cur: ', router.currentRoute.name, ', target: ', targetRouteName);
                // router.push({name: targetRouteName});
                router.replace({ name: targetRouteName }, function() {}, function() {});
            }
        },
        actLogin({ dispatch, commit }, payload) {
            axios.post('/api/users/login', payload)
                .then(response => {

                    // console.log(response);
                    // console.log(payload);

                    let Id = response.data.USERID;
                    let Token = response.data.ACCESSTOKEN;
                    let Language = payload.language;
                    response.data.LANGUAGE = Language;

                    if (Id !== null && Token !== null) {

                        // sessionStorage.setItem("user_id", Id);
                        // sessionStorage.setItem("access_token", Token);
                        //dispatch('addSessionStorage', {Id, Token});
                        // dispatch('addLocalStorage', { Id, Token });
                        dispatch('addLocalStorage', { Id, Token, Language });

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

            axios.post('/api/users/logout', { userId: userId, token: token })
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

            axios.post('/api/users/logout', { userId: userId, token: token })
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

            axios.post('/api/users/check', { userId: userId, token: Token })
                .then(response => {

                    //console.log(response);

                    if (response.status === 200) {
                        console.log("Success 200");

                        // console.log(response);

                        let Id = response.data.USERID;
                        let Token = response.data.ACCESSTOKEN;
                        let Language = "KOREAN";
                        response.data.LANGUAGE = Language;

                        console.log('New Token: ', Token);
                        console.log('userId: ', userId);
                        console.log("Language : " + Language);

                        // context.dispatch('addLocalStorage', { Id, Token });
                        context.dispatch('addLocalStorage', { Id, Token, Language });

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
            sessionStorage.setItem('language', info.Language);
        },
        clearSessionStorage() {
            console.log('clearSessionStorage');
            sessionStorage.removeItem('user_id');
            sessionStorage.removeItem('access_token');
            sessionStorage.removeItem('language');
        },
        addLocalStorage(context, info) {
            console.log('addlocalStorage info: ', info);
            localStorage.setItem('user_id', info.Id);
            localStorage.setItem('access_token', info.Token);
            localStorage.setItem('language', info.Language);
        },
        clearLocalStorage() {
            console.log('clearlocalStorage');
            localStorage.removeItem('user_id');
            localStorage.removeItem('access_token');
            localStorage.removeItem('language');
        }
    },
    modules: {
        MainStore
    }
})