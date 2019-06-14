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

import io.shm.tsubasa.domain.MGuerillaQuestTsubasaPointReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MGuerillaQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MGuerillaQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.dto.MGuerillaQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestTsubasaPointRewardMapper;

/**
 * Service for executing complex queries for {@link MGuerillaQuestTsubasaPointReward} entities in the database.
 * The main input is a {@link MGuerillaQuestTsubasaPointRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MGuerillaQuestTsubasaPointRewardDTO} or a {@link Page} of {@link MGuerillaQuestTsubasaPointRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MGuerillaQuestTsubasaPointRewardQueryService extends QueryService<MGuerillaQuestTsubasaPointReward> {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestTsubasaPointRewardQueryService.class);

    private final MGuerillaQuestTsubasaPointRewardRepository mGuerillaQuestTsubasaPointRewardRepository;

    private final MGuerillaQuestTsubasaPointRewardMapper mGuerillaQuestTsubasaPointRewardMapper;

    public MGuerillaQuestTsubasaPointRewardQueryService(MGuerillaQuestTsubasaPointRewardRepository mGuerillaQuestTsubasaPointRewardRepository, MGuerillaQuestTsubasaPointRewardMapper mGuerillaQuestTsubasaPointRewardMapper) {
        this.mGuerillaQuestTsubasaPointRewardRepository = mGuerillaQuestTsubasaPointRewardRepository;
        this.mGuerillaQuestTsubasaPointRewardMapper = mGuerillaQuestTsubasaPointRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MGuerillaQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MGuerillaQuestTsubasaPointRewardDTO> findByCriteria(MGuerillaQuestTsubasaPointRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MGuerillaQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mGuerillaQuestTsubasaPointRewardMapper.toDto(mGuerillaQuestTsubasaPointRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MGuerillaQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuerillaQuestTsubasaPointRewardDTO> findByCriteria(MGuerillaQuestTsubasaPointRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MGuerillaQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mGuerillaQuestTsubasaPointRewardRepository.findAll(specification, page)
            .map(mGuerillaQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MGuerillaQuestTsubasaPointRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MGuerillaQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mGuerillaQuestTsubasaPointRewardRepository.count(specification);
    }

    /**
     * Function to convert MGuerillaQuestTsubasaPointRewardCriteria to a {@link Specification}.
     */
    private Specification<MGuerillaQuestTsubasaPointReward> createSpecification(MGuerillaQuestTsubasaPointRewardCriteria criteria) {
        Specification<MGuerillaQuestTsubasaPointReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MGuerillaQuestTsubasaPointReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MGuerillaQuestTsubasaPointReward_.stageId));
            }
            if (criteria.getTsubasaPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTsubasaPoint(), MGuerillaQuestTsubasaPointReward_.tsubasaPoint));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MGuerillaQuestTsubasaPointReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MGuerillaQuestTsubasaPointReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MGuerillaQuestTsubasaPointReward_.contentAmount));
            }
        }
        return specification;
    }
}
