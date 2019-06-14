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

import io.shm.tsubasa.domain.MRecommendFormationFilterElement;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MRecommendFormationFilterElementRepository;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterElementCriteria;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterElementDTO;
import io.shm.tsubasa.service.mapper.MRecommendFormationFilterElementMapper;

/**
 * Service for executing complex queries for {@link MRecommendFormationFilterElement} entities in the database.
 * The main input is a {@link MRecommendFormationFilterElementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MRecommendFormationFilterElementDTO} or a {@link Page} of {@link MRecommendFormationFilterElementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MRecommendFormationFilterElementQueryService extends QueryService<MRecommendFormationFilterElement> {

    private final Logger log = LoggerFactory.getLogger(MRecommendFormationFilterElementQueryService.class);

    private final MRecommendFormationFilterElementRepository mRecommendFormationFilterElementRepository;

    private final MRecommendFormationFilterElementMapper mRecommendFormationFilterElementMapper;

    public MRecommendFormationFilterElementQueryService(MRecommendFormationFilterElementRepository mRecommendFormationFilterElementRepository, MRecommendFormationFilterElementMapper mRecommendFormationFilterElementMapper) {
        this.mRecommendFormationFilterElementRepository = mRecommendFormationFilterElementRepository;
        this.mRecommendFormationFilterElementMapper = mRecommendFormationFilterElementMapper;
    }

    /**
     * Return a {@link List} of {@link MRecommendFormationFilterElementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MRecommendFormationFilterElementDTO> findByCriteria(MRecommendFormationFilterElementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MRecommendFormationFilterElement> specification = createSpecification(criteria);
        return mRecommendFormationFilterElementMapper.toDto(mRecommendFormationFilterElementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MRecommendFormationFilterElementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MRecommendFormationFilterElementDTO> findByCriteria(MRecommendFormationFilterElementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MRecommendFormationFilterElement> specification = createSpecification(criteria);
        return mRecommendFormationFilterElementRepository.findAll(specification, page)
            .map(mRecommendFormationFilterElementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MRecommendFormationFilterElementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MRecommendFormationFilterElement> specification = createSpecification(criteria);
        return mRecommendFormationFilterElementRepository.count(specification);
    }

    /**
     * Function to convert MRecommendFormationFilterElementCriteria to a {@link Specification}.
     */
    private Specification<MRecommendFormationFilterElement> createSpecification(MRecommendFormationFilterElementCriteria criteria) {
        Specification<MRecommendFormationFilterElement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MRecommendFormationFilterElement_.id));
            }
            if (criteria.getFilterType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFilterType(), MRecommendFormationFilterElement_.filterType));
            }
            if (criteria.getElementId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getElementId(), MRecommendFormationFilterElement_.elementId));
            }
        }
        return specification;
    }
}
