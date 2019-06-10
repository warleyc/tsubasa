package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MContentableCardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MContentableCardDTO;
import io.shm.tsubasa.service.dto.MContentableCardCriteria;
import io.shm.tsubasa.service.MContentableCardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MContentableCard}.
 */
@RestController
@RequestMapping("/api")
public class MContentableCardResource {

    private final Logger log = LoggerFactory.getLogger(MContentableCardResource.class);

    private static final String ENTITY_NAME = "mContentableCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MContentableCardService mContentableCardService;

    private final MContentableCardQueryService mContentableCardQueryService;

    public MContentableCardResource(MContentableCardService mContentableCardService, MContentableCardQueryService mContentableCardQueryService) {
        this.mContentableCardService = mContentableCardService;
        this.mContentableCardQueryService = mContentableCardQueryService;
    }

    /**
     * {@code POST  /m-contentable-cards} : Create a new mContentableCard.
     *
     * @param mContentableCardDTO the mContentableCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mContentableCardDTO, or with status {@code 400 (Bad Request)} if the mContentableCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-contentable-cards")
    public ResponseEntity<MContentableCardDTO> createMContentableCard(@Valid @RequestBody MContentableCardDTO mContentableCardDTO) throws URISyntaxException {
        log.debug("REST request to save MContentableCard : {}", mContentableCardDTO);
        if (mContentableCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mContentableCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MContentableCardDTO result = mContentableCardService.save(mContentableCardDTO);
        return ResponseEntity.created(new URI("/api/m-contentable-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-contentable-cards} : Updates an existing mContentableCard.
     *
     * @param mContentableCardDTO the mContentableCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mContentableCardDTO,
     * or with status {@code 400 (Bad Request)} if the mContentableCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mContentableCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-contentable-cards")
    public ResponseEntity<MContentableCardDTO> updateMContentableCard(@Valid @RequestBody MContentableCardDTO mContentableCardDTO) throws URISyntaxException {
        log.debug("REST request to update MContentableCard : {}", mContentableCardDTO);
        if (mContentableCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MContentableCardDTO result = mContentableCardService.save(mContentableCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mContentableCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-contentable-cards} : get all the mContentableCards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mContentableCards in body.
     */
    @GetMapping("/m-contentable-cards")
    public ResponseEntity<List<MContentableCardDTO>> getAllMContentableCards(MContentableCardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MContentableCards by criteria: {}", criteria);
        Page<MContentableCardDTO> page = mContentableCardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-contentable-cards/count} : count all the mContentableCards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-contentable-cards/count")
    public ResponseEntity<Long> countMContentableCards(MContentableCardCriteria criteria) {
        log.debug("REST request to count MContentableCards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mContentableCardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-contentable-cards/:id} : get the "id" mContentableCard.
     *
     * @param id the id of the mContentableCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mContentableCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-contentable-cards/{id}")
    public ResponseEntity<MContentableCardDTO> getMContentableCard(@PathVariable Long id) {
        log.debug("REST request to get MContentableCard : {}", id);
        Optional<MContentableCardDTO> mContentableCardDTO = mContentableCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mContentableCardDTO);
    }

    /**
     * {@code DELETE  /m-contentable-cards/:id} : delete the "id" mContentableCard.
     *
     * @param id the id of the mContentableCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-contentable-cards/{id}")
    public ResponseEntity<Void> deleteMContentableCard(@PathVariable Long id) {
        log.debug("REST request to delete MContentableCard : {}", id);
        mContentableCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
