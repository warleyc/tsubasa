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

import io.shm.tsubasa.domain.MDummyOpponent;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MDummyOpponentRepository;
import io.shm.tsubasa.service.dto.MDummyOpponentCriteria;
import io.shm.tsubasa.service.dto.MDummyOpponentDTO;
import io.shm.tsubasa.service.mapper.MDummyOpponentMapper;

/**
 * Service for executing complex queries for {@link MDummyOpponent} entities in the database.
 * The main input is a {@link MDummyOpponentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MDummyOpponentDTO} or a {@link Page} of {@link MDummyOpponentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MDummyOpponentQueryService extends QueryService<MDummyOpponent> {

    private final Logger log = LoggerFactory.getLogger(MDummyOpponentQueryService.class);

    private final MDummyOpponentRepository mDummyOpponentRepository;

    private final MDummyOpponentMapper mDummyOpponentMapper;

    public MDummyOpponentQueryService(MDummyOpponentRepository mDummyOpponentRepository, MDummyOpponentMapper mDummyOpponentMapper) {
        this.mDummyOpponentRepository = mDummyOpponentRepository;
        this.mDummyOpponentMapper = mDummyOpponentMapper;
    }

    /**
     * Return a {@link List} of {@link MDummyOpponentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MDummyOpponentDTO> findByCriteria(MDummyOpponentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MDummyOpponent> specification = createSpecification(criteria);
        return mDummyOpponentMapper.toDto(mDummyOpponentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MDummyOpponentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MDummyOpponentDTO> findByCriteria(MDummyOpponentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MDummyOpponent> specification = createSpecification(criteria);
        return mDummyOpponentRepository.findAll(specification, page)
            .map(mDummyOpponentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MDummyOpponentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MDummyOpponent> specification = createSpecification(criteria);
        return mDummyOpponentRepository.count(specification);
    }

    /**
     * Function to convert MDummyOpponentCriteria to a {@link Specification}.
     */
    private Specification<MDummyOpponent> createSpecification(MDummyOpponentCriteria criteria) {
        Specification<MDummyOpponent> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MDummyOpponent_.id));
            }
            if (criteria.getNpcDeckId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpcDeckId(), MDummyOpponent_.npcDeckId));
            }
            if (criteria.getTotalParameter() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalParameter(), MDummyOpponent_.totalParameter));
            }
            if (criteria.getRank() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRank(), MDummyOpponent_.rank));
            }
            if (criteria.getEmblemId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEmblemId(), MDummyOpponent_.emblemId));
            }
            if (criteria.getFpUniformUpId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFpUniformUpId(), MDummyOpponent_.fpUniformUpId));
            }
            if (criteria.getFpUniformBottomId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFpUniformBottomId(), MDummyOpponent_.fpUniformBottomId));
            }
            if (criteria.getGkUniformUpId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGkUniformUpId(), MDummyOpponent_.gkUniformUpId));
            }
            if (criteria.getGkUniformBottomId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGkUniformBottomId(), MDummyOpponent_.gkUniformBottomId));
            }
            if (criteria.getMnpcdeckId() != null) {
                specification = specification.and(buildSpecification(criteria.getMnpcdeckId(),
                    root -> root.join(MDummyOpponent_.mnpcdeck, JoinType.LEFT).get(MNpcDeck_.id)));
            }
        }
        return specification;
    }
}
