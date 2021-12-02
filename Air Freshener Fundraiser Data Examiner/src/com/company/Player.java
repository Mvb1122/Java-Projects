package com.company;

public class Player {
    int numSold;
    String name;
    Team team;

    public Player(String name) {
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.addMember(this);
    }
}
