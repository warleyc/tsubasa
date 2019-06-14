package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MUniformUp;
import io.shm.tsubasa.repository.MUniformUpRepository;
import io.shm.tsubasa.service.dto.MUniformUpDTO;
import io.shm.tsubasa.service.mapper.MUniformUpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MUniformUp}.
 */
@Service
@Transactional
public class MUniformUpService {

    private final Logger log = LoggerFactory.getLogger(MUniformUpService.class);

    private final MUniformUpRepository mUniformUpRepository;

    private final MUniformUpMapper mUniformUpMapper;

    public MUniformUpService(MUniformUpRepository mUniformUpRepository, MUniformUpMapper mUniformUpMapper) {
        this.mUniformUpRepository = mUniformUpRepository;
        this.mUniformUpMapper = mUniformUpMapper;
    }

    /**
     * Save a mUniformUp.
     *
     * @param mUniformUpDTO the entity to save.
     * @return the persisted entity.
     */
    public MUniformUpDTO save(MUniformUpDTO mUniformUpDTO) {
        log.debug("Request to save MUniformUp : {}", mUniformUpDTO);
        MUniformUp mUniformUp = mUniformUpMapper.toEntity(mUniformUpDTO);
        mUniformUp = mUniformUpRepository.save(mUniformUp);
        return mUniformUpMapper.toDto(mUniformUp);
    }

    /**
     * Get all the mUniformUps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MUniformUpDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MUniformUps");
        return mUniformUpRepository.findAll(pageable)
            .map(mUniformUpMapper::toDto);
    }


    /**
     * Get one mUniformUp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MUniformUpDTO> findOne(Long id) {
        log.debug("Request to get MUniformUp : {}", id);
        return mUniformUpRepository.findById(id)
            .map(mUniformUpMapper::toDto);
    }

    /**
     * Delete the mUniformUp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MUniformUp : {}", id);
        mUniformUpRepository.deleteById(id);
    }
}
