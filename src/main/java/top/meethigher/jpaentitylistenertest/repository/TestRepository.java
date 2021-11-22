package top.meethigher.jpaentitylistenertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.meethigher.jpaentitylistenertest.entity.TestEntity;
/**
 * @author chenchuancheng
 * @since 2021/11/19 9:46
 */
public interface TestRepository extends JpaRepository<TestEntity,String> {
}
