package org.example;


import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Použití: java App" + " <vstupní soubor> [výstupní soubor]");
            return;
        }

        String inputFile = args[0];
        String outputFile = (args.length > 1) ? args[1] : null;

        try {
            ArrayList<Integer> numbers = readNumbers(inputFile);
            ArrayList<Integer> result = processNumbers(numbers);
            writeResult(result, outputFile);
        } catch (IOException e) {
            System.out.println("Chyba při čtení nebo zápisu souborů: " + e.getMessage());
        }
    }

    // Čtení čísel ze souboru nebo ze standardního vstupu
    private static ArrayList<Integer> readNumbers(String inputFile) throws IOException {
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner scanner = null;

        if (inputFile != null ) {
            try {
                int number = Integer.parseInt(inputFile);
                if (number >= 0) {
                    scanner = new Scanner(System.in);
                } else {
                    System.out.println("Číslo je záporné");
                }
            } catch (NumberFormatException e) {
                File file = new File(inputFile);
                scanner = new Scanner(file);;
            }
        }

        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }

        if (scanner != null) {
            scanner.close();
        }

        return numbers;
    }

    // Zpracování čísel podle zadaných pravidel
    private static ArrayList<Integer> processNumbers(ArrayList<Integer> numbers) {
        ArrayList<Integer> result = new ArrayList<>();
        int count = numbers.size();

        for (int i = 0; i < count; i++) {
            int number = numbers.get(i);

            if ((count % 2 == 0 && number % 2 == 0) || (count % 2 != 0 && number % 2 != 0)) {
                result.add(number);
            }
        }

        return result;
    }

    // Zápis výsledku do souboru nebo na standardní výstup
    private static void writeResult(ArrayList<Integer> result, String outputFile) throws IOException {
        PrintWriter writer = null;

        if (outputFile != null) {
            writer = new PrintWriter(new FileWriter(outputFile));
        } else {
            writer = new PrintWriter(System.out);
        }

        for (int number : result) {
            writer.println(number);
        }

        writer.close();
    }

}

