package com.example.jpabasic.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.swing.plaf.metal.MetalComboBoxEditor;

class MemberTest {

	@Test
	void 수정() {
		Member member = new Member();

		Orders orders = new Orders();
		orders.changeMember(member);

		assertThat(member.getOrders()).contains(orders);
	}

}