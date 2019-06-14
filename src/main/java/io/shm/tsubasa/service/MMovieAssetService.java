package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMovieAsset;
import io.shm.tsubasa.repository.MMovieAssetRepository;
import io.shm.tsubasa.service.dto.MMovieAssetDTO;
import io.shm.tsubasa.service.mapper.MMovieAssetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMovieAsset}.
 */
@Service
@Transactional
public class MMovieAssetService {

    private final Logger log = LoggerFactory.getLogger(MMovieAssetService.class);

    private final MMovieAssetRepository mMovieAssetRepository;

    private final MMovieAssetMapper mMovieAssetMapper;

    public MMovieAssetService(MMovieAssetRepository mMovieAssetRepository, MMovieAssetMapper mMovieAssetMapper) {
        this.mMovieAssetRepository = mMovieAssetRepository;
        this.mMovieAssetMapper = mMovieAssetMapper;
    }

    /**
     * Save a mMovieAsset.
     *
     * @param mMovieAssetDTO the entity to save.
     * @return the persisted entity.
     */
    public MMovieAssetDTO save(MMovieAssetDTO mMovieAssetDTO) {
        log.debug("Request to save MMovieAsset : {}", mMovieAssetDTO);
        MMovieAsset mMovieAsset = mMovieAssetMapper.toEntity(mMovieAssetDTO);
        mMovieAsset = mMovieAssetRepository.save(mMovieAsset);
        return mMovieAssetMapper.toDto(mMovieAsset);
    }

    /**
     * Get all the mMovieAssets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMovieAssetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMovieAssets");
        return mMovieAssetRepository.findAll(pageable)
            .map(mMovieAssetMapper::toDto);
    }


    /**
     * Get one mMovieAsset by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMovieAssetDTO> findOne(Long id) {
        log.debug("Request to get MMovieAsset : {}", id);
        return mMovieAssetRepository.findById(id)
            .map(mMovieAssetMapper::toDto);
    }

    /**
     * Delete the mMovieAsset by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMovieAsset : {}", id);
        mMovieAssetRepository.deleteById(id);
    }
}
