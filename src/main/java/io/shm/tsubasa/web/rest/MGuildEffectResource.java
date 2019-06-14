package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuildEffectService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuildEffectDTO;
import io.shm.tsubasa.service.dto.MGuildEffectCriteria;
import io.shm.tsubasa.service.MGuildEffectQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuildEffect}.
 */
@RestController
@RequestMapping("/api")
public class MGuildEffectResource {

    private final Logger log = LoggerFactory.getLogger(MGuildEffectResource.class);

    private static final String ENTITY_NAME = "mGuildEffect";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuildEffectService mGuildEffectService;

    private final MGuildEffectQueryService mGuildEffectQueryService;

    public MGuildEffectResource(MGuildEffectService mGuildEffectService, MGuildEffectQueryService mGuildEffectQueryService) {
        this.mGuildEffectService = mGuildEffectService;
        this.mGuildEffectQueryService = mGuildEffectQueryService;
    }

    /**
     * {@code POST  /m-guild-effects} : Create a new mGuildEffect.
     *
     * @param mGuildEffectDTO the mGuildEffectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuildEffectDTO, or with status {@code 400 (Bad Request)} if the mGuildEffect has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guild-effects")
    public ResponseEntity<MGuildEffectDTO> createMGuildEffect(@Valid @RequestBody MGuildEffectDTO mGuildEffectDTO) throws URISyntaxException {
        log.debug("REST request to save MGuildEffect : {}", mGuildEffectDTO);
        if (mGuildEffectDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuildEffect cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuildEffectDTO result = mGuildEffectService.save(mGuildEffectDTO);
        return ResponseEntity.created(new URI("/api/m-guild-effects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guild-effects} : Updates an existing mGuildEffect.
     *
     * @param mGuildEffectDTO the mGuildEffectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuildEffectDTO,
     * or with status {@code 400 (Bad Request)} if the mGuildEffectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuildEffectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guild-effects")
    public ResponseEntity<MGuildEffectDTO> updateMGuildEffect(@Valid @RequestBody MGuildEffectDTO mGuildEffectDTO) throws URISyntaxException {
        log.debug("REST request to update MGuildEffect : {}", mGuildEffectDTO);
        if (mGuildEffectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuildEffectDTO result = mGuildEffectService.save(mGuildEffectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuildEffectDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guild-effects} : get all the mGuildEffects.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuildEffects in body.
     */
    @GetMapping("/m-guild-effects")
    public ResponseEntity<List<MGuildEffectDTO>> getAllMGuildEffects(MGuildEffectCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuildEffects by criteria: {}", criteria);
        Page<MGuildEffectDTO> page = mGuildEffectQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guild-effects/count} : count all the mGuildEffects.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guild-effects/count")
    public ResponseEntity<Long> countMGuildEffects(MGuildEffectCriteria criteria) {
        log.debug("REST request to count MGuildEffects by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuildEffectQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guild-effects/:id} : get the "id" mGuildEffect.
     *
     * @param id the id of the mGuildEffectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuildEffectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guild-effects/{id}")
    public ResponseEntity<MGuildEffectDTO> getMGuildEffect(@PathVariable Long id) {
        log.debug("REST request to get MGuildEffect : {}", id);
        Optional<MGuildEffectDTO> mGuildEffectDTO = mGuildEffectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuildEffectDTO);
    }

    /**
     * {@code DELETE  /m-guild-effects/:id} : delete the "id" mGuildEffect.
     *
     * @param id the id of the mGuildEffectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guild-effects/{id}")
    public ResponseEntity<Void> deleteMGuildEffect(@PathVariable Long id) {
        log.debug("REST request to delete MGuildEffect : {}", id);
        mGuildEffectService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
