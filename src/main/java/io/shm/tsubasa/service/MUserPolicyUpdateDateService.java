package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MUserPolicyUpdateDate;
import io.shm.tsubasa.repository.MUserPolicyUpdateDateRepository;
import io.shm.tsubasa.service.dto.MUserPolicyUpdateDateDTO;
import io.shm.tsubasa.service.mapper.MUserPolicyUpdateDateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MUserPolicyUpdateDate}.
 */
@Service
@Transactional
public class MUserPolicyUpdateDateService {

    private final Logger log = LoggerFactory.getLogger(MUserPolicyUpdateDateService.class);

    private final MUserPolicyUpdateDateRepository mUserPolicyUpdateDateRepository;

    private final MUserPolicyUpdateDateMapper mUserPolicyUpdateDateMapper;

    public MUserPolicyUpdateDateService(MUserPolicyUpdateDateRepository mUserPolicyUpdateDateRepository, MUserPolicyUpdateDateMapper mUserPolicyUpdateDateMapper) {
        this.mUserPolicyUpdateDateRepository = mUserPolicyUpdateDateRepository;
        this.mUserPolicyUpdateDateMapper = mUserPolicyUpdateDateMapper;
    }

    /**
     * Save a mUserPolicyUpdateDate.
     *
     * @param mUserPolicyUpdateDateDTO the entity to save.
     * @return the persisted entity.
     */
    public MUserPolicyUpdateDateDTO save(MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO) {
        log.debug("Request to save MUserPolicyUpdateDate : {}", mUserPolicyUpdateDateDTO);
        MUserPolicyUpdateDate mUserPolicyUpdateDate = mUserPolicyUpdateDateMapper.toEntity(mUserPolicyUpdateDateDTO);
        mUserPolicyUpdateDate = mUserPolicyUpdateDateRepository.save(mUserPolicyUpdateDate);
        return mUserPolicyUpdateDateMapper.toDto(mUserPolicyUpdateDate);
    }

    /**
     * Get all the mUserPolicyUpdateDates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MUserPolicyUpdateDateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MUserPolicyUpdateDates");
        return mUserPolicyUpdateDateRepository.findAll(pageable)
            .map(mUserPolicyUpdateDateMapper::toDto);
    }


    /**
     * Get one mUserPolicyUpdateDate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MUserPolicyUpdateDateDTO> findOne(Long id) {
        log.debug("Request to get MUserPolicyUpdateDate : {}", id);
        return mUserPolicyUpdateDateRepository.findById(id)
            .map(mUserPolicyUpdateDateMapper::toDto);
    }

    /**
     * Delete the mUserPolicyUpdateDate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MUserPolicyUpdateDate : {}", id);
        mUserPolicyUpdateDateRepository.deleteById(id);
    }
}
