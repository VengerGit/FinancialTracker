package com.venger.Tracker2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FinancialTracker {
    private List<Transaction> transactions;

    public FinancialTracker() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(double amount, Transaction.Type type, String description) {
        if (amount <= 0) {
            System.out.println("Сума транзакції повинна бути більшою за нуль.");
            return;
        }
        Transaction transaction = new Transaction(amount, type, description);
        transactions.add(transaction);
        System.out.println("Транзакцію успішно додано");
    }

    public double getCurrentBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == Transaction.Type.INCOME) {
                balance += transaction.getAmount();
            } else {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }

    public void viewAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("Немає зареєстрованих транзакцій");
            return;
        }
        System.out.println("\n--- Всі транзакції ---");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + "." + transactions.get(i));
        }
        System.out.println("---------------------------");
    }

    public void saveTransactionsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.getAmount() + ","
                        + transaction.getType() + ","
                        + transaction.getDescription());
                writer.newLine();
            }
            System.out.println("Дані успішно збережено у файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Помилка при збережені даних у файл" + e.getMessage());
        }
    }

    public void loadTransactionsFromFile(String fileName) {
        transactions.clear();

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Файл '" + fileName + "' не знайдено. Почніть з нового аркуша або створіть нові транзакції.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);

                if (parts.length == 3) {
                    try {
                        double amount = Double.parseDouble(parts[0]);
                        Transaction.Type type = Transaction.Type.valueOf(parts[1]);
                        String description = parts[2];

                        transactions.add(new Transaction(amount, type, description));
                    } catch (NumberFormatException e) {
                        System.err.println("Помилка парсингу числа в рядку: '" + line + "'. " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Невідомий тип транзакції в рядку: '" + line + "'. " + e.getMessage());
                    }
                } else {
                    System.err.println("Некоректний формат рядка у файлі: '" + line + "'. Рядок пропущено.");
                }
            }
            System.out.println("Дані успішно завантажені з файлу: " + fileName);
            viewAllTransactions();
        } catch (IOException e) {
            System.err.println("Помилка при завантаженні даних з файлу: " + e.getMessage());
        }
    }
}
