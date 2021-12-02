package com.company;

public class Team {
    String name;
    Player[] members;
    int totalNumSold;

    public Team(String name) {
        this.name = name;
        members = new Player[0];
    }

    public void addMember(Player member) {
        // Append the new member onto the end of the old members array.
        Player[] newMembersArray = new Player[members.length + 1];
        System.arraycopy(members, 0, newMembersArray, 0, members.length);
        newMembersArray[newMembersArray.length - 1] = member;
        members = newMembersArray;
    }

    public boolean hasPlayer(String name) {
        return playerIndex(name) != -1;
    }

    private int playerIndex(String name) {
        for (int i = 0; i < members.length; i++) {
            if (members[i].name.equals(name)) return i;
        }
        return -1;
    }

    public void increasePlayer(String name) {
        if (hasPlayer(name)) {
            totalNumSold++;
            members[playerIndex(name)].numSold++;
        }
    }
}
