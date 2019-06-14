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

import io.shm.tsubasa.domain.MQuestCoopReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestCoopRewardRepository;
import io.shm.tsubasa.service.dto.MQuestCoopRewardCriteria;
import io.shm.tsubasa.service.dto.MQuestCoopRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestCoopRewardMapper;

/**
 * Service for executing complex queries for {@link MQuestCoopReward} entities in the database.
 * The main input is a {@link MQuestCoopRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestCoopRewardDTO} or a {@link Page} of {@link MQuestCoopRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestCoopRewardQueryService extends QueryService<MQuestCoopReward> {

    private final Logger log = LoggerFactory.getLogger(MQuestCoopRewardQueryService.class);

    private final MQuestCoopRewardRepository mQuestCoopRewardRepository;

    private final MQuestCoopRewardMapper mQuestCoopRewardMapper;

    public MQuestCoopRewardQueryService(MQuestCoopRewardRepository mQuestCoopRewardRepository, MQuestCoopRewardMapper mQuestCoopRewardMapper) {
        this.mQuestCoopRewardRepository = mQuestCoopRewardRepository;
        this.mQuestCoopRewardMapper = mQuestCoopRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestCoopRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestCoopRewardDTO> findByCriteria(MQuestCoopRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestCoopReward> specification = createSpecification(criteria);
        return mQuestCoopRewardMapper.toDto(mQuestCoopRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestCoopRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestCoopRewardDTO> findByCriteria(MQuestCoopRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestCoopReward> specification = createSpecification(criteria);
        return mQuestCoopRewardRepository.findAll(specification, page)
            .map(mQuestCoopRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestCoopRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestCoopReward> specification = createSpecification(criteria);
        return mQuestCoopRewardRepository.count(specification);
    }

    /**
     * Function to convert MQuestCoopRewardCriteria to a {@link Specification}.
     */
    private Specification<MQuestCoopReward> createSpecification(MQuestCoopRewardCriteria criteria) {
        Specification<MQuestCoopReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestCoopReward_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MQuestCoopReward_.groupId));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MQuestCoopReward_.weight));
            }
            if (criteria.getRank() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRank(), MQuestCoopReward_.rank));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MQuestCoopReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MQuestCoopReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MQuestCoopReward_.contentAmount));
            }
        }
        return specification;
    }
}
