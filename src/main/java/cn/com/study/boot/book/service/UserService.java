package cn.com.study.boot.book.service;

import cn.com.study.boot.book.jpa.dao.UserRepository;
import cn.com.study.boot.book.jpa.po.UserPO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    @Resource
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserPO> queryAllUser() {
        PageRequest pageRequest = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "lastName"));
        try (Stream<UserPO> stream = userRepository.streamAll(pageRequest)) {
            return stream.collect(Collectors.toList());
        }
    }

    public List<UserPO> queryNamedUser(String... names) {
        List<UserPO> userPOs = new ArrayList<>();
        List<Future<UserPO>> futures = new ArrayList<>();
        for (String name : names) {
            Future<UserPO> future = userRepository.findByFirstName(name);
            futures.add(future);
        }
        System.out.println("---");
        futures.forEach(x -> {
            try {
                userPOs.add(x.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return userPOs;
    }

    public List<UserPO> queryNamedUser2(String... names) {
        List<UserPO> userPOs = new ArrayList<>();
        for (String name : names) {
            UserPO future = userRepository.readByFirstName(name);
            userPOs.add(future);
        }
        return userPOs;
    }
}
