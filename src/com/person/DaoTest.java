package com.person;

import java.util.List;

/**
 * @author:kevinfoo
 * @date:2020/07/08
 * @file:com.person
 */
public class DaoTest {

    public static void main(String[] args) {
        personAdd();
//        personUpdate();
//        personDelete();
//        personQuery();
//        personQueryAll();
    }

    public static void personAdd() {

        Person person = new Person("wong", 24, DateUtils.strToUtil("1998-10-24"), "kk@foxmail.com", "北京市海淀区");
        PersonDaoImpl personDao = new PersonDaoImpl();
        int result = personDao.add(person);
        if (result == 1) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }


    }

    public static void personUpdate() {

        Person person = new Person(2, "teresa", 10, null, "teresa@vip.com", "南昌");
        PersonDaoImpl personDao = new PersonDaoImpl();
        int result = personDao.update(person);
        if (result == 1) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败");
        }
    }

    public static void personDelete() {

        PersonDaoImpl personDao = new PersonDaoImpl();
        int result = personDao.delete(2);
        if (result == 1) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    public static void personQuery() {

        PersonDaoImpl personDao = new PersonDaoImpl();
        Person person = personDao.query(3);
        if (person != null) {
            System.out.println(person);
        } else {
            System.out.println("查询失败");
        }

    }

    public static void personQueryAll() {

        PersonDaoImpl personDao = new PersonDaoImpl();
        List<Person> personList = personDao.queryAll();
        if (personList != null) {
            System.out.println(personList);
        } else {
            System.out.println("查询失败");
        }

    }
}
