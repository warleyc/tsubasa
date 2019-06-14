package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MNgWordService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MNgWordDTO;
import io.shm.tsubasa.service.dto.MNgWordCriteria;
import io.shm.tsubasa.service.MNgWordQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MNgWord}.
 */
@RestController
@RequestMapping("/api")
public class MNgWordResource {

    private final Logger log = LoggerFactory.getLogger(MNgWordResource.class);

    private static final String ENTITY_NAME = "mNgWord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MNgWordService mNgWordService;

    private final MNgWordQueryService mNgWordQueryService;

    public MNgWordResource(MNgWordService mNgWordService, MNgWordQueryService mNgWordQueryService) {
        this.mNgWordService = mNgWordService;
        this.mNgWordQueryService = mNgWordQueryService;
    }

    /**
     * {@code POST  /m-ng-words} : Create a new mNgWord.
     *
     * @param mNgWordDTO the mNgWordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mNgWordDTO, or with status {@code 400 (Bad Request)} if the mNgWord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-ng-words")
    public ResponseEntity<MNgWordDTO> createMNgWord(@Valid @RequestBody MNgWordDTO mNgWordDTO) throws URISyntaxException {
        log.debug("REST request to save MNgWord : {}", mNgWordDTO);
        if (mNgWordDTO.getId() != null) {
            throw new BadRequestAlertException("A new mNgWord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MNgWordDTO result = mNgWordService.save(mNgWordDTO);
        return ResponseEntity.created(new URI("/api/m-ng-words/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-ng-words} : Updates an existing mNgWord.
     *
     * @param mNgWordDTO the mNgWordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mNgWordDTO,
     * or with status {@code 400 (Bad Request)} if the mNgWordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mNgWordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-ng-words")
    public ResponseEntity<MNgWordDTO> updateMNgWord(@Valid @RequestBody MNgWordDTO mNgWordDTO) throws URISyntaxException {
        log.debug("REST request to update MNgWord : {}", mNgWordDTO);
        if (mNgWordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MNgWordDTO result = mNgWordService.save(mNgWordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mNgWordDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-ng-words} : get all the mNgWords.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mNgWords in body.
     */
    @GetMapping("/m-ng-words")
    public ResponseEntity<List<MNgWordDTO>> getAllMNgWords(MNgWordCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MNgWords by criteria: {}", criteria);
        Page<MNgWordDTO> page = mNgWordQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-ng-words/count} : count all the mNgWords.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-ng-words/count")
    public ResponseEntity<Long> countMNgWords(MNgWordCriteria criteria) {
        log.debug("REST request to count MNgWords by criteria: {}", criteria);
        return ResponseEntity.ok().body(mNgWordQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-ng-words/:id} : get the "id" mNgWord.
     *
     * @param id the id of the mNgWordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mNgWordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-ng-words/{id}")
    public ResponseEntity<MNgWordDTO> getMNgWord(@PathVariable Long id) {
        log.debug("REST request to get MNgWord : {}", id);
        Optional<MNgWordDTO> mNgWordDTO = mNgWordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mNgWordDTO);
    }

    /**
     * {@code DELETE  /m-ng-words/:id} : delete the "id" mNgWord.
     *
     * @param id the id of the mNgWordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-ng-words/{id}")
    public ResponseEntity<Void> deleteMNgWord(@PathVariable Long id) {
        log.debug("REST request to delete MNgWord : {}", id);
        mNgWordService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
