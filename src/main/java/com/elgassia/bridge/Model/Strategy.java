package com.elgassia.bridge.Model;

import java.io.Serializable;
import java.util.List;

public interface Strategy extends Serializable{
    List<Card> [] getCards();
}
