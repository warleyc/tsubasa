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

import io.shm.tsubasa.domain.MAnnounceText;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MAnnounceTextRepository;
import io.shm.tsubasa.service.dto.MAnnounceTextCriteria;
import io.shm.tsubasa.service.dto.MAnnounceTextDTO;
import io.shm.tsubasa.service.mapper.MAnnounceTextMapper;

/**
 * Service for executing complex queries for {@link MAnnounceText} entities in the database.
 * The main input is a {@link MAnnounceTextCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MAnnounceTextDTO} or a {@link Page} of {@link MAnnounceTextDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MAnnounceTextQueryService extends QueryService<MAnnounceText> {

    private final Logger log = LoggerFactory.getLogger(MAnnounceTextQueryService.class);

    private final MAnnounceTextRepository mAnnounceTextRepository;

    private final MAnnounceTextMapper mAnnounceTextMapper;

    public MAnnounceTextQueryService(MAnnounceTextRepository mAnnounceTextRepository, MAnnounceTextMapper mAnnounceTextMapper) {
        this.mAnnounceTextRepository = mAnnounceTextRepository;
        this.mAnnounceTextMapper = mAnnounceTextMapper;
    }

    /**
     * Return a {@link List} of {@link MAnnounceTextDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MAnnounceTextDTO> findByCriteria(MAnnounceTextCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MAnnounceText> specification = createSpecification(criteria);
        return mAnnounceTextMapper.toDto(mAnnounceTextRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MAnnounceTextDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MAnnounceTextDTO> findByCriteria(MAnnounceTextCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MAnnounceText> specification = createSpecification(criteria);
        return mAnnounceTextRepository.findAll(specification, page)
            .map(mAnnounceTextMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MAnnounceTextCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MAnnounceText> specification = createSpecification(criteria);
        return mAnnounceTextRepository.count(specification);
    }

    /**
     * Function to convert MAnnounceTextCriteria to a {@link Specification}.
     */
    private Specification<MAnnounceText> createSpecification(MAnnounceTextCriteria criteria) {
        Specification<MAnnounceText> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MAnnounceText_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MAnnounceText_.groupId));
            }
            if (criteria.getSeqNo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeqNo(), MAnnounceText_.seqNo));
            }
            if (criteria.getDelayTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDelayTime(), MAnnounceText_.delayTime));
            }
            if (criteria.getContinueTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getContinueTime(), MAnnounceText_.continueTime));
            }
        }
        return specification;
    }
}
