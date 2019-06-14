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

import io.shm.tsubasa.domain.MLuck;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLuckRepository;
import io.shm.tsubasa.service.dto.MLuckCriteria;
import io.shm.tsubasa.service.dto.MLuckDTO;
import io.shm.tsubasa.service.mapper.MLuckMapper;

/**
 * Service for executing complex queries for {@link MLuck} entities in the database.
 * The main input is a {@link MLuckCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLuckDTO} or a {@link Page} of {@link MLuckDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLuckQueryService extends QueryService<MLuck> {

    private final Logger log = LoggerFactory.getLogger(MLuckQueryService.class);

    private final MLuckRepository mLuckRepository;

    private final MLuckMapper mLuckMapper;

    public MLuckQueryService(MLuckRepository mLuckRepository, MLuckMapper mLuckMapper) {
        this.mLuckRepository = mLuckRepository;
        this.mLuckMapper = mLuckMapper;
    }

    /**
     * Return a {@link List} of {@link MLuckDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLuckDTO> findByCriteria(MLuckCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLuck> specification = createSpecification(criteria);
        return mLuckMapper.toDto(mLuckRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLuckDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLuckDTO> findByCriteria(MLuckCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLuck> specification = createSpecification(criteria);
        return mLuckRepository.findAll(specification, page)
            .map(mLuckMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLuckCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLuck> specification = createSpecification(criteria);
        return mLuckRepository.count(specification);
    }

    /**
     * Function to convert MLuckCriteria to a {@link Specification}.
     */
    private Specification<MLuck> createSpecification(MLuckCriteria criteria) {
        Specification<MLuck> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLuck_.id));
            }
            if (criteria.getTargetAttribute() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetAttribute(), MLuck_.targetAttribute));
            }
            if (criteria.getTargetCharacterGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharacterGroupId(), MLuck_.targetCharacterGroupId));
            }
            if (criteria.getTargetTeamGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetTeamGroupId(), MLuck_.targetTeamGroupId));
            }
            if (criteria.getTargetNationalityGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetNationalityGroupId(), MLuck_.targetNationalityGroupId));
            }
            if (criteria.getLuckRateGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLuckRateGroupId(), MLuck_.luckRateGroupId));
            }
        }
        return specification;
    }
}
