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

import io.shm.tsubasa.domain.MGachaRenditionSwipeIcon;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGachaRenditionSwipeIconRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionSwipeIconCriteria;
import io.shm.tsubasa.service.dto.MGachaRenditionSwipeIconDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionSwipeIconMapper;

/**
 * Service for executing complex queries for {@link MGachaRenditionSwipeIcon} entities in the database.
 * The main input is a {@link MGachaRenditionSwipeIconCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGachaRenditionSwipeIconDTO} or a {@link Page} of {@link MGachaRenditionSwipeIconDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGachaRenditionSwipeIconQueryService extends QueryService<MGachaRenditionSwipeIcon> {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionSwipeIconQueryService.class);

    private final MGachaRenditionSwipeIconRepository mGachaRenditionSwipeIconRepository;

    private final MGachaRenditionSwipeIconMapper mGachaRenditionSwipeIconMapper;

    public MGachaRenditionSwipeIconQueryService(MGachaRenditionSwipeIconRepository mGachaRenditionSwipeIconRepository, MGachaRenditionSwipeIconMapper mGachaRenditionSwipeIconMapper) {
        this.mGachaRenditionSwipeIconRepository = mGachaRenditionSwipeIconRepository;
        this.mGachaRenditionSwipeIconMapper = mGachaRenditionSwipeIconMapper;
    }

    /**
     * Return a {@link List} of {@link MGachaRenditionSwipeIconDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGachaRenditionSwipeIconDTO> findByCriteria(MGachaRenditionSwipeIconCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGachaRenditionSwipeIcon> specification = createSpecification(criteria);
        return mGachaRenditionSwipeIconMapper.toDto(mGachaRenditionSwipeIconRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGachaRenditionSwipeIconDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionSwipeIconDTO> findByCriteria(MGachaRenditionSwipeIconCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGachaRenditionSwipeIcon> specification = createSpecification(criteria);
        return mGachaRenditionSwipeIconRepository.findAll(specification, page)
            .map(mGachaRenditionSwipeIconMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGachaRenditionSwipeIconCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGachaRenditionSwipeIcon> specification = createSpecification(criteria);
        return mGachaRenditionSwipeIconRepository.count(specification);
    }

    /**
     * Function to convert MGachaRenditionSwipeIconCriteria to a {@link Specification}.
     */
    private Specification<MGachaRenditionSwipeIcon> createSpecification(MGachaRenditionSwipeIconCriteria criteria) {
        Specification<MGachaRenditionSwipeIcon> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGachaRenditionSwipeIcon_.id));
            }
            if (criteria.getIsSsr() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSsr(), MGachaRenditionSwipeIcon_.isSsr));
            }
            if (criteria.getIsROrLess() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsROrLess(), MGachaRenditionSwipeIcon_.isROrLess));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MGachaRenditionSwipeIcon_.weight));
            }
        }
        return specification;
    }
}
