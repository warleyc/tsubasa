package io.shm.tsubasa.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.shm.tsubasa.domain.MPvpWave;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPvpWaveRepository;
import io.shm.tsubasa.service.dto.MPvpWaveCriteria;
import io.shm.tsubasa.service.dto.MPvpWaveDTO;
import io.shm.tsubasa.service.mapper.MPvpWaveMapper;

/**
 * Service for executing complex queries for {@link MPvpWave} entities in the database.
 * The main input is a {@link MPvpWaveCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPvpWaveDTO} or a {@link Page} of {@link MPvpWaveDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPvpWaveQueryService extends QueryService<MPvpWave> {

    private final Logger log = LoggerFactory.getLogger(MPvpWaveQueryService.class);

    private final MPvpWaveRepository mPvpWaveRepository;

    private final MPvpWaveMapper mPvpWaveMapper;

    public MPvpWaveQueryService(MPvpWaveRepository mPvpWaveRepository, MPvpWaveMapper mPvpWaveMapper) {
        this.mPvpWaveRepository = mPvpWaveRepository;
        this.mPvpWaveMapper = mPvpWaveMapper;
    }

    /**
     * Return a {@link List} of {@link MPvpWaveDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPvpWaveDTO> findByCriteria(MPvpWaveCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPvpWave> specification = createSpecification(criteria);
        return mPvpWaveMapper.toDto(mPvpWaveRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPvpWaveDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpWaveDTO> findByCriteria(MPvpWaveCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPvpWave> specification = createSpecification(criteria);
        return mPvpWaveRepository.findAll(specification, page)
            .map(mPvpWaveMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPvpWaveCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPvpWave> specification = createSpecification(criteria);
        return mPvpWaveRepository.count(specification);
    }

    /**
     * Function to convert MPvpWaveCriteria to a {@link Specification}.
     */
    private Specification<MPvpWave> createSpecification(MPvpWaveCriteria criteria) {
        Specification<MPvpWave> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPvpWave_.id));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MPvpWave_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MPvpWave_.endAt));
            }
            if (criteria.getIsRanking() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsRanking(), MPvpWave_.isRanking));
            }
            if (criteria.getMPvpRankingRewardId() != null) {
                specification = specification.and(buildSpecification(criteria.getMPvpRankingRewardId(),
                    root -> root.join(MPvpWave_.mPvpRankingRewards, JoinType.LEFT).get(MPvpRankingReward_.id)));
            }
        }
        return specification;
    }
}
