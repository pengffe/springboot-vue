<template>
  <div>
    <h1>Reset Password</h1>
    <input type="password" v-model="password" placeholder="Please input your new password"/>
    <br>
    <input type="password" v-model="password2" placeholder="Please input your new password again"/>
    <br>
    <input type="submit" value="Submit" @click="reset_password"/>
    <br>
    <li v-bind="(value, key) in err_msg">{{value.toString().trim()}}</li>
  </div>
</template>

<script>
    export default {
        name: "ResetPassword",
        data(){
            return{
                password: '',
                password2: '',
                code: '',
                err_msg:'',
            }
        },
        methods:{
            reset_password(){
                if (this.password === this.password2){
                    this.code = this.$route.query.code
                    let info = {password: this.password, code: this.code}
                    // this.$http.get('http://127.0.0.1:8000/api/user_reset_password?password=' + this.password + '&'
                    //  + 'code=' + this.code).then(

                    this.$http.post('http://127.0.0.1:8000/api/user_reset_password', info, {emulateJSON:true}).then(
                        function(res){
                            if (res.data['reset'] === 'true'){
                                this.$router.push('/Registration')
                            }
                            else{
                                this.err_msg = res.data['error'];
                            }
                                // alert('Fail to reset password, please try again!')
                        })
                }
                else {
                    alert('The passwords should be the same!')
                }

            }
        }

    }
</script>

<style scoped>

</style>
