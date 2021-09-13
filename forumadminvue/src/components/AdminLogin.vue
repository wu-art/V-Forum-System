<template>
  <div id="bgImg">
      <p class="title">V部落论坛后台</p>
      <el-form
        :model="loginForm"
        status-icon
        :rules="rules"
        ref="loginForm"
        label-width="100px"
        class="loginform"
      >
        <el-form-item prop="loginId" class="textConfog">
          <span slot="label">用户名</span>
          <el-input
            type="text"
            v-model="loginForm.loginId"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item prop="loginPwd">
          <span slot="label">密&emsp;码</span>
          <el-input
            type="password"
            v-model="loginForm.loginPwd"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login('loginForm')">登录</el-button>
          <el-button @click="resetForm('loginForm')">重置</el-button>
        </el-form-item>
      </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginForm: {
        loginId: "",
        loginPwd: ""
      },
      rules: {
        loginId: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        loginPwd: [{ required: true, message: "请输入密码", trigger: "blur" }]
      }
    };
  },
  methods: {
    login(loginForm) {
      this.$refs[loginForm].validate(valid => {
        if (valid) {
          this.$http.post("/AdminLogin",this.loginForm).then((res)=>{
                if(res.data.state){
                      let currentUser=res.data.admin;
                      sessionStorage.setItem("user",currentUser);
                      this.$router.push("/index");
                      this.$message.success(res.data.msg);
                }else{
                      this.$message.error(res.data.msg);
                }
          }).catch((err)=>{
                console.log(err.data);
          })
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style scope>
#bgImg {
  width: 100%;
  height: 100%;
  position: absolute;
  z-index: -1;
  background-image: url("../assets/images/loginBg.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}
#bgImg .el-form {
  width: 30%;
  height: 300px;
  position: absolute;
  left: 33%;
}
.el-form-item__label span {
  font-size: 20px;
  color: rgb(17, 7, 102);
}
.title{
      width: 20%;
      height: 20px;
      margin-top: 10%;
      line-height: 20px;
      margin-left: 44%;
      font-size: 30px;
      font-family: "楷体";
}
</style>