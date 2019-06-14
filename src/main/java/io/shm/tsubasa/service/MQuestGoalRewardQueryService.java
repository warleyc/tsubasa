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

import io.shm.tsubasa.domain.MQuestGoalReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestGoalRewardRepository;
import io.shm.tsubasa.service.dto.MQuestGoalRewardCriteria;
import io.shm.tsubasa.service.dto.MQuestGoalRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestGoalRewardMapper;

/**
 * Service for executing complex queries for {@link MQuestGoalReward} entities in the database.
 * The main input is a {@link MQuestGoalRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestGoalRewardDTO} or a {@link Page} of {@link MQuestGoalRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestGoalRewardQueryService extends QueryService<MQuestGoalReward> {

    private final Logger log = LoggerFactory.getLogger(MQuestGoalRewardQueryService.class);

    private final MQuestGoalRewardRepository mQuestGoalRewardRepository;

    private final MQuestGoalRewardMapper mQuestGoalRewardMapper;

    public MQuestGoalRewardQueryService(MQuestGoalRewardRepository mQuestGoalRewardRepository, MQuestGoalRewardMapper mQuestGoalRewardMapper) {
        this.mQuestGoalRewardRepository = mQuestGoalRewardRepository;
        this.mQuestGoalRewardMapper = mQuestGoalRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestGoalRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestGoalRewardDTO> findByCriteria(MQuestGoalRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestGoalReward> specification = createSpecification(criteria);
        return mQuestGoalRewardMapper.toDto(mQuestGoalRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestGoalRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestGoalRewardDTO> findByCriteria(MQuestGoalRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestGoalReward> specification = createSpecification(criteria);
        return mQuestGoalRewardRepository.findAll(specification, page)
            .map(mQuestGoalRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestGoalRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestGoalReward> specification = createSpecification(criteria);
        return mQuestGoalRewardRepository.count(specification);
    }

    /**
     * Function to convert MQuestGoalRewardCriteria to a {@link Specification}.
     */
    private Specification<MQuestGoalReward> createSpecification(MQuestGoalRewardCriteria criteria) {
        Specification<MQuestGoalReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestGoalReward_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MQuestGoalReward_.groupId));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MQuestGoalReward_.weight));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MQuestGoalReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MQuestGoalReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MQuestGoalReward_.contentAmount));
            }
        }
        return specification;
    }
}
