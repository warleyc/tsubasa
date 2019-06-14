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

import io.shm.tsubasa.domain.MQuestDropRateCampaignContent;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestDropRateCampaignContentRepository;
import io.shm.tsubasa.service.dto.MQuestDropRateCampaignContentCriteria;
import io.shm.tsubasa.service.dto.MQuestDropRateCampaignContentDTO;
import io.shm.tsubasa.service.mapper.MQuestDropRateCampaignContentMapper;

/**
 * Service for executing complex queries for {@link MQuestDropRateCampaignContent} entities in the database.
 * The main input is a {@link MQuestDropRateCampaignContentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestDropRateCampaignContentDTO} or a {@link Page} of {@link MQuestDropRateCampaignContentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestDropRateCampaignContentQueryService extends QueryService<MQuestDropRateCampaignContent> {

    private final Logger log = LoggerFactory.getLogger(MQuestDropRateCampaignContentQueryService.class);

    private final MQuestDropRateCampaignContentRepository mQuestDropRateCampaignContentRepository;

    private final MQuestDropRateCampaignContentMapper mQuestDropRateCampaignContentMapper;

    public MQuestDropRateCampaignContentQueryService(MQuestDropRateCampaignContentRepository mQuestDropRateCampaignContentRepository, MQuestDropRateCampaignContentMapper mQuestDropRateCampaignContentMapper) {
        this.mQuestDropRateCampaignContentRepository = mQuestDropRateCampaignContentRepository;
        this.mQuestDropRateCampaignContentMapper = mQuestDropRateCampaignContentMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestDropRateCampaignContentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestDropRateCampaignContentDTO> findByCriteria(MQuestDropRateCampaignContentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestDropRateCampaignContent> specification = createSpecification(criteria);
        return mQuestDropRateCampaignContentMapper.toDto(mQuestDropRateCampaignContentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestDropRateCampaignContentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestDropRateCampaignContentDTO> findByCriteria(MQuestDropRateCampaignContentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestDropRateCampaignContent> specification = createSpecification(criteria);
        return mQuestDropRateCampaignContentRepository.findAll(specification, page)
            .map(mQuestDropRateCampaignContentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestDropRateCampaignContentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestDropRateCampaignContent> specification = createSpecification(criteria);
        return mQuestDropRateCampaignContentRepository.count(specification);
    }

    /**
     * Function to convert MQuestDropRateCampaignContentCriteria to a {@link Specification}.
     */
    private Specification<MQuestDropRateCampaignContent> createSpecification(MQuestDropRateCampaignContentCriteria criteria) {
        Specification<MQuestDropRateCampaignContent> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestDropRateCampaignContent_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MQuestDropRateCampaignContent_.groupId));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MQuestDropRateCampaignContent_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MQuestDropRateCampaignContent_.contentId));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), MQuestDropRateCampaignContent_.rate));
            }
        }
        return specification;
    }
}
