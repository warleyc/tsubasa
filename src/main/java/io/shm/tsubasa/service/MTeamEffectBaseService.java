package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTeamEffectBase;
import io.shm.tsubasa.repository.MTeamEffectBaseRepository;
import io.shm.tsubasa.service.dto.MTeamEffectBaseDTO;
import io.shm.tsubasa.service.mapper.MTeamEffectBaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTeamEffectBase}.
 */
@Service
@Transactional
public class MTeamEffectBaseService {

    private final Logger log = LoggerFactory.getLogger(MTeamEffectBaseService.class);

    private final MTeamEffectBaseRepository mTeamEffectBaseRepository;

    private final MTeamEffectBaseMapper mTeamEffectBaseMapper;

    public MTeamEffectBaseService(MTeamEffectBaseRepository mTeamEffectBaseRepository, MTeamEffectBaseMapper mTeamEffectBaseMapper) {
        this.mTeamEffectBaseRepository = mTeamEffectBaseRepository;
        this.mTeamEffectBaseMapper = mTeamEffectBaseMapper;
    }

    /**
     * Save a mTeamEffectBase.
     *
     * @param mTeamEffectBaseDTO the entity to save.
     * @return the persisted entity.
     */
    public MTeamEffectBaseDTO save(MTeamEffectBaseDTO mTeamEffectBaseDTO) {
        log.debug("Request to save MTeamEffectBase : {}", mTeamEffectBaseDTO);
        MTeamEffectBase mTeamEffectBase = mTeamEffectBaseMapper.toEntity(mTeamEffectBaseDTO);
        mTeamEffectBase = mTeamEffectBaseRepository.save(mTeamEffectBase);
        return mTeamEffectBaseMapper.toDto(mTeamEffectBase);
    }

    /**
     * Get all the mTeamEffectBases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTeamEffectBaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTeamEffectBases");
        return mTeamEffectBaseRepository.findAll(pageable)
            .map(mTeamEffectBaseMapper::toDto);
    }


    /**
     * Get one mTeamEffectBase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTeamEffectBaseDTO> findOne(Long id) {
        log.debug("Request to get MTeamEffectBase : {}", id);
        return mTeamEffectBaseRepository.findById(id)
            .map(mTeamEffectBaseMapper::toDto);
    }

    /**
     * Delete the mTeamEffectBase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTeamEffectBase : {}", id);
        mTeamEffectBaseRepository.deleteById(id);
    }
}
