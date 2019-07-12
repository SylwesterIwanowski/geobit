import Vue from 'vue'
import Router from 'vue-router'
import Register from "../components/Register";
import Login from "../components/Login";
import Tasks from "../components/Tasks";
import Employees from "../components/Employees";

Vue.use(Router);

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
    }
  ]
})
