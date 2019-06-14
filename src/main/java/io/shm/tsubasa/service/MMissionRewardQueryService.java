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

import io.shm.tsubasa.domain.MMissionReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMissionRewardRepository;
import io.shm.tsubasa.service.dto.MMissionRewardCriteria;
import io.shm.tsubasa.service.dto.MMissionRewardDTO;
import io.shm.tsubasa.service.mapper.MMissionRewardMapper;

/**
 * Service for executing complex queries for {@link MMissionReward} entities in the database.
 * The main input is a {@link MMissionRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMissionRewardDTO} or a {@link Page} of {@link MMissionRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMissionRewardQueryService extends QueryService<MMissionReward> {

    private final Logger log = LoggerFactory.getLogger(MMissionRewardQueryService.class);

    private final MMissionRewardRepository mMissionRewardRepository;

    private final MMissionRewardMapper mMissionRewardMapper;

    public MMissionRewardQueryService(MMissionRewardRepository mMissionRewardRepository, MMissionRewardMapper mMissionRewardMapper) {
        this.mMissionRewardRepository = mMissionRewardRepository;
        this.mMissionRewardMapper = mMissionRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MMissionRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMissionRewardDTO> findByCriteria(MMissionRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMissionReward> specification = createSpecification(criteria);
        return mMissionRewardMapper.toDto(mMissionRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMissionRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMissionRewardDTO> findByCriteria(MMissionRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMissionReward> specification = createSpecification(criteria);
        return mMissionRewardRepository.findAll(specification, page)
            .map(mMissionRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMissionRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMissionReward> specification = createSpecification(criteria);
        return mMissionRewardRepository.count(specification);
    }

    /**
     * Function to convert MMissionRewardCriteria to a {@link Specification}.
     */
    private Specification<MMissionReward> createSpecification(MMissionRewardCriteria criteria) {
        Specification<MMissionReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMissionReward_.id));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MMissionReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MMissionReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MMissionReward_.contentAmount));
            }
            if (criteria.getMGuildMissionId() != null) {
                specification = specification.and(buildSpecification(criteria.getMGuildMissionId(),
                    root -> root.join(MMissionReward_.mGuildMissions, JoinType.LEFT).get(MGuildMission_.id)));
            }
            if (criteria.getMMissionId() != null) {
                specification = specification.and(buildSpecification(criteria.getMMissionId(),
                    root -> root.join(MMissionReward_.mMissions, JoinType.LEFT).get(MMission_.id)));
            }
        }
        return specification;
    }
}
