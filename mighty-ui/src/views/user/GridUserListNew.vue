<template>
    <v-card>
        <v-card-title>
            <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
            ></v-text-field>
        </v-card-title>
        <v-data-table
            :headers="headers"
            :items="data"
            :search="search"
            :items-per-page="5"
            @click:row="doMouseClick"
            class="common_grid_row_pointer"
        ></v-data-table>
    </v-card>
</template>

<script>
import { userBUS } from "@/etc/EventBus";

export default {
    props: {
        data: Array
    },
    data() {
        return {
            search: "",
            headers: [
                {
                    text: "NO.",
                    align: "start",
                    filterable: false,
                    value: "no",
                    width: "80px",
                    fixed: true
                },
                { text: "User ID", value: "userId", width: "80px", fixed: true },
                { text: "PW", value: "password",width: "0px", fixed: true },
                { text: "User Name", value: "userName", width: "120px", fixed: true },
                { text: "E-Mail", value: "eMail", width: "150px" },
                { text: "Phone", value: "phone", width: "150px" },
                { text: "Department", value: "depart", width: "120px" },
                { text: "User Group", value: "userGroup", width: "200px" },
                { text: "Language", value: "langType", width: "80px" },
                { text: "Use", value: "use", width: "80px" },
            ],
        };
    },
    methods: {
        doMouseClick: function (value) {
            userBUS.$emit("selectedRow", value);
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
    width: 80px;
}
.gridTable th:nth-of-type(2),
.gridTable td:nth-of-type(2) {
    width: 120px;
}
.gridTable th:nth-of-type(3),
.gridTable td:nth-of-type(3) {
    width: 100px;
}
.gridTable th:nth-of-type(4),
.gridTable td:nth-of-type(4) {
    width: 120px;
}
.gridTable th:nth-of-type(5),
.gridTable td:nth-of-type(5) {
    width: 120px;
}
.gridTable th:nth-of-type(6),
.gridTable td:nth-of-type(6) {
    width: 100px;
}
.gridTable th:nth-of-type(7),
.gridTable td:nth-of-type(7) {
    width: 140px;
}
.gridTable th:nth-of-type(8),
.gridTable td:nth-of-type(8) {
    width: 150px;
}
.gridTable th:nth-of-type(9),
.gridTable td:nth-of-type(9) {
    width: 80px;
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