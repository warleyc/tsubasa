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

import io.shm.tsubasa.domain.MPvpGrade;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MPvpGradeRepository;
import io.shm.tsubasa.service.dto.MPvpGradeCriteria;
import io.shm.tsubasa.service.dto.MPvpGradeDTO;
import io.shm.tsubasa.service.mapper.MPvpGradeMapper;

/**
 * Service for executing complex queries for {@link MPvpGrade} entities in the database.
 * The main input is a {@link MPvpGradeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MPvpGradeDTO} or a {@link Page} of {@link MPvpGradeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MPvpGradeQueryService extends QueryService<MPvpGrade> {

    private final Logger log = LoggerFactory.getLogger(MPvpGradeQueryService.class);

    private final MPvpGradeRepository mPvpGradeRepository;

    private final MPvpGradeMapper mPvpGradeMapper;

    public MPvpGradeQueryService(MPvpGradeRepository mPvpGradeRepository, MPvpGradeMapper mPvpGradeMapper) {
        this.mPvpGradeRepository = mPvpGradeRepository;
        this.mPvpGradeMapper = mPvpGradeMapper;
    }

    /**
     * Return a {@link List} of {@link MPvpGradeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MPvpGradeDTO> findByCriteria(MPvpGradeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MPvpGrade> specification = createSpecification(criteria);
        return mPvpGradeMapper.toDto(mPvpGradeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MPvpGradeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpGradeDTO> findByCriteria(MPvpGradeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MPvpGrade> specification = createSpecification(criteria);
        return mPvpGradeRepository.findAll(specification, page)
            .map(mPvpGradeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MPvpGradeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MPvpGrade> specification = createSpecification(criteria);
        return mPvpGradeRepository.count(specification);
    }

    /**
     * Function to convert MPvpGradeCriteria to a {@link Specification}.
     */
    private Specification<MPvpGrade> createSpecification(MPvpGradeCriteria criteria) {
        Specification<MPvpGrade> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MPvpGrade_.id));
            }
            if (criteria.getWaveId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWaveId(), MPvpGrade_.waveId));
            }
            if (criteria.getGrade() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrade(), MPvpGrade_.grade));
            }
            if (criteria.getIsHigher() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsHigher(), MPvpGrade_.isHigher));
            }
            if (criteria.getIsLower() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsLower(), MPvpGrade_.isLower));
            }
            if (criteria.getLook() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLook(), MPvpGrade_.look));
            }
            if (criteria.getUpRequirementId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpRequirementId(), MPvpGrade_.upRequirementId));
            }
            if (criteria.getDownRequirementId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDownRequirementId(), MPvpGrade_.downRequirementId));
            }
            if (criteria.getWinPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWinPoint(), MPvpGrade_.winPoint));
            }
            if (criteria.getLosePoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLosePoint(), MPvpGrade_.losePoint));
            }
            if (criteria.getTotalParameterRangeUpper() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalParameterRangeUpper(), MPvpGrade_.totalParameterRangeUpper));
            }
            if (criteria.getTotalParameterRangeLower() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalParameterRangeLower(), MPvpGrade_.totalParameterRangeLower));
            }
        }
        return specification;
    }
}
