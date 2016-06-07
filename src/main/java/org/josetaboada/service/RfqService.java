package org.josetaboada.service;

import org.josetaboada.model.Quote;

import java.util.Optional;

public interface RfqService {
    Optional<Quote> quoteFor(String currency, int amount);
}