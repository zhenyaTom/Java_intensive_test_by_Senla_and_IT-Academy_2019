package com.senla.atm.dao.utils;

import com.senla.atm.entity.Card;
import com.senla.atm.entity.CashMachine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileWorker {

    public static String[] readCardFromFileByNumber(String cardNumber, String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            String[] cardStringArray = scanner.nextLine().split(" ");
            if (cardStringArray[0].equals(cardNumber)) {
                scanner.close();
                return cardStringArray;
            }
        }
        scanner.close();
        return null;
    }

    public static void writeCardToFile (Card card, String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        List<String> cardsListString = new ArrayList<>();
        while (scanner.hasNextLine()) {
            cardsListString.add(scanner.nextLine());
        }
        for (int i = 0; i < cardsListString.size(); i++) {
            String[] stringArray = cardsListString.get(i).split(" ");
            if (stringArray[0].equals(card.getNumber())) {
                cardsListString.set(i, card.toString());
                scanner.close();
                break;
            }
        }
        PrintWriter printWriter = new PrintWriter(new File(filePath));
        for (String aString : cardsListString){
            printWriter.println(aString);
        }
        printWriter.close();
    }

    public static String readCashMachineFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        String cashMachineString = scanner.nextLine();
        scanner.close();
        return cashMachineString;
    }

    public static void writeCashMachineToFile (CashMachine cashMachine, String path) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File(path));
        printWriter.println(cashMachine.toString());
        printWriter.close();
    }
}