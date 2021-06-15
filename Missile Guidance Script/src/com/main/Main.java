package com.main;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // How long each calculation takes, in seconds:
        long procSpeed = (long) 0.3;

        // How fast the missile goes, per tick.
        double missileSpeed = 1;

        // How close the missile has to be to detonate.
        int proximityDetonation = 3;

        // Coordinates of the missile.
        ArrayList<Double> missileCoordinates = new ArrayList<>();
        missileCoordinates.add((double) 0); // X
        missileCoordinates.add((double) 0); // Y
        missileCoordinates.add((double) 0); // Z

        // Coordinates of the target.
        ArrayList<Double> targetCoordinates = new ArrayList<>();
        targetCoordinates.add((double) 6); // X
        targetCoordinates.add((double) 18); // Y
        targetCoordinates.add((double) 3); // Z

        // Output information to screen and simulate missile advancement.
        missile missile = new missile(procSpeed, missileSpeed, missileCoordinates, targetCoordinates, proximityDetonation);
        missile.launch();
    }
}
