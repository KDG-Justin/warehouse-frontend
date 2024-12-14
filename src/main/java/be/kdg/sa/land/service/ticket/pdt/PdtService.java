package be.kdg.sa.land.service.ticket.pdt;

import be.kdg.sa.land.domain.ticket.PDT;
import be.kdg.sa.land.repository.ticket.PdtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PdtService {
    private final PdtRepository pdtRepository;
    private final static Logger logger = LoggerFactory.getLogger(PdtService.class);

    public PdtService(PdtRepository pdtRepository) {
        this.pdtRepository = pdtRepository;
    }

    @Transactional
    public PDT createPDT(PDT pdt) {
        logger.info("   Creating PDT with type {}", pdt.getMaterialType().toString());
        return pdtRepository.save(pdt);
    }
}
