package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    void name() {
        Car car = new Car();
        car.setId(0L);
        assertThat(car.isNew()).isTrue();

        Car save = carRepository.save(car);
        assertThat(car.isNew()).isFalse();
    }
}