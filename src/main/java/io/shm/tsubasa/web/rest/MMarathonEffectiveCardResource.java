package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonEffectiveCardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonEffectiveCardDTO;
import io.shm.tsubasa.service.dto.MMarathonEffectiveCardCriteria;
import io.shm.tsubasa.service.MMarathonEffectiveCardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonEffectiveCard}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonEffectiveCardResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonEffectiveCardResource.class);

    private static final String ENTITY_NAME = "mMarathonEffectiveCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonEffectiveCardService mMarathonEffectiveCardService;

    private final MMarathonEffectiveCardQueryService mMarathonEffectiveCardQueryService;

    public MMarathonEffectiveCardResource(MMarathonEffectiveCardService mMarathonEffectiveCardService, MMarathonEffectiveCardQueryService mMarathonEffectiveCardQueryService) {
        this.mMarathonEffectiveCardService = mMarathonEffectiveCardService;
        this.mMarathonEffectiveCardQueryService = mMarathonEffectiveCardQueryService;
    }

    /**
     * {@code POST  /m-marathon-effective-cards} : Create a new mMarathonEffectiveCard.
     *
     * @param mMarathonEffectiveCardDTO the mMarathonEffectiveCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonEffectiveCardDTO, or with status {@code 400 (Bad Request)} if the mMarathonEffectiveCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-effective-cards")
    public ResponseEntity<MMarathonEffectiveCardDTO> createMMarathonEffectiveCard(@Valid @RequestBody MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonEffectiveCard : {}", mMarathonEffectiveCardDTO);
        if (mMarathonEffectiveCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonEffectiveCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonEffectiveCardDTO result = mMarathonEffectiveCardService.save(mMarathonEffectiveCardDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-effective-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-effective-cards} : Updates an existing mMarathonEffectiveCard.
     *
     * @param mMarathonEffectiveCardDTO the mMarathonEffectiveCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonEffectiveCardDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonEffectiveCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonEffectiveCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-effective-cards")
    public ResponseEntity<MMarathonEffectiveCardDTO> updateMMarathonEffectiveCard(@Valid @RequestBody MMarathonEffectiveCardDTO mMarathonEffectiveCardDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonEffectiveCard : {}", mMarathonEffectiveCardDTO);
        if (mMarathonEffectiveCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonEffectiveCardDTO result = mMarathonEffectiveCardService.save(mMarathonEffectiveCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonEffectiveCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-effective-cards} : get all the mMarathonEffectiveCards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonEffectiveCards in body.
     */
    @GetMapping("/m-marathon-effective-cards")
    public ResponseEntity<List<MMarathonEffectiveCardDTO>> getAllMMarathonEffectiveCards(MMarathonEffectiveCardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonEffectiveCards by criteria: {}", criteria);
        Page<MMarathonEffectiveCardDTO> page = mMarathonEffectiveCardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-effective-cards/count} : count all the mMarathonEffectiveCards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-effective-cards/count")
    public ResponseEntity<Long> countMMarathonEffectiveCards(MMarathonEffectiveCardCriteria criteria) {
        log.debug("REST request to count MMarathonEffectiveCards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonEffectiveCardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-effective-cards/:id} : get the "id" mMarathonEffectiveCard.
     *
     * @param id the id of the mMarathonEffectiveCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonEffectiveCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-effective-cards/{id}")
    public ResponseEntity<MMarathonEffectiveCardDTO> getMMarathonEffectiveCard(@PathVariable Long id) {
        log.debug("REST request to get MMarathonEffectiveCard : {}", id);
        Optional<MMarathonEffectiveCardDTO> mMarathonEffectiveCardDTO = mMarathonEffectiveCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonEffectiveCardDTO);
    }

    /**
     * {@code DELETE  /m-marathon-effective-cards/:id} : delete the "id" mMarathonEffectiveCard.
     *
     * @param id the id of the mMarathonEffectiveCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-effective-cards/{id}")
    public ResponseEntity<Void> deleteMMarathonEffectiveCard(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonEffectiveCard : {}", id);
        mMarathonEffectiveCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
