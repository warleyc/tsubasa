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

import io.shm.tsubasa.domain.MLeagueAffiliateReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MLeagueAffiliateRewardRepository;
import io.shm.tsubasa.service.dto.MLeagueAffiliateRewardCriteria;
import io.shm.tsubasa.service.dto.MLeagueAffiliateRewardDTO;
import io.shm.tsubasa.service.mapper.MLeagueAffiliateRewardMapper;

/**
 * Service for executing complex queries for {@link MLeagueAffiliateReward} entities in the database.
 * The main input is a {@link MLeagueAffiliateRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MLeagueAffiliateRewardDTO} or a {@link Page} of {@link MLeagueAffiliateRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MLeagueAffiliateRewardQueryService extends QueryService<MLeagueAffiliateReward> {

    private final Logger log = LoggerFactory.getLogger(MLeagueAffiliateRewardQueryService.class);

    private final MLeagueAffiliateRewardRepository mLeagueAffiliateRewardRepository;

    private final MLeagueAffiliateRewardMapper mLeagueAffiliateRewardMapper;

    public MLeagueAffiliateRewardQueryService(MLeagueAffiliateRewardRepository mLeagueAffiliateRewardRepository, MLeagueAffiliateRewardMapper mLeagueAffiliateRewardMapper) {
        this.mLeagueAffiliateRewardRepository = mLeagueAffiliateRewardRepository;
        this.mLeagueAffiliateRewardMapper = mLeagueAffiliateRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MLeagueAffiliateRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MLeagueAffiliateRewardDTO> findByCriteria(MLeagueAffiliateRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MLeagueAffiliateReward> specification = createSpecification(criteria);
        return mLeagueAffiliateRewardMapper.toDto(mLeagueAffiliateRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MLeagueAffiliateRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueAffiliateRewardDTO> findByCriteria(MLeagueAffiliateRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MLeagueAffiliateReward> specification = createSpecification(criteria);
        return mLeagueAffiliateRewardRepository.findAll(specification, page)
            .map(mLeagueAffiliateRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MLeagueAffiliateRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MLeagueAffiliateReward> specification = createSpecification(criteria);
        return mLeagueAffiliateRewardRepository.count(specification);
    }

    /**
     * Function to convert MLeagueAffiliateRewardCriteria to a {@link Specification}.
     */
    private Specification<MLeagueAffiliateReward> createSpecification(MLeagueAffiliateRewardCriteria criteria) {
        Specification<MLeagueAffiliateReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MLeagueAffiliateReward_.id));
            }
            if (criteria.getHierarchy() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHierarchy(), MLeagueAffiliateReward_.hierarchy));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MLeagueAffiliateReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MLeagueAffiliateReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MLeagueAffiliateReward_.contentAmount));
            }
        }
        return specification;
    }
}
