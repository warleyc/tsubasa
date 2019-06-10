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

import io.shm.tsubasa.domain.MCharacterScoreCut;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCharacterScoreCutRepository;
import io.shm.tsubasa.service.dto.MCharacterScoreCutCriteria;
import io.shm.tsubasa.service.dto.MCharacterScoreCutDTO;
import io.shm.tsubasa.service.mapper.MCharacterScoreCutMapper;

/**
 * Service for executing complex queries for {@link MCharacterScoreCut} entities in the database.
 * The main input is a {@link MCharacterScoreCutCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCharacterScoreCutDTO} or a {@link Page} of {@link MCharacterScoreCutDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCharacterScoreCutQueryService extends QueryService<MCharacterScoreCut> {

    private final Logger log = LoggerFactory.getLogger(MCharacterScoreCutQueryService.class);

    private final MCharacterScoreCutRepository mCharacterScoreCutRepository;

    private final MCharacterScoreCutMapper mCharacterScoreCutMapper;

    public MCharacterScoreCutQueryService(MCharacterScoreCutRepository mCharacterScoreCutRepository, MCharacterScoreCutMapper mCharacterScoreCutMapper) {
        this.mCharacterScoreCutRepository = mCharacterScoreCutRepository;
        this.mCharacterScoreCutMapper = mCharacterScoreCutMapper;
    }

    /**
     * Return a {@link List} of {@link MCharacterScoreCutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCharacterScoreCutDTO> findByCriteria(MCharacterScoreCutCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCharacterScoreCut> specification = createSpecification(criteria);
        return mCharacterScoreCutMapper.toDto(mCharacterScoreCutRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCharacterScoreCutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCharacterScoreCutDTO> findByCriteria(MCharacterScoreCutCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCharacterScoreCut> specification = createSpecification(criteria);
        return mCharacterScoreCutRepository.findAll(specification, page)
            .map(mCharacterScoreCutMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCharacterScoreCutCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCharacterScoreCut> specification = createSpecification(criteria);
        return mCharacterScoreCutRepository.count(specification);
    }

    /**
     * Function to convert MCharacterScoreCutCriteria to a {@link Specification}.
     */
    private Specification<MCharacterScoreCut> createSpecification(MCharacterScoreCutCriteria criteria) {
        Specification<MCharacterScoreCut> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCharacterScoreCut_.id));
            }
            if (criteria.getCharacterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCharacterId(), MCharacterScoreCut_.characterId));
            }
            if (criteria.getTeamId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamId(), MCharacterScoreCut_.teamId));
            }
            if (criteria.getScoreCutType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScoreCutType(), MCharacterScoreCut_.scoreCutType));
            }
        }
        return specification;
    }
}
