package top.meethigher.jpaentitylistenertest.entity;

import org.hibernate.annotations.GenericGenerator;
import top.meethigher.jpaentitylistenertest.common.CommonField;
import top.meethigher.jpaentitylistenertest.entitylistener.TestEntityListener;

import javax.persistence.*;
/**
 * @author chenchuancheng
 * @since 2021/11/19 9:46
 */
@Entity
@EntityListeners(TestEntityListener.class)
public class TestEntity {
    @Id
    @Column(name = "id", length = 36, nullable = false)
    //这个是hibernate的注解/生成32位UUID
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String name;

    @Embedded
    private CommonField commonField;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommonField getCommonField() {
        return commonField;
    }

    public void setCommonField(CommonField commonField) {
        this.commonField = commonField;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", commonField=" + commonField +
                '}';
    }
}
