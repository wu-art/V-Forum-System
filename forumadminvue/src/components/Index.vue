<template>
  <div>
    <el-container>
      <el-header style="height:80px">
        <span class="nav-one">管理员:{{curUser}}</span>
        <!-- <img src="../assets/images/forum.jpg" alt="" class="log">
        <span class="nav-two">         
          V部落论坛后台管理系统
        </span> -->
        <span style="margin-left:78%">
          <a href="javascript:;" @click="loginout()" style="color:white">
            <i class="el-icon-eleme"></i>
            退出系统
          </a>
        </span>
      </el-header>
      <el-container>
            <div class="aside">
                  <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
                        <el-radio-button :label="false">
                          <i class="el-icon-caret-right"></i>
                        </el-radio-button>
                        <el-radio-button :label="true">
                          <i class="el-icon-caret-left"></i>
                        </el-radio-button>
                  </el-radio-group>
                  <div>
                        <el-menu :router="true" :unique-opened="true" 
                        :collapse="isCollapse" default-active="2" class="el-menu-vertical-demo" 
                        text-color="#fff" active-text-color="#ffd04b">
                              <el-submenu index="1">
                              <template slot="title">
                                    <i class="el-icon-s-custom"></i>
                                    <span slot="title">用户管理</span>
                              </template>
                              <el-menu-item index="UserList">
                                    <i class="el-icon-user"></i>
                                    用户列表
                              </el-menu-item>
                              </el-submenu>
                              <el-submenu index="2">
                                <template slot="title">
                                      <i class="el-icon-office-building"></i>
                                      <span>种类管理</span>
                                </template>
                                <el-menu-item index="KindInfo">
                                      <i class="el-icon-s-data"></i>
                                      分类信息
                                      <!-- <router-link to="/fwlist">房屋列表</router-link> -->
                                </el-menu-item>              
                              </el-submenu>
                              <el-submenu index="3">
                                <template slot="title">
                                      <i class="el-icon-notebook-1"></i>
                                      <span>吧贴管理</span>
                                </template>
                                <el-menu-item index="ForumInfo">
                                      <i class="el-icon-coin"></i>
                                      帖子信息
                                      <!-- <router-link to="/fwlist">房屋列表</router-link> -->
                                </el-menu-item>              
                              </el-submenu>
                              <el-submenu index="4">
                                <template slot="title">
                                      <i class="el-icon-bell"></i>
                                      <span>消息管理</span>
                                </template>
                                <el-menu-item index="MsgList">
                                      <i class="el-icon-message-solid"></i>
                                      消息列表
                                </el-menu-item>
                                <el-menu-item index="AddMsg">
                                      <i class="el-icon-phone"></i>
                                      推送消息
                                </el-menu-item>              
                              </el-submenu>
                              <el-submenu index="5">
                                <template slot="title">
                                      <i class="el-icon-warning"></i>
                                      <span>举报模块</span>
                                </template>
                                <el-menu-item index="ReportMsg">
                                      <i class="el-icon-s-release"></i>
                                      举报信息
                                </el-menu-item>             
                              </el-submenu>
                              <el-submenu index="6">
                                <template slot="title">
                                      <i class="el-icon-s-tools"></i>
                                      <span>系统设置</span>
                                </template>
                                <el-menu-item index="SysConfig">
                                      <i class="el-icon-setting"></i>
                                      设置中心
                                </el-menu-item>             
                              </el-submenu>                  
                        </el-menu>
                  </div>         
            </div>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
      <el-footer style="height:100px">
        <div class="footer">
          <p style="height:50px;margin-top:20px">&copy;xxx出版 | 作者:MisWu | 代码:60015260025sss</p>
          <p style="margin-top:-20px">V部落论坛后台管理</p>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      isCollapse: true,
      curUser:""
    };
  },
  created(){
    this.curUser=sessionStorage.getItem("user");
  },
  methods: {
    loginout(){
      sessionStorage.clear();
      this.$router.push("/");
    }
  },
};
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 230px;
    /* min-height: 490px; */
}
.el-header{
  background-color: rgb(108,159,202);
  color: white;
  font-family: "楷体";
  line-height: 80px;
  font-size: 25px;
}
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  /* line-height: 100px; */
}
.aside{
  background-color: rgb(47, 93, 131);
}
.el-menu{
  background-color: rgb(47, 93, 131);
}
.el-main {
  background-color: #e9eef3;
  color: #333;
  height: 540px;
}

.nav-two{ 
  margin-left: 28%;
}
.el-dropdown{
  float: right;
}
div.footer{
  color: gray;
}
.log{
  width: 50px;
  height:50px;
  border-radius:50%;
  position: relative;
  top: 15px;
  left: 28%;
}
</style>
