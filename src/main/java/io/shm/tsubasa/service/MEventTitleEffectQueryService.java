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

import io.shm.tsubasa.domain.MEventTitleEffect;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MEventTitleEffectRepository;
import io.shm.tsubasa.service.dto.MEventTitleEffectCriteria;
import io.shm.tsubasa.service.dto.MEventTitleEffectDTO;
import io.shm.tsubasa.service.mapper.MEventTitleEffectMapper;

/**
 * Service for executing complex queries for {@link MEventTitleEffect} entities in the database.
 * The main input is a {@link MEventTitleEffectCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MEventTitleEffectDTO} or a {@link Page} of {@link MEventTitleEffectDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MEventTitleEffectQueryService extends QueryService<MEventTitleEffect> {

    private final Logger log = LoggerFactory.getLogger(MEventTitleEffectQueryService.class);

    private final MEventTitleEffectRepository mEventTitleEffectRepository;

    private final MEventTitleEffectMapper mEventTitleEffectMapper;

    public MEventTitleEffectQueryService(MEventTitleEffectRepository mEventTitleEffectRepository, MEventTitleEffectMapper mEventTitleEffectMapper) {
        this.mEventTitleEffectRepository = mEventTitleEffectRepository;
        this.mEventTitleEffectMapper = mEventTitleEffectMapper;
    }

    /**
     * Return a {@link List} of {@link MEventTitleEffectDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MEventTitleEffectDTO> findByCriteria(MEventTitleEffectCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MEventTitleEffect> specification = createSpecification(criteria);
        return mEventTitleEffectMapper.toDto(mEventTitleEffectRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MEventTitleEffectDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MEventTitleEffectDTO> findByCriteria(MEventTitleEffectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MEventTitleEffect> specification = createSpecification(criteria);
        return mEventTitleEffectRepository.findAll(specification, page)
            .map(mEventTitleEffectMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MEventTitleEffectCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MEventTitleEffect> specification = createSpecification(criteria);
        return mEventTitleEffectRepository.count(specification);
    }

    /**
     * Function to convert MEventTitleEffectCriteria to a {@link Specification}.
     */
    private Specification<MEventTitleEffect> createSpecification(MEventTitleEffectCriteria criteria) {
        Specification<MEventTitleEffect> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MEventTitleEffect_.id));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MEventTitleEffect_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MEventTitleEffect_.endAt));
            }
        }
        return specification;
    }
}
