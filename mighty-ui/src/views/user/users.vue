<template>
    <div>
      <div v-bind:key="userId" v-for="user in gridData">
        <span>{{ user.userId }}</span>
        <span>||</span>
        <span>{{ user.userName }}</span>
        <span>||</span>
        <span>{{ user.depart }}</span>
        <span>||</span>
        <span>{{ user.langType }}</span>
      </div>
      <form id="search">
      Search <input id="queryinput" name="query" v-model="searchQuery">
      </form>
      <userGrid :data="gridData" :columns="gridColumns" :filter-key="searchQuery"></userGrid>
    </div>
</template>

<script>
import axios from 'axios';
import userGrid from '@/views/user/Grid';

export default {
  name:'User',
  components: {
    userGrid
  },
  data() {
    return {
      searchQuery: '',
      gridColumn: ['userId', 'userName', 'depart', 'langType'],
      gridData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      axios.get('/api/users/all')
      .then(res => { this.gridData = res.data;
      }).catch(e => {
        console.error(e);
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped="">
  #queryinput {width:310px; margin-left: 10px}
</style>