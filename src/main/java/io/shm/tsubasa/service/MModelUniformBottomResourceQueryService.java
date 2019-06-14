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

import io.shm.tsubasa.domain.MModelUniformBottomResource;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MModelUniformBottomResourceRepository;
import io.shm.tsubasa.service.dto.MModelUniformBottomResourceCriteria;
import io.shm.tsubasa.service.dto.MModelUniformBottomResourceDTO;
import io.shm.tsubasa.service.mapper.MModelUniformBottomResourceMapper;

/**
 * Service for executing complex queries for {@link MModelUniformBottomResource} entities in the database.
 * The main input is a {@link MModelUniformBottomResourceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MModelUniformBottomResourceDTO} or a {@link Page} of {@link MModelUniformBottomResourceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MModelUniformBottomResourceQueryService extends QueryService<MModelUniformBottomResource> {

    private final Logger log = LoggerFactory.getLogger(MModelUniformBottomResourceQueryService.class);

    private final MModelUniformBottomResourceRepository mModelUniformBottomResourceRepository;

    private final MModelUniformBottomResourceMapper mModelUniformBottomResourceMapper;

    public MModelUniformBottomResourceQueryService(MModelUniformBottomResourceRepository mModelUniformBottomResourceRepository, MModelUniformBottomResourceMapper mModelUniformBottomResourceMapper) {
        this.mModelUniformBottomResourceRepository = mModelUniformBottomResourceRepository;
        this.mModelUniformBottomResourceMapper = mModelUniformBottomResourceMapper;
    }

    /**
     * Return a {@link List} of {@link MModelUniformBottomResourceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MModelUniformBottomResourceDTO> findByCriteria(MModelUniformBottomResourceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MModelUniformBottomResource> specification = createSpecification(criteria);
        return mModelUniformBottomResourceMapper.toDto(mModelUniformBottomResourceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MModelUniformBottomResourceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelUniformBottomResourceDTO> findByCriteria(MModelUniformBottomResourceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MModelUniformBottomResource> specification = createSpecification(criteria);
        return mModelUniformBottomResourceRepository.findAll(specification, page)
            .map(mModelUniformBottomResourceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MModelUniformBottomResourceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MModelUniformBottomResource> specification = createSpecification(criteria);
        return mModelUniformBottomResourceRepository.count(specification);
    }

    /**
     * Function to convert MModelUniformBottomResourceCriteria to a {@link Specification}.
     */
    private Specification<MModelUniformBottomResource> createSpecification(MModelUniformBottomResourceCriteria criteria) {
        Specification<MModelUniformBottomResource> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MModelUniformBottomResource_.id));
            }
            if (criteria.getUniformTypeId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformTypeId(), MModelUniformBottomResource_.uniformTypeId));
            }
            if (criteria.getDressingType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDressingType(), MModelUniformBottomResource_.dressingType));
            }
            if (criteria.getWidth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWidth(), MModelUniformBottomResource_.width));
            }
        }
        return specification;
    }
}
