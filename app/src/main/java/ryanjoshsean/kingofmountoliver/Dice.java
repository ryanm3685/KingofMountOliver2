package ryanjoshsean.kingofmountoliver;

import java.util.Random;

/**
 * Created by ryanmcgregor on 3/12/16.
 */
public class Dice {
    boolean isSelected;
    DiceSides.diceSides value;
    int number;
    Random r;

    Dice()
    {
        r = new Random();
        value = DiceSides.diceSides.MOJO;
    }

    void roll()
    {
        number = r.nextInt(6);
        switch (number)
        {
            case 0:
                value = DiceSides.diceSides.ATTACK;
                break;
            case 1:
                value = DiceSides.diceSides.ONE;
                break;
            case 2:
                value = DiceSides.diceSides.TWO;
                break;
            case 3:
                value = DiceSides.diceSides.THREE;
                break;
            case 4:
                value = DiceSides.diceSides.ENERGY;
                break;
            case 5:
                value = DiceSides.diceSides.MOJO;
                break;

        }
    }
}
