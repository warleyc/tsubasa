package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDeckConditionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDeckConditionDTO;
import io.shm.tsubasa.service.dto.MDeckConditionCriteria;
import io.shm.tsubasa.service.MDeckConditionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDeckCondition}.
 */
@RestController
@RequestMapping("/api")
public class MDeckConditionResource {

    private final Logger log = LoggerFactory.getLogger(MDeckConditionResource.class);

    private static final String ENTITY_NAME = "mDeckCondition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDeckConditionService mDeckConditionService;

    private final MDeckConditionQueryService mDeckConditionQueryService;

    public MDeckConditionResource(MDeckConditionService mDeckConditionService, MDeckConditionQueryService mDeckConditionQueryService) {
        this.mDeckConditionService = mDeckConditionService;
        this.mDeckConditionQueryService = mDeckConditionQueryService;
    }

    /**
     * {@code POST  /m-deck-conditions} : Create a new mDeckCondition.
     *
     * @param mDeckConditionDTO the mDeckConditionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDeckConditionDTO, or with status {@code 400 (Bad Request)} if the mDeckCondition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-deck-conditions")
    public ResponseEntity<MDeckConditionDTO> createMDeckCondition(@Valid @RequestBody MDeckConditionDTO mDeckConditionDTO) throws URISyntaxException {
        log.debug("REST request to save MDeckCondition : {}", mDeckConditionDTO);
        if (mDeckConditionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDeckCondition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDeckConditionDTO result = mDeckConditionService.save(mDeckConditionDTO);
        return ResponseEntity.created(new URI("/api/m-deck-conditions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-deck-conditions} : Updates an existing mDeckCondition.
     *
     * @param mDeckConditionDTO the mDeckConditionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDeckConditionDTO,
     * or with status {@code 400 (Bad Request)} if the mDeckConditionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDeckConditionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-deck-conditions")
    public ResponseEntity<MDeckConditionDTO> updateMDeckCondition(@Valid @RequestBody MDeckConditionDTO mDeckConditionDTO) throws URISyntaxException {
        log.debug("REST request to update MDeckCondition : {}", mDeckConditionDTO);
        if (mDeckConditionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDeckConditionDTO result = mDeckConditionService.save(mDeckConditionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDeckConditionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-deck-conditions} : get all the mDeckConditions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDeckConditions in body.
     */
    @GetMapping("/m-deck-conditions")
    public ResponseEntity<List<MDeckConditionDTO>> getAllMDeckConditions(MDeckConditionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDeckConditions by criteria: {}", criteria);
        Page<MDeckConditionDTO> page = mDeckConditionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-deck-conditions/count} : count all the mDeckConditions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-deck-conditions/count")
    public ResponseEntity<Long> countMDeckConditions(MDeckConditionCriteria criteria) {
        log.debug("REST request to count MDeckConditions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDeckConditionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-deck-conditions/:id} : get the "id" mDeckCondition.
     *
     * @param id the id of the mDeckConditionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDeckConditionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-deck-conditions/{id}")
    public ResponseEntity<MDeckConditionDTO> getMDeckCondition(@PathVariable Long id) {
        log.debug("REST request to get MDeckCondition : {}", id);
        Optional<MDeckConditionDTO> mDeckConditionDTO = mDeckConditionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDeckConditionDTO);
    }

    /**
     * {@code DELETE  /m-deck-conditions/:id} : delete the "id" mDeckCondition.
     *
     * @param id the id of the mDeckConditionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-deck-conditions/{id}")
    public ResponseEntity<Void> deleteMDeckCondition(@PathVariable Long id) {
        log.debug("REST request to delete MDeckCondition : {}", id);
        mDeckConditionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
