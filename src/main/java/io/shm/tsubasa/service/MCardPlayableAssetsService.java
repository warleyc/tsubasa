package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCardPlayableAssets;
import io.shm.tsubasa.repository.MCardPlayableAssetsRepository;
import io.shm.tsubasa.service.dto.MCardPlayableAssetsDTO;
import io.shm.tsubasa.service.mapper.MCardPlayableAssetsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCardPlayableAssets}.
 */
@Service
@Transactional
public class MCardPlayableAssetsService {

    private final Logger log = LoggerFactory.getLogger(MCardPlayableAssetsService.class);

    private final MCardPlayableAssetsRepository mCardPlayableAssetsRepository;

    private final MCardPlayableAssetsMapper mCardPlayableAssetsMapper;

    public MCardPlayableAssetsService(MCardPlayableAssetsRepository mCardPlayableAssetsRepository, MCardPlayableAssetsMapper mCardPlayableAssetsMapper) {
        this.mCardPlayableAssetsRepository = mCardPlayableAssetsRepository;
        this.mCardPlayableAssetsMapper = mCardPlayableAssetsMapper;
    }

    /**
     * Save a mCardPlayableAssets.
     *
     * @param mCardPlayableAssetsDTO the entity to save.
     * @return the persisted entity.
     */
    public MCardPlayableAssetsDTO save(MCardPlayableAssetsDTO mCardPlayableAssetsDTO) {
        log.debug("Request to save MCardPlayableAssets : {}", mCardPlayableAssetsDTO);
        MCardPlayableAssets mCardPlayableAssets = mCardPlayableAssetsMapper.toEntity(mCardPlayableAssetsDTO);
        mCardPlayableAssets = mCardPlayableAssetsRepository.save(mCardPlayableAssets);
        return mCardPlayableAssetsMapper.toDto(mCardPlayableAssets);
    }

    /**
     * Get all the mCardPlayableAssets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardPlayableAssetsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCardPlayableAssets");
        return mCardPlayableAssetsRepository.findAll(pageable)
            .map(mCardPlayableAssetsMapper::toDto);
    }


    /**
     * Get one mCardPlayableAssets by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCardPlayableAssetsDTO> findOne(Long id) {
        log.debug("Request to get MCardPlayableAssets : {}", id);
        return mCardPlayableAssetsRepository.findById(id)
            .map(mCardPlayableAssetsMapper::toDto);
    }

    /**
     * Delete the mCardPlayableAssets by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCardPlayableAssets : {}", id);
        mCardPlayableAssetsRepository.deleteById(id);
    }
}
