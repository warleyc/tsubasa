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

import io.shm.tsubasa.domain.MQuestCoop;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MQuestCoopRepository;
import io.shm.tsubasa.service.dto.MQuestCoopCriteria;
import io.shm.tsubasa.service.dto.MQuestCoopDTO;
import io.shm.tsubasa.service.mapper.MQuestCoopMapper;

/**
 * Service for executing complex queries for {@link MQuestCoop} entities in the database.
 * The main input is a {@link MQuestCoopCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MQuestCoopDTO} or a {@link Page} of {@link MQuestCoopDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MQuestCoopQueryService extends QueryService<MQuestCoop> {

    private final Logger log = LoggerFactory.getLogger(MQuestCoopQueryService.class);

    private final MQuestCoopRepository mQuestCoopRepository;

    private final MQuestCoopMapper mQuestCoopMapper;

    public MQuestCoopQueryService(MQuestCoopRepository mQuestCoopRepository, MQuestCoopMapper mQuestCoopMapper) {
        this.mQuestCoopRepository = mQuestCoopRepository;
        this.mQuestCoopMapper = mQuestCoopMapper;
    }

    /**
     * Return a {@link List} of {@link MQuestCoopDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MQuestCoopDTO> findByCriteria(MQuestCoopCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MQuestCoop> specification = createSpecification(criteria);
        return mQuestCoopMapper.toDto(mQuestCoopRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MQuestCoopDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestCoopDTO> findByCriteria(MQuestCoopCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MQuestCoop> specification = createSpecification(criteria);
        return mQuestCoopRepository.findAll(specification, page)
            .map(mQuestCoopMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MQuestCoopCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MQuestCoop> specification = createSpecification(criteria);
        return mQuestCoopRepository.count(specification);
    }

    /**
     * Function to convert MQuestCoopCriteria to a {@link Specification}.
     */
    private Specification<MQuestCoop> createSpecification(MQuestCoopCriteria criteria) {
        Specification<MQuestCoop> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MQuestCoop_.id));
            }
            if (criteria.getGroupId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGroupId(), MQuestCoop_.groupId));
            }
            if (criteria.getEffectRarity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectRarity(), MQuestCoop_.effectRarity));
            }
            if (criteria.getEffectLevelFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectLevelFrom(), MQuestCoop_.effectLevelFrom));
            }
            if (criteria.getEffectLevelTo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectLevelTo(), MQuestCoop_.effectLevelTo));
            }
            if (criteria.getChoose1Weight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChoose1Weight(), MQuestCoop_.choose1Weight));
            }
            if (criteria.getChoose2Weight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChoose2Weight(), MQuestCoop_.choose2Weight));
            }
        }
        return specification;
    }
}
