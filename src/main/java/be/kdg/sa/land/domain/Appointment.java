package be.kdg.sa.land.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private LocalDateTime arrivalTime;

    @ManyToOne
    private Seller seller;

    @Column(unique = true)
    private String truckLicensePlate;

    protected Appointment() {}

    public Appointment(LocalDateTime arrivalTime, Seller seller, String truckLicensePlate) {
        this.arrivalTime = arrivalTime;
        this.seller = seller;
        this.truckLicensePlate = truckLicensePlate;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getTruckLicensePlate() {
        return truckLicensePlate;
    }

    public void setTruckLicensePlate(String truckLicensePlate) {
        this.truckLicensePlate = truckLicensePlate;
    }

    @Override
    public String toString() {
        return String.format("Appointment [arrivalTime=%s, seller=%s]", getArrivalTime(), getSeller().getName());
    }
}
