package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDictionaryEnService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDictionaryEnDTO;
import io.shm.tsubasa.service.dto.MDictionaryEnCriteria;
import io.shm.tsubasa.service.MDictionaryEnQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDictionaryEn}.
 */
@RestController
@RequestMapping("/api")
public class MDictionaryEnResource {

    private final Logger log = LoggerFactory.getLogger(MDictionaryEnResource.class);

    private static final String ENTITY_NAME = "mDictionaryEn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDictionaryEnService mDictionaryEnService;

    private final MDictionaryEnQueryService mDictionaryEnQueryService;

    public MDictionaryEnResource(MDictionaryEnService mDictionaryEnService, MDictionaryEnQueryService mDictionaryEnQueryService) {
        this.mDictionaryEnService = mDictionaryEnService;
        this.mDictionaryEnQueryService = mDictionaryEnQueryService;
    }

    /**
     * {@code POST  /m-dictionary-ens} : Create a new mDictionaryEn.
     *
     * @param mDictionaryEnDTO the mDictionaryEnDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDictionaryEnDTO, or with status {@code 400 (Bad Request)} if the mDictionaryEn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dictionary-ens")
    public ResponseEntity<MDictionaryEnDTO> createMDictionaryEn(@Valid @RequestBody MDictionaryEnDTO mDictionaryEnDTO) throws URISyntaxException {
        log.debug("REST request to save MDictionaryEn : {}", mDictionaryEnDTO);
        if (mDictionaryEnDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDictionaryEn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDictionaryEnDTO result = mDictionaryEnService.save(mDictionaryEnDTO);
        return ResponseEntity.created(new URI("/api/m-dictionary-ens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dictionary-ens} : Updates an existing mDictionaryEn.
     *
     * @param mDictionaryEnDTO the mDictionaryEnDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDictionaryEnDTO,
     * or with status {@code 400 (Bad Request)} if the mDictionaryEnDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDictionaryEnDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dictionary-ens")
    public ResponseEntity<MDictionaryEnDTO> updateMDictionaryEn(@Valid @RequestBody MDictionaryEnDTO mDictionaryEnDTO) throws URISyntaxException {
        log.debug("REST request to update MDictionaryEn : {}", mDictionaryEnDTO);
        if (mDictionaryEnDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDictionaryEnDTO result = mDictionaryEnService.save(mDictionaryEnDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDictionaryEnDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dictionary-ens} : get all the mDictionaryEns.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDictionaryEns in body.
     */
    @GetMapping("/m-dictionary-ens")
    public ResponseEntity<List<MDictionaryEnDTO>> getAllMDictionaryEns(MDictionaryEnCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDictionaryEns by criteria: {}", criteria);
        Page<MDictionaryEnDTO> page = mDictionaryEnQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dictionary-ens/count} : count all the mDictionaryEns.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dictionary-ens/count")
    public ResponseEntity<Long> countMDictionaryEns(MDictionaryEnCriteria criteria) {
        log.debug("REST request to count MDictionaryEns by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDictionaryEnQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dictionary-ens/:id} : get the "id" mDictionaryEn.
     *
     * @param id the id of the mDictionaryEnDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDictionaryEnDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dictionary-ens/{id}")
    public ResponseEntity<MDictionaryEnDTO> getMDictionaryEn(@PathVariable Long id) {
        log.debug("REST request to get MDictionaryEn : {}", id);
        Optional<MDictionaryEnDTO> mDictionaryEnDTO = mDictionaryEnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDictionaryEnDTO);
    }

    /**
     * {@code DELETE  /m-dictionary-ens/:id} : delete the "id" mDictionaryEn.
     *
     * @param id the id of the mDictionaryEnDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dictionary-ens/{id}")
    public ResponseEntity<Void> deleteMDictionaryEn(@PathVariable Long id) {
        log.debug("REST request to delete MDictionaryEn : {}", id);
        mDictionaryEnService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
