## SecurityCode 验证码生成View
author:Gloomy
[blog:https://www.gloomyer.com](https://www.gloomyer.com])
如果发现Bug，欢迎提交.

## 使用方式
第一步,拷贝:
很简单,你需要拷贝SecurityCode到你的项目中去.
然后拷贝/res/values/attrs.xml到你的项目中,
如果你的项目中已经存在这个了，就复制当前项目中的内容到你项目中的attrs去.

然后就可以在布局中写当前View了.有四个自定义属性:
```xml
app:TextColor="#a0f00000" 验证码字体颜色
app:TextLength="6" //验证码字体数量
app:TextSize="16sp" //验证码字体大小
app:bg="#abcdef" //验证码字体背景
```
然后在SecurityCode上有个getText()方法，可以获取到当前生成的验证码.

推荐简单浏览下Demo