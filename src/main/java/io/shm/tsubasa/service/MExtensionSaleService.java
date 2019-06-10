package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MExtensionSale;
import io.shm.tsubasa.repository.MExtensionSaleRepository;
import io.shm.tsubasa.service.dto.MExtensionSaleDTO;
import io.shm.tsubasa.service.mapper.MExtensionSaleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MExtensionSale}.
 */
@Service
@Transactional
public class MExtensionSaleService {

    private final Logger log = LoggerFactory.getLogger(MExtensionSaleService.class);

    private final MExtensionSaleRepository mExtensionSaleRepository;

    private final MExtensionSaleMapper mExtensionSaleMapper;

    public MExtensionSaleService(MExtensionSaleRepository mExtensionSaleRepository, MExtensionSaleMapper mExtensionSaleMapper) {
        this.mExtensionSaleRepository = mExtensionSaleRepository;
        this.mExtensionSaleMapper = mExtensionSaleMapper;
    }

    /**
     * Save a mExtensionSale.
     *
     * @param mExtensionSaleDTO the entity to save.
     * @return the persisted entity.
     */
    public MExtensionSaleDTO save(MExtensionSaleDTO mExtensionSaleDTO) {
        log.debug("Request to save MExtensionSale : {}", mExtensionSaleDTO);
        MExtensionSale mExtensionSale = mExtensionSaleMapper.toEntity(mExtensionSaleDTO);
        mExtensionSale = mExtensionSaleRepository.save(mExtensionSale);
        return mExtensionSaleMapper.toDto(mExtensionSale);
    }

    /**
     * Get all the mExtensionSales.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MExtensionSaleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MExtensionSales");
        return mExtensionSaleRepository.findAll(pageable)
            .map(mExtensionSaleMapper::toDto);
    }


    /**
     * Get one mExtensionSale by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MExtensionSaleDTO> findOne(Long id) {
        log.debug("Request to get MExtensionSale : {}", id);
        return mExtensionSaleRepository.findById(id)
            .map(mExtensionSaleMapper::toDto);
    }

    /**
     * Delete the mExtensionSale by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MExtensionSale : {}", id);
        mExtensionSaleRepository.deleteById(id);
    }
}
