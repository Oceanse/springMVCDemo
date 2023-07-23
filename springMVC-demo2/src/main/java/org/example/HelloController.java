package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * SpringMVC获取请求参数:通过ServletAPI获取
     * 将HttpServletRequest作为控制器方法的形参，此时HttpServletRequest类型的参数表示封装了当前请求的请求报文的对象
     *
     * @param request
     * @return
     */
    @RequestMapping("/testParam")
    public String testParam(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:" + username + ",password:" + password);
        return "success";
    }

    /**
     * 通过控制器方法的形参获取请求参数
     * 在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/testParam2")
    public String testParam2(String username, String password) {
        System.out.println("username:" + username + ",password:" + password);
        return "success";
    }


    /**
     * 通过控制器方法的形参获取请求参数
     * 在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参
     * <p>
     * 若请求所传输的请求参数中有多个同名的请求参数，前端请求@{/testParam2_2(username='admin2',nickname='cool',nickname='handsome')存在重名参数，此时可以在控制器方法的形参中设置字符串，那么可以：
     * 1 使用字符串数组类型的形参，此参数的数组中包含了每一个数据
     * 2 使用字符串类型的形参，此参数的值为每个数据中间使用逗号拼接的结果
     *
     * @param username
     * @param nickname
     * @return
     */
    @RequestMapping("/testParam2_2")
    public String testParam2_2(String username, String nickname) {
        //前端请求@{/testParam2_2(username='admin2',nickname='cool',nickname='handsome')
        System.out.println("username:" + username + ",nickname:" + nickname);//username:admin2,nickname:cool,handsome
        return "success";
    }

    /**
     * 通过控制器方法的形参获取请求参数
     * 在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参
     * <p>
     * 若请求所传输的请求参数中有多个同名的请求参数，前端请求@{/testParam2_3(username='admin2',nickname='sheep',nickname='goat')存在重名参数，此时可以在控制器方法的形参中设置字符串，那么可以：
     * 1 使用字符串数组类型的形参，此参数的数组中包含了每一个数据
     * 2 使用字符串类型的形参，此参数的值为每个数据中间使用逗号拼接的结果
     *
     * @param username
     * @param nickname
     * @return
     */
    @RequestMapping("/testParam2_3")
    public String testParam2_3(String username, String[] nickname) {
        //前端请求@{/testParam2_2(username='admin2',nickname='sheep',nickname='goat')
        System.out.println("username:" + username + ",nickname:" + Arrays.toString(nickname));//username:admin2,nickname:[sheep, goat]
        return "success";
    }


    /**
     * @param username
     * @param password
     * @return
     * @RequestParam是将请求参数和控制器方法的形参创建映射关系
     * @RequestParam注解一共有三个属性： value：指定为形参赋值的请求参数的参数名
     * required：设置是否必须传输此请求参数，默认值为true
     * 若设置为true时，则当前请求必须传输value所指定的请求参数，若没有传输该请求参数，且没有设置defaultValue属性，则页面报错400：Required String parameter ‘xxx’ is not present；若设置为false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为null
     * defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值
     */
    @RequestMapping("/testParam3")
    public String testParam3(@RequestParam(value = "name", required = false, defaultValue = "ocean") String username,
                             @RequestParam(value = "passwd", required = false, defaultValue ="111") String password) {
        System.out.println("username:" + username + ",password:" + password);
        return "success";
    }


    /**
     * 通过POJO获取请求参数
     * 可以在控制器方法的形参位置设置一个实体类类型的形参，此时若浏览器传输的请求参数的参数名和实体类中的属性名一致，那么请求参数就会为此属性赋值
     *
     * @param user
     * @return
     */
    @RequestMapping("/testpojo")
    public String testPOJO(User user) {
        System.out.println(user);
        return "success";
    }


    @RequestMapping("/testCookie")
    public String testCookie(@CookieValue("JESSIONID") String JESSIONID) {
        System.out.println("JESSIONID="+JESSIONID);
        return "success";
    }
}
