<template>
  <div>
    <v-app-bar flat tile color="transparent" extension-height="40">

      <v-row align="stretch" justify="start">
        <SysMenu @selectSysItem="setSysItem"></SysMenu>
        <InquiryMenu @selectInquiryItem="setInquiryItem"></InquiryMenu>   
      </v-row>

      <template v-slot:extension>
        <v-tabs
          v-model="tab"
          background-color="transparent"
          color="primary"
        >
          <v-tab v-for="tab in tabs" :key="tab.id"
          >
            <!-- <div> -->
              {{ tab.title }}
              <v-icon small left @click="removeTab(tab)">mdi-minus-circle</v-icon>
            <!-- </div> -->
          </v-tab>
        </v-tabs>
      </template>

    </v-app-bar>

    <!-- <v-runtime-template :template="tabItemTemplate"></v-runtime-template> -->
    <!-- <v-card-text> -->
    <v-tabs-items v-model="tab">
      <v-tab-item v-for="tab in tabs" :key="tab.id">
        <!-- <p>{{tab.title}}</p>     -->
          <div>
            <keep-alive><Groups v-show="tab.id === 0" post-title="hi!"></Groups></keep-alive>
            <keep-alive><users v-show="tab.id === 1" post-title="hello!"></users></keep-alive>                     
          </div>
      </v-tab-item>      
    </v-tabs-items>
    <!-- </v-card-text> -->
  </div>
</template>

<script>

import {mapState, mapActions} from 'vuex';
import VRuntimeTemplate from "v-runtime-template";
import SysMenu from '@/components/SysMenu';
import InquiryMenu from '@/components/InquiryMenu';
import users from '@/views/users';
import Groups from '@/views/Groups';

  export default {
    components: {
      // menu
      SysMenu,
      InquiryMenu,
      users,
      Groups,
      VRuntimeTemplate
    },
    computed: {
      ...mapState(['isLogIn'])
    },
    methods: {
      ...mapActions(['actLogout', 'actLogoutYN']),
      // menu
      setSysItem(item) {
        console.log(item);
        //this.activeItem = item;
        if (item.content === 'Logout') {
          this.$store.dispatch('actLogoutYN');
        } else {
          this.addTab(item);
        }
      },
      setInquiryItem(item) {
        console.log(item);
      },

      // tab
      activateTab: function(tab) {
        this.activeTab = tab;
      },
      removeTab(tab){
        this.tabs = this.tabs.filter(t => t.id !== tab.id);
      },
      addTab(item){
        console.log('item: ', item);

        if (this.tabs.some(t => t.id === item.id)) {
          console.log('already exist: ', item.value)       
          //this.activateTab(this.tabs.find(t => t.id === item.id));    
        } else {
          this.tabs.push({
                id: item.id,
                title: item.value,
                content: item.content
              }); 
          //this.activateTab(this.tabs[this.tabs.length - 1]);         
        }

        this.tab = item.id;

        console.log('tabs: ', this.tabs);
        console.log('tab: ', this.tab);
      }
    },
    data() {
        return {
          activeTab: null,
          // tab: null,
          tab: 0,
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
      this.tab = 0;
    },
    // tab, broswer 닫는 경우 처리 => 새로 고침 버튼에도 영향을 줌.
    // created() {
    //   window.addEventListener('beforeunload', (e) => {
    //     e.preventDefault();
    //     e.returnValue = '';
    //     this.$store.dispatch('actLogout');
    //   });
    // }
    beforeRouteLeave (to, from, next) {
      if (this.isLogIn === true) {
          next(false);
      } else {
        next();
      }
    }
  }
</script>

<style scoped>

</style>