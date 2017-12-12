# DataGenerator
## 数据生成器，轻松生成模拟数据，用作接口联调。
### 应用场景：
	1.某些业务的数据生成过程较为复杂，但是需要先看到数据。
	2.功能的实现方法还需要讨论，但是需要先看到数据。
	3.前端工程师明天请假，今天必须先把接口格式调通赶项目进度。
	4.单元测试时需要大批量测试数据，但手工创建相当消耗时间。

### 测试类：
```
public class Task {
    private String title;
    private Integer price;
    private Date endDate;
    private List<String> channelNames;
    private Set<String> nameSet;
    private Map<String, Integer> keyToDate;
    private List<SubTask> subTasks;
    private Boolean isOnGoing;
    private String contactWechat;
    private String timeString;   
}
```

### 简单上手使用方式：
```
public static void main(String[] args) throws Exception {
	Task one = GeneratorUtils.getOne(Task.class);
	List<Task> tasks = GeneratorUtils.getList(Task.class);
	System.out.println(JSONObject.toJSONString(one));
	System.out.println(JSONObject.toJSONString(tasks));
}
```


### 配置使用方式：
```
public static void main(String[] args) throws Exception {
	GenerateConfig config = new GenerateConfig();
	config.putGenerator(new StringGenerator(5,"QWERT"));
	config.putGenerator(new IntegerGenerator(250,300));
	config.putGenerator(new DateGenerator(DateGenerator.fluctuate_milltsecond, 3, 16));
	config.putGenerator("contactWechat",new StringGenerator(7,"1234567890"));
	config.putGenerator("timeString",new StringGenerator("201%s-%s2-03","12345670","01"));
	config.putGenerator("alternative",new StringGenerator("今天吃%s，喝%s",
					new String[]{"汉堡","薯条"},new String[]{"可乐","奶茶","水"}));
	config.setOpenMessageTip(false);
	config.setGenerateCount(4);
    
	Task superOne = GeneratorUtils.getOne(Task.class, config);
	List<Task> superTasks = GeneratorUtils.getList(Task.class,config);
	System.out.println(JSONObject.toJSONString(superOne));
	System.out.println(JSONObject.toJSONString(superTasks));
}
```

### 结果展示：
```
{
    "alternative": "今天吃汉堡，喝奶茶",
    "timeString": "2010-05-03",
    "title": "RRWRT",
    "contactWechat": "2408115",
    "endDate": 1513082505208,
    "isOnGoing": true,
    "price": 258,
    "channelNames": [
        "QWQEE",
        "QWWEE",
        "QWWWQ",
        "EQWWQ"
    ],
    "keyToDate": {
        "ERRTW": 254,
        "QQRET": 260,
        "QWQET": 298,
        "WEQWW": 284
    },
    "linkedList": [
        "RTQWQ",
        "TQEEE",
        "QEQRQ",
        "EERQW"
    ],
    "mapTest": {
        "RQEQE": 2023531,
        "WRRWT": 2772420,
        "ERRRR": 16052781,
        "TWQTW": 11492533
    },
    "nameSet": [
        "QEERE",
        "TEETW",
        "EWEEQ",
        "QWQRW"
    ],
    "subTasks": [
        {
            "beginDate": 1513082505213,
            "channelNames": [
                "RWEWR",
                "QQRER",
                "RQTER",
                "EQQER"
            ],
            "endDate": 1513082505217,
            "price": 294,
            "title": "WRQEE"
        },
        {
            "beginDate": 1513082505210,
            "channelNames": [
                "RTEEQ",
                "QTWRW",
                "QTQWW",
                "ERRTW"
            ],
            "endDate": 1513082505210,
            "price": 267,
            "title": "TQQQT"
        },
        {
            "beginDate": 1513082505213,
            "channelNames": [
                "WWQWR",
                "WRERR",
                "TQQWE",
                "QEWEW"
            ],
            "endDate": 1513082505211,
            "price": 295,
            "title": "TRRQW"
        },
        {
            "beginDate": 1513082505215,
            "channelNames": [
                "RTTQW",
                "TWERT",
                "EREWE",
                "TWWQQ"
            ],
            "endDate": 1513082505217,
            "price": 290,
            "title": "EEQRW"
        }
    ]
}
```

