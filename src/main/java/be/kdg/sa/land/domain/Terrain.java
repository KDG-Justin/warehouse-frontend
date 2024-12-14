package be.kdg.sa.land.domain;

import be.kdg.sa.land.domain.truck.Truck;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Terrain {
    @Id
    private String name;

    @OneToMany
    private List<Truck> trucks = new ArrayList<>();

    @OneToMany
    private List<Truck> fifoTrucks = new ArrayList<>();

    public Terrain(String name) {
        this.name = name;
    }

    protected Terrain() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public List<Truck> getFifoTrucks() {
        return fifoTrucks;
    }

    public void setFifoTrucks(List<Truck> fifoTrucks) {
        this.fifoTrucks = fifoTrucks;
    }
}
