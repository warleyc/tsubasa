package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuildNegativeWordService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuildNegativeWordDTO;
import io.shm.tsubasa.service.dto.MGuildNegativeWordCriteria;
import io.shm.tsubasa.service.MGuildNegativeWordQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuildNegativeWord}.
 */
@RestController
@RequestMapping("/api")
public class MGuildNegativeWordResource {

    private final Logger log = LoggerFactory.getLogger(MGuildNegativeWordResource.class);

    private static final String ENTITY_NAME = "mGuildNegativeWord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuildNegativeWordService mGuildNegativeWordService;

    private final MGuildNegativeWordQueryService mGuildNegativeWordQueryService;

    public MGuildNegativeWordResource(MGuildNegativeWordService mGuildNegativeWordService, MGuildNegativeWordQueryService mGuildNegativeWordQueryService) {
        this.mGuildNegativeWordService = mGuildNegativeWordService;
        this.mGuildNegativeWordQueryService = mGuildNegativeWordQueryService;
    }

    /**
     * {@code POST  /m-guild-negative-words} : Create a new mGuildNegativeWord.
     *
     * @param mGuildNegativeWordDTO the mGuildNegativeWordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuildNegativeWordDTO, or with status {@code 400 (Bad Request)} if the mGuildNegativeWord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guild-negative-words")
    public ResponseEntity<MGuildNegativeWordDTO> createMGuildNegativeWord(@Valid @RequestBody MGuildNegativeWordDTO mGuildNegativeWordDTO) throws URISyntaxException {
        log.debug("REST request to save MGuildNegativeWord : {}", mGuildNegativeWordDTO);
        if (mGuildNegativeWordDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuildNegativeWord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuildNegativeWordDTO result = mGuildNegativeWordService.save(mGuildNegativeWordDTO);
        return ResponseEntity.created(new URI("/api/m-guild-negative-words/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guild-negative-words} : Updates an existing mGuildNegativeWord.
     *
     * @param mGuildNegativeWordDTO the mGuildNegativeWordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuildNegativeWordDTO,
     * or with status {@code 400 (Bad Request)} if the mGuildNegativeWordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuildNegativeWordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guild-negative-words")
    public ResponseEntity<MGuildNegativeWordDTO> updateMGuildNegativeWord(@Valid @RequestBody MGuildNegativeWordDTO mGuildNegativeWordDTO) throws URISyntaxException {
        log.debug("REST request to update MGuildNegativeWord : {}", mGuildNegativeWordDTO);
        if (mGuildNegativeWordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuildNegativeWordDTO result = mGuildNegativeWordService.save(mGuildNegativeWordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuildNegativeWordDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guild-negative-words} : get all the mGuildNegativeWords.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuildNegativeWords in body.
     */
    @GetMapping("/m-guild-negative-words")
    public ResponseEntity<List<MGuildNegativeWordDTO>> getAllMGuildNegativeWords(MGuildNegativeWordCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuildNegativeWords by criteria: {}", criteria);
        Page<MGuildNegativeWordDTO> page = mGuildNegativeWordQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guild-negative-words/count} : count all the mGuildNegativeWords.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guild-negative-words/count")
    public ResponseEntity<Long> countMGuildNegativeWords(MGuildNegativeWordCriteria criteria) {
        log.debug("REST request to count MGuildNegativeWords by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuildNegativeWordQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guild-negative-words/:id} : get the "id" mGuildNegativeWord.
     *
     * @param id the id of the mGuildNegativeWordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuildNegativeWordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guild-negative-words/{id}")
    public ResponseEntity<MGuildNegativeWordDTO> getMGuildNegativeWord(@PathVariable Long id) {
        log.debug("REST request to get MGuildNegativeWord : {}", id);
        Optional<MGuildNegativeWordDTO> mGuildNegativeWordDTO = mGuildNegativeWordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuildNegativeWordDTO);
    }

    /**
     * {@code DELETE  /m-guild-negative-words/:id} : delete the "id" mGuildNegativeWord.
     *
     * @param id the id of the mGuildNegativeWordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guild-negative-words/{id}")
    public ResponseEntity<Void> deleteMGuildNegativeWord(@PathVariable Long id) {
        log.debug("REST request to delete MGuildNegativeWord : {}", id);
        mGuildNegativeWordService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
