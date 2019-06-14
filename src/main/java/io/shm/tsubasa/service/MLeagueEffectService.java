package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLeagueEffect;
import io.shm.tsubasa.repository.MLeagueEffectRepository;
import io.shm.tsubasa.service.dto.MLeagueEffectDTO;
import io.shm.tsubasa.service.mapper.MLeagueEffectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLeagueEffect}.
 */
@Service
@Transactional
public class MLeagueEffectService {

    private final Logger log = LoggerFactory.getLogger(MLeagueEffectService.class);

    private final MLeagueEffectRepository mLeagueEffectRepository;

    private final MLeagueEffectMapper mLeagueEffectMapper;

    public MLeagueEffectService(MLeagueEffectRepository mLeagueEffectRepository, MLeagueEffectMapper mLeagueEffectMapper) {
        this.mLeagueEffectRepository = mLeagueEffectRepository;
        this.mLeagueEffectMapper = mLeagueEffectMapper;
    }

    /**
     * Save a mLeagueEffect.
     *
     * @param mLeagueEffectDTO the entity to save.
     * @return the persisted entity.
     */
    public MLeagueEffectDTO save(MLeagueEffectDTO mLeagueEffectDTO) {
        log.debug("Request to save MLeagueEffect : {}", mLeagueEffectDTO);
        MLeagueEffect mLeagueEffect = mLeagueEffectMapper.toEntity(mLeagueEffectDTO);
        mLeagueEffect = mLeagueEffectRepository.save(mLeagueEffect);
        return mLeagueEffectMapper.toDto(mLeagueEffect);
    }

    /**
     * Get all the mLeagueEffects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueEffectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLeagueEffects");
        return mLeagueEffectRepository.findAll(pageable)
            .map(mLeagueEffectMapper::toDto);
    }


    /**
     * Get one mLeagueEffect by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLeagueEffectDTO> findOne(Long id) {
        log.debug("Request to get MLeagueEffect : {}", id);
        return mLeagueEffectRepository.findById(id)
            .map(mLeagueEffectMapper::toDto);
    }

    /**
     * Delete the mLeagueEffect by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLeagueEffect : {}", id);
        mLeagueEffectRepository.deleteById(id);
    }
}
