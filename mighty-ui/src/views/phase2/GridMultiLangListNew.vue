<template>
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
      :items="data"
      :search="search"
      :items-per-page="10"
      :page.sync="page"
      @click:row="doMouseClick"
      class="common_grid_row_pointer"
    ></v-data-table>
  </v-card>
</template>

<script>
// import { BUS_LANGUAGE } from "@/etc/EventBus";
import { getLangText, LANG_KEYWORD } from "@/language/Language";

export default {
  created() {
    // BUS_LANGUAGE.$on("searchClear", (value) => {
    //   console.log(value);
    //   this.search = value;
    //   this.page = 1;
    // });

    this.labelLangText();

    this.initHeaders();
  },
  props: {
    data: Array,
  },
  data() {
    return {
      search: "",
      headers: [],
      commonSearch: "", // 검색
      commonKeyword: "", // 키워드
      commonCode: "", // 코드
      commonText: "", // 문자열
      page: 1,
    };
  },
  methods: {
    // doMouseClick: function (value) {
    //   BUS_LANGUAGE.$emit("selectedRow", value);
    // },

    // 다국어 적용
    labelLangText: function () {
      this.commonSearch = this.langText(LANG_KEYWORD.COMMON_SEARCH);
      this.commonKeyword = this.langText(LANG_KEYWORD.COMMON_KEYWORD);
      this.commonCode = this.langText(LANG_KEYWORD.COMMON_CODE);
      this.commonText = this.langText(LANG_KEYWORD.COMMON_TEXT);
    },

    // 다국어 적용 (axios)
    langText: function (keyword) {
      console.log("keyword : " + keyword);
      return getLangText(keyword);
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
          text: this.commonKeyword,
          value: "langKeyword",
          width: "320px",
          fixed: true,
        },
        {
          text: this.commonCode,
          value: "langCode",
          width: "320px",
          fixed: true,
        },
        {
          text: this.commonText,
          value: "langText",
          width: "300px",
          fixed: true,
        },
      ];
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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