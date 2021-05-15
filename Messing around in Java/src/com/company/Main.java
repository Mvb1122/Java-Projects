package com.company;
import java.util.ArrayList;

/*
    This one's just me messin' 'round.
 */
public class Main {

    public static String translateToPigLatinV1(String input) {
        String word = input;
        word += "-";
        word = word.substring(1) + word.charAt(0);
        word += "ay";
        return word;
    }

    public static boolean isVowel(char input) {
        String letter = Character.toString(input);
        letter = letter.toLowerCase();
        // System.out.println(letter);
        return letter.equals("a") || letter.equals("e") || letter.equals("i") || letter.equals("o") || letter.equals("u");
    }
    /*
    public static String translateToPigLatinV2(String input) {
        ArrayList<String> sentance = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            String[] wordList = input.split(" ");
            String word = wordList[i];
            if (isVowel(input.charAt(0))) {
                word += "yay";
                System.out.println(word);
            }
            sentance.add(word);
        }


        String sentanceString = "";
        for (int i = 0; i < sentance.size(); i++) {
            sentanceString += sentance.get(i);
        }
        return sentanceString;
    }
    */

    public static void main(String[] args) {
        System.out.println(translateToPigLatinV1("Cats"));
        // System.out.println(translateToPigLatinV2("Hi I'm very cool."));
        // isVowel("hello".charAt(0));
    }

}
