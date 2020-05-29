package cn.timetell.Jsoup.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Timetellu on 2019/9/26.
 */
public class TestJsoup {
    public static void main(String[] args) throws Exception {
        //调用 getResource() 方法后再调用 toURI() 方法，注意此时的返回值类型为 URI，而不是 URL
        String path = TestJsoup.class.getClassLoader().getResource("student.xml").toURI().getPath();
        //解析xml文档，加载文档进内存，获取dom树
        Document document = Jsoup.parse(new File(path), "Utf-8");
        Elements elements = document.getElementsByTag("name");   //Elements extends ArrayList<Element>
        for (Element element : elements) {     //获取element对象
            String text = element.text();      //获取数据
            System.out.println(text);
        }

    }
}
