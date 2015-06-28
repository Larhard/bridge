package com.elgassia.bridge.Model;

import java.util.List;

public class DeckBuilder {
    List<Card>[] build(Strategy strategy)
    {
        return strategy.getCards();
    }
}
