# yeblog_plus
个人博客系统（Mybatis+thymeleaf+SpringBoot）

博客地址：http://www.yeidea.top/

### 一、所用技术
#### 1.前端
- JS框架：JQuery
- CSS框架：[Semantic UI](https://semantic-ui.com/)
- 音乐播放[：zplayer](https://gitee.com/supperzh/zplayer)
- 照片墙[：lightbox插件](https://github.com/JavaScript-Kit/jkresponsivegallery)
- 博客编辑[: Markdown插件](https://pandao.github.io/editor.md/) 下载-提取css，editormd.js，editormd.min.js,fonts,images，languages,lib文件-查看example使用
- animate.css动画效果：[: animate](https://daneden.github.io/animate.css/)
#### 2.后端
- 核心框架：SpringBoot 2.2.5
- 项目构建：jdk1.8、Maven 3
- 持久层框架：Mybatis
- 模板框架：Thymeleaf
- 分页插件：PageHelper
- 运行环境：阿里云Linux系统Centos7

#### 3.数据库
- MySQL 5.7

### 二、功能需求
个人博客，区分了一下普通用户和管理员用户,管理员用户有权进行发布博客

#### 1.普通用户
- 查看文章信息：文章列表、推荐文章、文章标题、文章内容等
- 查看分类文章：分类列表、分类文章信息
- 查看时间段：按照文章时间发布顺序查看文章
- 听音乐：上一曲、下一曲、音量控制、播放顺序控制、查看歌词等
- 留言：留言并回复
- 协同维护
- 查看相册信息：相册列表、照片名称、照片拍摄地点、时间、照片描述
- 搜索文章：导航栏右边搜索框根据关键字搜索
#### 2.管理员用户
- 拥有普通用户所有功能权限
- 登录：在主页路径下加“/admin”，可进入登录页面，根据数据库的用户名和密码进行登录
- 文章管理：查询文章列表、新增文章、编辑文章、删除文章、搜索文章
- 分类管理：查询分类列表、新增分类、编辑分类、删除分类
- 相册管理：查询相册列表、新增照片、编辑照片、删除照片
- 消息管理：登录后恢复评论留言会显示头像信息，并能显示删除消息按键，可以对消息进行删除

### 三、数据库设计

#### 1.数据表
- 博客数据表：t_blog
- 分类数据表：t_type
- 用户数据表：t_user
- 评论数据表：t_comment
- 留言数据表：t_message
- 协同维护数据表：t_friend
- 相册数据表：t_picture
