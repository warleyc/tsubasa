package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MActionSkillHolderCardContent;
import io.shm.tsubasa.repository.MActionSkillHolderCardContentRepository;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardContentDTO;
import io.shm.tsubasa.service.mapper.MActionSkillHolderCardContentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MActionSkillHolderCardContent}.
 */
@Service
@Transactional
public class MActionSkillHolderCardContentService {

    private final Logger log = LoggerFactory.getLogger(MActionSkillHolderCardContentService.class);

    private final MActionSkillHolderCardContentRepository mActionSkillHolderCardContentRepository;

    private final MActionSkillHolderCardContentMapper mActionSkillHolderCardContentMapper;

    public MActionSkillHolderCardContentService(MActionSkillHolderCardContentRepository mActionSkillHolderCardContentRepository, MActionSkillHolderCardContentMapper mActionSkillHolderCardContentMapper) {
        this.mActionSkillHolderCardContentRepository = mActionSkillHolderCardContentRepository;
        this.mActionSkillHolderCardContentMapper = mActionSkillHolderCardContentMapper;
    }

    /**
     * Save a mActionSkillHolderCardContent.
     *
     * @param mActionSkillHolderCardContentDTO the entity to save.
     * @return the persisted entity.
     */
    public MActionSkillHolderCardContentDTO save(MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO) {
        log.debug("Request to save MActionSkillHolderCardContent : {}", mActionSkillHolderCardContentDTO);
        MActionSkillHolderCardContent mActionSkillHolderCardContent = mActionSkillHolderCardContentMapper.toEntity(mActionSkillHolderCardContentDTO);
        mActionSkillHolderCardContent = mActionSkillHolderCardContentRepository.save(mActionSkillHolderCardContent);
        return mActionSkillHolderCardContentMapper.toDto(mActionSkillHolderCardContent);
    }

    /**
     * Get all the mActionSkillHolderCardContents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionSkillHolderCardContentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MActionSkillHolderCardContents");
        return mActionSkillHolderCardContentRepository.findAll(pageable)
            .map(mActionSkillHolderCardContentMapper::toDto);
    }


    /**
     * Get one mActionSkillHolderCardContent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MActionSkillHolderCardContentDTO> findOne(Long id) {
        log.debug("Request to get MActionSkillHolderCardContent : {}", id);
        return mActionSkillHolderCardContentRepository.findById(id)
            .map(mActionSkillHolderCardContentMapper::toDto);
    }

    /**
     * Delete the mActionSkillHolderCardContent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MActionSkillHolderCardContent : {}", id);
        mActionSkillHolderCardContentRepository.deleteById(id);
    }
}
