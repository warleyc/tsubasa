package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTargetTriggerEffectGroup;
import io.shm.tsubasa.repository.MTargetTriggerEffectGroupRepository;
import io.shm.tsubasa.service.dto.MTargetTriggerEffectGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetTriggerEffectGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTargetTriggerEffectGroup}.
 */
@Service
@Transactional
public class MTargetTriggerEffectGroupService {

    private final Logger log = LoggerFactory.getLogger(MTargetTriggerEffectGroupService.class);

    private final MTargetTriggerEffectGroupRepository mTargetTriggerEffectGroupRepository;

    private final MTargetTriggerEffectGroupMapper mTargetTriggerEffectGroupMapper;

    public MTargetTriggerEffectGroupService(MTargetTriggerEffectGroupRepository mTargetTriggerEffectGroupRepository, MTargetTriggerEffectGroupMapper mTargetTriggerEffectGroupMapper) {
        this.mTargetTriggerEffectGroupRepository = mTargetTriggerEffectGroupRepository;
        this.mTargetTriggerEffectGroupMapper = mTargetTriggerEffectGroupMapper;
    }

    /**
     * Save a mTargetTriggerEffectGroup.
     *
     * @param mTargetTriggerEffectGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MTargetTriggerEffectGroupDTO save(MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO) {
        log.debug("Request to save MTargetTriggerEffectGroup : {}", mTargetTriggerEffectGroupDTO);
        MTargetTriggerEffectGroup mTargetTriggerEffectGroup = mTargetTriggerEffectGroupMapper.toEntity(mTargetTriggerEffectGroupDTO);
        mTargetTriggerEffectGroup = mTargetTriggerEffectGroupRepository.save(mTargetTriggerEffectGroup);
        return mTargetTriggerEffectGroupMapper.toDto(mTargetTriggerEffectGroup);
    }

    /**
     * Get all the mTargetTriggerEffectGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetTriggerEffectGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTargetTriggerEffectGroups");
        return mTargetTriggerEffectGroupRepository.findAll(pageable)
            .map(mTargetTriggerEffectGroupMapper::toDto);
    }


    /**
     * Get one mTargetTriggerEffectGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTargetTriggerEffectGroupDTO> findOne(Long id) {
        log.debug("Request to get MTargetTriggerEffectGroup : {}", id);
        return mTargetTriggerEffectGroupRepository.findById(id)
            .map(mTargetTriggerEffectGroupMapper::toDto);
    }

    /**
     * Delete the mTargetTriggerEffectGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTargetTriggerEffectGroup : {}", id);
        mTargetTriggerEffectGroupRepository.deleteById(id);
    }
}
