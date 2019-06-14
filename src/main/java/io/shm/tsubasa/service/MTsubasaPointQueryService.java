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

import io.shm.tsubasa.domain.MTsubasaPoint;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTsubasaPointRepository;
import io.shm.tsubasa.service.dto.MTsubasaPointCriteria;
import io.shm.tsubasa.service.dto.MTsubasaPointDTO;
import io.shm.tsubasa.service.mapper.MTsubasaPointMapper;

/**
 * Service for executing complex queries for {@link MTsubasaPoint} entities in the database.
 * The main input is a {@link MTsubasaPointCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTsubasaPointDTO} or a {@link Page} of {@link MTsubasaPointDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTsubasaPointQueryService extends QueryService<MTsubasaPoint> {

    private final Logger log = LoggerFactory.getLogger(MTsubasaPointQueryService.class);

    private final MTsubasaPointRepository mTsubasaPointRepository;

    private final MTsubasaPointMapper mTsubasaPointMapper;

    public MTsubasaPointQueryService(MTsubasaPointRepository mTsubasaPointRepository, MTsubasaPointMapper mTsubasaPointMapper) {
        this.mTsubasaPointRepository = mTsubasaPointRepository;
        this.mTsubasaPointMapper = mTsubasaPointMapper;
    }

    /**
     * Return a {@link List} of {@link MTsubasaPointDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTsubasaPointDTO> findByCriteria(MTsubasaPointCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTsubasaPoint> specification = createSpecification(criteria);
        return mTsubasaPointMapper.toDto(mTsubasaPointRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTsubasaPointDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTsubasaPointDTO> findByCriteria(MTsubasaPointCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTsubasaPoint> specification = createSpecification(criteria);
        return mTsubasaPointRepository.findAll(specification, page)
            .map(mTsubasaPointMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTsubasaPointCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTsubasaPoint> specification = createSpecification(criteria);
        return mTsubasaPointRepository.count(specification);
    }

    /**
     * Function to convert MTsubasaPointCriteria to a {@link Specification}.
     */
    private Specification<MTsubasaPoint> createSpecification(MTsubasaPointCriteria criteria) {
        Specification<MTsubasaPoint> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTsubasaPoint_.id));
            }
            if (criteria.getMatchType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchType(), MTsubasaPoint_.matchType));
            }
            if (criteria.getPointType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPointType(), MTsubasaPoint_.pointType));
            }
            if (criteria.getCalcType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCalcType(), MTsubasaPoint_.calcType));
            }
            if (criteria.getaValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getaValue(), MTsubasaPoint_.aValue));
            }
            if (criteria.getbValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getbValue(), MTsubasaPoint_.bValue));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MTsubasaPoint_.orderNum));
            }
        }
        return specification;
    }
}
