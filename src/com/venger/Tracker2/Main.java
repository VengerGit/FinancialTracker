package com.venger.Tracker2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String FILENAME = "transaction.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinancialTracker tracker = new FinancialTracker();

        System.out.println("Спроба завантажити дані з файлу" + FILENAME + "...");
        tracker.loadTransactionsFromFile(FILENAME);
        System.out.println("Завантаження завершено");

        int choice;

        do {
            System.out.println("\n--- Система обліку фінансів ---");
            System.out.println("1. Додати дохід");
            System.out.println("2. Додати витрату");
            System.out.println("3. Переглянути баланс");
            System.out.println("4. Переглянути всі транзакції");
            System.out.println("5. Зберегти дані у файл"); // Нова опція
            System.out.println("6. Завантажити дані з файлу"); // Нова опція
            System.out.println("7. Вийти"); // Змінився номер
            System.out.print("Виберіть дію: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        addTransaction(scanner, tracker, Transaction.Type.INCOME);
                        break;
                    case 2:
                        addTransaction(scanner, tracker, Transaction.Type.EXPENSE);
                        break;
                    case 3:
                        System.out.printf("Поточний баланс: %.2f грн\n", tracker.getCurrentBalance());
                        break;
                    case 4:
                        tracker.viewAllTransactions();
                        break;
                    case 5:
                        tracker.saveTransactionsToFile(FILENAME);
                        break;
                    case 6:
                        tracker.loadTransactionsFromFile(FILENAME);
                        break;
                    case 7:
                        System.out.println("Автоматичне збереження даних перед виходом...");
                        tracker.saveTransactionsToFile(FILENAME);
                        System.out.println("Дякую за використання системи обліку фінансів!");
                        break;
                    default:
                        System.out.println("Невірний вибір. Будь ласка, спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Невірне введення. Будь ласка, введіть число.");
                scanner.nextLine();
                choice = 0;
            }

        } while (choice != 7);

        scanner.close();
    }

    private static void addTransaction(Scanner scanner, FinancialTracker tracker, Transaction.Type type) {
        System.out.print("Введіть суму: ");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine(); // Очищаємо буфер
        } catch (InputMismatchException e) {
            System.out.println("Невірний формат суми. Будь ласка, введіть число.");
            scanner.nextLine();
            return;
        }

        System.out.print("Введіть опис: ");
        String description = scanner.nextLine();

        tracker.addTransaction(amount, type, description);
    }
}
