package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPvpWave;
import io.shm.tsubasa.repository.MPvpWaveRepository;
import io.shm.tsubasa.service.dto.MPvpWaveDTO;
import io.shm.tsubasa.service.mapper.MPvpWaveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPvpWave}.
 */
@Service
@Transactional
public class MPvpWaveService {

    private final Logger log = LoggerFactory.getLogger(MPvpWaveService.class);

    private final MPvpWaveRepository mPvpWaveRepository;

    private final MPvpWaveMapper mPvpWaveMapper;

    public MPvpWaveService(MPvpWaveRepository mPvpWaveRepository, MPvpWaveMapper mPvpWaveMapper) {
        this.mPvpWaveRepository = mPvpWaveRepository;
        this.mPvpWaveMapper = mPvpWaveMapper;
    }

    /**
     * Save a mPvpWave.
     *
     * @param mPvpWaveDTO the entity to save.
     * @return the persisted entity.
     */
    public MPvpWaveDTO save(MPvpWaveDTO mPvpWaveDTO) {
        log.debug("Request to save MPvpWave : {}", mPvpWaveDTO);
        MPvpWave mPvpWave = mPvpWaveMapper.toEntity(mPvpWaveDTO);
        mPvpWave = mPvpWaveRepository.save(mPvpWave);
        return mPvpWaveMapper.toDto(mPvpWave);
    }

    /**
     * Get all the mPvpWaves.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpWaveDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPvpWaves");
        return mPvpWaveRepository.findAll(pageable)
            .map(mPvpWaveMapper::toDto);
    }


    /**
     * Get one mPvpWave by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPvpWaveDTO> findOne(Long id) {
        log.debug("Request to get MPvpWave : {}", id);
        return mPvpWaveRepository.findById(id)
            .map(mPvpWaveMapper::toDto);
    }

    /**
     * Delete the mPvpWave by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPvpWave : {}", id);
        mPvpWaveRepository.deleteById(id);
    }
}
