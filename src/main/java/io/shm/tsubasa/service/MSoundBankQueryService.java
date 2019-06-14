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

import io.shm.tsubasa.domain.MSoundBank;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MSoundBankRepository;
import io.shm.tsubasa.service.dto.MSoundBankCriteria;
import io.shm.tsubasa.service.dto.MSoundBankDTO;
import io.shm.tsubasa.service.mapper.MSoundBankMapper;

/**
 * Service for executing complex queries for {@link MSoundBank} entities in the database.
 * The main input is a {@link MSoundBankCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MSoundBankDTO} or a {@link Page} of {@link MSoundBankDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MSoundBankQueryService extends QueryService<MSoundBank> {

    private final Logger log = LoggerFactory.getLogger(MSoundBankQueryService.class);

    private final MSoundBankRepository mSoundBankRepository;

    private final MSoundBankMapper mSoundBankMapper;

    public MSoundBankQueryService(MSoundBankRepository mSoundBankRepository, MSoundBankMapper mSoundBankMapper) {
        this.mSoundBankRepository = mSoundBankRepository;
        this.mSoundBankMapper = mSoundBankMapper;
    }

    /**
     * Return a {@link List} of {@link MSoundBankDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MSoundBankDTO> findByCriteria(MSoundBankCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MSoundBank> specification = createSpecification(criteria);
        return mSoundBankMapper.toDto(mSoundBankRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MSoundBankDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MSoundBankDTO> findByCriteria(MSoundBankCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MSoundBank> specification = createSpecification(criteria);
        return mSoundBankRepository.findAll(specification, page)
            .map(mSoundBankMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MSoundBankCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MSoundBank> specification = createSpecification(criteria);
        return mSoundBankRepository.count(specification);
    }

    /**
     * Function to convert MSoundBankCriteria to a {@link Specification}.
     */
    private Specification<MSoundBank> createSpecification(MSoundBankCriteria criteria) {
        Specification<MSoundBank> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MSoundBank_.id));
            }
            if (criteria.getVersion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVersion(), MSoundBank_.version));
            }
            if (criteria.getFileSize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFileSize(), MSoundBank_.fileSize));
            }
        }
        return specification;
    }
}
