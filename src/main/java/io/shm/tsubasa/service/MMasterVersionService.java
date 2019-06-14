package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMasterVersion;
import io.shm.tsubasa.repository.MMasterVersionRepository;
import io.shm.tsubasa.service.dto.MMasterVersionDTO;
import io.shm.tsubasa.service.mapper.MMasterVersionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMasterVersion}.
 */
@Service
@Transactional
public class MMasterVersionService {

    private final Logger log = LoggerFactory.getLogger(MMasterVersionService.class);

    private final MMasterVersionRepository mMasterVersionRepository;

    private final MMasterVersionMapper mMasterVersionMapper;

    public MMasterVersionService(MMasterVersionRepository mMasterVersionRepository, MMasterVersionMapper mMasterVersionMapper) {
        this.mMasterVersionRepository = mMasterVersionRepository;
        this.mMasterVersionMapper = mMasterVersionMapper;
    }

    /**
     * Save a mMasterVersion.
     *
     * @param mMasterVersionDTO the entity to save.
     * @return the persisted entity.
     */
    public MMasterVersionDTO save(MMasterVersionDTO mMasterVersionDTO) {
        log.debug("Request to save MMasterVersion : {}", mMasterVersionDTO);
        MMasterVersion mMasterVersion = mMasterVersionMapper.toEntity(mMasterVersionDTO);
        mMasterVersion = mMasterVersionRepository.save(mMasterVersion);
        return mMasterVersionMapper.toDto(mMasterVersion);
    }

    /**
     * Get all the mMasterVersions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMasterVersionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMasterVersions");
        return mMasterVersionRepository.findAll(pageable)
            .map(mMasterVersionMapper::toDto);
    }


    /**
     * Get one mMasterVersion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMasterVersionDTO> findOne(Long id) {
        log.debug("Request to get MMasterVersion : {}", id);
        return mMasterVersionRepository.findById(id)
            .map(mMasterVersionMapper::toDto);
    }

    /**
     * Delete the mMasterVersion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMasterVersion : {}", id);
        mMasterVersionRepository.deleteById(id);
    }
}
