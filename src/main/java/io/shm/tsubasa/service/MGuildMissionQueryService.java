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

import io.shm.tsubasa.domain.MGuildMission;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuildMissionRepository;
import io.shm.tsubasa.service.dto.MGuildMissionCriteria;
import io.shm.tsubasa.service.dto.MGuildMissionDTO;
import io.shm.tsubasa.service.mapper.MGuildMissionMapper;

/**
 * Service for executing complex queries for {@link MGuildMission} entities in the database.
 * The main input is a {@link MGuildMissionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuildMissionDTO} or a {@link Page} of {@link MGuildMissionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuildMissionQueryService extends QueryService<MGuildMission> {

    private final Logger log = LoggerFactory.getLogger(MGuildMissionQueryService.class);

    private final MGuildMissionRepository mGuildMissionRepository;

    private final MGuildMissionMapper mGuildMissionMapper;

    public MGuildMissionQueryService(MGuildMissionRepository mGuildMissionRepository, MGuildMissionMapper mGuildMissionMapper) {
        this.mGuildMissionRepository = mGuildMissionRepository;
        this.mGuildMissionMapper = mGuildMissionMapper;
    }

    /**
     * Return a {@link List} of {@link MGuildMissionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuildMissionDTO> findByCriteria(MGuildMissionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuildMission> specification = createSpecification(criteria);
        return mGuildMissionMapper.toDto(mGuildMissionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuildMissionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildMissionDTO> findByCriteria(MGuildMissionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuildMission> specification = createSpecification(criteria);
        return mGuildMissionRepository.findAll(specification, page)
            .map(mGuildMissionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuildMissionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuildMission> specification = createSpecification(criteria);
        return mGuildMissionRepository.count(specification);
    }

    /**
     * Function to convert MGuildMissionCriteria to a {@link Specification}.
     */
    private Specification<MGuildMission> createSpecification(MGuildMissionCriteria criteria) {
        Specification<MGuildMission> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuildMission_.id));
            }
            if (criteria.getTerm() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTerm(), MGuildMission_.term));
            }
            if (criteria.getMissionType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMissionType(), MGuildMission_.missionType));
            }
            if (criteria.getParam1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParam1(), MGuildMission_.param1));
            }
            if (criteria.getRewardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRewardId(), MGuildMission_.rewardId));
            }
            if (criteria.getLink() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLink(), MGuildMission_.link));
            }
            if (criteria.getPickup() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPickup(), MGuildMission_.pickup));
            }
            if (criteria.getTriggerMissionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerMissionId(), MGuildMission_.triggerMissionId));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MGuildMission_.orderNum));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MGuildMission_.id, JoinType.LEFT).get(MMissionReward_.id)));
            }
        }
        return specification;
    }
}
