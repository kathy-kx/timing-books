package com.kxzhu.json;

import com.google.gson.reflect.TypeToken;
import com.kxzhu.pojo.Person;

import java.util.List;

/**
 * @ClassName PersonListType
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-25 22:25
 */
public class PersonListType extends TypeToken<List<Person>> {
    //TypeToken的泛型就是 我们要将字符串(personListJsonString)转换回去的类型
}
