package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTips;
import io.shm.tsubasa.repository.MTipsRepository;
import io.shm.tsubasa.service.dto.MTipsDTO;
import io.shm.tsubasa.service.mapper.MTipsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTips}.
 */
@Service
@Transactional
public class MTipsService {

    private final Logger log = LoggerFactory.getLogger(MTipsService.class);

    private final MTipsRepository mTipsRepository;

    private final MTipsMapper mTipsMapper;

    public MTipsService(MTipsRepository mTipsRepository, MTipsMapper mTipsMapper) {
        this.mTipsRepository = mTipsRepository;
        this.mTipsMapper = mTipsMapper;
    }

    /**
     * Save a mTips.
     *
     * @param mTipsDTO the entity to save.
     * @return the persisted entity.
     */
    public MTipsDTO save(MTipsDTO mTipsDTO) {
        log.debug("Request to save MTips : {}", mTipsDTO);
        MTips mTips = mTipsMapper.toEntity(mTipsDTO);
        mTips = mTipsRepository.save(mTips);
        return mTipsMapper.toDto(mTips);
    }

    /**
     * Get all the mTips.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTipsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTips");
        return mTipsRepository.findAll(pageable)
            .map(mTipsMapper::toDto);
    }


    /**
     * Get one mTips by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTipsDTO> findOne(Long id) {
        log.debug("Request to get MTips : {}", id);
        return mTipsRepository.findById(id)
            .map(mTipsMapper::toDto);
    }

    /**
     * Delete the mTips by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTips : {}", id);
        mTipsRepository.deleteById(id);
    }
}
