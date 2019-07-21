<template>
  <v-app light>
    <v-content>
      <v-container>
        <v-form>
          <v-container>
            <v-layout>

              <v-flex
                xs12
              >
                <v-btn color="primary" >Zadania</v-btn>
                <v-btn color="primary" >Pracownicy</v-btn>


                <!--v-btn color="primary" @click="submitLoginForm" >Zaloguj</v-btn-->
              </v-flex>
            </v-layout>
          </v-container>
        </v-form>
      </v-container>
    </v-content>
  </v-app>

</template>

<script>
    export default {
        name: "Employees"
    }
</script>

<style scoped>

</style>
