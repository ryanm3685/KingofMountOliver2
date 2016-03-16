package ryanjoshsean.kingofmountoliver;

import java.util.Random;

/**
 * Created by ryanmcgregor on 3/12/16.
 */
public class Dice {
    boolean isSelected;
    EnumClass.diceSides value;
    int number;
    Random r;

    Dice()
    {
        r = new Random();
        value = EnumClass.diceSides.MOJO;
    }

    void roll()
    {
        number = r.nextInt(6);
        switch (number)
        {
            case 0:
                value = EnumClass.diceSides.ATTACK;
                break;
            case 1:
                value = EnumClass.diceSides.ONE;
                break;
            case 2:
                value = EnumClass.diceSides.TWO;
                break;
            case 3:
                value = EnumClass.diceSides.THREE;
                break;
            case 4:
                value = EnumClass.diceSides.ENERGY;
                break;
            case 5:
                value = EnumClass.diceSides.MOJO;
                break;

        }
    }
}
