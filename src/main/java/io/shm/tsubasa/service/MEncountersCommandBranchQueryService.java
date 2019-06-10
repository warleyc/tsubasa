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

import io.shm.tsubasa.domain.MEncountersCommandBranch;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MEncountersCommandBranchRepository;
import io.shm.tsubasa.service.dto.MEncountersCommandBranchCriteria;
import io.shm.tsubasa.service.dto.MEncountersCommandBranchDTO;
import io.shm.tsubasa.service.mapper.MEncountersCommandBranchMapper;

/**
 * Service for executing complex queries for {@link MEncountersCommandBranch} entities in the database.
 * The main input is a {@link MEncountersCommandBranchCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MEncountersCommandBranchDTO} or a {@link Page} of {@link MEncountersCommandBranchDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MEncountersCommandBranchQueryService extends QueryService<MEncountersCommandBranch> {

    private final Logger log = LoggerFactory.getLogger(MEncountersCommandBranchQueryService.class);

    private final MEncountersCommandBranchRepository mEncountersCommandBranchRepository;

    private final MEncountersCommandBranchMapper mEncountersCommandBranchMapper;

    public MEncountersCommandBranchQueryService(MEncountersCommandBranchRepository mEncountersCommandBranchRepository, MEncountersCommandBranchMapper mEncountersCommandBranchMapper) {
        this.mEncountersCommandBranchRepository = mEncountersCommandBranchRepository;
        this.mEncountersCommandBranchMapper = mEncountersCommandBranchMapper;
    }

    /**
     * Return a {@link List} of {@link MEncountersCommandBranchDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MEncountersCommandBranchDTO> findByCriteria(MEncountersCommandBranchCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MEncountersCommandBranch> specification = createSpecification(criteria);
        return mEncountersCommandBranchMapper.toDto(mEncountersCommandBranchRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MEncountersCommandBranchDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MEncountersCommandBranchDTO> findByCriteria(MEncountersCommandBranchCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MEncountersCommandBranch> specification = createSpecification(criteria);
        return mEncountersCommandBranchRepository.findAll(specification, page)
            .map(mEncountersCommandBranchMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MEncountersCommandBranchCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MEncountersCommandBranch> specification = createSpecification(criteria);
        return mEncountersCommandBranchRepository.count(specification);
    }

    /**
     * Function to convert MEncountersCommandBranchCriteria to a {@link Specification}.
     */
    private Specification<MEncountersCommandBranch> createSpecification(MEncountersCommandBranchCriteria criteria) {
        Specification<MEncountersCommandBranch> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MEncountersCommandBranch_.id));
            }
            if (criteria.getBallFloatCondition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBallFloatCondition(), MEncountersCommandBranch_.ballFloatCondition));
            }
            if (criteria.getCondition() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCondition(), MEncountersCommandBranch_.condition));
            }
            if (criteria.getEncountersType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEncountersType(), MEncountersCommandBranch_.encountersType));
            }
            if (criteria.getIsSuccess() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsSuccess(), MEncountersCommandBranch_.isSuccess));
            }
            if (criteria.getCommandType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCommandType(), MEncountersCommandBranch_.commandType));
            }
            if (criteria.getSuccessRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSuccessRate(), MEncountersCommandBranch_.successRate));
            }
            if (criteria.getLooseBallRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLooseBallRate(), MEncountersCommandBranch_.looseBallRate));
            }
            if (criteria.getTouchLightlyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTouchLightlyRate(), MEncountersCommandBranch_.touchLightlyRate));
            }
            if (criteria.getPostRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPostRate(), MEncountersCommandBranch_.postRate));
            }
        }
        return specification;
    }
}
