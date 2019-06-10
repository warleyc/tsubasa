package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MAsset;
import io.shm.tsubasa.repository.MAssetRepository;
import io.shm.tsubasa.service.dto.MAssetDTO;
import io.shm.tsubasa.service.mapper.MAssetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MAsset}.
 */
@Service
@Transactional
public class MAssetService {

    private final Logger log = LoggerFactory.getLogger(MAssetService.class);

    private final MAssetRepository mAssetRepository;

    private final MAssetMapper mAssetMapper;

    public MAssetService(MAssetRepository mAssetRepository, MAssetMapper mAssetMapper) {
        this.mAssetRepository = mAssetRepository;
        this.mAssetMapper = mAssetMapper;
    }

    /**
     * Save a mAsset.
     *
     * @param mAssetDTO the entity to save.
     * @return the persisted entity.
     */
    public MAssetDTO save(MAssetDTO mAssetDTO) {
        log.debug("Request to save MAsset : {}", mAssetDTO);
        MAsset mAsset = mAssetMapper.toEntity(mAssetDTO);
        mAsset = mAssetRepository.save(mAsset);
        return mAssetMapper.toDto(mAsset);
    }

    /**
     * Get all the mAssets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MAssetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MAssets");
        return mAssetRepository.findAll(pageable)
            .map(mAssetMapper::toDto);
    }


    /**
     * Get one mAsset by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MAssetDTO> findOne(Long id) {
        log.debug("Request to get MAsset : {}", id);
        return mAssetRepository.findById(id)
            .map(mAssetMapper::toDto);
    }

    /**
     * Delete the mAsset by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MAsset : {}", id);
        mAssetRepository.deleteById(id);
    }
}
