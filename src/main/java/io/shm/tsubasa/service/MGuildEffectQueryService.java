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

import io.shm.tsubasa.domain.MGuildEffect;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuildEffectRepository;
import io.shm.tsubasa.service.dto.MGuildEffectCriteria;
import io.shm.tsubasa.service.dto.MGuildEffectDTO;
import io.shm.tsubasa.service.mapper.MGuildEffectMapper;

/**
 * Service for executing complex queries for {@link MGuildEffect} entities in the database.
 * The main input is a {@link MGuildEffectCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuildEffectDTO} or a {@link Page} of {@link MGuildEffectDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuildEffectQueryService extends QueryService<MGuildEffect> {

    private final Logger log = LoggerFactory.getLogger(MGuildEffectQueryService.class);

    private final MGuildEffectRepository mGuildEffectRepository;

    private final MGuildEffectMapper mGuildEffectMapper;

    public MGuildEffectQueryService(MGuildEffectRepository mGuildEffectRepository, MGuildEffectMapper mGuildEffectMapper) {
        this.mGuildEffectRepository = mGuildEffectRepository;
        this.mGuildEffectMapper = mGuildEffectMapper;
    }

    /**
     * Return a {@link List} of {@link MGuildEffectDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuildEffectDTO> findByCriteria(MGuildEffectCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuildEffect> specification = createSpecification(criteria);
        return mGuildEffectMapper.toDto(mGuildEffectRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuildEffectDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildEffectDTO> findByCriteria(MGuildEffectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuildEffect> specification = createSpecification(criteria);
        return mGuildEffectRepository.findAll(specification, page)
            .map(mGuildEffectMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuildEffectCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuildEffect> specification = createSpecification(criteria);
        return mGuildEffectRepository.count(specification);
    }

    /**
     * Function to convert MGuildEffectCriteria to a {@link Specification}.
     */
    private Specification<MGuildEffect> createSpecification(MGuildEffectCriteria criteria) {
        Specification<MGuildEffect> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuildEffect_.id));
            }
            if (criteria.getEffectType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectType(), MGuildEffect_.effectType));
            }
            if (criteria.getEffectId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectId(), MGuildEffect_.effectId));
            }
        }
        return specification;
    }
}
