<template>
  <div class="login">
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <v-app id="inspire">
      <v-main>
        <v-container class="fill-height" fluid>
          <v-row align="center" justify="center" >
            <v-col cols="12" sm="8" md="4" >
              <v-card class="elevation-12">
                <v-toolbar color="primary" dark flat >
                  <v-toolbar-title>Mighty Login </v-toolbar-title>
                  <v-spacer></v-spacer>
                </v-toolbar>
                <v-card-text>
                  <v-form>
                    <v-text-field
                      label="Login"
                      name="login"
                      prepend-icon="mdi-account"
                      type="text"
                      v-model="userId"
                    ></v-text-field>
  
                    <v-text-field
                      label="Password"
                      name="password"
                      prepend-icon="mdi-lock"
                      type="password"
                      v-model="password"
                    ></v-text-field>
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <!-- <v-btn color="primary" @click="actLogin({userId:userId, password:password})">Login</v-btn> -->
                  <v-btn color="primary" @click="Login">Login</v-btn>
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>
        </v-container>        
      </v-main>      
    </v-app>  
  </div>
</template>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'
import {mapState, mapActions} from 'vuex';

export default {
  name: 'Login',
  components: {
    //HelloWorld
  },
  computed: {
    ...mapState(['isLogIn'])
  },
  methods: {
    ...mapActions(['actLogin']),
    Login() {
      //this.actLogin({userId:userId, password:password});
      this.$store.dispatch('actLogin', {userId:this.userId, password:this.password});
      this.userId = null;
      this.password = null;
    },
    // 넘어온 값이 빈값인지 체크합니다.
    // !value 하면 생기는 논리적 오류를 제거하기 위해
    // 명시적으로 value == 사용
    // [], {} 도 빈값으로 처리
    isEmpty: function (value) {
      if (
        value == "" ||
        value == null ||
        value == undefined ||
        (value != null &&
        typeof value == "object" &&
        !Object.keys(value).length)
        ) {
            return true;
          } else {
            return false;
      }
    },
  },
  data() {
    return {
      userId: null,
      password: null
    }
  },
  watch: {
    userId: function() {
      return this.userId = this.isEmpty(this.userId) ? null : this.userId.toUpperCase();
    },
  },
  // data: () => ({
  //     userId: null,
  //     password: null
  // }),
}
</script>
