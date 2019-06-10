package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCardRarityService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCardRarityDTO;
import io.shm.tsubasa.service.dto.MCardRarityCriteria;
import io.shm.tsubasa.service.MCardRarityQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCardRarity}.
 */
@RestController
@RequestMapping("/api")
public class MCardRarityResource {

    private final Logger log = LoggerFactory.getLogger(MCardRarityResource.class);

    private static final String ENTITY_NAME = "mCardRarity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCardRarityService mCardRarityService;

    private final MCardRarityQueryService mCardRarityQueryService;

    public MCardRarityResource(MCardRarityService mCardRarityService, MCardRarityQueryService mCardRarityQueryService) {
        this.mCardRarityService = mCardRarityService;
        this.mCardRarityQueryService = mCardRarityQueryService;
    }

    /**
     * {@code POST  /m-card-rarities} : Create a new mCardRarity.
     *
     * @param mCardRarityDTO the mCardRarityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCardRarityDTO, or with status {@code 400 (Bad Request)} if the mCardRarity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-card-rarities")
    public ResponseEntity<MCardRarityDTO> createMCardRarity(@Valid @RequestBody MCardRarityDTO mCardRarityDTO) throws URISyntaxException {
        log.debug("REST request to save MCardRarity : {}", mCardRarityDTO);
        if (mCardRarityDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCardRarity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCardRarityDTO result = mCardRarityService.save(mCardRarityDTO);
        return ResponseEntity.created(new URI("/api/m-card-rarities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-card-rarities} : Updates an existing mCardRarity.
     *
     * @param mCardRarityDTO the mCardRarityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCardRarityDTO,
     * or with status {@code 400 (Bad Request)} if the mCardRarityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCardRarityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-card-rarities")
    public ResponseEntity<MCardRarityDTO> updateMCardRarity(@Valid @RequestBody MCardRarityDTO mCardRarityDTO) throws URISyntaxException {
        log.debug("REST request to update MCardRarity : {}", mCardRarityDTO);
        if (mCardRarityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCardRarityDTO result = mCardRarityService.save(mCardRarityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCardRarityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-card-rarities} : get all the mCardRarities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCardRarities in body.
     */
    @GetMapping("/m-card-rarities")
    public ResponseEntity<List<MCardRarityDTO>> getAllMCardRarities(MCardRarityCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCardRarities by criteria: {}", criteria);
        Page<MCardRarityDTO> page = mCardRarityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-card-rarities/count} : count all the mCardRarities.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-card-rarities/count")
    public ResponseEntity<Long> countMCardRarities(MCardRarityCriteria criteria) {
        log.debug("REST request to count MCardRarities by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCardRarityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-card-rarities/:id} : get the "id" mCardRarity.
     *
     * @param id the id of the mCardRarityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCardRarityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-card-rarities/{id}")
    public ResponseEntity<MCardRarityDTO> getMCardRarity(@PathVariable Long id) {
        log.debug("REST request to get MCardRarity : {}", id);
        Optional<MCardRarityDTO> mCardRarityDTO = mCardRarityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCardRarityDTO);
    }

    /**
     * {@code DELETE  /m-card-rarities/:id} : delete the "id" mCardRarity.
     *
     * @param id the id of the mCardRarityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-card-rarities/{id}")
    public ResponseEntity<Void> deleteMCardRarity(@PathVariable Long id) {
        log.debug("REST request to delete MCardRarity : {}", id);
        mCardRarityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
