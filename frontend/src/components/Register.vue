<template>
  <v-app light>
    <v-content>
      <v-container>
        <v-form>
          <v-container>
            <v-layout row wrap>
              <h1>REJESTRACJA</h1>
              <v-flex xs12>
               <v-text-field v-model="login" label="Email" required></v-text-field>
              <v-text-field v-model="password" label="Hasło" required></v-text-field>
              <v-text-field v-model="firstName" label="Imie" required></v-text-field>
              <v-text-field v-model="lastName" label="Nazwisko" required></v-text-field>
              <v-text-field v-model="phoneNumber" label="Telefon" required></v-text-field>
              <v-btn color="primary" @click="submitLoginForm" >Zarejestruj</v-btn>
              </v-flex>
            </v-layout>
          </v-container>
        </v-form>
      </v-container>
    </v-content>
  </v-app>



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
          Router.push({name: 'Tasks'})
        })
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2, h3 {
    font-weight: normal;
    text-align: center;
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
