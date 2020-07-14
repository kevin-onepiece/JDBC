package com.person;

/**
 * @author:kevinfoo
 * @date:2020/07/11
 * @file:com.person
 */
public class ServiceTest {

    public static void main(String[] args) {

        PersonServiceImpl personService = new PersonServiceImpl();
        Person person = new Person("gavin", 34, DateUtils.strToUtil("1998-04-01"), "gavin@163.com", "江西南昌");
        personService.register(person);

    }

}
