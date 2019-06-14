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

import io.shm.tsubasa.domain.MPvpRankingReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPvpRankingRewardRepository;
import io.shm.tsubasa.service.dto.MPvpRankingRewardCriteria;
import io.shm.tsubasa.service.dto.MPvpRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MPvpRankingRewardMapper;

/**
 * Service for executing complex queries for {@link MPvpRankingReward} entities in the database.
 * The main input is a {@link MPvpRankingRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPvpRankingRewardDTO} or a {@link Page} of {@link MPvpRankingRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPvpRankingRewardQueryService extends QueryService<MPvpRankingReward> {

    private final Logger log = LoggerFactory.getLogger(MPvpRankingRewardQueryService.class);

    private final MPvpRankingRewardRepository mPvpRankingRewardRepository;

    private final MPvpRankingRewardMapper mPvpRankingRewardMapper;

    public MPvpRankingRewardQueryService(MPvpRankingRewardRepository mPvpRankingRewardRepository, MPvpRankingRewardMapper mPvpRankingRewardMapper) {
        this.mPvpRankingRewardRepository = mPvpRankingRewardRepository;
        this.mPvpRankingRewardMapper = mPvpRankingRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MPvpRankingRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPvpRankingRewardDTO> findByCriteria(MPvpRankingRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPvpRankingReward> specification = createSpecification(criteria);
        return mPvpRankingRewardMapper.toDto(mPvpRankingRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPvpRankingRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpRankingRewardDTO> findByCriteria(MPvpRankingRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPvpRankingReward> specification = createSpecification(criteria);
        return mPvpRankingRewardRepository.findAll(specification, page)
            .map(mPvpRankingRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPvpRankingRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPvpRankingReward> specification = createSpecification(criteria);
        return mPvpRankingRewardRepository.count(specification);
    }

    /**
     * Function to convert MPvpRankingRewardCriteria to a {@link Specification}.
     */
    private Specification<MPvpRankingReward> createSpecification(MPvpRankingRewardCriteria criteria) {
        Specification<MPvpRankingReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPvpRankingReward_.id));
            }
            if (criteria.getWaveId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWaveId(), MPvpRankingReward_.waveId));
            }
            if (criteria.getDifficulty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDifficulty(), MPvpRankingReward_.difficulty));
            }
            if (criteria.getRankingFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRankingFrom(), MPvpRankingReward_.rankingFrom));
            }
            if (criteria.getRankingTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRankingTo(), MPvpRankingReward_.rankingTo));
            }
            if (criteria.getRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRewardGroupId(), MPvpRankingReward_.rewardGroupId));
            }
            if (criteria.getMpvpwaveId() != null) {
                specification = specification.and(buildSpecification(criteria.getMpvpwaveId(),
                    root -> root.join(MPvpRankingReward_.mpvpwave, JoinType.LEFT).get(MPvpWave_.id)));
            }
        }
        return specification;
    }
}
