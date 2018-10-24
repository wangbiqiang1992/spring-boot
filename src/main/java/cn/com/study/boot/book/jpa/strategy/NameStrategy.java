package cn.com.study.boot.book.jpa.strategy;

import cn.com.study.boot.book.jpa.po.PersonPO;
import org.springframework.stereotype.Component;

@Component
public class NameStrategy {

    public String allName(PersonPO personPO) {
        return "HTZQ HTSC " + personPO.getFirstName() +" "+ personPO.getLastName() + " very cool";
    }
}
