<template>
  <div class="main">
    <v-btn color="primary" @click="actLogout">Logout</v-btn>
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex';

  export default {
    computed: {
      ...mapState(['isLogIn'])
    },
    methods: {
      ...mapActions(['actLogout'])
    },
    data: () => ({
    }),
    // tab, broswer 닫는 경우 처리 => 새로 고침 버튼에도 영향을 줌.
    // created() {
    //   window.addEventListener('beforeunload', (e) => {
    //     e.preventDefault();
    //     e.returnValue = '';
    //     this.$store.dispatch('actLogout');
    //   });
    // }
    beforeRouteLeave (to, from, next) {

      console.log('from: ', from);
      console.log('to: ', to);
      console.log('isLogIn: ', this.isLogIn);

      if (this.isLogIn === true) {
        next(false);
      } else {
        next();
      }

    }
  }
</script>
