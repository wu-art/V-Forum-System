import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import UserInfo from '@/components/UserInfo'
import ForumColle from '@/components/ForumColle'
import ReviceMessage from '@/components/ReviceMessage'


Vue.use(Router)

export default new Router({
  routes: [
    {path: '/',name: 'Home',component: Home},
    {path:'/login',name:'Login',component:Login},
    {path:'/userinfo',name:'UserInfo',component:UserInfo},
    {path:'/usercollectforum',name:'ForumColle',component:ForumColle},
    {path:'/sysmessage',name:'ReviceMessage',component:ReviceMessage}
  ]
})
