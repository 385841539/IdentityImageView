
# IdentityImageView
一个能带进度条的图片框架，同时有身份标识动能，简单易用

## 效果图：
![效果图](https://github.com/385841539/IdentityImageView/blob/master/app/src/main/res/mipmap-xhdpi/biaoshi.gif)


# 博客介绍

CSDN:[http://blog.csdn.net/iamdingruihaha/article/details/69895266](http://blog.csdn.net/iamdingruihaha/article/details/69895266)



## Download

  简单的两个类：IdentityImageView，CircleImageView,下载下来 放到项目里面

#### OR 
>> 1.先在 build.gradle(Project:XXXX) 的 repositories 添加:</br>
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

>> 2.然后在 build.gradle(Module:app) 的 dependencies 添加:</br>
 
 ```
 dependencies {
	        
		//1.1.0已经废弃，有bug，请使用1.2.0
		 compile 'com.github.385841539:IdentityImageView:1.1.0'
		 
		 compile 'com.github.385841539:IdentityImageView:1.2.0'
	}
```
# Usage

### 基本使用：


```xml
<com.example.identityimageview.widegt.IdentityImageView
        android:id="@+id/iiv"
        android:layout_width="300dp"
        android:layout_height="300dp"
        app:iciv_progress_collor="@color/colorPrimary"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true"></com.example.identityimageview.widegt.IdentityImageView>
```


### 自定义(均设有默认值，可不使用)：


| 属性           	 		|    参数类型           	| 说明  					|
| ------------------------- |------------------ | --------------------- |
| iciv_bigimage				| reference 			|大图片|
| iciv_smallimage		| reference       	    | 小图片(标识)|
| iciv_angle				| float      	| 标识的角度，默认为45度|
| iciv_radiusscale			| float  |大小图片比例,默认为0.2|
| iciv_isprogress|boolean flag | 是否有进度条,默认为false,如果要用，必须设置为true|
|iciv_progress_collor|Color Or reference|  进度条颜色|
|  iciv_border_color  |Color Or reference|  边框颜色  |
|  iciv_border_width  |integer | 边框和进度条宽度  |
|iciv_hint_smallimageview | boolean|  是否隐藏小图片|


 
### 动态 设置属性值：
```java
   
                identityImageView = ((IdentityImageView) findViewById(R.id.iiv));
               
               //填充大图片
                identityImageView.getBigCircleImageView().setImageResource(R.mipmap.guojia);
		
		//改变图片比例大小，
                identityImageView.setRadiusScale(0.1f);
		
		
	          //增加边框
                identityImageView.setBorderWidth(100);
                identityImageView.setBorderColor(R.color.colorTest);
		
		   //增加进度条，以及改变的角度
                identityImageView.setIsprogress(true);
                identityImageView.setProgressColor(R.color.colorAccent);
		identityImageView.setProgress(120);
		
                
```

## Tips:
 原理很简单，献丑了，方便大家调用，为了节省大家时间。
 






