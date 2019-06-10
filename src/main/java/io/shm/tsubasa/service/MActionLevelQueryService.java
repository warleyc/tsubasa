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

import io.shm.tsubasa.domain.MActionLevel;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MActionLevelRepository;
import io.shm.tsubasa.service.dto.MActionLevelCriteria;
import io.shm.tsubasa.service.dto.MActionLevelDTO;
import io.shm.tsubasa.service.mapper.MActionLevelMapper;

/**
 * Service for executing complex queries for {@link MActionLevel} entities in the database.
 * The main input is a {@link MActionLevelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MActionLevelDTO} or a {@link Page} of {@link MActionLevelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MActionLevelQueryService extends QueryService<MActionLevel> {

    private final Logger log = LoggerFactory.getLogger(MActionLevelQueryService.class);

    private final MActionLevelRepository mActionLevelRepository;

    private final MActionLevelMapper mActionLevelMapper;

    public MActionLevelQueryService(MActionLevelRepository mActionLevelRepository, MActionLevelMapper mActionLevelMapper) {
        this.mActionLevelRepository = mActionLevelRepository;
        this.mActionLevelMapper = mActionLevelMapper;
    }

    /**
     * Return a {@link List} of {@link MActionLevelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MActionLevelDTO> findByCriteria(MActionLevelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MActionLevel> specification = createSpecification(criteria);
        return mActionLevelMapper.toDto(mActionLevelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MActionLevelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionLevelDTO> findByCriteria(MActionLevelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MActionLevel> specification = createSpecification(criteria);
        return mActionLevelRepository.findAll(specification, page)
            .map(mActionLevelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MActionLevelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MActionLevel> specification = createSpecification(criteria);
        return mActionLevelRepository.count(specification);
    }

    /**
     * Function to convert MActionLevelCriteria to a {@link Specification}.
     */
    private Specification<MActionLevel> createSpecification(MActionLevelCriteria criteria) {
        Specification<MActionLevel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MActionLevel_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MActionLevel_.rarity));
            }
            if (criteria.getLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevel(), MActionLevel_.level));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MActionLevel_.exp));
            }
        }
        return specification;
    }
}
