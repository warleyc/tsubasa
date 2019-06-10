package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDistributeParamPoint;
import io.shm.tsubasa.repository.MDistributeParamPointRepository;
import io.shm.tsubasa.service.dto.MDistributeParamPointDTO;
import io.shm.tsubasa.service.mapper.MDistributeParamPointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDistributeParamPoint}.
 */
@Service
@Transactional
public class MDistributeParamPointService {

    private final Logger log = LoggerFactory.getLogger(MDistributeParamPointService.class);

    private final MDistributeParamPointRepository mDistributeParamPointRepository;

    private final MDistributeParamPointMapper mDistributeParamPointMapper;

    public MDistributeParamPointService(MDistributeParamPointRepository mDistributeParamPointRepository, MDistributeParamPointMapper mDistributeParamPointMapper) {
        this.mDistributeParamPointRepository = mDistributeParamPointRepository;
        this.mDistributeParamPointMapper = mDistributeParamPointMapper;
    }

    /**
     * Save a mDistributeParamPoint.
     *
     * @param mDistributeParamPointDTO the entity to save.
     * @return the persisted entity.
     */
    public MDistributeParamPointDTO save(MDistributeParamPointDTO mDistributeParamPointDTO) {
        log.debug("Request to save MDistributeParamPoint : {}", mDistributeParamPointDTO);
        MDistributeParamPoint mDistributeParamPoint = mDistributeParamPointMapper.toEntity(mDistributeParamPointDTO);
        mDistributeParamPoint = mDistributeParamPointRepository.save(mDistributeParamPoint);
        return mDistributeParamPointMapper.toDto(mDistributeParamPoint);
    }

    /**
     * Get all the mDistributeParamPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDistributeParamPointDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDistributeParamPoints");
        return mDistributeParamPointRepository.findAll(pageable)
            .map(mDistributeParamPointMapper::toDto);
    }


    /**
     * Get one mDistributeParamPoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDistributeParamPointDTO> findOne(Long id) {
        log.debug("Request to get MDistributeParamPoint : {}", id);
        return mDistributeParamPointRepository.findById(id)
            .map(mDistributeParamPointMapper::toDto);
    }

    /**
     * Delete the mDistributeParamPoint by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDistributeParamPoint : {}", id);
        mDistributeParamPointRepository.deleteById(id);
    }
}
