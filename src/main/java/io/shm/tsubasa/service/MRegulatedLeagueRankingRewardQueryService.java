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

import io.shm.tsubasa.domain.MRegulatedLeagueRankingReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MRegulatedLeagueRankingRewardRepository;
import io.shm.tsubasa.service.dto.MRegulatedLeagueRankingRewardCriteria;
import io.shm.tsubasa.service.dto.MRegulatedLeagueRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MRegulatedLeagueRankingRewardMapper;

/**
 * Service for executing complex queries for {@link MRegulatedLeagueRankingReward} entities in the database.
 * The main input is a {@link MRegulatedLeagueRankingRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MRegulatedLeagueRankingRewardDTO} or a {@link Page} of {@link MRegulatedLeagueRankingRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MRegulatedLeagueRankingRewardQueryService extends QueryService<MRegulatedLeagueRankingReward> {

    private final Logger log = LoggerFactory.getLogger(MRegulatedLeagueRankingRewardQueryService.class);

    private final MRegulatedLeagueRankingRewardRepository mRegulatedLeagueRankingRewardRepository;

    private final MRegulatedLeagueRankingRewardMapper mRegulatedLeagueRankingRewardMapper;

    public MRegulatedLeagueRankingRewardQueryService(MRegulatedLeagueRankingRewardRepository mRegulatedLeagueRankingRewardRepository, MRegulatedLeagueRankingRewardMapper mRegulatedLeagueRankingRewardMapper) {
        this.mRegulatedLeagueRankingRewardRepository = mRegulatedLeagueRankingRewardRepository;
        this.mRegulatedLeagueRankingRewardMapper = mRegulatedLeagueRankingRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MRegulatedLeagueRankingRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MRegulatedLeagueRankingRewardDTO> findByCriteria(MRegulatedLeagueRankingRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MRegulatedLeagueRankingReward> specification = createSpecification(criteria);
        return mRegulatedLeagueRankingRewardMapper.toDto(mRegulatedLeagueRankingRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MRegulatedLeagueRankingRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MRegulatedLeagueRankingRewardDTO> findByCriteria(MRegulatedLeagueRankingRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MRegulatedLeagueRankingReward> specification = createSpecification(criteria);
        return mRegulatedLeagueRankingRewardRepository.findAll(specification, page)
            .map(mRegulatedLeagueRankingRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MRegulatedLeagueRankingRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MRegulatedLeagueRankingReward> specification = createSpecification(criteria);
        return mRegulatedLeagueRankingRewardRepository.count(specification);
    }

    /**
     * Function to convert MRegulatedLeagueRankingRewardCriteria to a {@link Specification}.
     */
    private Specification<MRegulatedLeagueRankingReward> createSpecification(MRegulatedLeagueRankingRewardCriteria criteria) {
        Specification<MRegulatedLeagueRankingReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MRegulatedLeagueRankingReward_.id));
            }
            if (criteria.getRegulatedLeagueId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegulatedLeagueId(), MRegulatedLeagueRankingReward_.regulatedLeagueId));
            }
            if (criteria.getLeagueHierarchy() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLeagueHierarchy(), MRegulatedLeagueRankingReward_.leagueHierarchy));
            }
            if (criteria.getRankTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRankTo(), MRegulatedLeagueRankingReward_.rankTo));
            }
            if (criteria.getRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRewardGroupId(), MRegulatedLeagueRankingReward_.rewardGroupId));
            }
        }
        return specification;
    }
}
