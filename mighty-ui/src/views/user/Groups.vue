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
              label="사용자 그룹 정보 검색"
              outlined="true"
            ></v-text-field>
            <groupGrid
              :data="gridData"
              :columns="gridColumns"
              :filter-key="searchQuery"
            ></groupGrid>
          </div>
        </v-col>
        <v-col>
          <h2>사용자 그룹 정보</h2>
          <br />
          <div>
            <v-text-field
              label="사용자 그룹 아이디"
              outlined="true"
              v-model="groupId"
            ></v-text-field>
            <v-text-field
              label="사용자 그룹 설명"
              outlined="true"
              v-model="groupDesc"
            ></v-text-field>
            <v-combobox
              label="권한 그룹 아이디"
              outlined="true"
              v-model="roleId"
            ></v-combobox>
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
import groupGrid from "@/views/user/Grid";

const USER_GROUP_ALL = "/api/group/all";
const USER_GROUP_ADD = "/api/group/add";
const USER_GROUP_MODIFY = "/api/group/modify";
const USER_GROUP_DELETE = "/api/group/delete";

// alert() 팝업 메시지 정보
const ID_INPUT_MESSAGE = "아이디를 입력하여 주십시오.";
const USER_GROUP_ADD_COMPLETE = "사용자 그룹 추가를 완료하였습니다.";
const USER_GROUP_ADD_FAILED = "사용자 그룹 추가를 실패하였습니다.";
const USER_GROUP_MODIFY_COMPLETE = "사용자 그룹 수정을 완료하였습니다.";
const USER_GROUP_MODIFY_FAILED = "사용자 그룹 수정을 실패하였습니다.";
const USER_GROUP_DELETE_COMPLETE = "사용자 그룹 삭제를 완료하였습니다.";
const USER_GROUP_DELETE_FAILED = "사용자 그룹 삭제를 실패하였습니다.";

export default {
  beforeCreate() {
    /* beforeCreate 훅은 인스턴스가 생성될 때 가장 처음으로 실행되는 훅이다. */
    // alert("beforeCreate() 호출");
  },
  created() {
    /* created 훅은 beforeCreate 훅의 다음 단계로서,
       beforeCreate 훅이 호출된 직후 데이터와 이벤트가 초기화되어 created 훅에서는 데이터와 이벤트에 접근할 수 있다. */
    // alert("created() 호출");

    // 모든 사용자 그룹 리스트 조회
    this.group_refresh();
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
      this.groupId = this.groupIdTemp;
      this.groupDesc = this.groupDescTemp;
      this.roleId = this.roleIdTemp;
      this.group_refresh();
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
  name: "Group",
  components: {
    groupGrid,
  },
  data() {
    return {
      searchQuery: "",
      gridColumns: ["groupId", "groupDesc", "roleId"], // 모든 사용자 그룹 항목 정보
      gridData: [], // 모든 사용자 그룹 데이터 정보 (SELECT)
      gridUpdate: false,
      groupId: "", // 사용자 그룹 아이디 (NOT NULL)
      groupDesc: "", // 사용자 그룹 설명 (NULL)
      roleId: "", // 권한 그룹 아이디 (NULL)
      groupIdTemp: "", // 임시 사용자 그룹 아이디
      groupDescTemp: "", // 임시 사용자 그룹 설명
      roleIdTemp: "", // 임시 권한 그룹 아이디
      // roleId: [
      //   {
      //     text: "A1",
      //     value: "A1",
      //   },
      //   {
      //     text: "A2",
      //     value: "A2",
      //   },
      //   {
      //     text: "A3",
      //     value: "A3",
      //   },
      //   {
      //     text: "A4",
      //     value: "A4",
      //   },
      // ],
    };
  },
  methods: {
    // 모든 사용자 그룹 리스트 조회
    group_refresh: function () {
      axios
        .get(USER_GROUP_ALL)
        .then(
          (response) => (
            (this.gridData = null), (this.gridData = response.data)
            // console.log(this.gridData)
          )
        )
        .catch((error) => alert(error));
    },

    // 사용자 그룹 추가
    user_group_add: function () {
      if (this.groupId === "") {
        alert(ID_INPUT_MESSAGE);
      } else {
        // console.log("[vue] groupId: " + this.groupId);
        // console.log("[vue] groupDesc: " + this.groupDesc);
        // console.log("[vue] roleId: " + this.roleId);

        axios
          .post(USER_GROUP_ADD, {
            groupId: this.groupId,
            groupDesc: this.groupDesc,
            roleId: this.roleId,
          })
          .then(
            (response) =>
              // console.log(response)
              alert(USER_GROUP_ADD_COMPLETE),
            ((this.gridUpdate = true),
            (this.groupId = ""),
            (this.groupDesc = ""),
            (this.roleId = ""),
            (this.groupIdTemp = ""),
            (this.groupDescTemp = ""),
            (this.roleIdTemp = ""))
          )
          .catch((error) =>
            // console.log(error)
            alert(USER_GROUP_ADD_FAILED)
          );
      }
    },

    // 사용자 그룹 정보 수정
    user_group_modify: function () {
      if (this.groupId === "") {
        alert(ID_INPUT_MESSAGE);
      } else {
        // console.log("[vue] groupId: " + this.groupId);
        // console.log("[vue] groupDesc: " + this.groupDesc);
        // console.log("[vue] roleId: " + this.roleId);

        axios
          .post(USER_GROUP_MODIFY, {
            groupId: this.groupId,
            groupDesc: this.groupDesc,
            roleId: this.roleId,
          })
          .then(
            (response) =>
              // console.log(response)
              alert(USER_GROUP_MODIFY_COMPLETE),
            ((this.gridUpdate = true),
            (this.groupIdTemp = this.groupId),
            (this.groupDescTemp = this.groupDesc),
            (this.roleIdTemp = this.roleId),
            (this.groupId = ""),
            (this.groupDesc = ""),
            (this.roleId = ""))
          )
          .catch((error) =>
            // console.log(error)
            alert(USER_GROUP_MODIFY_FAILED)
          );
      }
    },

    // 사용자 그룹 삭제
    user_group_delete: function () {
      if (this.groupId === "") {
        alert(ID_INPUT_MESSAGE);
      } else {
        // console.log("[vue] groupId: " + this.groupId);
        // console.log("[vue] groupDesc: " + this.groupDesc);
        // console.log("[vue] roleId: " + this.roleId);

        axios
          .post(USER_GROUP_DELETE, {
            groupId: this.groupId,
            groupDesc: this.groupDesc,
            roleId: this.roleId,
          })
          .then(
            (response) =>
              // console.log(response)
              alert(USER_GROUP_DELETE_COMPLETE),
            ((this.gridUpdate = true),
            (this.groupId = ""),
            (this.groupDesc = ""),
            (this.roleId = ""),
            (this.groupIdTemp = ""),
            (this.groupDescTemp = ""),
            (this.roleIdTemp = ""))
          )
          .catch((error) =>
            // console.log(error)
            alert(USER_GROUP_DELETE_FAILED)
          );
      }
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
  background-color: lightgray;
}
.v-btn {
  margin-right: 10px;
}
</style>
