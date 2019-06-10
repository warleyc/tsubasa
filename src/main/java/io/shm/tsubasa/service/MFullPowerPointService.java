package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MFullPowerPoint;
import io.shm.tsubasa.repository.MFullPowerPointRepository;
import io.shm.tsubasa.service.dto.MFullPowerPointDTO;
import io.shm.tsubasa.service.mapper.MFullPowerPointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MFullPowerPoint}.
 */
@Service
@Transactional
public class MFullPowerPointService {

    private final Logger log = LoggerFactory.getLogger(MFullPowerPointService.class);

    private final MFullPowerPointRepository mFullPowerPointRepository;

    private final MFullPowerPointMapper mFullPowerPointMapper;

    public MFullPowerPointService(MFullPowerPointRepository mFullPowerPointRepository, MFullPowerPointMapper mFullPowerPointMapper) {
        this.mFullPowerPointRepository = mFullPowerPointRepository;
        this.mFullPowerPointMapper = mFullPowerPointMapper;
    }

    /**
     * Save a mFullPowerPoint.
     *
     * @param mFullPowerPointDTO the entity to save.
     * @return the persisted entity.
     */
    public MFullPowerPointDTO save(MFullPowerPointDTO mFullPowerPointDTO) {
        log.debug("Request to save MFullPowerPoint : {}", mFullPowerPointDTO);
        MFullPowerPoint mFullPowerPoint = mFullPowerPointMapper.toEntity(mFullPowerPointDTO);
        mFullPowerPoint = mFullPowerPointRepository.save(mFullPowerPoint);
        return mFullPowerPointMapper.toDto(mFullPowerPoint);
    }

    /**
     * Get all the mFullPowerPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MFullPowerPointDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MFullPowerPoints");
        return mFullPowerPointRepository.findAll(pageable)
            .map(mFullPowerPointMapper::toDto);
    }


    /**
     * Get one mFullPowerPoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MFullPowerPointDTO> findOne(Long id) {
        log.debug("Request to get MFullPowerPoint : {}", id);
        return mFullPowerPointRepository.findById(id)
            .map(mFullPowerPointMapper::toDto);
    }

    /**
     * Delete the mFullPowerPoint by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MFullPowerPoint : {}", id);
        mFullPowerPointRepository.deleteById(id);
    }
}
