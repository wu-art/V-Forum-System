<template>
  <div id="colbox">
        <el-card class="box-card">
            <span>当前用户:{{nickName}}</span>
            <span><a href="javascript:;" @click="toSelfInfo">个人中心</a></span>
            <span><a href="javascript:;" @click="toHome">首页</a></span>
            <p>欢迎访问个人贴吧圈</p>
            <ul class="col">
                  <li v-for="item in collectList">
                        <el-alert                      
                        type="success"
                        :closable="false"
                        >
                        <img :src="`${baseUrl}/upload/${item.userPic}`" alt="" width="60" height="60">
                        <span class="one">作者:{{item.nickName}}|</span>
                        <span class="one">标题:{{item.title}}|</span>
                        <span class="one">分类:{{item.typeName}}|</span>
                        <span class="one">收藏于:{{item.collTime}}</span>
                        <el-button @click="delCollect(item.cid)" type="danger" size="mini">删除</el-button>
                        </el-alert>
                  </li>
            </ul>
            <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="currentPage"
                  :page-sizes="[10, 20, 30, 40]"
                  :page-size="pageSize"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="totals">
            </el-pagination>
        </el-card>
  </div>
</template>

<script>
export default {
      data(){
            return{
                  baseUrl:this.$http.defaults.baseURL,
                  nickName:'',
                  userId:'',
                  totals:0,
                  currentPage:1,
                  pageSize:10,
                  collectList:[]
            }
      },
      created(){
            let user=JSON.parse(sessionStorage.getItem("user"));
            this.nickName=user.nickName;
            this.userId=user.userId;
            this.getCollectList();
      },
      methods:{
            //获取收藏列表
            getCollectList(){
                  let pp="currentPage="+this.currentPage+"&pageSize="+this.pageSize+"&userId="+this.userId;
                  this.$http.get("/admin/FindCollectByUser?"+pp).then((res) => {
                        // console.log(res.data);
                        if(res.data.state){                             
                              this.collectList=res.data.colList;
                              this.totals=res.data.totals;
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err) => {
                        this.$message.error(err.data);
                  });
            },
            handleSizeChange(val) {
                  // console.log(`每页 ${val} 条`);
                  this.pageSize=val;
                  this.getCollectList();
            },
            handleCurrentChange(val) {
                  // console.log(`当前页: ${val}`);
                  this.currentPage=val;
                  this.getCollectList();
            },
            toSelfInfo(){
                  this.$router.push("/userinfo");
            },
            toHome(){
                  this.$router.push("/");
            },
            //删除收藏
            delCollect(cid){
                  this.$confirm("确定删除?").then(()=>{
                        this.$http.get("/admin/DelCollect?cid="+cid).then((res)=>{
                              if(res.data.state){
                                    this.$message.success(res.data.msg);
                                    this.getCollectList();
                              }else{
                                    this.$message.error(res.data.msg);
                              }
                        })
                  }).catch(()=>{
                        this.$message.info("已取消删除");
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
.box-card ul.col{
      list-style-type: none;
      margin-left: -40px;
}
.box-card ul.col li{
      margin-top: 20px;
}
.box-card ul.col li span.one{
      position: relative;
      top: -25px;
      font-size: 15px;
}
</style>