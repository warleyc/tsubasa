package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTargetCharacterGroup;
import io.shm.tsubasa.repository.MTargetCharacterGroupRepository;
import io.shm.tsubasa.service.dto.MTargetCharacterGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetCharacterGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTargetCharacterGroup}.
 */
@Service
@Transactional
public class MTargetCharacterGroupService {

    private final Logger log = LoggerFactory.getLogger(MTargetCharacterGroupService.class);

    private final MTargetCharacterGroupRepository mTargetCharacterGroupRepository;

    private final MTargetCharacterGroupMapper mTargetCharacterGroupMapper;

    public MTargetCharacterGroupService(MTargetCharacterGroupRepository mTargetCharacterGroupRepository, MTargetCharacterGroupMapper mTargetCharacterGroupMapper) {
        this.mTargetCharacterGroupRepository = mTargetCharacterGroupRepository;
        this.mTargetCharacterGroupMapper = mTargetCharacterGroupMapper;
    }

    /**
     * Save a mTargetCharacterGroup.
     *
     * @param mTargetCharacterGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MTargetCharacterGroupDTO save(MTargetCharacterGroupDTO mTargetCharacterGroupDTO) {
        log.debug("Request to save MTargetCharacterGroup : {}", mTargetCharacterGroupDTO);
        MTargetCharacterGroup mTargetCharacterGroup = mTargetCharacterGroupMapper.toEntity(mTargetCharacterGroupDTO);
        mTargetCharacterGroup = mTargetCharacterGroupRepository.save(mTargetCharacterGroup);
        return mTargetCharacterGroupMapper.toDto(mTargetCharacterGroup);
    }

    /**
     * Get all the mTargetCharacterGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetCharacterGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTargetCharacterGroups");
        return mTargetCharacterGroupRepository.findAll(pageable)
            .map(mTargetCharacterGroupMapper::toDto);
    }


    /**
     * Get one mTargetCharacterGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTargetCharacterGroupDTO> findOne(Long id) {
        log.debug("Request to get MTargetCharacterGroup : {}", id);
        return mTargetCharacterGroupRepository.findById(id)
            .map(mTargetCharacterGroupMapper::toDto);
    }

    /**
     * Delete the mTargetCharacterGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTargetCharacterGroup : {}", id);
        mTargetCharacterGroupRepository.deleteById(id);
    }
}
