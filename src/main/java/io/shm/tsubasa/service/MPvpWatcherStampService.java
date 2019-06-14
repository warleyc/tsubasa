package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPvpWatcherStamp;
import io.shm.tsubasa.repository.MPvpWatcherStampRepository;
import io.shm.tsubasa.service.dto.MPvpWatcherStampDTO;
import io.shm.tsubasa.service.mapper.MPvpWatcherStampMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPvpWatcherStamp}.
 */
@Service
@Transactional
public class MPvpWatcherStampService {

    private final Logger log = LoggerFactory.getLogger(MPvpWatcherStampService.class);

    private final MPvpWatcherStampRepository mPvpWatcherStampRepository;

    private final MPvpWatcherStampMapper mPvpWatcherStampMapper;

    public MPvpWatcherStampService(MPvpWatcherStampRepository mPvpWatcherStampRepository, MPvpWatcherStampMapper mPvpWatcherStampMapper) {
        this.mPvpWatcherStampRepository = mPvpWatcherStampRepository;
        this.mPvpWatcherStampMapper = mPvpWatcherStampMapper;
    }

    /**
     * Save a mPvpWatcherStamp.
     *
     * @param mPvpWatcherStampDTO the entity to save.
     * @return the persisted entity.
     */
    public MPvpWatcherStampDTO save(MPvpWatcherStampDTO mPvpWatcherStampDTO) {
        log.debug("Request to save MPvpWatcherStamp : {}", mPvpWatcherStampDTO);
        MPvpWatcherStamp mPvpWatcherStamp = mPvpWatcherStampMapper.toEntity(mPvpWatcherStampDTO);
        mPvpWatcherStamp = mPvpWatcherStampRepository.save(mPvpWatcherStamp);
        return mPvpWatcherStampMapper.toDto(mPvpWatcherStamp);
    }

    /**
     * Get all the mPvpWatcherStamps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpWatcherStampDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPvpWatcherStamps");
        return mPvpWatcherStampRepository.findAll(pageable)
            .map(mPvpWatcherStampMapper::toDto);
    }


    /**
     * Get one mPvpWatcherStamp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPvpWatcherStampDTO> findOne(Long id) {
        log.debug("Request to get MPvpWatcherStamp : {}", id);
        return mPvpWatcherStampRepository.findById(id)
            .map(mPvpWatcherStampMapper::toDto);
    }

    /**
     * Delete the mPvpWatcherStamp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPvpWatcherStamp : {}", id);
        mPvpWatcherStampRepository.deleteById(id);
    }
}
