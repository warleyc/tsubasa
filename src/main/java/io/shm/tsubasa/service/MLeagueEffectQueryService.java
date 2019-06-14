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

import io.shm.tsubasa.domain.MLeagueEffect;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLeagueEffectRepository;
import io.shm.tsubasa.service.dto.MLeagueEffectCriteria;
import io.shm.tsubasa.service.dto.MLeagueEffectDTO;
import io.shm.tsubasa.service.mapper.MLeagueEffectMapper;

/**
 * Service for executing complex queries for {@link MLeagueEffect} entities in the database.
 * The main input is a {@link MLeagueEffectCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLeagueEffectDTO} or a {@link Page} of {@link MLeagueEffectDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLeagueEffectQueryService extends QueryService<MLeagueEffect> {

    private final Logger log = LoggerFactory.getLogger(MLeagueEffectQueryService.class);

    private final MLeagueEffectRepository mLeagueEffectRepository;

    private final MLeagueEffectMapper mLeagueEffectMapper;

    public MLeagueEffectQueryService(MLeagueEffectRepository mLeagueEffectRepository, MLeagueEffectMapper mLeagueEffectMapper) {
        this.mLeagueEffectRepository = mLeagueEffectRepository;
        this.mLeagueEffectMapper = mLeagueEffectMapper;
    }

    /**
     * Return a {@link List} of {@link MLeagueEffectDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLeagueEffectDTO> findByCriteria(MLeagueEffectCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLeagueEffect> specification = createSpecification(criteria);
        return mLeagueEffectMapper.toDto(mLeagueEffectRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLeagueEffectDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueEffectDTO> findByCriteria(MLeagueEffectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLeagueEffect> specification = createSpecification(criteria);
        return mLeagueEffectRepository.findAll(specification, page)
            .map(mLeagueEffectMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLeagueEffectCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLeagueEffect> specification = createSpecification(criteria);
        return mLeagueEffectRepository.count(specification);
    }

    /**
     * Function to convert MLeagueEffectCriteria to a {@link Specification}.
     */
    private Specification<MLeagueEffect> createSpecification(MLeagueEffectCriteria criteria) {
        Specification<MLeagueEffect> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLeagueEffect_.id));
            }
            if (criteria.getEffectType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectType(), MLeagueEffect_.effectType));
            }
            if (criteria.getLeagueHierarchy() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLeagueHierarchy(), MLeagueEffect_.leagueHierarchy));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), MLeagueEffect_.rate));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), MLeagueEffect_.price));
            }
        }
        return specification;
    }
}
