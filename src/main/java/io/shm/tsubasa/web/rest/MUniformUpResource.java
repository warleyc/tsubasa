package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MUniformUpService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MUniformUpDTO;
import io.shm.tsubasa.service.dto.MUniformUpCriteria;
import io.shm.tsubasa.service.MUniformUpQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MUniformUp}.
 */
@RestController
@RequestMapping("/api")
public class MUniformUpResource {

    private final Logger log = LoggerFactory.getLogger(MUniformUpResource.class);

    private static final String ENTITY_NAME = "mUniformUp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MUniformUpService mUniformUpService;

    private final MUniformUpQueryService mUniformUpQueryService;

    public MUniformUpResource(MUniformUpService mUniformUpService, MUniformUpQueryService mUniformUpQueryService) {
        this.mUniformUpService = mUniformUpService;
        this.mUniformUpQueryService = mUniformUpQueryService;
    }

    /**
     * {@code POST  /m-uniform-ups} : Create a new mUniformUp.
     *
     * @param mUniformUpDTO the mUniformUpDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mUniformUpDTO, or with status {@code 400 (Bad Request)} if the mUniformUp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-uniform-ups")
    public ResponseEntity<MUniformUpDTO> createMUniformUp(@Valid @RequestBody MUniformUpDTO mUniformUpDTO) throws URISyntaxException {
        log.debug("REST request to save MUniformUp : {}", mUniformUpDTO);
        if (mUniformUpDTO.getId() != null) {
            throw new BadRequestAlertException("A new mUniformUp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MUniformUpDTO result = mUniformUpService.save(mUniformUpDTO);
        return ResponseEntity.created(new URI("/api/m-uniform-ups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-uniform-ups} : Updates an existing mUniformUp.
     *
     * @param mUniformUpDTO the mUniformUpDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mUniformUpDTO,
     * or with status {@code 400 (Bad Request)} if the mUniformUpDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mUniformUpDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-uniform-ups")
    public ResponseEntity<MUniformUpDTO> updateMUniformUp(@Valid @RequestBody MUniformUpDTO mUniformUpDTO) throws URISyntaxException {
        log.debug("REST request to update MUniformUp : {}", mUniformUpDTO);
        if (mUniformUpDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MUniformUpDTO result = mUniformUpService.save(mUniformUpDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mUniformUpDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-uniform-ups} : get all the mUniformUps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mUniformUps in body.
     */
    @GetMapping("/m-uniform-ups")
    public ResponseEntity<List<MUniformUpDTO>> getAllMUniformUps(MUniformUpCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MUniformUps by criteria: {}", criteria);
        Page<MUniformUpDTO> page = mUniformUpQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-uniform-ups/count} : count all the mUniformUps.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-uniform-ups/count")
    public ResponseEntity<Long> countMUniformUps(MUniformUpCriteria criteria) {
        log.debug("REST request to count MUniformUps by criteria: {}", criteria);
        return ResponseEntity.ok().body(mUniformUpQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-uniform-ups/:id} : get the "id" mUniformUp.
     *
     * @param id the id of the mUniformUpDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mUniformUpDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-uniform-ups/{id}")
    public ResponseEntity<MUniformUpDTO> getMUniformUp(@PathVariable Long id) {
        log.debug("REST request to get MUniformUp : {}", id);
        Optional<MUniformUpDTO> mUniformUpDTO = mUniformUpService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mUniformUpDTO);
    }

    /**
     * {@code DELETE  /m-uniform-ups/:id} : delete the "id" mUniformUp.
     *
     * @param id the id of the mUniformUpDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-uniform-ups/{id}")
    public ResponseEntity<Void> deleteMUniformUp(@PathVariable Long id) {
        log.debug("REST request to delete MUniformUp : {}", id);
        mUniformUpService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
