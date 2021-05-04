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
                            <h3>등록된 사용자</h3>
                        </div>
                        <v-row class="common_clear_all">
                            <v-col class="common_grid">
                                <userGrid :data="userData"></userGrid>
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
                            <h3>사용자 정보</h3>
                        </div>
                        <v-form ref="form">
                            <v-row class="common_clear_all">
                                <v-col>
                                    <v-text-field
                                        v-model="userId"
                                        :disabled="isUpdate"
                                        :rules="[rules.required, rules.nonSpace]"
                                        label="User ID"
                                    >
                                    </v-text-field>
                                </v-col>
                                <v-divider vertical></v-divider>
                                <v-col>
                                    <v-text-field
                                        v-model="password"
                                        :disabled="isUpdate"
                                        :append-icon="
                                            showPass ? 'mdi-eye' : 'mdi-eye-off'
                                        "
                                        :rules="[rules.nonSpace]"
                                        :type="showPass ? 'text' : 'password'"
                                        label="Password"
                                        @click:append="showPass = !showPass"
                                    >
                                    </v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        v-model="userName"
                                        :rules="[rules.required, rules.nonSpace]"
                                        label="User Name"
                                    >
                                    </v-text-field>
                                </v-col>
                                <v-divider vertical></v-divider>
                                <v-col>
                                    <v-text-field
                                        v-model="email"
                                        :rules="[rules.emailRules]"
                                        label="E-Mail"
                                    >
                                    </v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        v-model="phone"
                                        :rules="[rules.nonSpace]"
                                        label="Phone Number"
                                    >
                                    </v-text-field>
                                </v-col>
                                <v-divider vertical></v-divider>
                                <v-col>
                                    <v-text-field
                                        v-model="depart"
                                        label="Department"
                                    >
                                    </v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-select
                                        :items="itemsUserGroup"
                                        label="User Group"
                                        v-model="userGroup"
                                        item-value="groupId"
                                        item-text="groupDesc"
                                    >
                                    </v-select>
                                </v-col>
                                <v-divider vertical></v-divider>
                                <v-col></v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-select
                                        :items="itemsLangType"
                                        :rules="[rules.required]"
                                        label="Language"
                                        v-model="langType"
                                        item-value="value"
                                        item-text="name"
                                    >
                                    </v-select>
                                </v-col>
                                <v-divider vertical></v-divider>
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
                </v-col>
            </v-row>
            <v-row justify="center" class="common_button_bottom">
                <v-col cols="auto">
                    <v-btn class="common_button_1" @click="clear">초기화</v-btn>
                </v-col>
                <v-col cols="auto">
                    <v-btn class="common_search_button" @click="getUserAll"
                        >조회</v-btn
                    >
                </v-col>
                <v-spacer></v-spacer>
                <v-col cols="auto">
                    <v-btn class="common_default_button" :disabled="isUpdate" @click="user_regist"
                        >입력</v-btn
                    >
                </v-col>
                <v-col cols="auto">
                    <v-btn class="common_default_button" :disabled="isCreate" @click="user_modify"
                        >수정</v-btn
                    >
                </v-col>
                <v-col cols="auto">
                    <v-btn class="common_default_button" :disabled="isCreate" @click="user_delete"
                        >삭제</v-btn
                    >
                </v-col>
            </v-row>
            <v-dialog v-model="dialog" max-width="290">
                <v-card>
                    <v-card-title class="headline">
                        사용자 정보 수정
                    </v-card-title>

                    <v-card-text> 수정하시겠습니까? </v-card-text>

                    <v-card-actions>
                        <v-spacer></v-spacer>

                        <v-btn
                            color="green darken-1"
                            text
                            @click="dialog = false"
                        >
                            Cancel
                        </v-btn>

                        <v-btn color="green darken-1" text @click="user_modify">
                            OK
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-container>
    </v-app>
</template>

<script>
import axios from "axios";
import userGrid from "@/views/system/GridUserListNew";
//import userGrid from "@/views/system/GridTest";
import { BUS_USERS } from "@/etc/EventBus";
import messages from "@/etc/constants-messages";
import urls from "@/etc/constants-urls";

