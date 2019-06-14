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

import io.shm.tsubasa.domain.MMarathonRankingReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonRankingRewardRepository;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardCriteria;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonRankingRewardMapper;

/**
 * Service for executing complex queries for {@link MMarathonRankingReward} entities in the database.
 * The main input is a {@link MMarathonRankingRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonRankingRewardDTO} or a {@link Page} of {@link MMarathonRankingRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonRankingRewardQueryService extends QueryService<MMarathonRankingReward> {

    private final Logger log = LoggerFactory.getLogger(MMarathonRankingRewardQueryService.class);

    private final MMarathonRankingRewardRepository mMarathonRankingRewardRepository;

    private final MMarathonRankingRewardMapper mMarathonRankingRewardMapper;

    public MMarathonRankingRewardQueryService(MMarathonRankingRewardRepository mMarathonRankingRewardRepository, MMarathonRankingRewardMapper mMarathonRankingRewardMapper) {
        this.mMarathonRankingRewardRepository = mMarathonRankingRewardRepository;
        this.mMarathonRankingRewardMapper = mMarathonRankingRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonRankingRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonRankingRewardDTO> findByCriteria(MMarathonRankingRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonRankingReward> specification = createSpecification(criteria);
        return mMarathonRankingRewardMapper.toDto(mMarathonRankingRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonRankingRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonRankingRewardDTO> findByCriteria(MMarathonRankingRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonRankingReward> specification = createSpecification(criteria);
        return mMarathonRankingRewardRepository.findAll(specification, page)
            .map(mMarathonRankingRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonRankingRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonRankingReward> specification = createSpecification(criteria);
        return mMarathonRankingRewardRepository.count(specification);
    }

    /**
     * Function to convert MMarathonRankingRewardCriteria to a {@link Specification}.
     */
    private Specification<MMarathonRankingReward> createSpecification(MMarathonRankingRewardCriteria criteria) {
        Specification<MMarathonRankingReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonRankingReward_.id));
            }
            if (criteria.getEventId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventId(), MMarathonRankingReward_.eventId));
            }
            if (criteria.getRankingFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRankingFrom(), MMarathonRankingReward_.rankingFrom));
            }
            if (criteria.getRankingTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRankingTo(), MMarathonRankingReward_.rankingTo));
            }
            if (criteria.getRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRewardGroupId(), MMarathonRankingReward_.rewardGroupId));
            }
        }
        return specification;
    }
}
