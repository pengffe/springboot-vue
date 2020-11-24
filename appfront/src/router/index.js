import Vue from 'vue'
import Router from 'vue-router'
import Registration from "../components/Registration";
import Home from '../components/Home'
import UserProfile from "../components/UserProfile";
import School from "../components/School"
import Friends from "../components/Friends";
// import Profile from "../components/Profile";
import ForgetPassword from "../components/ForgetPassword";
import ResetPassword from "../components/ResetPassword";

Vue.use(Router)


export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/Registration'
    },
    {
      path: '/ForgetPassword',
      component: ForgetPassword
    },
    {
      path: '/ResetPassword',
      component: ResetPassword
    },
    {
      path: '/Registration',
      //name: 'Registration',
      component: Registration,
      children: [
        {
          path: '/School',
          component: School
        },
      ]
    },
    {
      path: '/Home',
      // name: 'Home',
      component: Home,
      children: [
        {
          path: '/Friends',
          component: Friends,
          hidden: true,
          // children: [
          //   {
          //     path: '/Profile',
          //     // name: 'UserProfile',
          //     component: Profile
          //   }
          // ]
        },
        {
          path: '/UserProfile',
          component: UserProfile,
        }
      ]
    }
  ]
})
