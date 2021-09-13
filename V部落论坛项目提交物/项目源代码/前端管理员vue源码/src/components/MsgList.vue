<template>
  <div id="msgBox">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>消息管理</el-breadcrumb-item>
      <el-breadcrumb-item>已发消息列表</el-breadcrumb-item>
    </el-breadcrumb><br>
    <el-table :data="tableData.filter(data => !search || data.msgContent.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column type="index" label="#" width="80"> </el-table-column>
      <el-table-column prop="user.userId" label="用户ID" width="180"> </el-table-column>
      <el-table-column prop="admin.loginId" label="管理员ID" width="180"> </el-table-column>
      <el-table-column prop="msgContent" label="消息" width="280" :formatter="getMsg">
      </el-table-column>
      <el-table-column prop="msgTime" label="发布时间" width="280">
      </el-table-column>
      <el-table-column align="right">
            <template slot="header" slot-scope="scope">
                  <el-input v-model="search" size="mini" placeholder="输入消息关键字搜索"/>
            </template>
            <template slot-scope="scope">
                  <el-button @click="detailMsg(scope.row)" size="mini" type="info">详情</el-button>
                  <el-button @click="delMsg(scope.row)" size="mini" type="danger">删除</el-button>
                  <el-button @click="addMsg(scope.row)" size="mini" type="success">去发送</el-button>
            </template>
      </el-table-column>
    </el-table>
    <!-- 消息详情弹框 -->
    <el-dialog
      title="详细内容"
      :visible.sync="detailDia"
      width="30%">
      <span>{{detailText}}</span>
      <span slot="footer" class="dialog-footer">
      <el-button @click="detailDia = false">取 消</el-button>
      <el-button type="primary" @click="detailDia = false">确 定</el-button>
      </span>
    </el-dialog>
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5,8,10]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totals">
    </el-pagination>
  </div>
</template>

<script>
export default {
      data(){
            return{
                  currentPage:1,
                  pageSize:8,
                  totals:0,
                  tableData:[],
                  search:'',
                  detailText:'',
                  detailDia:false
            }
      },
      created(){
            this.getMsgList();
      },
      methods:{
            //获取消息列表
            getMsgList(){
                  this.$http.get("/admin/FindMsgListAdmin?currentPage="+this.currentPage+"&pageSize="+this.pageSize).then((res)=>{
                        if(res.data.state){
                              // console.log(res.data);
                              this.tableData=res.data.msgList;
                              this.totals=res.data.totals;
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err)=>{
                        this.$message.error(err);
                  });
            },
            //格式化消息内容
            getMsg(row){
                  let con=row.msgContent;
                  let res=con.substring(0,16);
                  if(con.length > 15){
                        return res+"......";
                  }else{
                        return con;
                  }
                  
            },
            handleSizeChange(val) {
                  //console.log(`每页 ${val} 条`);
                  this.pageSize=val;
                  this.getMsgList();
            },
            handleCurrentChange(val) {
                  //console.log(`当前页: ${val}`);
                  this.currentPage=val;
                  this.getMsgList();
            },
            //发送消息
            addMsg(row){
                  this.$router.push("/AddMsg");
            },
            //消息详情
            detailMsg(row){
                  this.detailText=row.msgContent;
                  this.detailDia=true;
            },
            //删除消息
            delMsg(row){
                  this.$confirm("确定删除?").then(()=>{                      
                        this.$http.get("/admin/DelMsgFromAdmin?amId="+row.amId).then((res)=>{
                              if(res.data.state){
                                    this.$message.success(res.data.msg);
                                    this.getMsgList();
                              }else{
                                    this.$message.error(res.data.msg);
                              }
                        })
                  }).catch(()=>{
                        this.$message.info("已取消");
                  });
            }
      }
};
</script>

<style>
</style>