package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
