<template>
  <v-app>
    <v-container fluid>
      <v-row>
        <v-col cols="7">
          <div class="common_content_box">
            <div class="common_title_bullet">
              <v-img
                :src="srcTitleImage"
                max-width="22px"
                max-height="22px"
              ></v-img>
            </div>
            <div class="common_title_text">
              <h3>모든 권한 그룹</h3>
            </div>
            <v-row class="common_clear_all">
              <v-col class="common_grid">
                <authorityGrid :data="gridData"></authorityGrid>
              </v-col>
            </v-row>
          </div>
        </v-col>
        <v-col>
          <div class="common_content_box">
            <div class="common_title_bullet">
              <v-img
                :src="srcTitleImage"
                max-width="22px"
                max-height="22px"
              ></v-img>
            </div>
            <div class="common_title_text">
              <h3>권한 그룹 정보</h3>
            </div>
            <v-row class="common_clear_all">
              <v-col>
                <v-text-field label="권한 그룹 아이디" v-model="roleId">
                </v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field label="권한 그룹 설명" v-model="roleDesc">
                </v-text-field>
              </v-col>
            </v-row>
          </div>
        </v-col>
      </v-row>
      <v-row justify="center" class="common_button_bottom">
        <v-col cols="auto">
          <v-btn class="common_button_1" @click="clear">초기화</v-btn>
        </v-col>
        <v-col cols="auto">
          <v-btn class="common_search_button" @click="authority_refresh">
            조회
          </v-btn>
        </v-col>
        <v-spacer></v-spacer>
        <v-col cols="auto">
          <v-btn class="common_default_button" @click="authority_add">
            추가
          </v-btn>
        </v-col>
        <v-col cols="auto">
          <v-btn class="common_default_button" @click="authority_modify">
            수정
          </v-btn>
        </v-col>
        <v-col cols="auto">
          <v-btn class="common_default_button" @click="authority_delete">
            삭제
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
    <v-footer app> 권한 그룹 관리 메뉴 화면 입니다. </v-footer>
  </v-app>
</template>

<script>
import axios from "axios";
import authorityGrid from "@/views/system/GridAuthorityListNew";
// import authorityGrid from "@/views/system/GridAuthorityList";
import { BUS_AUTHORITY } from "@/etc/EventBus";
import messages from "@/etc/constants-messages";
import urls from "@/etc/constants-urls";

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

    // BUS_AUTHORITY.$on("selectedRow", (value) => {
    //   this.groupInfo = value;
    //   // console.log("roleId: ", this.groupInfo["roleId"]);
    //   // console.log("roleDesc: ", this.groupInfo["roleDesc"]);
    //   this.roleId = this.groupInfo["roleId"];
    //   this.roleDesc = this.groupInfo["roleDesc"];
    // });

    // "/api/auth/search"
    BUS_AUTHORITY.$on("selectedRow", (value) => {
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
      MESSAGES: messages,
      URLS: urls,
      gridData: [], // 모든 권한 그룹 데이터 정보 (SELECT)
      gridUpdate: false,
      roleId: "", // 권한 그룹 아이디 (NOT NULL)
      roleDesc: "", // 권한 그룹 설명 (NULL)
      roleIdTemp: "", // 임시 권한 그룹 아이디
      roleDescTemp: "", // 임시 권한 그룹 설명
      srcTitleImage: require("@/assets/images/titleBullet1.png"),
    };
  },

  methods: {
    // 모든 권한 그룹 리스트 조회 (/all)
    authority_refresh: function () {
      axios
        .get(this.URLS.AUTHORITY_GROUP_ALL)
        .then(
          (response) => (
            (this.gridData = null), (this.gridData = response.data)
          )
        )
        .catch((error) => alert(error));
    },

    // 권한 그룹 정보 조회 (/search)
    authority_search: function (id, desc) {
      axios
        .post(this.URLS.AUTHORITY_GROUP_SEARCH, {
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
        .catch((error) => alert(this.MESSAGES.AUTHORITY_GROUP_SEARCH_FAILED));
    },

    // Search 정보 초기화
    doSearchClear: function () {
      BUS_AUTHORITY.$emit("searchClear", "");
    },

    // 입력 정보 초기화
    clear: function () {
      this.doSearchClear();
      this.roleId = "";
      this.roleDesc = "";
      this.roleIdTemp = "";
      this.roleDescTemp = "";
    },

    // 권한 그룹 추가 (/add)
    authority_add: function () {
      if (this.roleId === "") {
        alert(this.MESSAGES.ID_INPUT_MESSAGE, "");
      } else {
        axios
          .post(this.URLS.AUTHORITY_GROUP_ADD, {
            roleId: this.roleId,
            roleDesc: this.roleDesc,
          })
          .then(
            (response) =>
              alert(
                response.data["roleId"] +
                  " " +
                  this.MESSAGES.AUTHORITY_GROUP_ADD_COMPLETE
              ),
            ((this.gridUpdate = true),
            (this.roleId = ""),
            (this.roleDesc = ""),
            (this.roleIdTemp = ""),
            (this.roleDescTemp = ""))
          )
          .catch((error) => alert(this.MESSAGES.AUTHORITY_GROUP_ADD_FAILED));
      }
    },

    // 권한 그룹 정보 수정 (/modify)
    authority_modify: function () {
      if (this.roleId === "") {
        alert(this.MESSAGES.ID_INPUT_MESSAGE);
      } else {
        axios
          .post(this.URLS.AUTHORITY_GROUP_MODIFY, {
            roleId: this.roleId,
            roleDesc: this.roleDesc,
          })
          .then(
            (response) =>
              alert(
                response.data["roleId"] +
                  " " +
                  this.MESSAGES.AUTHORITY_GROUP_MODIFY_COMPLETE
              ),
            ((this.gridUpdate = true),
            (this.roleIdTemp = this.roleId),
            (this.roleDescTemp = this.roleDesc),
            (this.roleId = ""),
            (this.roleDesc = ""))
          )
          .catch((error) => alert(this.MESSAGES.AUTHORITY_GROUP_MODIFY_FAILED));
      }
    },

    // 권한 그룹 삭제 (/delete)
    authority_delete: function () {
      if (this.roleId === "") {
        alert(this.MESSAGES.ID_INPUT_MESSAGE);
      } else {
        axios
          .post(this.URLS.AUTHORITY_GROUP_DELETE, {
            roleId: this.roleId,
            roleDesc: this.roleDesc,
          })
          .then(
            (response) =>
              alert(
                response.data["roleId"] +
                  " " +
                  this.MESSAGES.AUTHORITY_GROUP_DELETE_COMPLETE
              ),
            ((this.gridUpdate = true),
            (this.roleId = ""),
            (this.roleDesc = ""),
            (this.roleIdTemp = ""),
            (this.roleDescTemp = ""))
          )
          .catch((error) => alert(this.MESSAGES.AUTHORITY_GROUP_DELETE_FAILED));
      }
    },
  },
};
</script>
