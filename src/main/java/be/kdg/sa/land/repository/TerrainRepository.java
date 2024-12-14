package be.kdg.sa.land.repository;


import be.kdg.sa.land.domain.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerrainRepository extends JpaRepository<Terrain, String> {

    Terrain findTerrainByName(String name);

}
