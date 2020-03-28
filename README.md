# springboot-grid
### 1. 简介
这是一套基于spring boot 2.2.1、shiro、ehcache、swagger2、mybatis 、thymeleaf、layui 后台GIS管理系统， 权限控制的方式为 RBAC。
业务为代码通熟易懂 ，数据全程 ajax 获取，封装 ajax 工具类、菜单无线层级展示。

### 2. 具有如下特点
* 灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求
* 当角色或者菜单权限发生变化的时候能够自动刷新用户权限无需退出登录
* 友好的代码结构及注释，便于阅读及二次开发
* 页面交互使用thymeleaf+layui ，极大的提高了开发效率。
* 菜单支持无线层级展示、解决 layui.tree 树形组件数据回显错乱。
* 引入swagger文档支持，方便编写API接口文档。
* 实现单点登录功能
* 实现多列多图上传
* 使用art-template模板引擎

#### 预览图
![登录页](http://gridpic.tsing-tec.com/a6d71260-2348-4f59-ac10-e5596024dd8a)
![权限列表](http://gridpic.tsing-tec.com/a8c332a0-8c56-48da-a8f0-0ac8ed1e8ec3)
![角色列表](http://gridpic.tsing-tec.com/e972f475-17a4-4aac-98b5-a0347a484bc7)
![账号列表](http://gridpic.tsing-tec.com/4acc5883-db1c-41c8-b443-869eae603d73)
![账号赋值](http://gridpic.tsing-tec.com/fb354664-ccad-4fb2-ae6e-d7ff1f0d0f25)
![角色修改新增](http://gridpic.tsing-tec.com/0fd43962-83be-4226-a1f7-888bbe53f702)
![swager接口](http://gridpic.tsing-tec.com/57ac8f30-a065-4bb9-aadd-cddbb69aaf90)
![日志列表](http://gridpic.tsing-tec.com/ce813a13-b731-4bf5-b4c1-39184d5fb402)
![社区列表](http://gridpic.tsing-tec.com/0ef9881e-18f9-4527-a5a2-0991339fd955)
![社区详细](http://gridpic.tsing-tec.com/6dbf0f73-f76a-4263-be89-82fc52c995d4)
![多图预览](http://gridpic.tsing-tec.com/457ec170-e718-491d-ac16-2aad66b5fcf2)
![全景图查看](http://gridpic.tsing-tec.com/7a024d4b-9bb0-4f4b-9741-3a71b86a9888)
![吸毒人员信息编辑](http://gridpic.tsing-tec.com/9b8728d3-18a6-4dca-8b56-487882d5cbd6)
![统计页面](http://gridpic.tsing-tec.com/1820e7f6-e9bd-47ff-851e-24a4cd6ccdc7)
![网格员列表](http://gridpic.tsing-tec.com/828ddbbf-798a-45fe-a7ad-fc6b2b72ae2d)
![网格员详细](http://gridpic.tsing-tec.com/bd41aa51-e1ca-48bf-98db-149d1480b699)
![吸毒人员详细](http://gridpic.tsing-tec.com/64f6e099-0ddd-4e6a-8bcf-966a4e9947e4)
![吸毒人员列表](http://gridpic.tsing-tec.com/781dea6d-986d-4ac4-bc85-d78173fd732f)
![整体页面](http://gridpic.tsing-tec.com/590bf415-95bf-4e3f-b605-166283fd0370)
![吸毒人员列表](http://gridpic.tsing-tec.com/9f68d462-b6bc-4695-a7fe-f59a3a1a380f)

