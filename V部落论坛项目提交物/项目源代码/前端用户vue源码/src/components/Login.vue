<template>
  <div class="box">
    <p class="title">欢迎访问V部落论坛</p>
    <img src="../assets/images/lunLog.jpg" alt="">
    <div class="loginBox">
      <el-tabs v-model="activeName">
        <el-tab-pane label="用户登录" name="first">
          <el-form
            :model="loginForm"
            :rules="loginrules"
            ref="loginForm"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item label="用户名" prop="userId">
              <el-input v-model="loginForm.userId"></el-input>
            </el-form-item>
            <el-form-item prop="userPwd">
              <span slot="label">密&emsp;码</span>
              <el-input v-model="loginForm.userPwd"></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="code">
              <el-input
                v-model="loginForm.code"
                placeholder="不区分大小写"
                style="width:200px;font-size:16px"
              ></el-input>
              <img
                :src="codeImg"
                alt=""
                @click="getCodeImg"
                class="logincode"
              />
            </el-form-item>
            <el-form-item>
              <el-button @click="login('loginForm')" type="primary"
                >登录</el-button
              >
              <el-button @click="loginReset('loginForm')">重置</el-button>
              <el-button @click="backhome" type="primary">首页</el-button>
            </el-form-item>
            <div class="txt">
              &emsp;子曰：“学而时习之，不亦说乎？有朋自远方来，不亦乐乎？人不知而不愠，不亦君子乎？
              有子曰:“其为人也孝弟而好犯上者，鲜矣；不好犯上而好作乱者，未之有也。君子务本，本立而道生。孝弟也者，其为仁之本与!”
              子曰:“巧言令色，鲜矣仁！”
              曾子曰“吾日三省吾身:为人谋而不忠乎?与朋友交而不信乎?传不习乎?”
              子曰:“道千乘之国,敬事而信，节用而爱人，使民以时。”
              子曰：“弟子入则孝，出则弟，谨而信，泛爱众，而亲仁。行有余力，则以学文。”
              子夏曰：“贤贤易色；事父母，能竭其力；事君，能致其身；与朋友交，言而有信。虽曰未学，吾必谓之学矣。”
              子曰：“为政以德，譬如北辰，居其所而众星共之。”
              子曰：“《诗》三百，一言以蔽之，曰：‘思无邪’。”
            </div>
          </el-form>
        </el-tab-pane>
        <!-- 注册 -->
        <el-tab-pane label="用户注册" name="second">
          <el-form
            :model="regForm"
            :rules="regrules"
            ref="regForm"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item label="用户名" prop="userId">
              <el-input v-model="regForm.userId"></el-input>
            </el-form-item>
            <el-form-item prop="userPwd">
              <span slot="label">密&emsp;码</span>
              <el-input v-model="regForm.userPwd"></el-input>
            </el-form-item>
            <el-form-item prop="nickName">
              <span slot="label">昵&emsp;称</span>
              <el-input v-model="regForm.nickName"></el-input>
            </el-form-item>
            <el-form-item prop="tel">
              <span slot="label">电&emsp;话</span>
              <el-input v-model="regForm.tel"></el-input>
            </el-form-item>
            <el-form-item prop="regTime">
              <span slot="label">日&emsp;期</span>
              <el-date-picker
                v-model="regForm.regTime"
                type="datetime"
                format="yyyy-MM-dd HH:mm:ss"
                value-format="yyyy-MM-dd HH:mm:ss"
                placeholder="选择注册时间"
              >
              </el-date-picker>
            </el-form-item>
            <el-form-item>
              <span slot="label">头&emsp;像</span>
              <input type="file" @change="setUserPic" id="pic" name="userPic" />
            </el-form-item>
            <el-form-item>
              <el-button @click="reg('regForm')" type="primary">注册</el-button>
              <el-button @click="regReset('regForm')">重置</el-button>
              <el-button @click="backhome" type="primary">首页</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import { Data } from "@icon-park/vue";
