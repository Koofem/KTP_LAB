package lab1;

public class Primes {
    public static void main(String[] args) {
        for (int i = 2; i <= 100; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            };
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {

            if (n % i == 0) {
                return false; // Число не простое так делится нацело на другое число
            }
        }

        return true; // Число простое так делится только на себя и единицу
    }
}