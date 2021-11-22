package top.meethigher.jpaentitylistenertest.entitylistener;

import org.springframework.util.ObjectUtils;
import top.meethigher.jpaentitylistenertest.common.CommonField;
import top.meethigher.jpaentitylistenertest.entity.TestEntity;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @author chenchuancheng
 * @since 2021/11/19 9:46
 */
public class TestEntityListener {

    @PrePersist
    public void PrePersist(Object entity) {
        System.out.println("进行insert之前");
        if(entity instanceof TestEntity) {
            System.out.println(entity.toString());
            CommonField commonField = ((TestEntity) entity).getCommonField();
            if(ObjectUtils.isEmpty(commonField)) {
                commonField=new CommonField();
                commonField.setCreateTime(new Date());
                commonField.setUpdateTime(new Date());
                commonField.setCreateUserId("111");
                commonField.setCreateUserName("ccc");
                ((TestEntity) entity).setCommonField(commonField);
            }
            System.out.println(entity.toString());
        }

    }

    @PostPersist
    public void PostPersist(Object entity) {
        System.out.println("进行insert之后");
    }

    @PreUpdate
    public void PreUpdate(Object entity) {
        System.out.println("进行update之前");
    }

    @PostUpdate
    public void PostUpdate(Object entity) {
        System.out.println("进行update之后");
    }
}
