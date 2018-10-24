package cn.com.study.boot.book.jpa.dao;

import cn.com.study.boot.book.jpa.po.BookPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<BookPO,Long>,JpaSpecificationExecutor<BookPO> {

    List<BookPO> findByReader(String reader);
}
