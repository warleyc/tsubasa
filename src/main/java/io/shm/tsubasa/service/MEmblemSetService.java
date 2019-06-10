package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MEmblemSet;
import io.shm.tsubasa.repository.MEmblemSetRepository;
import io.shm.tsubasa.service.dto.MEmblemSetDTO;
import io.shm.tsubasa.service.mapper.MEmblemSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MEmblemSet}.
 */
@Service
@Transactional
public class MEmblemSetService {

    private final Logger log = LoggerFactory.getLogger(MEmblemSetService.class);

    private final MEmblemSetRepository mEmblemSetRepository;

    private final MEmblemSetMapper mEmblemSetMapper;

    public MEmblemSetService(MEmblemSetRepository mEmblemSetRepository, MEmblemSetMapper mEmblemSetMapper) {
        this.mEmblemSetRepository = mEmblemSetRepository;
        this.mEmblemSetMapper = mEmblemSetMapper;
    }

    /**
     * Save a mEmblemSet.
     *
     * @param mEmblemSetDTO the entity to save.
     * @return the persisted entity.
     */
    public MEmblemSetDTO save(MEmblemSetDTO mEmblemSetDTO) {
        log.debug("Request to save MEmblemSet : {}", mEmblemSetDTO);
        MEmblemSet mEmblemSet = mEmblemSetMapper.toEntity(mEmblemSetDTO);
        mEmblemSet = mEmblemSetRepository.save(mEmblemSet);
        return mEmblemSetMapper.toDto(mEmblemSet);
    }

    /**
     * Get all the mEmblemSets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MEmblemSetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MEmblemSets");
        return mEmblemSetRepository.findAll(pageable)
            .map(mEmblemSetMapper::toDto);
    }


    /**
     * Get one mEmblemSet by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MEmblemSetDTO> findOne(Long id) {
        log.debug("Request to get MEmblemSet : {}", id);
        return mEmblemSetRepository.findById(id)
            .map(mEmblemSetMapper::toDto);
    }

    /**
     * Delete the mEmblemSet by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MEmblemSet : {}", id);
        mEmblemSetRepository.deleteById(id);
    }
}
