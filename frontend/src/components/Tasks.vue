<template>
  <div>
    <ul>
      <li v-for="comment in tasks">

        <p>{{comment.task}}</p>
        <p>{{comment.consumer}}</p>
        <p>{{comment.employee}}</p>

      </li>
    </ul>
  </div>

</template>

<script>
  export default {
    name: "Tasks",

    data() {
      return {
        tasks: [
          {
            task: 'Zadanie 1',
            consumer: 'Mostostal sp.z o.o.',
            employee: 'Jaca i Robi'
          },
          {
            task: 'Zadanie 2',
            consumer: 'Pruchnik sp.z o.o.',
            employee: 'Gustaw i Robi'
          },
          {
            task: 'Zadanie 3',
            consumer: 'Gielda sp.z o.o.',
            employee: 'Jaca i Mariola'
          },
          {
            task: 'Zadanie 4',
            consumer: 'Orange sp.z o.o.',
            employee: 'Jaca i Karaol'
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
