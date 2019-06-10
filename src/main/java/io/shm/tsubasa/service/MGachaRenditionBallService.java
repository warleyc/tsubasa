package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionBall;
import io.shm.tsubasa.repository.MGachaRenditionBallRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionBallDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionBallMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionBall}.
 */
@Service
@Transactional
public class MGachaRenditionBallService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionBallService.class);

    private final MGachaRenditionBallRepository mGachaRenditionBallRepository;

    private final MGachaRenditionBallMapper mGachaRenditionBallMapper;

    public MGachaRenditionBallService(MGachaRenditionBallRepository mGachaRenditionBallRepository, MGachaRenditionBallMapper mGachaRenditionBallMapper) {
        this.mGachaRenditionBallRepository = mGachaRenditionBallRepository;
        this.mGachaRenditionBallMapper = mGachaRenditionBallMapper;
    }

    /**
     * Save a mGachaRenditionBall.
     *
     * @param mGachaRenditionBallDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionBallDTO save(MGachaRenditionBallDTO mGachaRenditionBallDTO) {
        log.debug("Request to save MGachaRenditionBall : {}", mGachaRenditionBallDTO);
        MGachaRenditionBall mGachaRenditionBall = mGachaRenditionBallMapper.toEntity(mGachaRenditionBallDTO);
        mGachaRenditionBall = mGachaRenditionBallRepository.save(mGachaRenditionBall);
        return mGachaRenditionBallMapper.toDto(mGachaRenditionBall);
    }

    /**
     * Get all the mGachaRenditionBalls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionBallDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionBalls");
        return mGachaRenditionBallRepository.findAll(pageable)
            .map(mGachaRenditionBallMapper::toDto);
    }


    /**
     * Get one mGachaRenditionBall by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionBallDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionBall : {}", id);
        return mGachaRenditionBallRepository.findById(id)
            .map(mGachaRenditionBallMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionBall by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionBall : {}", id);
        mGachaRenditionBallRepository.deleteById(id);
    }
}
