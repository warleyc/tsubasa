package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MSoundBankEventService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MSoundBankEventDTO;
import io.shm.tsubasa.service.dto.MSoundBankEventCriteria;
import io.shm.tsubasa.service.MSoundBankEventQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MSoundBankEvent}.
 */
@RestController
@RequestMapping("/api")
public class MSoundBankEventResource {

    private final Logger log = LoggerFactory.getLogger(MSoundBankEventResource.class);

    private static final String ENTITY_NAME = "mSoundBankEvent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MSoundBankEventService mSoundBankEventService;

    private final MSoundBankEventQueryService mSoundBankEventQueryService;

    public MSoundBankEventResource(MSoundBankEventService mSoundBankEventService, MSoundBankEventQueryService mSoundBankEventQueryService) {
        this.mSoundBankEventService = mSoundBankEventService;
        this.mSoundBankEventQueryService = mSoundBankEventQueryService;
    }

    /**
     * {@code POST  /m-sound-bank-events} : Create a new mSoundBankEvent.
     *
     * @param mSoundBankEventDTO the mSoundBankEventDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mSoundBankEventDTO, or with status {@code 400 (Bad Request)} if the mSoundBankEvent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-sound-bank-events")
    public ResponseEntity<MSoundBankEventDTO> createMSoundBankEvent(@Valid @RequestBody MSoundBankEventDTO mSoundBankEventDTO) throws URISyntaxException {
        log.debug("REST request to save MSoundBankEvent : {}", mSoundBankEventDTO);
        if (mSoundBankEventDTO.getId() != null) {
            throw new BadRequestAlertException("A new mSoundBankEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MSoundBankEventDTO result = mSoundBankEventService.save(mSoundBankEventDTO);
        return ResponseEntity.created(new URI("/api/m-sound-bank-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-sound-bank-events} : Updates an existing mSoundBankEvent.
     *
     * @param mSoundBankEventDTO the mSoundBankEventDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mSoundBankEventDTO,
     * or with status {@code 400 (Bad Request)} if the mSoundBankEventDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mSoundBankEventDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-sound-bank-events")
    public ResponseEntity<MSoundBankEventDTO> updateMSoundBankEvent(@Valid @RequestBody MSoundBankEventDTO mSoundBankEventDTO) throws URISyntaxException {
        log.debug("REST request to update MSoundBankEvent : {}", mSoundBankEventDTO);
        if (mSoundBankEventDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MSoundBankEventDTO result = mSoundBankEventService.save(mSoundBankEventDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mSoundBankEventDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-sound-bank-events} : get all the mSoundBankEvents.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mSoundBankEvents in body.
     */
    @GetMapping("/m-sound-bank-events")
    public ResponseEntity<List<MSoundBankEventDTO>> getAllMSoundBankEvents(MSoundBankEventCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MSoundBankEvents by criteria: {}", criteria);
        Page<MSoundBankEventDTO> page = mSoundBankEventQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-sound-bank-events/count} : count all the mSoundBankEvents.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-sound-bank-events/count")
    public ResponseEntity<Long> countMSoundBankEvents(MSoundBankEventCriteria criteria) {
        log.debug("REST request to count MSoundBankEvents by criteria: {}", criteria);
        return ResponseEntity.ok().body(mSoundBankEventQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-sound-bank-events/:id} : get the "id" mSoundBankEvent.
     *
     * @param id the id of the mSoundBankEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mSoundBankEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-sound-bank-events/{id}")
    public ResponseEntity<MSoundBankEventDTO> getMSoundBankEvent(@PathVariable Long id) {
        log.debug("REST request to get MSoundBankEvent : {}", id);
        Optional<MSoundBankEventDTO> mSoundBankEventDTO = mSoundBankEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mSoundBankEventDTO);
    }

    /**
     * {@code DELETE  /m-sound-bank-events/:id} : delete the "id" mSoundBankEvent.
     *
     * @param id the id of the mSoundBankEventDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-sound-bank-events/{id}")
    public ResponseEntity<Void> deleteMSoundBankEvent(@PathVariable Long id) {
        log.debug("REST request to delete MSoundBankEvent : {}", id);
        mSoundBankEventService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
