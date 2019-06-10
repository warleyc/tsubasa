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

import io.shm.tsubasa.domain.MEncountersCommandCompatibility;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MEncountersCommandCompatibilityRepository;
import io.shm.tsubasa.service.dto.MEncountersCommandCompatibilityCriteria;
import io.shm.tsubasa.service.dto.MEncountersCommandCompatibilityDTO;
import io.shm.tsubasa.service.mapper.MEncountersCommandCompatibilityMapper;

/**
 * Service for executing complex queries for {@link MEncountersCommandCompatibility} entities in the database.
 * The main input is a {@link MEncountersCommandCompatibilityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MEncountersCommandCompatibilityDTO} or a {@link Page} of {@link MEncountersCommandCompatibilityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MEncountersCommandCompatibilityQueryService extends QueryService<MEncountersCommandCompatibility> {

    private final Logger log = LoggerFactory.getLogger(MEncountersCommandCompatibilityQueryService.class);

    private final MEncountersCommandCompatibilityRepository mEncountersCommandCompatibilityRepository;

    private final MEncountersCommandCompatibilityMapper mEncountersCommandCompatibilityMapper;

    public MEncountersCommandCompatibilityQueryService(MEncountersCommandCompatibilityRepository mEncountersCommandCompatibilityRepository, MEncountersCommandCompatibilityMapper mEncountersCommandCompatibilityMapper) {
        this.mEncountersCommandCompatibilityRepository = mEncountersCommandCompatibilityRepository;
        this.mEncountersCommandCompatibilityMapper = mEncountersCommandCompatibilityMapper;
    }

    /**
     * Return a {@link List} of {@link MEncountersCommandCompatibilityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MEncountersCommandCompatibilityDTO> findByCriteria(MEncountersCommandCompatibilityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MEncountersCommandCompatibility> specification = createSpecification(criteria);
        return mEncountersCommandCompatibilityMapper.toDto(mEncountersCommandCompatibilityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MEncountersCommandCompatibilityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MEncountersCommandCompatibilityDTO> findByCriteria(MEncountersCommandCompatibilityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MEncountersCommandCompatibility> specification = createSpecification(criteria);
        return mEncountersCommandCompatibilityRepository.findAll(specification, page)
            .map(mEncountersCommandCompatibilityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MEncountersCommandCompatibilityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MEncountersCommandCompatibility> specification = createSpecification(criteria);
        return mEncountersCommandCompatibilityRepository.count(specification);
    }

    /**
     * Function to convert MEncountersCommandCompatibilityCriteria to a {@link Specification}.
     */
    private Specification<MEncountersCommandCompatibility> createSpecification(MEncountersCommandCompatibilityCriteria criteria) {
        Specification<MEncountersCommandCompatibility> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MEncountersCommandCompatibility_.id));
            }
            if (criteria.getEncountersType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEncountersType(), MEncountersCommandCompatibility_.encountersType));
            }
            if (criteria.getOffenseCommandType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOffenseCommandType(), MEncountersCommandCompatibility_.offenseCommandType));
            }
            if (criteria.getDefenseCommandType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDefenseCommandType(), MEncountersCommandCompatibility_.defenseCommandType));
            }
            if (criteria.getOffenseMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOffenseMagnification(), MEncountersCommandCompatibility_.offenseMagnification));
            }
            if (criteria.getDefenseMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDefenseMagnification(), MEncountersCommandCompatibility_.defenseMagnification));
            }
        }
        return specification;
    }
}
