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

import io.shm.tsubasa.domain.MQuestRewardGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestRewardGroupRepository;
import io.shm.tsubasa.service.dto.MQuestRewardGroupCriteria;
import io.shm.tsubasa.service.dto.MQuestRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MQuestRewardGroupMapper;

/**
 * Service for executing complex queries for {@link MQuestRewardGroup} entities in the database.
 * The main input is a {@link MQuestRewardGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestRewardGroupDTO} or a {@link Page} of {@link MQuestRewardGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestRewardGroupQueryService extends QueryService<MQuestRewardGroup> {

    private final Logger log = LoggerFactory.getLogger(MQuestRewardGroupQueryService.class);

    private final MQuestRewardGroupRepository mQuestRewardGroupRepository;

    private final MQuestRewardGroupMapper mQuestRewardGroupMapper;

    public MQuestRewardGroupQueryService(MQuestRewardGroupRepository mQuestRewardGroupRepository, MQuestRewardGroupMapper mQuestRewardGroupMapper) {
        this.mQuestRewardGroupRepository = mQuestRewardGroupRepository;
        this.mQuestRewardGroupMapper = mQuestRewardGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestRewardGroupDTO> findByCriteria(MQuestRewardGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestRewardGroup> specification = createSpecification(criteria);
        return mQuestRewardGroupMapper.toDto(mQuestRewardGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestRewardGroupDTO> findByCriteria(MQuestRewardGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestRewardGroup> specification = createSpecification(criteria);
        return mQuestRewardGroupRepository.findAll(specification, page)
            .map(mQuestRewardGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestRewardGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestRewardGroup> specification = createSpecification(criteria);
        return mQuestRewardGroupRepository.count(specification);
    }

    /**
     * Function to convert MQuestRewardGroupCriteria to a {@link Specification}.
     */
    private Specification<MQuestRewardGroup> createSpecification(MQuestRewardGroupCriteria criteria) {
        Specification<MQuestRewardGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestRewardGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MQuestRewardGroup_.groupId));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), MQuestRewardGroup_.rate));
            }
            if (criteria.getRank() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRank(), MQuestRewardGroup_.rank));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MQuestRewardGroup_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MQuestRewardGroup_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MQuestRewardGroup_.contentAmount));
            }
        }
        return specification;
    }
}
