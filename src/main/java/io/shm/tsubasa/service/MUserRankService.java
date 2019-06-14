package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MUserRank;
import io.shm.tsubasa.repository.MUserRankRepository;
import io.shm.tsubasa.service.dto.MUserRankDTO;
import io.shm.tsubasa.service.mapper.MUserRankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MUserRank}.
 */
@Service
@Transactional
public class MUserRankService {

    private final Logger log = LoggerFactory.getLogger(MUserRankService.class);

    private final MUserRankRepository mUserRankRepository;

    private final MUserRankMapper mUserRankMapper;

    public MUserRankService(MUserRankRepository mUserRankRepository, MUserRankMapper mUserRankMapper) {
        this.mUserRankRepository = mUserRankRepository;
        this.mUserRankMapper = mUserRankMapper;
    }

    /**
     * Save a mUserRank.
     *
     * @param mUserRankDTO the entity to save.
     * @return the persisted entity.
     */
    public MUserRankDTO save(MUserRankDTO mUserRankDTO) {
        log.debug("Request to save MUserRank : {}", mUserRankDTO);
        MUserRank mUserRank = mUserRankMapper.toEntity(mUserRankDTO);
        mUserRank = mUserRankRepository.save(mUserRank);
        return mUserRankMapper.toDto(mUserRank);
    }

    /**
     * Get all the mUserRanks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MUserRankDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MUserRanks");
        return mUserRankRepository.findAll(pageable)
            .map(mUserRankMapper::toDto);
    }


    /**
     * Get one mUserRank by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MUserRankDTO> findOne(Long id) {
        log.debug("Request to get MUserRank : {}", id);
        return mUserRankRepository.findById(id)
            .map(mUserRankMapper::toDto);
    }

    /**
     * Delete the mUserRank by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MUserRank : {}", id);
        mUserRankRepository.deleteById(id);
    }
}
