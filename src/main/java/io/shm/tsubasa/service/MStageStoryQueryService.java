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

import io.shm.tsubasa.domain.MStageStory;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MStageStoryRepository;
import io.shm.tsubasa.service.dto.MStageStoryCriteria;
import io.shm.tsubasa.service.dto.MStageStoryDTO;
import io.shm.tsubasa.service.mapper.MStageStoryMapper;

/**
 * Service for executing complex queries for {@link MStageStory} entities in the database.
 * The main input is a {@link MStageStoryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MStageStoryDTO} or a {@link Page} of {@link MStageStoryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MStageStoryQueryService extends QueryService<MStageStory> {

    private final Logger log = LoggerFactory.getLogger(MStageStoryQueryService.class);

    private final MStageStoryRepository mStageStoryRepository;

    private final MStageStoryMapper mStageStoryMapper;

    public MStageStoryQueryService(MStageStoryRepository mStageStoryRepository, MStageStoryMapper mStageStoryMapper) {
        this.mStageStoryRepository = mStageStoryRepository;
        this.mStageStoryMapper = mStageStoryMapper;
    }

    /**
     * Return a {@link List} of {@link MStageStoryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MStageStoryDTO> findByCriteria(MStageStoryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MStageStory> specification = createSpecification(criteria);
        return mStageStoryMapper.toDto(mStageStoryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MStageStoryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MStageStoryDTO> findByCriteria(MStageStoryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MStageStory> specification = createSpecification(criteria);
        return mStageStoryRepository.findAll(specification, page)
            .map(mStageStoryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MStageStoryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MStageStory> specification = createSpecification(criteria);
        return mStageStoryRepository.count(specification);
    }

    /**
     * Function to convert MStageStoryCriteria to a {@link Specification}.
     */
    private Specification<MStageStory> createSpecification(MStageStoryCriteria criteria) {
        Specification<MStageStory> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MStageStory_.id));
            }
            if (criteria.getStageId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStageId(), MStageStory_.stageId));
            }
            if (criteria.getEventType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventType(), MStageStory_.eventType));
            }
        }
        return specification;
    }
}
