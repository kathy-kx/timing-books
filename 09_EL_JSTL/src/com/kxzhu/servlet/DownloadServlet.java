package com.kxzhu.servlet;
/**
 * @author kxzhu
 * @create 2022-09-29 11:17
 */

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Base64.Encoder;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取要下载的文件名（先写死）
        String downloadFileName = "26.jpeg";

        //2.读取要下载的文件内容 (通过 ServletContext 对象可以读取)
        ServletContext servletContext = getServletContext();
        //这些应该放在前面：
        String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
        System.out.println("下载的文件类型：" + mimeType);
        //4、在回传前，通过响应头告诉客户端返回的数据类型
        response.setContentType(mimeType);

        //5、还要告诉客户端收到的数据是用于下载使用(还是使用响应头)
        //response.setHeader("Content-Disposition","attachment;filename="+ downloadFileName);//filename也可以自己指定
        String userAgent = request.getHeader("User-Agent");
        if(userAgent.contains("Firefox")){
            String str = "ttachment;filename=" + "?utf-8?B?" +
                    Base64.getEncoder().encode("夜叉.jpeg".getBytes("UTF-8")) + "?=";
            response.setHeader("Content-Disposition", str);
        }else {
            response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("夜叉.jpeg","UTF-8"));
            //解决谷歌浏览器中文乱码：URL编码URLEncoder
        }

        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadFileName);

        OutputStream outputStream = response.getOutputStream();//获取响应的输出流？？
        IOUtils.copy(resourceAsStream,outputStream);//读取输入流全部的数据，复制给输出流，输出给客户端

        //

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
