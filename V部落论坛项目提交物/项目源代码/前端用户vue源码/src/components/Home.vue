<template>
  <div id="box">
        <el-backtop :bottom="100"></el-backtop>
        <div class="nav">
              <ul>
                    <li v-if="see"><a href="javascript:;" @click="toLogin()"><i class="el-icon-user-solid"></i>登录</a></li>
                    <li v-if="seen">
                          <a href="javascript:;">
                                <i class="el-icon-user-solid"></i>欢迎{{nickName}}登录
                          </a>
                    </li>
                    <li><a href="javascript:;" @click="toLogin()"><i class="el-icon-user"></i>注册</a></li>
                    <li><a href="javascript:;" @click="selfCenter()"><i class="el-icon-s-custom"></i>个人中心</a></li>
                    <li class="net"><a href="#">网页</a></li>
                    <li><a href="#">资讯</a></li>
                    <li><a href="#">贴吧</a></li>
                    <li><a href="#">视频</a></li>
                    <li><a href="#">音乐</a></li>
                    <li><a href="#">图片</a></li>
                    <li><a href="javascript:;" @click="toMessage"><i class="el-icon-message-solid"></i>消息中心</a></li>
                    <li v-if="outseen">
                        <a href="javascript:;" @click="logout">
                          <power theme="outline" size="16" fill="#4a4a4a"/>
                          退出
                        </a>
                    </li>
              </ul>
        </div>
        <div class="header">
              <HeadSearch @forumByTitle="getTitleData" @forumByTotal="getTotal" @forumMsg="getMsg"/>
        </div>
        <div class="content">
            <div class="typeul">
                  <ul>
                        <li v-for="(item,index) in typeList" :key="index">
                              <a href="javascript:;" @click="searchType(item.typeName)">{{item.typeName}}</a>
                        </li>
                        <li>
                              <el-popover
                                    placement="top-start"
                                    title="其他类型"
                                    width="400"
                                    trigger="hover"
                                    >
                                    <el-button v-for="(it,index) in otherTypeList" :key="index" @click="getOtherType(it)">{{it.typeName}}</el-button>
                                    <a href="javascript:;" slot="reference">更多分类>></a>
                              </el-popover>
                        </li>
                  </ul>
                    
            </div>
            <!-- 帖子模块 -->
            <div class="forumul">                 
                  <ul>
                        <li v-for="(f,index) in forumList" :key="index">
                              <img :src="`${baseUrl}/upload/${f.forumUser.userPic}`" @click="findInfo(f.forumUser.nickName)" alt="pic" title="查看当前贴主信息" class="curuser"/>
                              <span class="fc" @click="detailDiaForum(f)">主题:#{{f.title}}</span><br>
                              <span class="sc">
                                    <i class="el-icon-star-off"></i>
                                    <a href="javascript:;" @click="collectForum(f.fid,f.forumUser.userId)">收藏</a>
                              </span>
                              <span><i class="el-icon-timer"></i>发布时间:{{f.releaseTime}}</span><br>
                              <!-- <el-button @click="detailDiaForum(f)" type="primary" size="mini" slot="reference">详情</el-button> -->
                              
                              <span>发表评论(或上传图片):</span>
                              <input type="file" @change="setCommentFile" />
                              <el-input type="textarea" v-model="fabiao['comm'+index]" placeholder="最多输入150个字"></el-input>
                              <el-button @click="delForum(f.fid,f.forumUser.userId)" class="fabiao" type="danger" size="mini">删帖</el-button>
                              <el-button @click="sendComment(f.fid,index)" type="primary" size="mini">发表</el-button>
                              <el-popover
                                    placement="right"
                                    width="400"
                                    trigger="click">
                                    <ul>
                                          <li v-for="(c,index) in comList" :key="index" style="border-bottom:1px solid gray">
                                                <span>{{c.forumUser.nickName}}:&emsp;</span>
                                                <span v-if="c.comContent=='undefined'?false:true">{{c.comContent}}</span>
                                                <img v-if="c.comFile?true:false" :src="`${baseUrl}/upload/${c.comFile}`" width="30" height="30" style="border-radius:50%">
                                                &emsp;&emsp;&emsp;&emsp;
                                                <a style="font-size:13px" href="javascript:;" @click="jubaoDialogVisi(c.cid)">举报!</a>
                                          </li>
                                    </ul>                   
                                    <el-button class="getcom" size="small" slot="reference" @click="setComment(f.fid,f.forumUser.userId)">点击查看评论</el-button>
                              </el-popover><br>
                              <br><br><br>
                        </li>
                  </ul>
            </div>
            <!-- 帖子详情弹框 -->
            <el-dialog
                  title="帖子详情"
                  :visible.sync="fdetaildialogVisible"
                  width="50%">
                  <el-form :model="detailForm" label-width="80px">
                        <el-form-item label="帖子内容">
                              <el-input type="textarea" v-model="detailForm.content" readonly></el-input>                        
                        </el-form-item>
                        <el-form-item label="内容文件">
                             <img v-if="detailForm.uploadFile===undefined?false:true" :src="`${baseUrl}/upload/${detailForm.uploadFile}`" width="150" height="100" />
                        </el-form-item>
                        <el-form-item label="帖子分类">
                              <el-input v-model="detailForm.areaType.typeName" readonly></el-input>
                        </el-form-item>
                        <el-form-item label="帖子博主">
                              <el-input v-model="detailForm.forumUser.nickName" readonly></el-input>
                        </el-form-item>
                  </el-form>
                  <span slot="footer" class="dialog-footer">
                  <el-button @click="fdetaildialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="fdetaildialogVisible = false">确 定</el-button>
                  </span>
            </el-dialog>
            <!-- 举报弹窗 -->
            <el-dialog
                  title="举报信息"
                  :visible.sync="jubaoDialog"
                  width="30%">
                  <el-form :model="reportForm" :rules="reportRules" ref="reportForm" label-width="100px" class="demo-ruleForm">
                        <el-form-item label="举报人" prop="nickName">
                              <el-input v-model="reportForm.nickName" readonly></el-input>
                        </el-form-item>
                        <el-form-item label="举报理由" prop="repContent">
                              <el-input v-model="reportForm.repContent" placeholder="3~50个字符"></el-input>
                        </el-form-item>
                  </el-form>
                  <span slot="footer" class="dialog-footer">
                  <el-button @click="jubaoDialog = false">取 消</el-button>
                  <el-button type="primary" @click="jubaoToAdmin('reportForm')">确 定</el-button>
                  </span>
            </el-dialog>
            <el-pagination
                  background
                  @current-change="handleCurrentChange"
                  :current-page="searchForum.currentPage"
                  :page-size="searchForum.pageSize"
                  layout="prev, pager, next"
                  :total="totals">
            </el-pagination>
        </div>

      <!-- 发帖 -->
        <div class="fatie">
            <el-form :model="ForumForm" :rules="ForumRules" ref="ForumForm" label-width="100px" class="demo-ruleForm">
                  <el-form-item label="帖子标题" prop="title">
                        <el-input v-model="ForumForm.title" placeholder="#话题"></el-input>
                  </el-form-item>
                  <el-form-item label="帖子内容" prop="content">
                        <el-input type="textarea" v-model="ForumForm.content" placeholder="请输入有效字符"></el-input>
                  </el-form-item>
                  <el-form-item label="所属科目" prop="typeId">
                        <el-select v-model="ForumForm.typeId" placeholder="请选择吧帖类型">
                              <el-option v-for="item in allTypeList" :label="item.typeName" :value="item.typeId" :key="item.typeId"></el-option>
                        </el-select>
                  </el-form-item>
                  <el-form-item label="上传图片" prop="uploadFile">
                        <input type="file" @change="getForumFile"></el-input>
                  </el-form-item>
                  <el-form-item label="发帖时间" prop="releaseTime">
                        <el-date-picker
                              v-model="ForumForm.releaseTime"
                              format="yyyy-MM-dd HH:mm:ss"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              type="datetime"
                              placeholder="选择发帖日期时间">
                        </el-date-picker>
                  </el-form-item>
                  <el-form-item>
                        <el-button @click="sendForum('ForumForm')" type="primary" size="small">发表帖子</el-button>
                        <el-button @click="resetForm('ForumForm')" type="primary" size="small">重置</el-button>
                  </el-form-item>
            </el-form>
        </div>
        <div class="footer">
              <p>
                    &copy;2021&emsp;贴吧协议&emsp;|&emsp;隐私政策&emsp;|&emsp;吧主制度&emsp;|&emsp;意见反馈&emsp;|&emsp;网络谣言警示
              </p>
        </div>
  </div>
