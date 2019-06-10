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

import io.shm.tsubasa.domain.MColorPalette;
import io.shm.tsubasa.domain.*; // for static metamodels
import io.shm.tsubasa.repository.MColorPaletteRepository;
import io.shm.tsubasa.service.dto.MColorPaletteCriteria;
import io.shm.tsubasa.service.dto.MColorPaletteDTO;
import io.shm.tsubasa.service.mapper.MColorPaletteMapper;

/**
 * Service for executing complex queries for {@link MColorPalette} entities in the database.
 * The main input is a {@link MColorPaletteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MColorPaletteDTO} or a {@link Page} of {@link MColorPaletteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MColorPaletteQueryService extends QueryService<MColorPalette> {

    private final Logger log = LoggerFactory.getLogger(MColorPaletteQueryService.class);

    private final MColorPaletteRepository mColorPaletteRepository;

    private final MColorPaletteMapper mColorPaletteMapper;

    public MColorPaletteQueryService(MColorPaletteRepository mColorPaletteRepository, MColorPaletteMapper mColorPaletteMapper) {
        this.mColorPaletteRepository = mColorPaletteRepository;
        this.mColorPaletteMapper = mColorPaletteMapper;
    }

    /**
     * Return a {@link List} of {@link MColorPaletteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MColorPaletteDTO> findByCriteria(MColorPaletteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MColorPalette> specification = createSpecification(criteria);
        return mColorPaletteMapper.toDto(mColorPaletteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MColorPaletteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MColorPaletteDTO> findByCriteria(MColorPaletteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MColorPalette> specification = createSpecification(criteria);
        return mColorPaletteRepository.findAll(specification, page)
            .map(mColorPaletteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MColorPaletteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MColorPalette> specification = createSpecification(criteria);
        return mColorPaletteRepository.count(specification);
    }

    /**
     * Function to convert MColorPaletteCriteria to a {@link Specification}.
     */
    private Specification<MColorPalette> createSpecification(MColorPaletteCriteria criteria) {
        Specification<MColorPalette> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MColorPalette_.id));
            }
            if (criteria.getOrderNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderNum(), MColorPalette_.orderNum));
            }
        }
        return specification;
    }
}
