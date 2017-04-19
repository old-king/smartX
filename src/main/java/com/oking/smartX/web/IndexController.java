package com.oking.smartX.web;

import com.oking.smartX.annotation.Controller;
import com.oking.smartX.annotation.RequestMapping;
import com.oking.smartX.annotation.RequestMethod;

/**
 * @author 谢青
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @date 2017/4/19 0019 17:57
 * ${tags}
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping
    public String index(){
        return "hello world!";
    }

    @RequestMapping(value="/post",method = RequestMethod.POST)
    public String post(){
        return "post hello world!";
    }
}
