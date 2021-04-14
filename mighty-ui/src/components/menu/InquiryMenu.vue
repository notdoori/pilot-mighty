<template>
  <v-menu bottom offset-y>
    <template v-slot:activator="{ on, attrs }">
      <v-btn
          tile
          depressed
          v-bind="attrs"
          v-on="on"
      >
        Inquiry
      </v-btn>
    </template>
    <v-list>
        <v-list-item
            v-for='item in this.items'
            :key='item.menu[0]'
            @click='itemClicked({id:item.menu[0], value:item.menu[1], content:item.menu[2]})'
        >
          <v-list-item-title>{{item.menu[1]}}</v-list-item-title>
        </v-list-item>
    </v-list>
  </v-menu>
</template>

<script>
export default {
    data() {
		return {
            items: [
                {menu: [10, '조회1', 'inquiry1']},
                {menu: [11, '조회2', 'inquiry2']}
		    ],
            activeItems: 'Piano'
		}
	},
    methods: {
		itemClicked: function(info) {
            this.$emit('selectInquiryItem', info);
		}
	}
}
</script>

<style>

</style>