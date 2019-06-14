package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPvpPlayerStamp;
import io.shm.tsubasa.repository.MPvpPlayerStampRepository;
import io.shm.tsubasa.service.dto.MPvpPlayerStampDTO;
import io.shm.tsubasa.service.mapper.MPvpPlayerStampMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPvpPlayerStamp}.
 */
@Service
@Transactional
public class MPvpPlayerStampService {

    private final Logger log = LoggerFactory.getLogger(MPvpPlayerStampService.class);

    private final MPvpPlayerStampRepository mPvpPlayerStampRepository;

    private final MPvpPlayerStampMapper mPvpPlayerStampMapper;

    public MPvpPlayerStampService(MPvpPlayerStampRepository mPvpPlayerStampRepository, MPvpPlayerStampMapper mPvpPlayerStampMapper) {
        this.mPvpPlayerStampRepository = mPvpPlayerStampRepository;
        this.mPvpPlayerStampMapper = mPvpPlayerStampMapper;
    }

    /**
     * Save a mPvpPlayerStamp.
     *
     * @param mPvpPlayerStampDTO the entity to save.
     * @return the persisted entity.
     */
    public MPvpPlayerStampDTO save(MPvpPlayerStampDTO mPvpPlayerStampDTO) {
        log.debug("Request to save MPvpPlayerStamp : {}", mPvpPlayerStampDTO);
        MPvpPlayerStamp mPvpPlayerStamp = mPvpPlayerStampMapper.toEntity(mPvpPlayerStampDTO);
        mPvpPlayerStamp = mPvpPlayerStampRepository.save(mPvpPlayerStamp);
        return mPvpPlayerStampMapper.toDto(mPvpPlayerStamp);
    }

    /**
     * Get all the mPvpPlayerStamps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpPlayerStampDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPvpPlayerStamps");
        return mPvpPlayerStampRepository.findAll(pageable)
            .map(mPvpPlayerStampMapper::toDto);
    }


    /**
     * Get one mPvpPlayerStamp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPvpPlayerStampDTO> findOne(Long id) {
        log.debug("Request to get MPvpPlayerStamp : {}", id);
        return mPvpPlayerStampRepository.findById(id)
            .map(mPvpPlayerStampMapper::toDto);
    }

    /**
     * Delete the mPvpPlayerStamp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPvpPlayerStamp : {}", id);
        mPvpPlayerStampRepository.deleteById(id);
    }
}
