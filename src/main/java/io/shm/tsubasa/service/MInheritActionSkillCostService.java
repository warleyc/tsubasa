package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MInheritActionSkillCost;
import io.shm.tsubasa.repository.MInheritActionSkillCostRepository;
import io.shm.tsubasa.service.dto.MInheritActionSkillCostDTO;
import io.shm.tsubasa.service.mapper.MInheritActionSkillCostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MInheritActionSkillCost}.
 */
@Service
@Transactional
public class MInheritActionSkillCostService {

    private final Logger log = LoggerFactory.getLogger(MInheritActionSkillCostService.class);

    private final MInheritActionSkillCostRepository mInheritActionSkillCostRepository;

    private final MInheritActionSkillCostMapper mInheritActionSkillCostMapper;

    public MInheritActionSkillCostService(MInheritActionSkillCostRepository mInheritActionSkillCostRepository, MInheritActionSkillCostMapper mInheritActionSkillCostMapper) {
        this.mInheritActionSkillCostRepository = mInheritActionSkillCostRepository;
        this.mInheritActionSkillCostMapper = mInheritActionSkillCostMapper;
    }

    /**
     * Save a mInheritActionSkillCost.
     *
     * @param mInheritActionSkillCostDTO the entity to save.
     * @return the persisted entity.
     */
    public MInheritActionSkillCostDTO save(MInheritActionSkillCostDTO mInheritActionSkillCostDTO) {
        log.debug("Request to save MInheritActionSkillCost : {}", mInheritActionSkillCostDTO);
        MInheritActionSkillCost mInheritActionSkillCost = mInheritActionSkillCostMapper.toEntity(mInheritActionSkillCostDTO);
        mInheritActionSkillCost = mInheritActionSkillCostRepository.save(mInheritActionSkillCost);
        return mInheritActionSkillCostMapper.toDto(mInheritActionSkillCost);
    }

    /**
     * Get all the mInheritActionSkillCosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MInheritActionSkillCostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MInheritActionSkillCosts");
        return mInheritActionSkillCostRepository.findAll(pageable)
            .map(mInheritActionSkillCostMapper::toDto);
    }


    /**
     * Get one mInheritActionSkillCost by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MInheritActionSkillCostDTO> findOne(Long id) {
        log.debug("Request to get MInheritActionSkillCost : {}", id);
        return mInheritActionSkillCostRepository.findById(id)
            .map(mInheritActionSkillCostMapper::toDto);
    }

    /**
     * Delete the mInheritActionSkillCost by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MInheritActionSkillCost : {}", id);
        mInheritActionSkillCostRepository.deleteById(id);
    }
}
