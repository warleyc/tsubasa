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

import io.shm.tsubasa.domain.MFormation;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MFormationRepository;
import io.shm.tsubasa.service.dto.MFormationCriteria;
import io.shm.tsubasa.service.dto.MFormationDTO;
import io.shm.tsubasa.service.mapper.MFormationMapper;

/**
 * Service for executing complex queries for {@link MFormation} entities in the database.
 * The main input is a {@link MFormationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MFormationDTO} or a {@link Page} of {@link MFormationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MFormationQueryService extends QueryService<MFormation> {

    private final Logger log = LoggerFactory.getLogger(MFormationQueryService.class);

    private final MFormationRepository mFormationRepository;

    private final MFormationMapper mFormationMapper;

    public MFormationQueryService(MFormationRepository mFormationRepository, MFormationMapper mFormationMapper) {
        this.mFormationRepository = mFormationRepository;
        this.mFormationMapper = mFormationMapper;
    }

    /**
     * Return a {@link List} of {@link MFormationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MFormationDTO> findByCriteria(MFormationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MFormation> specification = createSpecification(criteria);
        return mFormationMapper.toDto(mFormationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MFormationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MFormationDTO> findByCriteria(MFormationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MFormation> specification = createSpecification(criteria);
        return mFormationRepository.findAll(specification, page)
            .map(mFormationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MFormationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MFormation> specification = createSpecification(criteria);
        return mFormationRepository.count(specification);
    }

    /**
     * Function to convert MFormationCriteria to a {@link Specification}.
     */
    private Specification<MFormation> createSpecification(MFormationCriteria criteria) {
        Specification<MFormation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MFormation_.id));
            }
            if (criteria.getEffectValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectValue(), MFormation_.effectValue));
            }
            if (criteria.getEffectProbability() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectProbability(), MFormation_.effectProbability));
            }
            if (criteria.getEffectId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectId(), MFormation_.effectId));
            }
            if (criteria.getExType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExType(), MFormation_.exType));
            }
            if (criteria.getMatchFormationId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatchFormationId(), MFormation_.matchFormationId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MFormation_.id, JoinType.LEFT).get(MPassiveEffectRange_.id)));
            }
            if (criteria.getMNpcDeckId() != null) {
                specification = specification.and(buildSpecification(criteria.getMNpcDeckId(),
                    root -> root.join(MFormation_.mNpcDecks, JoinType.LEFT).get(MNpcDeck_.id)));
            }
            if (criteria.getMTargetFormationGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTargetFormationGroupId(),
                    root -> root.join(MFormation_.mTargetFormationGroups, JoinType.LEFT).get(MTargetFormationGroup_.id)));
            }
        }
        return specification;
    }
}
