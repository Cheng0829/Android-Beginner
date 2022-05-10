# 1

## Others

安卓的数据逻辑和布局视图是分离的

### res:
drawbleXXX是放图片的
mipmap是放图标的(后缀~hdpi代表分辨率)
layout是放布局的(包含文本内容helloworld和文本框的高度等信息)
values是放字符串和颜色的
在src/res/values/string.xml中可以更改软件标题名hellochina.

### XML与HMTL代码注释均为<!--XXXX-->,且不能插入各子模块代码

### log

log类有5种方法来打印日志,log.XX(tag,msg)(tag常设置为当前类名,主要用于对打印信息进行过滤,类似于label;msg即message,打印的具体内容)
log.v(tag,msg),verbose,打印最琐碎意义最小的日志信息
log.d(tag,msg)--debug,打印调试信息
log.i(tag,msg)--information,打印比较重要的数据
log.w(tag,msg)--warning,打印警告信息,提示开发者潜在的风险
log.e(tag,msg)--error,打印错误信息,
在src/main/MainActivity.java下的OnCreate方法外写入logt,回车即可自动生成tag(默认为当前类名)
在IDE下方的工具栏logcat项中可以看到当前应用的日志信息,有verbose~error 5类(还有assert),在有房可以定义日志过滤器配置,过滤器名称随意,日志标记须于目标tag一致,过滤器只会返回日志级别>=设定级别的日志

### XML注释

`<!XXXXXXX-->`,例如:

```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <!--禁用掉明文流量请求的检查-->
    <base-config cleartextTrafficPermitted="true" />
</network-security-config>
```

### AndroidManifest.xml

```xml
        <activity
                android:name=".MainActivity",//"."是简写,表示包名"com.ttit.myapp"
                android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>//最主要活动
                <category android:name="android.intent.category.LAUNCHER"/>//启动页
            </intent-filter>
        </activity>
```

### 下载外部jar包
build.gradle用来配置所有代码依赖仓库,可以用来引用各开源项目(因为墙,很可能同步失败"Sync project failed.")
登录https://mvnrepository.com/search?q=okhttp, 找到想要的jar包,在其页面的gradle下载处复制下载代码,粘贴到app/build.gradle下的dependencies中,然后在IDEA上方gradle会提示下载
如`implementation group: 'com.squareup.okhttp3', name: 'okhttp', version: '3.10.0'`,也可简写为`implementation  'com.squareup.okhttp3:okhttp:3.10.0`
在外部库可查看下载的jar包

### 下载外部插件

与上述基本一致,区别在于外部插件的路径是在项目目录下的build.gradle下的

## 启动页UI(activity_main.xml)

### 背景图

可以在预览页通过拖动页面进行直接布局,但是可能不严谨,

通过`android:background="@mipmap/splash"`直接调用res\mipmap-xxxhdpi下的背景图片

### 两个按钮:登录和注册

#### 嵌套一个相对布局

先把代码首部改为`<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"`

```xml
    <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
    </RelativeLayout>
```

#### 设计两个按钮:把按钮的文字信息保存到res/values/string.xml中(尽量避免使用绝对信息,方便统一修改)

```xml
<resources>
    <string name="app_name">myapp</string>
    <string name="login">登录</string>
    <string name="register">注册</string>
</resources>
```

#### PxCook标注图

用PxCook打开标注图可以看到图片要素组件的各种信息:文字颜色大小,宽高,偏左偏右,两个按钮之间的间距等等

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@mipmap/splash"

        tools:context=".MainActivity">
    <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:layout_marginBottom="90dp"
            android:paddingLeft="44dp"
            android:paddingRight="44dp">

        <Button
                android:layout_width="100dp"
                android:layout_height="40dp"
                android:layout_alignParentLeft="true"
                android:text="@string/login"
                android:textColor="#ffffff"
                android:background="@drawable/shape_login_btn"
                android:textSize="20sp"/>
        <Button
                android:layout_width="100dp"
                android:layout_height="40dp"
                android:layout_alignParentRight="true"
                android:text="@string/register"
                android:textColor="#ffffff"
                android:background="@drawable/shape_register_btn"
                android:textSize="20sp"/>
    </RelativeLayout>
