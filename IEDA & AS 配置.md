## 1.配置环境

首先要安装好JDK,但不需要单独下载SDK,只需在IDEA或AS的"设置->外观与行为->->系统设置->Android SDK"中下载相应版本即可(可以下载多个,不会冲突),然后在"设置->项目结构"中即可配置SDK

## 2.gradle同步失败

失败原因是网络不畅,解决办法为修改build.gradle(工程里是有两个build.gradle文件，这里修改的是project目录下的build.gradle文件,而不是app目录下的build.gradle文件)

```groovy
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        maven { url 'http://maven.aliyun.com/nexus/content/repositories/google' }
        maven { url 'http://maven.aliyun.com/nexus/content/repositories/jcenter'}
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.2'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
allprojects {
    repositories {
        maven { url 'http://maven.aliyun.com/nexus/content/repositories/google' }
        maven { url 'http://maven.aliyun.com/nexus/content/repositories/jcenter'}
    }
    tasks.withType(Javadoc) { // 新增
        options.addStringOption('Xdoclint:none', '-quiet')
        options.addStringOption('encoding', 'UTF-8')
    }
}
task clean(type: Delete) {
    delete rootProject.buildDir
}
```

其中,classpath 'com.android.tools.build:gradle:3.5.2'需要根据自己的Android Studio版本来修改,可以在Help->About中查看Android Studio版本。
