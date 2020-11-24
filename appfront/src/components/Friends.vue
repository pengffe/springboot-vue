<template>
  <el-container class="friends">
    <el-aside class="body" width="30%"  style="background: #a0cfff">
      <button @click="show_users('school')">school</button>
      <button @click="show_users('address')">address</button>
      <button @click="show_users('personality')">personality</button>
      <button @click="show_users('major')">major</button>
      <div v-text="{hello}"/>
      <el-row>
        <el-table :data="user_interested_list" style="width: 100%">
          <el-table-column prop="firstName" label="People you may know">
            <template slot-scope="scope">{{scope.row.firstName}}</template>
          </el-table-column>
          <el-table-column prop="username" label="">
            <!--first name 需要改成user_name!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!           -->
            <input slot-scope="scope" type="submit" value="add"
                   @click="add_friends(scope.row.username)"/>
          </el-table-column>
          <el-table-column prop="username" label="">
            <input slot-scope="scope" type="submit" value="preview"
                   @click="preview_profile(scope.row.username)"/>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row class="contacts">
        <el-table :data="friendList" style="width: 100%">
          <el-table-column prop="first_name" label="Friends">
            <template slot-scope="scope">{{scope.row.firstName}}</template>
          </el-table-column>
          <el-table-column prop="username">
            <input slot-scope="scope" type="submit" value="delete"
                   @click="remove_friends(scope.row.username)"/>
          </el-table-column>
        </el-table>
      </el-row>
    </el-aside>

    <el-main class="el-main" width="70%">
      <keep-alive>
        <Profile :friend_username="friend_username"></Profile>
      </keep-alive>
    </el-main>
  </el-container>
</template>

<script>

    import Profile from "./Profile";

    export default {
        name: "Friends",
        components: {Profile},
        data() {
            return {
                username: '',
                friendList: '',
                userList: '',
                user_interested_list: '',
                friend_username: '',
                current_type: ''//绑定button输入
            }
        },
        mounted:
            function init() {
                this.username = this.$route.query.user_name;
                this.show_users('school');
                this.show_friends();
            },
        methods: {
            add_friends(friend_username) {
                this.$axios.post('http://127.0.0.1:8081/friends/' + this.username + '/'+ friend_username)
                    .then((response) => {
                      if(response.data){
                        this.show_friends();
                        this.show_users(this.current_type);
                      }else{
                        alert("Fail to load info!")
                      }
                    })
            },
            remove_friends(friend_user_name) {
                this.$axios.delete('http://127.0.0.1:8081/friends/' + this.username + '/' + friend_user_name)
                    .then((response) => {
                      if(response.data){
                        this.show_friends();
                        this.show_users(this.current_type);
                      }else{
                        alert("Fail to load info!")
                      }
                    })
            },
            show_users(type) {
                let url = 'http://127.0.0.1:8081/'
                let api = ''
                switch (type) {
                    case 'school':
                        api = '/show_users_interested/school/'
                        // this.show_users_school()
                        break;
                    case 'address':
                        api = 'api/show_users_interested?type=address&user_name='
                        //this.show_users_address()
                        break;
                    case 'major':
                        api = 'api/show_users_interested?type=major&user_name='
                        //this.show_users_major()
                        break;
                    case 'personality':
                        api = 'api/show_users_interested?type=personality&user_name='
                        //this.show_users_personality()
                        break;
                    default:
                        api = 'api/show_users/user_name='
                }
                console.log('show appbackend with the same' + type)
                this.$axios.get(url + api + this.username)
                    .then((response) => {
                      this.user_interested_list = response.data;
                      this.current_type = type;
                    })
            },
            show_friends() {
                this.$axios.get('http://127.0.0.1:8081/friends/' + this.username)
                    .then((response) => {
                      this.friendList = response.data;
                    })
            },
            preview_profile(friend_username) {
                this.friend_username = friend_username
                // this.$router.push({path:'/Profile',
                //     query:{'friend_username':friend_username, 'user_name': this.username}})
            }
        }
    }
</script>

<style scoped>
  .Profile {
    float: right;
    display: inline-block;
    height: 80%;
  }

  .user, userlist, category {
    float: left
  }

  /*.user_list {*/
  /*  float: left*/
  /*}*/

</style>
