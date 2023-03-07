package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

public interface CarRepository extends JpaRepository<Car, Long> {

    /*
        Lock 설저이 없더라도 @Version이 적용된 필드가 엔티티에 있으면 자동으로 낙관적락을 적용한다.
        업데이트 시점에 버전이 증가하고 업데이트시 조회시점 버전과 다르면 에러가 발생한다.
        조회한 엔티티가 트랜잭션이 커밋될 때까지 다른 트랜잭션에 의해 변경되지 않음을 보장한다.
     */
    @Lock(LockModeType.OPTIMISTIC)
    Car findLockById(@Param("id") Long id);

    @Modifying(clearAutomatically = true) // 벌크 쿼리 실행후 엿속성 컨텍스트를 초기화 한다
    @Query("update Car c set c.version= c.version + 1 where c.version >= :version")
    int bulkVersionUpdate(@Param("version") int version);
}
