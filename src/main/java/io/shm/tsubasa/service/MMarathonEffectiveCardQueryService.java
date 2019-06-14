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

import io.shm.tsubasa.domain.MMarathonEffectiveCard;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonEffectiveCardRepository;
import io.shm.tsubasa.service.dto.MMarathonEffectiveCardCriteria;
import io.shm.tsubasa.service.dto.MMarathonEffectiveCardDTO;
import io.shm.tsubasa.service.mapper.MMarathonEffectiveCardMapper;

/**
 * Service for executing complex queries for {@link MMarathonEffectiveCard} entities in the database.
 * The main input is a {@link MMarathonEffectiveCardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonEffectiveCardDTO} or a {@link Page} of {@link MMarathonEffectiveCardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonEffectiveCardQueryService extends QueryService<MMarathonEffectiveCard> {

    private final Logger log = LoggerFactory.getLogger(MMarathonEffectiveCardQueryService.class);

    private final MMarathonEffectiveCardRepository mMarathonEffectiveCardRepository;

    private final MMarathonEffectiveCardMapper mMarathonEffectiveCardMapper;

    public MMarathonEffectiveCardQueryService(MMarathonEffectiveCardRepository mMarathonEffectiveCardRepository, MMarathonEffectiveCardMapper mMarathonEffectiveCardMapper) {
        this.mMarathonEffectiveCardRepository = mMarathonEffectiveCardRepository;
        this.mMarathonEffectiveCardMapper = mMarathonEffectiveCardMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonEffectiveCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonEffectiveCardDTO> findByCriteria(MMarathonEffectiveCardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonEffectiveCard> specification = createSpecification(criteria);
        return mMarathonEffectiveCardMapper.toDto(mMarathonEffectiveCardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonEffectiveCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonEffectiveCardDTO> findByCriteria(MMarathonEffectiveCardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonEffectiveCard> specification = createSpecification(criteria);
        return mMarathonEffectiveCardRepository.findAll(specification, page)
            .map(mMarathonEffectiveCardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonEffectiveCardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonEffectiveCard> specification = createSpecification(criteria);
        return mMarathonEffectiveCardRepository.count(specification);
    }

    /**
     * Function to convert MMarathonEffectiveCardCriteria to a {@link Specification}.
     */
    private Specification<MMarathonEffectiveCard> createSpecification(MMarathonEffectiveCardCriteria criteria) {
        Specification<MMarathonEffectiveCard> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonEffectiveCard_.id));
            }
            if (criteria.getEventId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventId(), MMarathonEffectiveCard_.eventId));
            }
            if (criteria.getPlayableCardId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlayableCardId(), MMarathonEffectiveCard_.playableCardId));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), MMarathonEffectiveCard_.rate));
            }
        }
        return specification;
    }
}
