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
              <!-- <h3>모든 다국어 정보</h3> -->
              <h3>{{ this.multiLangList }}</h3>
            </div>
            <v-row class="common_clear_all">
              <v-col class="common_grid">
                <table class="gridTable">
                <v-card>
                  <v-card-title>
                    <v-text-field
                      v-model="search"
                      append-icon="mdi-magnify"
                      :label="commonSearch"
                      single-line
                      hide-details
                    ></v-text-field>
                  </v-card-title>
                  <v-data-table
                    :headers="headers"
                    :items="gridData"
                    :search="search"
                    :items-per-page="5"
                    :page.sync="page"
                    @click:row="doClick"
                    class="common_grid_row_pointer"
                  ></v-data-table>
                </v-card>
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
              <!-- <h3>다국어 관리 정보</h3> -->
              <h3>{{ this.multiLangInfo }}</h3>
            </div>
            <v-row class="common_clear_all">
              <v-col>
                <v-text-field :label="commonCode" v-model="langCode"> </v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field :label="multiLangKorean" v-model="langKo"> </v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field :label="multiLangEnglish" v-model="langEn"> </v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field :label="multiLangChinese" v-model="langCn"> </v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field :label="multiLangVietnames" v-model="langVn"> </v-text-field>
              </v-col>
            </v-row>
          </div>
        </v-col>
      </v-row>
      <v-row justify="center" class="common_button_bottom">
        <v-col cols="auto">
          <v-btn class="common_button_1" @click="language_clear">
            <!-- 초기화 -->
            {{ this.commonReset }}
          </v-btn>
        </v-col>
        <v-col cols="auto">
          <v-btn class="common_search_button" @click="language_refresh">
            <!-- 조회 -->
            {{ this.commonInquiry }}
          </v-btn>
        </v-col>
        <v-spacer></v-spacer>
        <v-col cols="auto">
          <v-btn class="common_default_button" @click="language_add">
            <!-- 추가 -->
            {{ this.commonAdd }}
          </v-btn>
        </v-col>
        <v-col cols="auto">
          <v-btn class="common_default_button" @click="language_modify">
            <!-- 수정 -->
            {{ this.commonModify }}
          </v-btn>
        </v-col>
        <v-col cols="auto">
          <v-btn class="common_default_button" @click="language_delete">
            <!-- 삭제 -->
            {{ this.commonDelete }}
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
    <!-- <v-footer app> 다국어 정보 관리 메뉴 화면 입니다. </v-footer> -->
    <v-footer app> {{ this.multiLangFooter }} </v-footer>
  </v-app>
</template>

<script>
import axios from "axios";
import messages from "@/etc/constants-messages";
import urls from "@/etc/constants-urls";
// import { BUS_LANGUAGE } from "@/etc/EventBus";
import { getLangText, LANG_CODE } from "@/language/Language";

