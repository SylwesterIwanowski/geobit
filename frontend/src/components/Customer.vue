<template>
  <v-app light>
    <v-content>
      <v-container>
        <v-form>
          <v-container>
            <v-layout>
              <v-flex xs12>
                <v-btn color="primary" @click="newCustomer">Nowy zleceniodawca</v-btn>
                <v-btn color="primary" @click="getAllCustomer">Zleceniodawcy</v-btn>
                <v-btn color="primary">Pracownicy</v-btn>
                <v-btn color="primary">Urzadzenia</v-btn>
                <v-btn color="primary">Zadania</v-btn>
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
            <v-card-text>LISTA ZLECENIODAWCOW</v-card-text>
          </v-card>

          <table class="table">
            <tr>
              <th>Nazwa</th>
              <th>Telefon</th>
              <th>Pełna nazwa</th>
              <th>NIP</th>
            </tr>
            <tr v-for="customer in customers">
              <td class="clickable" @click="getEditCustomer(customer)">{{ customer.name }}</td>
              <td class="clickable" @click="getEditCustomer(customer)">{{ customer.phoneNumber }}</td>
              <td class="clickable" @click="getEditCustomer(customer)">{{customer.fullName}}</td>
              <td class="clickable" @click="getEditCustomer(customer)">{{customer.description}}</td>
              <td class="clickable" @click="getEditCustomer(customer)">{{ customer.nip }}</td>
            </tr>
          </table>
        </v-flex>
        <v-flex pa-2 xs5>
          <v-card color="primary">
            <v-card-text>SZCZEGOLY I EDYCJA ZLECENIODAWCY</v-card-text>
          </v-card>
          <v-card>
            <v-card-text>
              <v-text-field v-model="selectedCustomer.name" label="Nazwa:" required></v-text-field>
              <v-text-field v-model="selectedCustomer.phoneNumber" label="Telefon:" required></v-text-field>
              <v-text-field v-model="selectedCustomer.fullName" label="Pełna nazwa:" required></v-text-field>
              <v-text-field v-model="selectedCustomer.description" label="Pełna nazwa:" required></v-text-field>
              <v-text-field v-model="selectedCustomer.nip" label="Nr NIP:" required></v-text-field>
            </v-card-text>
          </v-card>
          <v-btn v-if="selectedCustomer.id" color="primary" @click="submitAddCustomer">Aktualizuj</v-btn>
          <v-btn v-if="!selectedCustomer.id" color="primary" @click="submitAddCustomer">Dodaj nowego zleceniodawce</v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>
</template>

<script>

  import axios from 'axios'

    export default {
        name: "Customer",

      data() {
        return {
          name: '',
          fullName: '',
          customer: '',
          nip: '',
          description: '',
          selectedCustomer: {},
          customers: [],
        }
      },
      methods: {
        getAllCustomer: function () {
          let vm = this;
          axios.get('http://localhost:8080/rest/customer/get')
            .then(function (response) {
              console.log(response);
              vm.customers = response.data;
              console.log('customerzy');
              console.log(response.data);
            })
        },
         submitAddCustomer: function () {
           let vm = this;
           axios.post('http://localhost:8080/rest/customer/add', this.selectedCustomer)
             .then(function (response) {
               console.log(response);
               console.log('selectedTask');
               vm.getAllCustomer();
             });
          // console.log(this.selectedCustomer);
         },

        getEditCustomer: function (customer) {
          console.log('selectedCustomer');
          console.log(customer);
          this.selectedCustomer = customer;
        },
        newCustomer: function () {
          this.selectedCustomer = {};
        },
      created() {
        this.getAllCustomer();
        //this.getAllEmployees();
      },
    }
    }
</script>

<style scoped>

</style>
