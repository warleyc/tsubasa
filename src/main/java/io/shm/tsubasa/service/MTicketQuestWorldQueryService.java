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

import io.shm.tsubasa.domain.MTicketQuestWorld;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MTicketQuestWorldRepository;
import io.shm.tsubasa.service.dto.MTicketQuestWorldCriteria;
import io.shm.tsubasa.service.dto.MTicketQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestWorldMapper;

/**
 * Service for executing complex queries for {@link MTicketQuestWorld} entities in the database.
 * The main input is a {@link MTicketQuestWorldCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MTicketQuestWorldDTO} or a {@link Page} of {@link MTicketQuestWorldDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MTicketQuestWorldQueryService extends QueryService<MTicketQuestWorld> {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestWorldQueryService.class);

    private final MTicketQuestWorldRepository mTicketQuestWorldRepository;

    private final MTicketQuestWorldMapper mTicketQuestWorldMapper;

    public MTicketQuestWorldQueryService(MTicketQuestWorldRepository mTicketQuestWorldRepository, MTicketQuestWorldMapper mTicketQuestWorldMapper) {
        this.mTicketQuestWorldRepository = mTicketQuestWorldRepository;
        this.mTicketQuestWorldMapper = mTicketQuestWorldMapper;
    }

    /**
     * Return a {@link List} of {@link MTicketQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MTicketQuestWorldDTO> findByCriteria(MTicketQuestWorldCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MTicketQuestWorld> specification = createSpecification(criteria);
        return mTicketQuestWorldMapper.toDto(mTicketQuestWorldRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MTicketQuestWorldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MTicketQuestWorldDTO> findByCriteria(MTicketQuestWorldCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MTicketQuestWorld> specification = createSpecification(criteria);
        return mTicketQuestWorldRepository.findAll(specification, page)
            .map(mTicketQuestWorldMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MTicketQuestWorldCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MTicketQuestWorld> specification = createSpecification(criteria);
        return mTicketQuestWorldRepository.count(specification);
    }

    /**
     * Function to convert MTicketQuestWorldCriteria to a {@link Specification}.
     */
    private Specification<MTicketQuestWorld> createSpecification(MTicketQuestWorldCriteria criteria) {
        Specification<MTicketQuestWorld> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MTicketQuestWorld_.id));
            }
            if (criteria.getSetId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSetId(), MTicketQuestWorld_.setId));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), MTicketQuestWorld_.number));
            }
            if (criteria.getStageUnlockPattern() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageUnlockPattern(), MTicketQuestWorld_.stageUnlockPattern));
            }
            if (criteria.getSpecialRewardContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentType(), MTicketQuestWorld_.specialRewardContentType));
            }
            if (criteria.getSpecialRewardContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSpecialRewardContentId(), MTicketQuestWorld_.specialRewardContentId));
            }
            if (criteria.getIsEnableCoop() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsEnableCoop(), MTicketQuestWorld_.isEnableCoop));
            }
            if (criteria.getIsHideDoNotHavingTicket() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsHideDoNotHavingTicket(), MTicketQuestWorld_.isHideDoNotHavingTicket));
            }
            if (criteria.getMTicketQuestStageId() != null) {
                specification = specification.and(buildSpecification(criteria.getMTicketQuestStageId(),
                    root -> root.join(MTicketQuestWorld_.mTicketQuestStages, JoinType.LEFT).get(MTicketQuestStage_.id)));
            }
        }
        return specification;
    }
}
