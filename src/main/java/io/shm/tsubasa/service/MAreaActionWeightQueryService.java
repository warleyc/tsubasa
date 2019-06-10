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

import io.shm.tsubasa.domain.MAreaActionWeight;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MAreaActionWeightRepository;
import io.shm.tsubasa.service.dto.MAreaActionWeightCriteria;
import io.shm.tsubasa.service.dto.MAreaActionWeightDTO;
import io.shm.tsubasa.service.mapper.MAreaActionWeightMapper;

/**
 * Service for executing complex queries for {@link MAreaActionWeight} entities in the database.
 * The main input is a {@link MAreaActionWeightCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MAreaActionWeightDTO} or a {@link Page} of {@link MAreaActionWeightDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MAreaActionWeightQueryService extends QueryService<MAreaActionWeight> {

    private final Logger log = LoggerFactory.getLogger(MAreaActionWeightQueryService.class);

    private final MAreaActionWeightRepository mAreaActionWeightRepository;

    private final MAreaActionWeightMapper mAreaActionWeightMapper;

    public MAreaActionWeightQueryService(MAreaActionWeightRepository mAreaActionWeightRepository, MAreaActionWeightMapper mAreaActionWeightMapper) {
        this.mAreaActionWeightRepository = mAreaActionWeightRepository;
        this.mAreaActionWeightMapper = mAreaActionWeightMapper;
    }

    /**
     * Return a {@link List} of {@link MAreaActionWeightDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MAreaActionWeightDTO> findByCriteria(MAreaActionWeightCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MAreaActionWeight> specification = createSpecification(criteria);
        return mAreaActionWeightMapper.toDto(mAreaActionWeightRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MAreaActionWeightDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MAreaActionWeightDTO> findByCriteria(MAreaActionWeightCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MAreaActionWeight> specification = createSpecification(criteria);
        return mAreaActionWeightRepository.findAll(specification, page)
            .map(mAreaActionWeightMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MAreaActionWeightCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MAreaActionWeight> specification = createSpecification(criteria);
        return mAreaActionWeightRepository.count(specification);
    }

    /**
     * Function to convert MAreaActionWeightCriteria to a {@link Specification}.
     */
    private Specification<MAreaActionWeight> createSpecification(MAreaActionWeightCriteria criteria) {
        Specification<MAreaActionWeight> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MAreaActionWeight_.id));
            }
            if (criteria.getAreaType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAreaType(), MAreaActionWeight_.areaType));
            }
            if (criteria.getDribbleRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDribbleRate(), MAreaActionWeight_.dribbleRate));
            }
            if (criteria.getPassingRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPassingRate(), MAreaActionWeight_.passingRate));
            }
            if (criteria.getOnetwoRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOnetwoRate(), MAreaActionWeight_.onetwoRate));
            }
            if (criteria.getShootRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShootRate(), MAreaActionWeight_.shootRate));
            }
            if (criteria.getVolleyShootRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVolleyShootRate(), MAreaActionWeight_.volleyShootRate));
            }
            if (criteria.getHeadingShootRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeadingShootRate(), MAreaActionWeight_.headingShootRate));
            }
            if (criteria.getTackleRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTackleRate(), MAreaActionWeight_.tackleRate));
            }
            if (criteria.getBlockRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBlockRate(), MAreaActionWeight_.blockRate));
            }
            if (criteria.getPassCutRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPassCutRate(), MAreaActionWeight_.passCutRate));
            }
            if (criteria.getClearRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRate(), MAreaActionWeight_.clearRate));
            }
            if (criteria.getCompeteRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCompeteRate(), MAreaActionWeight_.competeRate));
            }
            if (criteria.getTrapRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrapRate(), MAreaActionWeight_.trapRate));
            }
        }
        return specification;
    }
}
