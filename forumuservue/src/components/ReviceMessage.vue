<template>
  <div id="colbox">
        <el-card class="box-card">
            <span><a href="javascript:;" @click="toSelfInfo">个人中心</a></span>
            <span><a href="javascript:;" @click="toHome">首页</a></span>
            <p>消息接收与管理圈</p>
            <span v-for="item in msgList" :key="item.umId">
                  <el-card shadow="hover">
                        {{item.mesContent}}
                        <el-button @click="delMsg(item.umId)" :key="item.umId"type="danger" size="mini" icon="el-icon-delete"></el-button>
                  </el-card><br>
            </span>
            <span v-for="item in oldList">
                  <el-alert
                        :title="item.title"
                        type="warning"
                        :closable="false">
                        <el-button @click="resetSengForum(item.fid)" type="success" size="mini">重发</el-button>
                        <el-button @click="delForum(item.fid)" type="danger" size="mini">删除</el-button>
                  </el-alert>
                  
                  <br>
            </span>
            <!-- 分页 -->
            <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="currentPage"
                  :page-sizes="[5, 9, 12, 15]"
                  :page-size="pageSize"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="totals+oldList.length">
            </el-pagination>
        </el-card>
  </div>
</template>

<script>
export default {
      data(){
            return{
                  totals:0,
                  currentPage:1,
                  pageSize:10,
                  msgList:[],
                  oldList:[]
            }
      },
      created(){           
            this.getMsg();
            //路由传参
            let user=JSON.parse(sessionStorage.getItem("user"));
            let failList=JSON.parse(this.$route.query.oldList);
            // console.log(failList);
            if(failList.length>0){
                  for(let i=0;i<failList.length;i++){
                        if(failList[i].forumUser.userId===user.userId){
                              this.oldList.push(failList[i]);
                        }
                  }
            }
      },
      methods:{
            //获取消息列表
            getMsg(){
                  let user=JSON.parse(sessionStorage.getItem("user"));
                  let page="currPage="+this.currentPage+"&pageSize="+this.pageSize+"&userId="+user.userId;
                  this.$http.post("/admin/FindMsgListUser",page).then((res)=>{
                        if(res.data.state){
                              this.msgList=res.data.userMsgList;
                              this.totals=res.data.totals;
                              //console.log(this.msgList);
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err)=>{
                        this.$message.error(err.data);
                  });
            },
            handleSizeChange(val) {
                  // console.log(`每页 ${val} 条`);
                  this.pageSize=val;
                  this.getMsg();
            },
            handleCurrentChange(val) {
                  // console.log(`当前页: ${val}`);
                  this.currentPage=val;
                  this.getMsg();
            },
            toSelfInfo(){
                  this.$router.push("/userinfo");
            },
            toHome(){
                  this.$router.push("/");
            },
            //删除消息
            delMsg(umId){
                  this.$confirm("确定删除此消息?").then(()=>{
                        this.$http.get("/admin/DelMsgFromUser?umId="+umId).then((result)=>{
                              if(res.data.state){
                                    this.$message.success(res.data.msg);
                              }else{
                                    this.$message.error(res.data.msg);
                              }
                        }).catch((err)=>{
                              this.$message.error(err.data.msg);
                        })
                  }).catch(()=>{
                        this.$message.info("已取消!");
                  })
                  
            },
            //重发帖子
            resetSengForum(fid){
                  this.$http.post("/admin/ResendForumByUser?fid="+fid).then((res)=>{
                        if(res.data.state){
                              this.$message.success(res.data.msg);
                        }
                  }).catch((err)=>{
                        this.$message.error(err.data.msg);
                  })
            },
            //删除消息
            delForum(fid){
                  this.$confirm("确定删除?").then(()=>{
                        this.$http.post("/admin/DelForumByUser?fid="+fid).then((res)=>{
                              if(res.data.state){
                                    this.$message.success(res.data.msg+",请退出重新进入");
                              }
                        }).catch((err)=>{
                              this.$message.success(err.data.msg);
                        });
                  }).catch(()=>{
                        this.$message.info("取消操作");
                  });
            }
      }


}
</script>

<style scope>
#colbox{
       width: 60%;
       margin: 0 auto;
}
.box-card p{
      font-size: 30px;
      color: black;
      text-align: center;
}
</style>