package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Transient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Test
    void jpaAuditing테스트() {
        Car car = new Car();
        car.setId(0L);
        assertThat(car.isNew()).isTrue();

        Car save = carRepository.save(car);
        assertThat(car.isNew()).isFalse();

        entityManager.flush();
        entityManager.clear();

        save = carRepository.findById(save.getId()).get();

        assertThat(save.getCreatedDate()).isNotNull();
        assertThat(save.getCreatedBy()).isNotNull();
    }
}