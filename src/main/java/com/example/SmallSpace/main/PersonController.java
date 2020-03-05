package com.example.SmallSpace.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;


    //查看全部
    @RequestMapping(value = "/queryUserInfo", method = RequestMethod.GET)
    public Map sau(HttpServletResponse response) throws IOException {
        List<Student> list = personRepository.findAll();
        return backData(200, "请求成功", list);
//        return list;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map userLogin(@RequestParam(value = "userAccount") String account,
                         @RequestParam(value = "userPassWord") String password) {
        List<Student> list = personRepository.findAll();
        boolean isExit = false;
        Student students = new Student();
        String message = "账号不存在";
        int code = 302;
        for (Student student : list) {
            if (account.equals(student.getUserAccount() + "")) {
                students = student;
                isExit = true;
            }
        }
        if (isExit) {
            if (students.getUser_password().equals(password)) {
                code = 200;
                message = "登录成功";
            } else {
                code = 302;
                message = "密码错误，请重试";
                students = null;
            }
        } else {
            students = null;
        }
        List<Student> studentList = new ArrayList<>();
        studentList.add(students);
        return backData(code, message, studentList);
    }

    //添加一个
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public Map addPerson(@RequestParam(value = "userAccount") Integer name, @RequestParam(value = "userName", defaultValue = "0", required = false) String userName,
                         @RequestParam("userPassWord") String password, @RequestParam(value = "userAge", defaultValue = "0", required = false) int age,
                         @RequestParam(value = "userSex", defaultValue = "0", required = false) int sex,
                         @RequestParam(value = "userBirthday", defaultValue = "0", required = false) long userBirthday) {
        List<Student> list = personRepository.findAll();
        boolean isExit = false;
        for (Student student : list) {
            if (student.getUserAccount() == name) {
                isExit = true;
            }
        }
        if (isExit) {
            return backData(201, "账号已被注册", null);
        } else {
            Student person = new Student();
            person.setUserAccount(name);
            if (userName.equals("0")) {//如果用户名字为0，则将账号作为名字
                person.setUserName(name + "");
            } else {
                person.setUserName(userName);
            }
            person.setUser_password(password);
            person.setUser_birthday(userBirthday);
            person.setUser_age(age);
            person.setUser_sex(sex);
            personRepository.save(person);
            return backData(200, "注册成功", null);
        }
    }

    @GetMapping(value = "/hello/add")
    public Student addPerson1(@RequestParam("id") Integer id, @RequestParam("name") Integer name) {
        Student person = new Student();
        person.setId(id);
//        person.setName(name);
        return personRepository.save(person);
    }


    //修改一个
    @PutMapping(value = "/hello/{id}")
    public Student updatePerson(@PathVariable("id") Integer id, @RequestParam("name") Integer name) {
        Student person = new Student();
        person.setId(id);
//        person.setName(name);
        return personRepository.save(person);
    }

    @GetMapping(value = "/hello/upd")
    public Student updPerson(@RequestParam("id") Integer id, @RequestParam("name") int name) {//@PathVariable("id") Integer id){
        Student person = findOne(id);
//        person.setName(name);
        return personRepository.save(person);
    }

    //查找一个
    @GetMapping(value = "/hello/{id}")
    public Student findOne(@PathVariable("id") Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    //删除
    @GetMapping(value = "/hello/del/{id}")
    public void deletePerson(@PathVariable("id") Integer id) {
        Student person = findOne(id);
        personRepository.delete(person);
    }

    /**
     * 返回数据格式
     *
     * @param code    请求码
     * @param message 返回的消息
     * @param object  数据内容
     * @return
     */
    private Map<String, Object> backData(int code, String message, Object object) {
        Map<String, Object> objects = new LinkedHashMap<>();
        objects.put("code", code);
        objects.put("message", message);
        objects.put("data", object);
        return objects;
    }
}
