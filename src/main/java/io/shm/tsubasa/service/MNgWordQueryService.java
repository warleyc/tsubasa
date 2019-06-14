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

import io.shm.tsubasa.domain.MNgWord;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MNgWordRepository;
import io.shm.tsubasa.service.dto.MNgWordCriteria;
import io.shm.tsubasa.service.dto.MNgWordDTO;
import io.shm.tsubasa.service.mapper.MNgWordMapper;

/**
 * Service for executing complex queries for {@link MNgWord} entities in the database.
 * The main input is a {@link MNgWordCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MNgWordDTO} or a {@link Page} of {@link MNgWordDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MNgWordQueryService extends QueryService<MNgWord> {

    private final Logger log = LoggerFactory.getLogger(MNgWordQueryService.class);

    private final MNgWordRepository mNgWordRepository;

    private final MNgWordMapper mNgWordMapper;

    public MNgWordQueryService(MNgWordRepository mNgWordRepository, MNgWordMapper mNgWordMapper) {
        this.mNgWordRepository = mNgWordRepository;
        this.mNgWordMapper = mNgWordMapper;
    }

    /**
     * Return a {@link List} of {@link MNgWordDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MNgWordDTO> findByCriteria(MNgWordCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MNgWord> specification = createSpecification(criteria);
        return mNgWordMapper.toDto(mNgWordRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MNgWordDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MNgWordDTO> findByCriteria(MNgWordCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MNgWord> specification = createSpecification(criteria);
        return mNgWordRepository.findAll(specification, page)
            .map(mNgWordMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MNgWordCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MNgWord> specification = createSpecification(criteria);
        return mNgWordRepository.count(specification);
    }

    /**
     * Function to convert MNgWordCriteria to a {@link Specification}.
     */
    private Specification<MNgWord> createSpecification(MNgWordCriteria criteria) {
        Specification<MNgWord> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MNgWord_.id));
            }
            if (criteria.getJudgeType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getJudgeType(), MNgWord_.judgeType));
            }
        }
        return specification;
    }
}
