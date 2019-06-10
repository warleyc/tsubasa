package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionKicker;
import io.shm.tsubasa.repository.MGachaRenditionKickerRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionKickerDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionKickerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionKicker}.
 */
@Service
@Transactional
public class MGachaRenditionKickerService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionKickerService.class);

    private final MGachaRenditionKickerRepository mGachaRenditionKickerRepository;

    private final MGachaRenditionKickerMapper mGachaRenditionKickerMapper;

    public MGachaRenditionKickerService(MGachaRenditionKickerRepository mGachaRenditionKickerRepository, MGachaRenditionKickerMapper mGachaRenditionKickerMapper) {
        this.mGachaRenditionKickerRepository = mGachaRenditionKickerRepository;
        this.mGachaRenditionKickerMapper = mGachaRenditionKickerMapper;
    }

    /**
     * Save a mGachaRenditionKicker.
     *
     * @param mGachaRenditionKickerDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionKickerDTO save(MGachaRenditionKickerDTO mGachaRenditionKickerDTO) {
        log.debug("Request to save MGachaRenditionKicker : {}", mGachaRenditionKickerDTO);
        MGachaRenditionKicker mGachaRenditionKicker = mGachaRenditionKickerMapper.toEntity(mGachaRenditionKickerDTO);
        mGachaRenditionKicker = mGachaRenditionKickerRepository.save(mGachaRenditionKicker);
        return mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);
    }

    /**
     * Get all the mGachaRenditionKickers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionKickerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionKickers");
        return mGachaRenditionKickerRepository.findAll(pageable)
            .map(mGachaRenditionKickerMapper::toDto);
    }


    /**
     * Get one mGachaRenditionKicker by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionKickerDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionKicker : {}", id);
        return mGachaRenditionKickerRepository.findById(id)
            .map(mGachaRenditionKickerMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionKicker by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionKicker : {}", id);
        mGachaRenditionKickerRepository.deleteById(id);
    }
}
