package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MEventTitleEffectService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MEventTitleEffectDTO;
import io.shm.tsubasa.service.dto.MEventTitleEffectCriteria;
import io.shm.tsubasa.service.MEventTitleEffectQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MEventTitleEffect}.
 */
@RestController
@RequestMapping("/api")
public class MEventTitleEffectResource {

    private final Logger log = LoggerFactory.getLogger(MEventTitleEffectResource.class);

    private static final String ENTITY_NAME = "mEventTitleEffect";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MEventTitleEffectService mEventTitleEffectService;

    private final MEventTitleEffectQueryService mEventTitleEffectQueryService;

    public MEventTitleEffectResource(MEventTitleEffectService mEventTitleEffectService, MEventTitleEffectQueryService mEventTitleEffectQueryService) {
        this.mEventTitleEffectService = mEventTitleEffectService;
        this.mEventTitleEffectQueryService = mEventTitleEffectQueryService;
    }

    /**
     * {@code POST  /m-event-title-effects} : Create a new mEventTitleEffect.
     *
     * @param mEventTitleEffectDTO the mEventTitleEffectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mEventTitleEffectDTO, or with status {@code 400 (Bad Request)} if the mEventTitleEffect has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-event-title-effects")
    public ResponseEntity<MEventTitleEffectDTO> createMEventTitleEffect(@Valid @RequestBody MEventTitleEffectDTO mEventTitleEffectDTO) throws URISyntaxException {
        log.debug("REST request to save MEventTitleEffect : {}", mEventTitleEffectDTO);
        if (mEventTitleEffectDTO.getId() != null) {
            throw new BadRequestAlertException("A new mEventTitleEffect cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MEventTitleEffectDTO result = mEventTitleEffectService.save(mEventTitleEffectDTO);
        return ResponseEntity.created(new URI("/api/m-event-title-effects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-event-title-effects} : Updates an existing mEventTitleEffect.
     *
     * @param mEventTitleEffectDTO the mEventTitleEffectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mEventTitleEffectDTO,
     * or with status {@code 400 (Bad Request)} if the mEventTitleEffectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mEventTitleEffectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-event-title-effects")
    public ResponseEntity<MEventTitleEffectDTO> updateMEventTitleEffect(@Valid @RequestBody MEventTitleEffectDTO mEventTitleEffectDTO) throws URISyntaxException {
        log.debug("REST request to update MEventTitleEffect : {}", mEventTitleEffectDTO);
        if (mEventTitleEffectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MEventTitleEffectDTO result = mEventTitleEffectService.save(mEventTitleEffectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mEventTitleEffectDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-event-title-effects} : get all the mEventTitleEffects.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mEventTitleEffects in body.
     */
    @GetMapping("/m-event-title-effects")
    public ResponseEntity<List<MEventTitleEffectDTO>> getAllMEventTitleEffects(MEventTitleEffectCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MEventTitleEffects by criteria: {}", criteria);
        Page<MEventTitleEffectDTO> page = mEventTitleEffectQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-event-title-effects/count} : count all the mEventTitleEffects.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-event-title-effects/count")
    public ResponseEntity<Long> countMEventTitleEffects(MEventTitleEffectCriteria criteria) {
        log.debug("REST request to count MEventTitleEffects by criteria: {}", criteria);
        return ResponseEntity.ok().body(mEventTitleEffectQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-event-title-effects/:id} : get the "id" mEventTitleEffect.
     *
     * @param id the id of the mEventTitleEffectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mEventTitleEffectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-event-title-effects/{id}")
    public ResponseEntity<MEventTitleEffectDTO> getMEventTitleEffect(@PathVariable Long id) {
        log.debug("REST request to get MEventTitleEffect : {}", id);
        Optional<MEventTitleEffectDTO> mEventTitleEffectDTO = mEventTitleEffectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mEventTitleEffectDTO);
    }

    /**
     * {@code DELETE  /m-event-title-effects/:id} : delete the "id" mEventTitleEffect.
     *
     * @param id the id of the mEventTitleEffectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-event-title-effects/{id}")
    public ResponseEntity<Void> deleteMEventTitleEffect(@PathVariable Long id) {
        log.debug("REST request to delete MEventTitleEffect : {}", id);
        mEventTitleEffectService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
