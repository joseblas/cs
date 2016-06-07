package org.josetaboada.model;

public class Order {

    public final Direction direction;
    public final Double price;
    public final String currency;
    public final Long amount;

    public Order(Direction direction, Double price, String currency, Long amount) {
        this.direction = direction;
        this.price = price;
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "direction=" + direction +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}