export default {
  created() {
    /* created 훅은 beforeCreate 훅의 다음 단계로서,
       beforeCreate 훅이 호출된 직후 데이터와 이벤트가 초기화되어 created 훅에서는 데이터와 이벤트에 접근할 수 있다. */
    // alert("created() 호출");

    // BUS_LANGUAGE.$on("selectedRow", (value) => {
    //   console.log("value : " + value);
    // this.listInfo = value;
    // console.log("value : " + value);

    // this.langCode = listInfo["langCode"]; // 코드
    // this.langKo = listInfo["langKo"]; // 한국어
    // this.langEn = listInfo["langEn"]; // 영어
    // this.langCn = listInfo["langCn"]; // 중국어
    // this.langVn = listInfo["langVn"]; // 베트남어
    // });

    // 모든 권한 그룹 리스트 조회
    this.language_refresh();

    // 모든 다국어 문자열 조회
    this.labelLangText();

    // 리스트 헤더 정보 구성
    this.initHeaders();
  },
  name: "MultiLang",
  data() {
    return {
      MESSAGES: messages,
      URLS: urls,
      search: "",
      headers: [],
      gridData: [], // 모든 다국어 데이터 정보 (SELECT)
      langCode: "", // 코드
      langKo: "", // 한국어
      langEn: "", // 영어
      langCn: "", // 중국어
      langVn: "", // 베트남어

      srcTitleImage: require("@/assets/images/titleBullet1.png"),

      // 다국어
      commonSearch: "", // 검색
      commonReset: "", // 초기화
      commonInquiry: "", // 조회
      commonAdd: "", // 추가
      commonModify: "", // 수정
      commonDelete: "", // 삭제
      commonCode: "", // 코드
      multiLangList: "", // 모든 다국어 정보
      multiLangInfo: "", // 다국어 관리 정보
      multiLangKorean: "", // 한국어
      multiLangEnglish: "", // 영어
      multiLangChinese: "", // 중국어
      multiLangVietnames: "", // 베트남어
      multiLangFooter: "", // 다국어 정보 관리 메뉴 화면 입니다.
    };
  },

  methods: {
    doClick: function (value) {
      this.langCode = value["langCode"]; // 코드
      this.langKo = value["langKo"]; // 한국어
      this.langEn = value["langEn"]; // 영어
      this.langCn = value["langCn"]; // 중국어
      this.langVn = value["langVn"]; // 베트남어
    },

    // 다국어 적용
    labelLangText: function () {
      this.commonSearch = this.langText(LANG_CODE.COMMON_SEARCH);
      this.commonReset = this.langText(LANG_CODE.COMMON_RESET);
      this.commonInquiry = this.langText(LANG_CODE.COMMON_INQUIRY);
      this.commonAdd = this.langText(LANG_CODE.COMMON_ADD);
      this.commonModify = this.langText(LANG_CODE.COMMON_MODIFY);
      this.commonDelete = this.langText(LANG_CODE.COMMON_DELETE);
      this.commonCode = this.langText(LANG_CODE.COMMON_CODE);
      this.multiLangList = this.langText(LANG_CODE.MULTI_LANG_LIST);
      this.multiLangInfo = this.langText(LANG_CODE.MULTI_LANG_INFO);
      this.multiLangKorean = this.langText(LANG_CODE.MULTI_LANG_KOREAN);
      this.multiLangEnglish = this.langText(LANG_CODE.MULTI_LANG_ENGLISH);
      this.multiLangChinese = this.langText(LANG_CODE.MULTI_LANG_CHINESE);
      this.multiLangVietnames = this.langText(LANG_CODE.MULTI_LANG_VIETNAMES);
      this.multiLangFooter = this.langText(LANG_CODE.MULTI_LANG_FOOTER);
    },

    // 다국어 적용 (axios)
    langText: function (langCode) {
      console.log("keyword : " + langCode);
      return getLangText(langCode);
    },
    // 모든 다국어 리스트 조회 (/all)
    language_refresh: function () {
      this.language_clear();

      axios
        .get(this.URLS.MULTI_LANGUAGE_ALL)
        .then(
          (response) => (
            (this.data = null),
            (this.gridData = response.data),
            console.log(this.gridData)
          )
        )
        .catch((error) => alert(error));
    },

    // 입력 정보 초기화
    language_clear: function () {
      this.search = ""; // 검색
      this.langCode = ""; // 코드
      this.langKo = ""; // 한국어
      this.langEn = ""; // 영어
      this.langCn = ""; // 중국어
      this.langVn = ""; // 베트남어
    },

    // 다국어 정보 저장 (/add)
    language_add: function () {
      if (
        this.langCode === "" ||
        this.langKo === "" ||
        this.langEn === "" ||
        this.langCn === "" ||
        this.langVn === ""
      ) {
        alert(this.MESSAGES.INFO_INPUT_MESSAGE);
      } else {
        axios
          .post(this.URLS.MULTI_LANGUAGE_ADD, {
            langCode: this.langCode,
            langTypKo: "KO",
            langKo: this.langKo,
            langTypEn: "EN",
            langEn: this.langEn,
            langTypCn: "CN",
            langCn: this.langCn,
            langTypVn: "VN",
            langVn: this.langVn,
          })
          .then((response) => {
            alert(this.MESSAGES.MULTI_LANGUAGE_ADD_COMPLETE);

            this.language_clear();
          })
          .catch((error) => alert(this.MESSAGES.MULTI_LANGUAGE_ADD_FAILED));
      }
    },

    // 다국어 정보 저장 (/modify)
    language_modify: function () {
      if (
        this.langCode === "" ||
        this.langKo === "" ||
        this.langEn === "" ||
        this.langCn === "" ||
        this.langVn === ""
      ) {
        alert(this.MESSAGES.INFO_INPUT_MESSAGE);
      } else {
        axios
          .post(this.URLS.MULTI_LANGUAGE_MODIFY, {
            langCode: this.langCode,
            langTypKo: "KO",
            langKo: this.langKo,
            langTypEn: "EN",
            langEn: this.langEn,
            langTypCn: "CN",
            langCn: this.langCn,
            langTypVn: "VN",
            langVn: this.langVn,
          })
          .then((response) => {
            alert(this.MESSAGES.MULTI_LANGUAGE_MODIFY_COMPLETE);

            this.language_clear();
          })
          .catch((error) => alert(this.MESSAGES.MULTI_LANGUAGE_MODIFY_FAILED));
      }
    },

    // 다국어 정보 삭제 (/delete)
    language_delete: function () {
      if (this.langCode === "") {
        alert(this.MESSAGES.INFO_INPUT_MESSAGE);
      } else {
        axios
          .post(this.URLS.MULTI_LANGUAGE_DELETE, {
            langCode: this.langCode,
          })
          .then((response) => {
            alert(this.MESSAGES.MULTI_LANGUAGE_DELETE_COMPLETE);

            this.language_clear();
          })
          .catch((error) => alert(this.MESSAGES.MULTI_LANGUAGE_DELETE_FAILED));
      }
    },

    initHeaders: function () {
      this.headers = [
        {
          text: "NO.",
          align: "start",
          filterable: false,
          value: "no",
          width: "80px",
          fixed: true,
        },
        {
          text: this.commonCode,
          value: "langCode",
          width: "320px",
          fixed: true,
        },
        {
          text: this.multiLangKorean,
          value: "langKo",
          width: "160px",
          fixed: true,
        },
        {
          text: this.multiLangEnglish,
          value: "langEn",
          width: "160px",
          fixed: true,
        },
        {
          text: this.multiLangChinese,
          value: "langCn",
          width: "160px",
          fixed: true,
        },
        {
          text: this.multiLangVietnames,
          value: "langVn",
          width: "160px",
          fixed: true,
        },
      ];
    },
  },
};
</script>

