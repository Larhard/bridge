package com.elgassia.bridge.Model;

import org.junit.Test;

import java.io.IOException;

public class TeamModelMementoTest {
    @Test
    public void testMemento() throws IOException, ClassNotFoundException {
        TeamModel x=new TeamModel();
        TeamModel.Memento memento=new TeamModel.Memento(x);
        TeamModel y=memento.restore();
    }
}