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

import io.shm.tsubasa.domain.MQuestSpecialReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestSpecialRewardRepository;
import io.shm.tsubasa.service.dto.MQuestSpecialRewardCriteria;
import io.shm.tsubasa.service.dto.MQuestSpecialRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestSpecialRewardMapper;

/**
 * Service for executing complex queries for {@link MQuestSpecialReward} entities in the database.
 * The main input is a {@link MQuestSpecialRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestSpecialRewardDTO} or a {@link Page} of {@link MQuestSpecialRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestSpecialRewardQueryService extends QueryService<MQuestSpecialReward> {

    private final Logger log = LoggerFactory.getLogger(MQuestSpecialRewardQueryService.class);

    private final MQuestSpecialRewardRepository mQuestSpecialRewardRepository;

    private final MQuestSpecialRewardMapper mQuestSpecialRewardMapper;

    public MQuestSpecialRewardQueryService(MQuestSpecialRewardRepository mQuestSpecialRewardRepository, MQuestSpecialRewardMapper mQuestSpecialRewardMapper) {
        this.mQuestSpecialRewardRepository = mQuestSpecialRewardRepository;
        this.mQuestSpecialRewardMapper = mQuestSpecialRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestSpecialRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestSpecialRewardDTO> findByCriteria(MQuestSpecialRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestSpecialReward> specification = createSpecification(criteria);
        return mQuestSpecialRewardMapper.toDto(mQuestSpecialRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestSpecialRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestSpecialRewardDTO> findByCriteria(MQuestSpecialRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestSpecialReward> specification = createSpecification(criteria);
        return mQuestSpecialRewardRepository.findAll(specification, page)
            .map(mQuestSpecialRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestSpecialRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestSpecialReward> specification = createSpecification(criteria);
        return mQuestSpecialRewardRepository.count(specification);
    }

    /**
     * Function to convert MQuestSpecialRewardCriteria to a {@link Specification}.
     */
    private Specification<MQuestSpecialReward> createSpecification(MQuestSpecialRewardCriteria criteria) {
        Specification<MQuestSpecialReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestSpecialReward_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MQuestSpecialReward_.groupId));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MQuestSpecialReward_.weight));
            }
            if (criteria.getRank() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRank(), MQuestSpecialReward_.rank));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MQuestSpecialReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MQuestSpecialReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MQuestSpecialReward_.contentAmount));
            }
        }
        return specification;
    }
}
