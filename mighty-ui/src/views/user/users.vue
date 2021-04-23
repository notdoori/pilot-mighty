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
                        <!--<v-row class="common_clear_all">
                            <v-spacer></v-spacer>
                            <v-col cols="4" class="common_grid_search">
                                <v-text-field
                                    v-model="searchQuery"
                                    label="Search"
                                    clearable
                                    class="common_grid_search_field"
                                >
                                </v-text-field>
                            </v-col>
                        </v-row>-->
                        <v-row class="common_clear_all">
                            <v-col class="common_grid">
                                <userGrid
                                    :data="gridData"
                                    :columns="gridColumns"
                                    :filter-key="searchQuery"
                                ></userGrid>
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
                        <v-row class="common_clear_all">
                            <v-col>
                                <v-text-field
                                    v-model="userId"
                                    :rules="[rules.required]"
                                    label="User ID"
                                >
                                </v-text-field>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col>
                                <v-text-field
                                    v-model="password"
                                    :append-icon="
                                        showPass ? 'mdi-eye' : 'mdi-eye-off'
                                    "
                                    :rules="[rules.required]"
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
                                    :rules="[rules.required]"
                                    label="User Name"
                                >
                                </v-text-field>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col>
                                <v-text-field v-model="email" label="E-Mail">
                                </v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <v-text-field
                                    v-model="phone"
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
                                <v-text-field
                                    v-model="userGroup"
                                    label="User Group"
                                >
                                </v-text-field>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col></v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <v-select
                                    :items="itemsLangType"
                                    :rules="[rules.required]"
                                    label="Language Type"
                                    v-model="langType"
                                >
                                </v-select>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col>
                                <v-select
                                    :items="itemsYN"
                                    :rules="[rules.required]"
                                    label="Use YN"
                                    v-model="use"
                                >
                                </v-select>
                            </v-col>
                        </v-row>
                    </div>
                </v-col>
            </v-row>
            <v-row justify="center" class="common_button_bottom">
                <v-col cols="auto">
                    <v-btn class="common_button_1" @click="clear">초기화</v-btn>
                </v-col>
                <v-spacer></v-spacer>
                <v-col cols="auto">
                    <v-btn class="common_default_button" @click="user_regist"
                        >입력</v-btn
                    >
                </v-col>
                <v-col cols="auto">
                    <v-btn class="common_default_button" @click="user_modify"
                        >수정</v-btn
                    >
                </v-col>
                <v-col cols="auto">
                    <v-btn class="common_default_button" @click="user_delete"
                        >삭제</v-btn
                    >
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import axios from "axios";
import userGrid from "@/views/user/GridUserListNew";
import { userBUS } from "@/router/EventBus";

const USER_ALL = "/api/users/all";
const USER_REGIST = "/api/users/regist";
const USER_MODIFY = "/api/users/modify";
const USER_DELETE = "/api/users/delete";

const ID_INPUT_MESSAGE = "ID를 입력해주세요.";
const PW_INPUT_MESSAGE = "비밀번호를 입력해주세요.";
const NAME_INPUT_MESSAGE = "사용자 이름을 입력해주세요.";
const USER_REGIST_COMPLETE = "사용자 등록이 완료되었습니다.";
const USER_REGIST_FAILED = "사용자 등록을 실패하였습니다.";
const USER_MODIFY_COMPLETE = "사용자 수정이 완료되었습니다.";
const USER_MODIFY_FAILED = "사용자 수정을 실패하였습니다.";
const USER_DELETE_COMPLETE = "사용자 삭제가 완료되었습니다.";
const USER_DELETE_FAILED = "사용자 삭제를 실패하였습니다.";

