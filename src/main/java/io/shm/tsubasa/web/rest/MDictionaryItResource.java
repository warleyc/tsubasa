package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDictionaryItService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDictionaryItDTO;
import io.shm.tsubasa.service.dto.MDictionaryItCriteria;
import io.shm.tsubasa.service.MDictionaryItQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDictionaryIt}.
 */
@RestController
@RequestMapping("/api")
public class MDictionaryItResource {

    private final Logger log = LoggerFactory.getLogger(MDictionaryItResource.class);

    private static final String ENTITY_NAME = "mDictionaryIt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDictionaryItService mDictionaryItService;

    private final MDictionaryItQueryService mDictionaryItQueryService;

    public MDictionaryItResource(MDictionaryItService mDictionaryItService, MDictionaryItQueryService mDictionaryItQueryService) {
        this.mDictionaryItService = mDictionaryItService;
        this.mDictionaryItQueryService = mDictionaryItQueryService;
    }

    /**
     * {@code POST  /m-dictionary-its} : Create a new mDictionaryIt.
     *
     * @param mDictionaryItDTO the mDictionaryItDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDictionaryItDTO, or with status {@code 400 (Bad Request)} if the mDictionaryIt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dictionary-its")
    public ResponseEntity<MDictionaryItDTO> createMDictionaryIt(@Valid @RequestBody MDictionaryItDTO mDictionaryItDTO) throws URISyntaxException {
        log.debug("REST request to save MDictionaryIt : {}", mDictionaryItDTO);
        if (mDictionaryItDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDictionaryIt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDictionaryItDTO result = mDictionaryItService.save(mDictionaryItDTO);
        return ResponseEntity.created(new URI("/api/m-dictionary-its/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dictionary-its} : Updates an existing mDictionaryIt.
     *
     * @param mDictionaryItDTO the mDictionaryItDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDictionaryItDTO,
     * or with status {@code 400 (Bad Request)} if the mDictionaryItDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDictionaryItDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dictionary-its")
    public ResponseEntity<MDictionaryItDTO> updateMDictionaryIt(@Valid @RequestBody MDictionaryItDTO mDictionaryItDTO) throws URISyntaxException {
        log.debug("REST request to update MDictionaryIt : {}", mDictionaryItDTO);
        if (mDictionaryItDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDictionaryItDTO result = mDictionaryItService.save(mDictionaryItDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDictionaryItDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dictionary-its} : get all the mDictionaryIts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDictionaryIts in body.
     */
    @GetMapping("/m-dictionary-its")
    public ResponseEntity<List<MDictionaryItDTO>> getAllMDictionaryIts(MDictionaryItCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDictionaryIts by criteria: {}", criteria);
        Page<MDictionaryItDTO> page = mDictionaryItQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dictionary-its/count} : count all the mDictionaryIts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dictionary-its/count")
    public ResponseEntity<Long> countMDictionaryIts(MDictionaryItCriteria criteria) {
        log.debug("REST request to count MDictionaryIts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDictionaryItQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dictionary-its/:id} : get the "id" mDictionaryIt.
     *
     * @param id the id of the mDictionaryItDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDictionaryItDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dictionary-its/{id}")
    public ResponseEntity<MDictionaryItDTO> getMDictionaryIt(@PathVariable Long id) {
        log.debug("REST request to get MDictionaryIt : {}", id);
        Optional<MDictionaryItDTO> mDictionaryItDTO = mDictionaryItService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDictionaryItDTO);
    }

    /**
     * {@code DELETE  /m-dictionary-its/:id} : delete the "id" mDictionaryIt.
     *
     * @param id the id of the mDictionaryItDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dictionary-its/{id}")
    public ResponseEntity<Void> deleteMDictionaryIt(@PathVariable Long id) {
        log.debug("REST request to delete MDictionaryIt : {}", id);
        mDictionaryItService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
