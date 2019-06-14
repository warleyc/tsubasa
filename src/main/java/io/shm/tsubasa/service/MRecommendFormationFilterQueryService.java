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

import io.shm.tsubasa.domain.MRecommendFormationFilter;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MRecommendFormationFilterRepository;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterCriteria;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterDTO;
import io.shm.tsubasa.service.mapper.MRecommendFormationFilterMapper;

/**
 * Service for executing complex queries for {@link MRecommendFormationFilter} entities in the database.
 * The main input is a {@link MRecommendFormationFilterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MRecommendFormationFilterDTO} or a {@link Page} of {@link MRecommendFormationFilterDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MRecommendFormationFilterQueryService extends QueryService<MRecommendFormationFilter> {

    private final Logger log = LoggerFactory.getLogger(MRecommendFormationFilterQueryService.class);

    private final MRecommendFormationFilterRepository mRecommendFormationFilterRepository;

    private final MRecommendFormationFilterMapper mRecommendFormationFilterMapper;

    public MRecommendFormationFilterQueryService(MRecommendFormationFilterRepository mRecommendFormationFilterRepository, MRecommendFormationFilterMapper mRecommendFormationFilterMapper) {
        this.mRecommendFormationFilterRepository = mRecommendFormationFilterRepository;
        this.mRecommendFormationFilterMapper = mRecommendFormationFilterMapper;
    }

    /**
     * Return a {@link List} of {@link MRecommendFormationFilterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MRecommendFormationFilterDTO> findByCriteria(MRecommendFormationFilterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MRecommendFormationFilter> specification = createSpecification(criteria);
        return mRecommendFormationFilterMapper.toDto(mRecommendFormationFilterRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MRecommendFormationFilterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MRecommendFormationFilterDTO> findByCriteria(MRecommendFormationFilterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MRecommendFormationFilter> specification = createSpecification(criteria);
        return mRecommendFormationFilterRepository.findAll(specification, page)
            .map(mRecommendFormationFilterMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MRecommendFormationFilterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MRecommendFormationFilter> specification = createSpecification(criteria);
        return mRecommendFormationFilterRepository.count(specification);
    }

    /**
     * Function to convert MRecommendFormationFilterCriteria to a {@link Specification}.
     */
    private Specification<MRecommendFormationFilter> createSpecification(MRecommendFormationFilterCriteria criteria) {
        Specification<MRecommendFormationFilter> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MRecommendFormationFilter_.id));
            }
            if (criteria.getFilterType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFilterType(), MRecommendFormationFilter_.filterType));
            }
        }
        return specification;
    }
}
