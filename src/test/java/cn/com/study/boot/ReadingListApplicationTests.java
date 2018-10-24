package cn.com.study.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
public class ReadingListApplicationTests {

    @Autowired
    private Environment environment;

    @Test
    public void test(){
        System.out.println(environment.getProperty("server.port"));
    }
}
