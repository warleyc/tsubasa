package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionSwipeIcon;
import io.shm.tsubasa.repository.MGachaRenditionSwipeIconRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionSwipeIconDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionSwipeIconMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionSwipeIcon}.
 */
@Service
@Transactional
public class MGachaRenditionSwipeIconService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionSwipeIconService.class);

    private final MGachaRenditionSwipeIconRepository mGachaRenditionSwipeIconRepository;

    private final MGachaRenditionSwipeIconMapper mGachaRenditionSwipeIconMapper;

    public MGachaRenditionSwipeIconService(MGachaRenditionSwipeIconRepository mGachaRenditionSwipeIconRepository, MGachaRenditionSwipeIconMapper mGachaRenditionSwipeIconMapper) {
        this.mGachaRenditionSwipeIconRepository = mGachaRenditionSwipeIconRepository;
        this.mGachaRenditionSwipeIconMapper = mGachaRenditionSwipeIconMapper;
    }

    /**
     * Save a mGachaRenditionSwipeIcon.
     *
     * @param mGachaRenditionSwipeIconDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionSwipeIconDTO save(MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO) {
        log.debug("Request to save MGachaRenditionSwipeIcon : {}", mGachaRenditionSwipeIconDTO);
        MGachaRenditionSwipeIcon mGachaRenditionSwipeIcon = mGachaRenditionSwipeIconMapper.toEntity(mGachaRenditionSwipeIconDTO);
        mGachaRenditionSwipeIcon = mGachaRenditionSwipeIconRepository.save(mGachaRenditionSwipeIcon);
        return mGachaRenditionSwipeIconMapper.toDto(mGachaRenditionSwipeIcon);
    }

    /**
     * Get all the mGachaRenditionSwipeIcons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionSwipeIconDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionSwipeIcons");
        return mGachaRenditionSwipeIconRepository.findAll(pageable)
            .map(mGachaRenditionSwipeIconMapper::toDto);
    }


    /**
     * Get one mGachaRenditionSwipeIcon by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionSwipeIconDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionSwipeIcon : {}", id);
        return mGachaRenditionSwipeIconRepository.findById(id)
            .map(mGachaRenditionSwipeIconMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionSwipeIcon by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionSwipeIcon : {}", id);
        mGachaRenditionSwipeIconRepository.deleteById(id);
    }
}
