package cn.com.study.boot.book.jpa.dao;

import cn.com.study.boot.book.jpa.po.UserPO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;
import java.util.stream.Stream;

@Repository
public interface UserRepository extends JpaRepository<UserPO,Long>,JpaSpecificationExecutor<UserPO> {

    @Query("select u from UserPO u")
    Stream<UserPO> findAllByFirstNameNotNull();

    @Query("select u from UserPO u")
    Stream<UserPO> streamAll(Pageable pageable);

    @Async
    Future<UserPO> findByFirstName(String firstName);

    UserPO readByFirstName(String firstName);

}
