<template>
  <v-app>
    <br />
    <v-container class="v-container" row fluid>
      <v-row>
        <v-col>
          <h2>모든 사용자 그룹</h2>
          <br />
          <div>
            <v-text-field
              id="queryinput"
              name="query"
              v-model="searchQuery"
              hint="사용자 그룹 정보 검색"
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
          <h2>사용자 그룹 정보</h2>
          <br />
          <div>
            <v-text-field
              hint="사용자 그룹 아이디"
              outlined="true"
            ></v-text-field>
            <v-text-field
              hint="사용자 그룹 설명"
              outlined="true"
            ></v-text-field>
          </div>
          <div>
            <v-btn
              class="v-btn"
              @click="user_group_add"
              color="deep-orange lighten-3"
              width="120px"
            >
              추가
            </v-btn>
            <v-btn
              class="v-btn"
              @click="user_group_modify"
              color="deep-orange lighten-3"
              width="120px"
            >
              수정
            </v-btn>
            <v-btn
              @click="user_group_delete"
              color="deep-orange lighten-3"
              width="120px"
            >
              삭제
            </v-btn>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <v-footer app> 사용자 그룹 관리 메뉴 화면 입니다. </v-footer>
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
    // 모든 사용자 그룹 리스트 조회
    axios
      // .get("/api/users/all")
      .get("/api/group/all")
      .then(
        (response) => (
          (this.gridData = response.data), console.log(this.gridData)
        )
      )
      .catch((error) => console.log(error));
  },
  methods: {
    // 사용자 그룹 추가
    user_group_add: function () {},

    // 사용자 그룹 정보 수정
    user_group_modify: function () {},

    // 사용자 그룹 삭제
    user_group_delete: function () {},
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