```

#### 按钮背景

按钮背景最好不用图片表示(斗则图片太多,APP体积太大),可以在res/drawable下新建资源文件,根元素为shape,文件名shape_login/register_btn,吧pxcook中展现的按钮背景xml代码复制到shape_login/register_btn.xml即可
其中一个:

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
        android:shape="rectangle">
        <corners android:radius="9dp" />
        <solid android:color="#c75fe768" />
</shape>
```
#### 更改绝对数值

activity_main.xml中的各种dp距离最好不要用数值表达(方便修改),
在res中新建XML文件(Values XML Files),文件名dimens:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="dimen_90dp">90dp</dimen>
    <dimen name="dimen_44dp">44dp</dimen>

    <dimen name="size_20sp">20sp</dimen>
</resources>
```
在activity_main.xml中更改各绝对值即可,例如:` android:paddingLeft="@dimen/dimen_44dp"`




## 实现登录功能

### 登录页和注册页UI

在activity_main.xml为按钮添加id : `android:id=@+id/btn_login`,在com.ttit.myapp下新建软件包package,名为activity,然后对这个包新建empty activity,活动名设为LoginActivity,系统会自动在layout目录下为此活动LoginAcitivity.java创建一个布局activity_login.xml并自动引用

为登录按钮设置跳转功能:

```java
    private Button btnLogin,btnRegister;//设置按钮对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btn_login);//找到按钮id
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//设置点击事件
                Intent in = new Intent(MainActivity.this, LoginActivity.class);//跳转
                startActivity(in);
            }
        });
    }
```

然后仿照首页UI设计流程,在布局文件中设计即可.

注册页与登录页基本一致,改个名即可,但注意检查两个活动是否在AndroidManifest.xml注册

### 登录跳转

在activity_login.xml中为edit_text组件创建一个id,,然后在LoginActivity中实现登录功能:首先创建按钮和编辑框对象,然后用findViewById找到对象的id,为按钮绑定点击事件(登录事件),再编写登录事件即可

### 登录接口

首先用gradle下载okhttp库,然后在AndroidManifest.xml中注册获取权限`<uses-permission android:name="android.permission.INTERNET"/>`,然后把模板代码添加到login函数中:

```java
private void login(String account, String pwd) {
        if (StringUtils.isEmpty(account)) {
            //判断账号是否为空
            showToast("请输入账号");
        }
        if (StringUtils.isEmpty(pwd)) {
            //判断密码是否为空
            showToast("请输入密码");
        }

        //第一步创建OKHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Map m = new HashMap();
        m.put("mobile", account);
        m.put("password", pwd);//添加一个集合
        JSONObject jsonObject = new JSONObject(m);//转换成json字符串(看上去像是字典)
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson =
                RequestBody.create(MediaType.parse("application/json;charset=utf-8")
                        , jsonStr);//创建请求体
        //第三步创建Rquest
        Request request = new Request.Builder()
                .url(AppConfig.BASE_URL + "app/login")//请求链接
                .addHeader("contentType", "application/json;charset=UTF-8")//请求头
                .post(requestBodyJson)//请求方法
                .build();
        //第四步创建call回调对象
        final Call call = client.newCall(request);
        //第五步发起请求
        call.enqueue(new Callback() {
            @Override
            //回调1:失败
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", e.getMessage());
            }

            @Override
            //回调2:成功
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();//回调内容
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(result);
                    }
                });
            }
        });
    }

```

由于要实现网络连接,所以也要进行网址配置:AppConfig.java

### 注册跳转和接口

与登陆基本一致,微改即可.

### Token

登录之后,需要保存登录信息.
成功回调之后打印返回的json信息的Log,在子线程中准备一个消息队列Looper,负责登陆时返回的json字符串,安装GsonFormat插件(在androidmanifest.xml中添加依赖),生成,然后粘贴进去,自动生成json对象.在LoginActivity.java进行gettoken定义即可,可利用SharedPreference存储到XML文件中

## 首页

### 首页布局UI

由于有多个显示信息(首页,消息,联系人,更多),所以使用fragment,