/**
 * Created by Hamit on 7/16/2016.
 */
public class QuizDebugger {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            if (index >= input.length() - 3){
                break;
            }
            String found = input.substring(index + 1, index + 4);
            System.out.println("length of the given string is "+input.length());
            System.out.println("index before update "+index);
            System.out.println(found);
            index = input.indexOf("abc", index + 3);
            System.out.println("index after update "+index);


        }
    }

    public void test() {
        findAbc("abcabcabcabca");
    }

    public static void main(String[] args) {
        QuizDebugger testme = new QuizDebugger();
        testme.test();
    }

}
