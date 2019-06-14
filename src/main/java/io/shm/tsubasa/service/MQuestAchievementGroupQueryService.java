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

import io.shm.tsubasa.domain.MQuestAchievementGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestAchievementGroupRepository;
import io.shm.tsubasa.service.dto.MQuestAchievementGroupCriteria;
import io.shm.tsubasa.service.dto.MQuestAchievementGroupDTO;
import io.shm.tsubasa.service.mapper.MQuestAchievementGroupMapper;

/**
 * Service for executing complex queries for {@link MQuestAchievementGroup} entities in the database.
 * The main input is a {@link MQuestAchievementGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestAchievementGroupDTO} or a {@link Page} of {@link MQuestAchievementGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestAchievementGroupQueryService extends QueryService<MQuestAchievementGroup> {

    private final Logger log = LoggerFactory.getLogger(MQuestAchievementGroupQueryService.class);

    private final MQuestAchievementGroupRepository mQuestAchievementGroupRepository;

    private final MQuestAchievementGroupMapper mQuestAchievementGroupMapper;

    public MQuestAchievementGroupQueryService(MQuestAchievementGroupRepository mQuestAchievementGroupRepository, MQuestAchievementGroupMapper mQuestAchievementGroupMapper) {
        this.mQuestAchievementGroupRepository = mQuestAchievementGroupRepository;
        this.mQuestAchievementGroupMapper = mQuestAchievementGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestAchievementGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestAchievementGroupDTO> findByCriteria(MQuestAchievementGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestAchievementGroup> specification = createSpecification(criteria);
        return mQuestAchievementGroupMapper.toDto(mQuestAchievementGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestAchievementGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestAchievementGroupDTO> findByCriteria(MQuestAchievementGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestAchievementGroup> specification = createSpecification(criteria);
        return mQuestAchievementGroupRepository.findAll(specification, page)
            .map(mQuestAchievementGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestAchievementGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestAchievementGroup> specification = createSpecification(criteria);
        return mQuestAchievementGroupRepository.count(specification);
    }

    /**
     * Function to convert MQuestAchievementGroupCriteria to a {@link Specification}.
     */
    private Specification<MQuestAchievementGroup> createSpecification(MQuestAchievementGroupCriteria criteria) {
        Specification<MQuestAchievementGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestAchievementGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MQuestAchievementGroup_.groupId));
            }
            if (criteria.getAchievementType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAchievementType(), MQuestAchievementGroup_.achievementType));
            }
            if (criteria.getRank() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRank(), MQuestAchievementGroup_.rank));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), MQuestAchievementGroup_.weight));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MQuestAchievementGroup_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MQuestAchievementGroup_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MQuestAchievementGroup_.contentAmount));
            }
        }
        return specification;
    }
}
