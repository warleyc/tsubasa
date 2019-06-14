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

import io.shm.tsubasa.domain.MQuestStageReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MQuestStageRewardCriteria;
import io.shm.tsubasa.service.dto.MQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestStageRewardMapper;

/**
 * Service for executing complex queries for {@link MQuestStageReward} entities in the database.
 * The main input is a {@link MQuestStageRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestStageRewardDTO} or a {@link Page} of {@link MQuestStageRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestStageRewardQueryService extends QueryService<MQuestStageReward> {

    private final Logger log = LoggerFactory.getLogger(MQuestStageRewardQueryService.class);

    private final MQuestStageRewardRepository mQuestStageRewardRepository;

    private final MQuestStageRewardMapper mQuestStageRewardMapper;

    public MQuestStageRewardQueryService(MQuestStageRewardRepository mQuestStageRewardRepository, MQuestStageRewardMapper mQuestStageRewardMapper) {
        this.mQuestStageRewardRepository = mQuestStageRewardRepository;
        this.mQuestStageRewardMapper = mQuestStageRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestStageRewardDTO> findByCriteria(MQuestStageRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestStageReward> specification = createSpecification(criteria);
        return mQuestStageRewardMapper.toDto(mQuestStageRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestStageRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestStageRewardDTO> findByCriteria(MQuestStageRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestStageReward> specification = createSpecification(criteria);
        return mQuestStageRewardRepository.findAll(specification, page)
            .map(mQuestStageRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestStageRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestStageReward> specification = createSpecification(criteria);
        return mQuestStageRewardRepository.count(specification);
    }

    /**
     * Function to convert MQuestStageRewardCriteria to a {@link Specification}.
     */
    private Specification<MQuestStageReward> createSpecification(MQuestStageRewardCriteria criteria) {
        Specification<MQuestStageReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestStageReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MQuestStageReward_.stageId));
            }
            if (criteria.getExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExp(), MQuestStageReward_.exp));
            }
            if (criteria.getCoin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoin(), MQuestStageReward_.coin));
            }
            if (criteria.getGuildPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuildPoint(), MQuestStageReward_.guildPoint));
            }
            if (criteria.getClearRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardGroupId(), MQuestStageReward_.clearRewardGroupId));
            }
            if (criteria.getClearRewardWeightId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClearRewardWeightId(), MQuestStageReward_.clearRewardWeightId));
            }
            if (criteria.getAchievementRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAchievementRewardGroupId(), MQuestStageReward_.achievementRewardGroupId));
            }
            if (criteria.getCoopGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoopGroupId(), MQuestStageReward_.coopGroupId));
            }
            if (criteria.getSpecialRewardGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardGroupId(), MQuestStageReward_.specialRewardGroupId));
            }
            if (criteria.getSpecialRewardAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardAmount(), MQuestStageReward_.specialRewardAmount));
            }
        }
        return specification;
    }
}
