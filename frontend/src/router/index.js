import Router from 'vue-router'
import Register from "../components/Register";
import Login from "../components/Login";
import Employees from "../components/Employees";
import Tasks from "../components/Tasks";
import Machines from "../components/Machines";


export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/tasks',
      name: 'Tasks',
      component: Tasks
    },
    {
      path: '/employees',
      name: 'Employees',
      component: Employees
    },
    {
      path: '/machines',
      name: 'Machines',
      component: Machines
    },
  ]
})
