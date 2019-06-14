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

import io.shm.tsubasa.domain.MGuildRoulettePrize;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuildRoulettePrizeRepository;
import io.shm.tsubasa.service.dto.MGuildRoulettePrizeCriteria;
import io.shm.tsubasa.service.dto.MGuildRoulettePrizeDTO;
import io.shm.tsubasa.service.mapper.MGuildRoulettePrizeMapper;

/**
 * Service for executing complex queries for {@link MGuildRoulettePrize} entities in the database.
 * The main input is a {@link MGuildRoulettePrizeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuildRoulettePrizeDTO} or a {@link Page} of {@link MGuildRoulettePrizeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuildRoulettePrizeQueryService extends QueryService<MGuildRoulettePrize> {

    private final Logger log = LoggerFactory.getLogger(MGuildRoulettePrizeQueryService.class);

    private final MGuildRoulettePrizeRepository mGuildRoulettePrizeRepository;

    private final MGuildRoulettePrizeMapper mGuildRoulettePrizeMapper;

    public MGuildRoulettePrizeQueryService(MGuildRoulettePrizeRepository mGuildRoulettePrizeRepository, MGuildRoulettePrizeMapper mGuildRoulettePrizeMapper) {
        this.mGuildRoulettePrizeRepository = mGuildRoulettePrizeRepository;
        this.mGuildRoulettePrizeMapper = mGuildRoulettePrizeMapper;
    }

    /**
     * Return a {@link List} of {@link MGuildRoulettePrizeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuildRoulettePrizeDTO> findByCriteria(MGuildRoulettePrizeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuildRoulettePrize> specification = createSpecification(criteria);
        return mGuildRoulettePrizeMapper.toDto(mGuildRoulettePrizeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuildRoulettePrizeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildRoulettePrizeDTO> findByCriteria(MGuildRoulettePrizeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuildRoulettePrize> specification = createSpecification(criteria);
        return mGuildRoulettePrizeRepository.findAll(specification, page)
            .map(mGuildRoulettePrizeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuildRoulettePrizeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuildRoulettePrize> specification = createSpecification(criteria);
        return mGuildRoulettePrizeRepository.count(specification);
    }

    /**
     * Function to convert MGuildRoulettePrizeCriteria to a {@link Specification}.
     */
    private Specification<MGuildRoulettePrize> createSpecification(MGuildRoulettePrizeCriteria criteria) {
        Specification<MGuildRoulettePrize> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuildRoulettePrize_.id));
            }
            if (criteria.getRank() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRank(), MGuildRoulettePrize_.rank));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MGuildRoulettePrize_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MGuildRoulettePrize_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MGuildRoulettePrize_.contentAmount));
            }
        }
        return specification;
    }
}
