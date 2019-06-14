package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MShoot;
import io.shm.tsubasa.repository.MShootRepository;
import io.shm.tsubasa.service.dto.MShootDTO;
import io.shm.tsubasa.service.mapper.MShootMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MShoot}.
 */
@Service
@Transactional
public class MShootService {

    private final Logger log = LoggerFactory.getLogger(MShootService.class);

    private final MShootRepository mShootRepository;

    private final MShootMapper mShootMapper;

    public MShootService(MShootRepository mShootRepository, MShootMapper mShootMapper) {
        this.mShootRepository = mShootRepository;
        this.mShootMapper = mShootMapper;
    }

    /**
     * Save a mShoot.
     *
     * @param mShootDTO the entity to save.
     * @return the persisted entity.
     */
    public MShootDTO save(MShootDTO mShootDTO) {
        log.debug("Request to save MShoot : {}", mShootDTO);
        MShoot mShoot = mShootMapper.toEntity(mShootDTO);
        mShoot = mShootRepository.save(mShoot);
        return mShootMapper.toDto(mShoot);
    }

    /**
     * Get all the mShoots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MShootDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MShoots");
        return mShootRepository.findAll(pageable)
            .map(mShootMapper::toDto);
    }


    /**
     * Get one mShoot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MShootDTO> findOne(Long id) {
        log.debug("Request to get MShoot : {}", id);
        return mShootRepository.findById(id)
            .map(mShootMapper::toDto);
    }

    /**
     * Delete the mShoot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MShoot : {}", id);
        mShootRepository.deleteById(id);
    }
}
