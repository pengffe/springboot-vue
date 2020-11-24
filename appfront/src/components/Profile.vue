<template>
  <h>{{this.friend_username}}'s profile</h>
</template>

<script>
    export default {
        name: "Profile",
        props:{
            friend_username: String,
            // required: true
        },
        data(){
            return{
                username:''
            }
        },
        methods:{

        }
    }
</script>

<style scoped>

</style>
