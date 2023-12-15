package com.kxzhu.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kxzhu.pojo.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonTest
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-25 10:39
 */
public class JsonTest {

    /*
    javaBean 和 json 的互转
     */
    @Test
    public void test1(){
        Person person = new Person(1,"甘雨");

        //创建 Gson 对象实例
        Gson gson = new Gson();

        //toJson 方法可以把 java 对象转换成为 json 字符串
        String personJsonString = gson.toJson(person);
        System.out.println(personJsonString);                   //{"id":1,"name":"甘雨"}

        // fromJson 把 json 字符串转换回 Java 对象
        Person person1 = gson.fromJson(personJsonString, Person.class);
        System.out.println(person1);                            //Person{id=1, name=甘雨}


    }

    /*
    List 和 json 的互转
     */
    @Test
    public void test2(){
        List<Person> personList = new ArrayList<>();

        personList.add(new Person(1,"胡桃"));
        personList.add(new Person(2,"七七"));

        Gson gson = new Gson();

        //把 List 转换为 json 字符串
        String personListJsonString = gson.toJson(personList);
        System.out.println(personListJsonString);// [{"id":1,"name":"胡桃"},{"id":2,"name":"七七"}]
        //数组，里面每个元素都是json

        //json字符串转换为List
        List<Person> list = gson.fromJson(personListJsonString, new PersonListType().getType());//  [Person{id=1, name=胡桃}, Person{id=2, name=七七}]
        System.out.println(list);
        Person person0 = list.get(0);
        System.out.println(person0);


    }

    /*
    Map 和 json 的互转
     */
    @Test
    public void test3(){
        Map<Integer, Person> personMap = new HashMap<>();

        personMap.put(1,new Person(1,"胡桃"));
        personMap.put(2,new Person(2,"七七"));

        Gson gson = new Gson();
        // 把 map 集合转换成为 json 字符串
        String personMapJsonString = gson.toJson(personMap);
        System.out.println(personMapJsonString);//  {"1":{"id":1,"name":"胡桃"},"2":{"id":2,"name":"七七"}}

        //Map<Integer, Person> personMap2 = gson.fromJson(personMapJsonString, new PersonMapType().getType());
        Map<Integer, Person> personMap2 = gson.fromJson(personMapJsonString, new TypeToken<Map<Integer,Person>>(){}.getType());//匿名实现类的匿名对象
        System.out.println(personMap2);//   {1=Person{id=1, name=胡桃}, 2=Person{id=2, name=七七}}
        System.out.println(personMap2.get(1)); //   Person{id=1, name=胡桃}
    }


    }
