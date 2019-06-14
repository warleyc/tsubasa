package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MNpcDeckService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MNpcDeckDTO;
import io.shm.tsubasa.service.dto.MNpcDeckCriteria;
import io.shm.tsubasa.service.MNpcDeckQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MNpcDeck}.
 */
@RestController
@RequestMapping("/api")
public class MNpcDeckResource {

    private final Logger log = LoggerFactory.getLogger(MNpcDeckResource.class);

    private static final String ENTITY_NAME = "mNpcDeck";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MNpcDeckService mNpcDeckService;

    private final MNpcDeckQueryService mNpcDeckQueryService;

    public MNpcDeckResource(MNpcDeckService mNpcDeckService, MNpcDeckQueryService mNpcDeckQueryService) {
        this.mNpcDeckService = mNpcDeckService;
        this.mNpcDeckQueryService = mNpcDeckQueryService;
    }

    /**
     * {@code POST  /m-npc-decks} : Create a new mNpcDeck.
     *
     * @param mNpcDeckDTO the mNpcDeckDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mNpcDeckDTO, or with status {@code 400 (Bad Request)} if the mNpcDeck has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-npc-decks")
    public ResponseEntity<MNpcDeckDTO> createMNpcDeck(@Valid @RequestBody MNpcDeckDTO mNpcDeckDTO) throws URISyntaxException {
        log.debug("REST request to save MNpcDeck : {}", mNpcDeckDTO);
        if (mNpcDeckDTO.getId() != null) {
            throw new BadRequestAlertException("A new mNpcDeck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MNpcDeckDTO result = mNpcDeckService.save(mNpcDeckDTO);
        return ResponseEntity.created(new URI("/api/m-npc-decks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-npc-decks} : Updates an existing mNpcDeck.
     *
     * @param mNpcDeckDTO the mNpcDeckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mNpcDeckDTO,
     * or with status {@code 400 (Bad Request)} if the mNpcDeckDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mNpcDeckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-npc-decks")
    public ResponseEntity<MNpcDeckDTO> updateMNpcDeck(@Valid @RequestBody MNpcDeckDTO mNpcDeckDTO) throws URISyntaxException {
        log.debug("REST request to update MNpcDeck : {}", mNpcDeckDTO);
        if (mNpcDeckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MNpcDeckDTO result = mNpcDeckService.save(mNpcDeckDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mNpcDeckDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-npc-decks} : get all the mNpcDecks.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mNpcDecks in body.
     */
    @GetMapping("/m-npc-decks")
    public ResponseEntity<List<MNpcDeckDTO>> getAllMNpcDecks(MNpcDeckCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MNpcDecks by criteria: {}", criteria);
        Page<MNpcDeckDTO> page = mNpcDeckQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-npc-decks/count} : count all the mNpcDecks.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-npc-decks/count")
    public ResponseEntity<Long> countMNpcDecks(MNpcDeckCriteria criteria) {
        log.debug("REST request to count MNpcDecks by criteria: {}", criteria);
        return ResponseEntity.ok().body(mNpcDeckQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-npc-decks/:id} : get the "id" mNpcDeck.
     *
     * @param id the id of the mNpcDeckDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mNpcDeckDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-npc-decks/{id}")
    public ResponseEntity<MNpcDeckDTO> getMNpcDeck(@PathVariable Long id) {
        log.debug("REST request to get MNpcDeck : {}", id);
        Optional<MNpcDeckDTO> mNpcDeckDTO = mNpcDeckService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mNpcDeckDTO);
    }

    /**
     * {@code DELETE  /m-npc-decks/:id} : delete the "id" mNpcDeck.
     *
     * @param id the id of the mNpcDeckDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-npc-decks/{id}")
    public ResponseEntity<Void> deleteMNpcDeck(@PathVariable Long id) {
        log.debug("REST request to delete MNpcDeck : {}", id);
        mNpcDeckService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
