package org.josetaboada.service;

import org.josetaboada.model.Order;

import java.util.List;


public interface LiveOrderBoard {
    List<Order> ordersFor(String currency);
}