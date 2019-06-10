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

import io.shm.tsubasa.domain.MAchievement;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MAchievementRepository;
import io.shm.tsubasa.service.dto.MAchievementCriteria;
import io.shm.tsubasa.service.dto.MAchievementDTO;
import io.shm.tsubasa.service.mapper.MAchievementMapper;

/**
 * Service for executing complex queries for {@link MAchievement} entities in the database.
 * The main input is a {@link MAchievementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MAchievementDTO} or a {@link Page} of {@link MAchievementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MAchievementQueryService extends QueryService<MAchievement> {

    private final Logger log = LoggerFactory.getLogger(MAchievementQueryService.class);

    private final MAchievementRepository mAchievementRepository;

    private final MAchievementMapper mAchievementMapper;

    public MAchievementQueryService(MAchievementRepository mAchievementRepository, MAchievementMapper mAchievementMapper) {
        this.mAchievementRepository = mAchievementRepository;
        this.mAchievementMapper = mAchievementMapper;
    }

    /**
     * Return a {@link List} of {@link MAchievementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MAchievementDTO> findByCriteria(MAchievementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MAchievement> specification = createSpecification(criteria);
        return mAchievementMapper.toDto(mAchievementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MAchievementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MAchievementDTO> findByCriteria(MAchievementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MAchievement> specification = createSpecification(criteria);
        return mAchievementRepository.findAll(specification, page)
            .map(mAchievementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MAchievementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MAchievement> specification = createSpecification(criteria);
        return mAchievementRepository.count(specification);
    }

    /**
     * Function to convert MAchievementCriteria to a {@link Specification}.
     */
    private Specification<MAchievement> createSpecification(MAchievementCriteria criteria) {
        Specification<MAchievement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MAchievement_.id));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getType(), MAchievement_.type));
            }
            if (criteria.getMissionId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMissionId(), MAchievement_.missionId));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MAchievement_.id, JoinType.LEFT).get(MMission_.id)));
            }
        }
        return specification;
    }
}
