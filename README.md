参考文章[JPA实体类监听器@EntityListeners注解使用实例_疯狂的蜗牛-CSDN博客_entitylisteners](https://blog.csdn.net/weixin_37968613/article/details/88554236)

[本文源码](https://github.com/meethigher/jpa-entitylistener-test)

这也是来源于工作中的一个小需求，因为产品迭代时，需要给前端创建人，但是由于创建人是在操作记录的表里记录的，如果每次都要进行查询，效率很低，所以统一在数据表里加上创建人名称和创建人id，为了达到方便、批量的目的，就使用了jpa的实体监听器。

创建springboot项目，导入pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.1.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>top.meethigher</groupId>
    <artifactId>jpa-entitylistener-test</artifactId>
    <version>1.0.0</version>
    <name>jpa-entitylistener-test</name>
    <description>chenchuancheng&apos;s demo</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--jpa-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <!--sqlite-->
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.34.0</version>
        </dependency>
        <dependency>
            <groupId>com.github.gwenn</groupId>
            <artifactId>sqlite-dialect</artifactId>
            <version>0.1.2</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

创建配置文件application.yml

```yml
#logging:
#  config: classpath:logback.xml
server:
  port: 9090
  ssl:
    enabled: false
spring:
  datasource:
    driver-class-name: org.sqlite.JDBC
    url: jdbc:sqlite:D:/test.db
  jpa:
    database-platform: org.sqlite.hibernate.dialect.SQLiteDialect
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
  mvc:
    async:
      request-timeout: 30000
```

创建实体，@EntityListeners里面配置的是监听器对象

```java
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
```

创建实体公共创建人对象

```java
@Embeddable
public class CommonField {
    private Date createTime;

    private Date updateTime;

    private String createUserId;

    private String createUserName;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Override
    public String toString() {
        return "CommonField{" +
                "createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserId='" + createUserId + '\'' +
                ", createUserName='" + createUserName + '\'' +
                '}';
    }
}
```

创建jpa实体监听器，里面使用的这四个注解，顾名思义即可。

```java
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
```

