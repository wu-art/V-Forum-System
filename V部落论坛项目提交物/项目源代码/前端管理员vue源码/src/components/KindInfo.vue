<template>
  <div class="forumkind">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>种类管理</el-breadcrumb-item>
      <el-breadcrumb-item>分类信息</el-breadcrumb-item>
    </el-breadcrumb>
    <br>
    <!-- 数据 -->
    <el-table :data="typeData" border style="width: 60%;margin:0 auto">
      <el-table-column type="index" label="#" width="70">
      </el-table-column>
      <el-table-column prop="typeName" label="种类名称" width="240">
      </el-table-column>
      <el-table-column label="操作" width="270">
        <template slot-scope="scope">
          <el-button @click="updateDialog(scope.row)" size="small" type="primary">修改</el-button>
          <el-button @click="delType(scope.row.typeId)" size="small" type="danger">删除</el-button>
          <el-button @click="addTypeDia" size="small" type="success">添加</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹框 -->
    <el-dialog
      title="修改帖子种类"
      :visible.sync="dialogVisible"
      width="30%"
      >
      <el-form label-width="80px" :model="updateForm">
            <el-form-item label="分类名称">
                  <el-input v-model="updateForm.typeName"></el-input>
            </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="updateType">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 添加弹框 -->
    <el-dialog
      title="添加帖子种类"
      :visible.sync="addDisVisible"
      width="30%"
      >
      <el-form label-width="80px">
            <el-form-item label="分类名称">
                  <el-input v-model="addTypeName" placeholder="请输入类型名称"></el-input>
            </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
            <el-button @click="addDisVisible = false">取 消</el-button>
            <el-button type="primary" @click="addType">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 分页 -->
    <el-pagination
      style="margin-left:20%"
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
                  dialogVisible:false,
                  addDisVisible:false,
                  typeData:[],
                  currentPage:1,
                  pageSize:5,
                  totals:0,
                  updateForm:{},
                  addTypeName:''
            }
      },
      created(){
            this.getAllType();
      },
      methods:{
            getAllType(){
                  let page="currentPage="+this.currentPage+"&pageSize="+this.pageSize;
                  this.$http.get("/admin/FindAllAreaTypeByPage?"+page).then((result) => {
                        // console.log(result);
                        if(result.data.state){
                              this.typeData=result.data.typeList;
                              this.totals=result.data.total;
                        }else{
                              this.$message.error(result.data.msg);
                        }
                  }).catch((err) => {
                        this.$message.error(err.data.msg);
                  });
            },
            handleSizeChange(val) {
                  //console.log(`每页 ${val} 条`);
                  this.pageSize=val;
                  this.getAllType();
            },
            handleCurrentChange(val) {
                  //console.log(`当前页: ${val}`);
                  this.currentPage=val;
                  this.getAllType();
            },
            updateDialog(row){
                  this.updateForm=row;
                  this.dialogVisible=true;
            },
            updateType(){
                  this.$http.post("/admin/UpdateTypeName",this.updateForm).then((res) => {
                        if(res.data.state){
                              this.$message.success(res.data.msg);
                              this.dialogVisible=false;
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err) => {
                        this.$message.error(err);
                  });

            },
            delType(row){
                  this.$confirm('确定删除该分类?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                        }).then(() => {
                              this.$http.post("/admin/DelAreaType?typeId="+row).then((res) => {
                                    if(res.data.state){
                                          this.$message.success(res.data.msg);
                                          this.getAllType();
                                    }else{
                                          this.$message.error(res.data.msg);
                                    }
                              }).catch((err) => {
                                    
                              });
                        }).catch(() => {
                              this.$message.info("已取消删除");        
                  });
            },
            // 添加
            addTypeDia(){
                  this.addDisVisible=true;
            },
            addType(){
                  this.$http.post("/admin/AddTypeName?typeName="+this.addTypeName).then((res)=>{
                        if(res.data.state){
                              this.getAllType();
                              this.$message.success(res.data.msg);
                              this.addDisVisible=false;
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err)=>{
                        this.$message.error(err.data);
                  });
            }
      }

}
</script>

<style>

</style>