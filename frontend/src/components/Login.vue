<template>
  <div>
    <h1>Login Page</h1>
    <div>
      Email <input v-model="login"/>
    </div>
    <div>
      Haslo <input v-model="password"/>
    </div>
    <input @click="submitLoginForm" type="submit"/>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      login: '',
      password: ''
    }
  },
  methods: {
    submitLoginForm: function () {
      console.log(this.login)
      console.log(this.password)
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
