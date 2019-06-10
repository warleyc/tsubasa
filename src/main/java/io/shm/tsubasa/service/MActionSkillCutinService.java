package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MActionSkillCutin;
import io.shm.tsubasa.repository.MActionSkillCutinRepository;
import io.shm.tsubasa.service.dto.MActionSkillCutinDTO;
import io.shm.tsubasa.service.mapper.MActionSkillCutinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MActionSkillCutin}.
 */
@Service
@Transactional
public class MActionSkillCutinService {

    private final Logger log = LoggerFactory.getLogger(MActionSkillCutinService.class);

    private final MActionSkillCutinRepository mActionSkillCutinRepository;

    private final MActionSkillCutinMapper mActionSkillCutinMapper;

    public MActionSkillCutinService(MActionSkillCutinRepository mActionSkillCutinRepository, MActionSkillCutinMapper mActionSkillCutinMapper) {
        this.mActionSkillCutinRepository = mActionSkillCutinRepository;
        this.mActionSkillCutinMapper = mActionSkillCutinMapper;
    }

    /**
     * Save a mActionSkillCutin.
     *
     * @param mActionSkillCutinDTO the entity to save.
     * @return the persisted entity.
     */
    public MActionSkillCutinDTO save(MActionSkillCutinDTO mActionSkillCutinDTO) {
        log.debug("Request to save MActionSkillCutin : {}", mActionSkillCutinDTO);
        MActionSkillCutin mActionSkillCutin = mActionSkillCutinMapper.toEntity(mActionSkillCutinDTO);
        mActionSkillCutin = mActionSkillCutinRepository.save(mActionSkillCutin);
        return mActionSkillCutinMapper.toDto(mActionSkillCutin);
    }

    /**
     * Get all the mActionSkillCutins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionSkillCutinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MActionSkillCutins");
        return mActionSkillCutinRepository.findAll(pageable)
            .map(mActionSkillCutinMapper::toDto);
    }


    /**
     * Get one mActionSkillCutin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MActionSkillCutinDTO> findOne(Long id) {
        log.debug("Request to get MActionSkillCutin : {}", id);
        return mActionSkillCutinRepository.findById(id)
            .map(mActionSkillCutinMapper::toDto);
    }

    /**
     * Delete the mActionSkillCutin by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MActionSkillCutin : {}", id);
        mActionSkillCutinRepository.deleteById(id);
    }
}
