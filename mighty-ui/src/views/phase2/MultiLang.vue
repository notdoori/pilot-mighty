<template>
  <v-app>
    <v-container fluid>
      <v-row>
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
              <!-- <h3>모든 다국어 정보</h3> -->
              <h3>{{ this.multiLangList }}</h3>
            </div>
            <v-row class="common_clear_all">
              <v-col class="common_grid">
                <languageGrid :data="gridData"></languageGrid>
              </v-col>
            </v-row>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <!-- <v-footer app> 다국어 정보 관리 메뉴 화면 입니다. </v-footer> -->
    <v-footer app> {{ this.multiLangFooter }} </v-footer>
  </v-app>
</template>

<script>
import axios from "axios";
import languageGrid from "@/views/phase2/GridMultiLangListNew";
import { BUS_AUTHORITY } from "@/etc/EventBus";
import messages from "@/etc/constants-messages";
import urls from "@/etc/constants-urls";
import { getLangText, LANG_KEYWORD } from "@/language/Language";

export default {
  created() {
    /* created 훅은 beforeCreate 훅의 다음 단계로서,
       beforeCreate 훅이 호출된 직후 데이터와 이벤트가 초기화되어 created 훅에서는 데이터와 이벤트에 접근할 수 있다. */
    // alert("created() 호출");

    // 모든 권한 그룹 리스트 조회
    this.language_refresh();

    // "/api/auth/search"
    // BUS_LANGUAGE.$on("selectedRow", (value) => {
    //   // this.groupInfo = value;
    //   // this.roleId = this.groupInfo["roleId"];
    //   // this.roleDesc = this.groupInfo["roleDesc"];
    // });

    this.labelLangText();
  },
  name: "MultiLang",
  components: {
    languageGrid,
  },
  data() {
    return {
      //   MESSAGES: messages,
      URLS: urls,
      gridData: [], // 모든 다국어 데이터 정보 (SELECT)
      //   roleId: "", // 권한 그룹 아이디 (NOT NULL)
      //   roleDesc: "", // 권한 그룹 설명 (NULL)
      //   roleIdTemp: "", // 임시 권한 그룹 아이디
      //   roleDescTemp: "", // 임시 권한 그룹 설명
      srcTitleImage: require("@/assets/images/titleBullet1.png"),

      // 다국어
      multiLangList: "", // 모든 다국어 정보
      multiLangFooter: "", // 다국어 정보 관리 메뉴 화면 입니다.
    };
  },

  methods: {
    // 다국어 적용
    labelLangText: function () {
      this.multiLangList = this.langText(LANG_KEYWORD.MULTI_LANG_LIST);
      this.multiLangFooter = this.langText(LANG_KEYWORD.MULTI_LANG_FOOTER);
    },

    // 다국어 적용 (axios)
    langText: function (keyword) {
      console.log("keyword : " + keyword);
      return getLangText(keyword);
    },

    // 모든 다국어 리스트 조회 (/all)
    language_refresh: function () {
      axios
        .get(this.URLS.MULTI_LANGUAGE_ALL)
        .then(
          (response) => (
            (this.gridData = null), (this.gridData = response.data)
          )
        )
        .catch((error) => alert(error));
    },
  },
};
</script>
