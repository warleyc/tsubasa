package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MStoreReviewUrl;
import io.shm.tsubasa.repository.MStoreReviewUrlRepository;
import io.shm.tsubasa.service.dto.MStoreReviewUrlDTO;
import io.shm.tsubasa.service.mapper.MStoreReviewUrlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MStoreReviewUrl}.
 */
@Service
@Transactional
public class MStoreReviewUrlService {

    private final Logger log = LoggerFactory.getLogger(MStoreReviewUrlService.class);

    private final MStoreReviewUrlRepository mStoreReviewUrlRepository;

    private final MStoreReviewUrlMapper mStoreReviewUrlMapper;

    public MStoreReviewUrlService(MStoreReviewUrlRepository mStoreReviewUrlRepository, MStoreReviewUrlMapper mStoreReviewUrlMapper) {
        this.mStoreReviewUrlRepository = mStoreReviewUrlRepository;
        this.mStoreReviewUrlMapper = mStoreReviewUrlMapper;
    }

    /**
     * Save a mStoreReviewUrl.
     *
     * @param mStoreReviewUrlDTO the entity to save.
     * @return the persisted entity.
     */
    public MStoreReviewUrlDTO save(MStoreReviewUrlDTO mStoreReviewUrlDTO) {
        log.debug("Request to save MStoreReviewUrl : {}", mStoreReviewUrlDTO);
        MStoreReviewUrl mStoreReviewUrl = mStoreReviewUrlMapper.toEntity(mStoreReviewUrlDTO);
        mStoreReviewUrl = mStoreReviewUrlRepository.save(mStoreReviewUrl);
        return mStoreReviewUrlMapper.toDto(mStoreReviewUrl);
    }

    /**
     * Get all the mStoreReviewUrls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MStoreReviewUrlDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MStoreReviewUrls");
        return mStoreReviewUrlRepository.findAll(pageable)
            .map(mStoreReviewUrlMapper::toDto);
    }


    /**
     * Get one mStoreReviewUrl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MStoreReviewUrlDTO> findOne(Long id) {
        log.debug("Request to get MStoreReviewUrl : {}", id);
        return mStoreReviewUrlRepository.findById(id)
            .map(mStoreReviewUrlMapper::toDto);
    }

    /**
     * Delete the mStoreReviewUrl by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MStoreReviewUrl : {}", id);
        mStoreReviewUrlRepository.deleteById(id);
    }
}
