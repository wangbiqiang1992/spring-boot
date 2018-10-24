package cn.com.study.boot.book.jpa.po;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Data
@Table(name = "personpo")
public class PersonPO {
    @Id
    String id;

    String firstName,lastName;

    @OneToOne(cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    Address address;

    @Entity
    @Data
    static class Address{
        @Id
        String addressId;
        String zipCode,city,street;
    }
}
