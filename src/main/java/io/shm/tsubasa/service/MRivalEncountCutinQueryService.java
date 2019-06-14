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

import io.shm.tsubasa.domain.MRivalEncountCutin;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MRivalEncountCutinRepository;
import io.shm.tsubasa.service.dto.MRivalEncountCutinCriteria;
import io.shm.tsubasa.service.dto.MRivalEncountCutinDTO;
import io.shm.tsubasa.service.mapper.MRivalEncountCutinMapper;

/**
 * Service for executing complex queries for {@link MRivalEncountCutin} entities in the database.
 * The main input is a {@link MRivalEncountCutinCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MRivalEncountCutinDTO} or a {@link Page} of {@link MRivalEncountCutinDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MRivalEncountCutinQueryService extends QueryService<MRivalEncountCutin> {

    private final Logger log = LoggerFactory.getLogger(MRivalEncountCutinQueryService.class);

    private final MRivalEncountCutinRepository mRivalEncountCutinRepository;

    private final MRivalEncountCutinMapper mRivalEncountCutinMapper;

    public MRivalEncountCutinQueryService(MRivalEncountCutinRepository mRivalEncountCutinRepository, MRivalEncountCutinMapper mRivalEncountCutinMapper) {
        this.mRivalEncountCutinRepository = mRivalEncountCutinRepository;
        this.mRivalEncountCutinMapper = mRivalEncountCutinMapper;
    }

    /**
     * Return a {@link List} of {@link MRivalEncountCutinDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MRivalEncountCutinDTO> findByCriteria(MRivalEncountCutinCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MRivalEncountCutin> specification = createSpecification(criteria);
        return mRivalEncountCutinMapper.toDto(mRivalEncountCutinRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MRivalEncountCutinDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MRivalEncountCutinDTO> findByCriteria(MRivalEncountCutinCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MRivalEncountCutin> specification = createSpecification(criteria);
        return mRivalEncountCutinRepository.findAll(specification, page)
            .map(mRivalEncountCutinMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MRivalEncountCutinCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MRivalEncountCutin> specification = createSpecification(criteria);
        return mRivalEncountCutinRepository.count(specification);
    }

    /**
     * Function to convert MRivalEncountCutinCriteria to a {@link Specification}.
     */
    private Specification<MRivalEncountCutin> createSpecification(MRivalEncountCutinCriteria criteria) {
        Specification<MRivalEncountCutin> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MRivalEncountCutin_.id));
            }
            if (criteria.getOffenseCharacterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOffenseCharacterId(), MRivalEncountCutin_.offenseCharacterId));
            }
            if (criteria.getOffenseTeamId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOffenseTeamId(), MRivalEncountCutin_.offenseTeamId));
            }
            if (criteria.getDefenseCharacterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDefenseCharacterId(), MRivalEncountCutin_.defenseCharacterId));
            }
            if (criteria.getDefenseTeamId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDefenseTeamId(), MRivalEncountCutin_.defenseTeamId));
            }
            if (criteria.getCutinType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCutinType(), MRivalEncountCutin_.cutinType));
            }
        }
        return specification;
    }
}
