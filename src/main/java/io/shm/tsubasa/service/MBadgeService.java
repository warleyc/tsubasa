package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MBadge;
import io.shm.tsubasa.repository.MBadgeRepository;
import io.shm.tsubasa.service.dto.MBadgeDTO;
import io.shm.tsubasa.service.mapper.MBadgeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MBadge}.
 */
@Service
@Transactional
public class MBadgeService {

    private final Logger log = LoggerFactory.getLogger(MBadgeService.class);

    private final MBadgeRepository mBadgeRepository;

    private final MBadgeMapper mBadgeMapper;

    public MBadgeService(MBadgeRepository mBadgeRepository, MBadgeMapper mBadgeMapper) {
        this.mBadgeRepository = mBadgeRepository;
        this.mBadgeMapper = mBadgeMapper;
    }

    /**
     * Save a mBadge.
     *
     * @param mBadgeDTO the entity to save.
     * @return the persisted entity.
     */
    public MBadgeDTO save(MBadgeDTO mBadgeDTO) {
        log.debug("Request to save MBadge : {}", mBadgeDTO);
        MBadge mBadge = mBadgeMapper.toEntity(mBadgeDTO);
        mBadge = mBadgeRepository.save(mBadge);
        return mBadgeMapper.toDto(mBadge);
    }

    /**
     * Get all the mBadges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MBadgeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MBadges");
        return mBadgeRepository.findAll(pageable)
            .map(mBadgeMapper::toDto);
    }


    /**
     * Get one mBadge by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MBadgeDTO> findOne(Long id) {
        log.debug("Request to get MBadge : {}", id);
        return mBadgeRepository.findById(id)
            .map(mBadgeMapper::toDto);
    }

    /**
     * Delete the mBadge by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MBadge : {}", id);
        mBadgeRepository.deleteById(id);
    }
}
