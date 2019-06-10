package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCharacterBookService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCharacterBookDTO;
import io.shm.tsubasa.service.dto.MCharacterBookCriteria;
import io.shm.tsubasa.service.MCharacterBookQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCharacterBook}.
 */
@RestController
@RequestMapping("/api")
public class MCharacterBookResource {

    private final Logger log = LoggerFactory.getLogger(MCharacterBookResource.class);

    private static final String ENTITY_NAME = "mCharacterBook";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCharacterBookService mCharacterBookService;

    private final MCharacterBookQueryService mCharacterBookQueryService;

    public MCharacterBookResource(MCharacterBookService mCharacterBookService, MCharacterBookQueryService mCharacterBookQueryService) {
        this.mCharacterBookService = mCharacterBookService;
        this.mCharacterBookQueryService = mCharacterBookQueryService;
    }

    /**
     * {@code POST  /m-character-books} : Create a new mCharacterBook.
     *
     * @param mCharacterBookDTO the mCharacterBookDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCharacterBookDTO, or with status {@code 400 (Bad Request)} if the mCharacterBook has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-character-books")
    public ResponseEntity<MCharacterBookDTO> createMCharacterBook(@Valid @RequestBody MCharacterBookDTO mCharacterBookDTO) throws URISyntaxException {
        log.debug("REST request to save MCharacterBook : {}", mCharacterBookDTO);
        if (mCharacterBookDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCharacterBook cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCharacterBookDTO result = mCharacterBookService.save(mCharacterBookDTO);
        return ResponseEntity.created(new URI("/api/m-character-books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-character-books} : Updates an existing mCharacterBook.
     *
     * @param mCharacterBookDTO the mCharacterBookDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCharacterBookDTO,
     * or with status {@code 400 (Bad Request)} if the mCharacterBookDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCharacterBookDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-character-books")
    public ResponseEntity<MCharacterBookDTO> updateMCharacterBook(@Valid @RequestBody MCharacterBookDTO mCharacterBookDTO) throws URISyntaxException {
        log.debug("REST request to update MCharacterBook : {}", mCharacterBookDTO);
        if (mCharacterBookDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCharacterBookDTO result = mCharacterBookService.save(mCharacterBookDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCharacterBookDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-character-books} : get all the mCharacterBooks.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCharacterBooks in body.
     */
    @GetMapping("/m-character-books")
    public ResponseEntity<List<MCharacterBookDTO>> getAllMCharacterBooks(MCharacterBookCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCharacterBooks by criteria: {}", criteria);
        Page<MCharacterBookDTO> page = mCharacterBookQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-character-books/count} : count all the mCharacterBooks.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-character-books/count")
    public ResponseEntity<Long> countMCharacterBooks(MCharacterBookCriteria criteria) {
        log.debug("REST request to count MCharacterBooks by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCharacterBookQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-character-books/:id} : get the "id" mCharacterBook.
     *
     * @param id the id of the mCharacterBookDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCharacterBookDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-character-books/{id}")
    public ResponseEntity<MCharacterBookDTO> getMCharacterBook(@PathVariable Long id) {
        log.debug("REST request to get MCharacterBook : {}", id);
        Optional<MCharacterBookDTO> mCharacterBookDTO = mCharacterBookService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCharacterBookDTO);
    }

    /**
     * {@code DELETE  /m-character-books/:id} : delete the "id" mCharacterBook.
     *
     * @param id the id of the mCharacterBookDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-character-books/{id}")
    public ResponseEntity<Void> deleteMCharacterBook(@PathVariable Long id) {
        log.debug("REST request to delete MCharacterBook : {}", id);
        mCharacterBookService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
