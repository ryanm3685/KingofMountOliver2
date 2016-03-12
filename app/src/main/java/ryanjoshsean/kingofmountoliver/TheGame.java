package ryanjoshsean.kingofmountoliver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ryanmcgregor on 3/11/16.
 */
public class TheGame {
    List<Player> players;
    Dice [] diceArray;
    Player brownsvillePlayer, south18thPlayer, lastAttackerOfCenter; //who is on brownsville, who is on south 18th,
    //and who last attacked those two?
    Player currentPlayer; //whose turn is it
    enum diceSides  {MOJO, ENERGY, ATTACK, ONE, TWO, THREE};
    static int MAX_MOJO = 20;
    static int MAX_ENERGY = 99;
    static int PLAYERS_FOR_SOUTH_18TH = 5;

    public TheGame(int numPlayers)
    {
        players = new ArrayList<Player>();
        diceArray = new Dice[6];
    }

    public void rollDice(ArrayList<Dice> diceArrayList)
    {
        for (Dice dice : diceArrayList)
        {
            dice.roll();
        }
    }

    public void evaluateDice()
    {
        int energy = 0, mojo = 0, attack = 0, one = 0, two = 0, three = 0, juice = 0;
        for (Dice d : diceArray)
        {
            if (d.value == diceSides.MOJO) mojo += 1;
            else if (d.value == diceSides.ENERGY) energy += 1;
            else if (d.value == diceSides.ATTACK) attack += 1;
            else if (d.value == diceSides.ONE) one += 1;
            else if (d.value == diceSides.TWO) two += 1;
            else if (d.value == diceSides.THREE) three += 1;
        }

        if (mojo > 0) adjustMojo(currentPlayer, mojo, true);
        if (energy > 0) adjustEnergy(currentPlayer, energy, true);
        if (attack > 0) attack(currentPlayer, attack);
        if (one/3 > 0) juice += (one % 3);
        if (two/3 > 0) juice += (two % 3);
        if (three/3 > 0) juice += (three % 3);

        if (juice > 0) adjustJuice(currentPlayer, juice);
    }

    public void attack(Player attacker, int points)
    {
        if (null == brownsvillePlayer)
        {
            brownsvillePlayer = attacker;
        }
        else if (players.size() >= PLAYERS_FOR_SOUTH_18TH && null == south18thPlayer)
        {
            south18thPlayer = attacker;
        }
        else
        {
            if (brownsvillePlayer == attacker || south18thPlayer == attacker)
            {
                attackOutside(attacker, points);
            }
            else
            {
                attackInside(points);
            }
        }


    }

    public void attackOutside(Player attacker, int points)
    {
        for (Player player : players)
        {
            if (attacker != player && player != brownsvillePlayer && player != south18thPlayer)
            {
                adjustMojo(player, points, false);
            }
        }
    }

    public void attackInside(int points)
    {
        if (null != brownsvillePlayer)
        {
            adjustMojo(brownsvillePlayer, points, false);
            lastAttackerOfCenter = currentPlayer;
        }

        if (null != south18thPlayer)
        {
            adjustMojo(south18thPlayer, points, false);
            lastAttackerOfCenter = currentPlayer;
        }
    }

    public void adjustMojo(Player player, int points, boolean isHealing)
    {
        if (isHealing)
        {
            player.setMojo(player.getMojo() + points);
        }
        else {
            player.setMojo(player.getMojo() - points);
        }

        if (player.getMojo() <= 0) //can't live without mojo!
        {
            //knock out player
            players.remove(player);
            //do something with graphics to visualize they're not playing anymore
            if (players.size() == 1)
            {
                declareWinner(players.get(0));
            }
        }

        if (player.getMojo() > MAX_MOJO) player.setMojo(MAX_MOJO);
    }

    public void adjustJuice(Player player, int points)
    {
        player.setJuice(player.getJuice() + points);

        if (player.getJuice() >= 20)
        {
            //whoo hoo! you won!
            declareWinner(player);
        }
    }

    public void adjustEnergy(Player player, int points, boolean isEnergizing)
    {
        if (isEnergizing)
        {
            player.setEnergy(player.getEnergy() + points);
        }
        else
        {
            player.setEnergy(player.getEnergy() - points);
        }

        if (player.getEnergy() > MAX_ENERGY) player.setEnergy(MAX_ENERGY);
    }

    public void setBrownsvillePlayer(Player player)
    {
        brownsvillePlayer = player;
    }

    public void setSouth18thPlayer(Player player)
    {
        south18thPlayer = player;
    }

    public void leaveBrownsville()
    {
        setBrownsvillePlayer(lastAttackerOfCenter);
    }

    public void leaveSouth18th()
    {
        setSouth18thPlayer(lastAttackerOfCenter);
    }

    public void declareWinner(Player player)
    {

    }

    private class Dice
    {
        boolean isSelected;
        diceSides value;
        int number;
        Random r;

        Dice()
        {
            r = new Random();
            value = diceSides.MOJO;
        }

        void roll()
        {
            number = r.nextInt(6);
            switch (number)
            {
                case 0:
                    value = diceSides.ATTACK;
                    break;
                case 1:
                    value = diceSides.ONE;
                    break;
                case 2:
                    value = diceSides.TWO;
                    break;
                case 3:
                    value = diceSides.THREE;
                    break;
                case 4:
                    value = diceSides.ENERGY;
                    break;
                case 5:
                    value = diceSides.MOJO;
                    break;

            }
        }
    }

    private class SpecialDice
    {

    }
}
