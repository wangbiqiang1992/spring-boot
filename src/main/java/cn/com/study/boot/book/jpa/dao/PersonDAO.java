package cn.com.study.boot.book.jpa.dao;

import cn.com.study.boot.book.jpa.po.PersonPO;
import cn.com.study.boot.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

public interface PersonDAO extends JpaRepository<PersonPO,String>,JpaSpecificationExecutor<PersonPO> {

    Collection<NameOnly> findByLastName(String lastName);

    Collection<NameOnly2> findTop10ByLastName(String lastName);

    Collection<PersonPO> findPersonPOByLastName(String lastName);

    /*这种情况下，只会返回地址不为空的数据*/
    Collection<PersonSummary> readAllByLastName(String lastName);

    Collection<PersonPO> queryByLastName(String lastName);

    Collection<PersonDTO> readTop9ByLastName(String lastName);
}

interface NameOnly{
    String getFirstName();
    String getLastName();
    @Value("#{target.firstName + ' '+ target.lastName}")
    String getFullName();

    @Value("#{@nameStrategy.allName(target)}")
    String getAllName();
}

interface NameOnly2{
    @Value(value = "#{args[0] + ' ' + target.firstName + '!'}")
    String getSalutation(String prefix);
}

interface PersonSummary{
    String getFirstName();
    String getLastName();
    AddressSummary getAddress();

    interface AddressSummary{
        String getCity();
    }
}