<template>
  <div id="repbox">
      <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>举报管理</el-breadcrumb-item>
            <el-breadcrumb-item>举报信息</el-breadcrumb-item>
      </el-breadcrumb>
    <br>
    <!-- 数据 -->
    <el-table
     :data="reportData.filter(data => !search || data.repContent.toLowerCase().includes(search.toLowerCase()))" 
     border style="width:100%">
      <el-table-column type="index" label="#" width="70"> </el-table-column>
      <el-table-column prop="user.userId" label="举报人" width="100">
      </el-table-column>
      <el-table-column prop="admin.loginId" label="处理人" width="100">
      </el-table-column>    
      <el-table-column prop="comment.comContent" label="举报评论">
      </el-table-column>
      <el-table-column prop="repContent" label="举报内容" :formatter="filterRepText">
      </el-table-column>
      <el-table-column prop="repState" label="处理状态" :formatter="filterState">
      </el-table-column>
      <el-table-column align="right">
            <template slot="header" slot-scope="scope">
                  <el-input v-model="search" size="mini" placeholder="根据举报内容搜索"/>
            </template>
            <template slot-scope="scope">
                  <el-button @click="delReport(scope.row.rid)" size="small" type="danger">删除</el-button>
                  <el-button @click="detailReport(scope.row)" size="small" type="info">详情</el-button>
                  <el-button @click="updateReport(scope.row)" size="small" type="warning">修改</el-button>
            </template>
      </el-table-column>
    </el-table>
    <!-- 举报详情 -->
    <el-dialog
      title="详细内容"
      :visible.sync="detailDia"
      width="30%">
      <span>{{reportDetail}}</span>
      <span slot="footer" class="dialog-footer">
      <el-button @click="detailDia = false">取 消</el-button>
      <el-button type="primary" @click="detailDia = false">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 修改弹窗 -->
    <el-dialog
      title="处理违规评论"
      :visible.sync="updateDia"
      width="30%">
      <el-select v-model="updateRepState" placeholder="请选择处理方式">
            <el-option label="忽略或不做处理" value="0"></el-option>
            <el-option label="已处理(删除违法评论)" value="1"></el-option>
      </el-select>
      <span slot="footer" class="dialog-footer">
            <el-button @click="updateDia = false">取 消</el-button>
            <el-button type="primary" @click="updateReportState">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 分页 -->
    <el-pagination
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
                  reportData:[],
                  currentPage:1,
                  pageSize:5,
                  totals:0,
                  detailDia:false,
                  updateDia:false,
                  reportDetail:'',
                  search:'',
                  updateRepState:'', //修改处理状态   
                  currCid:''          
            }
      },
      created(){
            this.getReportList();
      },
      methods:{
            //举报消息列表
            getReportList(){
                  let page="currPage="+this.currentPage+"&pageSize="+this.pageSize;
                  this.$http.post("/admin/FindReportByAdmin",page).then((res) => {
                        // console.log(res.data);
                        if(res.data.state){
                              this.reportData=res.data.reportList;
                              this.totals=res.data.totals;
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err) => {
                        this.$message.error(err.data);
                  });
            },
            //过滤举报信息
            filterRepText(row){
                  let con=row.repContent;
                  let s=con.substring(0,19);
                  if(con.length>20){
                        return s+"......";
                  }else{
                        return con;
                  }
            },
            //删除举报
            delReport(rid){
                  this.$confirm("确定删除?").then(()=>{
                        this.$http.get("/admin/DelReportAdmin?rid="+rid).then((res)=>{
                              if(res.data.state){
                                    this.$message.success(res.data.msg);
                                    this.getReportList();
                              }else{
                                    this.$message.error(res.data.msg);
                              }
                        }).catch((err)=>{
                              this.$message.error(err.data);
                        })
                  }).catch(()=>{
                        this.$message.info("已取消");
                  })

            },
            detailReport(row){
                  this.reportDetail=row.repContent;
                  this.detailDia=true;
            },
            handleSizeChange(val) {
                  //console.log(`每页 ${val} 条`);
                  this.pageSize=val;
                  this.getReportList();
            },
            handleCurrentChange(val) {
                  //console.log(`当前页: ${val}`);
                  this.currentPage=val;
                  this.getReportList();
            },
            //过滤举报状态
            filterState(row){
                  if(row.repState===0){
                        return "未处理";
                  }else if(row.repState===1){
                        return "已处理";
                  }
            },
            //修改状态
            updateReport(row){
                  this.currCid=row.comment.cid
                  if(row.repState===0){
                        this.updateRepState="未处理";
                  }else if(row.repState===1){
                        this.$message.info("该条已处理完,无须再次操作");
                  }                
                  this.updateDia=true;
            },
            //处理删除该评论
            updateReportState(){
                  if(this.updateRepState==0){
                        this.updateDia=false;
                  }else if(this.updateRepState==1){
                        this.$http.get("/admin/DelComment?cid="+this.currCid).then((res)=>{
                              if(res.data.state){
                                    this.updateDia=false;
                                    this.getReportList();
                                    this.$message.success(res.data.msg);
                              }else{
                                    this.$message.error(res.data.msg);
                              }
                        }).catch((err)=>{
                              this.$message.error(err.data);
                        });
                  }
            }
      }

}
</script>

<style>

</style>