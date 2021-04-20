<template>
    <div>
        <div class="content">
            <div class="left">
                <div class="contTitle">
                    <h2>등록된 사용자</h2>
                </div>
                <div class="search">
                    <form id="search">
                        <span class="input input--jiro">
                            <input
                                class="input__field input__field--jiro"
                                type="text"
                                id="queryinput"
                                name="query"
                                v-model="searchQuery"
                            />
                            <label
                                class="input__label input__label--jiro"
                                for="queryinput"
                            >
                                <span
                                    class="input__label-content input__label-content--jiro"
                                    >Search</span
                                >
                            </label>
                        </span>
                    </form>
                </div>
                <div class="grid">
                    <userGrid
                        :data="gridData"
                        :columns="gridColumns"
                        :filter-key="searchQuery"
                    ></userGrid>
                </div>
            </div>
            <div class="right">
                <div class="contTitle">
                    <h2>사용자 정보</h2>
                </div>
                <div class="contDetail">
                    <div class="textbox">
                        <table class="inputTable">
                            <tr>
                                <td>
                                    <label for="userId">User ID</label>
                                </td>
                                <td>
                                    <input
                                        class="inputUpper"
                                        type="text"
                                        v-model="userId"
                                        required
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="password">Password</label>
                                </td>
                                <td>
                                    <input
                                        type="password"
                                        v-model="password"
                                        required
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="userName">User Name</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="userName"
                                        required
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="email">E-Mail</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="email"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="phone">Phone Number</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="phone"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="depart">Department</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="depart"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="userGroup">User Group</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="userGroup"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="langType">Language Type</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="langType"
                                        required
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="use">Use YN</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="use"
                                    />
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="bottom">
                <div class="highlightButtonGroup">
                    <a class="highlightButton" @click="load">초기화</a>
                </div>
                <div class="normalButtonGroup">
                    <a class="normalButton" @click="user_regist">입력</a>
                    <a class="normalButton" @click="user_modify">수정</a>
                    <a class="normalButton" @click="user_delete">삭제</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import userGrid from "@/views/user/GridUserList";
import { userBUS } from "@/router/EventBus";

const USER_ALL = "/api/users/all";
const USER_REGIST = "/api/users/regist";
const USER_MODIFY = "/api/users/modify";
const USER_DELETE = "/api/users/delete";

const ID_INPUT_MESSAGE = "ID를 입력해주세요.";
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
                "use"
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
            use: ""
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
        clear: function() {
            this.searchQuery = "",
            this.userId = "",
            this.password = "",
            this.userName = "",
            this.email = "",
            this.phone = "",
            this.depart = "",
            this.userGroup = "",
            this.langType = "",
            this.use = ""
        },
        user_regist: function() {
            if(this.userId === "") {
                alert(ID_INPUT_MESSAGE);
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
                    use: this.use
                })
                .then(res => {
                    console.log(res.data),
                    alert(USER_REGIST_COMPLETE),
                    this.clear(),
                    this.load()
                })
                .catch(error => {
                    console.log(error.data),
                    alert(USER_REGIST_FAILED)
                });
            }
        },
        user_modify: function() {
            if(this.userId === "") {
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
                    use: this.use
                })
                .then(res => {
                    console.log(res.data),
                    alert(USER_MODIFY_COMPLETE),
                    this.clear(),
                    this.load()
                })
                .catch(error => {
                    console.log(error.data),
                    alert(USER_MODIFY_FAILED)
                });
            }
        },
        user_delete: function() {
            if(this.userId === "") {
                alert(ID_INPUT_MESSAGE);
            } else {
                axios
                .post(USER_DELETE, {
                    userId: this.userId
                })
                .then(res => {
                    console.log(res.data),
                    alert(USER_DELETE_COMPLETE),
                    this.clear(),
                    this.load()
                })
                .catch(error => {
                    console.log(error.data),
                    alert(USER_DELETE_FAILED)
                });
            }
        }
    }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped="">
