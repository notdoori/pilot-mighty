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
            :key='item.menu[1]'
            @click='itemClicked(item.menu[1])'
        >
          <v-list-item-title>{{item.menu[0]}}</v-list-item-title>
        </v-list-item>
    </v-list>
  </v-menu>
</template>

<script>
export default {
    data() {
		return {
            items: [
                {menu: ['조회1', 'inquiry1']},
                {menu: ['조회2', 'inquiry2']}
		    ],
            activeItems: 'Piano'
		}
	},
    methods: {
		itemClicked: function(item) {
            this.$emit('selectInquiryItem', item);
		}
	}
}
</script>

<style>

</style>