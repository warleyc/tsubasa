package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDummyEmblem;
import io.shm.tsubasa.repository.MDummyEmblemRepository;
import io.shm.tsubasa.service.dto.MDummyEmblemDTO;
import io.shm.tsubasa.service.mapper.MDummyEmblemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDummyEmblem}.
 */
@Service
@Transactional
public class MDummyEmblemService {

    private final Logger log = LoggerFactory.getLogger(MDummyEmblemService.class);

    private final MDummyEmblemRepository mDummyEmblemRepository;

    private final MDummyEmblemMapper mDummyEmblemMapper;

    public MDummyEmblemService(MDummyEmblemRepository mDummyEmblemRepository, MDummyEmblemMapper mDummyEmblemMapper) {
        this.mDummyEmblemRepository = mDummyEmblemRepository;
        this.mDummyEmblemMapper = mDummyEmblemMapper;
    }

    /**
     * Save a mDummyEmblem.
     *
     * @param mDummyEmblemDTO the entity to save.
     * @return the persisted entity.
     */
    public MDummyEmblemDTO save(MDummyEmblemDTO mDummyEmblemDTO) {
        log.debug("Request to save MDummyEmblem : {}", mDummyEmblemDTO);
        MDummyEmblem mDummyEmblem = mDummyEmblemMapper.toEntity(mDummyEmblemDTO);
        mDummyEmblem = mDummyEmblemRepository.save(mDummyEmblem);
        return mDummyEmblemMapper.toDto(mDummyEmblem);
    }

    /**
     * Get all the mDummyEmblems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDummyEmblemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDummyEmblems");
        return mDummyEmblemRepository.findAll(pageable)
            .map(mDummyEmblemMapper::toDto);
    }


    /**
     * Get one mDummyEmblem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDummyEmblemDTO> findOne(Long id) {
        log.debug("Request to get MDummyEmblem : {}", id);
        return mDummyEmblemRepository.findById(id)
            .map(mDummyEmblemMapper::toDto);
    }

    /**
     * Delete the mDummyEmblem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDummyEmblem : {}", id);
        mDummyEmblemRepository.deleteById(id);
    }
}
