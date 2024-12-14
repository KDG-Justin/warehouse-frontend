package be.kdg.sa.land.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class FindWarehouseService {
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(FindWarehouseService.class);


    @Value("${spring.application.warehouse.URL}")
    private String WAREHOUSE_URL;

    @Value("${retries}")
    private int retry;


    public FindWarehouseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Transactional(readOnly = true)
    public UUID receiveWarehouseId(String type, String sellerName, String sellerAddress) {
        logger.info("   Finding warehouse id from {} for truck delivery type {}.", sellerName, type);
        String fullUrl = WAREHOUSE_URL+ "/warehouses/" + type + "/" + sellerName + "/" + sellerAddress;
        UUID warehouseId = null;

        for (int i = 0; i < retry; i++) {
            warehouseId = restTemplate.getForEntity(fullUrl, UUID.class).getBody();
            if (warehouseId != null) {
                break;
            }
        }
        return warehouseId;
    }

    @Transactional(readOnly = true)
    public BigDecimal receiveWarehouseOccupancyPercentage(UUID warehouseId) {
        logger.info("   Finding warehouse occupancy for {}", warehouseId);
        String fullUrl = WAREHOUSE_URL+ "/warehouses/" + warehouseId;
        return restTemplate.getForEntity(fullUrl, BigDecimal.class).getBody();
    }
}
