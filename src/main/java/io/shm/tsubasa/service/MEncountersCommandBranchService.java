package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MEncountersCommandBranch;
import io.shm.tsubasa.repository.MEncountersCommandBranchRepository;
import io.shm.tsubasa.service.dto.MEncountersCommandBranchDTO;
import io.shm.tsubasa.service.mapper.MEncountersCommandBranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MEncountersCommandBranch}.
 */
@Service
@Transactional
public class MEncountersCommandBranchService {

    private final Logger log = LoggerFactory.getLogger(MEncountersCommandBranchService.class);

    private final MEncountersCommandBranchRepository mEncountersCommandBranchRepository;

    private final MEncountersCommandBranchMapper mEncountersCommandBranchMapper;

    public MEncountersCommandBranchService(MEncountersCommandBranchRepository mEncountersCommandBranchRepository, MEncountersCommandBranchMapper mEncountersCommandBranchMapper) {
        this.mEncountersCommandBranchRepository = mEncountersCommandBranchRepository;
        this.mEncountersCommandBranchMapper = mEncountersCommandBranchMapper;
    }

    /**
     * Save a mEncountersCommandBranch.
     *
     * @param mEncountersCommandBranchDTO the entity to save.
     * @return the persisted entity.
     */
    public MEncountersCommandBranchDTO save(MEncountersCommandBranchDTO mEncountersCommandBranchDTO) {
        log.debug("Request to save MEncountersCommandBranch : {}", mEncountersCommandBranchDTO);
        MEncountersCommandBranch mEncountersCommandBranch = mEncountersCommandBranchMapper.toEntity(mEncountersCommandBranchDTO);
        mEncountersCommandBranch = mEncountersCommandBranchRepository.save(mEncountersCommandBranch);
        return mEncountersCommandBranchMapper.toDto(mEncountersCommandBranch);
    }

    /**
     * Get all the mEncountersCommandBranches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MEncountersCommandBranchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MEncountersCommandBranches");
        return mEncountersCommandBranchRepository.findAll(pageable)
            .map(mEncountersCommandBranchMapper::toDto);
    }


    /**
     * Get one mEncountersCommandBranch by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MEncountersCommandBranchDTO> findOne(Long id) {
        log.debug("Request to get MEncountersCommandBranch : {}", id);
        return mEncountersCommandBranchRepository.findById(id)
            .map(mEncountersCommandBranchMapper::toDto);
    }

    /**
     * Delete the mEncountersCommandBranch by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MEncountersCommandBranch : {}", id);
        mEncountersCommandBranchRepository.deleteById(id);
    }
}
