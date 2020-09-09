### 接口进行测试，观察结果分析

在上面 Controller 类中定义了四个接口，分别为：

- /normal： 正常接口信息，不抛出异常。
- /rsexception： 抛出一个自定义的异常，并在 ExcepitonHandler 类中设置了单独对此异常进行处理的异常处理器。
- /myexception： 抛出一个自定义的异常，并在 ExcepitonHandler 类中设置了单独对此异常进行处理的异常处理器。
- /exception： 抛出一个空指针异常，在 ExcepitonHandler 类中没有单独针对此异常做处理器，所以调用此接口用于测试全局异常捕捉。

**接口：/normal：**

```json
{
    "code": 1000,
    "message": "成功",
    "data": "正常"
}
```

**接口：/rsexception：**

```json
{
    "code": 1001,
    "message": "NullPointerException 空指针异常信息",
    "data": ""
}
```

**接口：/myexception：**

```json
{
    "code": 1002,
    "message": "MyException 自定义异常信息",
    "data": ""
}
```

**接口：/exception：**

```json
{
    "code": 9999,
    "message": "未知的错误!",
    "data": ""
}
```

​       调用上面的接口分别得到了异常处理器返回的 Json 格式的消息，根据测试结果可知，当外部访问 Controller 层接口时，如果发生异常则应设置将异常上抛给异常处理器处理，检测如果存在出现的异常类型监听的异常处理器则进行异常处理，如果不存在则继续查是否存在异常的父类 Exception 异常处理器，存在则进行处理，不存在则将异常信息丢弃。