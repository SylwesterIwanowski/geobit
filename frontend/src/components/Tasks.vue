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


                <!--v-btn color="primary" @click="submitLoginForm" >Zaloguj</v-btn-->
              </v-flex>
            </v-layout>
          </v-container>
        </v-form>
      </v-container>
    </v-content>

    <template>
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
                    <v-textarea v-model="title" label="Temat:" required></v-textarea>
                    <v-textarea v-model="textEditor" label="Opis zadania:"></v-textarea>


                    <v-btn  color="primary">Akceptuj</v-btn>
                  </v-card-text>
                </div>
              </v-card>

            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </template>
  </v-app>
</template>


<script>
  import axios from 'axios'


  export default {
    name: "Tasks",
    data() {
      return {
        textEditor: '',
        title: '',
        tasks: [],

      }
    },
    methods: {
      getEmployees: function () {
        axios.get('http://localhost:8080/rest/user/task/get').then(function (response) {
          this.tasks = response.data
        })
      }
    }
  }

</script>

<style scoped>

</style>
