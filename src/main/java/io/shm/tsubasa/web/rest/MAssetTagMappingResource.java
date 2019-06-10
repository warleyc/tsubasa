package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MAssetTagMappingService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MAssetTagMappingDTO;
import io.shm.tsubasa.service.dto.MAssetTagMappingCriteria;
import io.shm.tsubasa.service.MAssetTagMappingQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MAssetTagMapping}.
 */
@RestController
@RequestMapping("/api")
public class MAssetTagMappingResource {

    private final Logger log = LoggerFactory.getLogger(MAssetTagMappingResource.class);

    private static final String ENTITY_NAME = "mAssetTagMapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MAssetTagMappingService mAssetTagMappingService;

    private final MAssetTagMappingQueryService mAssetTagMappingQueryService;

    public MAssetTagMappingResource(MAssetTagMappingService mAssetTagMappingService, MAssetTagMappingQueryService mAssetTagMappingQueryService) {
        this.mAssetTagMappingService = mAssetTagMappingService;
        this.mAssetTagMappingQueryService = mAssetTagMappingQueryService;
    }

    /**
     * {@code POST  /m-asset-tag-mappings} : Create a new mAssetTagMapping.
     *
     * @param mAssetTagMappingDTO the mAssetTagMappingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mAssetTagMappingDTO, or with status {@code 400 (Bad Request)} if the mAssetTagMapping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-asset-tag-mappings")
    public ResponseEntity<MAssetTagMappingDTO> createMAssetTagMapping(@Valid @RequestBody MAssetTagMappingDTO mAssetTagMappingDTO) throws URISyntaxException {
        log.debug("REST request to save MAssetTagMapping : {}", mAssetTagMappingDTO);
        if (mAssetTagMappingDTO.getId() != null) {
            throw new BadRequestAlertException("A new mAssetTagMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MAssetTagMappingDTO result = mAssetTagMappingService.save(mAssetTagMappingDTO);
        return ResponseEntity.created(new URI("/api/m-asset-tag-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-asset-tag-mappings} : Updates an existing mAssetTagMapping.
     *
     * @param mAssetTagMappingDTO the mAssetTagMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mAssetTagMappingDTO,
     * or with status {@code 400 (Bad Request)} if the mAssetTagMappingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mAssetTagMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-asset-tag-mappings")
    public ResponseEntity<MAssetTagMappingDTO> updateMAssetTagMapping(@Valid @RequestBody MAssetTagMappingDTO mAssetTagMappingDTO) throws URISyntaxException {
        log.debug("REST request to update MAssetTagMapping : {}", mAssetTagMappingDTO);
        if (mAssetTagMappingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MAssetTagMappingDTO result = mAssetTagMappingService.save(mAssetTagMappingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mAssetTagMappingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-asset-tag-mappings} : get all the mAssetTagMappings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mAssetTagMappings in body.
     */
    @GetMapping("/m-asset-tag-mappings")
    public ResponseEntity<List<MAssetTagMappingDTO>> getAllMAssetTagMappings(MAssetTagMappingCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MAssetTagMappings by criteria: {}", criteria);
        Page<MAssetTagMappingDTO> page = mAssetTagMappingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-asset-tag-mappings/count} : count all the mAssetTagMappings.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-asset-tag-mappings/count")
    public ResponseEntity<Long> countMAssetTagMappings(MAssetTagMappingCriteria criteria) {
        log.debug("REST request to count MAssetTagMappings by criteria: {}", criteria);
        return ResponseEntity.ok().body(mAssetTagMappingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-asset-tag-mappings/:id} : get the "id" mAssetTagMapping.
     *
     * @param id the id of the mAssetTagMappingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mAssetTagMappingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-asset-tag-mappings/{id}")
    public ResponseEntity<MAssetTagMappingDTO> getMAssetTagMapping(@PathVariable Long id) {
        log.debug("REST request to get MAssetTagMapping : {}", id);
        Optional<MAssetTagMappingDTO> mAssetTagMappingDTO = mAssetTagMappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mAssetTagMappingDTO);
    }

    /**
     * {@code DELETE  /m-asset-tag-mappings/:id} : delete the "id" mAssetTagMapping.
     *
     * @param id the id of the mAssetTagMappingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-asset-tag-mappings/{id}")
    public ResponseEntity<Void> deleteMAssetTagMapping(@PathVariable Long id) {
        log.debug("REST request to delete MAssetTagMapping : {}", id);
        mAssetTagMappingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
