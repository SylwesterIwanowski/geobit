<template>
  <div>
   <p>Home</p>
    <input type="submit" @click="submitLoginForm"/>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Home',
  data() {
    return {
      login: '',
      password: ''
    }
  },
  methods: {
    submitLoginForm: function () {
      const endpointWithTOken = axios.create({
        timeout: 1000,
        headers: {token: localStorage.getItem('token')}
      });

      endpointWithTOken.get('http://localhost:8080/rest/user/get')
        .then(function (response) {
          console.log(response)
        })
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
