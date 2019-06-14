package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTsubasaPoint;
import io.shm.tsubasa.repository.MTsubasaPointRepository;
import io.shm.tsubasa.service.dto.MTsubasaPointDTO;
import io.shm.tsubasa.service.mapper.MTsubasaPointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTsubasaPoint}.
 */
@Service
@Transactional
public class MTsubasaPointService {

    private final Logger log = LoggerFactory.getLogger(MTsubasaPointService.class);

    private final MTsubasaPointRepository mTsubasaPointRepository;

    private final MTsubasaPointMapper mTsubasaPointMapper;

    public MTsubasaPointService(MTsubasaPointRepository mTsubasaPointRepository, MTsubasaPointMapper mTsubasaPointMapper) {
        this.mTsubasaPointRepository = mTsubasaPointRepository;
        this.mTsubasaPointMapper = mTsubasaPointMapper;
    }

    /**
     * Save a mTsubasaPoint.
     *
     * @param mTsubasaPointDTO the entity to save.
     * @return the persisted entity.
     */
    public MTsubasaPointDTO save(MTsubasaPointDTO mTsubasaPointDTO) {
        log.debug("Request to save MTsubasaPoint : {}", mTsubasaPointDTO);
        MTsubasaPoint mTsubasaPoint = mTsubasaPointMapper.toEntity(mTsubasaPointDTO);
        mTsubasaPoint = mTsubasaPointRepository.save(mTsubasaPoint);
        return mTsubasaPointMapper.toDto(mTsubasaPoint);
    }

    /**
     * Get all the mTsubasaPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTsubasaPointDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTsubasaPoints");
        return mTsubasaPointRepository.findAll(pageable)
            .map(mTsubasaPointMapper::toDto);
    }


    /**
     * Get one mTsubasaPoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTsubasaPointDTO> findOne(Long id) {
        log.debug("Request to get MTsubasaPoint : {}", id);
        return mTsubasaPointRepository.findById(id)
            .map(mTsubasaPointMapper::toDto);
    }

    /**
     * Delete the mTsubasaPoint by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTsubasaPoint : {}", id);
        mTsubasaPointRepository.deleteById(id);
    }
}
