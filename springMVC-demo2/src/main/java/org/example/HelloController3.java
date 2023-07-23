package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * SpringMVC中的视图是View接口，视图的作用渲染数据，将模型Model中的数据展示给用户
 */
@Controller
public class HelloController3 {


    /**
     * ThymeleafView
     * 当控制器方法中所设置的视图名称没有任何前缀时，此时的视图名称会被SpringMVC配置文件中所配置的视图解析器解析，
     * 视图名称拼接视图前缀和视图后缀所得到的最终路径，会通过转发的方式实现跳转
     * @return
     */
    @RequestMapping("/testHello")
    public String testHello(){
        return "hello";
    }


    /**
     * 转发视图
     * SpringMVC中默认的转发视图是InternalResourceView
     * SpringMVC中创建转发视图的情况：
     * 当控制器方法中所设置的视图名称以"forward:"为前缀时，创建InternalResourceView视图，
     * 此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析，而是会将前缀"forward:"去掉，
     * 剩余部分作为最终路径通过转发的方式实现跳转
     * 例如"forward:/"，“forward:/employee”
     * @return
     */
    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/testHello";//这里会进行请求转发
    }


    /**
     * 重定向视图
     * SpringMVC中默认的重定向视图是RedirectView
     * 当控制器方法中所设置的视图名称以"redirect:"为前缀时，创建RedirectView视图，此时的视图名称不会被SpringMVC配置
     * 文件中所配置的视图解析器解析，而是会将前缀"redirect:"去掉，剩余部分作为最终路径通过重定向的方式实现跳转
     * 例如"redirect:/"，“redirect:/employee”
     * @return
     */
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/testHello";//这里会进行重定向
    }


}
