












# IdentityImageView
一个能带进度条的图片框架，同时有身份标识动能，简单易用

## 效果图：
![效果图](https://github.com/385841539/IdentityImageView/blob/master/app/src/main/res/drawable/biaoshi.gif)


# 博客介绍

CSDN:[http://blog.csdn.net/iamdingruihaha/article/details/69895266](http://blog.csdn.net/iamdingruihaha/article/details/69895266)


## Download
```java
    allprojects {
	    repositories {
		    ...
		    maven { url 'https://jitpack.io' }
	    }
	}

    dependencies {
     compile 'com.github.385841539:IdentityImageView:-SNAPSHOT'
	}

```

# Usage


基本使用：


根布局需为CoordinatorLayout，类似FrameLayout
```xml
<com.example.identityimageview.widegt.IdentityImageView
        android:id="@+id/iiv"
        android:layout_width="300dp"
        android:layout_height="300dp"
        app:iciv_progress_collor="@color/colorPrimary"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true"></com.example.identityimageview.widegt.IdentityImageView>
```

```


根据不同的View在xml中设置不同的layout_behavior



参数     							|	说明
-----------------------------------|-----------------------
@string/title_view_behavior   		|   顶部标题栏
@string/bottom_view_behavior   	|   底部导航栏
@string/fab_scale_behavior   		|   浮动按钮（缩放）
@string/fab_vertical_behavior   	|    浮动按钮（上下滑动）



自定义(均设有默认值，可不使用)：


| 方法           	 		|    参数           	| 说明  					|
| ------------------------- |------------------ | --------------------- |
| setMinScrollY				| int y 			| 设置触发动画的最小滑动距离，如 setMinScrollY(10)为滑动10像素才可触发动画，默认为5.|
| setScrollYDistance		| int y      	    | 设置触发动画的滑动距离，防止用户缓慢滑动时单次滑动距离一直小于setMinScrollY的最小滑动距离导致无法触发动画.如设置此值为100，则用户即便缓慢滑动，当滑动距离达到100时也可触发动画.默认为40.|
| setDuration				| int duration     	| 设置动画持续时间.默认为400ms.|
| setInterpolator			| Interpolator interpolator | 设置动画插补器，修饰动画效果.默认模式为LinearOutSlowInInterpolator. [Interpolator官方文档](https://developer.android.google.cn/reference/android/view/animation/Interpolator.html)|


```java

	CommonBehavior.from(mFloatingActionButton).show();//代码控制显示
	CommonBehavior.from(mFloatingActionButton).hide();//隐藏

	CommonBehavior.from(mFloatingActionButton)
		.setMinScrollY(20)
		.setScrollYDistance(100)
		.setDuration(1000)
		.setInterpolator(new LinearOutSlowInInterpolator());
```

## Tips

1、因为根布局为CoordinatorLayout，所以使用时Toolbar可能会遮盖RecyclerView顶部的item，BottomBar也可能会遮盖底部item。
可以参考知乎首页设置顶部留白，具体可为RecyclerView添加一个占位的ItemDecoration，或者顶部加一个占位的View，若场景比较固定可简单设置Padding，Margin等，
详情可见Demo，简单处理了这种情况。


2、FloatingActionButton的elevation若大于BottomBar的elevation，则FloatingActionButton动画覆盖在BottomBar上层，反之则在下层，为gif的下部两个按钮的效果。


## Apk and More Info

For more usage, you can download or clone the demo. You can also [download the demo apk](https://github.com/Lauzy/LBehavior/raw/master/apk/demo.apk).









