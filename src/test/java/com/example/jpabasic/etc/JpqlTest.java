package com.example.jpabasic.etc;

import com.example.jpabasic.domain.Car;
import com.example.jpabasic.domain.Child;
import com.example.jpabasic.domain.Parent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@DataJpaTest
public class JpqlTest {

    @Autowired
    EntityManager em;

    @Test
    void jpql_카테시안_곱() {
        Parent parent = new Parent();
        em.persist(parent);

        em.persist(new Child(parent));
        em.persist(new Child(parent));

        // 1:N은 곱연산 발생
        List<Parent> resultList = em.createQuery("select p from Parent p join fetch p.childList", Parent.class)
                .getResultList();

        for (Parent parent1 : resultList) {
            System.out.println("parent1 = " + parent1);
        }

        // distinct 처리시 곱연산 발생하지 않음
        List<Parent> resultList2 = em.createQuery("select DISTINCT p from Parent p join fetch p.childList", Parent.class)
                .getResultList();

        for (Parent parent1 : resultList2) {
            System.out.println("parent1 = " + parent1);
        }

    }
}
