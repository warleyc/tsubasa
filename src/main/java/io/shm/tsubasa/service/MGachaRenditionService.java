package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRendition;
import io.shm.tsubasa.repository.MGachaRenditionRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRendition}.
 */
@Service
@Transactional
public class MGachaRenditionService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionService.class);

    private final MGachaRenditionRepository mGachaRenditionRepository;

    private final MGachaRenditionMapper mGachaRenditionMapper;

    public MGachaRenditionService(MGachaRenditionRepository mGachaRenditionRepository, MGachaRenditionMapper mGachaRenditionMapper) {
        this.mGachaRenditionRepository = mGachaRenditionRepository;
        this.mGachaRenditionMapper = mGachaRenditionMapper;
    }

    /**
     * Save a mGachaRendition.
     *
     * @param mGachaRenditionDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionDTO save(MGachaRenditionDTO mGachaRenditionDTO) {
        log.debug("Request to save MGachaRendition : {}", mGachaRenditionDTO);
        MGachaRendition mGachaRendition = mGachaRenditionMapper.toEntity(mGachaRenditionDTO);
        mGachaRendition = mGachaRenditionRepository.save(mGachaRendition);
        return mGachaRenditionMapper.toDto(mGachaRendition);
    }

    /**
     * Get all the mGachaRenditions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditions");
        return mGachaRenditionRepository.findAll(pageable)
            .map(mGachaRenditionMapper::toDto);
    }


    /**
     * Get one mGachaRendition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionDTO> findOne(Long id) {
        log.debug("Request to get MGachaRendition : {}", id);
        return mGachaRenditionRepository.findById(id)
            .map(mGachaRenditionMapper::toDto);
    }

    /**
     * Delete the mGachaRendition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRendition : {}", id);
        mGachaRenditionRepository.deleteById(id);
    }
}
