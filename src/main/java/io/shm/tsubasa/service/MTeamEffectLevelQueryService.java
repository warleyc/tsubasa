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

import io.shm.tsubasa.domain.MTeamEffectLevel;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTeamEffectLevelRepository;
import io.shm.tsubasa.service.dto.MTeamEffectLevelCriteria;
import io.shm.tsubasa.service.dto.MTeamEffectLevelDTO;
import io.shm.tsubasa.service.mapper.MTeamEffectLevelMapper;

/**
 * Service for executing complex queries for {@link MTeamEffectLevel} entities in the database.
 * The main input is a {@link MTeamEffectLevelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTeamEffectLevelDTO} or a {@link Page} of {@link MTeamEffectLevelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTeamEffectLevelQueryService extends QueryService<MTeamEffectLevel> {

    private final Logger log = LoggerFactory.getLogger(MTeamEffectLevelQueryService.class);

    private final MTeamEffectLevelRepository mTeamEffectLevelRepository;

    private final MTeamEffectLevelMapper mTeamEffectLevelMapper;

    public MTeamEffectLevelQueryService(MTeamEffectLevelRepository mTeamEffectLevelRepository, MTeamEffectLevelMapper mTeamEffectLevelMapper) {
        this.mTeamEffectLevelRepository = mTeamEffectLevelRepository;
        this.mTeamEffectLevelMapper = mTeamEffectLevelMapper;
    }

    /**
     * Return a {@link List} of {@link MTeamEffectLevelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTeamEffectLevelDTO> findByCriteria(MTeamEffectLevelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTeamEffectLevel> specification = createSpecification(criteria);
        return mTeamEffectLevelMapper.toDto(mTeamEffectLevelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTeamEffectLevelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTeamEffectLevelDTO> findByCriteria(MTeamEffectLevelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTeamEffectLevel> specification = createSpecification(criteria);
        return mTeamEffectLevelRepository.findAll(specification, page)
            .map(mTeamEffectLevelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTeamEffectLevelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTeamEffectLevel> specification = createSpecification(criteria);
        return mTeamEffectLevelRepository.count(specification);
    }

    /**
     * Function to convert MTeamEffectLevelCriteria to a {@link Specification}.
     */
    private Specification<MTeamEffectLevel> createSpecification(MTeamEffectLevelCriteria criteria) {
        Specification<MTeamEffectLevel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTeamEffectLevel_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MTeamEffectLevel_.rarity));
            }
            if (criteria.getLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevel(), MTeamEffectLevel_.level));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MTeamEffectLevel_.exp));
            }
        }
        return specification;
    }
}
