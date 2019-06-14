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

import io.shm.tsubasa.domain.MMarathonBoostSchedule;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonBoostScheduleRepository;
import io.shm.tsubasa.service.dto.MMarathonBoostScheduleCriteria;
import io.shm.tsubasa.service.dto.MMarathonBoostScheduleDTO;
import io.shm.tsubasa.service.mapper.MMarathonBoostScheduleMapper;

/**
 * Service for executing complex queries for {@link MMarathonBoostSchedule} entities in the database.
 * The main input is a {@link MMarathonBoostScheduleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonBoostScheduleDTO} or a {@link Page} of {@link MMarathonBoostScheduleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonBoostScheduleQueryService extends QueryService<MMarathonBoostSchedule> {

    private final Logger log = LoggerFactory.getLogger(MMarathonBoostScheduleQueryService.class);

    private final MMarathonBoostScheduleRepository mMarathonBoostScheduleRepository;

    private final MMarathonBoostScheduleMapper mMarathonBoostScheduleMapper;

    public MMarathonBoostScheduleQueryService(MMarathonBoostScheduleRepository mMarathonBoostScheduleRepository, MMarathonBoostScheduleMapper mMarathonBoostScheduleMapper) {
        this.mMarathonBoostScheduleRepository = mMarathonBoostScheduleRepository;
        this.mMarathonBoostScheduleMapper = mMarathonBoostScheduleMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonBoostScheduleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonBoostScheduleDTO> findByCriteria(MMarathonBoostScheduleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonBoostSchedule> specification = createSpecification(criteria);
        return mMarathonBoostScheduleMapper.toDto(mMarathonBoostScheduleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonBoostScheduleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonBoostScheduleDTO> findByCriteria(MMarathonBoostScheduleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonBoostSchedule> specification = createSpecification(criteria);
        return mMarathonBoostScheduleRepository.findAll(specification, page)
            .map(mMarathonBoostScheduleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonBoostScheduleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonBoostSchedule> specification = createSpecification(criteria);
        return mMarathonBoostScheduleRepository.count(specification);
    }

    /**
     * Function to convert MMarathonBoostScheduleCriteria to a {@link Specification}.
     */
    private Specification<MMarathonBoostSchedule> createSpecification(MMarathonBoostScheduleCriteria criteria) {
        Specification<MMarathonBoostSchedule> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonBoostSchedule_.id));
            }
            if (criteria.getEventId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventId(), MMarathonBoostSchedule_.eventId));
            }
            if (criteria.getBoostRatio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBoostRatio(), MMarathonBoostSchedule_.boostRatio));
            }
            if (criteria.getStartAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartAt(), MMarathonBoostSchedule_.startAt));
            }
            if (criteria.getEndAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndAt(), MMarathonBoostSchedule_.endAt));
            }
        }
        return specification;
    }
}
