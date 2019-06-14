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

import io.shm.tsubasa.domain.MQuestClearRewardWeight;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestClearRewardWeightRepository;
import io.shm.tsubasa.service.dto.MQuestClearRewardWeightCriteria;
import io.shm.tsubasa.service.dto.MQuestClearRewardWeightDTO;
import io.shm.tsubasa.service.mapper.MQuestClearRewardWeightMapper;

/**
 * Service for executing complex queries for {@link MQuestClearRewardWeight} entities in the database.
 * The main input is a {@link MQuestClearRewardWeightCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestClearRewardWeightDTO} or a {@link Page} of {@link MQuestClearRewardWeightDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestClearRewardWeightQueryService extends QueryService<MQuestClearRewardWeight> {

    private final Logger log = LoggerFactory.getLogger(MQuestClearRewardWeightQueryService.class);

    private final MQuestClearRewardWeightRepository mQuestClearRewardWeightRepository;

    private final MQuestClearRewardWeightMapper mQuestClearRewardWeightMapper;

    public MQuestClearRewardWeightQueryService(MQuestClearRewardWeightRepository mQuestClearRewardWeightRepository, MQuestClearRewardWeightMapper mQuestClearRewardWeightMapper) {
        this.mQuestClearRewardWeightRepository = mQuestClearRewardWeightRepository;
        this.mQuestClearRewardWeightMapper = mQuestClearRewardWeightMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestClearRewardWeightDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestClearRewardWeightDTO> findByCriteria(MQuestClearRewardWeightCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestClearRewardWeight> specification = createSpecification(criteria);
        return mQuestClearRewardWeightMapper.toDto(mQuestClearRewardWeightRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestClearRewardWeightDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestClearRewardWeightDTO> findByCriteria(MQuestClearRewardWeightCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestClearRewardWeight> specification = createSpecification(criteria);
        return mQuestClearRewardWeightRepository.findAll(specification, page)
            .map(mQuestClearRewardWeightMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestClearRewardWeightCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestClearRewardWeight> specification = createSpecification(criteria);
        return mQuestClearRewardWeightRepository.count(specification);
    }

    /**
     * Function to convert MQuestClearRewardWeightCriteria to a {@link Specification}.
     */
    private Specification<MQuestClearRewardWeight> createSpecification(MQuestClearRewardWeightCriteria criteria) {
        Specification<MQuestClearRewardWeight> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestClearRewardWeight_.id));
            }
            if (criteria.getReward1() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReward1(), MQuestClearRewardWeight_.reward1));
            }
            if (criteria.getReward2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReward2(), MQuestClearRewardWeight_.reward2));
            }
            if (criteria.getReward3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReward3(), MQuestClearRewardWeight_.reward3));
            }
            if (criteria.getReward4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReward4(), MQuestClearRewardWeight_.reward4));
            }
            if (criteria.getReward5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReward5(), MQuestClearRewardWeight_.reward5));
            }
        }
        return specification;
    }
}
