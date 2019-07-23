<template>
  <v-app light>
    <v-content>
      <v-container>
        <v-form>
          <v-container>
            <v-layout>

              <v-flex xs12>
                <v-btn color="primary" @click="newTask">Nowe zadanie</v-btn>
                <v-btn color="primary" @click="getAllTasks">Zadania</v-btn>
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
          <v-card >
            <v-card-text>ZADANIA DO WYKONANIA</v-card-text>
              <table class="table">
                <tr>
                  <th>Tytuł</th>
                </tr>
                <tr v-for="task in tasks">
                  <td class="clickable" @click="getEditTask(task)">{{ task.title }}</td>
                </tr>
              </table>

          </v-card>
        </v-flex>
        <v-flex pa-2 xs6>
          <v-card color="primary">
            <v-card-text>EDYTOWANIE ZADAN</v-card-text>

            <v-card>
              <v-app light>
                <div>
                  <v-card-text>
                    <v-text-field v-model="selectedTask.title" label="Temat:" required></v-text-field>
                    <v-text-field v-model="selectedTask.startDate" label="Data rozpoczecia zadania:" required></v-text-field>
                    <v-text-field v-model="selectedTask.endDate" label="Data zakonczenia zadania:" required></v-text-field>
                    <v-text-field v-model="selectedTask.kerg" label="Nr KERG:" required></v-text-field>
                    <v-textarea v-model="selectedTask.description" label="Opis zadania:" required></v-textarea>
                    <!--v-text-field v-model="customer" label="Zleceniodawca:" required></v-text-field-->
                    <!--v-text-field v-model="employee" label="Pracownicy odpowiedzialni za zadanie:" required></v-text-field-->
                    <!--v-btn color="primary" @click="getEmployees">Praownicy</v-btn-->
                    <v-btn v-if="selectedTask.id" color="primary" @click="submitAddTask">Aktualizuj</v-btn>
                    <v-btn v-if="!selectedTask.id" color="primary" @click="submitAddTask">Dodaj nowe zadanie</v-btn>
                  </v-card-text>
                </div>
              </v-app>
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


  export default {

    name: 'Tasks',
    data() {
      return {
        title: '',
        startDate: '',
        endDate: '',
        kerg: '',
        description: '',
        //customer: '',
        //employee: '',
        tasks: [],
        selectedTask: {}
      }
    },
    methods: {
      getAllTasks: function () {
        let vm = this;
        axios.get('http://localhost:8080/rest/task/get')
          .then(function (response) {
            console.log(response);
            console.log('dupa1');
            vm.tasks = response.data;
            console.log('dupa');
            console.log(response.data);


          })

      },
      submitAddTask: function () {
        let vm = this;

        axios.post('http://localhost:8080/rest/task/add', this.selectedTask)
          .then(function (response) {
            console.log(response);
            console.log('dupa');
            vm.getAllTasks();
          });

      },
      getEditTask: function(task) {
        console.log('xd');
        console.log(task);
        this.selectedTask = task;
      },
      newTask: function() {
        this.selectedTask = {};
      }
    },
    created() {
      this.getAllTasks();
    },
  }

</script>

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

  .clickable {
    cursor: pointer;
  }

</style>
