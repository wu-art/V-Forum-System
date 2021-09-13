<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <br>
    <!-- 数据 -->
    <el-table :data="userData" border style="width: 100%">
      <el-table-column type="index" label="#" width="70">
      </el-table-column>
      <el-table-column prop="userPic" label="头像" width="180">
            <template slot-scope="scope">
                  <el-avatar :src="`${baseUrl}/upload/${scope.row.userPic}`" :size="40"></el-avatar>
            </template>
      </el-table-column>
      <el-table-column prop="userId" label="账号" width="170">
      </el-table-column>
      <el-table-column prop="nickName" label="昵称" width="180">
      </el-table-column>
      <el-table-column prop="tel" label="电话" width="170">
      </el-table-column>
      <el-table-column prop="registerTime" label="注册日期" width="199">
      </el-table-column>
      <el-table-column prop="userState" label="状态" width="100">
            <template slot-scope="scope">
                  <el-switch
                  v-model="scope.row.userState"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  :active-value="1"
                  :inactive-value="0"
                  @change="changeSwitch(scope.row)">
            </el-switch>
            </template>
      </el-table-column>
      <el-table-column label="操作" width="170">
        <template slot-scope="scope">
          <el-button @click="updateDialog(scope.row)" size="small" type="primary">修改</el-button>
          <el-button @click="delUser(scope.row.userId)" size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹框 -->
    <el-dialog
      title="修改用户信息"
      :visible.sync="dialogVisible"
      width="30%"
      >
      <el-form label-width="50px" :model="updateForm">
            <el-form-item label="账户">
                  <el-input v-model="updateForm.userId" readonly></el-input>
            </el-form-item>
            <el-form-item label="电话">
                  <el-input v-model="updateForm.tel"></el-input>
            </el-form-item>
            <el-form-item label="昵称">
                  <el-input v-model="updateForm.nickName"></el-input>
            </el-form-item>
                  <!-- <el-input v-model="updateForm.userState"></el-input> -->
                  <!-- <el-select v-model="updateForm.userState" placeholder="选择状态">
                        <el-option label="正常" value="1"></el-option>
                        <el-option label="封禁" value="0"></el-option>
                  </el-select> -->
      </el-form>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="updateUser">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 分页 -->
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5, 6, 7, 8]"
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
                  baseUrl:this.$http.defaults.baseURL,
                  userData:[],
                  currentPage:1,
                  pageSize:6,
                  totals:0,
                  value:"",
                  updateForm:{},
                  dialogVisible: false
            }
      },
      created(){
            this.getUserList();
      },
      methods:{
            getUserList(){
                  let page="currentPage="+this.currentPage+"&pageSize="+this.pageSize;
                  this.$http.get("/admin/FindAllUser?"+page).then((result) => {
                        // console.log(result.data);
                        if(result.data.state){
                              this.userData=result.data.userList;
                              this.totals=result.data.totals;
                        }else{
                              this.$message.error(result.data.msg);
                        }
                  }).catch((err) => {
                        
                  });
            },
            handleSizeChange(val) {
                  console.log(`每页 ${val} 条`);
                  this.pageSize=val;
                  this.getUserList();
            },
            handleCurrentChange(val) {
                  console.log(`当前页: ${val}`);
                  this.currentPage=val;
                  this.getUserList();
            },
            changeSwitch(row){
                  this.$http.post("/admin/UpdateUserState?userId="+row.userId+"&userState="+row.userState).then((res) => {
                        if(res.data.state){
                              this.$message.success(res.data.msg);
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err) => {
                        this.$message.error(err);
                  });

            },
            // 修改用户信息
            updateDialog(row){
                  this.updateForm=row;
                  this.dialogVisible=true;
            },
            updateUser(){
                  this.$http.post("/admin/UpdateForumUser",this.updateForm).then((res) => {
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
            //删除
            delUser(userId){
                  this.$confirm('确定删除该用户?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                        }).then(() => {
                              this.$http.post("/admin/DelForumUser?userId="+userId).then((res) => {
                                    if(res.data.state){
                                          this.$message.success(res.data.msg);
                                          this.getUserList();
                                    }else{
                                          this.$message.error(res.data.msg);
                                    }
                              }).catch((err) => {
                                    
                              });
                        }).catch(() => {
                              this.$message.info(已取消删除);        
                  });
            }

      }
};
</script>

<style>
</style>