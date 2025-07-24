import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinancialTracker tracker = new FinancialTracker();
        int choice;
        do {
            System.out.println("\n --- Система обліку фінансів ---");
            System.out.println("1. Додати дохід");
            System.out.println("2. Додати витрату");
            System.out.println("3. Переглянути баланс");
            System.out.println("4. Переглянути всі транзакції");
            System.out.println("5. Вийти");
            System.out.println("Виберіть дію");
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
                        double currentBalance = tracker.getCurrentBalance();
                        System.out.printf("Поточний баланс: %.2f грн\n", currentBalance);
                        break;
                    case 4:
                        tracker.viewAllTransactions();
                        break;
                    case 5:
                        System.out.println("Дякую за використання системи обліку фінансів!");
                        break;
                    default:
                        System.out.println("Невірний вибір. Будь ласка, спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Невірне введення. Будь ласка введіть число.");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != 5);
        scanner.close();
    }

    private static void addTransaction(Scanner scanner, FinancialTracker tracker, Transaction.Type type) {
        System.out.println("Введіть суму:");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("Невірний формат суми. Будь ласка введіть число.");
            scanner.nextLine();
            return;
        }
        System.out.println("Введіть опис:");
        String description = scanner.nextLine();
        tracker.addTransaction(amount, type, description);
    }
}
