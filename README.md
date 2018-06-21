## 更新日志
### 2018-06-21
1. 自动根据字符串字段名生成内容（为了让数据看起来更容易调试）
* 如果字段名带有"id"字样，则生成32位uuid字符串。
* 如果字段名带有"date"字样，则生成日期"yyyy-MM-dd"样式的字符串。
* 如果字段名带有"time"字样，则生成日期"yyyy-MM-dd hh:mm:ss"样式的字符串。
2. 欢迎补充或者提出想法！

## DataGenerator使用说明书
### 数据生成器，轻松生成模拟数据，快速生成JSON提供联调。
#### 应用场景：
1. 某些业务的代码编写过程较为复杂，但需要快速生成一些随机的模拟数据来做“演示效果”
2. 需要快速生成一份模拟数据提供给前端工程师调接口
3. 单元测试时需要大批量测试数据，但手工填充相当消耗时间。

但不论哪种场景，本插件的本质，都是想要你能快速给VO填充一些数据，之后可以再使用JSON工具转成JSON字符串，让你达到偷懒的目的。

现在，你只需要一句代码既可以填充一个VO对象。

### 联调时，需要手动填充测试数据，提供JSON结果给对接人。你会创建一个Vo对象，一堆set方法。
```
Task task = new Task();
task.setStudentId("23");
task.setTitle("标题");
task.setTestDate("2018-10-20");
task.setTestTime("2018-10-20 11:11:23");
task.setPrice(44);
task.setEndDate(new Date());

Set<String> names = new HashSet<>();
names.add("jason");
names.add("lili");
names.add("jan");
task.setNameSet(names);
...
```
##### 而现在，你只要一行代码，就可以把你的VO填充满一些随机的数据，再转成Json格式就能发给你的对接人了。（这样的好处是你节省了很多时间）
```
Task one = GeneratorUtils.getOne(Task.class);
```

### 需要引入的依赖：
```
<dependency>
    <groupId>commons-collections</groupId>
    <artifactId>commons-collections</artifactId>
    <version>3.2.1</version>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.4</version>
</dependency>
<dependency>
    <groupId>commons-lang</groupId>
    <artifactId>commons-lang</artifactId>
    <version>2.1</version>
</dependency>
```

### 测试VO：
```
public class Task {
    	private String studentId;
	private String title;
	private String testDate;
	private String testTime;
	private String contactWechat;
	private String alternative;
	private Integer price;
	private Date endDate;
	private Boolean isOnGoing;
	private Set<String> nameSet;
	private LinkedHashSet<String> channelNames;
	private List<SubTask> subTasks;
	private LinkedList<String> linkedList;
	private Map<String, Long> mapTest;
	private TreeMap<String, Integer> keyToDate;
}
```

### 懒人使用方式：直接生成，无需任何配置
```
public static void main(String[] args) throws Exception {
	Task one = GeneratorUtils.getOne(Task.class);			//你成功生成了一个填满数据的对象
}
```
如果你需要生成一个List<Vo>
```
List<Task> tasks = GeneratorUtils.getList(Task.class);		//生成多个VO
```


### 自定义使用方式（新建一个GenerateConfig放到第二个参数）：
```
public static void main(String[] args) throws Exception {
	GenerateConfig config = new GenerateConfig();
	config.setGenerateCount(4);		//指定集合生成数量{List，Map，Set}等
	config.setOpenMessageTip(false);	//关掉消息提示
  
	Task superOne = GeneratorUtils.getOne(Task.class, config);
}
```

可以设定字符串生成范围
```
config.putGenerator(new StringGenerator(5,"QWERT"));	//设置字符串生成方式，参数为：数量，可选字符
```

也可以设定整形生成范围
```
config.putGenerator(new IntegerGenerator(250,300));	//设置整形生成方式，参数为：数值可选范围
```

