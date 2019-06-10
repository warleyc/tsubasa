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

import io.shm.tsubasa.domain.MActionSkillHolderCardCt;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MActionSkillHolderCardCtRepository;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardCtCriteria;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardCtDTO;
import io.shm.tsubasa.service.mapper.MActionSkillHolderCardCtMapper;

/**
 * Service for executing complex queries for {@link MActionSkillHolderCardCt} entities in the database.
 * The main input is a {@link MActionSkillHolderCardCtCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MActionSkillHolderCardCtDTO} or a {@link Page} of {@link MActionSkillHolderCardCtDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MActionSkillHolderCardCtQueryService extends QueryService<MActionSkillHolderCardCt> {

    private final Logger log = LoggerFactory.getLogger(MActionSkillHolderCardCtQueryService.class);

    private final MActionSkillHolderCardCtRepository mActionSkillHolderCardCtRepository;

    private final MActionSkillHolderCardCtMapper mActionSkillHolderCardCtMapper;

    public MActionSkillHolderCardCtQueryService(MActionSkillHolderCardCtRepository mActionSkillHolderCardCtRepository, MActionSkillHolderCardCtMapper mActionSkillHolderCardCtMapper) {
        this.mActionSkillHolderCardCtRepository = mActionSkillHolderCardCtRepository;
        this.mActionSkillHolderCardCtMapper = mActionSkillHolderCardCtMapper;
    }

    /**
     * Return a {@link List} of {@link MActionSkillHolderCardCtDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MActionSkillHolderCardCtDTO> findByCriteria(MActionSkillHolderCardCtCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MActionSkillHolderCardCt> specification = createSpecification(criteria);
        return mActionSkillHolderCardCtMapper.toDto(mActionSkillHolderCardCtRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MActionSkillHolderCardCtDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionSkillHolderCardCtDTO> findByCriteria(MActionSkillHolderCardCtCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MActionSkillHolderCardCt> specification = createSpecification(criteria);
        return mActionSkillHolderCardCtRepository.findAll(specification, page)
            .map(mActionSkillHolderCardCtMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MActionSkillHolderCardCtCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MActionSkillHolderCardCt> specification = createSpecification(criteria);
        return mActionSkillHolderCardCtRepository.count(specification);
    }

    /**
     * Function to convert MActionSkillHolderCardCtCriteria to a {@link Specification}.
     */
    private Specification<MActionSkillHolderCardCt> createSpecification(MActionSkillHolderCardCtCriteria criteria) {
        Specification<MActionSkillHolderCardCt> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MActionSkillHolderCardCt_.id));
            }
            if (criteria.getTargetCharaId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharaId(), MActionSkillHolderCardCt_.targetCharaId));
            }
            if (criteria.getActionMasterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionMasterId(), MActionSkillHolderCardCt_.actionMasterId));
            }
            if (criteria.getActionSkillExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionSkillExp(), MActionSkillHolderCardCt_.actionSkillExp));
            }
            if (criteria.getIdId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdId(),
                    root -> root.join(MActionSkillHolderCardCt_.id, JoinType.LEFT).get(MCharacter_.id)));
            }
        }
        return specification;
    }
}
