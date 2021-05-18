<template>
  <div>
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <v-app class="login" id="inspire">
      <v-main>
        <v-container class="fill-height" fluid>
          <v-row align="center" justify="center">
            <v-col cols="12" sm="8" md="4">
              <v-layout
                column
                align-center
                justify-center
                class="darkblue--text"
                fill-height
              >
                <img :src="srcTitleImage" height="200px" />
                <h1 class="title">기준정보 시스템</h1>
                <p class="desc">웹기반 기준정보 시스템 구축 프로젝트 Phase-2</p>
              </v-layout>
              <v-card class="elevation-12">
                <!--<v-toolbar color="primary" dark flat >
                  <v-toolbar-title>Login</v-toolbar-title>
                  <v-spacer></v-spacer>
                </v-toolbar>-->
                <v-card-text>
                  <v-form>
                    <v-text-field
                      label="Login"
                      name="login"
                      prepend-icon="mdi-account"
                      type="text"
                      v-model="userId"
                      tabindex="1"
                    ></v-text-field>

                    <v-text-field
                      label="Password"
                      name="password"
                      prepend-icon="mdi-lock"
                      type="password"
                      v-model="password"
                      @keyup.enter="Login"
                      tabindex="2"
                    ></v-text-field>

                    <v-select
                      label="Language"
                      prepend-icon="mdi-web"
                      v-model="language"
                      :items="languageItems"
                      @input="languageSelected"
                    ></v-select>
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <!-- <v-btn color="primary" @click="actLogin({userId:userId, password:password})">Login</v-btn> -->
                  <v-btn
                    x-large
                    rounded
                    color="primary"
                    dark
                    min-width="100%"
                    @keyup.enter="Login"
                    @click="Login"
                    tabindex="3"
                    >LOGIN</v-btn
                  >
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
import { mapState, mapActions } from "vuex";

export default {
  name: "Login",
  components: {
    //HelloWorld
  },
  computed: {
    ...mapState(["isLogIn"]),
  },
  methods: {
    ...mapActions(["actLogin"]),
    Login() {
      if (
        this.userId === null ||
        this.password === null ||
        this.language === null
      ) {
        alert("정보를 모두 입력하십시오.");
      } else {
        //this.actLogin({userId:userId, password:password});
        this.$store.dispatch("actLogin", {
          userId: this.userId,
          password: this.password,
          language: this.language,
        });
        this.userId = null;
        this.password = null;
        this.language = null;
      }
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

    // 언어 선택 이벤트
    languageSelected: function (event) {
      // console.log("event : " + event);
      this.language = event;
    },
  },
  data() {
    return {
      userId: null,
      password: null,
      language: null,
      languageItems: ["한국어", "English", "Chinese", "Vietnamese"], // 언어 선택
      srcTitleImage: require("@/assets/images/mighty.png"),
    };
  },
  watch: {
    userId: function () {
      return (this.userId = this.isEmpty(this.userId)
        ? null
        : this.userId.toUpperCase());
    },

    language: function () {
      return (this.language = this.isEmpty(this.language)
        ? null
        : this.language.toUpperCase());
    },
  },
  // data: () => ({
  //     userId: null,
  //     password: null
  // }),
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login {
  background-color: #f5f5f5;
}
.title {
  padding-top: 50px;
}
.desc {
  padding-top: 20px;
}
</style>