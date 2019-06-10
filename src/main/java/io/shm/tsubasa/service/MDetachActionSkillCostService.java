package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDetachActionSkillCost;
import io.shm.tsubasa.repository.MDetachActionSkillCostRepository;
import io.shm.tsubasa.service.dto.MDetachActionSkillCostDTO;
import io.shm.tsubasa.service.mapper.MDetachActionSkillCostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDetachActionSkillCost}.
 */
@Service
@Transactional
public class MDetachActionSkillCostService {

    private final Logger log = LoggerFactory.getLogger(MDetachActionSkillCostService.class);

    private final MDetachActionSkillCostRepository mDetachActionSkillCostRepository;

    private final MDetachActionSkillCostMapper mDetachActionSkillCostMapper;

    public MDetachActionSkillCostService(MDetachActionSkillCostRepository mDetachActionSkillCostRepository, MDetachActionSkillCostMapper mDetachActionSkillCostMapper) {
        this.mDetachActionSkillCostRepository = mDetachActionSkillCostRepository;
        this.mDetachActionSkillCostMapper = mDetachActionSkillCostMapper;
    }

    /**
     * Save a mDetachActionSkillCost.
     *
     * @param mDetachActionSkillCostDTO the entity to save.
     * @return the persisted entity.
     */
    public MDetachActionSkillCostDTO save(MDetachActionSkillCostDTO mDetachActionSkillCostDTO) {
        log.debug("Request to save MDetachActionSkillCost : {}", mDetachActionSkillCostDTO);
        MDetachActionSkillCost mDetachActionSkillCost = mDetachActionSkillCostMapper.toEntity(mDetachActionSkillCostDTO);
        mDetachActionSkillCost = mDetachActionSkillCostRepository.save(mDetachActionSkillCost);
        return mDetachActionSkillCostMapper.toDto(mDetachActionSkillCost);
    }

    /**
     * Get all the mDetachActionSkillCosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDetachActionSkillCostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDetachActionSkillCosts");
        return mDetachActionSkillCostRepository.findAll(pageable)
            .map(mDetachActionSkillCostMapper::toDto);
    }


    /**
     * Get one mDetachActionSkillCost by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDetachActionSkillCostDTO> findOne(Long id) {
        log.debug("Request to get MDetachActionSkillCost : {}", id);
        return mDetachActionSkillCostRepository.findById(id)
            .map(mDetachActionSkillCostMapper::toDto);
    }

    /**
     * Delete the mDetachActionSkillCost by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDetachActionSkillCost : {}", id);
        mDetachActionSkillCostRepository.deleteById(id);
    }
}
