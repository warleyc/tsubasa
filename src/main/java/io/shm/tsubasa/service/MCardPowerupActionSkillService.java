package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCardPowerupActionSkill;
import io.shm.tsubasa.repository.MCardPowerupActionSkillRepository;
import io.shm.tsubasa.service.dto.MCardPowerupActionSkillDTO;
import io.shm.tsubasa.service.mapper.MCardPowerupActionSkillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCardPowerupActionSkill}.
 */
@Service
@Transactional
public class MCardPowerupActionSkillService {

    private final Logger log = LoggerFactory.getLogger(MCardPowerupActionSkillService.class);

    private final MCardPowerupActionSkillRepository mCardPowerupActionSkillRepository;

    private final MCardPowerupActionSkillMapper mCardPowerupActionSkillMapper;

    public MCardPowerupActionSkillService(MCardPowerupActionSkillRepository mCardPowerupActionSkillRepository, MCardPowerupActionSkillMapper mCardPowerupActionSkillMapper) {
        this.mCardPowerupActionSkillRepository = mCardPowerupActionSkillRepository;
        this.mCardPowerupActionSkillMapper = mCardPowerupActionSkillMapper;
    }

    /**
     * Save a mCardPowerupActionSkill.
     *
     * @param mCardPowerupActionSkillDTO the entity to save.
     * @return the persisted entity.
     */
    public MCardPowerupActionSkillDTO save(MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO) {
        log.debug("Request to save MCardPowerupActionSkill : {}", mCardPowerupActionSkillDTO);
        MCardPowerupActionSkill mCardPowerupActionSkill = mCardPowerupActionSkillMapper.toEntity(mCardPowerupActionSkillDTO);
        mCardPowerupActionSkill = mCardPowerupActionSkillRepository.save(mCardPowerupActionSkill);
        return mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);
    }

    /**
     * Get all the mCardPowerupActionSkills.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardPowerupActionSkillDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCardPowerupActionSkills");
        return mCardPowerupActionSkillRepository.findAll(pageable)
            .map(mCardPowerupActionSkillMapper::toDto);
    }


    /**
     * Get one mCardPowerupActionSkill by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCardPowerupActionSkillDTO> findOne(Long id) {
        log.debug("Request to get MCardPowerupActionSkill : {}", id);
        return mCardPowerupActionSkillRepository.findById(id)
            .map(mCardPowerupActionSkillMapper::toDto);
    }

    /**
     * Delete the mCardPowerupActionSkill by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCardPowerupActionSkill : {}", id);
        mCardPowerupActionSkillRepository.deleteById(id);
    }
}
