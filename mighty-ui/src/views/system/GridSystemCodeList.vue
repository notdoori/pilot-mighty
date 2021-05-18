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
import { BUS_SYSTEMCODE } from "@/etc/EventBus";

export default {
    created() {
        BUS_SYSTEMCODE.$on("searchClear", (value) => {
            console.log("EventBus 초기화: ", value);
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
                { text: "Table Name", value: "name", width: "120px", fixed: true },
                { text: "Description", value: "value", width: "140px", fixed: true },
                { text: "Use", value: "use", width: "80px" },
            ],
            page: 1,
        };
    },
    methods: {
        doMouseClick: function (value) {
            BUS_SYSTEMCODE.$emit("selectedRow", value);
        },
    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped="">

</style>