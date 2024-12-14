package be.kdg.sa.land.controller.api;


import be.kdg.sa.land.controller.dto.terrain.ShowTerrainDto;
import be.kdg.sa.land.service.TerrainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/terrain")
public class TerrainRestController {
    private final TerrainService terrainService;

    public TerrainRestController(TerrainService terrainService) {
        this.terrainService = terrainService;
    }


    @GetMapping("")
    public ShowTerrainDto getTerrainTrucks() {
        return new ShowTerrainDto(terrainService.findTerrain("Main-Terrain"));
    }


}
