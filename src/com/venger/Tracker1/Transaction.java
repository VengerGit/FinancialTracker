package com.venger.Tracker1;

public class Transaction {
    public enum Type{
        INCOME,
        EXPENSE
    }
    private double amount;
    private Type type;
    private String description;

    public Transaction(double amount, Type type, String description) {
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "com.venger.Tracker1.Transaction{" +
                "Сума: " +
                "" + amount +
                ", Тип: " + (type == Type.INCOME ? "Дохід" : "Витрата") +
                ", Опис: " + description + '\'' +
                '}';
    }
}
