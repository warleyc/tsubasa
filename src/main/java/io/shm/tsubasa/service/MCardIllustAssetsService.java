package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCardIllustAssets;
import io.shm.tsubasa.repository.MCardIllustAssetsRepository;
import io.shm.tsubasa.service.dto.MCardIllustAssetsDTO;
import io.shm.tsubasa.service.mapper.MCardIllustAssetsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCardIllustAssets}.
 */
@Service
@Transactional
public class MCardIllustAssetsService {

    private final Logger log = LoggerFactory.getLogger(MCardIllustAssetsService.class);

    private final MCardIllustAssetsRepository mCardIllustAssetsRepository;

    private final MCardIllustAssetsMapper mCardIllustAssetsMapper;

    public MCardIllustAssetsService(MCardIllustAssetsRepository mCardIllustAssetsRepository, MCardIllustAssetsMapper mCardIllustAssetsMapper) {
        this.mCardIllustAssetsRepository = mCardIllustAssetsRepository;
        this.mCardIllustAssetsMapper = mCardIllustAssetsMapper;
    }

    /**
     * Save a mCardIllustAssets.
     *
     * @param mCardIllustAssetsDTO the entity to save.
     * @return the persisted entity.
     */
    public MCardIllustAssetsDTO save(MCardIllustAssetsDTO mCardIllustAssetsDTO) {
        log.debug("Request to save MCardIllustAssets : {}", mCardIllustAssetsDTO);
        MCardIllustAssets mCardIllustAssets = mCardIllustAssetsMapper.toEntity(mCardIllustAssetsDTO);
        mCardIllustAssets = mCardIllustAssetsRepository.save(mCardIllustAssets);
        return mCardIllustAssetsMapper.toDto(mCardIllustAssets);
    }

    /**
     * Get all the mCardIllustAssets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardIllustAssetsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCardIllustAssets");
        return mCardIllustAssetsRepository.findAll(pageable)
            .map(mCardIllustAssetsMapper::toDto);
    }


    /**
     * Get one mCardIllustAssets by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCardIllustAssetsDTO> findOne(Long id) {
        log.debug("Request to get MCardIllustAssets : {}", id);
        return mCardIllustAssetsRepository.findById(id)
            .map(mCardIllustAssetsMapper::toDto);
    }

    /**
     * Delete the mCardIllustAssets by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCardIllustAssets : {}", id);
        mCardIllustAssetsRepository.deleteById(id);
    }
}
