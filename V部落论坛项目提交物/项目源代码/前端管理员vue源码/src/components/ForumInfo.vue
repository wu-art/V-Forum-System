<template>
  <div id="fbox">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>贴吧管理</el-breadcrumb-item>
      <el-breadcrumb-item>帖子信息</el-breadcrumb-item>
    </el-breadcrumb>
    <br />
    <!-- 数据 -->
    <el-table
     :data="forumData.filter(data => !search || data.content.toLowerCase().includes(search.toLowerCase()))" 
     border style="width:100%">
      <el-table-column type="index" label="#" width="70"> </el-table-column>
      <el-table-column prop="userId" label="帖子作者" width="180">
      </el-table-column>
      <el-table-column prop="title" label="帖子标题" width="250">
      </el-table-column>
      <el-table-column prop="content" label="帖子内容" width="350" :formatter="contentFilter">
      </el-table-column>
      <el-table-column prop="state" label="帖子状态" width="150">
            <template slot-scope="scope">        
                  <el-switch
                        v-model="scope.row.state"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        :active-value="1"
                        :inactive-value="0"
                        @change="changeSwitch(scope.row)">
                  </el-switch>
            </template>
      </el-table-column>
      <el-table-column align="right">
            <template slot="header" slot-scope="scope">
                  <el-input v-model="search" size="mini" placeholder="根据帖子内容搜索"/>
            </template>
            <template slot-scope="scope">
                  <el-button @click="delForum(scope.row.fid)" size="small" type="danger">删除</el-button>
                  <el-button @click="detailForum(scope.row.content)" size="small" type="info">详情</el-button>
            </template>
      </el-table-column>
    </el-table>
    <!-- 帖子详情 -->
    <el-dialog
      title="详细内容"
      :visible.sync="detailDia"
      width="30%">
      <span>{{detailContent}}</span>
      <span slot="footer" class="dialog-footer">
      <el-button @click="detailDia = false">取 消</el-button>
      <el-button type="primary" @click="detailDia = false">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 分页 -->
    <el-pagination
      style="margin-left:50px"
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5, 6, 7]"
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
                  forumData:[],
                  currentPage:1,
                  pageSize:5,
                  totals:0,
                  search:'',
                  detailDia:false,
                  detailContent:''
            }
      },
      created(){
            this.getForumList();
      },
      methods:{
            //获取数据
            getForumList(){
                  let page="currPage="+this.currentPage+"&pageSize="+this.pageSize;
                  this.$http.post("/admin/FindForumByAdmin",page).then((res) => {
                        if(res.data.state){
                              for(let i=0;i<res.data.fbaList.length;i++){
                                    if(res.data.fbaList[i].state==="通过审核"){
                                          res.data.fbaList[i].state=1;
                                    }else if(res.data.fbaList[i].state==="审核未通过"){
                                          res.data.fbaList[i].state=0;
                                    }
                              }
                              this.forumData=res.data.fbaList;
                              this.totals=res.data.totals;
                        }else{
                              this.$message.error(res.data.msg);
                              this.totals=res.data.totals;
                        }
                  }).catch((err) => {
                        this.$message.error(err.data);
                  });
            },
            handleSizeChange(val) {
                  //console.log(`每页 ${val} 条`);
                  this.pageSize=val;
                  this.getForumList();
            },
            handleCurrentChange(val) {
                  //console.log(`当前页: ${val}`);
                  this.currentPage=val;
                  this.getForumList();
            },
            //过滤帖子内容
            contentFilter(row){
                  let c=row.content;
                  let len=c.substring(0,25);
                  if(c.length>25){
                        return len+"......";
                  }else{
                        return c;
                  }
            },
            //详情
            detailForum(content){
                  this.detailContent=content;
                  this.detailDia=true;
            },
            //修改帖子状态
            changeSwitch(row){
                  let state;
                  if(row.state===1){
                        state="通过审核";
                  }else if(row.state===0){
                        state="审核未通过";
                  }
                  let resp="fid="+row.fid+"&state="+state;
                  this.$http.post("/admin/UpdateForumState",resp).then((res)=>{
                        if(res.data.state){
                              this.$message.success(res.data.msg);
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err)=>{
                        this.$message.success(err.data);
                  });
            },
            //删除
            delForum(fid){
                  this.$confirm("确定删除?").then(()=>{
                        this.$http.get("/admin/DelForumByAdmin?fid="+fid).then((res=>{
                              if(res.data.state){
                                    this.$message.success(res.data.msg);
                                    this.getForumList();
                              }else{
                                    this.$message.error(res.data.msg);
                              }
                        })).catch((err)=>{
                              this.$message.error(err.data.msg);
                        });
                  }).catch(()=>{
                        this.$message.info("已取消");
                  });
            }
      }
};
</script>

<style>
</style>