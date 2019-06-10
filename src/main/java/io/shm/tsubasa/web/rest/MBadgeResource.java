package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MBadgeService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MBadgeDTO;
import io.shm.tsubasa.service.dto.MBadgeCriteria;
import io.shm.tsubasa.service.MBadgeQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MBadge}.
 */
@RestController
@RequestMapping("/api")
public class MBadgeResource {

    private final Logger log = LoggerFactory.getLogger(MBadgeResource.class);

    private static final String ENTITY_NAME = "mBadge";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MBadgeService mBadgeService;

    private final MBadgeQueryService mBadgeQueryService;

    public MBadgeResource(MBadgeService mBadgeService, MBadgeQueryService mBadgeQueryService) {
        this.mBadgeService = mBadgeService;
        this.mBadgeQueryService = mBadgeQueryService;
    }

    /**
     * {@code POST  /m-badges} : Create a new mBadge.
     *
     * @param mBadgeDTO the mBadgeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mBadgeDTO, or with status {@code 400 (Bad Request)} if the mBadge has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-badges")
    public ResponseEntity<MBadgeDTO> createMBadge(@Valid @RequestBody MBadgeDTO mBadgeDTO) throws URISyntaxException {
        log.debug("REST request to save MBadge : {}", mBadgeDTO);
        if (mBadgeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mBadge cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MBadgeDTO result = mBadgeService.save(mBadgeDTO);
        return ResponseEntity.created(new URI("/api/m-badges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-badges} : Updates an existing mBadge.
     *
     * @param mBadgeDTO the mBadgeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mBadgeDTO,
     * or with status {@code 400 (Bad Request)} if the mBadgeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mBadgeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-badges")
    public ResponseEntity<MBadgeDTO> updateMBadge(@Valid @RequestBody MBadgeDTO mBadgeDTO) throws URISyntaxException {
        log.debug("REST request to update MBadge : {}", mBadgeDTO);
        if (mBadgeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MBadgeDTO result = mBadgeService.save(mBadgeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mBadgeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-badges} : get all the mBadges.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mBadges in body.
     */
    @GetMapping("/m-badges")
    public ResponseEntity<List<MBadgeDTO>> getAllMBadges(MBadgeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MBadges by criteria: {}", criteria);
        Page<MBadgeDTO> page = mBadgeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-badges/count} : count all the mBadges.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-badges/count")
    public ResponseEntity<Long> countMBadges(MBadgeCriteria criteria) {
        log.debug("REST request to count MBadges by criteria: {}", criteria);
        return ResponseEntity.ok().body(mBadgeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-badges/:id} : get the "id" mBadge.
     *
     * @param id the id of the mBadgeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mBadgeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-badges/{id}")
    public ResponseEntity<MBadgeDTO> getMBadge(@PathVariable Long id) {
        log.debug("REST request to get MBadge : {}", id);
        Optional<MBadgeDTO> mBadgeDTO = mBadgeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mBadgeDTO);
    }

    /**
     * {@code DELETE  /m-badges/:id} : delete the "id" mBadge.
     *
     * @param id the id of the mBadgeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-badges/{id}")
    public ResponseEntity<Void> deleteMBadge(@PathVariable Long id) {
        log.debug("REST request to delete MBadge : {}", id);
        mBadgeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
