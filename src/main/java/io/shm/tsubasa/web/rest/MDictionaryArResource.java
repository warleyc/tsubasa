package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDictionaryArService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDictionaryArDTO;
import io.shm.tsubasa.service.dto.MDictionaryArCriteria;
import io.shm.tsubasa.service.MDictionaryArQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDictionaryAr}.
 */
@RestController
@RequestMapping("/api")
public class MDictionaryArResource {

    private final Logger log = LoggerFactory.getLogger(MDictionaryArResource.class);

    private static final String ENTITY_NAME = "mDictionaryAr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDictionaryArService mDictionaryArService;

    private final MDictionaryArQueryService mDictionaryArQueryService;

    public MDictionaryArResource(MDictionaryArService mDictionaryArService, MDictionaryArQueryService mDictionaryArQueryService) {
        this.mDictionaryArService = mDictionaryArService;
        this.mDictionaryArQueryService = mDictionaryArQueryService;
    }

    /**
     * {@code POST  /m-dictionary-ars} : Create a new mDictionaryAr.
     *
     * @param mDictionaryArDTO the mDictionaryArDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDictionaryArDTO, or with status {@code 400 (Bad Request)} if the mDictionaryAr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dictionary-ars")
    public ResponseEntity<MDictionaryArDTO> createMDictionaryAr(@Valid @RequestBody MDictionaryArDTO mDictionaryArDTO) throws URISyntaxException {
        log.debug("REST request to save MDictionaryAr : {}", mDictionaryArDTO);
        if (mDictionaryArDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDictionaryAr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDictionaryArDTO result = mDictionaryArService.save(mDictionaryArDTO);
        return ResponseEntity.created(new URI("/api/m-dictionary-ars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dictionary-ars} : Updates an existing mDictionaryAr.
     *
     * @param mDictionaryArDTO the mDictionaryArDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDictionaryArDTO,
     * or with status {@code 400 (Bad Request)} if the mDictionaryArDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDictionaryArDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dictionary-ars")
    public ResponseEntity<MDictionaryArDTO> updateMDictionaryAr(@Valid @RequestBody MDictionaryArDTO mDictionaryArDTO) throws URISyntaxException {
        log.debug("REST request to update MDictionaryAr : {}", mDictionaryArDTO);
        if (mDictionaryArDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDictionaryArDTO result = mDictionaryArService.save(mDictionaryArDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDictionaryArDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dictionary-ars} : get all the mDictionaryArs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDictionaryArs in body.
     */
    @GetMapping("/m-dictionary-ars")
    public ResponseEntity<List<MDictionaryArDTO>> getAllMDictionaryArs(MDictionaryArCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDictionaryArs by criteria: {}", criteria);
        Page<MDictionaryArDTO> page = mDictionaryArQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dictionary-ars/count} : count all the mDictionaryArs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dictionary-ars/count")
    public ResponseEntity<Long> countMDictionaryArs(MDictionaryArCriteria criteria) {
        log.debug("REST request to count MDictionaryArs by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDictionaryArQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dictionary-ars/:id} : get the "id" mDictionaryAr.
     *
     * @param id the id of the mDictionaryArDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDictionaryArDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dictionary-ars/{id}")
    public ResponseEntity<MDictionaryArDTO> getMDictionaryAr(@PathVariable Long id) {
        log.debug("REST request to get MDictionaryAr : {}", id);
        Optional<MDictionaryArDTO> mDictionaryArDTO = mDictionaryArService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDictionaryArDTO);
    }

    /**
     * {@code DELETE  /m-dictionary-ars/:id} : delete the "id" mDictionaryAr.
     *
     * @param id the id of the mDictionaryArDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dictionary-ars/{id}")
    public ResponseEntity<Void> deleteMDictionaryAr(@PathVariable Long id) {
        log.debug("REST request to delete MDictionaryAr : {}", id);
        mDictionaryArService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
