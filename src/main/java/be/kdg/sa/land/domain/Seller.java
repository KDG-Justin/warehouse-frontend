package be.kdg.sa.land.domain;

import be.kdg.sa.land.domain.truck.Truck;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String address;

    @OneToMany(orphanRemoval = true, mappedBy = "seller")
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy = "seller")
    private List<Truck> trucks = new ArrayList<>();

    protected Seller() {}

    public Seller(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }
}
