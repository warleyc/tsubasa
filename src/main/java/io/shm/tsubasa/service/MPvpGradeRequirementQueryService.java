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

import io.shm.tsubasa.domain.MPvpGradeRequirement;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPvpGradeRequirementRepository;
import io.shm.tsubasa.service.dto.MPvpGradeRequirementCriteria;
import io.shm.tsubasa.service.dto.MPvpGradeRequirementDTO;
import io.shm.tsubasa.service.mapper.MPvpGradeRequirementMapper;

/**
 * Service for executing complex queries for {@link MPvpGradeRequirement} entities in the database.
 * The main input is a {@link MPvpGradeRequirementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPvpGradeRequirementDTO} or a {@link Page} of {@link MPvpGradeRequirementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPvpGradeRequirementQueryService extends QueryService<MPvpGradeRequirement> {

    private final Logger log = LoggerFactory.getLogger(MPvpGradeRequirementQueryService.class);

    private final MPvpGradeRequirementRepository mPvpGradeRequirementRepository;

    private final MPvpGradeRequirementMapper mPvpGradeRequirementMapper;

    public MPvpGradeRequirementQueryService(MPvpGradeRequirementRepository mPvpGradeRequirementRepository, MPvpGradeRequirementMapper mPvpGradeRequirementMapper) {
        this.mPvpGradeRequirementRepository = mPvpGradeRequirementRepository;
        this.mPvpGradeRequirementMapper = mPvpGradeRequirementMapper;
    }

    /**
     * Return a {@link List} of {@link MPvpGradeRequirementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPvpGradeRequirementDTO> findByCriteria(MPvpGradeRequirementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPvpGradeRequirement> specification = createSpecification(criteria);
        return mPvpGradeRequirementMapper.toDto(mPvpGradeRequirementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPvpGradeRequirementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpGradeRequirementDTO> findByCriteria(MPvpGradeRequirementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPvpGradeRequirement> specification = createSpecification(criteria);
        return mPvpGradeRequirementRepository.findAll(specification, page)
            .map(mPvpGradeRequirementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPvpGradeRequirementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPvpGradeRequirement> specification = createSpecification(criteria);
        return mPvpGradeRequirementRepository.count(specification);
    }

    /**
     * Function to convert MPvpGradeRequirementCriteria to a {@link Specification}.
     */
    private Specification<MPvpGradeRequirement> createSpecification(MPvpGradeRequirementCriteria criteria) {
        Specification<MPvpGradeRequirement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPvpGradeRequirement_.id));
            }
            if (criteria.getTargetType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetType(), MPvpGradeRequirement_.targetType));
            }
            if (criteria.getTargetWave() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetWave(), MPvpGradeRequirement_.targetWave));
            }
            if (criteria.getTargetLowerGrade() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetLowerGrade(), MPvpGradeRequirement_.targetLowerGrade));
            }
            if (criteria.getTargetPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetPoint(), MPvpGradeRequirement_.targetPoint));
            }
        }
        return specification;
    }
}
