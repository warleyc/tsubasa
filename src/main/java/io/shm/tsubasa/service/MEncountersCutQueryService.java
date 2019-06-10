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

import io.shm.tsubasa.domain.MEncountersCut;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MEncountersCutRepository;
import io.shm.tsubasa.service.dto.MEncountersCutCriteria;
import io.shm.tsubasa.service.dto.MEncountersCutDTO;
import io.shm.tsubasa.service.mapper.MEncountersCutMapper;

/**
 * Service for executing complex queries for {@link MEncountersCut} entities in the database.
 * The main input is a {@link MEncountersCutCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MEncountersCutDTO} or a {@link Page} of {@link MEncountersCutDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MEncountersCutQueryService extends QueryService<MEncountersCut> {

    private final Logger log = LoggerFactory.getLogger(MEncountersCutQueryService.class);

    private final MEncountersCutRepository mEncountersCutRepository;

    private final MEncountersCutMapper mEncountersCutMapper;

    public MEncountersCutQueryService(MEncountersCutRepository mEncountersCutRepository, MEncountersCutMapper mEncountersCutMapper) {
        this.mEncountersCutRepository = mEncountersCutRepository;
        this.mEncountersCutMapper = mEncountersCutMapper;
    }

    /**
     * Return a {@link List} of {@link MEncountersCutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MEncountersCutDTO> findByCriteria(MEncountersCutCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MEncountersCut> specification = createSpecification(criteria);
        return mEncountersCutMapper.toDto(mEncountersCutRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MEncountersCutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MEncountersCutDTO> findByCriteria(MEncountersCutCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MEncountersCut> specification = createSpecification(criteria);
        return mEncountersCutRepository.findAll(specification, page)
            .map(mEncountersCutMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MEncountersCutCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MEncountersCut> specification = createSpecification(criteria);
        return mEncountersCutRepository.count(specification);
    }

    /**
     * Function to convert MEncountersCutCriteria to a {@link Specification}.
     */
    private Specification<MEncountersCut> createSpecification(MEncountersCutCriteria criteria) {
        Specification<MEncountersCut> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MEncountersCut_.id));
            }
            if (criteria.getCondition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCondition(), MEncountersCut_.condition));
            }
            if (criteria.getBallFloatCondition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBallFloatCondition(), MEncountersCut_.ballFloatCondition));
            }
            if (criteria.getCommand1Type() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCommand1Type(), MEncountersCut_.command1Type));
            }
            if (criteria.getCommand1IsSkill() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCommand1IsSkill(), MEncountersCut_.command1IsSkill));
            }
            if (criteria.getCommand2Type() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCommand2Type(), MEncountersCut_.command2Type));
            }
            if (criteria.getCommand2IsSkill() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCommand2IsSkill(), MEncountersCut_.command2IsSkill));
            }
            if (criteria.getResult() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResult(), MEncountersCut_.result));
            }
            if (criteria.getShootResult() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShootResult(), MEncountersCut_.shootResult));
            }
            if (criteria.getIsThrown() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsThrown(), MEncountersCut_.isThrown));
            }
        }
        return specification;
    }
}
