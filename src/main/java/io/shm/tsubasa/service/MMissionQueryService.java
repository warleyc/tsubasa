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

import io.shm.tsubasa.domain.MMission;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMissionRepository;
import io.shm.tsubasa.service.dto.MMissionCriteria;
import io.shm.tsubasa.service.dto.MMissionDTO;
import io.shm.tsubasa.service.mapper.MMissionMapper;

/**
 * Service for executing complex queries for {@link MMission} entities in the database.
 * The main input is a {@link MMissionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMissionDTO} or a {@link Page} of {@link MMissionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMissionQueryService extends QueryService<MMission> {

    private final Logger log = LoggerFactory.getLogger(MMissionQueryService.class);

    private final MMissionRepository mMissionRepository;

    private final MMissionMapper mMissionMapper;

    public MMissionQueryService(MMissionRepository mMissionRepository, MMissionMapper mMissionMapper) {
        this.mMissionRepository = mMissionRepository;
        this.mMissionMapper = mMissionMapper;
    }

    /**
     * Return a {@link List} of {@link MMissionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMissionDTO> findByCriteria(MMissionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMission> specification = createSpecification(criteria);
        return mMissionMapper.toDto(mMissionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMissionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMissionDTO> findByCriteria(MMissionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMission> specification = createSpecification(criteria);
        return mMissionRepository.findAll(specification, page)
            .map(mMissionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMissionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMission> specification = createSpecification(criteria);
        return mMissionRepository.count(specification);
    }

    /**
     * Function to convert MMissionCriteria to a {@link Specification}.
     */
    private Specification<MMission> createSpecification(MMissionCriteria criteria) {
        Specification<MMission> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMission_.id));
            }
            if (criteria.getTerm() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTerm(), MMission_.term));
            }
            if (criteria.getRoundNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRoundNum(), MMission_.roundNum));
            }
            if (criteria.getMissionType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMissionType(), MMission_.missionType));
            }
            if (criteria.getAbsolute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAbsolute(), MMission_.absolute));
            }
            if (criteria.getParam1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParam1(), MMission_.param1));
            }
            if (criteria.getParam2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParam2(), MMission_.param2));
            }
            if (criteria.getParam3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParam3(), MMission_.param3));
            }
            if (criteria.getParam4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParam4(), MMission_.param4));
            }
            if (criteria.getParam5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParam5(), MMission_.param5));
            }
            if (criteria.getTrigger() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrigger(), MMission_.trigger));
            }
            if (criteria.getTriggerCondition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerCondition(), MMission_.triggerCondition));
            }
            if (criteria.getRewardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRewardId(), MMission_.rewardId));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MMission_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MMission_.endAt));
            }
            if (criteria.getLink() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLink(), MMission_.link));
            }
            if (criteria.getPickup() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPickup(), MMission_.pickup));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MMission_.orderNum));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MMission_.id, JoinType.LEFT).get(MMissionReward_.id)));
            }
            if (criteria.getMAchievementId() != null) {
                specification = specification.and(buildSpecification(criteria.getMAchievementId(),
                    root -> root.join(MMission_.mAchievements, JoinType.LEFT).get(MAchievement_.id)));
            }
        }
        return specification;
    }
}