export default {
    name: "User",
    components: {
        userGrid,
    },
    data() {
        return {
            searchQuery: "",
            gridColumns: [
                "no",
                "userId",
                "password",
                "userName",
                "email",
                "phone",
                "depart",
                "userGroup",
                "langType",
                "use",
            ],
            gridData: [],
            userId: "",
            password: "",
            userName: "",
            email: "",
            phone: "",
            depart: "",
            userGroup: "",
            langType: "",
            use: "",
            showPass: false,
            rules: {
                required: (value) => !!value || "Required.",
                min: (v) => v.length >= 8 || "Min 8 characters",
                emailMatch: () =>
                    `The email and password you entered don't match`,
            },
            itemsLangType: ["KO", "EN", "CN"],
            itemsYN: ["Y", "N"],
            srcTitleImage: require("@/assets/images/titleBullet1.png"),
        };
    },
    created() {
        this.load();
        userBUS.$on("selectedRow", (value) => {
            console.log("User ID: ", value["userId"]);
            console.log("PW: ", value["password"]);
            console.log("User Name: ", value["userName"]);
            console.log("E-Mail: ", value["email"]);
            console.log("Phone: ", value["phone"]);
            console.log("Department: ", value["depart"]);
            console.log("User Group: ", value["userGroup"]);
            console.log("Language Type: ", value["langType"]);
            console.log("Use YN: ", value["use"]);
            this.userId = value["userId"];
            this.password = value["password"];
            this.userName = value["userName"];
            this.email = value["email"];
            this.phone = value["phone"];
            this.depart = value["depart"];
            this.userGroup = value["userGroup"];
            this.langType = value["langType"];
            this.use = value["use"];
        });
    },
    methods: {
        load() {
            axios
                .get(USER_ALL)
                .then((res) => {
                    this.gridData = res.data;
                    console.log("Select Data: ", res.data);
                })
                .catch((e) => {
                    console.error(e);
                });
        },
        clear: function () {
            (this.searchQuery = ""),
                (this.userId = ""),
                (this.password = ""),
                (this.userName = ""),
                (this.email = ""),
                (this.phone = ""),
                (this.depart = ""),
                (this.userGroup = ""),
                (this.langType = ""),
                (this.use = "");
        },
        checkRequired: function () {
            if (this.userId === "") {
                alert(ID_INPUT_MESSAGE);
                return false;
            } else if (this.password === "") {
                alert(PW_INPUT_MESSAGE);
                return false;
            } else if (this.userName === "") {
                alert(NAME_INPUT_MESSAGE);
                return false;
            }
            return true;
        },
        user_regist: function () {
            if (!this.checkRequired()) {
                return;
            } else {
                console.log(this.userId);
                axios
                    .post(USER_REGIST, {
                        userId: this.userId,
                        password: this.password,
                        userName: this.userName,
                        email: this.email,
                        phone: this.phone,
                        depart: this.depart,
                        userGroup: this.userGroup,
                        langType: this.langType,
                        use: this.use,
                    })
                    .then((res) => {
                        console.log(res.data),
                            alert(USER_REGIST_COMPLETE),
                            this.clear(),
                            this.load();
                    })
                    .catch((error) => {
                        console.log(error.data), alert(USER_REGIST_FAILED);
                    });
            }
        },
        user_modify: function () {
            if (this.userId === "") {
                alert(ID_INPUT_MESSAGE);
            } else {
                axios
                    .post(USER_MODIFY, {
                        userId: this.userId,
                        password: this.password,
                        userName: this.userName,
                        email: this.email,
                        phone: this.phone,
                        depart: this.depart,
                        userGroup: this.userGroup,
                        langType: this.langType,
                        use: this.use,
                    })
                    .then((res) => {
                        console.log(res.data),
                            alert(USER_MODIFY_COMPLETE),
                            this.clear(),
                            this.load();
                    })
                    .catch((error) => {
                        console.log(error.data), alert(USER_MODIFY_FAILED);
                    });
            }
        },
        user_delete: function () {
            if (this.userId === "") {
                alert(ID_INPUT_MESSAGE);
            } else {
                axios
                    .post(USER_DELETE, {
                        userId: this.userId,
                    })
                    .then((res) => {
                        console.log(res.data),
                            alert(USER_DELETE_COMPLETE),
                            this.clear(),
                            this.load();
                    })
                    .catch((error) => {
                        console.log(error.data), alert(USER_DELETE_FAILED);
                    });
            }
        },
    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>