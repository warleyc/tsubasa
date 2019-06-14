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

import io.shm.tsubasa.domain.MMarathonLoopReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonLoopRewardRepository;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardCriteria;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonLoopRewardMapper;

/**
 * Service for executing complex queries for {@link MMarathonLoopReward} entities in the database.
 * The main input is a {@link MMarathonLoopRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonLoopRewardDTO} or a {@link Page} of {@link MMarathonLoopRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonLoopRewardQueryService extends QueryService<MMarathonLoopReward> {

    private final Logger log = LoggerFactory.getLogger(MMarathonLoopRewardQueryService.class);

    private final MMarathonLoopRewardRepository mMarathonLoopRewardRepository;

    private final MMarathonLoopRewardMapper mMarathonLoopRewardMapper;

    public MMarathonLoopRewardQueryService(MMarathonLoopRewardRepository mMarathonLoopRewardRepository, MMarathonLoopRewardMapper mMarathonLoopRewardMapper) {
        this.mMarathonLoopRewardRepository = mMarathonLoopRewardRepository;
        this.mMarathonLoopRewardMapper = mMarathonLoopRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonLoopRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonLoopRewardDTO> findByCriteria(MMarathonLoopRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonLoopReward> specification = createSpecification(criteria);
        return mMarathonLoopRewardMapper.toDto(mMarathonLoopRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonLoopRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonLoopRewardDTO> findByCriteria(MMarathonLoopRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonLoopReward> specification = createSpecification(criteria);
        return mMarathonLoopRewardRepository.findAll(specification, page)
            .map(mMarathonLoopRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonLoopRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonLoopReward> specification = createSpecification(criteria);
        return mMarathonLoopRewardRepository.count(specification);
    }

    /**
     * Function to convert MMarathonLoopRewardCriteria to a {@link Specification}.
     */
    private Specification<MMarathonLoopReward> createSpecification(MMarathonLoopRewardCriteria criteria) {
        Specification<MMarathonLoopReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonLoopReward_.id));
            }
            if (criteria.getEventId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventId(), MMarathonLoopReward_.eventId));
            }
            if (criteria.getLoopPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoopPoint(), MMarathonLoopReward_.loopPoint));
            }
        }
        return specification;
    }
}
