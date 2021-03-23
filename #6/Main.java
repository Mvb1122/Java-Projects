public class Main {
    int variable;

    public Main(int inputVariable) {
        variable = inputVariable;
    }

    public static String caseExample (int inputNum) {
        String outputString;
        switch (inputNum) {
            case 1:
                outputString = "Logic in Java";
                break;
            case 2:
                outputString = "is basically the";
                break;
            case 3:
                outputString = "same as Javascript.";
                break;
            case 4:
                outputString = "except, we have to use";
                break;
            case 5:
                outputString = ".equals() instead of ===";
                break;
            case 6:
                outputString = "since Java's == checks **memory addresses**";
                break;
            case 7:
                outputString = "where .equals() checks the actual content.";
                break;
            default:
                outputString = "Your number was too high.";
        }
        return outputString;
    }


    public static void main(String[] args) {
        System.out.println(caseExample(1));
        System.out.println(caseExample(2));
        System.out.println(caseExample(3));
        System.out.println(caseExample(4));
        System.out.println(caseExample(5));
        System.out.println(caseExample(6));
        System.out.println(caseExample(7));
        System.out.println(" ");

        // The == vs .equals() in detail:
        // (The String object is built-in to Java.)
        String s1 = new String("HELLO");
        String s2 = new String("HELLO");


        if (s1 == s2) {
            System.out.println("s1 does == s2. (True)");
        } else {
            System.out.println("s2 does NOT == s2. (False)");
        }


        if (s1.equals(s2)) {
            System.out.println("s1.equals(s2) (True)");
        } else {
            System.out.println("s1 DOES NOT .equals(s2) (False)");
        }
    }

}