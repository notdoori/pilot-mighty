<template>
  <!-- <div>
    <button @click='toggleShow' class='anchor'>System</button>
    <div v-if='showMenu' class='menu'>
        <div
             v-for='item in this.items' :key='item.menu[1]'
             @click='itemClicked(item.menu[1])'>
             {{item.menu[0]}}
        </div>
    </div>
  </div> -->
  <v-menu bottom offset-y>
    <template v-slot:activator="{ on, attrs }">
      <v-btn
          tile
          depressed
          v-bind="attrs"
          v-on="on"
      >
        System
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
                {menu: [0, '권한그룹관리', 'authority']},
                {menu: [1, '사용자그룹관리','groups']},
                {menu: [2, '사용자관리','users']},
                {menu: [100, '로그아웃', 'Logout']}
		    ],
        activeItems: null
		}
	},
    methods: {
		itemClicked: function(info) {
            this.$emit('selectSysItem', info);
		}
	}
}
</script>

<style>

</style>