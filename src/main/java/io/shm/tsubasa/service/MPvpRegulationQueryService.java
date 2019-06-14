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

import io.shm.tsubasa.domain.MPvpRegulation;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPvpRegulationRepository;
import io.shm.tsubasa.service.dto.MPvpRegulationCriteria;
import io.shm.tsubasa.service.dto.MPvpRegulationDTO;
import io.shm.tsubasa.service.mapper.MPvpRegulationMapper;

/**
 * Service for executing complex queries for {@link MPvpRegulation} entities in the database.
 * The main input is a {@link MPvpRegulationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPvpRegulationDTO} or a {@link Page} of {@link MPvpRegulationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPvpRegulationQueryService extends QueryService<MPvpRegulation> {

    private final Logger log = LoggerFactory.getLogger(MPvpRegulationQueryService.class);

    private final MPvpRegulationRepository mPvpRegulationRepository;

    private final MPvpRegulationMapper mPvpRegulationMapper;

    public MPvpRegulationQueryService(MPvpRegulationRepository mPvpRegulationRepository, MPvpRegulationMapper mPvpRegulationMapper) {
        this.mPvpRegulationRepository = mPvpRegulationRepository;
        this.mPvpRegulationMapper = mPvpRegulationMapper;
    }

    /**
     * Return a {@link List} of {@link MPvpRegulationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPvpRegulationDTO> findByCriteria(MPvpRegulationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPvpRegulation> specification = createSpecification(criteria);
        return mPvpRegulationMapper.toDto(mPvpRegulationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPvpRegulationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpRegulationDTO> findByCriteria(MPvpRegulationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPvpRegulation> specification = createSpecification(criteria);
        return mPvpRegulationRepository.findAll(specification, page)
            .map(mPvpRegulationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPvpRegulationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPvpRegulation> specification = createSpecification(criteria);
        return mPvpRegulationRepository.count(specification);
    }

    /**
     * Function to convert MPvpRegulationCriteria to a {@link Specification}.
     */
    private Specification<MPvpRegulation> createSpecification(MPvpRegulationCriteria criteria) {
        Specification<MPvpRegulation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPvpRegulation_.id));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MPvpRegulation_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MPvpRegulation_.endAt));
            }
            if (criteria.getMatchOptionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchOptionId(), MPvpRegulation_.matchOptionId));
            }
            if (criteria.getDeckConditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckConditionId(), MPvpRegulation_.deckConditionId));
            }
            if (criteria.getRuleTutorialId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRuleTutorialId(), MPvpRegulation_.ruleTutorialId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MPvpRegulation_.id, JoinType.LEFT).get(MMatchOption_.id)));
            }
        }
        return specification;
    }
}
