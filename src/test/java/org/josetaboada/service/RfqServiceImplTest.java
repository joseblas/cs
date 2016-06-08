package org.josetaboada.service;

import com.google.common.collect.ImmutableList;
import org.josetaboada.model.Order;
import org.josetaboada.model.Quote;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.josetaboada.Utils.MARGIN;
import static org.josetaboada.model.Direction.Buy;
import static org.josetaboada.model.Direction.Sell;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RfqServiceImplTest {

    private static String USD = "USD";

    @Mock
    LiveOrderBoard board;

    List<Order> orders;

    RfqService service;

    @Before
    public void setUp() {
        service = new RfqServiceImpl(board);


        orders = ImmutableList.of(
                new Order(Buy, 232.71, USD, 200L),//B
                new Order(Sell, 232.74, USD, 100L),
                new Order(Sell, 232.73, USD, 200L),//S
                new Order(Buy, 232.71, USD, 500L),
                new Order(Buy, 232.70, USD, 100L),
                new Order(Sell, 232.75, USD, 200L),//S
                new Order(Buy, 232.69, USD, 500L),
                new Order(Sell, 232.76, USD, 300L),
                new Order(Buy, 232.70, USD, 200L)

        );
        when(board.ordersFor(USD)).thenReturn(orders);
    }

    @Test(expected = NullPointerException.class)
    public void null_LiveOrderBoard_should_fail() {
        new RfqServiceImpl(null);
    }

    @Test
    public void should_find_match_for_200() {
        double buy = 232.71;
        double sell = 232.73;

        Optional<Quote> quote = service.quoteFor(USD, 200);
        Assert.assertTrue(quote.isPresent());
        Assert.assertEquals(buy - MARGIN, quote.get().bid, 0);
        Assert.assertEquals(sell + MARGIN, quote.get().ask, 0);
    }

    @Test
    public void should_not_find_match_for_2() {
        Optional<Quote> quote = service.quoteFor(USD, 2);
        Assert.assertFalse(quote.isPresent());
    }

    @Test
    public void should_not_find_match_for_500_with_buy_only() {
        Optional<Quote> quote = service.quoteFor(USD, 500);
        Assert.assertFalse(quote.isPresent());
    }

    @Test
    public void should_not_find_match_for_300_with_sell_only() {
        Optional<Quote> quote = service.quoteFor(USD, 300);
        Assert.assertFalse(quote.isPresent());
    }
}