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
            MESSAGES: messages,
            URLS: urls,
            systemCode: [],
            systemCodeData: [],
            tableName: 'DEPARTMENT',
            srcTitleImage: require("@/assets/images/titleBullet1.png"),
        };
    },
    created() {
        console.log("Created");
        this.operator = localStorage.getItem("user_id");
        console.log("Logined User ID: ", this.operator);
        this.getSystemCodeAll();
        this.getSystemCodeDataAll();
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
    },
};
</script>

<style>
</style>