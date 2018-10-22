
项目实现kotlin+retrofit2+rxjava2+MVP

目录结构

1、bean实体类（不重要，可以自定义）；

2、core项目核心部分（主要对rxjava管理配置，mvp父类统一配置，以及网络请求和数据的统一处理）；

3、mvp 里面包括contract（定义接口），model（实现数据请求），presenter（实现数据返回处理）；

4、networkapi（网络请求接口）；

5、ui界面类（包括activity，fragment，apapter）；

大致目录就这样，项目目录可根据个人喜好定义

使用方法（主要说明Core，其他不重要；）：

1、使用时你只需要引入core包就ok了（因为我初始化时把BaseUrl放在了Application，BaseUrl你可以挪到别的地方的）

2、包里的东西没什么好说的（自我觉得命名时规范的，能知道他是做什么用的）

3、data/net/RxService类okhttp3配置SSL证书，cookie，超时时间，日志，缓存，超时时间，retrofit2请求api父类（retrofit2是建立在okhttp3上），

有的是使用token，token是加在Header上的不懂的自己去搜索一下，这里就不说了

4、data/entity/CoreDataResponse服务器返回统一格式（记得命名要和服务器返回的一样）

5、utils/RxUtil，返回状态码统一处理（各种自定义错误，其实关联了几个类）

6、utils工具类

好了不说（实在是不会说了，没上过大学的我感到压力，不像你们大学生，个个是大佬，说话都好好听，我超喜欢的）
