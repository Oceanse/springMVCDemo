package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 前端控制器DispatcherServlet对浏览器发送的请求进行了统一的处理，
 * 但是具体的请求有不同的处理过程，因此需要创建处理具体请求的类，即请求控制器
 * 请求控制器中每一个处理请求的方法成为控制器方法
 * <p>
 * 请求处理过程：
 * 浏览器发送请求，若请求地址符合前端控制器的url-pattern，该请求就会被前端控制器DispatcherServlet处理。前端控制器会读取SpringMVC的核心配置文件，
 * 通过扫描组件找到控制器，将请求地址和控制器中@RequestMapping注解的value属性值进行匹配，若匹配成功，该注解所标识的控制器方法就是处理请求的方法。
 * 处理请求的方法需要返回一个字符串类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，通过Thymeleaf对视图进行渲染，最终转发到视图所对应页面
 */
@Controller
public class HelloController {


    /**
     * @return
     * @RequestMapping注解：处理请求和控制器方法之间的映射关系, /表示的当前工程的上下文路径
     * localhost:8080/springMVCdemo1/
     * 处理请求的方法需要返回一个字符串类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，通过Thymeleaf对视图进行渲染，最终转发到视图所对应页面
     */
    @RequestMapping("/")
    public String index() {
        return "index";  //设置视图名称
    }


    @RequestMapping("/success")
    public String toSuccess() {
        return "success";  //设置视图名称
    }


    /**
     * @return
     * @RequestMapping注解：处理请求和控制器方法之间的映射关系, /表示的当前工程的上下文路径: localhost:8080/springMVCdemo1/
     * value属性是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址所对应的请求
     * <p>
     * method属性是一个RequestMethod类型的数组，表示该请求映射能够匹配多种请求方式的请求,当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，则浏览器报错405：Request method ‘xxx’ not supported
     * SpringMVC中提供了派生注解：@GetMapping, @PostMapping,@PutMapping等， 但是目前浏览器只支持get和post，若在form表单提交时，为method设置了其他请求方式的字符串（put或delete），则按照默认的请求方式get处理
     * <p>
     * params属性通过请求的请求参数匹配请求映射， 是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系
     * “param”：要求请求映射所匹配的请求必须携带param请求参数
     * “!param”：要求请求映射所匹配的请求必须不能携带param请求参数
     * “param=value”：要求请求映射所匹配的请求必须携带param请求参数且param=value
     * “param!=value”：要求请求映射所匹配的请求必须携带param请求参数但是param!=value
     * 若当前请求满足@RequestMapping注解的value和method属性，但是不满足params属性，此时页面回报错400：Parameter conditions “xxx” not met for actual request parameters "xxx"
     * <p>
     * headers属性通过请求的请求头信息匹配请求映射,是一个字符串类型的数组，可以通过四种表达式设置请求头信息和请求映射的匹配关系
     * “header”：要求请求映射所匹配的请求必须携带header请求头信息
     * “!header”：要求请求映射所匹配的请求必须不能携带header请求头信息
     * “header=value”：要求请求映射所匹配的请求必须携带header请求头信息且header=value
     * “header!=value”：要求请求映射所匹配的请求必须携带header请求头信息且header!=value
     * 若当前请求满足@RequestMapping注解的value和method属性，但是不满足headers属性，此时页面显示404错误，即资源未找到
     * <p>
     * 处理请求的方法需要返回一个字符串类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，通过Thymeleaf对视图进行渲染，最终转发到视图所对应页面
     */
    @RequestMapping(value = {"/target", "target2"},
            method = {RequestMethod.GET, RequestMethod.POST},
            params = {"requiredParam", "requiredParam2!=myValue2"})
    public String toTarget() {
        return "target"; //设置视图名称
    }


    /**
     * SpringMVC支持路径中的占位符
     * 原始方式：/deleteUser?id=1
     * rest方式：/deleteUser/1
     * 占位符常用于RESTful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中,
     * 可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，在通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参
     *
     * ？：表示任意的单个字符
     * *：表示任意的0个或多个字符
     * **：表示任意的一层或多层目录
     * @param id
     * @param username
     * @return
     */
    @RequestMapping("/testRest/{id}/**/{username}")
    public String testRest(@PathVariable("id") String id, @PathVariable("username") String username){
        System.out.println("id:"+id+",username:"+username);
        return "success";
    }

}
