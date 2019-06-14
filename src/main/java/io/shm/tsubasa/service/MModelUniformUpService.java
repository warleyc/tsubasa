package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MModelUniformUp;
import io.shm.tsubasa.repository.MModelUniformUpRepository;
import io.shm.tsubasa.service.dto.MModelUniformUpDTO;
import io.shm.tsubasa.service.mapper.MModelUniformUpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MModelUniformUp}.
 */
@Service
@Transactional
public class MModelUniformUpService {

    private final Logger log = LoggerFactory.getLogger(MModelUniformUpService.class);

    private final MModelUniformUpRepository mModelUniformUpRepository;

    private final MModelUniformUpMapper mModelUniformUpMapper;

    public MModelUniformUpService(MModelUniformUpRepository mModelUniformUpRepository, MModelUniformUpMapper mModelUniformUpMapper) {
        this.mModelUniformUpRepository = mModelUniformUpRepository;
        this.mModelUniformUpMapper = mModelUniformUpMapper;
    }

    /**
     * Save a mModelUniformUp.
     *
     * @param mModelUniformUpDTO the entity to save.
     * @return the persisted entity.
     */
    public MModelUniformUpDTO save(MModelUniformUpDTO mModelUniformUpDTO) {
        log.debug("Request to save MModelUniformUp : {}", mModelUniformUpDTO);
        MModelUniformUp mModelUniformUp = mModelUniformUpMapper.toEntity(mModelUniformUpDTO);
        mModelUniformUp = mModelUniformUpRepository.save(mModelUniformUp);
        return mModelUniformUpMapper.toDto(mModelUniformUp);
    }

    /**
     * Get all the mModelUniformUps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelUniformUpDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MModelUniformUps");
        return mModelUniformUpRepository.findAll(pageable)
            .map(mModelUniformUpMapper::toDto);
    }


    /**
     * Get one mModelUniformUp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MModelUniformUpDTO> findOne(Long id) {
        log.debug("Request to get MModelUniformUp : {}", id);
        return mModelUniformUpRepository.findById(id)
            .map(mModelUniformUpMapper::toDto);
    }

    /**
     * Delete the mModelUniformUp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MModelUniformUp : {}", id);
        mModelUniformUpRepository.deleteById(id);
    }
}
