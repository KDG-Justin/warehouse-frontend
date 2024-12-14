package be.kdg.sa.land.controller.dto.terrain;

import be.kdg.sa.land.domain.truck.Truck;

import java.util.List;

public class TerrainDto {
    private String terrainName;
    private List<Truck> trucks;
    private List<Truck> fifoTrucks;

    public TerrainDto() {}


    public String getTerrainName() {
        return terrainName;
    }

    public void setTerrainName(String terrainName) {
        this.terrainName = terrainName;
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
