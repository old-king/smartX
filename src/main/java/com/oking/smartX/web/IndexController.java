package com.oking.smartX.web;

import com.oking.smartX.annotation.Controller;
import com.oking.smartX.annotation.RequestMapping;
import com.oking.smartX.annotation.RequestMethod;

/**
 * @author 谢青
 * @Description: 控制层--index
 * @date 2017/4/19 0019 17:57
 * ${tags}
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping
    public String index() {
        return "hello world!";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post(String name, int age) {
        return name + "say: hello world! age:" + age;
    }
}
