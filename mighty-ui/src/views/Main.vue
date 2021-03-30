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
        <v-tab v-for="tab in tabs" :key="tab.id">
          {{ tab.title }}
          <v-icon small left @click="removeTab(tab)">mdi-minus-circle</v-icon>
        </v-tab>
      </v-tabs>

      <!-- <v-runtime-template :template="tabItemTemplate"></v-runtime-template> -->
      <v-tabs-items v-model="activeTab">
        <v-tab-item v-for="tab in tabs" :key="tab.id">
          <!-- <p>{{tab.title}}</p>     -->
            <div>
              <keep-alive><Authority v-show="tab.id === 0"></Authority></keep-alive>
              <keep-alive><Groups v-show="tab.id === 1"></Groups></keep-alive>
              <keep-alive><users v-show="tab.id === 2"></users></keep-alive>  
              <keep-alive><Inquiry1 v-show="tab.id === 10"></Inquiry1></keep-alive>        
              <keep-alive><Inquiry2 v-show="tab.id === 11"></Inquiry2></keep-alive>                
            </div>
        </v-tab-item>      
      </v-tabs-items>
    </div>
  </div>
</template>

<script>

import {mapState, mapActions} from 'vuex';
import VRuntimeTemplate from "v-runtime-template";
import SysMenu from '@/components/menu/SysMenu';
import InquiryMenu from '@/components/menu/InquiryMenu';
import users from '@/views/user/users';
import Groups from '@/views/user/Groups';
import Authority from '@/views/user/Authority';
import Inquiry1 from '@/views/Inquiry/Inquiry1';
import Inquiry2 from '@/views/Inquiry/Inquiry2';

  export default {
    components: {
      // menu
      SysMenu,
      InquiryMenu,
      users,
      Groups,
      Authority,
      Inquiry1,
      Inquiry2,
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
      activateTab: function(tab) {
        this.activeTab = tab;
      },
      removeTab(tab){
        this.tabs = this.tabs.filter(t => t.id !== tab.id);
        console.log('tabs: ', this.tabs);
        console.log('activeTab: ', this.activeTab);
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
        }

        this.activeTab = this.tabs.findIndex(t => t.id === item.id);

        console.log('activeTab: ', this.activeTab);
        console.log('tabs: ', this.tabs);
      }
    },
    data() {
        return {
          activeTab: 0,
          //tab: 0,
          tabs: [],
          // test template
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
    created() {
      this.activeTab = 0;
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