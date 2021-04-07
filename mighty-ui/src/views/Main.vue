<template>
  <div>
    <v-app-bar tile color="transparent" height="40">
      <v-row align="stretch" justify="start">
        <SysMenu @selectSysItem="setSysItem"></SysMenu>
        <InquiryMenu @selectInquiryItem="setInquiryItem"></InquiryMenu>
      </v-row>
    </v-app-bar>

    <div class="d1">
      <v-tabs
        v-model="activeTab"
        background-color="transparent"
        color="primary"
        height="38"
      >
        <v-tab v-for="tab in tabs" :key="tab.id" exact @click="updateTab(tab)">
          {{ tab.title }}
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
import SysMenu from '@/components/menu/SysMenu';
import InquiryMenu from '@/components/menu/InquiryMenu';
// import users from '@/views/user/users';
// import Groups from '@/views/user/Groups';
// import Authority from '@/views/user/Authority';
// import Inquiry1 from '@/views/Inquiry/Inquiry1';
// import Inquiry2 from '@/views/Inquiry/Inquiry2';

  export default {
    components: {
      // menu
      SysMenu,
      InquiryMenu,
      // users,
      // Groups,
      // Authority,
      // Inquiry1,
      // Inquiry2,
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
      // menu
      setSysItem(item) {
        // console.log(item);
        // console.log('mTab: ', this.mTab);
        // console.log('go MainStore');
        // this.actTest();
        //this.activeItem = item;
        if (item.content === 'Logout') {
          this.$store.dispatch('actLogoutYN');
        } else {
          this.addTab(item);
        }
      },
      setInquiryItem(item) {
        //console.log(item);
        this.addTab(item);
      },

      // tab
      updateTab(tab) {
        //console.log('updateTab: ', tab);
        this.$router.push({path: tab.content}, function(){}, function(){});
        this.activeTab = this.tabs.findIndex(t => t.id === tab.id);
      },
      // activateTab: function(tab) {
      //   this.activeTab = tab;
      // },
      removeTab(tab){
        this.tabs = this.tabs.filter(t => t.id !== tab.id);
        //console.log('tab: ', tab);
        this.$router.go(-1);
        if (this.tabs.length === 0) {
          this.$router.content = "";
        }
        // console.log('activeTab: ', this.activeTab);
      },
      addTab(item){
        console.log('item: ', item);

        if (this.tabs.some(t => t.id === item.id)) {
          console.log('already exist: ', item.value)         
        } else {
          this.tabs.push({
                id: item.id,
                title: item.value,
                content: item.content
              });
          this.$router.push({path: item.content}, function(){}, function(){});
        }

        // this.$router.push({path: item.content}, function(){}, function(){});

        this.activeTab = this.tabs.findIndex(t => t.id === item.id);

        //console.log('activeTab: ', this.activeTab);
        //console.log('tabs: ', this.tabs);
      }
    },
    data() {
        return {
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
    },
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