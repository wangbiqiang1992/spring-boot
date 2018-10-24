package cn.com.study.boot.book.jpa.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "A_BOOK")
public class BookPO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reader;

    private String isbn;

    private String title;

    private String author;

    private String description;
}
