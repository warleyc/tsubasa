package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDictionaryPtService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDictionaryPtDTO;
import io.shm.tsubasa.service.dto.MDictionaryPtCriteria;
import io.shm.tsubasa.service.MDictionaryPtQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDictionaryPt}.
 */
@RestController
@RequestMapping("/api")
public class MDictionaryPtResource {

    private final Logger log = LoggerFactory.getLogger(MDictionaryPtResource.class);

    private static final String ENTITY_NAME = "mDictionaryPt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDictionaryPtService mDictionaryPtService;

    private final MDictionaryPtQueryService mDictionaryPtQueryService;

    public MDictionaryPtResource(MDictionaryPtService mDictionaryPtService, MDictionaryPtQueryService mDictionaryPtQueryService) {
        this.mDictionaryPtService = mDictionaryPtService;
        this.mDictionaryPtQueryService = mDictionaryPtQueryService;
    }

    /**
     * {@code POST  /m-dictionary-pts} : Create a new mDictionaryPt.
     *
     * @param mDictionaryPtDTO the mDictionaryPtDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDictionaryPtDTO, or with status {@code 400 (Bad Request)} if the mDictionaryPt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dictionary-pts")
    public ResponseEntity<MDictionaryPtDTO> createMDictionaryPt(@Valid @RequestBody MDictionaryPtDTO mDictionaryPtDTO) throws URISyntaxException {
        log.debug("REST request to save MDictionaryPt : {}", mDictionaryPtDTO);
        if (mDictionaryPtDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDictionaryPt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDictionaryPtDTO result = mDictionaryPtService.save(mDictionaryPtDTO);
        return ResponseEntity.created(new URI("/api/m-dictionary-pts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dictionary-pts} : Updates an existing mDictionaryPt.
     *
     * @param mDictionaryPtDTO the mDictionaryPtDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDictionaryPtDTO,
     * or with status {@code 400 (Bad Request)} if the mDictionaryPtDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDictionaryPtDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dictionary-pts")
    public ResponseEntity<MDictionaryPtDTO> updateMDictionaryPt(@Valid @RequestBody MDictionaryPtDTO mDictionaryPtDTO) throws URISyntaxException {
        log.debug("REST request to update MDictionaryPt : {}", mDictionaryPtDTO);
        if (mDictionaryPtDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDictionaryPtDTO result = mDictionaryPtService.save(mDictionaryPtDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDictionaryPtDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dictionary-pts} : get all the mDictionaryPts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDictionaryPts in body.
     */
    @GetMapping("/m-dictionary-pts")
    public ResponseEntity<List<MDictionaryPtDTO>> getAllMDictionaryPts(MDictionaryPtCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDictionaryPts by criteria: {}", criteria);
        Page<MDictionaryPtDTO> page = mDictionaryPtQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dictionary-pts/count} : count all the mDictionaryPts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dictionary-pts/count")
    public ResponseEntity<Long> countMDictionaryPts(MDictionaryPtCriteria criteria) {
        log.debug("REST request to count MDictionaryPts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDictionaryPtQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dictionary-pts/:id} : get the "id" mDictionaryPt.
     *
     * @param id the id of the mDictionaryPtDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDictionaryPtDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dictionary-pts/{id}")
    public ResponseEntity<MDictionaryPtDTO> getMDictionaryPt(@PathVariable Long id) {
        log.debug("REST request to get MDictionaryPt : {}", id);
        Optional<MDictionaryPtDTO> mDictionaryPtDTO = mDictionaryPtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDictionaryPtDTO);
    }

    /**
     * {@code DELETE  /m-dictionary-pts/:id} : delete the "id" mDictionaryPt.
     *
     * @param id the id of the mDictionaryPtDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dictionary-pts/{id}")
    public ResponseEntity<Void> deleteMDictionaryPt(@PathVariable Long id) {
        log.debug("REST request to delete MDictionaryPt : {}", id);
        mDictionaryPtService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
