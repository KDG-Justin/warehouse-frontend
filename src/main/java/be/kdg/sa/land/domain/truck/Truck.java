package be.kdg.sa.land.domain.truck;

import be.kdg.sa.land.domain.Delivery;
import be.kdg.sa.land.domain.Seller;
import be.kdg.sa.land.domain.ticket.PDT;
import be.kdg.sa.land.domain.ticket.WBT;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Truck {
    @Id
    private String licensePlate;
    private BigDecimal currentWeight;

    @ManyToOne
    private Seller seller;

    @OneToMany
    private List<Delivery> deliveries = new ArrayList<>();

    @OneToMany
    private List<PDT> pdts = new ArrayList<>();

    @OneToMany
    private List<WBT> wbts = new ArrayList<>();

    protected Truck() {}

    public Truck(String licensePlate, BigDecimal currentWeight, Seller seller) {
        this.licensePlate = licensePlate;
        this.currentWeight = currentWeight;
        this.seller = seller;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public BigDecimal getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(BigDecimal currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public List<PDT> getPdts() {
        return pdts;
    }

    public List<WBT> getWbts() {
        return wbts;
    }

    @Override
    public String toString() {
        return "LicensePlate: " + licensePlate;
    }
}
