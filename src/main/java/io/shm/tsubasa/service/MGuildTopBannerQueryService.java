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

import io.shm.tsubasa.domain.MGuildTopBanner;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuildTopBannerRepository;
import io.shm.tsubasa.service.dto.MGuildTopBannerCriteria;
import io.shm.tsubasa.service.dto.MGuildTopBannerDTO;
import io.shm.tsubasa.service.mapper.MGuildTopBannerMapper;

/**
 * Service for executing complex queries for {@link MGuildTopBanner} entities in the database.
 * The main input is a {@link MGuildTopBannerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuildTopBannerDTO} or a {@link Page} of {@link MGuildTopBannerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuildTopBannerQueryService extends QueryService<MGuildTopBanner> {

    private final Logger log = LoggerFactory.getLogger(MGuildTopBannerQueryService.class);

    private final MGuildTopBannerRepository mGuildTopBannerRepository;

    private final MGuildTopBannerMapper mGuildTopBannerMapper;

    public MGuildTopBannerQueryService(MGuildTopBannerRepository mGuildTopBannerRepository, MGuildTopBannerMapper mGuildTopBannerMapper) {
        this.mGuildTopBannerRepository = mGuildTopBannerRepository;
        this.mGuildTopBannerMapper = mGuildTopBannerMapper;
    }

    /**
     * Return a {@link List} of {@link MGuildTopBannerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuildTopBannerDTO> findByCriteria(MGuildTopBannerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuildTopBanner> specification = createSpecification(criteria);
        return mGuildTopBannerMapper.toDto(mGuildTopBannerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuildTopBannerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildTopBannerDTO> findByCriteria(MGuildTopBannerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuildTopBanner> specification = createSpecification(criteria);
        return mGuildTopBannerRepository.findAll(specification, page)
            .map(mGuildTopBannerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuildTopBannerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuildTopBanner> specification = createSpecification(criteria);
        return mGuildTopBannerRepository.count(specification);
    }

    /**
     * Function to convert MGuildTopBannerCriteria to a {@link Specification}.
     */
    private Specification<MGuildTopBanner> createSpecification(MGuildTopBannerCriteria criteria) {
        Specification<MGuildTopBanner> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuildTopBanner_.id));
            }
            if (criteria.getGuildBannerType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildBannerType(), MGuildTopBanner_.guildBannerType));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MGuildTopBanner_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MGuildTopBanner_.endAt));
            }
        }
        return specification;
    }
}
