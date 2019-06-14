package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLoginBonusIncentive;
import io.shm.tsubasa.repository.MLoginBonusIncentiveRepository;
import io.shm.tsubasa.service.dto.MLoginBonusIncentiveDTO;
import io.shm.tsubasa.service.mapper.MLoginBonusIncentiveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLoginBonusIncentive}.
 */
@Service
@Transactional
public class MLoginBonusIncentiveService {

    private final Logger log = LoggerFactory.getLogger(MLoginBonusIncentiveService.class);

    private final MLoginBonusIncentiveRepository mLoginBonusIncentiveRepository;

    private final MLoginBonusIncentiveMapper mLoginBonusIncentiveMapper;

    public MLoginBonusIncentiveService(MLoginBonusIncentiveRepository mLoginBonusIncentiveRepository, MLoginBonusIncentiveMapper mLoginBonusIncentiveMapper) {
        this.mLoginBonusIncentiveRepository = mLoginBonusIncentiveRepository;
        this.mLoginBonusIncentiveMapper = mLoginBonusIncentiveMapper;
    }

    /**
     * Save a mLoginBonusIncentive.
     *
     * @param mLoginBonusIncentiveDTO the entity to save.
     * @return the persisted entity.
     */
    public MLoginBonusIncentiveDTO save(MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO) {
        log.debug("Request to save MLoginBonusIncentive : {}", mLoginBonusIncentiveDTO);
        MLoginBonusIncentive mLoginBonusIncentive = mLoginBonusIncentiveMapper.toEntity(mLoginBonusIncentiveDTO);
        mLoginBonusIncentive = mLoginBonusIncentiveRepository.save(mLoginBonusIncentive);
        return mLoginBonusIncentiveMapper.toDto(mLoginBonusIncentive);
    }

    /**
     * Get all the mLoginBonusIncentives.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLoginBonusIncentiveDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLoginBonusIncentives");
        return mLoginBonusIncentiveRepository.findAll(pageable)
            .map(mLoginBonusIncentiveMapper::toDto);
    }


    /**
     * Get one mLoginBonusIncentive by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLoginBonusIncentiveDTO> findOne(Long id) {
        log.debug("Request to get MLoginBonusIncentive : {}", id);
        return mLoginBonusIncentiveRepository.findById(id)
            .map(mLoginBonusIncentiveMapper::toDto);
    }

    /**
     * Delete the mLoginBonusIncentive by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLoginBonusIncentive : {}", id);
        mLoginBonusIncentiveRepository.deleteById(id);
    }
}
