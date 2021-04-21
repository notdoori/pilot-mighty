<template>
  <div>
    <v-app-bar tile color="transparent" height="40">
      <v-btn icon small>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
      
      <span v-for="(menu, index) in menuList" :key="index">
        <Menu :menuGroup="menu" :key="menuKey" @clickMenu="clickMenu"></Menu>
      </span>
    </v-app-bar>

    <div class="d1">
      <v-tabs
        v-model="activeTab"
        background-color="transparent"
        color="primary"
        height="38"
      >
        <v-tab v-for="(tab, index) in tabs" :key="index" exact @click="updateTab(tab)">
          {{ tab.name }}
          <v-icon small left @click="removeTab(tab)">mdi-minus-circle</v-icon>
        </v-tab>
      </v-tabs>

      <router-view></router-view>

      
      <!-- <v-tabs-items v-model="activeTab">
        <v-tab-item v-for="tab in tabs" :key="tab.id">
            <div>
              <keep-alive><Authority v-show="tab.id === 0"></Authority></keep-alive>
              <keep-alive><Groups v-show="tab.id === 1"></Groups></keep-alive>
              <keep-alive><users v-show="tab.id === 2"></users></keep-alive>  
              <keep-alive><Inquiry1 v-show="tab.id === 10"></Inquiry1></keep-alive>        
              <keep-alive><Inquiry2 v-show="tab.id === 11"></Inquiry2></keep-alive>                
            </div>
        </v-tab-item>      
      </v-tabs-items> -->

      <!-- <v-runtime-template :template="tabItemTemplate"></v-runtime-template> -->
    </div>
  </div>
</template>

<script>

import {mapState, mapActions} from 'vuex';
import VRuntimeTemplate from "v-runtime-template";
import Menu from '@/components/menu/Menu';
import { BUS_MENU } from "@/router/EventBus";

import axios from 'axios';

  export default {
    components: {
      // menu
      Menu,
      VRuntimeTemplate
    },
    computed: {
      ...mapState(['isLogIn']),
      //...mapState({mTab: state => state.MainStore.mTab})
      ...mapState('MainStore', {mTab: state => state.mTab})
    },
    methods: {
      ...mapActions(['actLogout', 'actLogoutYN']),
      ...mapActions('MainStore', ['actTest']),
      // tab
      updateTab(tab) {
        //console.log('updateTab: ', tab);
        //this.$router.push({path: tab.route}, function(){}, function(){});
        this.$router.replace({path: tab.route}, function(){}, function(){});
        this.activeTab = this.tabs.findIndex(t => t.id === tab.id);
        console.log('update activeTab: ', this.activeTab);
      },
      removeTab(tab) {

        let removeIdx = this.tabs.findIndex(t => t.id === tab.id);

        this.tabs = this.tabs.filter(t => t.id !== tab.id);

        if (this.tabs.length <= 0) {
          this.$router.replace({path: this.curRoutePath}, function(){}, function(){});
        } else {
          let maxIdx = this.tabs.length - 1;
          let tabIdx = removeIdx >= maxIdx ? maxIdx : removeIdx;
          console.log('maxIdx: ', maxIdx, ' removeIdx: ', removeIdx, ' tabIdx: ', tabIdx);
          this.activeTab = tabIdx;                 
          this.$router.replace({path: this.tabs[tabIdx].route}, function(){}, function(){});  
        }
      },
      clickMenu(item) {
        console.log('id: ', item.id, ' name: ', item.name);

        if (item.id === 'M0004') {
          this.$store.dispatch('actLogoutYN');
        } else {
          this.addMenuTab(item);
        }
      },
      addMenuTab(item) {
        //console.log('id: ', item.id, ' name: ', item.name);

        if (this.tabs.some(t => t.id === item.id)) {
          console.log('already exist id: ', item.id, ' name: ', item.name);         
        } else {
          let nextRoutePath=this.curRoutePath + '/' + item.id;

          this.tabs.push({
                id: item.id,
                name: item.name,
                route: nextRoutePath
              });

            

            //console.log('next route path: ', nextRoutePath);

            this.$router.replace({path: nextRoutePath}, function(){}, function(){});
        }

        this.activeTab = this.tabs.findIndex(t => t.id === item.id);
        console.log('new activeTab: ', this.activeTab);
      },
      forceRerender() {
          this.menuKey += 1;
      }
    },
    data() {
        return {
          curRoutePath: "/",
          activeTab: 0,
          tabs: [],
          // test template for <v-runtime-template templat>
          // tabItemTemplate: `<v-tabs-items v-model="tab">
          //                     <v-tab-item v-for="tab in tabs" :key="tab">
          //                       <p>{{tab.title}}</p>    
          //                       <div>
          //                         <v-runtime-template :template="tab.content"></v-runtime-template>
          //                       </div>
          //                     </v-tab-item>      
          //                   </v-tabs-items>`
          menuList: [],
          menuKey: 0
      }
    },
    // tab, broswer 닫는 경우 처리 => 새로 고침 버튼에도 영향을 줌.
    created() {
      window.addEventListener('beforeunload', (e) => {
        e.preventDefault();
        e.returnValue = '';        
      });
      window.addEventListener('unload', function(e) {
        this.$store.dispatch('actLogout');
      });

      BUS_MENU.$on('clickTreeMenu', item => {
        // console.log('clickTreeMenu id: ', item.id, ' name: ', item.name);

        if (item.id === 'M0004') {
          this.$store.dispatch('actLogoutYN');
        } else {
          this.addMenuTab(item);
          
          this.forceRerender();
        }
        
      })
    },
    mounted() {
      this.curRoutePath = this.$route.path;
      this.menuList = [
                       {id: "K0001", name: "SYSTEN", desc: "",
                        children: [{id: "M0001", name: "권한 관리", desc: "권한 관리 메뉴", children: []},
                                   {id: "M0002", name: "사용자 그룹 관리", desc: "사용자 그룹 관리 메뉴", children:[]},
                                   {id: "M0003", name: "사용자 관리", desc: "사용자 관리 메뉴", children:[]},
                                   {id: "M0004", name: "로그 아웃", desc: "로그 아웃 메뉴", children:[]}
                                  ]
                       },
                       {id: "K0002", name: "INQUIRY", desc: "", children: []
                       }
                      ];
      let userId = localStorage.getItem("user_id");
      let url = '/api/menu/all?userId=' + `${userId}`;
      axios.get(url)
        .then(response => {
            if (response.data) {
              this.menuList = response.data;
            }
        })
        .catch((error) => alert(error));
    }
    // beforeRouteLeave (to, from, next) {
    //   console.log('from: ', from);
    //   console.log('to: ', to);
    //   if (this.isLogIn === true) {
    //     next(false);
    //   } else {
    //     next();
    //   }
    // }
  }
</script>

<style scoped>
  .d1 {
      margin-left: 0.25em;  
  }
</style>

<!--
<style lang="scss">
@import "..\\assets\\css\\default.css";
</style>
-->
