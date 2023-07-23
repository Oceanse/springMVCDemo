package org.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @Autowired
    EmployeeDao employeeDao;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getEmployeeList(Model model){
        Collection<Employee> employeeList = employeeDao.getAll();
        model.addAttribute("employeeList", employeeList);
        return "employee_list";
    }

    /**
     * @RequestBody可以获取请求体，需要在控制器方法设置一个形参，
     * 使用@RequestBody进行标识，当前请求的请求体就会为当前注解所标识的形参赋值
     * @param requestBody
     * @return
     */
    @PostMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println("requestBody:"+requestBody);
        return "success";
    }

    /**
     * RequestEntity封装请求报文的一种类型，需要在控制器方法的形参中设置该类型的形参，
     * 当前请求的请求报文就会赋值给该形参，可以通过getHeaders()获取请求头信息，通过getBody()获取请求体信息
     * @param requestEntity
     * @return
     */
    @PostMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity){
        System.out.println("requestHeader:"+requestEntity.getHeaders());
        System.out.println("requestBody:"+requestEntity.getBody());
        return "success";
    }

    /**
     * @ResponseBody用于标识一个控制器方法，可以将该方法的返回值直接作为响应报文的响应体响应到浏览器
     * @return
     */
    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "success";//这里不再是作为视图名称被视图解析器解析，而是直接作为响应数据(响应体)
    }

    /**
     * 这里需要引入jackson依赖
     * @return
     */
    @RequestMapping("/testResponseBody2")
    @ResponseBody
    public Employee testResponseUser(){
        return new Employee(1001,"admin","123456",23);
    }

    /**
     * SpringMVC处理ajax
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/testAjax")
    @ResponseBody
    public String testAjax(String username, String password){
        System.out.println("username:"+username+",password:"+password);
        return "hello,ajax";
    }


}