日期当然也可以
```
config.putGenerator(new DateGenerator(DateGenerator.fluctuate_milltsecond, 3, 16));	//日期浮动单位，范围
```

为 contactWechat 字段指定“7位数微信账号的字符串生成器”
```
config.putGenerator("contactWechat",new StringGenerator(7,"1234567890"));		//只填充特定字段名
```

为 timeString 字段选择一个格式化字符串生成器，%s会在后面的字符串里选择一个字符串来填充。
```
config.putGenerator("timeString",new StringGenerator("201%s-%s2-03","12345670","01"));	//格式化字符串
```

为 alternative 字段选择一个更复杂的格式化字符串生成器。
```
config.putGenerator("alternative",new StringGenerator("今天吃%s，喝%s",			
					new String[]{"汉堡","薯条"},new String[]{"可乐","奶茶","水"}));
```

第一个参数为字段名，第二个参数为一个继承Generator接口的对象，你也可以为你的类型设计一个你需要的生成器，很简单，试试看吧。
	

### 转成JSON字符串输出：
```
{
    "studentId": "53b222761459425c91f7c67d5703eec7",
    "title": "第3个标题",
    "alternative": "今天吃汉堡，喝可乐",
    "testDate": "2019-07-18",
    "testTime": "2017-08-13 17:36:41",
    "price": 299,
    "contactWechat": "9567542",
    "endDate": 1513085282812,
    "isOnGoing": false,
    "channelNames": [
        "EQEQW",
        "EERQQ"
    ],
    "keyToDate": {
        "2015-06-16": 256,
        "2019-05-14": 271
    },
    "linkedList": [
        "WQEQW",
        "ETWRE"
    ],
    "mapTest": {
        "QERRT": 22348458,
        "EQQET": 25607496
    },
    "nameSet": [
        "ERRRQ",
        "QWTWR"
    ],
    "subTasks": [
        {
            "beginDate": 1513085282818,
            "channelNames": [
                "TEQER",
                "RQETW"
            ],
            "endDate": 1513085282807,
            "price": 268,
            "title": "第3个标题"
        },
        {
            "beginDate": 1513085282814,
            "channelNames": [
                "TETET",
                "RERWQ"
            ],
            "endDate": 1513085282808,
            "price": 290,
            "title": "第x个标题"
        }
    ]
}
```

### 转成JSON字符串输出（List<Task>）：
```
[
    {
        "alternative": "今天吃薯条，喝可乐",
        "channelNames": [
            "QTERR",
            "WQEER"
        ],
        "contactWechat": "9504714",
        "endDate": 1513085282807,
        "isOnGoing": false,
        "keyToDate": {
            "2017-09-11": 292,
            "2018-07-16": 255
        },
        "linkedList": [
            "WQTTR",
            "TWWTR"
        ],
        "mapTest": {
            "TETRW": 7885318,
            "TRQEQ": 19566364
        },
        "nameSet": [
            "QQERR",
            "QREEE"
        ],
        "price": 274,
        "studentId": "9dae88e1957b4727968e07170b9bbd6f",
        "subTasks": [
            {
                "beginDate": 1513085282811,
                "channelNames": [
                    "QRQRW",
                    "QQWRE"
                ],
                "endDate": 1513085282809,
                "price": 276,
                "title": "第7个标题"
            },
            {
                "beginDate": 1513085282814,
                "channelNames": [
                    "QQTWQ",
                    "TETEE"
                ],
                "endDate": 1513085282815,
                "price": 272,
                "title": "第4个标题"
            }
        ],
        "testDate": "2018-08-18",
        "testTime": "2011-02-16 18-32-49",
        "title": "第3个标题"
    },
    {
        "alternative": "今天吃薯条，喝奶茶",
        "channelNames": [
            "QQQQT",
            "ETQWW"
        ],
        "contactWechat": "9509625",
        "endDate": 1513085282816,
        "isOnGoing": false,
        "keyToDate": {
            "2014-06-19": 288,
            "2018-04-17": 273
        },
        "linkedList": [
            "TQTRW",
            "TQRTT"
        ],
        "mapTest": {
            "TTRTT": 28259961,
            "ERQTE": 2032971
        },
        "nameSet": [
            "WQQWQ",
            "WQTWT"
        ],
        "price": 270,
        "studentId": "aef4dd19a72843658b56790fef40c3b6",
        "subTasks": [
            {
                "beginDate": 1513085282810,
                "channelNames": [
                    "TERTR",
                    "WQTTE"
                ],
                "endDate": 1513085282816,
                "price": 250,
                "title": "第7个标题"
            },
            {
                "beginDate": 1513085282819,
                "channelNames": [
                    "QQQEE",
                    "TTQQR"
                ],
                "endDate": 1513085282810,
                "price": 297,
                "title": "第0个标题"
            }
        ],
        "testDate": "2012-09-11",
        "testTime": "2018-02-15 14-36-43",
        "title": "第5个标题"
    }
]
```
	
