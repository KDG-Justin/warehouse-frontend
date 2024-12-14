package be.kdg.sa.land.service;


import be.kdg.sa.land.controller.dto.terrain.TerrainDto;
import be.kdg.sa.land.domain.Terrain;
import be.kdg.sa.land.domain.truck.Truck;
import be.kdg.sa.land.repository.TerrainRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TerrainService {
    private final TerrainRepository terrainRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(TerrainService.class);

    public TerrainService(TerrainRepository terrainRepository, ModelMapper modelMapper) {
        this.terrainRepository = terrainRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public TerrainDto findTerrain(String terrainName) {
        logger.info("Looking for terrain: {}", terrainName);
        Terrain terrain = terrainRepository.findTerrainByName(terrainName);
        return modelMapper.map(terrain, TerrainDto.class);
    }


    @Transactional
    public void updateTerrainByTrucks(Truck truck) {
        logger.info("Adding truck {} to terrain Main-Terrain", truck.getLicensePlate());
        Terrain mainTerrain = terrainRepository.findTerrainByName("Main-Terrain");
        mainTerrain.getTrucks().add(truck);
    }

    @Transactional
    public void updateTerrainByFIFOTrucks(Truck truck) {
        logger.info("   Adding trucks to FIFO row in Main-Terrain");
        Terrain mainTerrain = terrainRepository.findTerrainByName("Main-Terrain");
        mainTerrain.getFifoTrucks().add(truck);
    }

    @Transactional
    public void deleteTruckFromTerrain(Truck truck) {
        logger.info("   Deleting truck {} terrain Main-Terrain", truck.getLicensePlate());
        Terrain mainTerrain = terrainRepository.findTerrainByName("Main-Terrain");
        mainTerrain.getTrucks().remove(truck);
    }

    @Transactional
    public void deleteTruckFromFIFOTerrain(Truck truck) {
        logger.info("   Deleting Truck {} in FIFO row from Main-Terrain", truck.getLicensePlate());
        Terrain mainTerrain = terrainRepository.findTerrainByName("Main-Terrain");
        mainTerrain.getFifoTrucks().remove(truck);
    }
}
