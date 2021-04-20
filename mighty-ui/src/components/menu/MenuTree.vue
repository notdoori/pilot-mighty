<template>
<!-- component re-rendering scheme :key="componentKey" -->
  <div :key="componentKey">
      <v-list dense subheader>
        <div v-if="isFolder">
            <v-list-group >
                <template v-slot:activator>
                    <v-list-item-title>{{treeData.name}}</v-list-item-title>                                          
                </template>

                <tree-node v-for="(node, index) in treeData.children" :key="index" :treeData="node"
                >
                </tree-node>
          
            </v-list-group>
        </div>
        <div v-else>
            <v-list-item  @click="onNodeClick(treeData)">
                <v-list-item-title>{{treeData.name}}</v-list-item-title>         
            </v-list-item>
        </div>        
      </v-list> 
  </div>
</template>

<script>

import { BUS_MENU } from "@/router/EventBus";

export default {
    name: "tree-node",
    // props: ['treeData', 'componentKey'],
    props: ['treeData'],
    computed: {
        isFolder: function() {
            return this.treeData.children && this.treeData.children.length;
            }
    },
    methods: {
        onNodeClick(item) {
            // console.log('onNodeClick id: ', item.id, ' name: ', item.name);
            BUS_MENU.$emit('clickTreeMenu', item);
        }
    }

}
</script>

<style>

</style>