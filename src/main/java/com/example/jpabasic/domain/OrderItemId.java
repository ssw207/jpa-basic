package com.example.jpabasic.domain;

import lombok.EqualsAndHashCode;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@EqualsAndHashCode
@Embeddable
public class OrderItemId implements Serializable {

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ITEM_ID")
    private Long itemId;
}
