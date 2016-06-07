package org.josetaboada.service;

import org.apache.commons.lang3.tuple.Pair;
import org.josetaboada.model.Order;
import org.josetaboada.model.Quote;


import java.util.Optional;
import java.util.function.BinaryOperator;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;
import static org.josetaboada.Utils.buildForQuote;
import static org.josetaboada.model.Direction.Buy;


public class RfqServiceImpl implements RfqService {

    private LiveOrderBoard board;

    public RfqServiceImpl(LiveOrderBoard board) {
        this.board = checkNotNull(board);
    }

    public Optional<Quote> quoteFor(String currency, final int amount) {
        Optional<Pair<Double, Double>> pair = board.ordersFor(currency).stream()
                .filter(order -> order.amount == amount)
                .map(o -> {
                    if (Buy.equals(o.direction)) {
                        return Pair.<Double, Double>of(o.price, MAX_VALUE);
                    }
                    return Pair.<Double, Double>of(MIN_VALUE, o.price);
                })
                .reduce(reducePairs());

        return buildForQuote(pair);
    }

    /**
     *  Reduce function to get the highest buy and lowest sell.
     */
    public static BinaryOperator<Pair<Double,Double>> reducePairs() {
        return (p1, p2) -> Pair.<Double, Double>of(Math.max(p1.getLeft(), p2.getLeft()), Math.min(p1.getRight(), p2.getRight()));
    }
}