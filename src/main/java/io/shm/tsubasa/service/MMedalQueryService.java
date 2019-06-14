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

import io.shm.tsubasa.domain.MMedal;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMedalRepository;
import io.shm.tsubasa.service.dto.MMedalCriteria;
import io.shm.tsubasa.service.dto.MMedalDTO;
import io.shm.tsubasa.service.mapper.MMedalMapper;

/**
 * Service for executing complex queries for {@link MMedal} entities in the database.
 * The main input is a {@link MMedalCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMedalDTO} or a {@link Page} of {@link MMedalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMedalQueryService extends QueryService<MMedal> {

    private final Logger log = LoggerFactory.getLogger(MMedalQueryService.class);

    private final MMedalRepository mMedalRepository;

    private final MMedalMapper mMedalMapper;

    public MMedalQueryService(MMedalRepository mMedalRepository, MMedalMapper mMedalMapper) {
        this.mMedalRepository = mMedalRepository;
        this.mMedalMapper = mMedalMapper;
    }

    /**
     * Return a {@link List} of {@link MMedalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMedalDTO> findByCriteria(MMedalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMedal> specification = createSpecification(criteria);
        return mMedalMapper.toDto(mMedalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMedalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMedalDTO> findByCriteria(MMedalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMedal> specification = createSpecification(criteria);
        return mMedalRepository.findAll(specification, page)
            .map(mMedalMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMedalCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMedal> specification = createSpecification(criteria);
        return mMedalRepository.count(specification);
    }

    /**
     * Function to convert MMedalCriteria to a {@link Specification}.
     */
    private Specification<MMedal> createSpecification(MMedalCriteria criteria) {
        Specification<MMedal> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMedal_.id));
            }
            if (criteria.getMedalType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMedalType(), MMedal_.medalType));
            }
            if (criteria.getMaxAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxAmount(), MMedal_.maxAmount));
            }
        }
        return specification;
    }
}
