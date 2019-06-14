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

import io.shm.tsubasa.domain.MGoalEffectiveCard;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGoalEffectiveCardRepository;
import io.shm.tsubasa.service.dto.MGoalEffectiveCardCriteria;
import io.shm.tsubasa.service.dto.MGoalEffectiveCardDTO;
import io.shm.tsubasa.service.mapper.MGoalEffectiveCardMapper;

/**
 * Service for executing complex queries for {@link MGoalEffectiveCard} entities in the database.
 * The main input is a {@link MGoalEffectiveCardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGoalEffectiveCardDTO} or a {@link Page} of {@link MGoalEffectiveCardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGoalEffectiveCardQueryService extends QueryService<MGoalEffectiveCard> {

    private final Logger log = LoggerFactory.getLogger(MGoalEffectiveCardQueryService.class);

    private final MGoalEffectiveCardRepository mGoalEffectiveCardRepository;

    private final MGoalEffectiveCardMapper mGoalEffectiveCardMapper;

    public MGoalEffectiveCardQueryService(MGoalEffectiveCardRepository mGoalEffectiveCardRepository, MGoalEffectiveCardMapper mGoalEffectiveCardMapper) {
        this.mGoalEffectiveCardRepository = mGoalEffectiveCardRepository;
        this.mGoalEffectiveCardMapper = mGoalEffectiveCardMapper;
    }

    /**
     * Return a {@link List} of {@link MGoalEffectiveCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGoalEffectiveCardDTO> findByCriteria(MGoalEffectiveCardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGoalEffectiveCard> specification = createSpecification(criteria);
        return mGoalEffectiveCardMapper.toDto(mGoalEffectiveCardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGoalEffectiveCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGoalEffectiveCardDTO> findByCriteria(MGoalEffectiveCardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGoalEffectiveCard> specification = createSpecification(criteria);
        return mGoalEffectiveCardRepository.findAll(specification, page)
            .map(mGoalEffectiveCardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGoalEffectiveCardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGoalEffectiveCard> specification = createSpecification(criteria);
        return mGoalEffectiveCardRepository.count(specification);
    }

    /**
     * Function to convert MGoalEffectiveCardCriteria to a {@link Specification}.
     */
    private Specification<MGoalEffectiveCard> createSpecification(MGoalEffectiveCardCriteria criteria) {
        Specification<MGoalEffectiveCard> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGoalEffectiveCard_.id));
            }
            if (criteria.getEventType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventType(), MGoalEffectiveCard_.eventType));
            }
            if (criteria.getEventId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventId(), MGoalEffectiveCard_.eventId));
            }
            if (criteria.getPlayableCardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlayableCardId(), MGoalEffectiveCard_.playableCardId));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), MGoalEffectiveCard_.rate));
            }
        }
        return specification;
    }
}
