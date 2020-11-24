<template>
  <div>
    <h1>Forget Password</h1>
    <input type="text" v-model="username" placeholder="Please input the email!"/>
    <input type="submit" value="Submit" @click="user_forget_password"/>
    <router-link to="/Registration">Sign in directly</router-link>
  </div>
</template>

<script>
    export default {
        name: "ForgetPassword",
        data() {
            return {
                username: ''
            }
        },
        methods: {
            user_forget_password() {
                this.$http.post('http://127.0.0.1:8000/api/user_forget_password',
                    {user_name: this.username}, {emulateJSON: true})
                this.$router.push('/Registration')
            }
        }
    }
</script>

<style scoped>

</style>
