package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MRecommendFormationFilterElementService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterElementDTO;
import io.shm.tsubasa.service.dto.MRecommendFormationFilterElementCriteria;
import io.shm.tsubasa.service.MRecommendFormationFilterElementQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MRecommendFormationFilterElement}.
 */
@RestController
@RequestMapping("/api")
public class MRecommendFormationFilterElementResource {

    private final Logger log = LoggerFactory.getLogger(MRecommendFormationFilterElementResource.class);

    private static final String ENTITY_NAME = "mRecommendFormationFilterElement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MRecommendFormationFilterElementService mRecommendFormationFilterElementService;

    private final MRecommendFormationFilterElementQueryService mRecommendFormationFilterElementQueryService;

    public MRecommendFormationFilterElementResource(MRecommendFormationFilterElementService mRecommendFormationFilterElementService, MRecommendFormationFilterElementQueryService mRecommendFormationFilterElementQueryService) {
        this.mRecommendFormationFilterElementService = mRecommendFormationFilterElementService;
        this.mRecommendFormationFilterElementQueryService = mRecommendFormationFilterElementQueryService;
    }

    /**
     * {@code POST  /m-recommend-formation-filter-elements} : Create a new mRecommendFormationFilterElement.
     *
     * @param mRecommendFormationFilterElementDTO the mRecommendFormationFilterElementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mRecommendFormationFilterElementDTO, or with status {@code 400 (Bad Request)} if the mRecommendFormationFilterElement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-recommend-formation-filter-elements")
    public ResponseEntity<MRecommendFormationFilterElementDTO> createMRecommendFormationFilterElement(@Valid @RequestBody MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO) throws URISyntaxException {
        log.debug("REST request to save MRecommendFormationFilterElement : {}", mRecommendFormationFilterElementDTO);
        if (mRecommendFormationFilterElementDTO.getId() != null) {
            throw new BadRequestAlertException("A new mRecommendFormationFilterElement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MRecommendFormationFilterElementDTO result = mRecommendFormationFilterElementService.save(mRecommendFormationFilterElementDTO);
        return ResponseEntity.created(new URI("/api/m-recommend-formation-filter-elements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-recommend-formation-filter-elements} : Updates an existing mRecommendFormationFilterElement.
     *
     * @param mRecommendFormationFilterElementDTO the mRecommendFormationFilterElementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mRecommendFormationFilterElementDTO,
     * or with status {@code 400 (Bad Request)} if the mRecommendFormationFilterElementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mRecommendFormationFilterElementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-recommend-formation-filter-elements")
    public ResponseEntity<MRecommendFormationFilterElementDTO> updateMRecommendFormationFilterElement(@Valid @RequestBody MRecommendFormationFilterElementDTO mRecommendFormationFilterElementDTO) throws URISyntaxException {
        log.debug("REST request to update MRecommendFormationFilterElement : {}", mRecommendFormationFilterElementDTO);
        if (mRecommendFormationFilterElementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MRecommendFormationFilterElementDTO result = mRecommendFormationFilterElementService.save(mRecommendFormationFilterElementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mRecommendFormationFilterElementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-recommend-formation-filter-elements} : get all the mRecommendFormationFilterElements.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mRecommendFormationFilterElements in body.
     */
    @GetMapping("/m-recommend-formation-filter-elements")
    public ResponseEntity<List<MRecommendFormationFilterElementDTO>> getAllMRecommendFormationFilterElements(MRecommendFormationFilterElementCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MRecommendFormationFilterElements by criteria: {}", criteria);
        Page<MRecommendFormationFilterElementDTO> page = mRecommendFormationFilterElementQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-recommend-formation-filter-elements/count} : count all the mRecommendFormationFilterElements.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-recommend-formation-filter-elements/count")
    public ResponseEntity<Long> countMRecommendFormationFilterElements(MRecommendFormationFilterElementCriteria criteria) {
        log.debug("REST request to count MRecommendFormationFilterElements by criteria: {}", criteria);
        return ResponseEntity.ok().body(mRecommendFormationFilterElementQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-recommend-formation-filter-elements/:id} : get the "id" mRecommendFormationFilterElement.
     *
     * @param id the id of the mRecommendFormationFilterElementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mRecommendFormationFilterElementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-recommend-formation-filter-elements/{id}")
    public ResponseEntity<MRecommendFormationFilterElementDTO> getMRecommendFormationFilterElement(@PathVariable Long id) {
        log.debug("REST request to get MRecommendFormationFilterElement : {}", id);
        Optional<MRecommendFormationFilterElementDTO> mRecommendFormationFilterElementDTO = mRecommendFormationFilterElementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mRecommendFormationFilterElementDTO);
    }

    /**
     * {@code DELETE  /m-recommend-formation-filter-elements/:id} : delete the "id" mRecommendFormationFilterElement.
     *
     * @param id the id of the mRecommendFormationFilterElementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-recommend-formation-filter-elements/{id}")
    public ResponseEntity<Void> deleteMRecommendFormationFilterElement(@PathVariable Long id) {
        log.debug("REST request to delete MRecommendFormationFilterElement : {}", id);
        mRecommendFormationFilterElementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
