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

import io.shm.tsubasa.domain.MLeagueRankingRewardGroup;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLeagueRankingRewardGroupRepository;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardGroupCriteria;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MLeagueRankingRewardGroupMapper;

/**
 * Service for executing complex queries for {@link MLeagueRankingRewardGroup} entities in the database.
 * The main input is a {@link MLeagueRankingRewardGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLeagueRankingRewardGroupDTO} or a {@link Page} of {@link MLeagueRankingRewardGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLeagueRankingRewardGroupQueryService extends QueryService<MLeagueRankingRewardGroup> {

    private final Logger log = LoggerFactory.getLogger(MLeagueRankingRewardGroupQueryService.class);

    private final MLeagueRankingRewardGroupRepository mLeagueRankingRewardGroupRepository;

    private final MLeagueRankingRewardGroupMapper mLeagueRankingRewardGroupMapper;

    public MLeagueRankingRewardGroupQueryService(MLeagueRankingRewardGroupRepository mLeagueRankingRewardGroupRepository, MLeagueRankingRewardGroupMapper mLeagueRankingRewardGroupMapper) {
        this.mLeagueRankingRewardGroupRepository = mLeagueRankingRewardGroupRepository;
        this.mLeagueRankingRewardGroupMapper = mLeagueRankingRewardGroupMapper;
    }

    /**
     * Return a {@link List} of {@link MLeagueRankingRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLeagueRankingRewardGroupDTO> findByCriteria(MLeagueRankingRewardGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLeagueRankingRewardGroup> specification = createSpecification(criteria);
        return mLeagueRankingRewardGroupMapper.toDto(mLeagueRankingRewardGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLeagueRankingRewardGroupDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueRankingRewardGroupDTO> findByCriteria(MLeagueRankingRewardGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLeagueRankingRewardGroup> specification = createSpecification(criteria);
        return mLeagueRankingRewardGroupRepository.findAll(specification, page)
            .map(mLeagueRankingRewardGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLeagueRankingRewardGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLeagueRankingRewardGroup> specification = createSpecification(criteria);
        return mLeagueRankingRewardGroupRepository.count(specification);
    }

    /**
     * Function to convert MLeagueRankingRewardGroupCriteria to a {@link Specification}.
     */
    private Specification<MLeagueRankingRewardGroup> createSpecification(MLeagueRankingRewardGroupCriteria criteria) {
        Specification<MLeagueRankingRewardGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLeagueRankingRewardGroup_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MLeagueRankingRewardGroup_.groupId));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MLeagueRankingRewardGroup_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MLeagueRankingRewardGroup_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MLeagueRankingRewardGroup_.contentAmount));
            }
        }
        return specification;
    }
}
