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

import io.shm.tsubasa.domain.MQuestTsubasaPointReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.dto.MQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestTsubasaPointRewardMapper;

/**
 * Service for executing complex queries for {@link MQuestTsubasaPointReward} entities in the database.
 * The main input is a {@link MQuestTsubasaPointRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestTsubasaPointRewardDTO} or a {@link Page} of {@link MQuestTsubasaPointRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestTsubasaPointRewardQueryService extends QueryService<MQuestTsubasaPointReward> {

    private final Logger log = LoggerFactory.getLogger(MQuestTsubasaPointRewardQueryService.class);

    private final MQuestTsubasaPointRewardRepository mQuestTsubasaPointRewardRepository;

    private final MQuestTsubasaPointRewardMapper mQuestTsubasaPointRewardMapper;

    public MQuestTsubasaPointRewardQueryService(MQuestTsubasaPointRewardRepository mQuestTsubasaPointRewardRepository, MQuestTsubasaPointRewardMapper mQuestTsubasaPointRewardMapper) {
        this.mQuestTsubasaPointRewardRepository = mQuestTsubasaPointRewardRepository;
        this.mQuestTsubasaPointRewardMapper = mQuestTsubasaPointRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestTsubasaPointRewardDTO> findByCriteria(MQuestTsubasaPointRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mQuestTsubasaPointRewardMapper.toDto(mQuestTsubasaPointRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestTsubasaPointRewardDTO> findByCriteria(MQuestTsubasaPointRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mQuestTsubasaPointRewardRepository.findAll(specification, page)
            .map(mQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestTsubasaPointRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mQuestTsubasaPointRewardRepository.count(specification);
    }

    /**
     * Function to convert MQuestTsubasaPointRewardCriteria to a {@link Specification}.
     */
    private Specification<MQuestTsubasaPointReward> createSpecification(MQuestTsubasaPointRewardCriteria criteria) {
        Specification<MQuestTsubasaPointReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestTsubasaPointReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MQuestTsubasaPointReward_.stageId));
            }
            if (criteria.getTsubasaPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTsubasaPoint(), MQuestTsubasaPointReward_.tsubasaPoint));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MQuestTsubasaPointReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MQuestTsubasaPointReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MQuestTsubasaPointReward_.contentAmount));
            }
        }
        return specification;
    }
}
