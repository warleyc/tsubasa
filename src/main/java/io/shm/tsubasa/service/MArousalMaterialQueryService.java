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

import io.shm.tsubasa.domain.MArousalMaterial;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MArousalMaterialRepository;
import io.shm.tsubasa.service.dto.MArousalMaterialCriteria;
import io.shm.tsubasa.service.dto.MArousalMaterialDTO;
import io.shm.tsubasa.service.mapper.MArousalMaterialMapper;

/**
 * Service for executing complex queries for {@link MArousalMaterial} entities in the database.
 * The main input is a {@link MArousalMaterialCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MArousalMaterialDTO} or a {@link Page} of {@link MArousalMaterialDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MArousalMaterialQueryService extends QueryService<MArousalMaterial> {

    private final Logger log = LoggerFactory.getLogger(MArousalMaterialQueryService.class);

    private final MArousalMaterialRepository mArousalMaterialRepository;

    private final MArousalMaterialMapper mArousalMaterialMapper;

    public MArousalMaterialQueryService(MArousalMaterialRepository mArousalMaterialRepository, MArousalMaterialMapper mArousalMaterialMapper) {
        this.mArousalMaterialRepository = mArousalMaterialRepository;
        this.mArousalMaterialMapper = mArousalMaterialMapper;
    }

    /**
     * Return a {@link List} of {@link MArousalMaterialDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MArousalMaterialDTO> findByCriteria(MArousalMaterialCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MArousalMaterial> specification = createSpecification(criteria);
        return mArousalMaterialMapper.toDto(mArousalMaterialRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MArousalMaterialDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MArousalMaterialDTO> findByCriteria(MArousalMaterialCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MArousalMaterial> specification = createSpecification(criteria);
        return mArousalMaterialRepository.findAll(specification, page)
            .map(mArousalMaterialMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MArousalMaterialCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MArousalMaterial> specification = createSpecification(criteria);
        return mArousalMaterialRepository.count(specification);
    }

    /**
     * Function to convert MArousalMaterialCriteria to a {@link Specification}.
     */
    private Specification<MArousalMaterial> createSpecification(MArousalMaterialCriteria criteria) {
        Specification<MArousalMaterial> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MArousalMaterial_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MArousalMaterial_.groupId));
            }
            if (criteria.getContentType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentType(), MArousalMaterial_.contentType));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentId(), MArousalMaterial_.contentId));
            }
            if (criteria.getContentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContentAmount(), MArousalMaterial_.contentAmount));
            }
            if (criteria.getMainActionLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMainActionLevel(), MArousalMaterial_.mainActionLevel));
            }
        }
        return specification;
    }
}
