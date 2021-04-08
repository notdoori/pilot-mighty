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
  beforeCreate() {
    /* beforeCreate 훅은 인스턴스가 생성될 때 가장 처음으로 실행되는 훅이다. */
    alert("beforeCreate() 호출");
  },
  created() {
    /* created 훅은 beforeCreate 훅의 다음 단계로서,
       beforeCreate 훅이 호출된 직후 데이터와 이벤트가 초기화되어 created 훅에서는 데이터와 이벤트에 접근할 수 있다. */
    alert("created() 호출");

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
  beforeMount() {
    /* beforeMount 훅 이후부터는 컴포넌트에 접근할 수 있다. */
    alert("beforeMount() 호출");
  },
  mounted() {
    /* mounted 훅에서는 인스턴스의 렌더와 DOM 마운트가 끝난 상태이다. */
    alert("mounted() 호출");
  },
  beforeUpdate() {
    /* 컴포넌트가 마운트가 다 된 후,
       데이터의 감지됐을 때 해당하는 데이터와 관련 있는 DOM 을 업데이트하기 전에 호출된다. */
    alert("beforeUpdate() 호출");
  },
  updated() {
    /* updated 훅은 가상 DOM 이 재렌더링 되어 실제 DOM 이 되었을 때 호출된다. */
    alert("updated() 호출");
  },
  beforeDestroy() {
    /* beforeDestroy 훅은 Vue 인스턴스가 제거되기 전에 호출되는 훅이다. */
    alert("beforeDestroy() 호출");
  },
  destroyed() {
    /* destroyed 훅은 Vue 인스턴스가 제거된 후에 실행되는 훅이다 */
    alert("destroyed() 호출");
  },
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
  background-color: lightgray;
}
.v-btn {
  margin-right: 10px;
}
</style>
