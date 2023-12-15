package com.kxzhu.servlet; /**
 * @author kxzhu
 * 这里是使用tomcat10时，使用api不成功，所以去配置了tomcat9，使用UploadServlet2
 * @create 2022-09-27 09:25
 */

//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //处理上传的数据
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    //System.out.println("文件上传成功");
    //    ServletInputStream inputStream = request.getInputStream();
    //
    //    //浏览器以流的形式发送数据，服务器必须以流的形式接收收据。
    //    byte[] buffer = new byte[1024];
    //    int len;
    //    while ( (len = inputStream.read(buffer) )!= -1){
    //        System.out.println(new String(buffer, 0, len));
    //    }


    }
}
