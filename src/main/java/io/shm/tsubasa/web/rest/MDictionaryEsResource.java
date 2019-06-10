package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDictionaryEsService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDictionaryEsDTO;
import io.shm.tsubasa.service.dto.MDictionaryEsCriteria;
import io.shm.tsubasa.service.MDictionaryEsQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDictionaryEs}.
 */
@RestController
@RequestMapping("/api")
public class MDictionaryEsResource {

    private final Logger log = LoggerFactory.getLogger(MDictionaryEsResource.class);

    private static final String ENTITY_NAME = "mDictionaryEs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDictionaryEsService mDictionaryEsService;

    private final MDictionaryEsQueryService mDictionaryEsQueryService;

    public MDictionaryEsResource(MDictionaryEsService mDictionaryEsService, MDictionaryEsQueryService mDictionaryEsQueryService) {
        this.mDictionaryEsService = mDictionaryEsService;
        this.mDictionaryEsQueryService = mDictionaryEsQueryService;
    }

    /**
     * {@code POST  /m-dictionary-es} : Create a new mDictionaryEs.
     *
     * @param mDictionaryEsDTO the mDictionaryEsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDictionaryEsDTO, or with status {@code 400 (Bad Request)} if the mDictionaryEs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dictionary-es")
    public ResponseEntity<MDictionaryEsDTO> createMDictionaryEs(@Valid @RequestBody MDictionaryEsDTO mDictionaryEsDTO) throws URISyntaxException {
        log.debug("REST request to save MDictionaryEs : {}", mDictionaryEsDTO);
        if (mDictionaryEsDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDictionaryEs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDictionaryEsDTO result = mDictionaryEsService.save(mDictionaryEsDTO);
        return ResponseEntity.created(new URI("/api/m-dictionary-es/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dictionary-es} : Updates an existing mDictionaryEs.
     *
     * @param mDictionaryEsDTO the mDictionaryEsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDictionaryEsDTO,
     * or with status {@code 400 (Bad Request)} if the mDictionaryEsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDictionaryEsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dictionary-es")
    public ResponseEntity<MDictionaryEsDTO> updateMDictionaryEs(@Valid @RequestBody MDictionaryEsDTO mDictionaryEsDTO) throws URISyntaxException {
        log.debug("REST request to update MDictionaryEs : {}", mDictionaryEsDTO);
        if (mDictionaryEsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDictionaryEsDTO result = mDictionaryEsService.save(mDictionaryEsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDictionaryEsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dictionary-es} : get all the mDictionaryEs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDictionaryEs in body.
     */
    @GetMapping("/m-dictionary-es")
    public ResponseEntity<List<MDictionaryEsDTO>> getAllMDictionaryEs(MDictionaryEsCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDictionaryEs by criteria: {}", criteria);
        Page<MDictionaryEsDTO> page = mDictionaryEsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dictionary-es/count} : count all the mDictionaryEs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dictionary-es/count")
    public ResponseEntity<Long> countMDictionaryEs(MDictionaryEsCriteria criteria) {
        log.debug("REST request to count MDictionaryEs by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDictionaryEsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dictionary-es/:id} : get the "id" mDictionaryEs.
     *
     * @param id the id of the mDictionaryEsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDictionaryEsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dictionary-es/{id}")
    public ResponseEntity<MDictionaryEsDTO> getMDictionaryEs(@PathVariable Long id) {
        log.debug("REST request to get MDictionaryEs : {}", id);
        Optional<MDictionaryEsDTO> mDictionaryEsDTO = mDictionaryEsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDictionaryEsDTO);
    }

    /**
     * {@code DELETE  /m-dictionary-es/:id} : delete the "id" mDictionaryEs.
     *
     * @param id the id of the mDictionaryEsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dictionary-es/{id}")
    public ResponseEntity<Void> deleteMDictionaryEs(@PathVariable Long id) {
        log.debug("REST request to delete MDictionaryEs : {}", id);
        mDictionaryEsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
