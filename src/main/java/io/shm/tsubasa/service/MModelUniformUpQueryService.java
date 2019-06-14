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

import io.shm.tsubasa.domain.MModelUniformUp;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MModelUniformUpRepository;
import io.shm.tsubasa.service.dto.MModelUniformUpCriteria;
import io.shm.tsubasa.service.dto.MModelUniformUpDTO;
import io.shm.tsubasa.service.mapper.MModelUniformUpMapper;

/**
 * Service for executing complex queries for {@link MModelUniformUp} entities in the database.
 * The main input is a {@link MModelUniformUpCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MModelUniformUpDTO} or a {@link Page} of {@link MModelUniformUpDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MModelUniformUpQueryService extends QueryService<MModelUniformUp> {

    private final Logger log = LoggerFactory.getLogger(MModelUniformUpQueryService.class);

    private final MModelUniformUpRepository mModelUniformUpRepository;

    private final MModelUniformUpMapper mModelUniformUpMapper;

    public MModelUniformUpQueryService(MModelUniformUpRepository mModelUniformUpRepository, MModelUniformUpMapper mModelUniformUpMapper) {
        this.mModelUniformUpRepository = mModelUniformUpRepository;
        this.mModelUniformUpMapper = mModelUniformUpMapper;
    }

    /**
     * Return a {@link List} of {@link MModelUniformUpDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MModelUniformUpDTO> findByCriteria(MModelUniformUpCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MModelUniformUp> specification = createSpecification(criteria);
        return mModelUniformUpMapper.toDto(mModelUniformUpRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MModelUniformUpDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelUniformUpDTO> findByCriteria(MModelUniformUpCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MModelUniformUp> specification = createSpecification(criteria);
        return mModelUniformUpRepository.findAll(specification, page)
            .map(mModelUniformUpMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MModelUniformUpCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MModelUniformUp> specification = createSpecification(criteria);
        return mModelUniformUpRepository.count(specification);
    }

    /**
     * Function to convert MModelUniformUpCriteria to a {@link Specification}.
     */
    private Specification<MModelUniformUp> createSpecification(MModelUniformUpCriteria criteria) {
        Specification<MModelUniformUp> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MModelUniformUp_.id));
            }
            if (criteria.getUniformUpId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformUpId(), MModelUniformUp_.uniformUpId));
            }
            if (criteria.getDefaultDressingType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDefaultDressingType(), MModelUniformUp_.defaultDressingType));
            }
            if (criteria.getUniformModel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformModel(), MModelUniformUp_.uniformModel));
            }
            if (criteria.getUniformTexture() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformTexture(), MModelUniformUp_.uniformTexture));
            }
            if (criteria.getUniformNoTexture() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformNoTexture(), MModelUniformUp_.uniformNoTexture));
            }
            if (criteria.getNumberChest() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberChest(), MModelUniformUp_.numberChest));
            }
            if (criteria.getNumberBelly() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberBelly(), MModelUniformUp_.numberBelly));
            }
            if (criteria.getNumberBack() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberBack(), MModelUniformUp_.numberBack));
            }
            if (criteria.getUniformNoPositionX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformNoPositionX(), MModelUniformUp_.uniformNoPositionX));
            }
            if (criteria.getUniformNoPositionY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUniformNoPositionY(), MModelUniformUp_.uniformNoPositionY));
            }
        }
        return specification;
    }
}
