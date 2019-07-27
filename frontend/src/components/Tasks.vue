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
                <v-btn color="primary">Zleceniodawcy</v-btn>
              </v-flex>
            </v-layout>
          </v-container>
        </v-form>
      </v-container>
    </v-content>


    <v-container fluid>
      <v-layout row>
        <v-flex pa-2 xs7>
          <v-card color="primary">
            <v-card-text>ZADANIA DO WYKONANIA</v-card-text>
          </v-card>

          <table class="table">
            <tr>
              <th>Tytuł</th>
              <th>Opis zadania</th>
              <th>Pracownicy</th>
              <th>Data zakonczenia zadania</th>
            </tr>
            <tr v-for="task in tasks">
              <td class="clickable" @click="getEditTask(task)">{{ task.title }}</td>
              <td class="clickable" @click="getEditTask(task)">{{ task.description }}</td>
              <td class="clickable" @click="getEditTask(task)">
                <div v-for="employee in task.employees">{{employee.firstName}} {{employee.lastName}}</div></td>
              <td class="clickable" @click="getEditTask(task)">{{ task.endDate }}</td>
            </tr>
          </table>
        </v-flex>
        <v-flex pa-2 xs5>
          <v-card color="primary">
            <v-card-text>SZCZEGOLY I EDYCJA ZADANIA</v-card-text>
          </v-card>
          <v-card>
            <v-card-text>
              <v-text-field v-model="selectedTask.title" label="Temat:" required></v-text-field>
              <v-text-field v-model="selectedTask.startDate" label="Data rozpoczecia zadania:" required></v-text-field>
              <v-text-field v-model="selectedTask.endDate" label="Data zakonczenia zadania:" required></v-text-field>
              <v-text-field v-model="selectedTask.kerg" label="Nr KERG:" required></v-text-field>
              <v-textarea v-model="selectedTask.description" label="Opis zadania:" required></v-textarea>
              <v-select
                v-model="selectedEmployees"
                :items="employee"
                :menu-props="{ maxHeight: '400' }"
                label="Wybierz pracownikow"
                multiple>
              </v-select>
              <hr>
              <v-select
                v-model="selectedCustomers"
                :items="customer"
                :menu-props="{ maxHeight: '400' }"
                label="Wybierz zleceniodawce"
                persistent-hint>
              </v-select>
            </v-card-text>
          </v-card>
          <v-btn v-if="selectedTask.id" color="primary" @click="submitAddTask">Aktualizuj</v-btn>
          <v-btn v-if="!selectedTask.id" color="primary" @click="submitAddTask">Dodaj nowe zadanie</v-btn>
        </v-flex>
      </v-layout>
    </v-container>
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
        customers: [],
        selectedEmployees: [],
        allEmployees: [],
        employees: [],
        employee: [],
        customer: [
          'Mostostal', 'Stal Export', 'International', 'Exporting', 'Mafixing'
        ],
        tasks: [],
        selectedCustomers: '',
        selectedTask: {},


      }
    },
    methods: {
      getAllTasks: function () {
        let vm = this;
        axios.get('http://localhost:8080/rest/task/get')
          .then(function (response) {
            console.log(response);
            vm.tasks = response.data;
            console.log('taski');
            console.log(response.data);
          })
      },
      submitAddTask: function () {
        let vm = this;
        this.selectedTask.employees = this.convertNamesToOjects();
        axios.post('http://localhost:8080/rest/task/add', this.selectedTask)
          .then(function (response) {
            console.log(response);
            console.log('selectedTask');
            vm.getAllTasks();
          });
        console.log(this.selectedEmployees);
      },
      getEditTask: function (task) {
        console.log('selectedTask');
        console.log(task);
        this.selectedTask = task;
      },
      newTask: function () {
        this.selectedTask = {};
      },
      getAllEmployees: function () {
        let vm = this;
        axios.get('http://localhost:8080/rest/user/get')
          .then(function (response) {
            console.log(response);
            vm.allEmployees = response.data;
            console.log('pracownicy');
            console.log(response.data);
            vm.createEmployeeLabels();
          })
      },
      createEmployeeLabels: function () {
        var i;
        for (i = 0; i < this.allEmployees.length; i++) {
          console.log(i);
          console.log(this.allEmployees[i]);
          console.log(this.allEmployees[i].firstName + " " + this.allEmployees[i].lastName);
          this.employee.push(this.allEmployees[i].firstName + " " + this.allEmployees[i].lastName)
        }
      },
      findEmployeeObject: function (fullName) {
        var fullNameSplited = fullName.split(" ");
        var firstName;
        var lastName;
        firstName = fullNameSplited[0];
        lastName = fullNameSplited[1];
        var i;
        for (i = 0; this.allEmployees.length; i++) {
          var currentEmployee = this.allEmployees[i];
          if(currentEmployee.firstName == firstName && currentEmployee.lastName == lastName){
            return currentEmployee;
          }
        }
        return null;
      },
      convertNamesToOjects: function(){
        var result = [];
        var i;
        for(i=0; i < this.selectedEmployees.length; i++){
          var selectedEmployeeObject = this.findEmployeeObject(this.selectedEmployees[i])
          result.push(selectedEmployeeObject);
        }
        return result;
      }
    },
    created() {
      this.getAllTasks();
      this.getAllEmployees();
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
