package com.person;

/**
 * @author:kevinfoo
 * @date:2020/07/11
 * @file:com.person
 */
public class PersonServiceImpl {

    public void register(Person person) {
        // 1.查询有没有这个用户
        PersonDaoImpl personDao = new PersonDaoImpl();
        Person person2 = personDao.queryName(person.getName());
        // 2.有的话就插入
        if (person2 == null) {
            personDao.add(person);
        } else {
            System.out.println("已存在");
        }
    }
}
