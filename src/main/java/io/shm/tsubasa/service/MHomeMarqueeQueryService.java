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

import io.shm.tsubasa.domain.MHomeMarquee;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MHomeMarqueeRepository;
import io.shm.tsubasa.service.dto.MHomeMarqueeCriteria;
import io.shm.tsubasa.service.dto.MHomeMarqueeDTO;
import io.shm.tsubasa.service.mapper.MHomeMarqueeMapper;

/**
 * Service for executing complex queries for {@link MHomeMarquee} entities in the database.
 * The main input is a {@link MHomeMarqueeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MHomeMarqueeDTO} or a {@link Page} of {@link MHomeMarqueeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MHomeMarqueeQueryService extends QueryService<MHomeMarquee> {

    private final Logger log = LoggerFactory.getLogger(MHomeMarqueeQueryService.class);

    private final MHomeMarqueeRepository mHomeMarqueeRepository;

    private final MHomeMarqueeMapper mHomeMarqueeMapper;

    public MHomeMarqueeQueryService(MHomeMarqueeRepository mHomeMarqueeRepository, MHomeMarqueeMapper mHomeMarqueeMapper) {
        this.mHomeMarqueeRepository = mHomeMarqueeRepository;
        this.mHomeMarqueeMapper = mHomeMarqueeMapper;
    }

    /**
     * Return a {@link List} of {@link MHomeMarqueeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MHomeMarqueeDTO> findByCriteria(MHomeMarqueeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MHomeMarquee> specification = createSpecification(criteria);
        return mHomeMarqueeMapper.toDto(mHomeMarqueeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MHomeMarqueeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MHomeMarqueeDTO> findByCriteria(MHomeMarqueeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MHomeMarquee> specification = createSpecification(criteria);
        return mHomeMarqueeRepository.findAll(specification, page)
            .map(mHomeMarqueeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MHomeMarqueeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MHomeMarquee> specification = createSpecification(criteria);
        return mHomeMarqueeRepository.count(specification);
    }

    /**
     * Function to convert MHomeMarqueeCriteria to a {@link Specification}.
     */
    private Specification<MHomeMarquee> createSpecification(MHomeMarqueeCriteria criteria) {
        Specification<MHomeMarquee> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MHomeMarquee_.id));
            }
            if (criteria.getPriority() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriority(), MHomeMarquee_.priority));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MHomeMarquee_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MHomeMarquee_.endAt));
            }
        }
        return specification;
    }
}
