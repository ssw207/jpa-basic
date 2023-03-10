package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Team;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TeamResponse {

    private final String name;

    public static TeamResponse of(Team team) {
        return new TeamResponse(team.getName());
    }
}
