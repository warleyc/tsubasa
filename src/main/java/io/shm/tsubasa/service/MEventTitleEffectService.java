package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MEventTitleEffect;
import io.shm.tsubasa.repository.MEventTitleEffectRepository;
import io.shm.tsubasa.service.dto.MEventTitleEffectDTO;
import io.shm.tsubasa.service.mapper.MEventTitleEffectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MEventTitleEffect}.
 */
@Service
@Transactional
public class MEventTitleEffectService {

    private final Logger log = LoggerFactory.getLogger(MEventTitleEffectService.class);

    private final MEventTitleEffectRepository mEventTitleEffectRepository;

    private final MEventTitleEffectMapper mEventTitleEffectMapper;

    public MEventTitleEffectService(MEventTitleEffectRepository mEventTitleEffectRepository, MEventTitleEffectMapper mEventTitleEffectMapper) {
        this.mEventTitleEffectRepository = mEventTitleEffectRepository;
        this.mEventTitleEffectMapper = mEventTitleEffectMapper;
    }

    /**
     * Save a mEventTitleEffect.
     *
     * @param mEventTitleEffectDTO the entity to save.
     * @return the persisted entity.
     */
    public MEventTitleEffectDTO save(MEventTitleEffectDTO mEventTitleEffectDTO) {
        log.debug("Request to save MEventTitleEffect : {}", mEventTitleEffectDTO);
        MEventTitleEffect mEventTitleEffect = mEventTitleEffectMapper.toEntity(mEventTitleEffectDTO);
        mEventTitleEffect = mEventTitleEffectRepository.save(mEventTitleEffect);
        return mEventTitleEffectMapper.toDto(mEventTitleEffect);
    }

    /**
     * Get all the mEventTitleEffects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MEventTitleEffectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MEventTitleEffects");
        return mEventTitleEffectRepository.findAll(pageable)
            .map(mEventTitleEffectMapper::toDto);
    }


    /**
     * Get one mEventTitleEffect by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MEventTitleEffectDTO> findOne(Long id) {
        log.debug("Request to get MEventTitleEffect : {}", id);
        return mEventTitleEffectRepository.findById(id)
            .map(mEventTitleEffectMapper::toDto);
    }

    /**
     * Delete the mEventTitleEffect by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MEventTitleEffect : {}", id);
        mEventTitleEffectRepository.deleteById(id);
    }
}
