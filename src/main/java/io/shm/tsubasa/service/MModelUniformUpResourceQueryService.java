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

import io.shm.tsubasa.domain.MModelUniformUpResource;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MModelUniformUpResourceRepository;
import io.shm.tsubasa.service.dto.MModelUniformUpResourceCriteria;
import io.shm.tsubasa.service.dto.MModelUniformUpResourceDTO;
import io.shm.tsubasa.service.mapper.MModelUniformUpResourceMapper;

/**
 * Service for executing complex queries for {@link MModelUniformUpResource} entities in the database.
 * The main input is a {@link MModelUniformUpResourceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MModelUniformUpResourceDTO} or a {@link Page} of {@link MModelUniformUpResourceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MModelUniformUpResourceQueryService extends QueryService<MModelUniformUpResource> {

    private final Logger log = LoggerFactory.getLogger(MModelUniformUpResourceQueryService.class);

    private final MModelUniformUpResourceRepository mModelUniformUpResourceRepository;

    private final MModelUniformUpResourceMapper mModelUniformUpResourceMapper;

    public MModelUniformUpResourceQueryService(MModelUniformUpResourceRepository mModelUniformUpResourceRepository, MModelUniformUpResourceMapper mModelUniformUpResourceMapper) {
        this.mModelUniformUpResourceRepository = mModelUniformUpResourceRepository;
        this.mModelUniformUpResourceMapper = mModelUniformUpResourceMapper;
    }

    /**
     * Return a {@link List} of {@link MModelUniformUpResourceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MModelUniformUpResourceDTO> findByCriteria(MModelUniformUpResourceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MModelUniformUpResource> specification = createSpecification(criteria);
        return mModelUniformUpResourceMapper.toDto(mModelUniformUpResourceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MModelUniformUpResourceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelUniformUpResourceDTO> findByCriteria(MModelUniformUpResourceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MModelUniformUpResource> specification = createSpecification(criteria);
        return mModelUniformUpResourceRepository.findAll(specification, page)
            .map(mModelUniformUpResourceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MModelUniformUpResourceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MModelUniformUpResource> specification = createSpecification(criteria);
        return mModelUniformUpResourceRepository.count(specification);
    }

    /**
     * Function to convert MModelUniformUpResourceCriteria to a {@link Specification}.
     */
    private Specification<MModelUniformUpResource> createSpecification(MModelUniformUpResourceCriteria criteria) {
        Specification<MModelUniformUpResource> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MModelUniformUpResource_.id));
            }
            if (criteria.getUniformTypeId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformTypeId(), MModelUniformUpResource_.uniformTypeId));
            }
            if (criteria.getDressingType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDressingType(), MModelUniformUpResource_.dressingType));
            }
            if (criteria.getWidth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWidth(), MModelUniformUpResource_.width));
            }
        }
        return specification;
    }
}
