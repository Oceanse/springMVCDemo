package org.example.demo2;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class HelloController2 {


    @RequestMapping("/upload")
    public String testUp(@RequestParam("myfile") MultipartFile multipartFile) throws IOException {
       if(multipartFile.isEmpty()){
           throw new RuntimeException("上传失败，请选择图片");
       }
        //获取上传的文件的文件名
        String fileName = multipartFile.getOriginalFilename();
        //处理文件重名问题,获取扩展名
        String extensionName = fileName.substring(fileName.lastIndexOf("."));
        //生成新的文件名
        fileName = UUID.randomUUID() + extensionName;
        //获取服务器中image目录的路径，不存在则创建
        File file = new File("./image/");
        if(!file.exists()){
            file.mkdirs();
        }
        String finalPath = "./image/" + fileName;
        //实现上传功能
        multipartFile.transferTo(new File(finalPath));
        return "success";
    }


    @RequestMapping("/testDown")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/1.jpg");
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }

}
