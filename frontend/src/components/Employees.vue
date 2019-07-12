<template>
  <div>
    <ul>
      <li v-for="comment in employees">
        <p>{{comment.firstName}} {{comment.lastName}}</p>
        <p>telefon: {{comment.phoneNumber}}, email: {{comment.email}}</p>
        <p>{{comment.employeeTasks}}</p>

      </li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: "Employees",

    data() {
      return {
        employees: [
          {
            firstName: 'Jaca',
            lastName: 'Jackowa',
            phoneNumber: '5645456554665',
            email: 'dfgfgd@dddd',
            employeeTasks: 'Zadania pracownika'
          },
          {
            firstName: 'Toma',
            lastName: 'Kowalski',
            phoneNumber: '5645456554668',
            email: 'sds@ghhfghf',
            employeeTasks: 'Zadania pracownika'
          },
          {
            firstName: 'Jesyka',
            lastName: 'Kowalska',
            phoneNumber: '45664666',
            email: 'dddd@ghhjhfgf',
            employeeTasks: 'Zadania pracownika'
          },
          {
            firstName: 'Tumek',
            lastName: 'Jagodzinski',
            phoneNumber: '463666466',
            email: 'gfgfgff@ghhfghf',
            employeeTasks: 'Zadania pracownika'
          },

        ],
      }
    }
  }
</script>

<style scoped>
p {
  text-align: left;
}

li {
  background: lavender;
  list-style: none;
  margin-bottom: 20px;
  padding: 10px;
}

p:first-child {
  font-size: 20px;
}
</style>
