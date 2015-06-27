package com.elgassia.bridge.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vereena on 6/26/15.
 */
public interface Strategy extends Serializable{
    List<Card> [] getCards();
}
