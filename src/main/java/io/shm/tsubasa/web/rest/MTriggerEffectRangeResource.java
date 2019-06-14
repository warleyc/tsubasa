package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTriggerEffectRangeService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTriggerEffectRangeDTO;
import io.shm.tsubasa.service.dto.MTriggerEffectRangeCriteria;
import io.shm.tsubasa.service.MTriggerEffectRangeQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTriggerEffectRange}.
 */
@RestController
@RequestMapping("/api")
public class MTriggerEffectRangeResource {

    private final Logger log = LoggerFactory.getLogger(MTriggerEffectRangeResource.class);

    private static final String ENTITY_NAME = "mTriggerEffectRange";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTriggerEffectRangeService mTriggerEffectRangeService;

    private final MTriggerEffectRangeQueryService mTriggerEffectRangeQueryService;

    public MTriggerEffectRangeResource(MTriggerEffectRangeService mTriggerEffectRangeService, MTriggerEffectRangeQueryService mTriggerEffectRangeQueryService) {
        this.mTriggerEffectRangeService = mTriggerEffectRangeService;
        this.mTriggerEffectRangeQueryService = mTriggerEffectRangeQueryService;
    }

    /**
     * {@code POST  /m-trigger-effect-ranges} : Create a new mTriggerEffectRange.
     *
     * @param mTriggerEffectRangeDTO the mTriggerEffectRangeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTriggerEffectRangeDTO, or with status {@code 400 (Bad Request)} if the mTriggerEffectRange has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-trigger-effect-ranges")
    public ResponseEntity<MTriggerEffectRangeDTO> createMTriggerEffectRange(@Valid @RequestBody MTriggerEffectRangeDTO mTriggerEffectRangeDTO) throws URISyntaxException {
        log.debug("REST request to save MTriggerEffectRange : {}", mTriggerEffectRangeDTO);
        if (mTriggerEffectRangeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTriggerEffectRange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTriggerEffectRangeDTO result = mTriggerEffectRangeService.save(mTriggerEffectRangeDTO);
        return ResponseEntity.created(new URI("/api/m-trigger-effect-ranges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-trigger-effect-ranges} : Updates an existing mTriggerEffectRange.
     *
     * @param mTriggerEffectRangeDTO the mTriggerEffectRangeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTriggerEffectRangeDTO,
     * or with status {@code 400 (Bad Request)} if the mTriggerEffectRangeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTriggerEffectRangeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-trigger-effect-ranges")
    public ResponseEntity<MTriggerEffectRangeDTO> updateMTriggerEffectRange(@Valid @RequestBody MTriggerEffectRangeDTO mTriggerEffectRangeDTO) throws URISyntaxException {
        log.debug("REST request to update MTriggerEffectRange : {}", mTriggerEffectRangeDTO);
        if (mTriggerEffectRangeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTriggerEffectRangeDTO result = mTriggerEffectRangeService.save(mTriggerEffectRangeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTriggerEffectRangeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-trigger-effect-ranges} : get all the mTriggerEffectRanges.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTriggerEffectRanges in body.
     */
    @GetMapping("/m-trigger-effect-ranges")
    public ResponseEntity<List<MTriggerEffectRangeDTO>> getAllMTriggerEffectRanges(MTriggerEffectRangeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTriggerEffectRanges by criteria: {}", criteria);
        Page<MTriggerEffectRangeDTO> page = mTriggerEffectRangeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-trigger-effect-ranges/count} : count all the mTriggerEffectRanges.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-trigger-effect-ranges/count")
    public ResponseEntity<Long> countMTriggerEffectRanges(MTriggerEffectRangeCriteria criteria) {
        log.debug("REST request to count MTriggerEffectRanges by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTriggerEffectRangeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-trigger-effect-ranges/:id} : get the "id" mTriggerEffectRange.
     *
     * @param id the id of the mTriggerEffectRangeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTriggerEffectRangeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-trigger-effect-ranges/{id}")
    public ResponseEntity<MTriggerEffectRangeDTO> getMTriggerEffectRange(@PathVariable Long id) {
        log.debug("REST request to get MTriggerEffectRange : {}", id);
        Optional<MTriggerEffectRangeDTO> mTriggerEffectRangeDTO = mTriggerEffectRangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTriggerEffectRangeDTO);
    }

    /**
     * {@code DELETE  /m-trigger-effect-ranges/:id} : delete the "id" mTriggerEffectRange.
     *
     * @param id the id of the mTriggerEffectRangeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-trigger-effect-ranges/{id}")
    public ResponseEntity<Void> deleteMTriggerEffectRange(@PathVariable Long id) {
        log.debug("REST request to delete MTriggerEffectRange : {}", id);
        mTriggerEffectRangeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
