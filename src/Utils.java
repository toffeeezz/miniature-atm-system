import java.util.ArrayList;

public class Utils {


    public static void SimplePrint(String txt){
        System.out.println(txt);
    }
    public static void LinePrint(String txt){
        System.out.print(txt);
    }
    public static void LineBreak(){
        System.out.println(" ");
    }
    public static void PrintDesign(){
        Utils.SimplePrint("----------------------");
        Utils.LinePrint("> ");
    }
    public static void FormatPrint(String txt, Object... var){
        System.out.printf(txt, var);
    }
    public static boolean FilterCharacters(String txt){
        for(char c: txt.toCharArray()){
            if(Character.isLetter(c)){
                continue;
            }else if(c == ',' || c == ' '){
                continue;
            }
             return false;
        }
        return true;
    }
    public static boolean CheckNameFormat(String name){
        String[] splits = name.trim().split(", ");
        if(splits.length != 2){
            return false;
        }
        String[] firstNameSplits = splits[1].split(" ");
        ArrayList<Character> firstLetters = new ArrayList<>();
        for(int i = 1; i < splits[0].length(); i++){
            if(Character.isUpperCase(splits[0].charAt(i)))
                return false;
        }
        firstLetters.add(splits[0].charAt(0));
        for(String split: firstNameSplits){
            firstLetters.add(split.charAt(0));
        }
        for (String firstName : firstNameSplits) {
            for (int a = 1; a < firstName.length(); a++) {
                if (Character.isUpperCase(firstName.charAt(a))) {
                    return false;
                }
            }
        }
        for(char letter: firstLetters){
            if(Character.isLowerCase(letter)){
                return false;
            }
        }
        return true;
    }
}
