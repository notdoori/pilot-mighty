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
          <h2>[모든 권한 그룹]</h2>
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
              label="권한 그룹 정보 검색"
              outlined="true"
            ></v-text-field>
            <authorityGrid
              :data="gridData"
              :columns="gridColumns"
              :filter-key="searchQuery"
            ></authorityGrid>
          </div>
        </v-col>
        <v-col>
          <h2>[권한 그룹 정보]</h2>
          <br />
          <div>
            <v-text-field
              label="권한 그룹 아이디"
              outlined="true"
              v-model="roleId"
            ></v-text-field>
            <v-text-field
              label="권한 그룹 설명"
              outlined="true"
              v-model="roleDesc"
            ></v-text-field>
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

    <v-footer app> 권한 그룹 관리 메뉴 화면 입니다. </v-footer>
  </v-app>
</template>

<script>
import axios from "axios";
import authorityGrid from "@/views/user/GridAuthorityList";
import { BUS } from "@/router/EventBus";

const AUTHORITY_GROUP_ALL = "/api/auth/all";
const AUTHORITY_GROUP_SEARCH = "/api/auth/search";
const AUTHORITY_GROUP_ADD = "/api/auth/add";
const AUTHORITY_GROUP_MODIFY = "/api/auth/modify";
const AUTHORITY_GROUP_DELETE = "/api/auth/delete";

// alert() 팝업 메시지 정보
const NO_ID_MESSAGE = "아이디가 존재하지 않습니다.";
const ID_INPUT_MESSAGE = "아이디를 입력하여 주십시오.";
const AUTHORITY_GROUP_SEARCH_FAILED = "권한 그룹 정보 조회를 실패하였습니다.";
const AUTHORITY_GROUP_ADD_COMPLETE = "권한 그룹 추가를 완료하였습니다.";
const AUTHORITY_GROUP_ADD_FAILED = "권한 그룹 추가를 실패하였습니다.";
const AUTHORITY_GROUP_MODIFY_COMPLETE = "권한 그룹 수정을 완료하였습니다.";
const AUTHORITY_GROUP_MODIFY_FAILED = "권한 그룹 수정을 실패하였습니다.";
const AUTHORITY_GROUP_DELETE_COMPLETE = "권한 그룹 삭제를 완료하였습니다.";
const AUTHORITY_GROUP_DELETE_FAILED = "권한 그룹 삭제를 실패하였습니다.";

