package marinara;

public class Coder {
    private static char[][] codes;
    static {
        // Map codes to codes on startup, once.
        System.out.println("Codes constructed.");
        codes = new char[7][7];
        codes[0] = new char[]{'a', 'b', 'c', 'd', 'e', 'f', '='};
        codes[1] = new char[]{'g', 'h', 'i', 'j', 'k', 'l', '+'};
        codes[2] = new char[]{'m', 'n', 'o', 'p', 'q', 'r', '-'};
        codes[3] = new char[]{'s', 't', 'u', 'v', 'w', 'x', '%'};
        codes[4] = new char[]{'y', 'z', '1', '2', '3', '4', '$'};
        codes[5] = new char[]{'5', '6', '7', '8', '9', '0', '#'};
        codes[6] = new char[]{'!', '?', '.', ',', ';', '"', ' '};
    }

    /**
     * @param input A string consisting of Two numbers, EG: "11" which corresponds to an index in the codes.
     * @return the cooresponding char object which is help at the specified index.
     */
    public static char decode(String input) {
        if (input.equals(" ")) return ' ';
        int[] coordinates = new int[]{Integer.parseInt(input.substring(0, 1)), Integer.parseInt(input.substring(1))};
        if (coordinates[0] >= 1 && coordinates[1] >= 1) return codes[coordinates[0] - 1][coordinates[1] - 1];
        else return ' '; // Assume that anybody who messes up and puts a 0 means SPACE.
    }

    /**
     * @param input a char, holding your character to encode.
     * @return a String, consisting of Two numbers, EG: "11" which corresponds to the char you passed in.
     */
    public static String encode(char input) {
        // Search codes list for character
        for (int i = 0; i < codes.length; i++) {
            for (int j = 0; j < codes[i].length; j++) {
                char code = codes[i][j];
                if (code == input) return "" + (i + 1) + "" + (j + 1);
            }
        }
        // Assume anybody that doesn't find a match means SPACE.
        return " ";
    }

    public static char[][] codes() {return codes;}
}
