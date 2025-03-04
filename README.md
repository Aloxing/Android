# 本项目依赖库分析

## 图片加载
- `implementation libs.glide` - Glide图片加载库，用于高效加载和显示图片。
- `annotationProcessor libs.compiler` - Glide的注解处理器，用于在编译时生成代码。

## 响应式编程
- `implementation libs.rxjava` - RxJava2核心库，用于响应式编程。
- `implementation libs.rxandroid` - RxAndroid库，提供Android特定的调度器，如主线程调度器。

## 数据库
- `implementation libs.room.runtime` - Room持久化库的运行时库，用于在Android上进行数据库操作。
- `annotationProcessor libs.room.compiler` - Room的注解处理器，用于生成Room数据库的代码。
- `implementation libs.room.rxjava2` - Room的RxJava2支持库，允许在Room中使用RxJava2。
- `implementation libs.room.rxjava3` - Room的RxJava3支持库，允许在Room中使用RxJava3。
- `implementation libs.room.guava` - Room的Guava支持库，提供Optional和ListenableFuture等功能。
- `testImplementation libs.room.testing` - Room的测试辅助库，用于编写Room数据库的测试。
- `implementation libs.room.paging` - Room的Paging 3集成库，用于在Room中使用Paging 3。

## 网络请求
- `implementation libs.retrofit` - Retrofit库，用于网络请求和API调用。
- `implementation libs.converter.gson` - Retrofit的Gson转换器，用于JSON数据的序列化和反序列化。

## UI组件
- `implementation libs.appcompat` - AndroidX AppCompat库，提供向后兼容的UI组件。
- `implementation libs.material` - Material Components for Android库，提供Material Design组件。
- `implementation libs.activity` - AndroidX Activity库，提供Activity相关的功能。
- `implementation libs.constraintlayout` - ConstraintLayout库，用于构建复杂的布局。
- `implementation libs.legacy.support.v4` - AndroidX Legacy Support V4库，提供向后兼容的功能。
- `implementation libs.recyclerview` - RecyclerView库，用于显示大量数据的列表。

## 测试
- `testImplementation libs.junit` - JUnit测试框架，用于单元测试。
- `androidTestImplementation libs.ext.junit` - AndroidX的JUnit扩展库，用于Android instrumentation测试。
- `androidTestImplementation libs.espresso.core` - Espresso测试框架的核心库，用于UI测试。