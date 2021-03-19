<template>
  <v-app>
    <v-main>
      <Login v-show="isLogIn === false"></Login>   
      <router-view v-show="isLogIn === true"></router-view>
    </v-main>
  </v-app>
</template>

<script>

import {mapState, mapActions} from 'vuex';
import Login from '@/views/Login';

export default {
  computed: {
    ...mapState(['isLogIn'])
  },
  methods: {
    ...mapActions(['actLogout'])
  },
  components: {
    Login
  }
};

//window.addEventListener('beforeunload', function (e) {
  // Cancel the event
//  e.preventDefault(); 
  // If you prevent default behavior in Mozilla Firefox prompt will always be shown Chrome requires returnValue to be set
//  e.returnValue = '';
//});


</script>
