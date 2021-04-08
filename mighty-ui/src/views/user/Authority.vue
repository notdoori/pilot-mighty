<template>
  <v-app>
    <!-- <v-navigation-drawer app>
      v-navigation-drawer
    </v-navigation-drawer> -->

    <!-- <v-app-bar app>
      <h1>기준정보 시스템 - 권한 관리</h1>
    </v-app-bar> -->

    <br />
    <v-container class="v-container" row fluid>
      <v-row>
        <v-col>
          <h2>모든 권한 그룹</h2>
          <br />
          <div>
            <!-- <div v-bind:key="user.userId" v-for="user in users">
              <span>{{ user.userId }}, </span>
              <span>{{ user.userName }}, </span>
              <span>{{ user.depart }}, </span>
              <span>{{ user.langType }}</span>
            </div> -->
            <!-- <form id="search"> -->
            <!-- 권한 정보 검색 -->
            <!-- <input id="queryinput" name="query" v-model="searchQuery" /> -->
            <!-- </form> -->
            <v-text-field
              id="queryinput"
              name="query"
              v-model="searchQuery"
              hint="권한 정보 검색"
              outlined="true"
            ></v-text-field>
            <userGrid
              :data="gridData"
              :columns="gridColumns"
              :filter-key="searchQuery"
            ></userGrid>
          </div>
        </v-col>
        <v-col>
          <h2>권한 그룹 정보</h2>
          <br />
          <div>
            <v-text-field
              hint="권한 그룹 아이디"
              outlined="true"
            ></v-text-field>
            <v-text-field hint="권한 그룹 설명" outlined="true"></v-text-field>
          </div>
          <div>
            <v-btn
              class="v-btn"
              @click="authority_add"
              color="deep-orange lighten-3"
              width="120px"
            >
              추가
            </v-btn>
            <v-btn
              class="v-btn"
              @click="authority_modify"
              color="deep-orange lighten-3"
              width="120px"
            >
              수정
            </v-btn>
            <v-btn
              @click="authority_delete"
              color="deep-orange lighten-3"
              width="120px"
            >
              삭제
            </v-btn>
          </div>
        </v-col>
      </v-row>
    </v-container>

    <!-- </v-main> -->

    <v-footer app> 권한 관리 메뉴 화면 입니다. </v-footer>
  </v-app>
</template>

<script>
import axios from "axios";
import userGrid from "@/views/user/Grid";

export default {
  name: "User",
  components: {
    userGrid,
  },
  data() {
    return {
      searchQuery: "",
      gridColumns: ["userId", "userName", "depart", "langType"],
      gridData: [],
    };
  },
  created() {
    // 모든 권한 그룹 리스트 조회
    axios
      // .get("http://127.0.0.1:8080/api/users/all")
      //.get("/api/users/all")
      .get("/api/auth/all")
      .then(
        (response) => (
          (this.gridData = response.data), console.log(this.gridData)
        )
      )
      .catch((error) => console.log(error));
  },
  methods: {
    // 권한 그룹 정보 조회
    // authority_select: function () {},

    // 권한 그룹 추가
    authority_add: function () {
      axios
        .post("/api/auth/add", {
          authId: "KTH_ID_add",
          authDesc: "KTH_DESC_add",
        })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
    },

    // 권한 그룹 정보 수정
    authority_modify: function () {
      axios
        .post("/api/auth/modify", {
          authId: "KTH_ID_modify",
          authDesc: "KTH_DESC_modify",
        })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
    },

    // 권한 그룹 삭제
    authority_delete: function () {
      axios
        .post("/api/auth/delete", {
          authId: "KTH_ID_delete",
          authDesc: "KTH_DESC_delete",
        })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
    },
  },
};
</script>

<!--
<style type="text/css">
 .v-content {
  color: red;
}
</style>
-->

<style scoped="">
.v-container {
  background-color: grey lighten-1;
}
.v-btn {
  margin-right: 10px;
}
</style>