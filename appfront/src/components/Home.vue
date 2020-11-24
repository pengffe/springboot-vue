<template>
  <layout>
    <div slot="header">
      <TopBar></TopBar>
    </div>
    <div slot="nav">
      <SideBar></SideBar>
    </div>
    <div slot="post">
      <router-view></router-view>
    </div>
  </layout>

</template>

<script>
    // import Profile from "./Profile";
    import TopBar from "../views/Top-bar";
    import SideBar from "../views/Side-bar";
    export default {
        name: "Home",
        // components: {SideBar, TopBar, Profile},
        components: {SideBar, TopBar},
      // data(){
        //     return{
        //         username:''
        //     }
        // },
        // mounted:{
        //     function(){
        //         this.username = this.$route.query.user_name
        //     }
        // }
    }
</script>

<style scoped>

</style>
