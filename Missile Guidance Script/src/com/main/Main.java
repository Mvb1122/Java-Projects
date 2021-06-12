package com.main;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        /*
        Missile Guidance Program, intakes six parameters- the XYZ coords of both the target and the missile.
        Only has support for stationary targets.
         */

        ArrayList<Double> missileCoordinates = new ArrayList<Double>();
        missileCoordinates.add((double) 0);
        missileCoordinates.add((double) 0);
        missileCoordinates.add((double) 0);

        ArrayList<Double> targetCoordinates = new ArrayList<Double>();
        targetCoordinates.add((double) 1);
        targetCoordinates.add((double) 1);
        targetCoordinates.add((double) 0);


        // Output information to screen and simulate missile advancement.
        boolean stop = false;
        do {
            System.out.println("\n\nThe missile is located at: " + missileCoordinates);
            System.out.println("The target is located at: " + targetCoordinates);

            double dist = calculateDistanceBetweenPoints(missileCoordinates.get(0), missileCoordinates.get(1), missileCoordinates.get(2), targetCoordinates.get(0), targetCoordinates.get(1), targetCoordinates.get(2));
            System.out.println("The target and missile are: " + dist + "u apart.");

            // Simulate missile moving closer to target.
            ArrayList<Double> Angles = new ArrayList<>(calculateAngleOfAttack(missileCoordinates, targetCoordinates));
            System.out.println("Angles: " + Angles);



            // End loop if missile is in target.
            if (missileCoordinates.get(0) == targetCoordinates.get(0) && missileCoordinates.get(1) == targetCoordinates.get(1) && missileCoordinates.get(2) == targetCoordinates.get(2)) {
                stop = true;
            }
            stop = true;
        } while (!stop);

    }

    private static double calculateDistanceBetweenPoints(double x1, double y1, double z1, double x2, double y2, double z2) {
        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) + Math.pow(z2 - z1, 2));
        return distance;
    }

    private static ArrayList<Double> calculateAngleOfAttack(ArrayList<Double> input1, ArrayList<Double> input2) {
        ArrayList<Double> angles = new ArrayList<>();

        // TODO: Figure this out.

        // Inputting: Two sets of coordinates, the missile is input1 and the target is input2.
        // Outputting: Angles between them.

        for (int i = 0; i < input1.size() - 1; i++) {
            // Degrees = tan^-1 ( rise / run )
            double rise = Math.abs(input1.get(0 + i) - input2.get(0 + i));
            double run = Math.abs(input1.get(1 + i) - input2.get(1 + i));
            double riseOverRun = rise / run;
            Double angle = Math.toDegrees(Math.atan(riseOverRun));
            angles.add(angle);
        }

        return angles;
    }
}
