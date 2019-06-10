package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MAssetTagMapping;
import io.shm.tsubasa.repository.MAssetTagMappingRepository;
import io.shm.tsubasa.service.dto.MAssetTagMappingDTO;
import io.shm.tsubasa.service.mapper.MAssetTagMappingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MAssetTagMapping}.
 */
@Service
@Transactional
public class MAssetTagMappingService {

    private final Logger log = LoggerFactory.getLogger(MAssetTagMappingService.class);

    private final MAssetTagMappingRepository mAssetTagMappingRepository;

    private final MAssetTagMappingMapper mAssetTagMappingMapper;

    public MAssetTagMappingService(MAssetTagMappingRepository mAssetTagMappingRepository, MAssetTagMappingMapper mAssetTagMappingMapper) {
        this.mAssetTagMappingRepository = mAssetTagMappingRepository;
        this.mAssetTagMappingMapper = mAssetTagMappingMapper;
    }

    /**
     * Save a mAssetTagMapping.
     *
     * @param mAssetTagMappingDTO the entity to save.
     * @return the persisted entity.
     */
    public MAssetTagMappingDTO save(MAssetTagMappingDTO mAssetTagMappingDTO) {
        log.debug("Request to save MAssetTagMapping : {}", mAssetTagMappingDTO);
        MAssetTagMapping mAssetTagMapping = mAssetTagMappingMapper.toEntity(mAssetTagMappingDTO);
        mAssetTagMapping = mAssetTagMappingRepository.save(mAssetTagMapping);
        return mAssetTagMappingMapper.toDto(mAssetTagMapping);
    }

    /**
     * Get all the mAssetTagMappings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MAssetTagMappingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MAssetTagMappings");
        return mAssetTagMappingRepository.findAll(pageable)
            .map(mAssetTagMappingMapper::toDto);
    }


    /**
     * Get one mAssetTagMapping by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MAssetTagMappingDTO> findOne(Long id) {
        log.debug("Request to get MAssetTagMapping : {}", id);
        return mAssetTagMappingRepository.findById(id)
            .map(mAssetTagMappingMapper::toDto);
    }

    /**
     * Delete the mAssetTagMapping by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MAssetTagMapping : {}", id);
        mAssetTagMappingRepository.deleteById(id);
    }
}
