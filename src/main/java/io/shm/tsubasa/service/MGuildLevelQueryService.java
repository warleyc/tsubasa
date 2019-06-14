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

import io.shm.tsubasa.domain.MGuildLevel;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuildLevelRepository;
import io.shm.tsubasa.service.dto.MGuildLevelCriteria;
import io.shm.tsubasa.service.dto.MGuildLevelDTO;
import io.shm.tsubasa.service.mapper.MGuildLevelMapper;

/**
 * Service for executing complex queries for {@link MGuildLevel} entities in the database.
 * The main input is a {@link MGuildLevelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuildLevelDTO} or a {@link Page} of {@link MGuildLevelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuildLevelQueryService extends QueryService<MGuildLevel> {

    private final Logger log = LoggerFactory.getLogger(MGuildLevelQueryService.class);

    private final MGuildLevelRepository mGuildLevelRepository;

    private final MGuildLevelMapper mGuildLevelMapper;

    public MGuildLevelQueryService(MGuildLevelRepository mGuildLevelRepository, MGuildLevelMapper mGuildLevelMapper) {
        this.mGuildLevelRepository = mGuildLevelRepository;
        this.mGuildLevelMapper = mGuildLevelMapper;
    }

    /**
     * Return a {@link List} of {@link MGuildLevelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuildLevelDTO> findByCriteria(MGuildLevelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuildLevel> specification = createSpecification(criteria);
        return mGuildLevelMapper.toDto(mGuildLevelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuildLevelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildLevelDTO> findByCriteria(MGuildLevelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuildLevel> specification = createSpecification(criteria);
        return mGuildLevelRepository.findAll(specification, page)
            .map(mGuildLevelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuildLevelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuildLevel> specification = createSpecification(criteria);
        return mGuildLevelRepository.count(specification);
    }

    /**
     * Function to convert MGuildLevelCriteria to a {@link Specification}.
     */
    private Specification<MGuildLevel> createSpecification(MGuildLevelCriteria criteria) {
        Specification<MGuildLevel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuildLevel_.id));
            }
            if (criteria.getLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevel(), MGuildLevel_.level));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MGuildLevel_.exp));
            }
            if (criteria.getMemberCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMemberCount(), MGuildLevel_.memberCount));
            }
            if (criteria.getRecommendMemberCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecommendMemberCount(), MGuildLevel_.recommendMemberCount));
            }
            if (criteria.getGuildMedal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildMedal(), MGuildLevel_.guildMedal));
            }
        }
        return specification;
    }
}
