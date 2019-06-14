package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGoalEffectiveCardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGoalEffectiveCardDTO;
import io.shm.tsubasa.service.dto.MGoalEffectiveCardCriteria;
import io.shm.tsubasa.service.MGoalEffectiveCardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGoalEffectiveCard}.
 */
@RestController
@RequestMapping("/api")
public class MGoalEffectiveCardResource {

    private final Logger log = LoggerFactory.getLogger(MGoalEffectiveCardResource.class);

    private static final String ENTITY_NAME = "mGoalEffectiveCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGoalEffectiveCardService mGoalEffectiveCardService;

    private final MGoalEffectiveCardQueryService mGoalEffectiveCardQueryService;

    public MGoalEffectiveCardResource(MGoalEffectiveCardService mGoalEffectiveCardService, MGoalEffectiveCardQueryService mGoalEffectiveCardQueryService) {
        this.mGoalEffectiveCardService = mGoalEffectiveCardService;
        this.mGoalEffectiveCardQueryService = mGoalEffectiveCardQueryService;
    }

    /**
     * {@code POST  /m-goal-effective-cards} : Create a new mGoalEffectiveCard.
     *
     * @param mGoalEffectiveCardDTO the mGoalEffectiveCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGoalEffectiveCardDTO, or with status {@code 400 (Bad Request)} if the mGoalEffectiveCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-goal-effective-cards")
    public ResponseEntity<MGoalEffectiveCardDTO> createMGoalEffectiveCard(@Valid @RequestBody MGoalEffectiveCardDTO mGoalEffectiveCardDTO) throws URISyntaxException {
        log.debug("REST request to save MGoalEffectiveCard : {}", mGoalEffectiveCardDTO);
        if (mGoalEffectiveCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGoalEffectiveCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGoalEffectiveCardDTO result = mGoalEffectiveCardService.save(mGoalEffectiveCardDTO);
        return ResponseEntity.created(new URI("/api/m-goal-effective-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-goal-effective-cards} : Updates an existing mGoalEffectiveCard.
     *
     * @param mGoalEffectiveCardDTO the mGoalEffectiveCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGoalEffectiveCardDTO,
     * or with status {@code 400 (Bad Request)} if the mGoalEffectiveCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGoalEffectiveCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-goal-effective-cards")
    public ResponseEntity<MGoalEffectiveCardDTO> updateMGoalEffectiveCard(@Valid @RequestBody MGoalEffectiveCardDTO mGoalEffectiveCardDTO) throws URISyntaxException {
        log.debug("REST request to update MGoalEffectiveCard : {}", mGoalEffectiveCardDTO);
        if (mGoalEffectiveCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGoalEffectiveCardDTO result = mGoalEffectiveCardService.save(mGoalEffectiveCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGoalEffectiveCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-goal-effective-cards} : get all the mGoalEffectiveCards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGoalEffectiveCards in body.
     */
    @GetMapping("/m-goal-effective-cards")
    public ResponseEntity<List<MGoalEffectiveCardDTO>> getAllMGoalEffectiveCards(MGoalEffectiveCardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGoalEffectiveCards by criteria: {}", criteria);
        Page<MGoalEffectiveCardDTO> page = mGoalEffectiveCardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-goal-effective-cards/count} : count all the mGoalEffectiveCards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-goal-effective-cards/count")
    public ResponseEntity<Long> countMGoalEffectiveCards(MGoalEffectiveCardCriteria criteria) {
        log.debug("REST request to count MGoalEffectiveCards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGoalEffectiveCardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-goal-effective-cards/:id} : get the "id" mGoalEffectiveCard.
     *
     * @param id the id of the mGoalEffectiveCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGoalEffectiveCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-goal-effective-cards/{id}")
    public ResponseEntity<MGoalEffectiveCardDTO> getMGoalEffectiveCard(@PathVariable Long id) {
        log.debug("REST request to get MGoalEffectiveCard : {}", id);
        Optional<MGoalEffectiveCardDTO> mGoalEffectiveCardDTO = mGoalEffectiveCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGoalEffectiveCardDTO);
    }

    /**
     * {@code DELETE  /m-goal-effective-cards/:id} : delete the "id" mGoalEffectiveCard.
     *
     * @param id the id of the mGoalEffectiveCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-goal-effective-cards/{id}")
    public ResponseEntity<Void> deleteMGoalEffectiveCard(@PathVariable Long id) {
        log.debug("REST request to delete MGoalEffectiveCard : {}", id);
        mGoalEffectiveCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
