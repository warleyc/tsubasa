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

import io.shm.tsubasa.domain.MNpcPersonality;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MNpcPersonalityRepository;
import io.shm.tsubasa.service.dto.MNpcPersonalityCriteria;
import io.shm.tsubasa.service.dto.MNpcPersonalityDTO;
import io.shm.tsubasa.service.mapper.MNpcPersonalityMapper;

/**
 * Service for executing complex queries for {@link MNpcPersonality} entities in the database.
 * The main input is a {@link MNpcPersonalityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MNpcPersonalityDTO} or a {@link Page} of {@link MNpcPersonalityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MNpcPersonalityQueryService extends QueryService<MNpcPersonality> {

    private final Logger log = LoggerFactory.getLogger(MNpcPersonalityQueryService.class);

    private final MNpcPersonalityRepository mNpcPersonalityRepository;

    private final MNpcPersonalityMapper mNpcPersonalityMapper;

    public MNpcPersonalityQueryService(MNpcPersonalityRepository mNpcPersonalityRepository, MNpcPersonalityMapper mNpcPersonalityMapper) {
        this.mNpcPersonalityRepository = mNpcPersonalityRepository;
        this.mNpcPersonalityMapper = mNpcPersonalityMapper;
    }

    /**
     * Return a {@link List} of {@link MNpcPersonalityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MNpcPersonalityDTO> findByCriteria(MNpcPersonalityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MNpcPersonality> specification = createSpecification(criteria);
        return mNpcPersonalityMapper.toDto(mNpcPersonalityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MNpcPersonalityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MNpcPersonalityDTO> findByCriteria(MNpcPersonalityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MNpcPersonality> specification = createSpecification(criteria);
        return mNpcPersonalityRepository.findAll(specification, page)
            .map(mNpcPersonalityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MNpcPersonalityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MNpcPersonality> specification = createSpecification(criteria);
        return mNpcPersonalityRepository.count(specification);
    }

    /**
     * Function to convert MNpcPersonalityCriteria to a {@link Specification}.
     */
    private Specification<MNpcPersonality> createSpecification(MNpcPersonalityCriteria criteria) {
        Specification<MNpcPersonality> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MNpcPersonality_.id));
            }
            if (criteria.getPassingTargetRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPassingTargetRate(), MNpcPersonality_.passingTargetRate));
            }
            if (criteria.getActionSkillRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionSkillRate(), MNpcPersonality_.actionSkillRate));
            }
            if (criteria.getDribbleMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDribbleMagnification(), MNpcPersonality_.dribbleMagnification));
            }
            if (criteria.getPassingMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPassingMagnification(), MNpcPersonality_.passingMagnification));
            }
            if (criteria.getOnetwoMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOnetwoMagnification(), MNpcPersonality_.onetwoMagnification));
            }
            if (criteria.getShootMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShootMagnification(), MNpcPersonality_.shootMagnification));
            }
            if (criteria.getVolleyShootMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVolleyShootMagnification(), MNpcPersonality_.volleyShootMagnification));
            }
            if (criteria.getHeadingShootMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeadingShootMagnification(), MNpcPersonality_.headingShootMagnification));
            }
            if (criteria.getTackleMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTackleMagnification(), MNpcPersonality_.tackleMagnification));
            }
            if (criteria.getBlockMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBlockMagnification(), MNpcPersonality_.blockMagnification));
            }
            if (criteria.getPassCutMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPassCutMagnification(), MNpcPersonality_.passCutMagnification));
            }
            if (criteria.getClearMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearMagnification(), MNpcPersonality_.clearMagnification));
            }
            if (criteria.getCompeteMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCompeteMagnification(), MNpcPersonality_.competeMagnification));
            }
            if (criteria.getTrapMagnification() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTrapMagnification(), MNpcPersonality_.trapMagnification));
            }
        }
        return specification;
    }
}
