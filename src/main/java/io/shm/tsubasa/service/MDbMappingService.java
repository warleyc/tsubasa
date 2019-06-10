package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDbMapping;
import io.shm.tsubasa.repository.MDbMappingRepository;
import io.shm.tsubasa.service.dto.MDbMappingDTO;
import io.shm.tsubasa.service.mapper.MDbMappingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDbMapping}.
 */
@Service
@Transactional
public class MDbMappingService {

    private final Logger log = LoggerFactory.getLogger(MDbMappingService.class);

    private final MDbMappingRepository mDbMappingRepository;

    private final MDbMappingMapper mDbMappingMapper;

    public MDbMappingService(MDbMappingRepository mDbMappingRepository, MDbMappingMapper mDbMappingMapper) {
        this.mDbMappingRepository = mDbMappingRepository;
        this.mDbMappingMapper = mDbMappingMapper;
    }

    /**
     * Save a mDbMapping.
     *
     * @param mDbMappingDTO the entity to save.
     * @return the persisted entity.
     */
    public MDbMappingDTO save(MDbMappingDTO mDbMappingDTO) {
        log.debug("Request to save MDbMapping : {}", mDbMappingDTO);
        MDbMapping mDbMapping = mDbMappingMapper.toEntity(mDbMappingDTO);
        mDbMapping = mDbMappingRepository.save(mDbMapping);
        return mDbMappingMapper.toDto(mDbMapping);
    }

    /**
     * Get all the mDbMappings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDbMappingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDbMappings");
        return mDbMappingRepository.findAll(pageable)
            .map(mDbMappingMapper::toDto);
    }


    /**
     * Get one mDbMapping by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDbMappingDTO> findOne(Long id) {
        log.debug("Request to get MDbMapping : {}", id);
        return mDbMappingRepository.findById(id)
            .map(mDbMappingMapper::toDto);
    }

    /**
     * Delete the mDbMapping by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDbMapping : {}", id);
        mDbMappingRepository.deleteById(id);
    }
}
