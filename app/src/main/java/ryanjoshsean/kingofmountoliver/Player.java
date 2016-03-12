package ryanjoshsean.kingofmountoliver;

import java.util.ArrayList;

/**
 * Created by ryanmcgregor on 3/11/16.
 */
public class Player {
    int image, mojo = 10, juice, energy;
    boolean isAI;
    String name;
    ArrayList<Card> cards;

    public boolean isAI() {
        return isAI;
    }

    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getMojo() {
        return mojo;
    }

    public void setMojo(int mojo) {
        this.mojo = mojo;
    }

    public int getJuice() {
        return juice;
    }

    public void setJuice(int juice) {
        this.juice = juice;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
