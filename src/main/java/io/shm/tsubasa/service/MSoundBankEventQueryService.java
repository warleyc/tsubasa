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

import io.shm.tsubasa.domain.MSoundBankEvent;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MSoundBankEventRepository;
import io.shm.tsubasa.service.dto.MSoundBankEventCriteria;
import io.shm.tsubasa.service.dto.MSoundBankEventDTO;
import io.shm.tsubasa.service.mapper.MSoundBankEventMapper;

/**
 * Service for executing complex queries for {@link MSoundBankEvent} entities in the database.
 * The main input is a {@link MSoundBankEventCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MSoundBankEventDTO} or a {@link Page} of {@link MSoundBankEventDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MSoundBankEventQueryService extends QueryService<MSoundBankEvent> {

    private final Logger log = LoggerFactory.getLogger(MSoundBankEventQueryService.class);

    private final MSoundBankEventRepository mSoundBankEventRepository;

    private final MSoundBankEventMapper mSoundBankEventMapper;

    public MSoundBankEventQueryService(MSoundBankEventRepository mSoundBankEventRepository, MSoundBankEventMapper mSoundBankEventMapper) {
        this.mSoundBankEventRepository = mSoundBankEventRepository;
        this.mSoundBankEventMapper = mSoundBankEventMapper;
    }

    /**
     * Return a {@link List} of {@link MSoundBankEventDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MSoundBankEventDTO> findByCriteria(MSoundBankEventCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MSoundBankEvent> specification = createSpecification(criteria);
        return mSoundBankEventMapper.toDto(mSoundBankEventRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MSoundBankEventDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MSoundBankEventDTO> findByCriteria(MSoundBankEventCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MSoundBankEvent> specification = createSpecification(criteria);
        return mSoundBankEventRepository.findAll(specification, page)
            .map(mSoundBankEventMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MSoundBankEventCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MSoundBankEvent> specification = createSpecification(criteria);
        return mSoundBankEventRepository.count(specification);
    }

    /**
     * Function to convert MSoundBankEventCriteria to a {@link Specification}.
     */
    private Specification<MSoundBankEvent> createSpecification(MSoundBankEventCriteria criteria) {
        Specification<MSoundBankEvent> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MSoundBankEvent_.id));
            }
        }
        return specification;
    }
}
