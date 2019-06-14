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

import io.shm.tsubasa.domain.MMarathonQuestTsubasaPointReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MMarathonQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MMarathonQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.dto.MMarathonQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestTsubasaPointRewardMapper;

/**
 * Service for executing complex queries for {@link MMarathonQuestTsubasaPointReward} entities in the database.
 * The main input is a {@link MMarathonQuestTsubasaPointRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MMarathonQuestTsubasaPointRewardDTO} or a {@link Page} of {@link MMarathonQuestTsubasaPointRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MMarathonQuestTsubasaPointRewardQueryService extends QueryService<MMarathonQuestTsubasaPointReward> {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestTsubasaPointRewardQueryService.class);

    private final MMarathonQuestTsubasaPointRewardRepository mMarathonQuestTsubasaPointRewardRepository;

    private final MMarathonQuestTsubasaPointRewardMapper mMarathonQuestTsubasaPointRewardMapper;

    public MMarathonQuestTsubasaPointRewardQueryService(MMarathonQuestTsubasaPointRewardRepository mMarathonQuestTsubasaPointRewardRepository, MMarathonQuestTsubasaPointRewardMapper mMarathonQuestTsubasaPointRewardMapper) {
        this.mMarathonQuestTsubasaPointRewardRepository = mMarathonQuestTsubasaPointRewardRepository;
        this.mMarathonQuestTsubasaPointRewardMapper = mMarathonQuestTsubasaPointRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MMarathonQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MMarathonQuestTsubasaPointRewardDTO> findByCriteria(MMarathonQuestTsubasaPointRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MMarathonQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mMarathonQuestTsubasaPointRewardMapper.toDto(mMarathonQuestTsubasaPointRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MMarathonQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonQuestTsubasaPointRewardDTO> findByCriteria(MMarathonQuestTsubasaPointRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MMarathonQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mMarathonQuestTsubasaPointRewardRepository.findAll(specification, page)
            .map(mMarathonQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MMarathonQuestTsubasaPointRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MMarathonQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mMarathonQuestTsubasaPointRewardRepository.count(specification);
    }

    /**
     * Function to convert MMarathonQuestTsubasaPointRewardCriteria to a {@link Specification}.
     */
    private Specification<MMarathonQuestTsubasaPointReward> createSpecification(MMarathonQuestTsubasaPointRewardCriteria criteria) {
        Specification<MMarathonQuestTsubasaPointReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MMarathonQuestTsubasaPointReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MMarathonQuestTsubasaPointReward_.stageId));
            }
            if (criteria.getTsubasaPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTsubasaPoint(), MMarathonQuestTsubasaPointReward_.tsubasaPoint));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MMarathonQuestTsubasaPointReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MMarathonQuestTsubasaPointReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MMarathonQuestTsubasaPointReward_.contentAmount));
            }
        }
        return specification;
    }
}
