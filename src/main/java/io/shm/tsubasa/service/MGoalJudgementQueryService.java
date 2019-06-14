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

import io.shm.tsubasa.domain.MGoalJudgement;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGoalJudgementRepository;
import io.shm.tsubasa.service.dto.MGoalJudgementCriteria;
import io.shm.tsubasa.service.dto.MGoalJudgementDTO;
import io.shm.tsubasa.service.mapper.MGoalJudgementMapper;

/**
 * Service for executing complex queries for {@link MGoalJudgement} entities in the database.
 * The main input is a {@link MGoalJudgementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGoalJudgementDTO} or a {@link Page} of {@link MGoalJudgementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGoalJudgementQueryService extends QueryService<MGoalJudgement> {

    private final Logger log = LoggerFactory.getLogger(MGoalJudgementQueryService.class);

    private final MGoalJudgementRepository mGoalJudgementRepository;

    private final MGoalJudgementMapper mGoalJudgementMapper;

    public MGoalJudgementQueryService(MGoalJudgementRepository mGoalJudgementRepository, MGoalJudgementMapper mGoalJudgementMapper) {
        this.mGoalJudgementRepository = mGoalJudgementRepository;
        this.mGoalJudgementMapper = mGoalJudgementMapper;
    }

    /**
     * Return a {@link List} of {@link MGoalJudgementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGoalJudgementDTO> findByCriteria(MGoalJudgementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGoalJudgement> specification = createSpecification(criteria);
        return mGoalJudgementMapper.toDto(mGoalJudgementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGoalJudgementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGoalJudgementDTO> findByCriteria(MGoalJudgementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGoalJudgement> specification = createSpecification(criteria);
        return mGoalJudgementRepository.findAll(specification, page)
            .map(mGoalJudgementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGoalJudgementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGoalJudgement> specification = createSpecification(criteria);
        return mGoalJudgementRepository.count(specification);
    }

    /**
     * Function to convert MGoalJudgementCriteria to a {@link Specification}.
     */
    private Specification<MGoalJudgement> createSpecification(MGoalJudgementCriteria criteria) {
        Specification<MGoalJudgement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGoalJudgement_.id));
            }
            if (criteria.getJudgementId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getJudgementId(), MGoalJudgement_.judgementId));
            }
            if (criteria.getEncountersType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEncountersType(), MGoalJudgement_.encountersType));
            }
            if (criteria.getSuccessRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSuccessRate(), MGoalJudgement_.successRate));
            }
            if (criteria.getGoalPostRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoalPostRate(), MGoalJudgement_.goalPostRate));
            }
            if (criteria.getBallPushRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBallPushRate(), MGoalJudgement_.ballPushRate));
            }
            if (criteria.getClearRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRate(), MGoalJudgement_.clearRate));
            }
        }
        return specification;
    }
}
