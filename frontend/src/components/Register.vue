<template>
  <div>
    <h1>Login Page</h1>
    <p> Email <input v-model="login"></p>
    <p> Haslo <input v-model="password"></p>
    <p> Imie <input v-model="firstName"></p>
    <p> Nazwisko <input v-model="lastName"></p>
    <p> Tel. <input v-model="phoneNumber"></p>
    <input @click.stop.prevent="submitLoginForm" type="submit" value="Rejestracja">
  </div>
</template>

<script>
import axios from 'axios'
import Router from '../router'

export default {
  name: 'Register',
  data () {
    return {
      login: '',
      password: '',
      phoneNumber: '',
      lastName: '',
      firstName: ''
    }
  },
  methods: {
    submitLoginForm: function () {
      console.log(this.login);
      console.log(this.password);

      let newUser = {};
      newUser.email = this.login;
      newUser.password = this.password;
      newUser.phoneNumber = this.phoneNumber;
      newUser.lastName = this.lastName;
      newUser.firstName = this.firstName;

      axios.post('http://localhost:8080/rest/user/add', newUser)
        .then(function (response) {
          console.log(response);
          Router.push({name: 'Hello'})
        })
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2, h3 {
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
