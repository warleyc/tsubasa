package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCutSeqGroup;
import io.shm.tsubasa.repository.MCutSeqGroupRepository;
import io.shm.tsubasa.service.dto.MCutSeqGroupDTO;
import io.shm.tsubasa.service.mapper.MCutSeqGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCutSeqGroup}.
 */
@Service
@Transactional
public class MCutSeqGroupService {

    private final Logger log = LoggerFactory.getLogger(MCutSeqGroupService.class);

    private final MCutSeqGroupRepository mCutSeqGroupRepository;

    private final MCutSeqGroupMapper mCutSeqGroupMapper;

    public MCutSeqGroupService(MCutSeqGroupRepository mCutSeqGroupRepository, MCutSeqGroupMapper mCutSeqGroupMapper) {
        this.mCutSeqGroupRepository = mCutSeqGroupRepository;
        this.mCutSeqGroupMapper = mCutSeqGroupMapper;
    }

    /**
     * Save a mCutSeqGroup.
     *
     * @param mCutSeqGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MCutSeqGroupDTO save(MCutSeqGroupDTO mCutSeqGroupDTO) {
        log.debug("Request to save MCutSeqGroup : {}", mCutSeqGroupDTO);
        MCutSeqGroup mCutSeqGroup = mCutSeqGroupMapper.toEntity(mCutSeqGroupDTO);
        mCutSeqGroup = mCutSeqGroupRepository.save(mCutSeqGroup);
        return mCutSeqGroupMapper.toDto(mCutSeqGroup);
    }

    /**
     * Get all the mCutSeqGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCutSeqGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCutSeqGroups");
        return mCutSeqGroupRepository.findAll(pageable)
            .map(mCutSeqGroupMapper::toDto);
    }


    /**
     * Get one mCutSeqGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCutSeqGroupDTO> findOne(Long id) {
        log.debug("Request to get MCutSeqGroup : {}", id);
        return mCutSeqGroupRepository.findById(id)
            .map(mCutSeqGroupMapper::toDto);
    }

    /**
     * Delete the mCutSeqGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCutSeqGroup : {}", id);
        mCutSeqGroupRepository.deleteById(id);
    }
}
