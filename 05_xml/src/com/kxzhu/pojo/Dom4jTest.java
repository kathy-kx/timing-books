package com.kxzhu.pojo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author kxzhu
 * @create 2022-09-14 15:18
 */
public class Dom4jTest {

    //读取books.xml文件, 解析获取Document对象
    @Test
    public void test1() throws Exception {
        //创建一个SaxReader输入流，读取xml配置文件，生成Document对象
        SAXReader saxReader = new SAXReader();

        //这个对象用于读取xml文件，返回一个 Document。
        Document document = saxReader.read("src/books.xml");
        //idea中,单元测试Test里，默认路径在module下；main方法里，默认路径在项目下

        System.out.println(document);//打印到控制台，看看是否创建成功
    }


    //读取books.xml文件生成Book类
    @Test
    public void test2() throws Exception {
        //1、读取books.xml文件
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/books.xml");

        //2、通过Document对象,获取根元素（books）
        Element rootElement = document.getRootElement();
//        System.out.println(rootElement);//[Element: <books attributes: []/>]

        //3、通过根元素，获取子元素（book标签对象）
        //element()和elements() 都是通过标签名查找子元素。
        // element()是返回一个子标签/元素，elements()是查找到多个子标签/元素，用List承装
        List<Element> books = rootElement.elements("book");

        //4、遍历，处理每个book标签转换为Book类
        for (Element book: books){
            //asXML():把标签对象，转换为标签字符串
//            System.out.println(book.asXML());//每一个book标签
            Element nameElement = book.element("name");
//            System.out.println(nameElement.asXML());//得到每一个name标签
            //getText(): 可以获取标签中的文本内容
            String nameText = nameElement.getText();

            //elementText() 直接获取指定标签名的文本内容
            String priceText = book.elementText("price");
            String authorText = book.elementText("author");

            //获取属性的值
            String snValue = book.attributeValue("sn");

            System.out.println(new Book(snValue, nameText, new BigDecimal(priceText), authorText));

        }
    }
}
