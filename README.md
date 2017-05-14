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
	config.putGenerator("timeString",new StringGenerator("201%s-05-03","12345670"));
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
    "channelNames": [
        "WWEWE",
        "WQTRT",
        "TERRW",
        "WEQTW"
    ],
    "contactWechat": "3369792",
    "endDate": 1494773579178,
    "isOnGoing": false,
    "keyToDate": {
        "TWTWQ": 259,
        "ERTQQ": 262,
        "TTEEQ": 276,
        "WRRWW": 258
    },
    "nameSet": [
        "TTRRQ",
        "EQETE",
        "WWTQE",
        "QRTWT"
    ],
    "price": 261,
    "subTasks": [
        {
            "beginDate": 1494773579176,
            "channelNames": [
                "QTQWE",
                "TTRRT",
                "EQEQW",
                "QETTE"
            ],
            "endDate": 1494773579175,
            "price": 262,
            "title": "TRTWE"
        },
        {
            "beginDate": 1494773579174,
            "channelNames": [
                "TWRTT",
                "TWEQQ",
                "WRRQT",
                "RRRWQ"
            ],
            "endDate": 1494773579177,
            "price": 264,
            "title": "EWRRR"
        },
        {
            "beginDate": 1494773579175,
            "channelNames": [
                "QQQQT",
                "RRQQR",
                "REEEE",
                "EWWTQ"
            ],
            "endDate": 1494773579183,
            "price": 288,
            "title": "TWEQE"
        },
        {
            "beginDate": 1494773579176,
            "channelNames": [
                "RQRQE",
                "RTWEW",
                "QTRQW",
                "TTTQR"
            ],
            "endDate": 1494773579179,
            "price": 274,
            "title": "RWERT"
        }
    ],
    "timeString": "2015-05-03",
    "title": "QETRE"
}
```
