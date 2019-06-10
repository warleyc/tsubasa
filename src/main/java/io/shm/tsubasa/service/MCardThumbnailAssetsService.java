package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCardThumbnailAssets;
import io.shm.tsubasa.repository.MCardThumbnailAssetsRepository;
import io.shm.tsubasa.service.dto.MCardThumbnailAssetsDTO;
import io.shm.tsubasa.service.mapper.MCardThumbnailAssetsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCardThumbnailAssets}.
 */
@Service
@Transactional
public class MCardThumbnailAssetsService {

    private final Logger log = LoggerFactory.getLogger(MCardThumbnailAssetsService.class);

    private final MCardThumbnailAssetsRepository mCardThumbnailAssetsRepository;

    private final MCardThumbnailAssetsMapper mCardThumbnailAssetsMapper;

    public MCardThumbnailAssetsService(MCardThumbnailAssetsRepository mCardThumbnailAssetsRepository, MCardThumbnailAssetsMapper mCardThumbnailAssetsMapper) {
        this.mCardThumbnailAssetsRepository = mCardThumbnailAssetsRepository;
        this.mCardThumbnailAssetsMapper = mCardThumbnailAssetsMapper;
    }

    /**
     * Save a mCardThumbnailAssets.
     *
     * @param mCardThumbnailAssetsDTO the entity to save.
     * @return the persisted entity.
     */
    public MCardThumbnailAssetsDTO save(MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO) {
        log.debug("Request to save MCardThumbnailAssets : {}", mCardThumbnailAssetsDTO);
        MCardThumbnailAssets mCardThumbnailAssets = mCardThumbnailAssetsMapper.toEntity(mCardThumbnailAssetsDTO);
        mCardThumbnailAssets = mCardThumbnailAssetsRepository.save(mCardThumbnailAssets);
        return mCardThumbnailAssetsMapper.toDto(mCardThumbnailAssets);
    }

    /**
     * Get all the mCardThumbnailAssets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardThumbnailAssetsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCardThumbnailAssets");
        return mCardThumbnailAssetsRepository.findAll(pageable)
            .map(mCardThumbnailAssetsMapper::toDto);
    }


    /**
     * Get one mCardThumbnailAssets by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCardThumbnailAssetsDTO> findOne(Long id) {
        log.debug("Request to get MCardThumbnailAssets : {}", id);
        return mCardThumbnailAssetsRepository.findById(id)
            .map(mCardThumbnailAssetsMapper::toDto);
    }

    /**
     * Delete the mCardThumbnailAssets by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCardThumbnailAssets : {}", id);
        mCardThumbnailAssetsRepository.deleteById(id);
    }
}