export default {
  beforeCreate() {
    /* beforeCreate 훅은 인스턴스가 생성될 때 가장 처음으로 실행되는 훅이다. */
    // alert("beforeCreate() 호출");
  },
  created() {
    /* created 훅은 beforeCreate 훅의 다음 단계로서,
       beforeCreate 훅이 호출된 직후 데이터와 이벤트가 초기화되어 created 훅에서는 데이터와 이벤트에 접근할 수 있다. */
    // alert("created() 호출");

    // 모든 권한 그룹 리스트 조회
    this.authority_refresh();

    // BUS.$on("selectedRow", (value) => {
    //   this.groupInfo = value;
    //   // console.log("roleId: ", this.groupInfo["roleId"]);
    //   // console.log("roleDesc: ", this.groupInfo["roleDesc"]);
    //   this.roleId = this.groupInfo["roleId"];
    //   this.roleDesc = this.groupInfo["roleDesc"];
    // });

    // "/api/auth/search"
    BUS.$on("selectedRow", (value) => {
      this.groupInfo = value;
      this.authority_search(
        this.groupInfo["roleId"],
        this.groupInfo["roleDesc"]
      );
    });
  },
  beforeMount() {
    /* beforeMount 훅 이후부터는 컴포넌트에 접근할 수 있다. */
    // alert("beforeMount() 호출");
  },
  mounted() {
    /* mounted 훅에서는 인스턴스의 렌더와 DOM 마운트가 끝난 상태이다. */
    // alert("mounted() 호출");
  },
  beforeUpdate() {
    /* 컴포넌트가 마운트가 다 된 후,
       데이터의 감지됐을 때 해당하는 데이터와 관련 있는 DOM 을 업데이트하기 전에 호출된다. */
    // alert("beforeUpdate() 호출");
  },
  updated() {
    /* updated 훅은 가상 DOM 이 재렌더링 되어 실제 DOM 이 되었을 때 호출된다. */
    // alert("updated() 호출");

    // 모든 권한 그룹 리스트 조회
    if (this.gridUpdate === true) {
      this.gridUpdate = false;
      this.roleId = this.roleIdTemp;
      this.roleDesc = this.roleDescTemp;
      this.authority_refresh();
    }
  },
  beforeDestroy() {
    /* beforeDestroy 훅은 Vue 인스턴스가 제거되기 전에 호출되는 훅이다. */
    // alert("beforeDestroy() 호출");
  },
  destroyed() {
    /* destroyed 훅은 Vue 인스턴스가 제거된 후에 실행되는 훅이다 */
    // alert("destroyed() 호출");
  },
  name: "Auth",
  components: {
    authorityGrid,
  },
  data() {
    return {
      searchQuery: "",
      gridColumns: ["roleId", "roleDesc"], // 모든 권한 그룹 항목 정보
      gridData: [], // 모든 권한 그룹 데이터 정보 (SELECT)
      gridUpdate: false,
      roleId: "", // 권한 그룹 아이디 (NOT NULL)
      roleDesc: "", // 권한 그룹 설명 (NULL)
      roleIdTemp: "", // 임시 권한 그룹 아이디
      roleDescTemp: "", // 임시 권한 그룹 설명
    };
  },

  methods: {
    // 모든 권한 그룹 리스트 조회 (/all)
    authority_refresh: function () {
      axios
        .get(AUTHORITY_GROUP_ALL)
        .then(
          (response) => (
            (this.gridData = null), (this.gridData = response.data)
          )
        )
        .catch((error) => alert(error));
    },

    // 권한 그룹 정보 조회 (/search)
    authority_search: function (id, desc) {
      // console.log("ID : " + id);
      // console.log("DESC : " + desc);

      if (id === "") {
        alert(NO_ID_MESSAGE);
      } else {
        axios
          .post(AUTHORITY_GROUP_SEARCH, {
            roleId: id,
            roleDesc: desc,
          })
          .then(
            (response) => (
              (this.roleId = response.data["roleId"]),
              (this.roleDesc = response.data["roleDesc"]),
              (this.roleIdTemp = response.data["roleId"]),
              (this.roleDescTemp = response.data["roleDesc"])
            )
          )
          .catch((error) => alert(AUTHORITY_GROUP_SEARCH_FAILED));
      }
    },

    // 권한 그룹 추가 (/add)
    authority_add: function () {
      if (this.roleId === "") {
        alert(ID_INPUT_MESSAGE, "");
      } else {
        axios
          .post(AUTHORITY_GROUP_ADD, {
            roleId: this.roleId,
            roleDesc: this.roleDesc,
          })
          .then(
            (response) => alert(AUTHORITY_GROUP_ADD_COMPLETE),
            ((this.gridUpdate = true),
            (this.roleId = ""),
            (this.roleDesc = ""),
            (this.roleIdTemp = ""),
            (this.roleDescTemp = ""))
          )
          .catch((error) => alert(AUTHORITY_GROUP_ADD_FAILED));
      }
    },

    // 권한 그룹 정보 수정 (/modify)
    authority_modify: function () {
      if (this.roleId === "") {
        alert(ID_INPUT_MESSAGE);
      } else {
        axios
          .post(AUTHORITY_GROUP_MODIFY, {
            roleId: this.roleId,
            roleDesc: this.roleDesc,
          })
          .then(
            (response) => alert(AUTHORITY_GROUP_MODIFY_COMPLETE),
            ((this.gridUpdate = true),
            (this.roleIdTemp = this.roleId),
            (this.roleDescTemp = this.roleDesc),
            (this.roleId = ""),
            (this.roleDesc = ""))
          )
          .catch((error) => alert(AUTHORITY_GROUP_MODIFY_FAILED));
      }
    },

    // 권한 그룹 삭제 (/delete)
    authority_delete: function () {
      if (this.roleId === "") {
        alert(ID_INPUT_MESSAGE);
      } else {
        axios
          .post(AUTHORITY_GROUP_DELETE, {
            roleId: this.roleId,
            roleDesc: this.roleDesc,
          })
          .then(
            (response) => alert(AUTHORITY_GROUP_DELETE_COMPLETE),
            ((this.gridUpdate = true),
            (this.roleId = ""),
            (this.roleDesc = ""),
            (this.roleIdTemp = ""),
            (this.roleDescTemp = ""))
          )
          .catch((error) => alert(AUTHORITY_GROUP_DELETE_FAILED));
      }
    },
  },
};
</script>

<!-- <style type="text/css"> -->
<style scoped="">
.v-container {
  background-color: lightgray;
}
.v-btn {
  margin-right: 10px;
}
</style>
