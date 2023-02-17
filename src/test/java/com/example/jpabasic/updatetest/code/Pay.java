package com.example.jpabasic.updatetest.code;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
public class Pay {
	private String code;
	private Long amount;

	public Pay() {
		System.out.println("생성자 호출!!");
	}
}