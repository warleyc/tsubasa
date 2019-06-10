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

import io.shm.tsubasa.domain.MAdventQuestTsubasaPointReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MAdventQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MAdventQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.dto.MAdventQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestTsubasaPointRewardMapper;

/**
 * Service for executing complex queries for {@link MAdventQuestTsubasaPointReward} entities in the database.
 * The main input is a {@link MAdventQuestTsubasaPointRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MAdventQuestTsubasaPointRewardDTO} or a {@link Page} of {@link MAdventQuestTsubasaPointRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MAdventQuestTsubasaPointRewardQueryService extends QueryService<MAdventQuestTsubasaPointReward> {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestTsubasaPointRewardQueryService.class);

    private final MAdventQuestTsubasaPointRewardRepository mAdventQuestTsubasaPointRewardRepository;

    private final MAdventQuestTsubasaPointRewardMapper mAdventQuestTsubasaPointRewardMapper;

    public MAdventQuestTsubasaPointRewardQueryService(MAdventQuestTsubasaPointRewardRepository mAdventQuestTsubasaPointRewardRepository, MAdventQuestTsubasaPointRewardMapper mAdventQuestTsubasaPointRewardMapper) {
        this.mAdventQuestTsubasaPointRewardRepository = mAdventQuestTsubasaPointRewardRepository;
        this.mAdventQuestTsubasaPointRewardMapper = mAdventQuestTsubasaPointRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MAdventQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MAdventQuestTsubasaPointRewardDTO> findByCriteria(MAdventQuestTsubasaPointRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MAdventQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mAdventQuestTsubasaPointRewardMapper.toDto(mAdventQuestTsubasaPointRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MAdventQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MAdventQuestTsubasaPointRewardDTO> findByCriteria(MAdventQuestTsubasaPointRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MAdventQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mAdventQuestTsubasaPointRewardRepository.findAll(specification, page)
            .map(mAdventQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MAdventQuestTsubasaPointRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MAdventQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mAdventQuestTsubasaPointRewardRepository.count(specification);
    }

    /**
     * Function to convert MAdventQuestTsubasaPointRewardCriteria to a {@link Specification}.
     */
    private Specification<MAdventQuestTsubasaPointReward> createSpecification(MAdventQuestTsubasaPointRewardCriteria criteria) {
        Specification<MAdventQuestTsubasaPointReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MAdventQuestTsubasaPointReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MAdventQuestTsubasaPointReward_.stageId));
            }
            if (criteria.getTsubasaPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTsubasaPoint(), MAdventQuestTsubasaPointReward_.tsubasaPoint));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MAdventQuestTsubasaPointReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MAdventQuestTsubasaPointReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MAdventQuestTsubasaPointReward_.contentAmount));
            }
        }
        return specification;
    }
}
