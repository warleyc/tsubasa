package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionTradeSign;
import io.shm.tsubasa.repository.MGachaRenditionTradeSignRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionTradeSignDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTradeSignMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionTradeSign}.
 */
@Service
@Transactional
public class MGachaRenditionTradeSignService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTradeSignService.class);

    private final MGachaRenditionTradeSignRepository mGachaRenditionTradeSignRepository;

    private final MGachaRenditionTradeSignMapper mGachaRenditionTradeSignMapper;

    public MGachaRenditionTradeSignService(MGachaRenditionTradeSignRepository mGachaRenditionTradeSignRepository, MGachaRenditionTradeSignMapper mGachaRenditionTradeSignMapper) {
        this.mGachaRenditionTradeSignRepository = mGachaRenditionTradeSignRepository;
        this.mGachaRenditionTradeSignMapper = mGachaRenditionTradeSignMapper;
    }

    /**
     * Save a mGachaRenditionTradeSign.
     *
     * @param mGachaRenditionTradeSignDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionTradeSignDTO save(MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO) {
        log.debug("Request to save MGachaRenditionTradeSign : {}", mGachaRenditionTradeSignDTO);
        MGachaRenditionTradeSign mGachaRenditionTradeSign = mGachaRenditionTradeSignMapper.toEntity(mGachaRenditionTradeSignDTO);
        mGachaRenditionTradeSign = mGachaRenditionTradeSignRepository.save(mGachaRenditionTradeSign);
        return mGachaRenditionTradeSignMapper.toDto(mGachaRenditionTradeSign);
    }

    /**
     * Get all the mGachaRenditionTradeSigns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionTradeSignDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionTradeSigns");
        return mGachaRenditionTradeSignRepository.findAll(pageable)
            .map(mGachaRenditionTradeSignMapper::toDto);
    }


    /**
     * Get one mGachaRenditionTradeSign by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionTradeSignDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionTradeSign : {}", id);
        return mGachaRenditionTradeSignRepository.findById(id)
            .map(mGachaRenditionTradeSignMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionTradeSign by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionTradeSign : {}", id);
        mGachaRenditionTradeSignRepository.deleteById(id);
    }
}
