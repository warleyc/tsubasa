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

import io.shm.tsubasa.domain.MTimeattackRankingReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTimeattackRankingRewardRepository;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardCriteria;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MTimeattackRankingRewardMapper;

/**
 * Service for executing complex queries for {@link MTimeattackRankingReward} entities in the database.
 * The main input is a {@link MTimeattackRankingRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTimeattackRankingRewardDTO} or a {@link Page} of {@link MTimeattackRankingRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTimeattackRankingRewardQueryService extends QueryService<MTimeattackRankingReward> {

    private final Logger log = LoggerFactory.getLogger(MTimeattackRankingRewardQueryService.class);

    private final MTimeattackRankingRewardRepository mTimeattackRankingRewardRepository;

    private final MTimeattackRankingRewardMapper mTimeattackRankingRewardMapper;

    public MTimeattackRankingRewardQueryService(MTimeattackRankingRewardRepository mTimeattackRankingRewardRepository, MTimeattackRankingRewardMapper mTimeattackRankingRewardMapper) {
        this.mTimeattackRankingRewardRepository = mTimeattackRankingRewardRepository;
        this.mTimeattackRankingRewardMapper = mTimeattackRankingRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MTimeattackRankingRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTimeattackRankingRewardDTO> findByCriteria(MTimeattackRankingRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTimeattackRankingReward> specification = createSpecification(criteria);
        return mTimeattackRankingRewardMapper.toDto(mTimeattackRankingRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTimeattackRankingRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTimeattackRankingRewardDTO> findByCriteria(MTimeattackRankingRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTimeattackRankingReward> specification = createSpecification(criteria);
        return mTimeattackRankingRewardRepository.findAll(specification, page)
            .map(mTimeattackRankingRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTimeattackRankingRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTimeattackRankingReward> specification = createSpecification(criteria);
        return mTimeattackRankingRewardRepository.count(specification);
    }

    /**
     * Function to convert MTimeattackRankingRewardCriteria to a {@link Specification}.
     */
    private Specification<MTimeattackRankingReward> createSpecification(MTimeattackRankingRewardCriteria criteria) {
        Specification<MTimeattackRankingReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTimeattackRankingReward_.id));
            }
            if (criteria.getEventId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventId(), MTimeattackRankingReward_.eventId));
            }
            if (criteria.getRankingFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRankingFrom(), MTimeattackRankingReward_.rankingFrom));
            }
            if (criteria.getRankingTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRankingTo(), MTimeattackRankingReward_.rankingTo));
            }
            if (criteria.getRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRewardGroupId(), MTimeattackRankingReward_.rewardGroupId));
            }
        }
        return specification;
    }
}
