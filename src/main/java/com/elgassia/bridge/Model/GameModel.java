package com.elgassia.bridge.Model;

/**
 * Created by vereena on 6/19/15.
 */
public class GameModel {
    private TeamModel teamModel;
    private int [] playerOrder;
    private int playingTeam;
    private Color atu;
    private int contract;
    private int currentPlayer;
    private int grandpa;
    GameModel(TeamModel teamModel)
    {
        this.teamModel=teamModel;
        this.playerOrder=teamModel.getPlayerOrder();
        this.playingTeam=teamModel.getBiddingWinner()%2;
        this.atu=teamModel.getAtu();
        this.contract=teamModel.getContract();
        this.currentPlayer=teamModel.getBiddingWinner()+1;
        if(this.currentPlayer==4)
            this.currentPlayer=0;
        this.grandpa=this.currentPlayer+1;
        if(this.grandpa==4)
            this.grandpa=0;
    }
}