export default {
  data() {
    return {
      baseUrl: this.$http.defaults.baseURL,
      codeImg: this.$http.defaults.baseURL + "/kaptcha.jpg",
      activeName: "first",
      loginForm: {
        userId: "",
        userPwd: "",
        code: ""
      },
      regForm: {
        userId: "",
        userPwd: "",
        nickName: "",
        tel: "",
        regTime: ""
      },
      //文件
      userPic: "",
      loginrules: {
        userId: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        userPwd: [{ required: true, message: "请输入密码", trigger: "blur" }],
        code: [{ required: true, message: "请输入验证码", trigger: "blur" }]
      },
      regrules: {
        userId: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        userPwd: [{ required: true, message: "请输入密码", trigger: "blur" }],
        nickName: [{ required: true, message: "请输入昵称", trigger: "blur" }],
        tel: [{ required: true, message: "请输入电话", trigger: "blur" }]
      }
    };
  },
  created() {
    this.getCodeServer();
  },
  methods: {
    login(loginForm) {
      this.$refs[loginForm].validate(valid => {
        if (valid) {
          this.$http
            .post("/UserLogin", this.loginForm)
            .then(res => {
              if (res.data.state) {
                sessionStorage.setItem("user", JSON.stringify(res.data.user));
                this.$message.success(res.data.msg);
                this.$confirm("前往首页?", "提示", {
                  confirmButtonText: "确定",
                  cancelButtonText: "取消",
                  type: "warning"
                })
                  .then(() => {
                    this.$router.push("/");
                  })
                  .catch(() => {
                    this.$message({
                      type: "info",
                      message: "已取消"
                    });
                  });
              } else {
                this.$message.error(res.data.msg);
              }
            })
            .catch(err => {
              this.$message.error(err.data.msg);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    loginReset(loginForm) {
      this.$refs[loginForm].resetFields();
    },
    //点击更新验证码
    getCodeImg() {
      this.getCodeServer();
    },
    //获取验证码
    getCodeServer() {
      this.codeImg = this.baseUrl + "/kaptcha.jpg?d=" + new Date();
    },
    //上传文件
    setUserPic(event) {
      this.userPic = event.target.files[0];
    },
    //注册
    reg(regForm) {
      this.$refs[regForm].validate(valid => {
        if (valid) {
          let formData = new FormData(); //获取表单数据
          let re = this.regForm; //书籍对象
          Object.keys(re).forEach(key => {
            formData.append(key, re[key]);
          });
          formData.append("userPic", this.userPic);
          this.$http
            .post("/RegisterUser", formData, {
              headers: {
                "content-type": "multipart/form-data"
              }
            })
            .then(res => {
              if (res.data.state) {
                this.$message.success(res.data.msg);
                this.regForm = {};
              } else {
                this.$message.error(res.data.msg);
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
      console.log(this.regForm);
    },
    regReset(regForm) {
      this.$refs[regForm].resetFields();
    },
    backhome() {
      this.$router.push("/");
    }
  }
};
</script>

<style scope>
.loginBox {
  width: 40%;
  height: 500px;
  margin: 0 auto;
  margin-top: -55px;
  border: 1px rgb(77, 70, 70) solid;
  clear: both;
}
.box .title {
      font-size: 25px;
      font-weight: 700;
      width: 230px;
      margin-left: 35%;
      margin-top: 80px;
}
.box>img{
      width: 100px;
      height: 60px;
      position: relative;
      top: -65px;
      left: 55%;
}
.loginBox .el-form {
  margin-left: -20px;
}
.loginBox .el-form .el-form-item {
  width: 90%;
}
div.txt {
  margin-left: 25px;
  font-family: "楷体";
}
.logincode {
  width: 110px;
  height: 40px;
  float: right;
  margin-right: 80px;
}
</style>