package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTriggerEffectBase;
import io.shm.tsubasa.repository.MTriggerEffectBaseRepository;
import io.shm.tsubasa.service.dto.MTriggerEffectBaseDTO;
import io.shm.tsubasa.service.mapper.MTriggerEffectBaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTriggerEffectBase}.
 */
@Service
@Transactional
public class MTriggerEffectBaseService {

    private final Logger log = LoggerFactory.getLogger(MTriggerEffectBaseService.class);

    private final MTriggerEffectBaseRepository mTriggerEffectBaseRepository;

    private final MTriggerEffectBaseMapper mTriggerEffectBaseMapper;

    public MTriggerEffectBaseService(MTriggerEffectBaseRepository mTriggerEffectBaseRepository, MTriggerEffectBaseMapper mTriggerEffectBaseMapper) {
        this.mTriggerEffectBaseRepository = mTriggerEffectBaseRepository;
        this.mTriggerEffectBaseMapper = mTriggerEffectBaseMapper;
    }

    /**
     * Save a mTriggerEffectBase.
     *
     * @param mTriggerEffectBaseDTO the entity to save.
     * @return the persisted entity.
     */
    public MTriggerEffectBaseDTO save(MTriggerEffectBaseDTO mTriggerEffectBaseDTO) {
        log.debug("Request to save MTriggerEffectBase : {}", mTriggerEffectBaseDTO);
        MTriggerEffectBase mTriggerEffectBase = mTriggerEffectBaseMapper.toEntity(mTriggerEffectBaseDTO);
        mTriggerEffectBase = mTriggerEffectBaseRepository.save(mTriggerEffectBase);
        return mTriggerEffectBaseMapper.toDto(mTriggerEffectBase);
    }

    /**
     * Get all the mTriggerEffectBases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTriggerEffectBaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTriggerEffectBases");
        return mTriggerEffectBaseRepository.findAll(pageable)
            .map(mTriggerEffectBaseMapper::toDto);
    }


    /**
     * Get one mTriggerEffectBase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTriggerEffectBaseDTO> findOne(Long id) {
        log.debug("Request to get MTriggerEffectBase : {}", id);
        return mTriggerEffectBaseRepository.findById(id)
            .map(mTriggerEffectBaseMapper::toDto);
    }

    /**
     * Delete the mTriggerEffectBase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTriggerEffectBase : {}", id);
        mTriggerEffectBaseRepository.deleteById(id);
    }
}
