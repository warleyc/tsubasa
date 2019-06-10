package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDeckRarityConditionDescriptionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDeckRarityConditionDescriptionDTO;
import io.shm.tsubasa.service.dto.MDeckRarityConditionDescriptionCriteria;
import io.shm.tsubasa.service.MDeckRarityConditionDescriptionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDeckRarityConditionDescription}.
 */
@RestController
@RequestMapping("/api")
public class MDeckRarityConditionDescriptionResource {

    private final Logger log = LoggerFactory.getLogger(MDeckRarityConditionDescriptionResource.class);

    private static final String ENTITY_NAME = "mDeckRarityConditionDescription";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDeckRarityConditionDescriptionService mDeckRarityConditionDescriptionService;

    private final MDeckRarityConditionDescriptionQueryService mDeckRarityConditionDescriptionQueryService;

    public MDeckRarityConditionDescriptionResource(MDeckRarityConditionDescriptionService mDeckRarityConditionDescriptionService, MDeckRarityConditionDescriptionQueryService mDeckRarityConditionDescriptionQueryService) {
        this.mDeckRarityConditionDescriptionService = mDeckRarityConditionDescriptionService;
        this.mDeckRarityConditionDescriptionQueryService = mDeckRarityConditionDescriptionQueryService;
    }

    /**
     * {@code POST  /m-deck-rarity-condition-descriptions} : Create a new mDeckRarityConditionDescription.
     *
     * @param mDeckRarityConditionDescriptionDTO the mDeckRarityConditionDescriptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDeckRarityConditionDescriptionDTO, or with status {@code 400 (Bad Request)} if the mDeckRarityConditionDescription has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-deck-rarity-condition-descriptions")
    public ResponseEntity<MDeckRarityConditionDescriptionDTO> createMDeckRarityConditionDescription(@Valid @RequestBody MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO) throws URISyntaxException {
        log.debug("REST request to save MDeckRarityConditionDescription : {}", mDeckRarityConditionDescriptionDTO);
        if (mDeckRarityConditionDescriptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDeckRarityConditionDescription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDeckRarityConditionDescriptionDTO result = mDeckRarityConditionDescriptionService.save(mDeckRarityConditionDescriptionDTO);
        return ResponseEntity.created(new URI("/api/m-deck-rarity-condition-descriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-deck-rarity-condition-descriptions} : Updates an existing mDeckRarityConditionDescription.
     *
     * @param mDeckRarityConditionDescriptionDTO the mDeckRarityConditionDescriptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDeckRarityConditionDescriptionDTO,
     * or with status {@code 400 (Bad Request)} if the mDeckRarityConditionDescriptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDeckRarityConditionDescriptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-deck-rarity-condition-descriptions")
    public ResponseEntity<MDeckRarityConditionDescriptionDTO> updateMDeckRarityConditionDescription(@Valid @RequestBody MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO) throws URISyntaxException {
        log.debug("REST request to update MDeckRarityConditionDescription : {}", mDeckRarityConditionDescriptionDTO);
        if (mDeckRarityConditionDescriptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDeckRarityConditionDescriptionDTO result = mDeckRarityConditionDescriptionService.save(mDeckRarityConditionDescriptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDeckRarityConditionDescriptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-deck-rarity-condition-descriptions} : get all the mDeckRarityConditionDescriptions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDeckRarityConditionDescriptions in body.
     */
    @GetMapping("/m-deck-rarity-condition-descriptions")
    public ResponseEntity<List<MDeckRarityConditionDescriptionDTO>> getAllMDeckRarityConditionDescriptions(MDeckRarityConditionDescriptionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDeckRarityConditionDescriptions by criteria: {}", criteria);
        Page<MDeckRarityConditionDescriptionDTO> page = mDeckRarityConditionDescriptionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-deck-rarity-condition-descriptions/count} : count all the mDeckRarityConditionDescriptions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-deck-rarity-condition-descriptions/count")
    public ResponseEntity<Long> countMDeckRarityConditionDescriptions(MDeckRarityConditionDescriptionCriteria criteria) {
        log.debug("REST request to count MDeckRarityConditionDescriptions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDeckRarityConditionDescriptionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-deck-rarity-condition-descriptions/:id} : get the "id" mDeckRarityConditionDescription.
     *
     * @param id the id of the mDeckRarityConditionDescriptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDeckRarityConditionDescriptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-deck-rarity-condition-descriptions/{id}")
    public ResponseEntity<MDeckRarityConditionDescriptionDTO> getMDeckRarityConditionDescription(@PathVariable Long id) {
        log.debug("REST request to get MDeckRarityConditionDescription : {}", id);
        Optional<MDeckRarityConditionDescriptionDTO> mDeckRarityConditionDescriptionDTO = mDeckRarityConditionDescriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDeckRarityConditionDescriptionDTO);
    }

    /**
     * {@code DELETE  /m-deck-rarity-condition-descriptions/:id} : delete the "id" mDeckRarityConditionDescription.
     *
     * @param id the id of the mDeckRarityConditionDescriptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-deck-rarity-condition-descriptions/{id}")
    public ResponseEntity<Void> deleteMDeckRarityConditionDescription(@PathVariable Long id) {
        log.debug("REST request to delete MDeckRarityConditionDescription : {}", id);
        mDeckRarityConditionDescriptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
