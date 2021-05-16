package com.company;
import javax.swing.*;
import java.awt.event.*;


// Originally, this class was called Counter, but I was too lazy to move it into another file so I just renamed it to be "Main."
public class Main {
    // Make the counter value.
    int counter;
    int startingValue;

    // Create the counter when the constructor is called.
    public Main(int startingValue) {
        this.counter = startingValue;
        this.startingValue = startingValue;
    }

    // Increase the counter's value by one.
    public void increase() {
        counter ++;
    }

    // Return the value of the counter.
    public int getValue() {
        return counter;
    }

    public void setValue(int value) {
        counter = value;
    }

    // Actually run program:
    public static void main(String[] args) {

        // Make counter for number of clicks.
        Main clickCounter = new Main(1);
        Main completionCounter = new Main(1);
        Main easterEggCounter = new Main(1);

        // Create the JFrame -- The display.
        JFrame f = new JFrame();
        JFrame d = new JFrame();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the button, b, which you see on screen.
        JButton b = new JButton("Click me!");
        JButton c = new JButton("Wow, you clicked that button 10 times!");
        JButton e = new JButton("Continue");

        // make the edges of the button. (X, Y, Width, Hight)
        b.setBounds(80, 100, 250, 40);
        c.setBounds(20, 80, 350, 60);
        e.setBounds(50, 140, 290, 40);

        // Wait for a click, then run the stuff.
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clickCounterString = "" + clickCounter.getValue() + "";
                b.setText(clickCounterString);
                clickCounter.increase();
                if (clickCounter.getValue() > 10) {
                    d.setVisible(true);
                    f.setVisible(false);
                }
            }
        });


// EASTER EGG: Click the completion display 10 times.
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = easterEggCounter.getValue();
                if (value <= 10) {
                    c.setText("Wrong button?");
                } else if (value <= 20) {
                    c.setText("dude stop clicking me");
                } else if (value <= 30) {
                    c.setText("dude what the heck stop");
                } else if (value <= 40) {
                    c.setText("alright you've forced my hand.");
                } else if (value <= 45) {
                    c.setText("I'm forcing you to click the right button.");
                } else {
                    clickCounter.setValue(1);
                    b.setText("Hi, again.");
                    f.setVisible(true);
                    d.setVisible(false);
                    System.out.println("Reset!");
                    String fTitle = "Completions: " + completionCounter.getValue();
                    System.out.println(fTitle);
                    f.setTitle(fTitle);
                    String cText = "Wow, you clicked that button " + (10 * completionCounter.getValue() + 10) + " times!";
                    c.setText(cText);
                    completionCounter.increase();
                }
                easterEggCounter.increase();
            }
        });

        e.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCounter.setValue(1);
                b.setText("Hi, again.");
                f.setVisible(true);
                d.setVisible(false);
                System.out.println("Reset!");
                String fTitle = "Completions: " + completionCounter.getValue();
                System.out.println(fTitle);
                f.setTitle(fTitle);
                String cText = "Wow, you clicked that button " + (10 * completionCounter.getValue() + 10) + " times!";
                c.setText(cText);
                completionCounter.increase();
            }
        });

        // add button to JFrame
        f.add(b);
        d.add(c);
        d.add(e);

        // Set the size and type of JFrame.
        f.setSize(400, 500);
        f.setLayout(null);

        d.setSize(400, 500);
        d.setLayout(null);

        // make the JFrame visible
        f.setVisible(true);
        d.setVisible(false);

        // System.out.println(f);
    }
}

// In case you're wondering why I can't just make an integer value and increase it by one every time the button is clicked, Java has rules. Unlike dumb JS.

/* Q&A:

Q: Why did you do this?
A: Because I didn't have anything else to do.

Q: What is JSwing?
A: A class for Java that makes visible trash.

Q: Have you ever used JSwing before?
A: No.

*/
