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

import io.shm.tsubasa.domain.MCardLevel;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCardLevelRepository;
import io.shm.tsubasa.service.dto.MCardLevelCriteria;
import io.shm.tsubasa.service.dto.MCardLevelDTO;
import io.shm.tsubasa.service.mapper.MCardLevelMapper;

/**
 * Service for executing complex queries for {@link MCardLevel} entities in the database.
 * The main input is a {@link MCardLevelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCardLevelDTO} or a {@link Page} of {@link MCardLevelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCardLevelQueryService extends QueryService<MCardLevel> {

    private final Logger log = LoggerFactory.getLogger(MCardLevelQueryService.class);

    private final MCardLevelRepository mCardLevelRepository;

    private final MCardLevelMapper mCardLevelMapper;

    public MCardLevelQueryService(MCardLevelRepository mCardLevelRepository, MCardLevelMapper mCardLevelMapper) {
        this.mCardLevelRepository = mCardLevelRepository;
        this.mCardLevelMapper = mCardLevelMapper;
    }

    /**
     * Return a {@link List} of {@link MCardLevelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCardLevelDTO> findByCriteria(MCardLevelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCardLevel> specification = createSpecification(criteria);
        return mCardLevelMapper.toDto(mCardLevelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCardLevelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardLevelDTO> findByCriteria(MCardLevelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCardLevel> specification = createSpecification(criteria);
        return mCardLevelRepository.findAll(specification, page)
            .map(mCardLevelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCardLevelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCardLevel> specification = createSpecification(criteria);
        return mCardLevelRepository.count(specification);
    }

    /**
     * Function to convert MCardLevelCriteria to a {@link Specification}.
     */
    private Specification<MCardLevel> createSpecification(MCardLevelCriteria criteria) {
        Specification<MCardLevel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCardLevel_.id));
            }
            if (criteria.getRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRarity(), MCardLevel_.rarity));
            }
            if (criteria.getLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevel(), MCardLevel_.level));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MCardLevel_.groupId));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MCardLevel_.exp));
            }
        }
        return specification;
    }
}
