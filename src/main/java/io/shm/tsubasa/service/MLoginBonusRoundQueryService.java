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

import io.shm.tsubasa.domain.MLoginBonusRound;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLoginBonusRoundRepository;
import io.shm.tsubasa.service.dto.MLoginBonusRoundCriteria;
import io.shm.tsubasa.service.dto.MLoginBonusRoundDTO;
import io.shm.tsubasa.service.mapper.MLoginBonusRoundMapper;

/**
 * Service for executing complex queries for {@link MLoginBonusRound} entities in the database.
 * The main input is a {@link MLoginBonusRoundCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLoginBonusRoundDTO} or a {@link Page} of {@link MLoginBonusRoundDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLoginBonusRoundQueryService extends QueryService<MLoginBonusRound> {

    private final Logger log = LoggerFactory.getLogger(MLoginBonusRoundQueryService.class);

    private final MLoginBonusRoundRepository mLoginBonusRoundRepository;

    private final MLoginBonusRoundMapper mLoginBonusRoundMapper;

    public MLoginBonusRoundQueryService(MLoginBonusRoundRepository mLoginBonusRoundRepository, MLoginBonusRoundMapper mLoginBonusRoundMapper) {
        this.mLoginBonusRoundRepository = mLoginBonusRoundRepository;
        this.mLoginBonusRoundMapper = mLoginBonusRoundMapper;
    }

    /**
     * Return a {@link List} of {@link MLoginBonusRoundDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLoginBonusRoundDTO> findByCriteria(MLoginBonusRoundCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLoginBonusRound> specification = createSpecification(criteria);
        return mLoginBonusRoundMapper.toDto(mLoginBonusRoundRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLoginBonusRoundDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLoginBonusRoundDTO> findByCriteria(MLoginBonusRoundCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLoginBonusRound> specification = createSpecification(criteria);
        return mLoginBonusRoundRepository.findAll(specification, page)
            .map(mLoginBonusRoundMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLoginBonusRoundCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLoginBonusRound> specification = createSpecification(criteria);
        return mLoginBonusRoundRepository.count(specification);
    }

    /**
     * Function to convert MLoginBonusRoundCriteria to a {@link Specification}.
     */
    private Specification<MLoginBonusRound> createSpecification(MLoginBonusRoundCriteria criteria) {
        Specification<MLoginBonusRound> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLoginBonusRound_.id));
            }
            if (criteria.getRoundId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRoundId(), MLoginBonusRound_.roundId));
            }
            if (criteria.getBonusType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBonusType(), MLoginBonusRound_.bonusType));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MLoginBonusRound_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MLoginBonusRound_.endAt));
            }
            if (criteria.getNextId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNextId(), MLoginBonusRound_.nextId));
            }
        }
        return specification;
    }
}
