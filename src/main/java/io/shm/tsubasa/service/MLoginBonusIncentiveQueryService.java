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

import io.shm.tsubasa.domain.MLoginBonusIncentive;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLoginBonusIncentiveRepository;
import io.shm.tsubasa.service.dto.MLoginBonusIncentiveCriteria;
import io.shm.tsubasa.service.dto.MLoginBonusIncentiveDTO;
import io.shm.tsubasa.service.mapper.MLoginBonusIncentiveMapper;

/**
 * Service for executing complex queries for {@link MLoginBonusIncentive} entities in the database.
 * The main input is a {@link MLoginBonusIncentiveCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLoginBonusIncentiveDTO} or a {@link Page} of {@link MLoginBonusIncentiveDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLoginBonusIncentiveQueryService extends QueryService<MLoginBonusIncentive> {

    private final Logger log = LoggerFactory.getLogger(MLoginBonusIncentiveQueryService.class);

    private final MLoginBonusIncentiveRepository mLoginBonusIncentiveRepository;

    private final MLoginBonusIncentiveMapper mLoginBonusIncentiveMapper;

    public MLoginBonusIncentiveQueryService(MLoginBonusIncentiveRepository mLoginBonusIncentiveRepository, MLoginBonusIncentiveMapper mLoginBonusIncentiveMapper) {
        this.mLoginBonusIncentiveRepository = mLoginBonusIncentiveRepository;
        this.mLoginBonusIncentiveMapper = mLoginBonusIncentiveMapper;
    }

    /**
     * Return a {@link List} of {@link MLoginBonusIncentiveDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLoginBonusIncentiveDTO> findByCriteria(MLoginBonusIncentiveCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLoginBonusIncentive> specification = createSpecification(criteria);
        return mLoginBonusIncentiveMapper.toDto(mLoginBonusIncentiveRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLoginBonusIncentiveDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLoginBonusIncentiveDTO> findByCriteria(MLoginBonusIncentiveCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLoginBonusIncentive> specification = createSpecification(criteria);
        return mLoginBonusIncentiveRepository.findAll(specification, page)
            .map(mLoginBonusIncentiveMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLoginBonusIncentiveCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLoginBonusIncentive> specification = createSpecification(criteria);
        return mLoginBonusIncentiveRepository.count(specification);
    }

    /**
     * Function to convert MLoginBonusIncentiveCriteria to a {@link Specification}.
     */
    private Specification<MLoginBonusIncentive> createSpecification(MLoginBonusIncentiveCriteria criteria) {
        Specification<MLoginBonusIncentive> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLoginBonusIncentive_.id));
            }
            if (criteria.getRoundId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRoundId(), MLoginBonusIncentive_.roundId));
            }
            if (criteria.getDay() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDay(), MLoginBonusIncentive_.day));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MLoginBonusIncentive_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MLoginBonusIncentive_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MLoginBonusIncentive_.contentAmount));
            }
            if (criteria.getIsDecorated() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsDecorated(), MLoginBonusIncentive_.isDecorated));
            }
        }
        return specification;
    }
}
