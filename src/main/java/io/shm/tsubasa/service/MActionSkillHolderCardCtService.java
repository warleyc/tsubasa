package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MActionSkillHolderCardCt;
import io.shm.tsubasa.repository.MActionSkillHolderCardCtRepository;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardCtDTO;
import io.shm.tsubasa.service.mapper.MActionSkillHolderCardCtMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MActionSkillHolderCardCt}.
 */
@Service
@Transactional
public class MActionSkillHolderCardCtService {

    private final Logger log = LoggerFactory.getLogger(MActionSkillHolderCardCtService.class);

    private final MActionSkillHolderCardCtRepository mActionSkillHolderCardCtRepository;

    private final MActionSkillHolderCardCtMapper mActionSkillHolderCardCtMapper;

    public MActionSkillHolderCardCtService(MActionSkillHolderCardCtRepository mActionSkillHolderCardCtRepository, MActionSkillHolderCardCtMapper mActionSkillHolderCardCtMapper) {
        this.mActionSkillHolderCardCtRepository = mActionSkillHolderCardCtRepository;
        this.mActionSkillHolderCardCtMapper = mActionSkillHolderCardCtMapper;
    }

    /**
     * Save a mActionSkillHolderCardCt.
     *
     * @param mActionSkillHolderCardCtDTO the entity to save.
     * @return the persisted entity.
     */
    public MActionSkillHolderCardCtDTO save(MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO) {
        log.debug("Request to save MActionSkillHolderCardCt : {}", mActionSkillHolderCardCtDTO);
        MActionSkillHolderCardCt mActionSkillHolderCardCt = mActionSkillHolderCardCtMapper.toEntity(mActionSkillHolderCardCtDTO);
        mActionSkillHolderCardCt = mActionSkillHolderCardCtRepository.save(mActionSkillHolderCardCt);
        return mActionSkillHolderCardCtMapper.toDto(mActionSkillHolderCardCt);
    }

    /**
     * Get all the mActionSkillHolderCardCts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionSkillHolderCardCtDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MActionSkillHolderCardCts");
        return mActionSkillHolderCardCtRepository.findAll(pageable)
            .map(mActionSkillHolderCardCtMapper::toDto);
    }


    /**
     * Get one mActionSkillHolderCardCt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MActionSkillHolderCardCtDTO> findOne(Long id) {
        log.debug("Request to get MActionSkillHolderCardCt : {}", id);
        return mActionSkillHolderCardCtRepository.findById(id)
            .map(mActionSkillHolderCardCtMapper::toDto);
    }

    /**
     * Delete the mActionSkillHolderCardCt by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MActionSkillHolderCardCt : {}", id);
        mActionSkillHolderCardCtRepository.deleteById(id);
    }
}
