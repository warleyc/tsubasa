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

import io.shm.tsubasa.domain.MBadge;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MBadgeRepository;
import io.shm.tsubasa.service.dto.MBadgeCriteria;
import io.shm.tsubasa.service.dto.MBadgeDTO;
import io.shm.tsubasa.service.mapper.MBadgeMapper;

/**
 * Service for executing complex queries for {@link MBadge} entities in the database.
 * The main input is a {@link MBadgeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MBadgeDTO} or a {@link Page} of {@link MBadgeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MBadgeQueryService extends QueryService<MBadge> {

    private final Logger log = LoggerFactory.getLogger(MBadgeQueryService.class);

    private final MBadgeRepository mBadgeRepository;

    private final MBadgeMapper mBadgeMapper;

    public MBadgeQueryService(MBadgeRepository mBadgeRepository, MBadgeMapper mBadgeMapper) {
        this.mBadgeRepository = mBadgeRepository;
        this.mBadgeMapper = mBadgeMapper;
    }

    /**
     * Return a {@link List} of {@link MBadgeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MBadgeDTO> findByCriteria(MBadgeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MBadge> specification = createSpecification(criteria);
        return mBadgeMapper.toDto(mBadgeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MBadgeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MBadgeDTO> findByCriteria(MBadgeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MBadge> specification = createSpecification(criteria);
        return mBadgeRepository.findAll(specification, page)
            .map(mBadgeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MBadgeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MBadge> specification = createSpecification(criteria);
        return mBadgeRepository.count(specification);
    }

    /**
     * Function to convert MBadgeCriteria to a {@link Specification}.
     */
    private Specification<MBadge> createSpecification(MBadgeCriteria criteria) {
        Specification<MBadge> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MBadge_.id));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getType(), MBadge_.type));
            }
        }
        return specification;
    }
}
