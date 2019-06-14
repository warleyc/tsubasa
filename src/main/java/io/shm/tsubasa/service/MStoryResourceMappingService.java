package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MStoryResourceMapping;
import io.shm.tsubasa.repository.MStoryResourceMappingRepository;
import io.shm.tsubasa.service.dto.MStoryResourceMappingDTO;
import io.shm.tsubasa.service.mapper.MStoryResourceMappingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MStoryResourceMapping}.
 */
@Service
@Transactional
public class MStoryResourceMappingService {

    private final Logger log = LoggerFactory.getLogger(MStoryResourceMappingService.class);

    private final MStoryResourceMappingRepository mStoryResourceMappingRepository;

    private final MStoryResourceMappingMapper mStoryResourceMappingMapper;

    public MStoryResourceMappingService(MStoryResourceMappingRepository mStoryResourceMappingRepository, MStoryResourceMappingMapper mStoryResourceMappingMapper) {
        this.mStoryResourceMappingRepository = mStoryResourceMappingRepository;
        this.mStoryResourceMappingMapper = mStoryResourceMappingMapper;
    }

    /**
     * Save a mStoryResourceMapping.
     *
     * @param mStoryResourceMappingDTO the entity to save.
     * @return the persisted entity.
     */
    public MStoryResourceMappingDTO save(MStoryResourceMappingDTO mStoryResourceMappingDTO) {
        log.debug("Request to save MStoryResourceMapping : {}", mStoryResourceMappingDTO);
        MStoryResourceMapping mStoryResourceMapping = mStoryResourceMappingMapper.toEntity(mStoryResourceMappingDTO);
        mStoryResourceMapping = mStoryResourceMappingRepository.save(mStoryResourceMapping);
        return mStoryResourceMappingMapper.toDto(mStoryResourceMapping);
    }

    /**
     * Get all the mStoryResourceMappings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MStoryResourceMappingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MStoryResourceMappings");
        return mStoryResourceMappingRepository.findAll(pageable)
            .map(mStoryResourceMappingMapper::toDto);
    }


    /**
     * Get one mStoryResourceMapping by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MStoryResourceMappingDTO> findOne(Long id) {
        log.debug("Request to get MStoryResourceMapping : {}", id);
        return mStoryResourceMappingRepository.findById(id)
            .map(mStoryResourceMappingMapper::toDto);
    }

    /**
     * Delete the mStoryResourceMapping by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MStoryResourceMapping : {}", id);
        mStoryResourceMappingRepository.deleteById(id);
    }
}
