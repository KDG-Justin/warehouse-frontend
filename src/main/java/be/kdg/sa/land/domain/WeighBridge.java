package be.kdg.sa.land.domain;

import jakarta.persistence.*;

@Entity
public class WeighBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int WBN;

    private String name;

    protected WeighBridge(){}

    public WeighBridge(String name) {
        this.name = name;
    }

    public WeighBridge(int WBN){
        this.WBN = WBN;
    }

    public int getWBN() {
        return WBN;
    }

    public void setWBN(int WBN) {
        this.WBN = WBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WBN = "+ WBN;
    }
}
