<template>
    <div>
        <div class="content">
            <div class="left">
                <div class="contTitle">
                    <ul>
                        <li>등록된 사용자 목록</li>
                    </ul>
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
                    <ul>
                        <li>사용자 정보</li>
                    </ul>
                </div>
                <div class="contDetail">
                    <div class="textbox">
                        <table class="inputTable">
                            <tr>
                                <td class="inputTitle">
                                    <label for="userId">User ID</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="userInfo.userId"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td class="inputTitle">
                                    <label for="userName">User Name</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="userInfo.userName"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td class="inputTitle">
                                    <label for="depart">Department</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="userInfo.depart"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td class="inputTitle">
                                    <label for="langType">Language Type</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="userInfo.langType"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <td class="inputTitle">
                                    <label for="userGroup">User Group</label>
                                </td>
                                <td>
                                    <input
                                        type="text"
                                        v-model="userInfo.userGroup"
                                    />
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="bottom">
                <div class="highlightButtonGroup">
                    <a class="highlightButton">초기화</a>
                </div>
                <div class="normalButtonGroup">
                    <a class="normalButton">입력</a>
                    <a class="normalButton">수정</a>
                    <a class="normalButton">삭제</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import userGrid from "@/views/user/GridUserList";
import { BUS } from "@/router/EventBus";

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
                "userName",
                "depart",
                "langType",
                "userGroup",
            ],
            gridData: [],
            userInfo: [],
            titleBulet: "@./assets/images/titleBullet1.png",
        };
    },
    created() {
        this.load();
        BUS.$on("selectedRow", (value) => {
            this.userInfo = value;
            console.log("User ID: ", this.userInfo["userId"]);
            console.log("User Name: ", this.userInfo["userName"]);
            console.log("Department: ", this.userInfo["depart"]);
            console.log("Language Type: ", this.userInfo["langType"]);
            console.log("User Group: ", this.userInfo["userGroup"]);
            this.userId = this.userInfo["userId"];
            this.userName = this.userInfo["userName"];
            this.depart = this.userInfo["depart"];
            this.langType = this.userInfo["langType"];
            this.userGroup = this.userInfo["userGroup"];
        });
    },
    methods: {
        load() {
            axios
                .get("/api/users/all")
                .then((res) => {
                    this.gridData = res.data;
                })
                .catch((e) => {
                    console.error(e);
                });
        },
    },
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
    height: 415px;
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
</style>