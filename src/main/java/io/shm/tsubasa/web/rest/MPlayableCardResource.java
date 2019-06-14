package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPlayableCardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPlayableCardDTO;
import io.shm.tsubasa.service.dto.MPlayableCardCriteria;
import io.shm.tsubasa.service.MPlayableCardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPlayableCard}.
 */
@RestController
@RequestMapping("/api")
public class MPlayableCardResource {

    private final Logger log = LoggerFactory.getLogger(MPlayableCardResource.class);

    private static final String ENTITY_NAME = "mPlayableCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPlayableCardService mPlayableCardService;

    private final MPlayableCardQueryService mPlayableCardQueryService;

    public MPlayableCardResource(MPlayableCardService mPlayableCardService, MPlayableCardQueryService mPlayableCardQueryService) {
        this.mPlayableCardService = mPlayableCardService;
        this.mPlayableCardQueryService = mPlayableCardQueryService;
    }

    /**
     * {@code POST  /m-playable-cards} : Create a new mPlayableCard.
     *
     * @param mPlayableCardDTO the mPlayableCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPlayableCardDTO, or with status {@code 400 (Bad Request)} if the mPlayableCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-playable-cards")
    public ResponseEntity<MPlayableCardDTO> createMPlayableCard(@Valid @RequestBody MPlayableCardDTO mPlayableCardDTO) throws URISyntaxException {
        log.debug("REST request to save MPlayableCard : {}", mPlayableCardDTO);
        if (mPlayableCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPlayableCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPlayableCardDTO result = mPlayableCardService.save(mPlayableCardDTO);
        return ResponseEntity.created(new URI("/api/m-playable-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-playable-cards} : Updates an existing mPlayableCard.
     *
     * @param mPlayableCardDTO the mPlayableCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPlayableCardDTO,
     * or with status {@code 400 (Bad Request)} if the mPlayableCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPlayableCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-playable-cards")
    public ResponseEntity<MPlayableCardDTO> updateMPlayableCard(@Valid @RequestBody MPlayableCardDTO mPlayableCardDTO) throws URISyntaxException {
        log.debug("REST request to update MPlayableCard : {}", mPlayableCardDTO);
        if (mPlayableCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPlayableCardDTO result = mPlayableCardService.save(mPlayableCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPlayableCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-playable-cards} : get all the mPlayableCards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPlayableCards in body.
     */
    @GetMapping("/m-playable-cards")
    public ResponseEntity<List<MPlayableCardDTO>> getAllMPlayableCards(MPlayableCardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPlayableCards by criteria: {}", criteria);
        Page<MPlayableCardDTO> page = mPlayableCardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-playable-cards/count} : count all the mPlayableCards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-playable-cards/count")
    public ResponseEntity<Long> countMPlayableCards(MPlayableCardCriteria criteria) {
        log.debug("REST request to count MPlayableCards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPlayableCardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-playable-cards/:id} : get the "id" mPlayableCard.
     *
     * @param id the id of the mPlayableCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPlayableCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-playable-cards/{id}")
    public ResponseEntity<MPlayableCardDTO> getMPlayableCard(@PathVariable Long id) {
        log.debug("REST request to get MPlayableCard : {}", id);
        Optional<MPlayableCardDTO> mPlayableCardDTO = mPlayableCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPlayableCardDTO);
    }

    /**
     * {@code DELETE  /m-playable-cards/:id} : delete the "id" mPlayableCard.
     *
     * @param id the id of the mPlayableCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-playable-cards/{id}")
    public ResponseEntity<Void> deleteMPlayableCard(@PathVariable Long id) {
        log.debug("REST request to delete MPlayableCard : {}", id);
        mPlayableCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
