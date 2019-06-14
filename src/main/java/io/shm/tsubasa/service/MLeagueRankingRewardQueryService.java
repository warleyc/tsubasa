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

import io.shm.tsubasa.domain.MLeagueRankingReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLeagueRankingRewardRepository;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardCriteria;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MLeagueRankingRewardMapper;

/**
 * Service for executing complex queries for {@link MLeagueRankingReward} entities in the database.
 * The main input is a {@link MLeagueRankingRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLeagueRankingRewardDTO} or a {@link Page} of {@link MLeagueRankingRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLeagueRankingRewardQueryService extends QueryService<MLeagueRankingReward> {

    private final Logger log = LoggerFactory.getLogger(MLeagueRankingRewardQueryService.class);

    private final MLeagueRankingRewardRepository mLeagueRankingRewardRepository;

    private final MLeagueRankingRewardMapper mLeagueRankingRewardMapper;

    public MLeagueRankingRewardQueryService(MLeagueRankingRewardRepository mLeagueRankingRewardRepository, MLeagueRankingRewardMapper mLeagueRankingRewardMapper) {
        this.mLeagueRankingRewardRepository = mLeagueRankingRewardRepository;
        this.mLeagueRankingRewardMapper = mLeagueRankingRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MLeagueRankingRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLeagueRankingRewardDTO> findByCriteria(MLeagueRankingRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLeagueRankingReward> specification = createSpecification(criteria);
        return mLeagueRankingRewardMapper.toDto(mLeagueRankingRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLeagueRankingRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueRankingRewardDTO> findByCriteria(MLeagueRankingRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLeagueRankingReward> specification = createSpecification(criteria);
        return mLeagueRankingRewardRepository.findAll(specification, page)
            .map(mLeagueRankingRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLeagueRankingRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLeagueRankingReward> specification = createSpecification(criteria);
        return mLeagueRankingRewardRepository.count(specification);
    }

    /**
     * Function to convert MLeagueRankingRewardCriteria to a {@link Specification}.
     */
    private Specification<MLeagueRankingReward> createSpecification(MLeagueRankingRewardCriteria criteria) {
        Specification<MLeagueRankingReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLeagueRankingReward_.id));
            }
            if (criteria.getLeagueHierarchy() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLeagueHierarchy(), MLeagueRankingReward_.leagueHierarchy));
            }
            if (criteria.getRankTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRankTo(), MLeagueRankingReward_.rankTo));
            }
            if (criteria.getRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRewardGroupId(), MLeagueRankingReward_.rewardGroupId));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MLeagueRankingReward_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MLeagueRankingReward_.endAt));
            }
        }
        return specification;
    }
}