# 项目文件结构及实现

### 基础知识
你需要了解：
* 泛型
* 反射
* 反射处理泛型和数组

### 这是一张数据生成器的tree结构图（windows自带tree /F命令，你也可以试试）
![avatar](https://raw.githubusercontent.com/JasonFirst/DataGenerator/master/%E6%96%87%E4%BB%B6%E7%BB%93%E6%9E%84%E5%9B%BE.png)

其中：
* config：        数据生成配置项
* processer：     数据生成处理中心（如StringGenerator用来随机生成一些字符串）
* type：          内容生成器。（基础数据类型也使用这些类）
* utils：         整个项目的终点站，提供一些可调用的方法。

![avatar](https://raw.githubusercontent.com/JasonFirst/DataGenerator/master/%E6%95%B0%E6%8D%AE%E7%94%9F%E6%88%90%E5%99%A8%E8%A7%A3%E6%9E%90%E5%9B%BE.png)

* 处理中心通过配置中心的定义来生成数据，如果你没有提供，则系统自动创建一个配置对象。
* 而配置中心，存放着type包下的全部内容生成器。你也可以定义一个，然后put到配置中心去。

![avatar](https://raw.githubusercontent.com/JasonFirst/DataGenerator/master/%E6%95%B0%E6%8D%AE%E7%94%9F%E6%88%90%E5%99%A8%E5%A4%84%E7%90%86%E4%B8%AD%E5%BF%83%E7%BB%93%E6%9E%84.png)

这是整个GeneratorProcesser（处理中心）的方法。
其中为4种泛型分别定义了生成方式：
* getObjectByClassType              基础Class类型，包括基本数组
* getObjectParameterizedType        泛型参数化类型（如：List<T>）
* getObjectGenericArrayType         泛型数组（如：List<T>[]，因不常用目前未实现，欢迎补充）
* getObjectWildcardType		    通配符类型（如：? extends classA 中的?，因不常用目前未实现，欢迎补充）

![avatar](https://raw.githubusercontent.com/JasonFirst/DataGenerator/master/%E5%AF%B9%E8%B1%A1%E7%94%9F%E6%88%90%E5%99%A8%E5%A4%84%E7%90%86%E4%B8%AD%E5%BF%83%E5%86%85%E9%83%A8%E9%80%BB%E8%BE%91%20.png)

1. 最初始的入口为generateNormalObject
2. 当找到对应的“内容生成器”时，直接处理并返回。
3. 当未找到时，则使用反射找到所有的字段，然后根据每个字段的类型分别查找“内容生成器”去处理并赋值。
4. 如果某个依然无法找到内容生成器，则再进行第2二步的分解字段和第3步的处理。（以此来处理多重嵌套的VO）
5. 如此递归处理，直到得到一个完整的对象，到此为止一个填充慢数据的VO就生成了。
