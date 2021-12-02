package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static char delimiter = ',';
    static String path = "form_crop.csv";
    static ArrayList<String>[] extData;

    public static void main(String[] args) {
	    // Fetch data, deal.
        String data = "";
        try {
            Scanner s = new Scanner(new File(path));
            while (s.hasNextLine()) data += s.nextLine() + "\n";
        } catch (FileNotFoundException e) {
            System.out.println(path + " not found!");
            e.printStackTrace();
        }

        // Parse data into an arraylist.
            // Create an array which is as long as the input has lines.
        Scanner lineGetter = new Scanner(data);
        extData = new ArrayList[numberOf('\n', data)];
        for (int i = 0; i < extData.length && lineGetter.hasNextLine(); i++) {
            String line = lineGetter.nextLine();
            extData[i] = new ArrayList<>(0);

            // Split contents by the delimiter.
            for (int place = 0; place < line.length(); place++) if (line.charAt(place) == delimiter) {
                // Subtract the chunk from the line, add it to the array.
                String unit = line.substring(0, place);
                line = line.substring(place + 1);
                extData[i].add(unit);
                place = 0;
            }
        }

        // Figure out Teams.
            // Get column information, create a list of teams.
        int teamsColumn = CVSIndex("Seller's Team");
        ArrayList<Team> teams = new ArrayList<>(0);
            // Loop through each column, and add the team if it isn't already on the list.
        for (int i = 1; i < extData.length; i++) {
            for (int teamNum = 0; teamNum < teams.size() + 1; teamNum++) if (!containsTeamWithName(extData[i].get(teamsColumn).trim(), teams)) {
                teams.add(new Team(extData[i].get(teamsColumn).trim()));
            }
        }

        // Figure out Players and add their sold goods to the tally.
        int playersColumn = CVSIndex("Seller");
        int soldColumn = CVSIndex("Quantity");
            // Loop through each column, add the player to their team if they aren't already on it.
        for (int i = 1; i < extData.length; i++) {
            Team playersTeam = findTeam(extData[i].get(teamsColumn).trim(), teams);
            if (!playersTeam.hasPlayer(extData[i].get(playersColumn).trim())) {
                playersTeam.addMember(new Player(extData[i].get(playersColumn).trim()));
            }

            for (int soldNum = 0; soldNum < Integer.parseInt(extData[i].get(soldColumn)); soldNum++)
                playersTeam.increasePlayer(extData[i].get(playersColumn).trim());
        }

        // Output a list of players who have past the five-sold mark.
        System.out.println("People who have sold their 5:");
            // Create an arraylist to hold people who have sold, but not all of theirs.
        ArrayList<Player> peopleWhoHaventSoldFiveYet = new ArrayList<>(0);
        for (Team team : teams) for (Player p : team.members) if (p.numSold >= 5) System.out.printf("\tName: %15s, Number Sold: %s%n", p.name, p.numSold);
                                                              else peopleWhoHaventSoldFiveYet.add(p);

        // Output a list of people who havne't sold their five yet, but have sold at least one.
        System.out.println("\nPeople who have sold at least one, but not five:");
        for (Player p : peopleWhoHaventSoldFiveYet) System.out.printf("\tName: %15s, Number Sold: %s%n", p.name, p.numSold);

        // Output the number sold for each team:
        System.out.println("\nTeam Breakdown:");
        for (Team team : teams) System.out.printf("\tTeam Name: %15s, Total Number Sold: %s%n", team.name, team.totalNumSold);


        System.out.println("Done.");
    }

    private static boolean containsTeamWithName(String name, ArrayList<Team> array) {
        for (Team team : array) {
            if (team.name.equals(name)) return true;
        }
        return false;
    }

    private static Team findTeam(String teamName, ArrayList<Team> array) {
        for (Team team : array) if (team.name.equals(teamName)) return team;
        return null;
    }

    private static int CVSIndex(String column) {
        return getIndexOf(column, extData[0]);
    }

    private static int getIndexOf(String column, ArrayList<String> input) {
        for (int i = 0; i < input.size(); i++) if (input.get(i).equals(column)) return i;
        return -1;
    }

    private static int numberOf(char searchChar, String data) {
        int numOf = 0;
        for (int i = 0; i < data.length(); i++) if (data.charAt(i) == searchChar) numOf++;
        return numOf;
    }
}
