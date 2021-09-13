import Vue from 'vue'
import Router from 'vue-router'
import AdminLogin from '@/components/AdminLogin'
import Index from '@/components/Index' 
import UserList from '@/components/UserList'
import KindInfo from '@/components/KindInfo'
import MsgList from '@/components/MsgList'
import AddMsg from '@/components/AddMsg'
import ForumInfo from '@/components/ForumInfo'
import ReportMsg from '@/components/ReportMsg'
import SysConfig from '@/components/SysConfig'
import Welcome from '@/components/Welcome'

Vue.use(Router)

export default new Router({
  routes: [
    // {path: '/Welcome',name: 'Welcome',component: Welcome},
    {path: '/',name: 'AdminLogin',component: AdminLogin},
    {path: '/index',name: 'Index',component: Index,
      redirect:'/Welcome',
      children:[
        {path: '/Welcome',name: 'Welcome',component: Welcome},
        {path: '/userlist',name: 'UserList',component: UserList},
        {path: '/KindInfo',name: 'KindInfo',component: KindInfo},
        {path: '/MsgList',name: 'MsgList',component: MsgList},
        {path: '/AddMsg',name: 'AddMsg',component: AddMsg},
        {path: '/ForumInfo',name: 'ForumInfo',component: ForumInfo},
        {path: '/ReportMsg',name: 'ReportMsg',component: ReportMsg},
        {path: '/SysConfig',name: 'SysConfig',component: SysConfig}
      ]
    },
  ]
})
