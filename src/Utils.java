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
}