<style scoped="">
.gridTable {
  display: block;
  white-space: nowrap;
  overflow-y: scroll;
  width: 100%;
  border: 2px solid #df6659;
  border-radius: 3px;
  background-color: #fff;
}
.gridTable thead {
  display: block;
  background-color: #fff;
  color: rgba(255, 255, 255, 0.66);
}
.gridTable th {
  background-color: #df6659;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.gridTable tbody {
  display: block;
  max-height: 600px;
}
.gridTable td {
  background-color: #f9f9f9;
}
.gridTable th,
td {
  font-size: 12px;
  padding: 5px 10px;
}
.gridTable th:nth-of-type(1),
.gridTable td:nth-of-type(1) {
  width: 520px;
}
.gridTable th:nth-of-type(2),
.gridTable td:nth-of-type(2) {
  width: 520px;
}
.gridTable th:last-child {
  width: 80px;
}
.gridTable td:last-child {
  width: calc(80px - 19px);
}
.gridTable th.active {
  color: #fff;
}
.gridTable th.active .arrow {
  opacity: 1;
}
.gridTable .arrow {
  display: inline-block;
  vertical-align: middle;
  width: 0;
  height: 0;
  margin-left: 5px;
  opacity: 0.66;
}
.gridTable .arrow.asc {
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-bottom: 4px solid #fff;
}
.gridTable .arrow.dsc {
  border-left: 4px solid transparent;
  border-right: 4px solid transparent;
  border-top: 4px solid #fff;
}
.gridTable tr:hover td {
  cursor: pointer;
  background-color: #fbcfd0;
}
</style>