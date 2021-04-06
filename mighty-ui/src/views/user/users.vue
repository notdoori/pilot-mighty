<template>
    <div>
      <dviv -bind:key="userId" v-for="user in users">
        <span>{{ user.userId }}</span>
        <span>{{ user.userName }}</span>
        <span>{{ user.depart }}</span>
        <span>{{ user.langType }}</span>
      </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      users: []
    }
  },
  created() {
    axios.get('/api/users/all')
      .then(response => this.users = response.data)
      .catch((error) => console.log(error));
  },
  methods: {

  }
}
</script>

<style>

</style>