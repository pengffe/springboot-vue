<template>
  <div class = "UserProfile">
    <form>
      <input type="text" v-model="Last_name" name="Last name" placeholder="Last Name"><br>
      <input type="text" v-model="First_name" name="First name" placeholder="First Name"><br>
      <el-date-picker
                    v-model="form.Birthday"
                    type="date"
                    @change="getBirthdayDate"
                    placeholder="Date of Birth"
                    format="yyyy年MM月dd日"
                    value-format="yyyy-MM-dd">
      </el-date-picker><br>
      <select name="Gender" v-model="Gender">
        <option value="1">Male</option>
        <option value="2">Female</option>
      </select><br>
      <input type="text" v-model="Address" name="Address" placeholder="Address"><br>
      <input type="text" v-model="Phone_number" name="Phone number" placeholder="Phone Number"><br>
      <input type="text" v-model="School" name="School" placeholder="School"><br>
      <input type="text" v-model="Major" name="Major" placeholder="Major"><br>
      <el-date-picker
                    v-model="form.Enroll_time"
                    type="date"
                    @change="getEnrollTime"
                    placeholder="Date of Enrollment"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd">
      </el-date-picker><br>
      <select name="Personality" v-model="Personality" placeholder="Personality">
        <option value="1">Party</option>
        <option value="2">Basketball</option>
        <option value="3">Game</option>
        <option value="4">Switch</option>
      </select><br>
      <input type="submit" value="Save" @click="save_user_profile()"/>
      <li v-bind="(value, key) in err_msg">{{value.toString().trim()}}</li>
    </form>
  </div>
</template>

<script>
    export default {
        name: "UserProfile",
        data(){
            return{
                user_name:'',
                Last_name: '',
                First_name:'',
                Gender:'',
                Address:'',
                Phone_number:'',
                School:'',
                Major:'',
                Personality:'',
                form: {
                    Birthday: '',
                    Enroll_time:''
                },
                err_msg:''
            }
        },
        mounted(){
            this.user_name = this.$route.query.user_name
        },
        methods: {
            getBirthdayDate(val){
                this.form.Birthday = val;
            },
            getEnrollTime(val){
                this.form.Enroll_time = val;
            },
            save_user_profile(){
                let profile= {user_name: this.user_name, last_name: this.Last_name, first_name: this.First_name,
                              gender: this.Gender, address: this.Address, phone_number: this.Phone_number,
                              school: this.School, major: this.Major, personality: this.Personality,
                              birthday: this.form.Birthday, enroll_time: this.form.Enroll_time
                }
                this.$http.post('http://127.0.0.1:8000/api/save_user_profile', profile, {emulateJSON:true}).then(
                    function (res) {
                        if(res.data['error']){
                            this.err_msg = res.data['error']
                        }
                        else{
                            this.$router.push('/Home')
                        }
                    }
                )
            }
        }
    }
</script>

<style scoped>
  input[type=text], el-date-picker {
    width: 40%;
    padding: 18px 20px;
    margin: 20px 0px;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: larger;
  }
  select{
    width: 15%;
    height: 150%;
    padding: 18px 20px;
    margin: 20px 0px;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: large;
  }
</style>
