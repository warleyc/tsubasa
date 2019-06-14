package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MUniformOriginalSet;
import io.shm.tsubasa.repository.MUniformOriginalSetRepository;
import io.shm.tsubasa.service.dto.MUniformOriginalSetDTO;
import io.shm.tsubasa.service.mapper.MUniformOriginalSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MUniformOriginalSet}.
 */
@Service
@Transactional
public class MUniformOriginalSetService {

    private final Logger log = LoggerFactory.getLogger(MUniformOriginalSetService.class);

    private final MUniformOriginalSetRepository mUniformOriginalSetRepository;

    private final MUniformOriginalSetMapper mUniformOriginalSetMapper;

    public MUniformOriginalSetService(MUniformOriginalSetRepository mUniformOriginalSetRepository, MUniformOriginalSetMapper mUniformOriginalSetMapper) {
        this.mUniformOriginalSetRepository = mUniformOriginalSetRepository;
        this.mUniformOriginalSetMapper = mUniformOriginalSetMapper;
    }

    /**
     * Save a mUniformOriginalSet.
     *
     * @param mUniformOriginalSetDTO the entity to save.
     * @return the persisted entity.
     */
    public MUniformOriginalSetDTO save(MUniformOriginalSetDTO mUniformOriginalSetDTO) {
        log.debug("Request to save MUniformOriginalSet : {}", mUniformOriginalSetDTO);
        MUniformOriginalSet mUniformOriginalSet = mUniformOriginalSetMapper.toEntity(mUniformOriginalSetDTO);
        mUniformOriginalSet = mUniformOriginalSetRepository.save(mUniformOriginalSet);
        return mUniformOriginalSetMapper.toDto(mUniformOriginalSet);
    }

    /**
     * Get all the mUniformOriginalSets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MUniformOriginalSetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MUniformOriginalSets");
        return mUniformOriginalSetRepository.findAll(pageable)
            .map(mUniformOriginalSetMapper::toDto);
    }


    /**
     * Get one mUniformOriginalSet by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MUniformOriginalSetDTO> findOne(Long id) {
        log.debug("Request to get MUniformOriginalSet : {}", id);
        return mUniformOriginalSetRepository.findById(id)
            .map(mUniformOriginalSetMapper::toDto);
    }

    /**
     * Delete the mUniformOriginalSet by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MUniformOriginalSet : {}", id);
        mUniformOriginalSetRepository.deleteById(id);
    }
}
