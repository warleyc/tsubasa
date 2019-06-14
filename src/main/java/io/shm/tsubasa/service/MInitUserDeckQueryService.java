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

import io.shm.tsubasa.domain.MInitUserDeck;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MInitUserDeckRepository;
import io.shm.tsubasa.service.dto.MInitUserDeckCriteria;
import io.shm.tsubasa.service.dto.MInitUserDeckDTO;
import io.shm.tsubasa.service.mapper.MInitUserDeckMapper;

/**
 * Service for executing complex queries for {@link MInitUserDeck} entities in the database.
 * The main input is a {@link MInitUserDeckCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MInitUserDeckDTO} or a {@link Page} of {@link MInitUserDeckDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MInitUserDeckQueryService extends QueryService<MInitUserDeck> {

    private final Logger log = LoggerFactory.getLogger(MInitUserDeckQueryService.class);

    private final MInitUserDeckRepository mInitUserDeckRepository;

    private final MInitUserDeckMapper mInitUserDeckMapper;

    public MInitUserDeckQueryService(MInitUserDeckRepository mInitUserDeckRepository, MInitUserDeckMapper mInitUserDeckMapper) {
        this.mInitUserDeckRepository = mInitUserDeckRepository;
        this.mInitUserDeckMapper = mInitUserDeckMapper;
    }

    /**
     * Return a {@link List} of {@link MInitUserDeckDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MInitUserDeckDTO> findByCriteria(MInitUserDeckCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MInitUserDeck> specification = createSpecification(criteria);
        return mInitUserDeckMapper.toDto(mInitUserDeckRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MInitUserDeckDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MInitUserDeckDTO> findByCriteria(MInitUserDeckCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MInitUserDeck> specification = createSpecification(criteria);
        return mInitUserDeckRepository.findAll(specification, page)
            .map(mInitUserDeckMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MInitUserDeckCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MInitUserDeck> specification = createSpecification(criteria);
        return mInitUserDeckRepository.count(specification);
    }

    /**
     * Function to convert MInitUserDeckCriteria to a {@link Specification}.
     */
    private Specification<MInitUserDeck> createSpecification(MInitUserDeckCriteria criteria) {
        Specification<MInitUserDeck> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MInitUserDeck_.id));
            }
            if (criteria.getDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeckId(), MInitUserDeck_.deckId));
            }
            if (criteria.getFormationId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFormationId(), MInitUserDeck_.formationId));
            }
            if (criteria.getCaptainCardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCaptainCardId(), MInitUserDeck_.captainCardId));
            }
            if (criteria.getGkCardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGkCardId(), MInitUserDeck_.gkCardId));
            }
            if (criteria.getFp1CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFp1CardId(), MInitUserDeck_.fp1CardId));
            }
            if (criteria.getFp2CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFp2CardId(), MInitUserDeck_.fp2CardId));
            }
            if (criteria.getFp3CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFp3CardId(), MInitUserDeck_.fp3CardId));
            }
            if (criteria.getFp4CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFp4CardId(), MInitUserDeck_.fp4CardId));
            }
            if (criteria.getFp5CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFp5CardId(), MInitUserDeck_.fp5CardId));
            }
            if (criteria.getFp6CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFp6CardId(), MInitUserDeck_.fp6CardId));
            }
            if (criteria.getFp7CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFp7CardId(), MInitUserDeck_.fp7CardId));
            }
            if (criteria.getFp8CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFp8CardId(), MInitUserDeck_.fp8CardId));
            }
            if (criteria.getFp9CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFp9CardId(), MInitUserDeck_.fp9CardId));
            }
            if (criteria.getFp10CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFp10CardId(), MInitUserDeck_.fp10CardId));
            }
            if (criteria.getSub1CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSub1CardId(), MInitUserDeck_.sub1CardId));
            }
            if (criteria.getSub2CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSub2CardId(), MInitUserDeck_.sub2CardId));
            }
            if (criteria.getSub3CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSub3CardId(), MInitUserDeck_.sub3CardId));
            }
            if (criteria.getSub4CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSub4CardId(), MInitUserDeck_.sub4CardId));
            }
            if (criteria.getSub5CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSub5CardId(), MInitUserDeck_.sub5CardId));
            }
            if (criteria.getTeamEffect1CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamEffect1CardId(), MInitUserDeck_.teamEffect1CardId));
            }
            if (criteria.getTeamEffect2CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamEffect2CardId(), MInitUserDeck_.teamEffect2CardId));
            }
            if (criteria.getTeamEffect3CardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTeamEffect3CardId(), MInitUserDeck_.teamEffect3CardId));
            }
        }
        return specification;
    }
}
