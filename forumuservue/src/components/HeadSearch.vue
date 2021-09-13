<template>
  <div id="souBox">
        <div class="top">
              <img src="../assets/images/lunLog.jpg" alt="" width="150" height="90">
              <el-input v-model="forumPage.forumTitle" @clear="clearTitle()" type="text" clearable class="s_query" placeholder="请输入帖子标题"/>
              <input type="button" value="贴吧搜索" class="btn" @click="searchByTitle">
        </div>
        <div class="foot">
            <el-carousel>
                  <el-carousel-item v-for="item in lunbo" :key="item.id">
                        <img :src="item.url" alt="背景" width="100%" height="100%">
                  </el-carousel-item>
            </el-carousel>
        </div>
  </div>
</template>

<script>
export default {
      name:'souBox',
      data(){
            return{
                  lunbo:[
                        {id:'1',url:require('../assets/images/l1.png')},
                        {id:'2',url:require('../assets/images/l2.jpg')},
                        {id:'3',url:require('../assets/images/l3.jpg')},
                        {id:'4',url:require('../assets/images/l4.jpg')}
                  ],
                  forumPage:{
                        currentPage:1,
                        pageSize:20,
                        forumTitle:'',
                  }
            }
      },
      methods:{
            searchByTitle(){
                  this.$http.post("/FindForumByPage",this.forumPage).then((res) => {
                        if(res.data.state){
                              let forumList=res.data.forumList;
                              let totals=res.data.total;
                              this.$emit("forumByTitle",forumList);
                              this.$emit("forumByTotal",totals);
                        }
                        this.$emit("forumMsg",res.data.msg);
                  }).catch((err) => {
                        this.$message.error(err.data);
                  });
            },
            clearTitle(){
                  this.searchByTitle();
            }
            
      }

}
</script>

<style scope>
.top > img{
      position: absolute;
      top: 60px;
      left: 20%;
      /* border-radius: 20px; */
      /* margin-left: 20%; */
}
.s_query{
      width: 560px;
      margin-left: 35%;
      margin-top: 30px;  
      padding-left: 20px;
}
.top .el-input__inner{
      height: 46px;
      border-radius: 10px 0 0 10px;
      border: 2px solid #C4C7CE;
      font-size: 17px;
}
.top .el-input__inner:focus{
      outline: none;
      border: 2px solid #4E6EF2;
}
.btn{
      width: 112px;
      height: 46px;
      position: absolute;
      margin-left: -5px;
      background-color: #4E6EF2;
      border-radius: 0 10px 10px 0;
      color: #fff;
      border: 0;
      font-size: 17px;
      cursor: pointer;
      margin-top: 30px;
}
.foot{
      width: 60%;
      /* height: 400px; */
      margin: 0 auto;
      margin-top: 40px;
}
.foot > .el-carousel{
      width: 100%;
      height: 100%;
}
.el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

</style>