package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MStoryResourceMappingService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MStoryResourceMappingDTO;
import io.shm.tsubasa.service.dto.MStoryResourceMappingCriteria;
import io.shm.tsubasa.service.MStoryResourceMappingQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MStoryResourceMapping}.
 */
@RestController
@RequestMapping("/api")
public class MStoryResourceMappingResource {

    private final Logger log = LoggerFactory.getLogger(MStoryResourceMappingResource.class);

    private static final String ENTITY_NAME = "mStoryResourceMapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MStoryResourceMappingService mStoryResourceMappingService;

    private final MStoryResourceMappingQueryService mStoryResourceMappingQueryService;

    public MStoryResourceMappingResource(MStoryResourceMappingService mStoryResourceMappingService, MStoryResourceMappingQueryService mStoryResourceMappingQueryService) {
        this.mStoryResourceMappingService = mStoryResourceMappingService;
        this.mStoryResourceMappingQueryService = mStoryResourceMappingQueryService;
    }

    /**
     * {@code POST  /m-story-resource-mappings} : Create a new mStoryResourceMapping.
     *
     * @param mStoryResourceMappingDTO the mStoryResourceMappingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mStoryResourceMappingDTO, or with status {@code 400 (Bad Request)} if the mStoryResourceMapping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-story-resource-mappings")
    public ResponseEntity<MStoryResourceMappingDTO> createMStoryResourceMapping(@Valid @RequestBody MStoryResourceMappingDTO mStoryResourceMappingDTO) throws URISyntaxException {
        log.debug("REST request to save MStoryResourceMapping : {}", mStoryResourceMappingDTO);
        if (mStoryResourceMappingDTO.getId() != null) {
            throw new BadRequestAlertException("A new mStoryResourceMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MStoryResourceMappingDTO result = mStoryResourceMappingService.save(mStoryResourceMappingDTO);
        return ResponseEntity.created(new URI("/api/m-story-resource-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-story-resource-mappings} : Updates an existing mStoryResourceMapping.
     *
     * @param mStoryResourceMappingDTO the mStoryResourceMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mStoryResourceMappingDTO,
     * or with status {@code 400 (Bad Request)} if the mStoryResourceMappingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mStoryResourceMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-story-resource-mappings")
    public ResponseEntity<MStoryResourceMappingDTO> updateMStoryResourceMapping(@Valid @RequestBody MStoryResourceMappingDTO mStoryResourceMappingDTO) throws URISyntaxException {
        log.debug("REST request to update MStoryResourceMapping : {}", mStoryResourceMappingDTO);
        if (mStoryResourceMappingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MStoryResourceMappingDTO result = mStoryResourceMappingService.save(mStoryResourceMappingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mStoryResourceMappingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-story-resource-mappings} : get all the mStoryResourceMappings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mStoryResourceMappings in body.
     */
    @GetMapping("/m-story-resource-mappings")
    public ResponseEntity<List<MStoryResourceMappingDTO>> getAllMStoryResourceMappings(MStoryResourceMappingCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MStoryResourceMappings by criteria: {}", criteria);
        Page<MStoryResourceMappingDTO> page = mStoryResourceMappingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-story-resource-mappings/count} : count all the mStoryResourceMappings.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-story-resource-mappings/count")
    public ResponseEntity<Long> countMStoryResourceMappings(MStoryResourceMappingCriteria criteria) {
        log.debug("REST request to count MStoryResourceMappings by criteria: {}", criteria);
        return ResponseEntity.ok().body(mStoryResourceMappingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-story-resource-mappings/:id} : get the "id" mStoryResourceMapping.
     *
     * @param id the id of the mStoryResourceMappingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mStoryResourceMappingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-story-resource-mappings/{id}")
    public ResponseEntity<MStoryResourceMappingDTO> getMStoryResourceMapping(@PathVariable Long id) {
        log.debug("REST request to get MStoryResourceMapping : {}", id);
        Optional<MStoryResourceMappingDTO> mStoryResourceMappingDTO = mStoryResourceMappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mStoryResourceMappingDTO);
    }

    /**
     * {@code DELETE  /m-story-resource-mappings/:id} : delete the "id" mStoryResourceMapping.
     *
     * @param id the id of the mStoryResourceMappingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-story-resource-mappings/{id}")
    public ResponseEntity<Void> deleteMStoryResourceMapping(@PathVariable Long id) {
        log.debug("REST request to delete MStoryResourceMapping : {}", id);
        mStoryResourceMappingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
