# IdentityImageView
一个能带进度条的图片框架，同时有身份标识动能，简单易用

## 效果图：
![效果图](https://github.com/385841539/IdentityImageView/tree/master/app/src/main/res/drawable)
## 如何使用/How to use？
### 添加以来（或者直接把项目中的两个自定义View拷贝下来也可以这个类放到项目里面
>> 1.先在 build.gradle(Project:XXXX) 的 repositories 添加:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

>> 2.然后在 build.gradle(Module:app) 的 dependencies 添加:
 
 ```
 dependencies {
	        compile 'com.github.385841539:IdentityImageView:-SNAPSHOT'
	}

	}
```
### 布局文件
```
<com.example.identityimageview.widegt.IdentityImageView
        android:id="@+id/iiv"
        android:layout_width="300dp"
        android:layout_height="300dp"
        app:iciv_progress_collor="@color/colorPrimary"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true"></com.example.identityimageview.widegt.IdentityImageView>
```

## 具体实现思路以及其他方法请看博客，[点击查看](http://blog.csdn.net/iamdingruihaha/article/details/69895266)
