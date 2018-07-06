# csdnSpider
获取csdn博客专家的博客

jpa + mysql + webmagic

maven package 打包后，要运行jar包的话，需要把config文件夹和jar包放在同一目录下
并且把applicationContext.xml中
<context:property-placeholder location="file:config.properties"/>改为
<context:property-placeholder location="file:config/config.properties"/>
才能找到config.properties文件

ps：spring 加载配置文件一定要在main函数里面
