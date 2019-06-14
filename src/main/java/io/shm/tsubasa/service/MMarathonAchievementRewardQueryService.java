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

import io.shm.tsubasa.domain.MMarathonAchievementReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonAchievementRewardRepository;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardCriteria;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonAchievementRewardMapper;

/**
 * Service for executing complex queries for {@link MMarathonAchievementReward} entities in the database.
 * The main input is a {@link MMarathonAchievementRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonAchievementRewardDTO} or a {@link Page} of {@link MMarathonAchievementRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonAchievementRewardQueryService extends QueryService<MMarathonAchievementReward> {

    private final Logger log = LoggerFactory.getLogger(MMarathonAchievementRewardQueryService.class);

    private final MMarathonAchievementRewardRepository mMarathonAchievementRewardRepository;

    private final MMarathonAchievementRewardMapper mMarathonAchievementRewardMapper;

    public MMarathonAchievementRewardQueryService(MMarathonAchievementRewardRepository mMarathonAchievementRewardRepository, MMarathonAchievementRewardMapper mMarathonAchievementRewardMapper) {
        this.mMarathonAchievementRewardRepository = mMarathonAchievementRewardRepository;
        this.mMarathonAchievementRewardMapper = mMarathonAchievementRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonAchievementRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonAchievementRewardDTO> findByCriteria(MMarathonAchievementRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonAchievementReward> specification = createSpecification(criteria);
        return mMarathonAchievementRewardMapper.toDto(mMarathonAchievementRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonAchievementRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonAchievementRewardDTO> findByCriteria(MMarathonAchievementRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonAchievementReward> specification = createSpecification(criteria);
        return mMarathonAchievementRewardRepository.findAll(specification, page)
            .map(mMarathonAchievementRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonAchievementRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonAchievementReward> specification = createSpecification(criteria);
        return mMarathonAchievementRewardRepository.count(specification);
    }

    /**
     * Function to convert MMarathonAchievementRewardCriteria to a {@link Specification}.
     */
    private Specification<MMarathonAchievementReward> createSpecification(MMarathonAchievementRewardCriteria criteria) {
        Specification<MMarathonAchievementReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonAchievementReward_.id));
            }
            if (criteria.getEventId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventId(), MMarathonAchievementReward_.eventId));
            }
            if (criteria.getEventPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventPoint(), MMarathonAchievementReward_.eventPoint));
            }
            if (criteria.getRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRewardGroupId(), MMarathonAchievementReward_.rewardGroupId));
            }
        }
        return specification;
    }
}