</template>

<script>
import HeadSearch from '@/components/HeadSearch.vue';
import {Power} from '@icon-park/vue';
let padaDate=function(value){
    return value<10 ? "0"+value:value;
}
export default {
      name:'box',
      components:{
            Power,
            "HeadSearch":HeadSearch
      },
      data(){
            return{
                  baseUrl:this.$http.defaults.baseURL,
                  //登陆退出
                  see:'',
                  seen:'',
                  outseen:'',
                  nickName:'',
                  searchForum:{
                        currentPage:1,
                        pageSize:20,
                        forumTitle:'',
                        typeName:''
                  },
                  forumList:[],
                  totals:0,
                  allTypeList:[],
                  typeList:[],
                  otherTypeList:[],
                  //发表评论
                  fabiao:{},
                  //发表帖子
                  ForumForm:{},
                  //发帖文件
                  faTieFile:'',
                  //评论图片
                  commentFile:'',
                  //评论列表
                  comList:[],
                  //验证发帖表单
                  ForumRules:{
                        title: [
                              { required: true, message: '请输入帖子标题', trigger: 'blur' },
                              { min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur' }
                        ],
                        content:[
                              { required: true, message: '请输入帖子内容', trigger: 'blur' }
                        ],
                        typeId:[{ required: true, message: '请选择所属科目', trigger: 'blur' }],
                        releaseTime:[{ required: true, message: '请选择发帖时间', trigger: 'blur' }]
                  },
                  //举报评论
                  jubaoDialog:false,
                  //帖子详情
                  fdetaildialogVisible:false,
                  detailForm:{
                        areaType:{
                              typeName:''
                        },
                        forumUser:{
                              nickName:''
                        }
                  },
                  reportForm:{
                        cid:'',
                        nickName:'',
                        userId:'',
                        repContent:''
                  },
                  //举报表单验证
                  reportRules:{
                        repContent: [
                              { required: true, message: '请输入举报理由', trigger: 'blur' },
                              { min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur' }
                        ]
                  },
                  //组件传值
                  oldList:[]

            }
      },
      created(){
            this.getAllForum();
            this.getAllType();
            let user=JSON.parse(sessionStorage.getItem("user"));
            if(user != null){
                  this.see=false;
                  this.seen=true;
                  this.outseen=true;
                  this.nickName=user.nickName;
                  this.reportForm.nickName=user.nickName;
                  this.reportForm.userId=user.userId;
            }else{
                  this.see=true;
                  this.seen=false;
                  this.outseen=false;
            }           
      },
      methods:{
            //获取所有帖子内容
            getAllForum(){
                 this.$http.post("/FindForumByPage",this.searchForum).then((res) => {
                        // console.log(res.data);
                        this.forumList=[];
                        if(res.data.state){
                              for(let i=0;i<res.data.forumList.length;i++){
                                    if(res.data.forumList[i].state==='通过审核'){
                                          this.forumList.push(res.data.forumList[i]);
                                          this.totals=res.data.total;
                                    }else if(res.data.forumList[i].state==='审核未通过'){
                                          // let  oldList=[];                                       
                                          this.oldList.push(res.data.forumList[i]);
                                    }
                              }
                                                  
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err) => {
                        this.$message.error(err);
                  });
            },
            //分页
            handleCurrentChange(val) {
                  //console.log(`当前页: ${val}`);
                  this.searchForum.currentPage=val;
                  this.getAllForum();
            },
            //获取所有分类
            getAllType(){
                 this.$http.post("/FindAllTypeByUser").then((res) => {
                        if(res.data.state){
                              let result=res.data.typeList;
                              this.allTypeList=result;
                              for(let i=0;i<result.length;i++){
                                    if(i<7){
                                          this.typeList.push(result[i]);
                                          
                                    }else{
                                          this.otherTypeList.push(result[i]);
                                          
                                    }
                              }
                        }else{
                              this.$message.error(res.data.msg);
                        }
                  }).catch((err) => {
                        this.$message.error(err.data);
                  }); 
            },
            //登录
            toLogin(){
                  this.$router.push('/login');
            },
            //退出
            logout(){
                  sessionStorage.clear();
                  this.see=true;
                  this.seen=false;
                  this.outseen=false;
                  // this.$router.push("/");
            },
            //个人中心
            selfCenter(){
                  let u=JSON.parse(sessionStorage.getItem("user"));
                  if(u!=null){
                        this.$router.push("/userinfo");
                  }else{
                        this.$message.error("请先登录");
                  }
                  
            },
            //emit接收数据
            //根据帖子标题查询的数据列表
            getTitleData(val){
                  this.forumList=val;
            },
            //总数
            getTotal(val){
                  this.totals=val;
            },
            //提示消息
            getMsg(val){
                  this.$message.info(val);
            },
            //根据分类查询
            searchType(typeName){
                  this.searchForum.typeName=typeName;
                  this.forumList=[];
                  this.getAllForum();
            },
            //更多分类
            getOtherType(val){
                  this.searchForum.typeName=val.typeName;
                  this.forumList=[];
                  this.getAllForum();
            },
            //发帖上传图片
            getForumFile(event){
                  this.faTieFile=event.target.files[0];
            },
            //发表帖子
            sendForum(ForumForm){
                  let user=JSON.parse(sessionStorage.getItem("user"));
                  this.$refs[ForumForm].validate((valid) => {
                        if (valid) {                             
                              if(user!=null){
                                    let formData = new FormData(); //获取表单数据
                                    let re = this.ForumForm;//书籍对象
                                    Object.keys(re).forEach((key) => {
                                          formData.append(key, re[key]);
                                    });
                                    formData.append("state","通过审核");
                                    formData.append("userId",user.userId);
                                    formData.append("uploadFile", this.faTieFile);
                                    this.$http.post("/admin/AddForum",formData,{
                                          Headers:{'content-type': 'multipart/form-data'}
                                    }).then((res)=>{
                                          if(res.data.state){                                              
                                                this.searchForum.typeName="";
                                                this.getAllForum();
                                                this.ForumForm={};
                                                this.faTieFile="";
                                                this.$message.success(res.data.msg);
                                          }else{
                                                this.$message.error(res.data.msg);
                                          }
                                    })
                              }else{
                                    this.$message.error("请先登陆");
                              }
                        } else {
                              console.log('error submit!!');
                              return false;
                        }
                  });
            },
            //发帖重置
            resetForm(ForumForm){
                  this.$refs[ForumForm].resetFields();
            },
            //发表评论上传图片
            setCommentFile(event){
                  this.commentFile=event.target.files[0];
                  // console.log(this.commentFile);
            },
            //查询评论
            getComment(fid){
                  this.$http.get("/FindCommentByUserId?fid="+fid).then((res)=>{
                        if(res.data.state){
                              this.comList=res.data.comList;
                              // console.log(this.comList);
                        }else{
                              this.comList={};
                              this.$message.info(res.data.msg);
                        }
                  })
            },
            //点击获取评论
            setComment(fid){
                  let user=JSON.parse(sessionStorage.getItem("user"));
                  if(user!=null){
                        this.getComment(fid);
                        // alert(fid+"--"+uid);
                  }else{
                        this.$message.error("请先登录");
                  }
                  
            },
            //获取当前日期时间
            timefilter(datetime){
                  let year=datetime.getFullYear();
                  let month=padaDate(datetime.getMonth()+1);
                  let date=padaDate(datetime.getDate());
                  let h=padaDate(datetime.getHours());
                  let m=padaDate(datetime.getMinutes());
                  let s=padaDate(datetime.getSeconds());
                  let result=`${year}年${month}月${date}日 ${h}:${m}:${s}`;
                  return result;
            },
            //发表评论
            sendComment(fid,index){
                  let content=this.fabiao['comm'+index];
                  let userId=JSON.parse(sessionStorage.getItem("user")).userId;
                  //只发表图片或内容或两者同时
                  if(content==null || content.length<=150){
                        let time=this.timefilter(new Date())
                        let currComment={fid:fid,userId:userId,comContent:content,comTime:time};
                        // alert(JSON.stringify(currComment));
                        let fd =new FormData();
                        let re = currComment;//评论对象
                        Object.keys(re).forEach((key) => {
                              fd.append(key, re[key]);
                        });
                        fd.append("comentFile",this.commentFile);
                        this.$http.post("/admin/AddComment",fd,{
                              Headers:{'content-type': 'multipart/form-data'}
                        }).then((res)=>{
                              // console.log(res.data);
                              if(res.data.state){                           
                                    this.getComment(fid);
                                    this.fabiao={};
                                    this.commentFile="";
                                    this.$message.success(res.data.msg);
                              }else{
                                    this.$message.error(res.data.msg);
                              }
                        });
                  }else{
                        this.$message.error("输入内容过长");
                  }
                  
            },
            //删除帖子
            delForum(fid,userId){
                  let user=JSON.parse(sessionStorage.getItem("user"));
                  if(user!=null){
                        if(user.userId===userId){
                              this.$confirm("确定删除该贴?",'提示').then(()=>{
                                    this.$http.post("/admin/DelForumByUser?fid="+fid).then((res)=>{
                                          if(res.data.state){
                                                this.$message.success(res.data.msg);
                                                this.getAllForum();
                                          }else{
                                                this.$message.error(res.data.msg);
                                          }
                                    })
                              }).catch((err)=>{
                                    this.$message.info("已取消删除");
                              });
                        }else{
                              this.$message.error("权限不足,无法操作");
                        }
                  }else{
                        this.$message.error("请先登录")
                  }
                  
            },
            //收藏
            collectForum(fid,userId){
                  let u=JSON.parse(sessionStorage.getItem("user"));
                  if(u!=null){
                        let collect={
                              fid:fid,
                              userId:u.userId,
                              collTime:this.timefilter(new Date())
                        };
                        this.$http.post("/admin/AddCollectForum",collect).then((res)=>{
                              if(res.data.state){
                                    this.$message.success(res.data.msg);
                              }else{
                                    this.$message.error(res.data.msg);
                              }
                        }).catch((err)=>{
                              this.$message.error(err.data);
                        });
                  }else{
                        this.$message.error("请先登录");
                  }
            },
            //举报弹窗
            jubaoDialogVisi(cid){
                  this.reportForm.cid=cid;
                  this.jubaoDialog=true;
            },
            //举报
            jubaoToAdmin(reportForm){
                  this.$refs[reportForm].validate((valid) => {
                        if (valid) {
                              let rep="cid="+this.reportForm.cid+"&userId="+this.reportForm.userId+"&repContent="+this.reportForm.repContent;
                              this.$http.get("/admin/AddReportComment?"+rep).then((res)=>{
                                    console.log(res.data);
                                    if(res.data.state){
                                          this.$message.success(res.data.msg);
                                          this.reportForm.repContent="";
                                          this.jubaoDialog=false;
                                    }else{
                                         this.$message.error(res.data.msg);
                                         this.reportForm.repContent="";
                                          this.jubaoDialog=false; 
                                    }
                              })
                        } else {
                              console.log('error submit!!');
                              return false;
                        }
                  });
            },
            //点击帖子头像查看个人信息
            findInfo(nickName){
                  this.$notify({
                        title: '昵称:'+nickName,
                        dangerouslyUseHTMLString: true, //支持html代码
                        message: "<strong>用户状态:<span style='color:#67C23A'>正常</span></strong>"
                  });
            },
            //消息中心
            toMessage(){
                  this.$router.push({path:'/sysmessage',query:{oldList:JSON.stringify(this.oldList)}});
            },
            //帖子详情弹框
            detailDiaForum(row){
                  this.detailForm=row;
                  this.fdetaildialogVisible=true;
            }

      }

}
</script>

<style scope>
@import url('../assets/css/home.css');

</style>