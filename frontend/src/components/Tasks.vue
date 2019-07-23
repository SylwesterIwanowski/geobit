<template>
  <v-app light>
    <v-content>
      <v-container>
        <v-form>
          <v-container>
            <v-layout>

              <v-flex xs12>
                <v-btn color="primary">Zadania</v-btn>
                <v-btn color="primary">Pracownicy</v-btn>
                <v-btn color="primary">Urzadzenia</v-btn>


                <!--v-btn color="primary" @click="submitLoginForm" >Zaloguj</v-btn-->
              </v-flex>
            </v-layout>
          </v-container>
        </v-form>
      </v-container>
    </v-content>

    <!--template-->
      <v-container fluid>
        <v-layout row>
          <v-flex pa-2 xs6>
            <v-card color="primary">
              <v-card-text>ZADANIA DO WYKONANIA</v-card-text>
              <v-expansion-panel>
                <v-expansion-panel-content
                  v-for="task in tasks"
                  :key="i">
                  <template v-slot:header>
                    <div>{{task.title}}</div>
                  </template>
                  <v-card>
                    <v-card-text>{{task.description}}</v-card-text>

                    <v-btn color="primary" align="right">Edytuj zadanie</v-btn>
                  </v-card>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-card>
          </v-flex>
          <v-flex pa-2 xs6>
            <v-card color="primary">
              <v-card-text>EDYTOWANIE ZADAN</v-card-text>

              <v-card>
                <div>
                  <v-card-text>
                    <v-text-field v-model="title" label="Temat:" required></v-text-field>
                    <v-tex-field v-model="startDate" label="Data rozpoczecia zadania:" required></v-tex-field>
                    <v-tex-field v-model="endDate" label="Data zakonczenia zadania:" required></v-tex-field>
                    <v-text-field v-model="kerg" label="Nr KERG:" required></v-text-field>
                    <v-textarea v-model="description" label="Opis zadania:" required></v-textarea>
                    <v-text-field v-model="customer" label="Zleceniodawca:" required></v-text-field>
                    <v-text-field v-model="employee" label="Pracownicy odpowiedzialni za zadanie:" required></v-text-field>
                    <v-btn color="primary" @click="getEmployees">Praownicy</v-btn>
                    <v-btn color="primary" @click="submitAddTask">Akceptuj</v-btn>
                  </v-card-text>
                </div>
              </v-card>

            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    <!--/template-->
  </v-app>
</template>


<script>
  import axios from 'axios'
  import Router from '../router'

  export default {
    name: 'Tasks',
    data() {
      return {
        title: '',
        startDate: '',
        endDate: '',
        kerg: '',
        description: '',
        customer: '',
        employee: '',
        tasks: [],

      }
    },
    methods: {
      getEmployees: function () {
        console.log(this.title);
        axios.get('http://localhost:8080/rest/task/get')
          .then(function (response) {
          this.tasks = response.data
            Router.push({name: 'Tasks'})
        })
      }
    },
    submitAddTask: function () {

      let newTask = {};
      newTask.title = this.title;
      newTask.startDate = this.startDate;
      newTask.endDate = this.endDate;
      newTask.kerg = this.kerg;
      newTask.description = this.description;
      newTask.customer = this.customer;
      newTask.addEmployee = this.employee;

      axios.post('http://localhost:8080/rest/task/add', newTask)
        .then(function (response) {
          console.log(response);
          Router.push({name: 'Tasks'})
        })
    }
  }

</script>

<style scoped>

</style>
