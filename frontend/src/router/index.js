import Vue from 'vue'
import Router from 'vue-router'
import Register from "../components/Register";
import Login from "../components/Login";
import Tasks from "../components/Tasks";
import Employees from "../components/Employees";
import Home from "../components/Home";
import Login2 from "../components/Login2";

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
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/login2',
      name: 'Login2',
      component: Login2
    }
  ]
})
