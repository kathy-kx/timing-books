package com.kxzhu.servlet; /**
 * @author kxzhu
 * @create 2022-09-27 16:50
 */
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
//ServletFileUpload.isMultipartContent(req)有红线的是因为
// Tomcat10/lib包中，servlet-api.jar包下面是jakarta.servlet
//需要重新配置Tomcat9版本。
// 都需要改哪里：
// 1、Project Structure中，将新版本中lib的jar包添加到本工程的lib中；
// 2、Edit Configuration配置信息的端口号；
// 3、import改成javax而不是jakarta；
// 4、jsp页面的action端口号改成新的


//问题：web.xml

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用fileupload类库：
        //1 先判断上传的数据是否多段数据(多段的数据，才是文件上传的)
        if(ServletFileUpload.isMultipartContent(request)){
            request.setCharacterEncoding("UTF-8");//POST 请求的中文乱码解决

            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();//创建FileItemFactory工厂实现类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);//创建用于解析上传数据的类ServletFileUpload类

            try {
                //解析上传的数据，得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);

                //判断每个表单项 是普通类型 还是上传文件？
                for (FileItem fileItem: list){
                    if(fileItem.isFormField()){
                        //该表单项是普通表单项
                        System.out.println("表单项的name属性值：" + fileItem.getFieldName());
                        System.out.println("表单项的value属性值：" + fileItem.getString("UTF-8"));
                    }else{
                        //该表单项是上传文件
                        System.out.println("表单项的name属性值："+ fileItem.getFieldName() );
                        System.out.println("上传的文件名：" + fileItem.getName());

                        fileItem.write(new File("/Users/xiwang/Desktop/test/" + fileItem.getName() ));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
