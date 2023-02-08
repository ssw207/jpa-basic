package com.example.jpabasic.service;

import com.example.jpabasic.domain.Car;
import com.example.jpabasic.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(Long id, String name) {
        Car car = carRepository.findLockById(id);
        car.setName(name);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Long id, String name) {
        Car car = new Car();
        car.setId(id);
        car.setName(name);
        carRepository.save(car);
    }
}
