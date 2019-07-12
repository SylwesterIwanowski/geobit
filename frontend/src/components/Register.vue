<template>
  <div>
    <h1>Login Page</h1>
    <p> Email <input v-model="login"></p>
    <p> Haslo <input v-model="password"></p>
    <input @click.stop.prevent="submitLoginForm" type="submit" value="Rejestracja">
  </div>
</template>

<script>
  import axios from "axios";
  //import router from '../router'
  //import {VueConstructor as Vue} from "vue/types/vue";

 // Vue.use(router);

  export default {
    name: 'Register',
    data() {
      return {
        login: '',
        password: ''
      }
    },
    methods: {
      submitLoginForm: function () {
        console.log(this.login);
        console.log(this.password);

        let newUser = {};
        newUser.email = this.login;
        newUser.password = this.password;


        axios.post('http://localhost:8080/rest/user/add', newUser)
          .then(function (response) {
            console.log(response);
            //router.push({ name: 'Login'});
            //window.history.back();
          });
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
