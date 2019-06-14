package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuildEffect;
import io.shm.tsubasa.repository.MGuildEffectRepository;
import io.shm.tsubasa.service.dto.MGuildEffectDTO;
import io.shm.tsubasa.service.mapper.MGuildEffectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuildEffect}.
 */
@Service
@Transactional
public class MGuildEffectService {

    private final Logger log = LoggerFactory.getLogger(MGuildEffectService.class);

    private final MGuildEffectRepository mGuildEffectRepository;

    private final MGuildEffectMapper mGuildEffectMapper;

    public MGuildEffectService(MGuildEffectRepository mGuildEffectRepository, MGuildEffectMapper mGuildEffectMapper) {
        this.mGuildEffectRepository = mGuildEffectRepository;
        this.mGuildEffectMapper = mGuildEffectMapper;
    }

    /**
     * Save a mGuildEffect.
     *
     * @param mGuildEffectDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuildEffectDTO save(MGuildEffectDTO mGuildEffectDTO) {
        log.debug("Request to save MGuildEffect : {}", mGuildEffectDTO);
        MGuildEffect mGuildEffect = mGuildEffectMapper.toEntity(mGuildEffectDTO);
        mGuildEffect = mGuildEffectRepository.save(mGuildEffect);
        return mGuildEffectMapper.toDto(mGuildEffect);
    }

    /**
     * Get all the mGuildEffects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildEffectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuildEffects");
        return mGuildEffectRepository.findAll(pageable)
            .map(mGuildEffectMapper::toDto);
    }


    /**
     * Get one mGuildEffect by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuildEffectDTO> findOne(Long id) {
        log.debug("Request to get MGuildEffect : {}", id);
        return mGuildEffectRepository.findById(id)
            .map(mGuildEffectMapper::toDto);
    }

    /**
     * Delete the mGuildEffect by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuildEffect : {}", id);
        mGuildEffectRepository.deleteById(id);
    }
}
