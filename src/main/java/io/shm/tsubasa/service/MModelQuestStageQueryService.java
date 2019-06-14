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

import io.shm.tsubasa.domain.MModelQuestStage;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MModelQuestStageRepository;
import io.shm.tsubasa.service.dto.MModelQuestStageCriteria;
import io.shm.tsubasa.service.dto.MModelQuestStageDTO;
import io.shm.tsubasa.service.mapper.MModelQuestStageMapper;

/**
 * Service for executing complex queries for {@link MModelQuestStage} entities in the database.
 * The main input is a {@link MModelQuestStageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MModelQuestStageDTO} or a {@link Page} of {@link MModelQuestStageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MModelQuestStageQueryService extends QueryService<MModelQuestStage> {

    private final Logger log = LoggerFactory.getLogger(MModelQuestStageQueryService.class);

    private final MModelQuestStageRepository mModelQuestStageRepository;

    private final MModelQuestStageMapper mModelQuestStageMapper;

    public MModelQuestStageQueryService(MModelQuestStageRepository mModelQuestStageRepository, MModelQuestStageMapper mModelQuestStageMapper) {
        this.mModelQuestStageRepository = mModelQuestStageRepository;
        this.mModelQuestStageMapper = mModelQuestStageMapper;
    }

    /**
     * Return a {@link List} of {@link MModelQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MModelQuestStageDTO> findByCriteria(MModelQuestStageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MModelQuestStage> specification = createSpecification(criteria);
        return mModelQuestStageMapper.toDto(mModelQuestStageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MModelQuestStageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelQuestStageDTO> findByCriteria(MModelQuestStageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MModelQuestStage> specification = createSpecification(criteria);
        return mModelQuestStageRepository.findAll(specification, page)
            .map(mModelQuestStageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MModelQuestStageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MModelQuestStage> specification = createSpecification(criteria);
        return mModelQuestStageRepository.count(specification);
    }

    /**
     * Function to convert MModelQuestStageCriteria to a {@link Specification}.
     */
    private Specification<MModelQuestStage> createSpecification(MModelQuestStageCriteria criteria) {
        Specification<MModelQuestStage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MModelQuestStage_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MModelQuestStage_.stageId));
            }
        }
        return specification;
    }
}
