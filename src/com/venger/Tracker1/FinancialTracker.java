package com.venger.Tracker1;

import java.util.ArrayList;
import java.util.List;

public class FinancialTracker {
    private List<Transaction> transactions;

    public FinancialTracker(List<Transaction> transactions) {
        this.transactions = new ArrayList<>();
    }

    public FinancialTracker() {
        this.transactions = new ArrayList<>();
        }

    public void addTransaction(double amount, Transaction.Type type, String description){
        if (amount <=0){
            System.out.println("Сума транзакції повинна бути більше за нуль");
            return;
        }
        Transaction transaction = new Transaction(amount,type,description);
        transactions.add(transaction);
        System.out.println("Транзакція успішно додана");
    }
    public double getCurrentBalance(){
        double balance = 0;
        for (Transaction transaction: transactions){
            if (transaction.getType() == Transaction.Type.INCOME){
                balance += transaction.getAmount();
            }else {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }
    public void viewAllTransactions(){
        if(transactions.isEmpty()){
            System.out.println("Немає зареєстрованих транзакцій");
            return;
        }
        System.out.println("\n--- Всі транзакції ---");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i+1) + "." + transactions.get(i));
        }
        System.out.println("-------------------------------------");
    }
}
