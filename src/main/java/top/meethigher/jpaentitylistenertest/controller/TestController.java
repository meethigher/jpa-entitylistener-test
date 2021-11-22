package top.meethigher.jpaentitylistenertest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.meethigher.jpaentitylistenertest.entity.TestEntity;
import top.meethigher.jpaentitylistenertest.repository.TestRepository;

@RestController
public class TestController {
    @Autowired
    private TestRepository testRepository;

    @RequestMapping(value = "/insert")
    public void insert() {
        TestEntity test = new TestEntity();
        test.setName("陈传诚");
        testRepository.save(test);
    }

    @RequestMapping(value="/update")
    public void update() {
        TestEntity test = new TestEntity();
        test.setId("40288a817d35eafc017d35eaff6c0000");

        test.setName(""+Math.random());
        testRepository.save(test);
    }
}
