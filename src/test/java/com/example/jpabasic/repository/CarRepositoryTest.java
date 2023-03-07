package com.example.jpabasic.repository;

import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpabasic.domain.Car;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    void jpaAuditing테스트() {
        Car car = new Car();
        car.setId(0L);
        assertThat(car.isNew()).isTrue();

        Car save = carRepository.save(car);
        assertThat(car.isNew()).isFalse();

        em.flush();
        em.clear();

        save = carRepository.findById(save.getId()).get();

        assertThat(save.getCreatedDate()).isNotNull();
        assertThat(save.getCreatedBy()).isNotNull();
    }

    @Test
    void 캐시() {
        Car car = new Car();
        car.setId(0L);

        Car save = carRepository.save(car);
        
        Car car1 = carRepository.findById(save.getId()).get();
        System.out.println("car1 = " + car1);
    }

    @Transactional
    @Test
    void 벌크_업데이트() {
        carRepository.save(new Car(0L));
        carRepository.save(new Car(1L));

        int cnt = carRepository.bulkVersionUpdate(0);
        assertThat(cnt).isEqualTo(2);

        Car car = carRepository.findById(0L).get();
        assertThat(car.getVersion()).isEqualTo(1);
    }
}