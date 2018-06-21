# DataGenerator
## 数据生成器，轻松生成模拟数据，快速生成JSON。
### 应用场景：
#### 	1.某些业务的代码编写过程较为复杂，但需要快速生成一些随机的模拟数据来做“演示效果”
#### 	2.需要快速生成一份模拟数据提供给前端工程师调接口
#### 	3.单元测试时需要大批量测试数据，但手工填充相当消耗时间。
但不论哪种场景，本插件的本质，都是想要你能快速给VO填充一些数据，之后可以再使用JSON工具转成JSON字符串，让你达到偷懒的目的。

只需要一句代码既可以使用。

### 联调时，需要手动填充测试数据，提供json结果给对接人。你会创建一个Vo对象，一堆set方法。
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
	Task one = GeneratorUtils.getOne(Task.class);			//生成一个VO
	System.out.println(JSONObject.toJSONString(one));
}
```
如果你需要生成一个List<Vo>
```
List<Task> tasks = GeneratorUtils.getList(Task.class);		//生成多个VO
```


### 自定义使用方式（增加一个GenerateConfig参数即可）：
```
public static void main(String[] args) throws Exception {
	GenerateConfig config = new GenerateConfig();
	config.setOpenMessageTip(false);	//关掉的消息提示
	config.setGenerateCount(4);		//集合生成数量
	
  
	Task superOne = GeneratorUtils.getOne(Task.class, config);
	System.out.println(JSONObject.toJSONString(superOne));
	
	List<Task> superTasks = GeneratorUtils.getList(Task.class,config);
	System.out.println(JSONObject.toJSONString(superTasks));
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

为某个字段指定“内容生成器”（一个继承Generator接口的类，你也可以为你的类型设计一个你需要的生成器，很简单，试试看吧。）
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
