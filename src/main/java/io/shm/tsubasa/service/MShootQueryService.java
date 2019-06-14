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

import io.shm.tsubasa.domain.MShoot;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MShootRepository;
import io.shm.tsubasa.service.dto.MShootCriteria;
import io.shm.tsubasa.service.dto.MShootDTO;
import io.shm.tsubasa.service.mapper.MShootMapper;

/**
 * Service for executing complex queries for {@link MShoot} entities in the database.
 * The main input is a {@link MShootCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MShootDTO} or a {@link Page} of {@link MShootDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MShootQueryService extends QueryService<MShoot> {

    private final Logger log = LoggerFactory.getLogger(MShootQueryService.class);

    private final MShootRepository mShootRepository;

    private final MShootMapper mShootMapper;

    public MShootQueryService(MShootRepository mShootRepository, MShootMapper mShootMapper) {
        this.mShootRepository = mShootRepository;
        this.mShootMapper = mShootMapper;
    }

    /**
     * Return a {@link List} of {@link MShootDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MShootDTO> findByCriteria(MShootCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MShoot> specification = createSpecification(criteria);
        return mShootMapper.toDto(mShootRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MShootDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MShootDTO> findByCriteria(MShootCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MShoot> specification = createSpecification(criteria);
        return mShootRepository.findAll(specification, page)
            .map(mShootMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MShootCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MShoot> specification = createSpecification(criteria);
        return mShootRepository.count(specification);
    }

    /**
     * Function to convert MShootCriteria to a {@link Specification}.
     */
    private Specification<MShoot> createSpecification(MShootCriteria criteria) {
        Specification<MShoot> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MShoot_.id));
            }
            if (criteria.getAngleDecayType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAngleDecayType(), MShoot_.angleDecayType));
            }
            if (criteria.getShootOrbit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShootOrbit(), MShoot_.shootOrbit));
            }
            if (criteria.getJudgementId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getJudgementId(), MShoot_.judgementId));
            }
        }
        return specification;
    }
}
