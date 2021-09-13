<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>消息管理</el-breadcrumb-item>
      <el-breadcrumb-item>推送消息</el-breadcrumb-item>
    </el-breadcrumb><br>
    <el-form
      :model="msgForm"
      :rules="rules"
      ref="msgForm"
      label-width="100px"
      class="demo-ruleForm"     
    >
      <el-form-item label="消息接收者" prop="userId">
            <el-select v-model="msgForm.userId" placeholder="请选择接收人" style="width:40%">
                  <el-option v-for="item in userList" :label="item.nickName" :value="item.userId" :key="item.userId"></el-option>
            </el-select>
            <!-- 可多选 -->
         <!-- <el-checkbox-group v-model="ruleForm.type">
            <el-checkbox label="美食/餐厅线上活动" name="user"></el-checkbox>
         </el-checkbox-group> -->
      </el-form-item>
      <el-form-item label="消息内容" prop="msgContent">
         <el-input type="textarea" v-model="msgForm.msgContent" placeholder="最多200个字" style="width:40%"></el-input>
      </el-form-item>
      <el-form-item label="推送时间" prop="msgTime">
         <el-date-picker
            style="width:40%"
            v-model="msgForm.msgTime"
            type="datetime"
            format="yyyy-MM-dd HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间">
         </el-date-picker>
      </el-form-item>
      <el-form-item>
            <el-button @click="sendMsg('msgForm')" type="success" size="small">推送</el-button>
            <el-button @click="reset('msgForm')" type="primary" size="small">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
      data(){
            return{
                  msgForm:{},
                  userList:[],
                  rules:{
                        userId: [{ required: true, message: '请选择接收人', trigger: 'blur' }],
                        msgContent: [{ required: true, message: '请输入消息内容', trigger: 'blur' }],
                        msgTime: [{ required: true, message: '请选择推送时间', trigger: 'blur' }]
                  }
            }
      },
      created(){
            this.getUserList();
      },
      methods:{
            getUserList(){
                  this.$http.get("/admin/FindUserList").then((res) => {
                        // console.log(res.data);
                        if(res.data.state){
                              this.userList=res.data.userList;
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err) => {
                        this.$message.error(err);
                  });
            },
            sendMsg(msgForm){
                  this.$refs[msgForm].validate((valid) => {
                        if (valid) {
                              let con="userId="+this.msgForm.userId+"&msgContent="+this.msgForm.msgContent+"&msgTime="+this.msgForm.msgTime;
                              this.$http.post("/admin/AddMsgByAdmin",con).then((res)=>{
                                    if(res.data.state){
                                          this.$message.success(res.data.msg);
                                          this.msgForm={};
                                    }else{
                                          this.$message.error(res.data.msg);
                                    }
                              }).catch((err)=>{
                                    this.$message.error(err);
                              });
                        } else {
                              console.log('error submit!!');
                              return false;
                        }
                  });
            },
            reset(msgForm){
                  this.$refs[msgForm].resetFields();
            }
      }
};
</script>

<style>
</style>