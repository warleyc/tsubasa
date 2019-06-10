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

import io.shm.tsubasa.domain.MCoopRoomStamp;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MCoopRoomStampRepository;
import io.shm.tsubasa.service.dto.MCoopRoomStampCriteria;
import io.shm.tsubasa.service.dto.MCoopRoomStampDTO;
import io.shm.tsubasa.service.mapper.MCoopRoomStampMapper;

/**
 * Service for executing complex queries for {@link MCoopRoomStamp} entities in the database.
 * The main input is a {@link MCoopRoomStampCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MCoopRoomStampDTO} or a {@link Page} of {@link MCoopRoomStampDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MCoopRoomStampQueryService extends QueryService<MCoopRoomStamp> {

    private final Logger log = LoggerFactory.getLogger(MCoopRoomStampQueryService.class);

    private final MCoopRoomStampRepository mCoopRoomStampRepository;

    private final MCoopRoomStampMapper mCoopRoomStampMapper;

    public MCoopRoomStampQueryService(MCoopRoomStampRepository mCoopRoomStampRepository, MCoopRoomStampMapper mCoopRoomStampMapper) {
        this.mCoopRoomStampRepository = mCoopRoomStampRepository;
        this.mCoopRoomStampMapper = mCoopRoomStampMapper;
    }

    /**
     * Return a {@link List} of {@link MCoopRoomStampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MCoopRoomStampDTO> findByCriteria(MCoopRoomStampCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MCoopRoomStamp> specification = createSpecification(criteria);
        return mCoopRoomStampMapper.toDto(mCoopRoomStampRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MCoopRoomStampDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MCoopRoomStampDTO> findByCriteria(MCoopRoomStampCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MCoopRoomStamp> specification = createSpecification(criteria);
        return mCoopRoomStampRepository.findAll(specification, page)
            .map(mCoopRoomStampMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MCoopRoomStampCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MCoopRoomStamp> specification = createSpecification(criteria);
        return mCoopRoomStampRepository.count(specification);
    }

    /**
     * Function to convert MCoopRoomStampCriteria to a {@link Specification}.
     */
    private Specification<MCoopRoomStamp> createSpecification(MCoopRoomStampCriteria criteria) {
        Specification<MCoopRoomStamp> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MCoopRoomStamp_.id));
            }
            if (criteria.getRole() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRole(), MCoopRoomStamp_.role));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MCoopRoomStamp_.orderNum));
            }
            if (criteria.getMasterId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMasterId(), MCoopRoomStamp_.masterId));
            }
        }
        return specification;
    }
}
