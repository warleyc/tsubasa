package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MRecommendFormationFilterService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterDTO;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterCriteria;
import io.shm.tsubasa.service.MRecommendFormationFilterQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.shm.tsubasa.domain.MRecommendFormationFilter}.
 */
@RestController
@RequestMapping("/api")
public class MRecommendFormationFilterResource {

    private final Logger log = LoggerFactory.getLogger(MRecommendFormationFilterResource.class);

    private static final String ENTITY_NAME = "mRecommendFormationFilter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MRecommendFormationFilterService mRecommendFormationFilterService;

    private final MRecommendFormationFilterQueryService mRecommendFormationFilterQueryService;

    public MRecommendFormationFilterResource(MRecommendFormationFilterService mRecommendFormationFilterService, MRecommendFormationFilterQueryService mRecommendFormationFilterQueryService) {
        this.mRecommendFormationFilterService = mRecommendFormationFilterService;
        this.mRecommendFormationFilterQueryService = mRecommendFormationFilterQueryService;
    }

    /**
     * {@code POST  /m-recommend-formation-filters} : Create a new mRecommendFormationFilter.
     *
     * @param mRecommendFormationFilterDTO the mRecommendFormationFilterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mRecommendFormationFilterDTO, or with status {@code 400 (Bad Request)} if the mRecommendFormationFilter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-recommend-formation-filters")
    public ResponseEntity<MRecommendFormationFilterDTO> createMRecommendFormationFilter(@Valid @RequestBody MRecommendFormationFilterDTO mRecommendFormationFilterDTO) throws URISyntaxException {
        log.debug("REST request to save MRecommendFormationFilter : {}", mRecommendFormationFilterDTO);
        if (mRecommendFormationFilterDTO.getId() != null) {
            throw new BadRequestAlertException("A new mRecommendFormationFilter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MRecommendFormationFilterDTO result = mRecommendFormationFilterService.save(mRecommendFormationFilterDTO);
        return ResponseEntity.created(new URI("/api/m-recommend-formation-filters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-recommend-formation-filters} : Updates an existing mRecommendFormationFilter.
     *
     * @param mRecommendFormationFilterDTO the mRecommendFormationFilterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mRecommendFormationFilterDTO,
     * or with status {@code 400 (Bad Request)} if the mRecommendFormationFilterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mRecommendFormationFilterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-recommend-formation-filters")
    public ResponseEntity<MRecommendFormationFilterDTO> updateMRecommendFormationFilter(@Valid @RequestBody MRecommendFormationFilterDTO mRecommendFormationFilterDTO) throws URISyntaxException {
        log.debug("REST request to update MRecommendFormationFilter : {}", mRecommendFormationFilterDTO);
        if (mRecommendFormationFilterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MRecommendFormationFilterDTO result = mRecommendFormationFilterService.save(mRecommendFormationFilterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mRecommendFormationFilterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-recommend-formation-filters} : get all the mRecommendFormationFilters.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mRecommendFormationFilters in body.
     */
    @GetMapping("/m-recommend-formation-filters")
    public ResponseEntity<List<MRecommendFormationFilterDTO>> getAllMRecommendFormationFilters(MRecommendFormationFilterCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MRecommendFormationFilters by criteria: {}", criteria);
        Page<MRecommendFormationFilterDTO> page = mRecommendFormationFilterQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-recommend-formation-filters/count} : count all the mRecommendFormationFilters.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-recommend-formation-filters/count")
    public ResponseEntity<Long> countMRecommendFormationFilters(MRecommendFormationFilterCriteria criteria) {
        log.debug("REST request to count MRecommendFormationFilters by criteria: {}", criteria);
        return ResponseEntity.ok().body(mRecommendFormationFilterQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-recommend-formation-filters/:id} : get the "id" mRecommendFormationFilter.
     *
     * @param id the id of the mRecommendFormationFilterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mRecommendFormationFilterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-recommend-formation-filters/{id}")
    public ResponseEntity<MRecommendFormationFilterDTO> getMRecommendFormationFilter(@PathVariable Long id) {
        log.debug("REST request to get MRecommendFormationFilter : {}", id);
        Optional<MRecommendFormationFilterDTO> mRecommendFormationFilterDTO = mRecommendFormationFilterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mRecommendFormationFilterDTO);
    }

    /**
     * {@code DELETE  /m-recommend-formation-filters/:id} : delete the "id" mRecommendFormationFilter.
     *
     * @param id the id of the mRecommendFormationFilterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-recommend-formation-filters/{id}")
    public ResponseEntity<Void> deleteMRecommendFormationFilter(@PathVariable Long id) {
        log.debug("REST request to delete MRecommendFormationFilter : {}", id);
        mRecommendFormationFilterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
