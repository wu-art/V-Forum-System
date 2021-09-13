prompt PL/SQL Developer import file
prompt Created on 2021年8月29日 by lenovo
set feedback off
set define off
prompt Creating ADMIN...
create table ADMIN
(
  loginid  VARCHAR2(45) not null,
  loginpwd VARCHAR2(45) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMIN
  add primary key (LOGINID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating ADMINMESSAGE...
create table ADMINMESSAGE
(
  amid        NUMBER(20) not null,
  loginid     VARCHAR2(45) not null,
  userid      VARCHAR2(45) not null,
  mes_content VARCHAR2(255),
  msgtime     VARCHAR2(45)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ADMINMESSAGE
  add primary key (AMID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating AREATYPE...
create table AREATYPE
(
  typeid   NUMBER(20) not null,
  typename VARCHAR2(45)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table AREATYPE
  add primary key (TYPEID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating COLLECTION...
create table COLLECTION
(
  col_id   NUMBER(20) not null,
  fid      NUMBER(20) not null,
  userid   VARCHAR2(45) not null,
  colltime VARCHAR2(45)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table COLLECTION
  add primary key (COL_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating COMENT...
create table COMENT
(
  cid         NUMBER(20) not null,
  fid         NUMBER(20) not null,
  userid      VARCHAR2(45) not null,
  com_content VARCHAR2(45),
  com_time    VARCHAR2(45),
  comfile     VARCHAR2(45)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table COMENT
  add primary key (CID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating FORUM...
create table FORUM
(
  fid          NUMBER(20) not null,
  title        VARCHAR2(255),
  userid       VARCHAR2(45) not null,
  typeid       NUMBER(20) not null,
  release_time VARCHAR2(45),
  content      VARCHAR2(255),
  state        VARCHAR2(45),
  uploadfile   VARCHAR2(45)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table FORUM
  add primary key (FID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating FORUM_USER...
create table FORUM_USER
(
  userid     VARCHAR2(45) not null,
  userpwd    VARCHAR2(45) not null,
  nickname   VARCHAR2(45),
  tel        VARCHAR2(45),
  userpic    VARCHAR2(45),
  regis_time DATE,
  user_state NUMBER(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table FORUM_USER
  add primary key (USERID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating REPORT...
create table REPORT
(
  rid         NUMBER(20) not null,
  loginid     VARCHAR2(45) not null,
  userid      VARCHAR2(45) not null,
  cid         NUMBER(20) not null,
  rep_content VARCHAR2(255),
  repstate    NUMBER(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table REPORT
  add primary key (RID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating USERMESSAGE...
create table USERMESSAGE
(
  umid        NUMBER(20) not null,
  loginid     VARCHAR2(45) not null,
  userid      VARCHAR2(45) not null,
  mes_content VARCHAR2(255),
  msgtime     VARCHAR2(45)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table USERMESSAGE
  add primary key (UMID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for ADMIN...
alter table ADMIN disable all triggers;
prompt Disabling triggers for ADMINMESSAGE...
alter table ADMINMESSAGE disable all triggers;
prompt Disabling triggers for AREATYPE...
alter table AREATYPE disable all triggers;
prompt Disabling triggers for COLLECTION...
alter table COLLECTION disable all triggers;
prompt Disabling triggers for COMENT...
alter table COMENT disable all triggers;
prompt Disabling triggers for FORUM...
alter table FORUM disable all triggers;
prompt Disabling triggers for FORUM_USER...
alter table FORUM_USER disable all triggers;
prompt Disabling triggers for REPORT...
alter table REPORT disable all triggers;
prompt Disabling triggers for USERMESSAGE...
alter table USERMESSAGE disable all triggers;
prompt Deleting USERMESSAGE...
delete from USERMESSAGE;
commit;
prompt Deleting REPORT...
delete from REPORT;
commit;
prompt Deleting FORUM_USER...
delete from FORUM_USER;
commit;
prompt Deleting FORUM...
delete from FORUM;
commit;
prompt Deleting COMENT...
delete from COMENT;
commit;
prompt Deleting COLLECTION...
delete from COLLECTION;
commit;
prompt Deleting AREATYPE...
delete from AREATYPE;
commit;
prompt Deleting ADMINMESSAGE...
delete from ADMINMESSAGE;
commit;
prompt Deleting ADMIN...
delete from ADMIN;
commit;
prompt Loading ADMIN...
insert into ADMIN (loginid, loginpwd)
values ('admin', '123456');
commit;
prompt 1 records loaded
prompt Loading ADMINMESSAGE...
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (86, 'admin', 'qqw', '西游记86版本相当好看', '2021-08-20 16:14:54');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (160, 'admin', 'aaa', '测试', '2021-08-26 15:47:03');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (90, 'admin', 'zzz', '我们都有一个家，名字叫中国', '2021-08-20 16:15:45');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (92, 'admin', 'aaa', '水浒传是英雄好汉看的电视剧', '2021-08-20 16:16:14');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (83, 'admin', 'zzz', '世上再无阿富汗', '2021-08-19 21:39:55');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (78, 'admin', '小小', '今天得西藏很精彩，沃恩很山东省', '2021-08-19 21:15:29');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (81, 'admin', 'qqw', '金泰你的世界的巨变的wooded事物是的是的', '2021-08-24 01:10:07');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (88, 'admin', '小小', '红楼们林黛玉很可怜', '2021-08-20 16:15:24');
commit;
prompt 8 records loaded
prompt Loading AREATYPE...
insert into AREATYPE (typeid, typename)
values (6, 'java全栈技术');
insert into AREATYPE (typeid, typename)
values (7, 'C++技术栈');
insert into AREATYPE (typeid, typename)
values (8, 'python社区');
insert into AREATYPE (typeid, typename)
values (9, 'PHP技术');
insert into AREATYPE (typeid, typename)
values (10, 'HTML社区');
insert into AREATYPE (typeid, typename)
values (11, 'CSS页面美化');
insert into AREATYPE (typeid, typename)
values (14, 'JS论坛');
insert into AREATYPE (typeid, typename)
values (129, 'MySql数据库');
insert into AREATYPE (typeid, typename)
values (128, 'Oracle数据库');
insert into AREATYPE (typeid, typename)
values (130, 'vue2.0社区');
insert into AREATYPE (typeid, typename)
values (159, '测试');
commit;
prompt 11 records loaded
prompt Loading COLLECTION...
insert into COLLECTION (col_id, fid, userid, colltime)
values (68, 26, 'qqw', '2021年08月19日 11:06:31');
insert into COLLECTION (col_id, fid, userid, colltime)
values (153, 131, 'qqw', '2021年08月26日 15:38:39');
insert into COLLECTION (col_id, fid, userid, colltime)
values (74, 18, 'qqw', '2021年08月19日 14:17:46');
insert into COLLECTION (col_id, fid, userid, colltime)
values (67, 27, 'qqw', '2021年08月19日 10:57:40');
commit;
prompt 4 records loaded
prompt Loading COMENT...
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (1, 11, '小小', '很美好，good!', '2021-08-16 15:23:30', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (2, 12, '花花', '调控的很不错', '2001-09-29 15:23:30', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (22, 21, 'qqw', '我我是说', '2021年08月18日 10:48:16', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (23, 21, 'qqw', '学生形势下', '2021年08月18日 10:49:32', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (96, 1, 'aaa', '猴子三棒子敲死了一推！', '2021年08月22日 13:22:50', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (98, 18, 'qqw', '小猴子听调皮', '2021年08月22日 13:34:21', 'pic1629610461383.jpg');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (100, 26, 'qqw', '中华五千年的历史', '2021年08月22日 13:37:01', 'pic1629610621888.gif');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (105, 69, '小小', 'undefined', '2021年08月22日 13:55:12', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (106, 69, '小小', '我的名字不能匾额理论的', '2021年08月22日 14:00:51', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (108, 69, 'zzz', '251551551', '2021年08月22日 14:07:22', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (150, 127, 'qqw', '我们的时光！', '2021年08月25日 11:01:14', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (154, 127, 'qqw', '测试', '2021年08月26日 15:38:54', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (163, 127, '小小', 'azazazazaz', '2021年08月26日 16:04:47', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (16, 11, '小小', 'asaa中国', '2021年08月18日 09:43:58', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (102, 69, '小小', '来牛的孩子红花二恶', '2021年08月22日 13:49:46', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (103, 69, '小小', '铁扇公主的扇子太牛了', '2021年08月22日 13:50:47', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (109, 1, 'zzz', 'zenee26265', '2021年08月22日 14:11:19', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (143, 132, '小小', '不错，很好很好！', '2021年08月24日 10:08:02', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (155, 131, 'qqw', '我们', '2021年08月26日 15:39:54', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (164, 127, '小小', '嗡嗡嗡嗡嗡', '2021年08月26日 16:05:12', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (17, 12, '花花', '我的家在', '2021年08月18日 09:48:07', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (25, 19, 'qqw', '熊悟空', '2021年08月18日 11:24:09', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (75, 18, 'qqw', '垃圾', '2021年08月19日 14:18:35', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (99, 18, 'qqw', '小狗子的妈妈', '2021年08月22日 13:34:39', 'pic1629610479941.jpg');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (142, 132, 'zzz', '常用标点在html中应用很多！', '2021年08月24日 10:06:58', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (152, 127, 'qqw', 'undefined', '2021年08月25日 11:28:00', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (156, 127, 'qqw', 'undefined', '2021年08月26日 15:40:31', 'pic1629963631450.webp');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (101, 26, 'qqw', '6666', '2021年08月22日 13:37:11', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (95, 1, 'aaa', '我是小小，来看西游记！', '2021年08月22日 13:18:39', 'pic1629609519587.jpg');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (104, 69, '小小', '我的命i在被改了', '2021年08月22日 13:55:12', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (110, 18, 'zzz', 'swswsw4747', '2021年08月22日 14:11:33', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (151, 127, 'qqw', 'undefined', '2021年08月25日 11:27:53', 'pic1629862073403.jpg');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (158, 157, 'qqw', 'womnene', '2021年08月26日 15:41:19', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (24, 20, 'qqw', '我的函数', '2021年08月18日 11:14:32', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (107, 69, 'zzz', '德尔福空间的九分东方', '2021年08月22日 14:06:41', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (76, 18, 'qqw', '垃圾', '2021年08月19日 14:22:32', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (97, 1, 'aaa', 'undefined', '2021年08月22日 13:28:05', 'pic1629610085673.jpg');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (149, 148, '小小', '验证码存在session中！', '2021年08月25日 09:02:29', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (162, 131, 'aaa', '小小', '2021年08月26日 15:47:41', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (165, 135, 'aaa', 'tadegushi', '2021年08月26日 16:17:09', null);
commit;
prompt 40 records loaded
prompt Loading FORUM...
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (157, 'java', 'qqw', 6, '2021-08-26 15:41:08', 'javase', '通过审核', 'pic1629963669113.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (134, 'python是什么', 'zzz', 8, '2021-08-24 09:53:40', 'Python提供了高效的高级数据结构，还能简单有效地面向对象编程。Python语法和动态类型，以及解释型语言的本质，使它成为多数平台上写脚本和快速开发应用的编程语言；', '审核未通过', 'pic1629770021184.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (136, 'HTML新手入门', 'zzz', 10, '2021-08-24 09:57:44', 'html是一门超文本标记语言，适合新手小白学习，是前端必备的一项技能！', '通过审核', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (141, '前端主流框架Vue', 'zzz', 130, '2021-08-24 10:05:52', 'Vue (读音 /vju？/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。', '通过审核', 'pic1629770753386.gif');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (145, '前后台跨域怎么解决？', 'aaa', 6, '2021-08-25 08:45:54', '可以在后台项目中添加一个过滤器类，设置请求头，请求及响应的编码方式等；若是vue框架，在前端也设置跨域；', '通过审核', 'pic1629852356220.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (127, '#请问如何解决pycharm下无法建立python package的问题', 'zzz', 7, '2021-08-24 09:16:14', '活动日志里总是报错说是无法保存配置, 请重启pycharm, 但重启了也没有用. 这是在我配置python interpreter之后就一直报错.始终无法保存我的配置.', '通过审核', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (137, 'CSS设计', 'zzz', 11, '2021-08-24 10:00:37', 'css是页面美化的一种前端技术，也属于UI设计板块！', '通过审核', 'pic1629770443859.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (140, 'Oracle数据库', 'zzz', 128, '2021-08-24 10:04:49', 'Oracle Database，又名Oracle RDBMS，或简称Oracle。是甲骨文公司的一款关系数据库管理系统。它是在数据库领域一直处于领先地位的产品。可以说Oracle数据库系统是目前世界上流行的关系数据库管理系统，系统可移植性好、使用方便、功能强，适用于各类大、中、小微机环境。', '通过审核', 'pic1629770697624.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (146, '后台报数据格式转换异常？', 'aaa', 6, '2021-08-25 08:49:24', '正常情况下前台传一个int类型的数据，后台接收时是Double型，即传1接收1.0，所以须将string转double(Double.parseDouble())再转int(new Double(d).intValue());', '通过审核', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (147, 'vue组件间怎么传值？', 'aaa', 130, '2021-08-25 08:56:24', '组件间的传值，一个父向子props,子向父使用函数this.$emit(fun,value),还可以路由传参，（path,{values}）,使用this.$route.values接收；还可以使用vuex的状态管理工具！', '通过审核', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (148, 'java验证码工具类的使用？', '小小', 6, '2021-08-25 09:01:42', '在Java中谷歌提供了一个验证码的jar包kaptcha,首先需要导包，然后在web.xml中引入jar包,其servlet-class在jar包的servlet类中可见，再使用img标签去使用它，动态点击即多次向后台发送请求！注：须注意浏览器缓存问题，谷歌和火狐、IE均有区别；', '通过审核', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (131, 'UI设计狮聚集地', 'zzz', 11, '2021-08-24 09:45:28', '在开发中我们经常会使用各种图标，为了节省资源，你可能不会自己设计自己需要的图标，这时候你可已通过http://www.iconfont.cn/这个网站来获取自己需要的图标，在开发过程中，我们会使用在线图标链接，易于增加所需图标或者删除多余的图标', '通过审核', 'pic1629769528827.gif');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (132, '常用图标符号', 'zzz', 10, '2021-08-24 09:47:45', '◢ ◣ ◥ ◤ ▽ ？ ？ ？ ？ ？ ？ ？ ？ ？ ▓ ？ ？ Café № ＠ ㊣ ？ ℡ 凸 の ？？？ ？？？ ？？？ o(？''''''？)o ？？？？？ べò？？？？ ？？？？ ？？？？ べ ？ ？ ？？ ？ ⊙？ ？ ？ ？ ？ ◇ ◆ Café', '通过审核', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (133, 'java全栈之入门', 'zzz', 6, '2021-08-26 00:00:00', '包含: Java 基础, Java 部分源码, JVM, Spring, Spring Boot, Spring Cloud, 数据库原理, MySQL, ElasticSearch, MongoDB, Docker, k8s, CI&CD, Linux, DevOps, 分布式, 中间件, 开发工具, Git, IDE；', '通过审核', 'pic1629769879174.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (135, 'PHP入门讲解', 'zzz', 9, '2021-08-24 09:56:05', 'PHP（PHP: Hypertext Preprocessor）即“超文本预处理器”，是在服务器端执行的脚本语言，尤其适用于Web开发并可嵌入HTML中。', '通过审核', 'pic1629770166628.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (138, 'JS新手入门', 'zzz', 14, '2021-08-19 00:00:00', 'js即javascript,是属于 HTML 和 Web 的编程语言。', '通过审核', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (139, 'MySql数据库', 'zzz', 129, '2021-08-24 10:03:46', 'MySql是一种关系型数据库，开源免费，是目前开发人员比较喜欢的一款数据库！', '通过审核', null);
commit;
prompt 17 records loaded
prompt Loading FORUM_USER...
insert into FORUM_USER (userid, userpwd, nickname, tel, userpic, regis_time, user_state)
values ('小小', '111', 'ajaxx', '132658', 'pic1629267572695.jpg', to_date('18-08-2021', 'dd-mm-yyyy'), 1);
insert into FORUM_USER (userid, userpwd, nickname, tel, userpic, regis_time, user_state)
values ('aaa', '111', '小a', '58585855', 'pic1629349974295.jpg', to_date('19-08-2021', 'dd-mm-yyyy'), 1);
insert into FORUM_USER (userid, userpwd, nickname, tel, userpic, regis_time, user_state)
values ('xxx', '111', 'ded', '12356', 'pic1629964140024.jpg', to_date('26-08-2021', 'dd-mm-yyyy'), 1);
insert into FORUM_USER (userid, userpwd, nickname, tel, userpic, regis_time, user_state)
values ('zzz', '111', '小z', '3695281', 'pic1629349548626.jpg', to_date('19-08-2021', 'dd-mm-yyyy'), 1);
commit;
prompt 4 records loaded
prompt Loading REPORT...
insert into REPORT (rid, loginid, userid, cid, rep_content, repstate)
values (94, 'admin', 'qqw', 75, '说脏话，言语侮辱', 0);
insert into REPORT (rid, loginid, userid, cid, rep_content, repstate)
values (63, 'admin', 'qqw', 61, '存在暴力倾向', 0);
insert into REPORT (rid, loginid, userid, cid, rep_content, repstate)
values (166, 'admin', 'aaa', 165, '它是违法的从', 0);
insert into REPORT (rid, loginid, userid, cid, rep_content, repstate)
values (64, 'admin', 'qqw', 28, '太厉害了，夸张的', 0);
commit;
prompt 4 records loaded
prompt Loading USERMESSAGE...
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (161, 'admin', 'aaa', '测试', '2021-08-26 15:47:03');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (91, 'admin', 'zzz', '我们都有一个家，名字叫中国', '2021-08-20 16:15:45');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (93, 'admin', 'aaa', '水浒传是英雄好汉看的电视剧', '2021-08-20 16:16:14');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (84, 'admin', 'zzz', '世上再无阿富汗', '2021-08-19 21:39:55');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (80, 'admin', '小小', '今天得西藏很精彩，沃恩很山东省', '2021-08-19 21:15:29');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (82, 'admin', 'qqw', '金泰你的世界的巨变的wooded事物是的是的', '2021-08-24 01:10:07');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (89, 'admin', '小小', '红楼们林黛玉很可怜', '2021-08-20 16:15:24');
commit;
prompt 7 records loaded
prompt Enabling triggers for ADMIN...
alter table ADMIN enable all triggers;
prompt Enabling triggers for ADMINMESSAGE...
alter table ADMINMESSAGE enable all triggers;
prompt Enabling triggers for AREATYPE...
alter table AREATYPE enable all triggers;
prompt Enabling triggers for COLLECTION...
alter table COLLECTION enable all triggers;
prompt Enabling triggers for COMENT...
alter table COMENT enable all triggers;
prompt Enabling triggers for FORUM...
alter table FORUM enable all triggers;
prompt Enabling triggers for FORUM_USER...
alter table FORUM_USER enable all triggers;
prompt Enabling triggers for REPORT...
alter table REPORT enable all triggers;
prompt Enabling triggers for USERMESSAGE...
alter table USERMESSAGE enable all triggers;
set feedback on
set define on
prompt Done.
