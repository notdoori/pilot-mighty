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
                            <h3>시스템 코드</h3>
                        </div>
                        <v-row class="common_clear_all">
                            <v-col class="common_grid">
                                <systemCodeGrid :data="systemCode"></systemCodeGrid>
                            </v-col>
                        </v-row>
                        <div class="common_subcontent_box">
                            <div class="common_subtitle_bullet">
                                <v-img
                                    :src="srcSubTitleImage"
                                    max-width="22px"
                                    max-height="22px"
                                ></v-img>
                            </div>
                            <div class="common_title_text">
                                <h4>시스템 코드 정보</h4>
                            </div>
                            <v-form ref="form">
                                <v-row class="common_subcontent_item">
                                    <v-col>
                                        <v-text-field
                                            v-model="tableName"
                                            :disabled="isUpdate"
                                            :rules="[rules.required, rules.nonSpace]"
                                            label="Table Name"
                                        >
                                        </v-text-field>
                                    </v-col>
                                </v-row>
                                <v-row class="common_subcontent_item">
                                    <v-col>
                                        <v-text-field
                                            v-model="tableDesc"
                                            label="Table Description"
                                        >
                                        </v-text-field>
                                    </v-col>
                                </v-row>
                                <v-row class="common_subcontent_item">
                                    <v-col>
                                        <v-select
                                            :items="itemsUse"
                                            :rules="[rules.required]"
                                            label="Use"
                                            v-model="use"
                                            item-value="value"
                                            item-text="name"
                                        >
                                        </v-select>
                                    </v-col>
                                </v-row>
                            </v-form>
                        </div>
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
                            <h3>시스템 코드 데이터</h3>
                        </div>
                        <v-row class="common_clear_all">
                            <v-col class="common_grid">
                                <systemCodeDataGrid :data="systemCodeData"></systemCodeDataGrid>
                            </v-col>
                        </v-row>
                        <div class="common_subcontent_box">
                            <div class="common_subtitle_bullet">
                                <v-img
                                    :src="srcSubTitleImage"
                                    max-width="22px"
                                    max-height="22px"
                                ></v-img>
                            </div>
                            <div class="common_title_text">
                                <h4>시스템 코드 데이터 정보</h4>
                            </div>
                            <v-form ref="form">
                                <v-row class="common_subcontent_item">
                                    <v-col>
                                        <v-text-field
                                            v-model="codeName"
                                            :disabled="isUpdate"
                                            :rules="[rules.required, rules.nonSpace]"
                                            label="Code Name"
                                        >
                                        </v-text-field>
                                    </v-col>
                                </v-row>
                                <v-row class="common_subcontent_item">
                                    <v-col>
                                        <v-text-field
                                            v-model="codeDesc"
                                            label="Code Description"
                                        >
                                        </v-text-field>
                                    </v-col>
                                </v-row>
                                <v-row class="common_subcontent_item">
                                    <v-col>
                                        <v-text-field
                                            v-model="codeSeq"
                                            label="Code Sequence"
                                        >
                                        </v-text-field>
                                    </v-col>
                                </v-row>
                            </v-form>
                        </div>
                    </div>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import axios from "axios";
import systemCodeGrid from "@/views/system/GridSystemCodeList";
import systemCodeDataGrid from "@/views/system/GridSystemCodeDataList";
import { BUS_SYSTEMCODE } from "@/etc/EventBus";
import { BUS_SYSTEMCODEDATA } from "@/etc/EventBus";
import messages from "@/etc/constants-messages";
import urls from "@/etc/constants-urls";

export default {
    name: "SystemCode",
    components: {
        systemCodeGrid,
        systemCodeDataGrid,
    },
    data() {
        return {
            isCreate: true,
            isUpdate: false,
            MESSAGES: messages,
            URLS: urls,
            systemCode: [],
            systemCodeData: [],
            tableName: 'DEPARTMENT',
            tableDesc: null,
            use: "Y",
            codeName: null,
            codeDesc: null,
            codeSeq: null,
            rules: {
                required: (value) => !!value || this.MESSAGES.MSG_INPUT_REQUIRED,
                min: (v) => v.length >= 8 || 'Min 8 characters',
                nonSpace: v => (v || '').indexOf(' ') < 0 || this.MESSAGES.MSG_NON_SPACE,
            },
            itemsUse: [
                //{ name: '사용', value: 'Y' },
                //{ name: '미사용', value: 'N' },
            ],
            srcTitleImage: require("@/assets/images/titleBullet1.png"),
            srcSubTitleImage: require("@/assets/images/titleBullet2.png"),
        };
    },
    created() {
        console.log("Created");
        this.operator = localStorage.getItem("user_id");
        console.log("Logined User ID: ", this.operator);
        this.getSystemCodeAll();
        this.getSystemCodeDataAll();
        this.itemsUse = this.getSystemCodeData('USE_YN');

        BUS_SYSTEMCODE.$on("selectedRow", (value) => {
            this.tableName = value["name"];
            this.tableDesc = value["value"];
            this.use = value["use"];
            console.log("value: ", value);
            this.isCreate = false;
            this.isUpdate = true;
            this.$refs.form.resetValidation();
        });

        BUS_SYSTEMCODEDATA.$on("selectedRow", (value) => {
            this.codeName = value["name"];
            this.codeDesc = value["value"];
            this.codeSeq = value["seq"];
            console.log("value: ", value);
            this.isCreate = false;
            this.isUpdate = true;
            this.$refs.form.resetValidation();
        });
    },
    watch: {
        userId: function() {
            console.log("uppercase value", this.tableName);
            return this.tableName = this.isEmpty(this.tableName) ? null : this.tableName.toUpperCase();
        },

    },
    beforeUpdate() {
        console.log("beforeUpdate...");
    },
    updated() {
        console.log("updated...");
    },
    methods: {
        getSystemCodeAll: function () {
            axios
                .get(this.URLS.SYSTEM_CODE_ALL)
                .then((res) => {
                    this.systemCode = res.data;
                    console.log("System Code: ", res.data);
                })
                .catch((e) => {
                    console.error(e);
                });
        },
        getSystemCodeDataAll: function () {
            axios
                .get(this.URLS.SYSTEM_CODE_DATA_ALL, {
                    params: {
                        tableName: this.tableName,
                    }
                })
                .then((res) => {
                    this.systemCodeData = res.data;
                    console.log("System Code Data: ", res.data);
                })
                .catch((e) => {
                    console.error(e);
                });
        },
        getSystemCodeData: function (code) {
            axios
                .get(this.URLS.SYSTEM_CODE_DATA_SEARCH, {
                    params: {
                        tableName: code,
                        use: 'Y',
                    }
                })
                .then((res) => {
                    return res.data;
                    console.log("System Code Data: ", res.data);
                })
                .catch((e) => {
                    console.error(e);
                });
        },
        clear: function () {
            //this.$refs.form.resetValidation();
            this.tableName = null;
            this.tableDesc = null;
            this.use = 'Y';
            this.doSearchClear();
            this.getSystemCodeAll();
            this.getSystemCodeDataAll();
            this.isCreate = true;
            this.isUpdate = false;
        },
        // Search 정보 초기화
        doSearchClear: function () {
            BUS_USERS.$emit("searchClear", "");
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
    },
};
</script>

<style>
</style>