package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLuckRateGroup;
import io.shm.tsubasa.repository.MLuckRateGroupRepository;
import io.shm.tsubasa.service.dto.MLuckRateGroupDTO;
import io.shm.tsubasa.service.mapper.MLuckRateGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLuckRateGroup}.
 */
@Service
@Transactional
public class MLuckRateGroupService {

    private final Logger log = LoggerFactory.getLogger(MLuckRateGroupService.class);

    private final MLuckRateGroupRepository mLuckRateGroupRepository;

    private final MLuckRateGroupMapper mLuckRateGroupMapper;

    public MLuckRateGroupService(MLuckRateGroupRepository mLuckRateGroupRepository, MLuckRateGroupMapper mLuckRateGroupMapper) {
        this.mLuckRateGroupRepository = mLuckRateGroupRepository;
        this.mLuckRateGroupMapper = mLuckRateGroupMapper;
    }

    /**
     * Save a mLuckRateGroup.
     *
     * @param mLuckRateGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MLuckRateGroupDTO save(MLuckRateGroupDTO mLuckRateGroupDTO) {
        log.debug("Request to save MLuckRateGroup : {}", mLuckRateGroupDTO);
        MLuckRateGroup mLuckRateGroup = mLuckRateGroupMapper.toEntity(mLuckRateGroupDTO);
        mLuckRateGroup = mLuckRateGroupRepository.save(mLuckRateGroup);
        return mLuckRateGroupMapper.toDto(mLuckRateGroup);
    }

    /**
     * Get all the mLuckRateGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLuckRateGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLuckRateGroups");
        return mLuckRateGroupRepository.findAll(pageable)
            .map(mLuckRateGroupMapper::toDto);
    }


    /**
     * Get one mLuckRateGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLuckRateGroupDTO> findOne(Long id) {
        log.debug("Request to get MLuckRateGroup : {}", id);
        return mLuckRateGroupRepository.findById(id)
            .map(mLuckRateGroupMapper::toDto);
    }

    /**
     * Delete the mLuckRateGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLuckRateGroup : {}", id);
        mLuckRateGroupRepository.deleteById(id);
    }
}
