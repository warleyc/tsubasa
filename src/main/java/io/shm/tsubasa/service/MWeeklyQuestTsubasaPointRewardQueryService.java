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

import io.shm.tsubasa.domain.MWeeklyQuestTsubasaPointReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MWeeklyQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MWeeklyQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.dto.MWeeklyQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestTsubasaPointRewardMapper;

/**
 * Service for executing complex queries for {@link MWeeklyQuestTsubasaPointReward} entities in the database.
 * The main input is a {@link MWeeklyQuestTsubasaPointRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MWeeklyQuestTsubasaPointRewardDTO} or a {@link Page} of {@link MWeeklyQuestTsubasaPointRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MWeeklyQuestTsubasaPointRewardQueryService extends QueryService<MWeeklyQuestTsubasaPointReward> {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestTsubasaPointRewardQueryService.class);

    private final MWeeklyQuestTsubasaPointRewardRepository mWeeklyQuestTsubasaPointRewardRepository;

    private final MWeeklyQuestTsubasaPointRewardMapper mWeeklyQuestTsubasaPointRewardMapper;

    public MWeeklyQuestTsubasaPointRewardQueryService(MWeeklyQuestTsubasaPointRewardRepository mWeeklyQuestTsubasaPointRewardRepository, MWeeklyQuestTsubasaPointRewardMapper mWeeklyQuestTsubasaPointRewardMapper) {
        this.mWeeklyQuestTsubasaPointRewardRepository = mWeeklyQuestTsubasaPointRewardRepository;
        this.mWeeklyQuestTsubasaPointRewardMapper = mWeeklyQuestTsubasaPointRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MWeeklyQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MWeeklyQuestTsubasaPointRewardDTO> findByCriteria(MWeeklyQuestTsubasaPointRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MWeeklyQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mWeeklyQuestTsubasaPointRewardMapper.toDto(mWeeklyQuestTsubasaPointRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MWeeklyQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MWeeklyQuestTsubasaPointRewardDTO> findByCriteria(MWeeklyQuestTsubasaPointRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MWeeklyQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mWeeklyQuestTsubasaPointRewardRepository.findAll(specification, page)
            .map(mWeeklyQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MWeeklyQuestTsubasaPointRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MWeeklyQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mWeeklyQuestTsubasaPointRewardRepository.count(specification);
    }

    /**
     * Function to convert MWeeklyQuestTsubasaPointRewardCriteria to a {@link Specification}.
     */
    private Specification<MWeeklyQuestTsubasaPointReward> createSpecification(MWeeklyQuestTsubasaPointRewardCriteria criteria) {
        Specification<MWeeklyQuestTsubasaPointReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MWeeklyQuestTsubasaPointReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MWeeklyQuestTsubasaPointReward_.stageId));
            }
            if (criteria.getTsubasaPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTsubasaPoint(), MWeeklyQuestTsubasaPointReward_.tsubasaPoint));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MWeeklyQuestTsubasaPointReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MWeeklyQuestTsubasaPointReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MWeeklyQuestTsubasaPointReward_.contentAmount));
            }
        }
        return specification;
    }
}
