package lab1;

public class Palindrome {
    public static void main(String[] args) {
        String[] Palindroms = {"deed", "nun", "flower", "peep", "mind", "malayalam"};
        for (int i = 0; i < Palindroms.length; i++) {
            // Проверяем массив слов на то являются ли слова полиндромами и сразу выводим
            System.out.println("Is the word " + Palindroms[i]  + " palindrome? " + isPalindrome(Palindroms[i]) + " "+ reverseString(Palindroms[i]));
        }
    }

    public static String reverseString(String argStr) {
        String reversedString = "";

        for (int i = argStr.length() - 1; i >= 0; i--) {
            reversedString += argStr.charAt(i);
        }

        return reversedString;
    }

    public static boolean isPalindrome(String word) {
        boolean isPalindrome = false;
        String reversedString = "";

        for (int i = word.length() - 1; i >= 0; i--) {
            reversedString += word.charAt(i);
        }

        if (word.equals(reversedString)) {
            isPalindrome = true;
        }

        return isPalindrome;
    }
}