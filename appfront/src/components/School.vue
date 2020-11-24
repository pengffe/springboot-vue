<template>
  <div class="school">
    <el-table :data="user_interested_list" style="width: 30%">
          <el-table-column prop="first_name" lable="People you may interest in">
            <template slot-scope="scope">{{scope.row.fields.first_name}}</template>
          </el-table-column>
          <el-table-column prop="user_name">
<!--first name 需要改成user_name!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!           -->
            <input slot-scope="scope" type="submit" value="add" @click="add_friends(scope.row.fields.first_name)"/>
          </el-table-column>
    </el-table>
  </div>
</template>

<script>
    export default {
        name: "School",
        data(){
            return{
                user_interested_list:''
            }
        },
        mounted:
            function(){
            this.show_users_school()
        },
        methods:{
            show_users_school(){
                alert("show appbackend with the same school")
                this.$http.get('http://127.0.0.1:8000/api/show_users_school?user_name=' + this.username)
                    .then((response) => {
                        let res = JSON.parse(response.bodyText)
                        console.log(res)
                        if (res.error_num === 0){
                            this.user_interested_list = res['list']
                        } else {
                            this.$message.error('Fail to show appbackend with same university!')
                            console.log(res['msg'])
                        }
                    })
            }
        }
    }
</script>

<style scoped>

</style>
