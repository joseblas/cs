package org.josetaboada;

import org.apache.commons.lang3.tuple.Pair;
import org.josetaboada.model.Order;
import org.josetaboada.model.Quote;

import java.math.BigDecimal;
import java.util.Optional;

public class Utils {

    public static final double MARGIN = 0.02d;

    public static double round(Double price) {
        return BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * Builds the Quote object, adding the margin
     *
     * @param opt Buy and Sell Orders matched
     * @return Optional Quote
     */
    public static Optional<Quote> buildForQuote(Optional<Pair<Double,Double>> opt) {
        if (!opt.isPresent() || Double.MIN_VALUE ==  opt.get().getLeft()  || Double.MAX_VALUE == opt.get().getRight())
            return Optional.empty();

        Pair<Double, Double> pair = opt.get();
        return Optional.of(new Quote(Utils.round(pair.getLeft() - MARGIN), Utils.round(pair.getRight() + MARGIN)));
    }
}