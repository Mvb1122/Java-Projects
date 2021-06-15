package com.main;

import java.util.ArrayList;

/**
 *
 *
 *          Missile Guidance Program, intakes six parameters- the X, Y, and Z coordinates of both the target and the missile.
 *          Only has support for stationary targets.
 *
 *          @author Micah Bushman
 */
@SuppressWarnings("BusyWait")
public class missile {
    final long procSpeed;
    final double missileSpeed;
    final ArrayList<Double> missileCoordinates;
    final ArrayList<Double> targetCoordinates;
    final int proxDet;


    /**
     * @param procSpeed A long, containing the number of seconds that each calculation takes.
     * @param missileSpeed A double, containing the number of units, per tick, that the missile moves.
     * @param missileCoordinates A Double Arraylist containing the missile's coordinates, XYZ.
     * @param targetCoordinates A Double Arraylist containing the target's coordinates, XYZ.
     * @param proxDet An int containing the distance at which the missile detonates. If blank, it tries for a direct strike.
     * @author Mvb1122
     */
    public missile(long procSpeed, double missileSpeed, ArrayList<Double> missileCoordinates, ArrayList<Double> targetCoordinates, int proxDet) {
        this.procSpeed = procSpeed;
        this.missileSpeed = missileSpeed;
        this.missileCoordinates = missileCoordinates;
        this.targetCoordinates = targetCoordinates;
        this.proxDet = proxDet;
    }

    public void launch() {
        final int[] numTicks = {0};

        // Start a new thread containing the missile's launch code.
        Thread missile = new Thread(() -> {
            do {
                System.out.println("\n\nTick number " + numTicks[0]);
                // End loop if missile is in target.
                if (missileCoordinates.get(0).equals(targetCoordinates.get(0)) && missileCoordinates.get(1).equals(targetCoordinates.get(1)) && missileCoordinates.get(2).equals(targetCoordinates.get(2)) || numTicks[0] == 500) {
                    break;
                }

                // Print Target and Missile location information to screen.
                System.out.println("The missile is located at: " + missileCoordinates);
                System.out.println("The target is located at: " + targetCoordinates);

                double dist = calculateDistanceBetweenPoints(missileCoordinates.get(0), missileCoordinates.get(1), missileCoordinates.get(2), targetCoordinates.get(0), targetCoordinates.get(1), targetCoordinates.get(2));
                String distString = "" + dist + "";
                String distToFixed = distString.substring(0, distString.indexOf(".") + 2);
                System.out.println("The target and missile are " + distToFixed + "u apart.");

                // Detonate missile if within proximity detonation range.
                if (dist <= proxDet) {
                    System.out.println("The missile detonated " + distToFixed + "u away, since it was within its proximity detonation range of " + proxDet + ".");
                    break;
                }

                try {
                    Thread.sleep(1000 * procSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Calculate and print angles for movement.
                ArrayList<Double> Angles = new ArrayList<>(calculateAngleOfAttack(missileCoordinates, targetCoordinates));
                System.out.println("Angles: " + Angles);

                try {
                    Thread.sleep(1000 * procSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Simulate missile moving forward.
                // Calculate difference in missile location and target location.
                ArrayList<Double> deltas = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    deltas.add(targetCoordinates.get(i) - missileCoordinates.get(i));
                }
                // Move missile by its speed per tick in the direction of the target.
                for (int i = 0; i < 3; i++) {
                    missileCoordinates.set(i, missileSpeed * Math.signum(deltas.get(i)) + missileCoordinates.get(i));
                }

                System.out.println("Distances: " + deltas);
                numTicks[0]++;
            } while (true);

            if (numTicks[0] == 500) {
                System.out.println("\nThe missile missed.");
            } else {
                System.out.println("\nTarget Destroyed.");
            }
        });
        missile.start();
    }

    private double calculateDistanceBetweenPoints(double x1, double y1, double z1, double x2, double y2, double z2) {
        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) + Math.pow(z2 - z1, 2));

        // Simulate this process taking time
        try {
            Thread.sleep(1000 * procSpeed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return distance;
    }

    private ArrayList<Double> calculateAngleOfAttack(ArrayList<Double> input1, ArrayList<Double> input2) {
        ArrayList<Double> angles = new ArrayList<>();

        for (int i = 0; i < input1.size() - 1; i++) {
            // Degrees = tan^-1 ( rise / run )
            double rise = Math.abs(input1.get(i) - input2.get(i));
            double run = Math.abs(input1.get(1 + i) - input2.get(1 + i));
            double riseOverRun = rise / run;
            Double angle = Math.toDegrees(Math.atan(riseOverRun));
            angles.add(angle);
        }

        // Simulate this process taking time
        try {
            Thread.sleep(1000 * procSpeed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return angles;
    }
}
