<template>
  <v-app light>
    <v-content>
      <v-container>
        <v-form>
          <v-container>
            <v-layout>

              <v-flex xs12>
                <v-btn color="primary" @click="newMachine">Nowe urzadzenie</v-btn>
                <v-btn color="primary" @click="getAllMachines">Urzadzenia</v-btn>
                <v-btn color="primary">Pracownicy</v-btn>
                <v-btn color="primary">Zadania</v-btn>
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
          <v-card-text>EWIDENCJA URZADZEN</v-card-text>
        </v-card>

        <table class="table">
          <tr>
            <th>NAZWA</th>
            <th>NAZWA W SIECI</th>
            <th>OPIS</th>
            <th>WESRJA SYSTEMU</th>
            <th>PRACOWNICY</th>
          </tr>
          <tr v-for="machine in machines">
            <td class="clickable" @click="getEditMachine(machine)">{{ machine.nameMachine }}</td>
            <td class="clickable" @click="getEditMachine(machine)">{{ machine.nameNetwork }}</td>
            <td class="clickable" @click="getEditMachine(machine)">{{ machine.description }}</td>
            <td class="clickable" @click="getEditMachine(machine)">{{ machine.systemVersion }}</td>
            <td class="clickable" @click="getEditMachine(machine)">
              <div v-for="employee in machine.employeesMachine">{{employee.firstName}} {{employee.lastName}}</div></td>
           </tr>
        </table>
      </v-flex>

      <v-flex pa-2 xs5>
        <v-card color="primary">
          <v-card-text>SZCZEGOLY I EDYCJA URZADZENIA</v-card-text>
        </v-card>

        <v-card>
          <v-card-text>
            <v-text-field v-model="selectedMachine.nameMachine" label="Nazwa:" required></v-text-field>
            <v-text-field v-model="selectedMachine.nameNetwork" label="Nazwa w sieci:" required></v-text-field>
            <v-text-field v-model="selectedMachine.description" label="Opis:" required></v-text-field>
            <v-text-field v-model="selectedMachine.systemVersion" label="Wersja systemu:" required></v-text-field>

            <v-select
              v-model="selectedEmployees"
              :items="employee"
              :menu-props="{ maxHeight: '400' }"
              label="Wybierz pracownikow"
              multiple>
            </v-select>

          </v-card-text>
        </v-card>
        <v-btn v-if="selectedMachine.id" color="primary" @click="submitAddMachine">Aktualizuj</v-btn>
        <v-btn v-if="!selectedMachine.id" color="primary" @click="submitAddMachine">Dodaj nowe urzadzenie</v-btn>

      </v-flex>
    </v-layout>
  </v-container>
  </v-app>
</template>

<script>

  import axios from 'axios'

  export default {
    name: "Machines",

    data() {
      return {
        nameMachine: '',
        nameNetwork: '',
        description: '',
        systemVersion: '',
        selectedEmployees: [],
        allEmployees: [],
        employees: [],
        employee: [],
        machines: [],
        selectedMachine: {},
        employeesMachine: []

      }
    },
    methods: {
      getAllMachines: function () {
        let vm = this;
        axios.get('http://localhost:8080/rest/machine/get')
          .then(function (response) {
            console.log(response);
            vm.machines = response.data;
            console.log('machines');
            console.log(response.data);
          })
      },
      submitAddMachine: function () {
        let vm = this;
        this.selectedMachine.employeesMachine = this.convertNamesToOjects();
        axios.post('http://localhost:8080/rest/machine/add', this.selectedMachine)
          .then(function (response) {
            console.log(response);
            console.log('selectedMachine');
            vm.getAllMachines();
          });
        console.log(this.selectedEmployees);


      },
      getEditMachine: function (machine) {
        console.log('selectedMachine');
        console.log(machine);
        this.selectedMachine = machine;
      },
      newMachine: function () {
        this.selectedMachine = {};
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
      this.getAllMachines();
      this.getAllEmployees();

    }
    ,
  }


</script>

<style scoped>

</style>
