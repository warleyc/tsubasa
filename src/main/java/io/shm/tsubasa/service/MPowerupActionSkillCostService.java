package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPowerupActionSkillCost;
import io.shm.tsubasa.repository.MPowerupActionSkillCostRepository;
import io.shm.tsubasa.service.dto.MPowerupActionSkillCostDTO;
import io.shm.tsubasa.service.mapper.MPowerupActionSkillCostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPowerupActionSkillCost}.
 */
@Service
@Transactional
public class MPowerupActionSkillCostService {

    private final Logger log = LoggerFactory.getLogger(MPowerupActionSkillCostService.class);

    private final MPowerupActionSkillCostRepository mPowerupActionSkillCostRepository;

    private final MPowerupActionSkillCostMapper mPowerupActionSkillCostMapper;

    public MPowerupActionSkillCostService(MPowerupActionSkillCostRepository mPowerupActionSkillCostRepository, MPowerupActionSkillCostMapper mPowerupActionSkillCostMapper) {
        this.mPowerupActionSkillCostRepository = mPowerupActionSkillCostRepository;
        this.mPowerupActionSkillCostMapper = mPowerupActionSkillCostMapper;
    }

    /**
     * Save a mPowerupActionSkillCost.
     *
     * @param mPowerupActionSkillCostDTO the entity to save.
     * @return the persisted entity.
     */
    public MPowerupActionSkillCostDTO save(MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO) {
        log.debug("Request to save MPowerupActionSkillCost : {}", mPowerupActionSkillCostDTO);
        MPowerupActionSkillCost mPowerupActionSkillCost = mPowerupActionSkillCostMapper.toEntity(mPowerupActionSkillCostDTO);
        mPowerupActionSkillCost = mPowerupActionSkillCostRepository.save(mPowerupActionSkillCost);
        return mPowerupActionSkillCostMapper.toDto(mPowerupActionSkillCost);
    }

    /**
     * Get all the mPowerupActionSkillCosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPowerupActionSkillCostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPowerupActionSkillCosts");
        return mPowerupActionSkillCostRepository.findAll(pageable)
            .map(mPowerupActionSkillCostMapper::toDto);
    }


    /**
     * Get one mPowerupActionSkillCost by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPowerupActionSkillCostDTO> findOne(Long id) {
        log.debug("Request to get MPowerupActionSkillCost : {}", id);
        return mPowerupActionSkillCostRepository.findById(id)
            .map(mPowerupActionSkillCostMapper::toDto);
    }

    /**
     * Delete the mPowerupActionSkillCost by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPowerupActionSkillCost : {}", id);
        mPowerupActionSkillCostRepository.deleteById(id);
    }
}
