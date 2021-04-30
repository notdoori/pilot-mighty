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
            :page.sync="page"
            @click:row="doMouseClick"
            class="common_grid_row_pointer"
        ></v-data-table>
    </v-card>
</template>

<script>
import { BUS_USERS } from "@/etc/EventBus";

export default {
    created() {
        BUS_USERS.$on("searchClear", (value) => {
            console.log(value);
            this.search = value;
            this.page = 1;
        });
    },
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
                { text: "User ID", value: "userId", width: "120px", fixed: true },
                { text: "User Name", value: "userName", width: "140px", fixed: true },
                { text: "E-Mail", value: "email", width: "150px" },
                { text: "Phone", value: "phone", width: "150px" },
                { text: "Department", value: "depart", width: "120px" },
                { text: "User Group", value: "userGroup", width: "150px" },
                { text: "User Group Desc", value: "userGroupDesc", width: "200px" },
                { text: "Language", value: "langType", width: "120px" },
                { text: "Use", value: "use", width: "80px" },
            ],
            page: 1,
        };
    },
    methods: {
        doMouseClick: function (value) {
            BUS_USERS.$emit("selectedRow", value);
        },
    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped="">

</style>