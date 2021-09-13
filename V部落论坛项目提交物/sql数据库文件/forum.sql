prompt PL/SQL Developer import file
prompt Created on 2021��8��29�� by lenovo
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
values (86, 'admin', 'qqw', '���μ�86�汾�൱�ÿ�', '2021-08-20 16:14:54');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (160, 'admin', 'aaa', '����', '2021-08-26 15:47:03');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (90, 'admin', 'zzz', '���Ƕ���һ���ң����ֽ��й�', '2021-08-20 16:15:45');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (92, 'admin', 'aaa', 'ˮ䰴���Ӣ�ۺú����ĵ��Ӿ�', '2021-08-20 16:16:14');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (83, 'admin', 'zzz', '�������ް�����', '2021-08-19 21:39:55');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (78, 'admin', 'СС', '��������غܾ��ʣ��ֶ���ɽ��ʡ', '2021-08-19 21:15:29');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (81, 'admin', 'qqw', '��̩�������ľޱ��wooded�����ǵ��ǵ�', '2021-08-24 01:10:07');
insert into ADMINMESSAGE (amid, loginid, userid, mes_content, msgtime)
values (88, 'admin', 'СС', '��¥��������ܿ���', '2021-08-20 16:15:24');
commit;
prompt 8 records loaded
prompt Loading AREATYPE...
insert into AREATYPE (typeid, typename)
values (6, 'javaȫջ����');
insert into AREATYPE (typeid, typename)
values (7, 'C++����ջ');
insert into AREATYPE (typeid, typename)
values (8, 'python����');
insert into AREATYPE (typeid, typename)
values (9, 'PHP����');
insert into AREATYPE (typeid, typename)
values (10, 'HTML����');
insert into AREATYPE (typeid, typename)
values (11, 'CSSҳ������');
insert into AREATYPE (typeid, typename)
values (14, 'JS��̳');
insert into AREATYPE (typeid, typename)
values (129, 'MySql���ݿ�');
insert into AREATYPE (typeid, typename)
values (128, 'Oracle���ݿ�');
insert into AREATYPE (typeid, typename)
values (130, 'vue2.0����');
insert into AREATYPE (typeid, typename)
values (159, '����');
commit;
prompt 11 records loaded
prompt Loading COLLECTION...
insert into COLLECTION (col_id, fid, userid, colltime)
values (68, 26, 'qqw', '2021��08��19�� 11:06:31');
insert into COLLECTION (col_id, fid, userid, colltime)
values (153, 131, 'qqw', '2021��08��26�� 15:38:39');
insert into COLLECTION (col_id, fid, userid, colltime)
values (74, 18, 'qqw', '2021��08��19�� 14:17:46');
insert into COLLECTION (col_id, fid, userid, colltime)
values (67, 27, 'qqw', '2021��08��19�� 10:57:40');
commit;
prompt 4 records loaded
prompt Loading COMENT...
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (1, 11, 'СС', '�����ã�good!', '2021-08-16 15:23:30', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (2, 12, '����', '���صĺܲ���', '2001-09-29 15:23:30', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (22, 21, 'qqw', '������˵', '2021��08��18�� 10:48:16', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (23, 21, 'qqw', 'ѧ��������', '2021��08��18�� 10:49:32', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (96, 1, 'aaa', '����������������һ�ƣ�', '2021��08��22�� 13:22:50', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (98, 18, 'qqw', 'С��������Ƥ', '2021��08��22�� 13:34:21', 'pic1629610461383.jpg');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (100, 26, 'qqw', '�л���ǧ�����ʷ', '2021��08��22�� 13:37:01', 'pic1629610621888.gif');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (105, 69, 'СС', 'undefined', '2021��08��22�� 13:55:12', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (106, 69, 'СС', '�ҵ����ֲ����Ҷ����۵�', '2021��08��22�� 14:00:51', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (108, 69, 'zzz', '251551551', '2021��08��22�� 14:07:22', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (150, 127, 'qqw', '���ǵ�ʱ�⣡', '2021��08��25�� 11:01:14', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (154, 127, 'qqw', '����', '2021��08��26�� 15:38:54', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (163, 127, 'СС', 'azazazazaz', '2021��08��26�� 16:04:47', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (16, 11, 'СС', 'asaa�й�', '2021��08��18�� 09:43:58', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (102, 69, 'СС', '��ţ�ĺ��Ӻ컨����', '2021��08��22�� 13:49:46', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (103, 69, 'СС', '���ȹ���������̫ţ��', '2021��08��22�� 13:50:47', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (109, 1, 'zzz', 'zenee26265', '2021��08��22�� 14:11:19', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (143, 132, 'СС', '�����ܺúܺã�', '2021��08��24�� 10:08:02', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (155, 131, 'qqw', '����', '2021��08��26�� 15:39:54', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (164, 127, 'СС', '����������', '2021��08��26�� 16:05:12', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (17, 12, '����', '�ҵļ���', '2021��08��18�� 09:48:07', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (25, 19, 'qqw', '�����', '2021��08��18�� 11:24:09', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (75, 18, 'qqw', '����', '2021��08��19�� 14:18:35', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (99, 18, 'qqw', 'С���ӵ�����', '2021��08��22�� 13:34:39', 'pic1629610479941.jpg');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (142, 132, 'zzz', '���ñ����html��Ӧ�úܶ࣡', '2021��08��24�� 10:06:58', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (152, 127, 'qqw', 'undefined', '2021��08��25�� 11:28:00', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (156, 127, 'qqw', 'undefined', '2021��08��26�� 15:40:31', 'pic1629963631450.webp');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (101, 26, 'qqw', '6666', '2021��08��22�� 13:37:11', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (95, 1, 'aaa', '����СС���������μǣ�', '2021��08��22�� 13:18:39', 'pic1629609519587.jpg');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (104, 69, 'СС', '�ҵ���i�ڱ�����', '2021��08��22�� 13:55:12', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (110, 18, 'zzz', 'swswsw4747', '2021��08��22�� 14:11:33', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (151, 127, 'qqw', 'undefined', '2021��08��25�� 11:27:53', 'pic1629862073403.jpg');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (158, 157, 'qqw', 'womnene', '2021��08��26�� 15:41:19', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (24, 20, 'qqw', '�ҵĺ���', '2021��08��18�� 11:14:32', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (107, 69, 'zzz', '�¶����ռ�ľŷֶ���', '2021��08��22�� 14:06:41', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (76, 18, 'qqw', '����', '2021��08��19�� 14:22:32', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (97, 1, 'aaa', 'undefined', '2021��08��22�� 13:28:05', 'pic1629610085673.jpg');
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (149, 148, 'СС', '��֤�����session�У�', '2021��08��25�� 09:02:29', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (162, 131, 'aaa', 'СС', '2021��08��26�� 15:47:41', null);
insert into COMENT (cid, fid, userid, com_content, com_time, comfile)
values (165, 135, 'aaa', 'tadegushi', '2021��08��26�� 16:17:09', null);
commit;
prompt 40 records loaded
prompt Loading FORUM...
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (157, 'java', 'qqw', 6, '2021-08-26 15:41:08', 'javase', 'ͨ�����', 'pic1629963669113.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (134, 'python��ʲô', 'zzz', 8, '2021-08-24 09:53:40', 'Python�ṩ�˸�Ч�ĸ߼����ݽṹ�����ܼ���Ч����������̡�Python�﷨�Ͷ�̬���ͣ��Լ����������Եı��ʣ�ʹ����Ϊ����ƽ̨��д�ű��Ϳ��ٿ���Ӧ�õı�����ԣ�', '���δͨ��', 'pic1629770021184.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (136, 'HTML��������', 'zzz', 10, '2021-08-24 09:57:44', 'html��һ�ų��ı�������ԣ��ʺ�����С��ѧϰ����ǰ�˱ر���һ��ܣ�', 'ͨ�����', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (141, 'ǰ���������Vue', 'zzz', 130, '2021-08-24 10:05:52', 'Vue (���� /vju��/�������� view) ��һ�����ڹ����û�����Ľ���ʽ��ܡ����������Ϳ�ܲ�ͬ���ǣ�Vue �����Ϊ�����Ե��������Ӧ�á�Vue �ĺ��Ŀ�ֻ��ע��ͼ�㣬�����������֣����������������������Ŀ���ϡ�', 'ͨ�����', 'pic1629770753386.gif');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (145, 'ǰ��̨������ô�����', 'aaa', 6, '2021-08-25 08:45:54', '�����ں�̨��Ŀ�����һ���������࣬��������ͷ��������Ӧ�ı��뷽ʽ�ȣ�����vue��ܣ���ǰ��Ҳ���ÿ���', 'ͨ�����', 'pic1629852356220.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (127, '#������ν��pycharm���޷�����python package������', 'zzz', 7, '2021-08-24 09:16:14', '���־�����Ǳ���˵���޷���������, ������pycharm, ��������Ҳû����. ������������python interpreter֮���һֱ����.ʼ���޷������ҵ�����.', 'ͨ�����', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (137, 'CSS���', 'zzz', 11, '2021-08-24 10:00:37', 'css��ҳ��������һ��ǰ�˼�����Ҳ����UI��ư�飡', 'ͨ�����', 'pic1629770443859.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (140, 'Oracle���ݿ�', 'zzz', 128, '2021-08-24 10:04:49', 'Oracle Database������Oracle RDBMS������Oracle���Ǽ׹��Ĺ�˾��һ���ϵ���ݿ����ϵͳ�����������ݿ�����һֱ�������ȵ�λ�Ĳ�Ʒ������˵Oracle���ݿ�ϵͳ��Ŀǰ���������еĹ�ϵ���ݿ����ϵͳ��ϵͳ����ֲ�Ժá�ʹ�÷��㡢����ǿ�������ڸ�����С�С΢��������', 'ͨ�����', 'pic1629770697624.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (146, '��̨�����ݸ�ʽת���쳣��', 'aaa', 6, '2021-08-25 08:49:24', '���������ǰ̨��һ��int���͵����ݣ���̨����ʱ��Double�ͣ�����1����1.0�������뽫stringתdouble(Double.parseDouble())��תint(new Double(d).intValue());', 'ͨ�����', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (147, 'vue�������ô��ֵ��', 'aaa', 130, '2021-08-25 08:56:24', '�����Ĵ�ֵ��һ��������props,����ʹ�ú���this.$emit(fun,value),������·�ɴ��Σ���path,{values}��,ʹ��this.$route.values���գ�������ʹ��vuex��״̬�����ߣ�', 'ͨ�����', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (148, 'java��֤�빤�����ʹ�ã�', 'СС', 6, '2021-08-25 09:01:42', '��Java�йȸ��ṩ��һ����֤���jar��kaptcha,������Ҫ������Ȼ����web.xml������jar��,��servlet-class��jar����servlet���пɼ�����ʹ��img��ǩȥʹ��������̬�����������̨��������ע����ע��������������⣬�ȸ�ͻ����IE��������', 'ͨ�����', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (131, 'UI���ʨ�ۼ���', 'zzz', 11, '2021-08-24 09:45:28', '�ڿ��������Ǿ�����ʹ�ø���ͼ�꣬Ϊ�˽�ʡ��Դ������ܲ����Լ�����Լ���Ҫ��ͼ�꣬��ʱ�������ͨ��http://www.iconfont.cn/�����վ����ȡ�Լ���Ҫ��ͼ�꣬�ڿ��������У����ǻ�ʹ������ͼ�����ӣ�������������ͼ�����ɾ�������ͼ��', 'ͨ�����', 'pic1629769528827.gif');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (132, '����ͼ�����', 'zzz', 10, '2021-08-24 09:47:45', '�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� Caf�� �� �� �I �� �Y ͹ �� ������ ������ ������ o(��''''''��)o ���������� �٨��������� �������� �������� �� �� �� ���� �� �ѣ� �� �� �� �� �� �� Caf��', 'ͨ�����', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (133, 'javaȫջ֮����', 'zzz', 6, '2021-08-26 00:00:00', '����: Java ����, Java ����Դ��, JVM, Spring, Spring Boot, Spring Cloud, ���ݿ�ԭ��, MySQL, ElasticSearch, MongoDB, Docker, k8s, CI&CD, Linux, DevOps, �ֲ�ʽ, �м��, ��������, Git, IDE��', 'ͨ�����', 'pic1629769879174.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (135, 'PHP���Ž���', 'zzz', 9, '2021-08-24 09:56:05', 'PHP��PHP: Hypertext Preprocessor���������ı�Ԥ�������������ڷ�������ִ�еĽű����ԣ�����������Web��������Ƕ��HTML�С�', 'ͨ�����', 'pic1629770166628.jpg');
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (138, 'JS��������', 'zzz', 14, '2021-08-19 00:00:00', 'js��javascript,������ HTML �� Web �ı�����ԡ�', 'ͨ�����', null);
insert into FORUM (fid, title, userid, typeid, release_time, content, state, uploadfile)
values (139, 'MySql���ݿ�', 'zzz', 129, '2021-08-24 10:03:46', 'MySql��һ�ֹ�ϵ�����ݿ⣬��Դ��ѣ���Ŀǰ������Ա�Ƚ�ϲ����һ�����ݿ⣡', 'ͨ�����', null);
commit;
prompt 17 records loaded
prompt Loading FORUM_USER...
insert into FORUM_USER (userid, userpwd, nickname, tel, userpic, regis_time, user_state)
values ('СС', '111', 'ajaxx', '132658', 'pic1629267572695.jpg', to_date('18-08-2021', 'dd-mm-yyyy'), 1);
insert into FORUM_USER (userid, userpwd, nickname, tel, userpic, regis_time, user_state)
values ('aaa', '111', 'Сa', '58585855', 'pic1629349974295.jpg', to_date('19-08-2021', 'dd-mm-yyyy'), 1);
insert into FORUM_USER (userid, userpwd, nickname, tel, userpic, regis_time, user_state)
values ('xxx', '111', 'ded', '12356', 'pic1629964140024.jpg', to_date('26-08-2021', 'dd-mm-yyyy'), 1);
insert into FORUM_USER (userid, userpwd, nickname, tel, userpic, regis_time, user_state)
values ('zzz', '111', 'Сz', '3695281', 'pic1629349548626.jpg', to_date('19-08-2021', 'dd-mm-yyyy'), 1);
commit;
prompt 4 records loaded
prompt Loading REPORT...
insert into REPORT (rid, loginid, userid, cid, rep_content, repstate)
values (94, 'admin', 'qqw', 75, '˵�໰����������', 0);
insert into REPORT (rid, loginid, userid, cid, rep_content, repstate)
values (63, 'admin', 'qqw', 61, '���ڱ�������', 0);
insert into REPORT (rid, loginid, userid, cid, rep_content, repstate)
values (166, 'admin', 'aaa', 165, '����Υ���Ĵ�', 0);
insert into REPORT (rid, loginid, userid, cid, rep_content, repstate)
values (64, 'admin', 'qqw', 28, '̫�����ˣ����ŵ�', 0);
commit;
prompt 4 records loaded
prompt Loading USERMESSAGE...
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (161, 'admin', 'aaa', '����', '2021-08-26 15:47:03');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (91, 'admin', 'zzz', '���Ƕ���һ���ң����ֽ��й�', '2021-08-20 16:15:45');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (93, 'admin', 'aaa', 'ˮ䰴���Ӣ�ۺú����ĵ��Ӿ�', '2021-08-20 16:16:14');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (84, 'admin', 'zzz', '�������ް�����', '2021-08-19 21:39:55');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (80, 'admin', 'СС', '��������غܾ��ʣ��ֶ���ɽ��ʡ', '2021-08-19 21:15:29');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (82, 'admin', 'qqw', '��̩�������ľޱ��wooded�����ǵ��ǵ�', '2021-08-24 01:10:07');
insert into USERMESSAGE (umid, loginid, userid, mes_content, msgtime)
values (89, 'admin', 'СС', '��¥��������ܿ���', '2021-08-20 16:15:24');
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
