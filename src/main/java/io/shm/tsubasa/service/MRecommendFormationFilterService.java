package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MRecommendFormationFilter;
import io.shm.tsubasa.repository.MRecommendFormationFilterRepository;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterDTO;
import io.shm.tsubasa.service.mapper.MRecommendFormationFilterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MRecommendFormationFilter}.
 */
@Service
@Transactional
public class MRecommendFormationFilterService {

    private final Logger log = LoggerFactory.getLogger(MRecommendFormationFilterService.class);

    private final MRecommendFormationFilterRepository mRecommendFormationFilterRepository;

    private final MRecommendFormationFilterMapper mRecommendFormationFilterMapper;

    public MRecommendFormationFilterService(MRecommendFormationFilterRepository mRecommendFormationFilterRepository, MRecommendFormationFilterMapper mRecommendFormationFilterMapper) {
        this.mRecommendFormationFilterRepository = mRecommendFormationFilterRepository;
        this.mRecommendFormationFilterMapper = mRecommendFormationFilterMapper;
    }

    /**
     * Save a mRecommendFormationFilter.
     *
     * @param mRecommendFormationFilterDTO the entity to save.
     * @return the persisted entity.
     */
    public MRecommendFormationFilterDTO save(MRecommendFormationFilterDTO mRecommendFormationFilterDTO) {
        log.debug("Request to save MRecommendFormationFilter : {}", mRecommendFormationFilterDTO);
        MRecommendFormationFilter mRecommendFormationFilter = mRecommendFormationFilterMapper.toEntity(mRecommendFormationFilterDTO);
        mRecommendFormationFilter = mRecommendFormationFilterRepository.save(mRecommendFormationFilter);
        return mRecommendFormationFilterMapper.toDto(mRecommendFormationFilter);
    }

    /**
     * Get all the mRecommendFormationFilters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MRecommendFormationFilterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MRecommendFormationFilters");
        return mRecommendFormationFilterRepository.findAll(pageable)
            .map(mRecommendFormationFilterMapper::toDto);
    }


    /**
     * Get one mRecommendFormationFilter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MRecommendFormationFilterDTO> findOne(Long id) {
        log.debug("Request to get MRecommendFormationFilter : {}", id);
        return mRecommendFormationFilterRepository.findById(id)
            .map(mRecommendFormationFilterMapper::toDto);
    }

    /**
     * Delete the mRecommendFormationFilter by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MRecommendFormationFilter : {}", id);
        mRecommendFormationFilterRepository.deleteById(id);
    }
}
