package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDictionaryFrService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDictionaryFrDTO;
import io.shm.tsubasa.service.dto.MDictionaryFrCriteria;
import io.shm.tsubasa.service.MDictionaryFrQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDictionaryFr}.
 */
@RestController
@RequestMapping("/api")
public class MDictionaryFrResource {

    private final Logger log = LoggerFactory.getLogger(MDictionaryFrResource.class);

    private static final String ENTITY_NAME = "mDictionaryFr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDictionaryFrService mDictionaryFrService;

    private final MDictionaryFrQueryService mDictionaryFrQueryService;

    public MDictionaryFrResource(MDictionaryFrService mDictionaryFrService, MDictionaryFrQueryService mDictionaryFrQueryService) {
        this.mDictionaryFrService = mDictionaryFrService;
        this.mDictionaryFrQueryService = mDictionaryFrQueryService;
    }

    /**
     * {@code POST  /m-dictionary-frs} : Create a new mDictionaryFr.
     *
     * @param mDictionaryFrDTO the mDictionaryFrDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDictionaryFrDTO, or with status {@code 400 (Bad Request)} if the mDictionaryFr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dictionary-frs")
    public ResponseEntity<MDictionaryFrDTO> createMDictionaryFr(@Valid @RequestBody MDictionaryFrDTO mDictionaryFrDTO) throws URISyntaxException {
        log.debug("REST request to save MDictionaryFr : {}", mDictionaryFrDTO);
        if (mDictionaryFrDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDictionaryFr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDictionaryFrDTO result = mDictionaryFrService.save(mDictionaryFrDTO);
        return ResponseEntity.created(new URI("/api/m-dictionary-frs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dictionary-frs} : Updates an existing mDictionaryFr.
     *
     * @param mDictionaryFrDTO the mDictionaryFrDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDictionaryFrDTO,
     * or with status {@code 400 (Bad Request)} if the mDictionaryFrDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDictionaryFrDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dictionary-frs")
    public ResponseEntity<MDictionaryFrDTO> updateMDictionaryFr(@Valid @RequestBody MDictionaryFrDTO mDictionaryFrDTO) throws URISyntaxException {
        log.debug("REST request to update MDictionaryFr : {}", mDictionaryFrDTO);
        if (mDictionaryFrDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDictionaryFrDTO result = mDictionaryFrService.save(mDictionaryFrDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDictionaryFrDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dictionary-frs} : get all the mDictionaryFrs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDictionaryFrs in body.
     */
    @GetMapping("/m-dictionary-frs")
    public ResponseEntity<List<MDictionaryFrDTO>> getAllMDictionaryFrs(MDictionaryFrCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDictionaryFrs by criteria: {}", criteria);
        Page<MDictionaryFrDTO> page = mDictionaryFrQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dictionary-frs/count} : count all the mDictionaryFrs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dictionary-frs/count")
    public ResponseEntity<Long> countMDictionaryFrs(MDictionaryFrCriteria criteria) {
        log.debug("REST request to count MDictionaryFrs by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDictionaryFrQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dictionary-frs/:id} : get the "id" mDictionaryFr.
     *
     * @param id the id of the mDictionaryFrDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDictionaryFrDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dictionary-frs/{id}")
    public ResponseEntity<MDictionaryFrDTO> getMDictionaryFr(@PathVariable Long id) {
        log.debug("REST request to get MDictionaryFr : {}", id);
        Optional<MDictionaryFrDTO> mDictionaryFrDTO = mDictionaryFrService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDictionaryFrDTO);
    }

    /**
     * {@code DELETE  /m-dictionary-frs/:id} : delete the "id" mDictionaryFr.
     *
     * @param id the id of the mDictionaryFrDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dictionary-frs/{id}")
    public ResponseEntity<Void> deleteMDictionaryFr(@PathVariable Long id) {
        log.debug("REST request to delete MDictionaryFr : {}", id);
        mDictionaryFrService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
