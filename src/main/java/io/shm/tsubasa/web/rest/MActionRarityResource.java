package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MActionRarityService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MActionRarityDTO;
import io.shm.tsubasa.service.dto.MActionRarityCriteria;
import io.shm.tsubasa.service.MActionRarityQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MActionRarity}.
 */
@RestController
@RequestMapping("/api")
public class MActionRarityResource {

    private final Logger log = LoggerFactory.getLogger(MActionRarityResource.class);

    private static final String ENTITY_NAME = "mActionRarity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MActionRarityService mActionRarityService;

    private final MActionRarityQueryService mActionRarityQueryService;

    public MActionRarityResource(MActionRarityService mActionRarityService, MActionRarityQueryService mActionRarityQueryService) {
        this.mActionRarityService = mActionRarityService;
        this.mActionRarityQueryService = mActionRarityQueryService;
    }

    /**
     * {@code POST  /m-action-rarities} : Create a new mActionRarity.
     *
     * @param mActionRarityDTO the mActionRarityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mActionRarityDTO, or with status {@code 400 (Bad Request)} if the mActionRarity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-action-rarities")
    public ResponseEntity<MActionRarityDTO> createMActionRarity(@Valid @RequestBody MActionRarityDTO mActionRarityDTO) throws URISyntaxException {
        log.debug("REST request to save MActionRarity : {}", mActionRarityDTO);
        if (mActionRarityDTO.getId() != null) {
            throw new BadRequestAlertException("A new mActionRarity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MActionRarityDTO result = mActionRarityService.save(mActionRarityDTO);
        return ResponseEntity.created(new URI("/api/m-action-rarities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-action-rarities} : Updates an existing mActionRarity.
     *
     * @param mActionRarityDTO the mActionRarityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mActionRarityDTO,
     * or with status {@code 400 (Bad Request)} if the mActionRarityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mActionRarityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-action-rarities")
    public ResponseEntity<MActionRarityDTO> updateMActionRarity(@Valid @RequestBody MActionRarityDTO mActionRarityDTO) throws URISyntaxException {
        log.debug("REST request to update MActionRarity : {}", mActionRarityDTO);
        if (mActionRarityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MActionRarityDTO result = mActionRarityService.save(mActionRarityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mActionRarityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-action-rarities} : get all the mActionRarities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mActionRarities in body.
     */
    @GetMapping("/m-action-rarities")
    public ResponseEntity<List<MActionRarityDTO>> getAllMActionRarities(MActionRarityCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MActionRarities by criteria: {}", criteria);
        Page<MActionRarityDTO> page = mActionRarityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-action-rarities/count} : count all the mActionRarities.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-action-rarities/count")
    public ResponseEntity<Long> countMActionRarities(MActionRarityCriteria criteria) {
        log.debug("REST request to count MActionRarities by criteria: {}", criteria);
        return ResponseEntity.ok().body(mActionRarityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-action-rarities/:id} : get the "id" mActionRarity.
     *
     * @param id the id of the mActionRarityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mActionRarityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-action-rarities/{id}")
    public ResponseEntity<MActionRarityDTO> getMActionRarity(@PathVariable Long id) {
        log.debug("REST request to get MActionRarity : {}", id);
        Optional<MActionRarityDTO> mActionRarityDTO = mActionRarityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mActionRarityDTO);
    }

    /**
     * {@code DELETE  /m-action-rarities/:id} : delete the "id" mActionRarity.
     *
     * @param id the id of the mActionRarityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-action-rarities/{id}")
    public ResponseEntity<Void> deleteMActionRarity(@PathVariable Long id) {
        log.debug("REST request to delete MActionRarity : {}", id);
        mActionRarityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
