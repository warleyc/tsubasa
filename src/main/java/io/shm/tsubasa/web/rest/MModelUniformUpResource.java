package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MModelUniformUpService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MModelUniformUpDTO;
import io.shm.tsubasa.service.dto.MModelUniformUpCriteria;
import io.shm.tsubasa.service.MModelUniformUpQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MModelUniformUp}.
 */
@RestController
@RequestMapping("/api")
public class MModelUniformUpResource {

    private final Logger log = LoggerFactory.getLogger(MModelUniformUpResource.class);

    private static final String ENTITY_NAME = "mModelUniformUp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MModelUniformUpService mModelUniformUpService;

    private final MModelUniformUpQueryService mModelUniformUpQueryService;

    public MModelUniformUpResource(MModelUniformUpService mModelUniformUpService, MModelUniformUpQueryService mModelUniformUpQueryService) {
        this.mModelUniformUpService = mModelUniformUpService;
        this.mModelUniformUpQueryService = mModelUniformUpQueryService;
    }

    /**
     * {@code POST  /m-model-uniform-ups} : Create a new mModelUniformUp.
     *
     * @param mModelUniformUpDTO the mModelUniformUpDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mModelUniformUpDTO, or with status {@code 400 (Bad Request)} if the mModelUniformUp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-model-uniform-ups")
    public ResponseEntity<MModelUniformUpDTO> createMModelUniformUp(@Valid @RequestBody MModelUniformUpDTO mModelUniformUpDTO) throws URISyntaxException {
        log.debug("REST request to save MModelUniformUp : {}", mModelUniformUpDTO);
        if (mModelUniformUpDTO.getId() != null) {
            throw new BadRequestAlertException("A new mModelUniformUp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MModelUniformUpDTO result = mModelUniformUpService.save(mModelUniformUpDTO);
        return ResponseEntity.created(new URI("/api/m-model-uniform-ups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-model-uniform-ups} : Updates an existing mModelUniformUp.
     *
     * @param mModelUniformUpDTO the mModelUniformUpDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mModelUniformUpDTO,
     * or with status {@code 400 (Bad Request)} if the mModelUniformUpDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mModelUniformUpDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-model-uniform-ups")
    public ResponseEntity<MModelUniformUpDTO> updateMModelUniformUp(@Valid @RequestBody MModelUniformUpDTO mModelUniformUpDTO) throws URISyntaxException {
        log.debug("REST request to update MModelUniformUp : {}", mModelUniformUpDTO);
        if (mModelUniformUpDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MModelUniformUpDTO result = mModelUniformUpService.save(mModelUniformUpDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mModelUniformUpDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-model-uniform-ups} : get all the mModelUniformUps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mModelUniformUps in body.
     */
    @GetMapping("/m-model-uniform-ups")
    public ResponseEntity<List<MModelUniformUpDTO>> getAllMModelUniformUps(MModelUniformUpCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MModelUniformUps by criteria: {}", criteria);
        Page<MModelUniformUpDTO> page = mModelUniformUpQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-model-uniform-ups/count} : count all the mModelUniformUps.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-model-uniform-ups/count")
    public ResponseEntity<Long> countMModelUniformUps(MModelUniformUpCriteria criteria) {
        log.debug("REST request to count MModelUniformUps by criteria: {}", criteria);
        return ResponseEntity.ok().body(mModelUniformUpQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-model-uniform-ups/:id} : get the "id" mModelUniformUp.
     *
     * @param id the id of the mModelUniformUpDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mModelUniformUpDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-model-uniform-ups/{id}")
    public ResponseEntity<MModelUniformUpDTO> getMModelUniformUp(@PathVariable Long id) {
        log.debug("REST request to get MModelUniformUp : {}", id);
        Optional<MModelUniformUpDTO> mModelUniformUpDTO = mModelUniformUpService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mModelUniformUpDTO);
    }

    /**
     * {@code DELETE  /m-model-uniform-ups/:id} : delete the "id" mModelUniformUp.
     *
     * @param id the id of the mModelUniformUpDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-model-uniform-ups/{id}")
    public ResponseEntity<Void> deleteMModelUniformUp(@PathVariable Long id) {
        log.debug("REST request to delete MModelUniformUp : {}", id);
        mModelUniformUpService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
