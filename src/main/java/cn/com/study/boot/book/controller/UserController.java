package cn.com.study.boot.book.controller;

import cn.com.study.boot.book.jpa.dao.BookDAO;
import cn.com.study.boot.book.jpa.dao.PersonDAO;
import cn.com.study.boot.book.jpa.dao.UserRepository;
import cn.com.study.boot.book.jpa.po.BookPO;
import cn.com.study.boot.book.jpa.po.UserPO;
import cn.com.study.boot.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BookDAO bookDAO;

    @GetMapping("/users")
    @Transactional(readOnly = true)
    public Object findAllUsers() {
        PageRequest pageRequest = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "lastName"));
        try (Stream<UserPO> stream = userRepository.streamAll(pageRequest)) {
            return stream.collect(Collectors.toList());
        }
    }

    @GetMapping("/users2")
    public Object findAllUsers2() {
        String[] names = new String[1000];
        for (int i = 0; i <= 99; i++) {
            names[i] = "zhang";
        }
        long time1 = System.currentTimeMillis();
        Object obj = userService.queryNamedUser(names);
        long time = System.currentTimeMillis();
        System.out.println("耗费"+(time-time1));
        return obj;
    }

    @GetMapping("/users3")
    public Object findAllUsers3() {
        String[] names = new String[1000];
        for (int i = 0; i <= 99; i++) {
            names[i] = "zhang";
        }
        long time1 = System.currentTimeMillis();
        Object obj = userService.queryNamedUser2(names);
        long time = System.currentTimeMillis();
        System.out.println("耗费"+(time-time1));
        return obj;
    }

    @Autowired
    private PersonDAO personDAO;

    @GetMapping("/person")
    public Object queryPerson(String name){
        return personDAO.findByLastName(name);
    }

    @GetMapping("/person2")
    public Object queryPerson2(String name){
        return personDAO.findPersonPOByLastName(name);
    }

    @GetMapping("/person3")
    public Object queryPerson3(String name){
        return personDAO.readAllByLastName(name);
    }

    @GetMapping("/person4")
    public Object queryPerson4(String name){
        return personDAO.queryByLastName(name);
    }

    @GetMapping("/person5")
    public Object queryPerson5(String name){
        return personDAO.findTop10ByLastName(name);
    }

    @GetMapping("/person6")
    public Object queryPerson6(String name){
        return personDAO.readTop9ByLastName(name);
    }

}
