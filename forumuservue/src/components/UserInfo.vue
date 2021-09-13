<template>
  <div class="myinfo">
      <el-card class="box-card">
            <a href="javascript:;" @click="backHome">回到首页</a>
            <a href="javascript:;" @click="collectionForum">个人贴吧收藏</a>
            <p class="header">个人信息中心</p>
            <el-form label-width="80px" :model="selfInfo">
                  <el-form-item label="头像">
                        <img :src="`${baseUrl}/upload/${selfInfo.userPic}`" alt="头像" width="100" height="100">
                        <br>
                        <span>修改头像</span>
                        <input type="file" @change="updateUserPic" id="pic" name="userPic" />
                        <input type="button" @click="updatePic" value="点击上传"/>
                        <span class="info">(若未改变,退出重新登录即可)</span>
                  </el-form-item>
                  <el-form-item label="账户">
                  <el-input v-model="selfInfo.userId" readonly></el-input>
                  </el-form-item>
                  <el-form-item label="密码">
                  <el-input v-model="selfInfo.userPwd" readonly></el-input>
                  </el-form-item>
                  <el-form-item label="昵称">
                  <el-input v-model="selfInfo.nickName" readonly></el-input>
                  </el-form-item>
                  <el-form-item label="电话">
                  <el-input v-model="selfInfo.tel" readonly></el-input>
                  </el-form-item>
                  <el-form-item label="注册时间">
                  <el-input v-model="selfInfo.registerTime" readonly></el-input>
                  </el-form-item>
                  <el-form-item label="当前状态" >
                        <el-input v-model="selfInfo.userState" readonly>
                        </el-input>
                  </el-form-item>
            </el-form>
            
      </el-card>
  </div>
</template>

<script>
export default {
      data(){
            return{
                  selfInfo:{},
                  baseUrl:this.$http.defaults.baseURL,
                  userPic:''
            }
      },
      created(){
            this.selfInfo=JSON.parse(sessionStorage.getItem("user"));
            if(this.selfInfo.userState ==1){
                  this.selfInfo.userState="用户正常";
                  this.selfInfo.userPwd="xxxxxx";
            }else{
                  this.selfInfo.userState="用户存在异常";
                  this.selfInfo.userPwd="xxxxxx";
            }
      },
      methods:{
            //上传文件
            updateUserPic(event){
                  this.userPic = event.target.files[0];
            },
            updatePic(){
                  alert(1)
                  let fd=new FormData();
                  fd.append("userPic",this.userPic);
                  fd.append("userId",this.selfInfo.userId);
                  this.$http.post("/admin/UpdateUserPic",fd,{
                        Headers:{
                              'content-type': 'multipart/form-data'
                        }
                  }).then((res) => {
                        if(res.data.state){
                              this.$message.success(res.data.msg);
                        }

                  }).catch((err) => {
                        this.$message.success(err.data);
                  });
            },
            backHome(){
                  this.$router.push("/");
            },
            collectionForum(){
                  this.$router.push("/usercollectforum");
            }
      }

}
</script>

<style scope>
.myinfo>.box-card{
      margin: 0 auto;
      width: 80%;
}
.box-card .header{
      width: 200px;
      font-size: 30px;
      font-family: "楷体";
      margin-left: 30%;
}
.box-card .el-form{
      width: 70%;
}
.el-form .info{
      font-size: 14px;
      color: grey;
}
</style>