package be.kdg.sa.land.controller.dto.terrain;

public class ShowTerrainDto {
    private int trucks;
    private int fifoTrucks;

    public ShowTerrainDto() {}

    public ShowTerrainDto(TerrainDto terrainDto) {
        this.trucks = terrainDto.getTrucks().size();
        this.fifoTrucks = terrainDto.getFifoTrucks().size();
    }

    public int getTrucks() {
        return trucks;
    }

    public void setTrucks(int trucks) {
        this.trucks = trucks;
    }

    public int getFifoTrucks() {
        return fifoTrucks;
    }

    public void setFifoTrucks(int fifoTrucks) {
        this.fifoTrucks = fifoTrucks;
    }
}
