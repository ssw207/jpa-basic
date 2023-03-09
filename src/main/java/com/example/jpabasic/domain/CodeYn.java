package com.example.jpabasic.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CodeYn {
    Y("Y"),
    N("N");

    private final String code;
}
