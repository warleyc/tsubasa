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

import io.shm.tsubasa.domain.MActionSkillCutin;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MActionSkillCutinRepository;
import io.shm.tsubasa.service.dto.MActionSkillCutinCriteria;
import io.shm.tsubasa.service.dto.MActionSkillCutinDTO;
import io.shm.tsubasa.service.mapper.MActionSkillCutinMapper;

/**
 * Service for executing complex queries for {@link MActionSkillCutin} entities in the database.
 * The main input is a {@link MActionSkillCutinCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MActionSkillCutinDTO} or a {@link Page} of {@link MActionSkillCutinDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MActionSkillCutinQueryService extends QueryService<MActionSkillCutin> {

    private final Logger log = LoggerFactory.getLogger(MActionSkillCutinQueryService.class);

    private final MActionSkillCutinRepository mActionSkillCutinRepository;

    private final MActionSkillCutinMapper mActionSkillCutinMapper;

    public MActionSkillCutinQueryService(MActionSkillCutinRepository mActionSkillCutinRepository, MActionSkillCutinMapper mActionSkillCutinMapper) {
        this.mActionSkillCutinRepository = mActionSkillCutinRepository;
        this.mActionSkillCutinMapper = mActionSkillCutinMapper;
    }

    /**
     * Return a {@link List} of {@link MActionSkillCutinDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MActionSkillCutinDTO> findByCriteria(MActionSkillCutinCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MActionSkillCutin> specification = createSpecification(criteria);
        return mActionSkillCutinMapper.toDto(mActionSkillCutinRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MActionSkillCutinDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionSkillCutinDTO> findByCriteria(MActionSkillCutinCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MActionSkillCutin> specification = createSpecification(criteria);
        return mActionSkillCutinRepository.findAll(specification, page)
            .map(mActionSkillCutinMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MActionSkillCutinCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MActionSkillCutin> specification = createSpecification(criteria);
        return mActionSkillCutinRepository.count(specification);
    }

    /**
     * Function to convert MActionSkillCutinCriteria to a {@link Specification}.
     */
    private Specification<MActionSkillCutin> createSpecification(MActionSkillCutinCriteria criteria) {
        Specification<MActionSkillCutin> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MActionSkillCutin_.id));
            }
            if (criteria.getActionCutId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionCutId(), MActionSkillCutin_.actionCutId));
            }
            if (criteria.getCharacterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCharacterId(), MActionSkillCutin_.characterId));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getType(), MActionSkillCutin_.type));
            }
            if (criteria.getStartSynchronize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartSynchronize(), MActionSkillCutin_.startSynchronize));
            }
            if (criteria.getFinishSynchronize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinishSynchronize(), MActionSkillCutin_.finishSynchronize));
            }
            if (criteria.getStartDelay() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartDelay(), MActionSkillCutin_.startDelay));
            }
            if (criteria.getChapter1Character() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChapter1Character(), MActionSkillCutin_.chapter1Character));
            }
            if (criteria.getChapter2Character() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChapter2Character(), MActionSkillCutin_.chapter2Character));
            }
            if (criteria.getChapter3Character() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChapter3Character(), MActionSkillCutin_.chapter3Character));
            }
            if (criteria.getChapter4Character() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChapter4Character(), MActionSkillCutin_.chapter4Character));
            }
            if (criteria.getChapter5Character() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChapter5Character(), MActionSkillCutin_.chapter5Character));
            }
        }
        return specification;
    }
}
