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

import io.shm.tsubasa.domain.MActionSkillHolderCardContent;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MActionSkillHolderCardContentRepository;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardContentCriteria;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardContentDTO;
import io.shm.tsubasa.service.mapper.MActionSkillHolderCardContentMapper;

/**
 * Service for executing complex queries for {@link MActionSkillHolderCardContent} entities in the database.
 * The main input is a {@link MActionSkillHolderCardContentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MActionSkillHolderCardContentDTO} or a {@link Page} of {@link MActionSkillHolderCardContentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MActionSkillHolderCardContentQueryService extends QueryService<MActionSkillHolderCardContent> {

    private final Logger log = LoggerFactory.getLogger(MActionSkillHolderCardContentQueryService.class);

    private final MActionSkillHolderCardContentRepository mActionSkillHolderCardContentRepository;

    private final MActionSkillHolderCardContentMapper mActionSkillHolderCardContentMapper;

    public MActionSkillHolderCardContentQueryService(MActionSkillHolderCardContentRepository mActionSkillHolderCardContentRepository, MActionSkillHolderCardContentMapper mActionSkillHolderCardContentMapper) {
        this.mActionSkillHolderCardContentRepository = mActionSkillHolderCardContentRepository;
        this.mActionSkillHolderCardContentMapper = mActionSkillHolderCardContentMapper;
    }

    /**
     * Return a {@link List} of {@link MActionSkillHolderCardContentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MActionSkillHolderCardContentDTO> findByCriteria(MActionSkillHolderCardContentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MActionSkillHolderCardContent> specification = createSpecification(criteria);
        return mActionSkillHolderCardContentMapper.toDto(mActionSkillHolderCardContentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MActionSkillHolderCardContentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionSkillHolderCardContentDTO> findByCriteria(MActionSkillHolderCardContentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MActionSkillHolderCardContent> specification = createSpecification(criteria);
        return mActionSkillHolderCardContentRepository.findAll(specification, page)
            .map(mActionSkillHolderCardContentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MActionSkillHolderCardContentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MActionSkillHolderCardContent> specification = createSpecification(criteria);
        return mActionSkillHolderCardContentRepository.count(specification);
    }

    /**
     * Function to convert MActionSkillHolderCardContentCriteria to a {@link Specification}.
     */
    private Specification<MActionSkillHolderCardContent> createSpecification(MActionSkillHolderCardContentCriteria criteria) {
        Specification<MActionSkillHolderCardContent> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MActionSkillHolderCardContent_.id));
            }
            if (criteria.getTargetCharaId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTargetCharaId(), MActionSkillHolderCardContent_.targetCharaId));
            }
            if (criteria.getActionMasterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionMasterId(), MActionSkillHolderCardContent_.actionMasterId));
            }
            if (criteria.getActionSkillExp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionSkillExp(), MActionSkillHolderCardContent_.actionSkillExp));
            }
            if (criteria.getMcharacterId() != null) {
                specification = specification.and(buildSpecification(criteria.getMcharacterId(),
                    root -> root.join(MActionSkillHolderCardContent_.mcharacter, JoinType.LEFT).get(MCharacter_.id)));
            }
        }
        return specification;
    }
}
