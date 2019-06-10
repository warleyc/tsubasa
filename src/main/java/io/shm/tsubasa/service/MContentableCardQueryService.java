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

import io.shm.tsubasa.domain.MContentableCard;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MContentableCardRepository;
import io.shm.tsubasa.service.dto.MContentableCardCriteria;
import io.shm.tsubasa.service.dto.MContentableCardDTO;
import io.shm.tsubasa.service.mapper.MContentableCardMapper;

/**
 * Service for executing complex queries for {@link MContentableCard} entities in the database.
 * The main input is a {@link MContentableCardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MContentableCardDTO} or a {@link Page} of {@link MContentableCardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MContentableCardQueryService extends QueryService<MContentableCard> {

    private final Logger log = LoggerFactory.getLogger(MContentableCardQueryService.class);

    private final MContentableCardRepository mContentableCardRepository;

    private final MContentableCardMapper mContentableCardMapper;

    public MContentableCardQueryService(MContentableCardRepository mContentableCardRepository, MContentableCardMapper mContentableCardMapper) {
        this.mContentableCardRepository = mContentableCardRepository;
        this.mContentableCardMapper = mContentableCardMapper;
    }

    /**
     * Return a {@link List} of {@link MContentableCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MContentableCardDTO> findByCriteria(MContentableCardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MContentableCard> specification = createSpecification(criteria);
        return mContentableCardMapper.toDto(mContentableCardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MContentableCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MContentableCardDTO> findByCriteria(MContentableCardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MContentableCard> specification = createSpecification(criteria);
        return mContentableCardRepository.findAll(specification, page)
            .map(mContentableCardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MContentableCardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MContentableCard> specification = createSpecification(criteria);
        return mContentableCardRepository.count(specification);
    }

    /**
     * Function to convert MContentableCardCriteria to a {@link Specification}.
     */
    private Specification<MContentableCard> createSpecification(MContentableCardCriteria criteria) {
        Specification<MContentableCard> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MContentableCard_.id));
            }
            if (criteria.getPlayableCardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlayableCardId(), MContentableCard_.playableCardId));
            }
            if (criteria.getLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevel(), MContentableCard_.level));
            }
            if (criteria.getActionMainLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionMainLevel(), MContentableCard_.actionMainLevel));
            }
            if (criteria.getActionSub1Level() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionSub1Level(), MContentableCard_.actionSub1Level));
            }
            if (criteria.getActionSub2Level() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionSub2Level(), MContentableCard_.actionSub2Level));
            }
            if (criteria.getActionSub3Level() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionSub3Level(), MContentableCard_.actionSub3Level));
            }
            if (criteria.getActionSub4Level() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionSub4Level(), MContentableCard_.actionSub4Level));
            }
            if (criteria.getPlusRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlusRate(), MContentableCard_.plusRate));
            }
        }
        return specification;
    }
}
