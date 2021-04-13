<template>
  <v-menu :close-on-content-click="false" bottom offset-y v-model="showMenu" >
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

      <v-list dense subheader v-for="(node, index) in menuGroup.children" :key="index">
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
      </v-list> 

  </v-menu>  
</template>

<script>

export default {
    components: {
    },
    props: {
        menuGroup: Object
    },
    methods: {
        hasChild(item) {
          if (item.children !== null) {
              return item.children.length;
          } else {
              return 0;
          }
        },
        onClick() {
          this.forceRerender();
        },
        onMenuClick(item) {
          this.showMenu = false;

          this.$emit('clickMenu', {id: item.id, name: item.name});
        },
        forceRerender() {
          this.componentKey += 1;
        },
    },
    data() {
        return {
            showMenu: false,
            componentKey: 0,
        }
    }
}
</script>

<style scoped="">
</style>