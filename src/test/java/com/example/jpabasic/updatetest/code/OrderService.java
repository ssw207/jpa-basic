package com.example.jpabasic.updatetest.code;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;

	@Transactional
	public void showPayDetailAmount(String orderNo) {
		// 영속성 컨텍스트 시작
		log.info(">>>>>> findAll");
		orderRepository.findAll();
		// 조회결과 1차 캐시에 저장
		// 영속성 컨텍스트 종료 - 1차캐시와 원본 엔티티를 비교해 다르면 더티체킹으로 업데이트 쿼리 실행
	}
}