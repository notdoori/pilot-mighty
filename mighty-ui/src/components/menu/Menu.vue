<template>
  <v-menu :close-on-content-click="false" bottom offset-y :key="menuKey">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
            tile
            depressed
            v-bind="attrs"
            v-on="on"
            @click="onClick"
        >
            {{menuGroup.name}}
        </v-btn>
      </template>

      <!-- component re-rendering scheme :key="componentKey" -->
      <div v-for="(node, index) in menuGroup.children" :key="index">
          <MenuTree :treeData="node" :key="componentKey"></MenuTree>
          <!-- <MenuTree :treeData="node"></MenuTree> -->
      </div>
      
      <!-- <v-list dense subheader v-for="(node, index) in menuGroup.children" :key="index">
        <div :key="componentKey" v-if="hasChild(node)">
            <v-list-group >
                <template v-slot:activator>
                    <v-list-item-title>{{node.name}}</v-list-item-title>                                          
                </template>

                <v-list-item 
                    v-for="(child, i) in node.children" :key="i" @click="onMenuClick(child)"
                >
                    <v-list-item-title>{{child.name}}</v-list-item-title>  
                </v-list-item>
            </v-list-group>
        </div>
        <div v-else>
            <v-list-item  @click="onMenuClick(node)">
                <v-list-item-title>{{node.name}}</v-list-item-title>         
            </v-list-item>
        </div>        
      </v-list>  -->

  </v-menu>  
</template>

<script>

import MenuTree from '@/components/menu/MenuTree';

export default {
    components: {
        MenuTree
    },
    // props: [{
    //     menuGroup: Object
    // }, 'menuKey'], 
    props: ['menuGroup', 'menuKey'],
    methods: {
        // hasChild(item) {
        //   if (item.children !== null) {
        //       return item.children.length;
        //   } else {
        //       return 0;
        //   }
        // },
        // onMenuClick(item) {
        //   this.showMenu = false;
        //   this.$emit('clickMenu', {id: item.id, name: item.name});
        // },
        onClick() {
          this.forceRerender();
        },
        forceRerender() {
          this.componentKey += 1;
        }
    },
    data() {
        return {
            // showMenu: false,
            componentKey: 0,
        }
    }
}
</script>

<style scoped="">
</style>