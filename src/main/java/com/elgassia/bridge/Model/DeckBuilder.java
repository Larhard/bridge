package com.elgassia.bridge.Model;

import java.util.List;

/**
 * Created by vereena on 6/26/15.
 */
public class DeckBuilder {
    List<Card>[] build(Strategy strategy)
    {
        return strategy.getCards();
    }
}
