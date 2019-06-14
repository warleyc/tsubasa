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

import io.shm.tsubasa.domain.MTicketQuestTsubasaPointReward;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTicketQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MTicketQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.dto.MTicketQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestTsubasaPointRewardMapper;

/**
 * Service for executing complex queries for {@link MTicketQuestTsubasaPointReward} entities in the database.
 * The main input is a {@link MTicketQuestTsubasaPointRewardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTicketQuestTsubasaPointRewardDTO} or a {@link Page} of {@link MTicketQuestTsubasaPointRewardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTicketQuestTsubasaPointRewardQueryService extends QueryService<MTicketQuestTsubasaPointReward> {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestTsubasaPointRewardQueryService.class);

    private final MTicketQuestTsubasaPointRewardRepository mTicketQuestTsubasaPointRewardRepository;

    private final MTicketQuestTsubasaPointRewardMapper mTicketQuestTsubasaPointRewardMapper;

    public MTicketQuestTsubasaPointRewardQueryService(MTicketQuestTsubasaPointRewardRepository mTicketQuestTsubasaPointRewardRepository, MTicketQuestTsubasaPointRewardMapper mTicketQuestTsubasaPointRewardMapper) {
        this.mTicketQuestTsubasaPointRewardRepository = mTicketQuestTsubasaPointRewardRepository;
        this.mTicketQuestTsubasaPointRewardMapper = mTicketQuestTsubasaPointRewardMapper;
    }

    /**
     * Return a {@link List} of {@link MTicketQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTicketQuestTsubasaPointRewardDTO> findByCriteria(MTicketQuestTsubasaPointRewardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTicketQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mTicketQuestTsubasaPointRewardMapper.toDto(mTicketQuestTsubasaPointRewardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTicketQuestTsubasaPointRewardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTicketQuestTsubasaPointRewardDTO> findByCriteria(MTicketQuestTsubasaPointRewardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTicketQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mTicketQuestTsubasaPointRewardRepository.findAll(specification, page)
            .map(mTicketQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTicketQuestTsubasaPointRewardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTicketQuestTsubasaPointReward> specification = createSpecification(criteria);
        return mTicketQuestTsubasaPointRewardRepository.count(specification);
    }

    /**
     * Function to convert MTicketQuestTsubasaPointRewardCriteria to a {@link Specification}.
     */
    private Specification<MTicketQuestTsubasaPointReward> createSpecification(MTicketQuestTsubasaPointRewardCriteria criteria) {
        Specification<MTicketQuestTsubasaPointReward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTicketQuestTsubasaPointReward_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MTicketQuestTsubasaPointReward_.stageId));
            }
            if (criteria.getTsubasaPoint() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTsubasaPoint(), MTicketQuestTsubasaPointReward_.tsubasaPoint));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MTicketQuestTsubasaPointReward_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MTicketQuestTsubasaPointReward_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MTicketQuestTsubasaPointReward_.contentAmount));
            }
        }
        return specification;
    }
}