html,
body {
    padding: 10px;
    padding-right: 20px;
    height: 95%;
}
.content {
    float: left;
    width: 100%;
    height: 100%;
    margin: 20px auto;
    padding: 20px;
    border: 3px solid #1976d2;
    border-radius: 10px;
}
.left {
    float: left;
    width: 730px;
}
.search {
}
.grid {
}
.right {
    float: left;
    width: 50%;
    height: 100%;
    padding-left: 20px;
    min-width: 480px;
}
.contTitle {
    padding-top: 20px;
}
.contDetail {
    margin-top: 20px;
    padding-top: 20px;
    padding-bottom: 20px;
    border: 1px solid #bebebe;
    border-radius: 3px;
    background-color: #f2f2f2;
}
.bottom {
    clear: both;
}
.inputTable {
    width: 100%;
}
.inputTable th,
td {
    font-size: 80%;
    min-width: 20px;
    padding: 5px 10px;
}
.inputTitle {
    width: 150px;
}
.textbox {
    position: relative;
    padding: 0.4em 0.2em;
}
.textbox label {
    padding: 0.8em 0.5em; /* input 요소의 padding 값 만큼 */
    color: #999;
}
.textbox input[type="text"],
.textbox input[type="password"] {
    width: 300px; /* 원하는 너비 설정 */
    height: auto; /* 높이값 초기화 */
    line-height: normal; /* line-height 초기화 */
    padding: 0.8em 0.5em; /* 원하는 여백 설정, 상하단 여백으로 높이를 조절 */
    font-family: inherit; /* 폰트 상속 */
    border: 1px solid #999;
    background-color: #fff;
}
.inputUpper {
    text-transform: uppercase;
}
.normalButtonGroup {
    float: right;
    padding: 10px;
}
.normalButton {
    margin: 5px;
    box-shadow: inset 0px 1px 0px 0px #bee2f9;
    background: linear-gradient(to bottom, #63b8ee 5%, #468ccf 100%);
    background-color: #63b8ee;
    border-radius: 6px;
    border: 1px solid #3866a3;
    display: inline-block;
    cursor: pointer;
    color: #14396a;
    font-family: Arial;
    font-size: 15px;
    font-weight: bold;
    padding: 6px 24px;
    text-decoration: none;
    text-shadow: 0px 1px 0px #7cacde;
}
.normalButton:hover {
    background: linear-gradient(to bottom, #468ccf 5%, #63b8ee 100%);
    background-color: #468ccf;
}
.normalButton:active {
    position: relative;
    top: 1px;
}
.highlightButtonGroup {
    float: left;
    padding: 10px;
}
.highlightButton {
    margin: 5px;
    box-shadow: inset 0px 1px 0px 0px #fff6af;
    background: linear-gradient(to bottom, #ffec64 5%, #ffab23 100%);
    background-color: #ffec64;
    border-radius: 6px;
    border: 1px solid #ffaa22;
    display: inline-block;
    cursor: pointer;
    color: #333333;
    font-family: Arial;
    font-size: 15px;
    font-weight: bold;
    padding: 6px 24px;
    text-decoration: none;
    text-shadow: 0px 1px 0px #ffee66;
}
.highlightButton:hover {
    background: linear-gradient(to bottom, #ffab23 5%, #ffec64 100%);
    background-color: #ffab23;
}
.highlightButton:active {
    position: relative;
    top: 1px;
}

.input {
    position: relative;
    z-index: 1;
    display: inline-block;
    margin: 1em;
    max-width: 200px;
    width: calc(100% - 2em);
    vertical-align: top;
}

.input__field {
    position: relative;
    display: block;
    float: right;
    padding: 0.8em;
    width: 60%;
    border: none;
    border-radius: 0;
    background: #f0f0f0;
    color: #aaa;
    font-weight: bold;
    -webkit-appearance: none; /* for box shadows to show on iOS */
}

.input__field:focus {
    outline: none;
}

.input__label {
    display: inline-block;
    float: right;
    padding: 0 1em;
    width: 40%;
    color: #6a7989;
    font-weight: bold;
    font-size: 70%;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    -webkit-touch-callout: none;
    -webkit-user-select: none;
    -khtml-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}

.input__label-content {
    position: relative;
    display: block;
    padding: 1.6em 0;
    width: 100%;
}

/* Individual styles */

/* Jiro */
.input--jiro {
    margin-top: 2em;
}

.input__field--jiro {
    padding: 0.85em 0.5em;
    width: 100%;
    background: transparent;
    color: #dde2e2;
    opacity: 0;
    -webkit-transition: opacity 0.3s;
    transition: opacity 0.3s;
}

.input__label--jiro {
    position: absolute;
    left: 0;
    padding: 0 0.85em;
    width: 100%;
    height: 100%;
    text-align: left;
    pointer-events: none;
}

.input__label-content--jiro {
    -webkit-transition: -webkit-transform 0.3s 0.3s;
    transition: transform 0.3s 0.3s;
}

.input__label--jiro::before,
.input__label--jiro::after {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    -webkit-transition: -webkit-transform 0.3s;
    transition: transform 0.3s;
}

.input__label--jiro::before {
    border-top: 2px solid #6a7989;
    -webkit-transform: translate3d(0, 100%, 0) translate3d(0, -2px, 0);
    transform: translate3d(0, 100%, 0) translate3d(0, -2px, 0);
    -webkit-transition-delay: 0.3s;
    transition-delay: 0.3s;
}

.input__label--jiro::after {
    z-index: -1;
    background: #6a7989;
    -webkit-transform: scale3d(1, 0, 1);
    transform: scale3d(1, 0, 1);
    -webkit-transform-origin: 50% 0%;
    transform-origin: 50% 0%;
}

.input__field--jiro:focus,
.input--filled .input__field--jiro {
    opacity: 1;
    -webkit-transition-delay: 0.3s;
    transition-delay: 0.3s;
}

.input__field--jiro:focus + .input__label--jiro .input__label-content--jiro,
.input--filled .input__label-content--jiro {
    -webkit-transform: translate3d(0, -80%, 0);
    transform: translate3d(0, -80%, 0);
    -webkit-transition-timing-function: cubic-bezier(0.2, 1, 0.3, 1);
    transition-timing-function: cubic-bezier(0.2, 1, 0.3, 1);
}

.input__field--jiro:focus + .input__label--jiro::before,
.input--filled .input__label--jiro::before {
    -webkit-transition-delay: 0s;
    transition-delay: 0s;
}

.input__field--jiro:focus + .input__label--jiro::before,
.input--filled .input__label--jiro::before {
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
}

.input__field--jiro:focus + .input__label--jiro::after,
.input--filled .input__label--jiro::after {
    -webkit-transform: scale3d(1, 1, 1);
    transform: scale3d(1, 1, 1);
    -webkit-transition-delay: 0.3s;
    transition-delay: 0.3s;
    -webkit-transition-timing-function: cubic-bezier(0.2, 1, 0.3, 1);
    transition-timing-function: cubic-bezier(0.2, 1, 0.3, 1);
}
</style>