export default {
    name: "User",
    components: {
        userGrid,
    },
    data() {
        return {
            isCreate: true,
            isUpdate: false,
            dialog: false,
            MESSAGES: messages,
            URLS: urls,
            userData: [],
            userId: null,
            password: null,
            userName: null,
            email: null,
            phone: null,
            depart: null,
            userGroup: null,
            userGroupDesc: null,
            langType: "KO",
            use: "Y",
            operator: null,
            showPass: false,
            rules: {
                required: (value) => !!value || '필수항목을 입력하세요.',
                min: (v) => v.length >= 8 || 'Min 8 characters',
                emailRules: v => /^(([^<>()[\]\\.,;:\s@']+(\.[^<>()\\[\]\\.,;:\s@']+)*)|('.+'))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(v)
                    || '올바른 E-mail 형식이 아닙니다.',
                nonSpace: v => (v || '').indexOf(' ') < 0 || '공백을 허용하지 않습니다.',
            },
            itemsUserGroup: [],
            itemsLangType: [
                { name: '한국어', value: 'KO' },
                { name: '영어', value: 'EN' },
                { name: '중국어', value: 'CN' },
            ],
            itemsUse: [
                { name: '사용', value: 'Y' },
                { name: '미사용', value: 'N' },
            ],
            srcTitleImage: require("@/assets/images/titleBullet1.png"),
        };
    },
    created() {
        console.log("Created");
        this.operator = localStorage.getItem("user_id");
        console.log("Logined User ID: ", this.operator);
        this.getGroupAll();
        this.getUserAll();

        BUS_USERS.$on("selectedRow", (value) => {
            this.userId = value["userId"];
            this.userName = value["userName"];
            this.email = value["email"];
            this.phone = value["phone"];
            this.depart = value["depart"];
            this.userGroup = value["userGroup"];
            this.userGroupDesc = value["userGroupDesc"];
            this.langType = value["langType"];
            this.use = value["use"];
            console.log("value: ", value);
            this.isCreate = false;
            this.isUpdate = true;
            this.$refs.form.resetValidation();
        });
    },
    watch: {
        userId: function() {
            console.log("uppercase value", this.userId);
            return this.userId = this.isEmpty(this.userId) ? null : this.userId.toUpperCase();
        },

    },
    beforeUpdate() {
        console.log("beforeUpdate...");
    },
    updated() {
        console.log("updated...");
    },
    methods: {
        getUserAll: function () {
            axios
                .get(this.URLS.USER_ALL)
                .then((res) => {
                    this.userData = res.data;
                    console.log("User Data: ", res.data);
                })
                .catch((e) => {
                    console.error(e);
                });
        },
        getGroupAll: function () {
            axios
                .get(this.URLS.GROUP_ALL)
                .then((res) => {
                    this.itemsUserGroup = res.data;
                    console.log("Group Data: ", res.data);
                })
                .catch((e) => {
                    console.error(e);
                });
        },
        clear: function () {
            this.$refs.form.resetValidation();
            this.userId = null;
            this.password = null;
            this.userName = null;
            this.email = null;
            this.phone = null;
            this.depart = null;
            this.userGroup = null;
            this.langType = 'KO';
            this.use = 'Y';
            this.doSearchClear();
            this.getGroupAll();
            this.getUserAll();
            this.isCreate = true;
            this.isUpdate = false;
        },
        // Search 정보 초기화
        doSearchClear: function () {
            BUS_USERS.$emit("searchClear", "");
        },
        checkRequired: function (mode) {
            if (mode === "D") {
                if (this.isEmpty(this.userId)) {
                    alert(this.MESSAGES.ID_INPUT_MESSAGE);
                    return false;
                }
            } else {
                if (this.isEmpty(this.userId)) {
                    alert(this.MESSAGES.ID_INPUT_MESSAGE);
                    return false;
                } else if (mode === "C" && this.isEmpty(this.password)) {
                    alert(this.MESSAGES.PW_INPUT_MESSAGE);
                    return false;
                } else if (this.isEmpty(this.userName)) {
                    alert(this.MESSAGES.NAME_INPUT_MESSAGE);
                    return false;
                } else if (this.isEmpty(this.userGroup)) {
                    alert(this.MESSAGES.USERGROUP_SELECT_MESSAGE);
                    return false;
                } else if (this.isEmpty(this.langType)) {
                    alert(this.MESSAGES.LANGTYPE_SELECT_MESSAGE);
                    return false;
                } else if (this.isEmpty(this.use)) {
                    alert(this.MESSAGES.USE_SELECT_MESSAGE);
                    return false;
                }
                if (!this.$refs.form.validate()) {
                alert(this.MESSAGES.VALIDATION_FAILED);
                return false;
            }
            }
            return true;
        },
        user_regist: function () {
            if (!this.checkRequired("C")) {
                return;
            } else {
                axios
                    .post(this.URLS.USER_REGIST, {
                        userId: this.userId,
                        password: this.password,
                        userName: this.userName,
                        email: this.email,
                        phone: this.phone,
                        depart: this.depart,
                        userGroup: this.userGroup,
                        langType: this.langType,
                        use: this.use,
                        operator: this.operator,
                    })
                    .then((res) => {
                        console.log(res.data);
                        alert(this.MESSAGES.USER_REGIST_COMPLETE);
                        this.clear();
                    })
                    .catch((error) => {
                        console.log(error),
                            alert(this.MESSAGES.USER_REGIST_FAILED);
                    });
            }
        },
        user_modify: function () {
            if (!this.checkRequired("U")) {
                return;
            } else {
                axios
                    .post(this.URLS.USER_MODIFY, {
                        userId: this.userId,
                        password: this.password,
                        userName: this.userName,
                        email: this.email,
                        phone: this.phone,
                        depart: this.depart,
                        userGroup: this.userGroup,
                        langType: this.langType,
                        use: this.use,
                        operator: this.operator,
                    })
                    .then((res) => {
                        console.log(res.data);
                        alert(this.MESSAGES.USER_MODIFY_COMPLETE);
                        this.clear();
                    })
                    .catch((error) => {
                        alert(this.MESSAGES.USER_MODIFY_FAILED);
                    });
            }
        },
        user_delete: function () {
            if (!this.checkRequired("D")) {
                return;
            } else {
                axios
                    .post(this.URLS.USER_DELETE, {
                        userId: this.userId,
                    })
                    .then((res) => {
                        console.log(res.data);
                        alert(this.MESSAGES.USER_DELETE_COMPLETE);
                        this.clear();
                    })
                    .catch((error) => {
                        console.log(error.data),
                            alert(this.MESSAGES.USER_DELETE_FAILED);
                    });
            }
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
    beforeDestroy() {
        console.log("beforeDestroy...");
        BUS_USERS.$off("selectedRow");
    },
    destroyed() {
        console.log("Destroyed...");
    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>