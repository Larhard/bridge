package com.elgassia.bridge.Model;

import junit.framework.TestCase;

import java.io.IOException;

public class TeamModelMementoTest extends TestCase {
    public void testMemento() throws IOException, ClassNotFoundException {
        TeamModel x=new TeamModel();
        TeamModel.Memento memento=new TeamModel.Memento(x);
        TeamModel y=memento.restore();
    }
}