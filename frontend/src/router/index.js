import Router from 'vue-router'
import Register from "../components/Register";
import Login from "../components/Login";
import TasksAndEmployees from "../components/TasksAndEmployees";
import Employees from "../components/Employees";
import Tasks from "../components/Tasks";


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
      path: '/tasksAndEmployees',
      name: 'TasksAndEmployees',
      component: TasksAndEmployees
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
