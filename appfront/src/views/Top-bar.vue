<template>
  <div class="topnav">
    <div class="options">
      <button class="friends" @click="show_friends()">Friends</button>
      <router-link to="/Home">Home</router-link>
      <router-link to="/Friends">Friends</router-link>
      <button class="userProfile" @click="edit_profile()">Profile</button>
    </div>
  </div>
</template>

<script>
    export default {
        name: "Top-bar",
        data(){
            return{
                username:''
            }
        },
        mounted() {
            this.username = this.$route.query.user_name
        },
        methods:{
            show_friends(){
                this.$router.push({path: '/Friends', query: {'user_name': this.username}})
            },
            edit_profile(){
                this.$router.push({path: '/UserProfile', query: {'user_name': this.username}})
            }
        }
    }
</script>

<style scoped>

</style>
