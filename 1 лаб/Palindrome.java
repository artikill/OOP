package labs.lab1;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your word");
        String s1 = input.nextLine();
        String s2 = reverseString(s1);
        if (s1.equals(s2)){
            System.out.println(s1 + " is Palindrome");
        }
        else System.out.println(s1 + " Is not a palindrome");
    }
    static String reverseString(String s ){
        int length = s.length() - 1 ;
        char[] reverse;
        reverse = new char[s.length()];
        String word = "";
        for(int i = 0; i<s.length(); i++){
            reverse[i] = s.charAt(length);
            word = word + reverse[i];
            length--;
        }
        return word;
    }
}