package top.meethigher.jpaentitylistenertest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.meethigher.jpaentitylistenertest.entity.TestEntity;
import top.meethigher.jpaentitylistenertest.repository.TestRepository;

@SpringBootTest
class JpaEntitylistenerTestApplicationTests {

    @Autowired
    private TestRepository testRepository;

    @Test
    void contextLoads() {
        TestEntity test = new TestEntity();
        test.setName("陈传诚");
        testRepository.save(test);
    }

}
