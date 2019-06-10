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

import io.shm.tsubasa.domain.MGachaRenditionKicker;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionKickerRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionKickerCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionKickerDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionKickerMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionKicker} entities in the database.
 * The main input is a {@link MGachaRenditionKickerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionKickerDTO} or a {@link Page} of {@link MGachaRenditionKickerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionKickerQueryService extends QueryService<MGachaRenditionKicker> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionKickerQueryService.class);

    private final MGachaRenditionKickerRepository mGachaRenditionKickerRepository;

    private final MGachaRenditionKickerMapper mGachaRenditionKickerMapper;

    public MGachaRenditionKickerQueryService(MGachaRenditionKickerRepository mGachaRenditionKickerRepository, MGachaRenditionKickerMapper mGachaRenditionKickerMapper) {
        this.mGachaRenditionKickerRepository = mGachaRenditionKickerRepository;
        this.mGachaRenditionKickerMapper = mGachaRenditionKickerMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionKickerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionKickerDTO> findByCriteria(MGachaRenditionKickerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionKicker> specification = createSpecification(criteria);
        return mGachaRenditionKickerMapper.toDto(mGachaRenditionKickerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionKickerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionKickerDTO> findByCriteria(MGachaRenditionKickerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionKicker> specification = createSpecification(criteria);
        return mGachaRenditionKickerRepository.findAll(specification, page)
            .map(mGachaRenditionKickerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionKickerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionKicker> specification = createSpecification(criteria);
        return mGachaRenditionKickerRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionKickerCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionKicker> createSpecification(MGachaRenditionKickerCriteria criteria) {
        Specification<MGachaRenditionKicker> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionKicker_.id));
            }
            if (criteria.getRenditionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRenditionId(), MGachaRenditionKicker_.renditionId));
            }
            if (criteria.getIsManySsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsManySsr(), MGachaRenditionKicker_.isManySsr));
            }
            if (criteria.getIsSsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSsr(), MGachaRenditionKicker_.isSsr));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionKicker_.weight));
            }
            if (criteria.getLeftModelId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLeftModelId(), MGachaRenditionKicker_.leftModelId));
            }
            if (criteria.getLeftUniformUpId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLeftUniformUpId(), MGachaRenditionKicker_.leftUniformUpId));
            }
            if (criteria.getLeftUniformBottomId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLeftUniformBottomId(), MGachaRenditionKicker_.leftUniformBottomId));
            }
            if (criteria.getLeftUniformNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLeftUniformNumber(), MGachaRenditionKicker_.leftUniformNumber));
            }
            if (criteria.getRightModelId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRightModelId(), MGachaRenditionKicker_.rightModelId));
            }
            if (criteria.getRightUniformUpId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRightUniformUpId(), MGachaRenditionKicker_.rightUniformUpId));
            }
            if (criteria.getRightUniformBottomId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRightUniformBottomId(), MGachaRenditionKicker_.rightUniformBottomId));
            }
            if (criteria.getRightUniformNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRightUniformNumber(), MGachaRenditionKicker_.rightUniformNumber));
            }
            if (criteria.getKickerId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getKickerId(), MGachaRenditionKicker_.kickerId));
            }
        }
        return specification;
    }
}
