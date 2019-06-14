package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPassiveEffectRangeService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPassiveEffectRangeDTO;
import io.shm.tsubasa.service.dto.MPassiveEffectRangeCriteria;
import io.shm.tsubasa.service.MPassiveEffectRangeQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPassiveEffectRange}.
 */
@RestController
@RequestMapping("/api")
public class MPassiveEffectRangeResource {

    private final Logger log = LoggerFactory.getLogger(MPassiveEffectRangeResource.class);

    private static final String ENTITY_NAME = "mPassiveEffectRange";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPassiveEffectRangeService mPassiveEffectRangeService;

    private final MPassiveEffectRangeQueryService mPassiveEffectRangeQueryService;

    public MPassiveEffectRangeResource(MPassiveEffectRangeService mPassiveEffectRangeService, MPassiveEffectRangeQueryService mPassiveEffectRangeQueryService) {
        this.mPassiveEffectRangeService = mPassiveEffectRangeService;
        this.mPassiveEffectRangeQueryService = mPassiveEffectRangeQueryService;
    }

    /**
     * {@code POST  /m-passive-effect-ranges} : Create a new mPassiveEffectRange.
     *
     * @param mPassiveEffectRangeDTO the mPassiveEffectRangeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPassiveEffectRangeDTO, or with status {@code 400 (Bad Request)} if the mPassiveEffectRange has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-passive-effect-ranges")
    public ResponseEntity<MPassiveEffectRangeDTO> createMPassiveEffectRange(@Valid @RequestBody MPassiveEffectRangeDTO mPassiveEffectRangeDTO) throws URISyntaxException {
        log.debug("REST request to save MPassiveEffectRange : {}", mPassiveEffectRangeDTO);
        if (mPassiveEffectRangeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPassiveEffectRange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPassiveEffectRangeDTO result = mPassiveEffectRangeService.save(mPassiveEffectRangeDTO);
        return ResponseEntity.created(new URI("/api/m-passive-effect-ranges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-passive-effect-ranges} : Updates an existing mPassiveEffectRange.
     *
     * @param mPassiveEffectRangeDTO the mPassiveEffectRangeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPassiveEffectRangeDTO,
     * or with status {@code 400 (Bad Request)} if the mPassiveEffectRangeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPassiveEffectRangeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-passive-effect-ranges")
    public ResponseEntity<MPassiveEffectRangeDTO> updateMPassiveEffectRange(@Valid @RequestBody MPassiveEffectRangeDTO mPassiveEffectRangeDTO) throws URISyntaxException {
        log.debug("REST request to update MPassiveEffectRange : {}", mPassiveEffectRangeDTO);
        if (mPassiveEffectRangeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPassiveEffectRangeDTO result = mPassiveEffectRangeService.save(mPassiveEffectRangeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPassiveEffectRangeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-passive-effect-ranges} : get all the mPassiveEffectRanges.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPassiveEffectRanges in body.
     */
    @GetMapping("/m-passive-effect-ranges")
    public ResponseEntity<List<MPassiveEffectRangeDTO>> getAllMPassiveEffectRanges(MPassiveEffectRangeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPassiveEffectRanges by criteria: {}", criteria);
        Page<MPassiveEffectRangeDTO> page = mPassiveEffectRangeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-passive-effect-ranges/count} : count all the mPassiveEffectRanges.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-passive-effect-ranges/count")
    public ResponseEntity<Long> countMPassiveEffectRanges(MPassiveEffectRangeCriteria criteria) {
        log.debug("REST request to count MPassiveEffectRanges by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPassiveEffectRangeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-passive-effect-ranges/:id} : get the "id" mPassiveEffectRange.
     *
     * @param id the id of the mPassiveEffectRangeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPassiveEffectRangeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-passive-effect-ranges/{id}")
    public ResponseEntity<MPassiveEffectRangeDTO> getMPassiveEffectRange(@PathVariable Long id) {
        log.debug("REST request to get MPassiveEffectRange : {}", id);
        Optional<MPassiveEffectRangeDTO> mPassiveEffectRangeDTO = mPassiveEffectRangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPassiveEffectRangeDTO);
    }

    /**
     * {@code DELETE  /m-passive-effect-ranges/:id} : delete the "id" mPassiveEffectRange.
     *
     * @param id the id of the mPassiveEffectRangeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-passive-effect-ranges/{id}")
    public ResponseEntity<Void> deleteMPassiveEffectRange(@PathVariable Long id) {
        log.debug("REST request to delete MPassiveEffectRange : {}", id);
        mPassiveEffectRangeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
