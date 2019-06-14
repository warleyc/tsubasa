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

import io.shm.tsubasa.domain.MGuildEffectLevel;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuildEffectLevelRepository;
import io.shm.tsubasa.service.dto.MGuildEffectLevelCriteria;
import io.shm.tsubasa.service.dto.MGuildEffectLevelDTO;
import io.shm.tsubasa.service.mapper.MGuildEffectLevelMapper;

/**
 * Service for executing complex queries for {@link MGuildEffectLevel} entities in the database.
 * The main input is a {@link MGuildEffectLevelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuildEffectLevelDTO} or a {@link Page} of {@link MGuildEffectLevelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuildEffectLevelQueryService extends QueryService<MGuildEffectLevel> {

    private final Logger log = LoggerFactory.getLogger(MGuildEffectLevelQueryService.class);

    private final MGuildEffectLevelRepository mGuildEffectLevelRepository;

    private final MGuildEffectLevelMapper mGuildEffectLevelMapper;

    public MGuildEffectLevelQueryService(MGuildEffectLevelRepository mGuildEffectLevelRepository, MGuildEffectLevelMapper mGuildEffectLevelMapper) {
        this.mGuildEffectLevelRepository = mGuildEffectLevelRepository;
        this.mGuildEffectLevelMapper = mGuildEffectLevelMapper;
    }

    /**
     * Return a {@link List} of {@link MGuildEffectLevelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuildEffectLevelDTO> findByCriteria(MGuildEffectLevelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuildEffectLevel> specification = createSpecification(criteria);
        return mGuildEffectLevelMapper.toDto(mGuildEffectLevelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuildEffectLevelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildEffectLevelDTO> findByCriteria(MGuildEffectLevelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuildEffectLevel> specification = createSpecification(criteria);
        return mGuildEffectLevelRepository.findAll(specification, page)
            .map(mGuildEffectLevelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuildEffectLevelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuildEffectLevel> specification = createSpecification(criteria);
        return mGuildEffectLevelRepository.count(specification);
    }

    /**
     * Function to convert MGuildEffectLevelCriteria to a {@link Specification}.
     */
    private Specification<MGuildEffectLevel> createSpecification(MGuildEffectLevelCriteria criteria) {
        Specification<MGuildEffectLevel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuildEffectLevel_.id));
            }
            if (criteria.getEffectType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectType(), MGuildEffectLevel_.effectType));
            }
            if (criteria.getLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevel(), MGuildEffectLevel_.level));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), MGuildEffectLevel_.rate));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MGuildEffectLevel_.coin));
            }
            if (criteria.getGuildLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildLevel(), MGuildEffectLevel_.guildLevel));
            }
            if (criteria.getGuildMedal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildMedal(), MGuildEffectLevel_.guildMedal));
            }
        }
        return specification;
    }
}
