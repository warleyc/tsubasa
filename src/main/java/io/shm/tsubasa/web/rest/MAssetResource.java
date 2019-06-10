package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MAssetService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MAssetDTO;
import io.shm.tsubasa.service.dto.MAssetCriteria;
import io.shm.tsubasa.service.MAssetQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MAsset}.
 */
@RestController
@RequestMapping("/api")
public class MAssetResource {

    private final Logger log = LoggerFactory.getLogger(MAssetResource.class);

    private static final String ENTITY_NAME = "mAsset";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MAssetService mAssetService;

    private final MAssetQueryService mAssetQueryService;

    public MAssetResource(MAssetService mAssetService, MAssetQueryService mAssetQueryService) {
        this.mAssetService = mAssetService;
        this.mAssetQueryService = mAssetQueryService;
    }

    /**
     * {@code POST  /m-assets} : Create a new mAsset.
     *
     * @param mAssetDTO the mAssetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mAssetDTO, or with status {@code 400 (Bad Request)} if the mAsset has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-assets")
    public ResponseEntity<MAssetDTO> createMAsset(@Valid @RequestBody MAssetDTO mAssetDTO) throws URISyntaxException {
        log.debug("REST request to save MAsset : {}", mAssetDTO);
        if (mAssetDTO.getId() != null) {
            throw new BadRequestAlertException("A new mAsset cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MAssetDTO result = mAssetService.save(mAssetDTO);
        return ResponseEntity.created(new URI("/api/m-assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-assets} : Updates an existing mAsset.
     *
     * @param mAssetDTO the mAssetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mAssetDTO,
     * or with status {@code 400 (Bad Request)} if the mAssetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mAssetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-assets")
    public ResponseEntity<MAssetDTO> updateMAsset(@Valid @RequestBody MAssetDTO mAssetDTO) throws URISyntaxException {
        log.debug("REST request to update MAsset : {}", mAssetDTO);
        if (mAssetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MAssetDTO result = mAssetService.save(mAssetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mAssetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-assets} : get all the mAssets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mAssets in body.
     */
    @GetMapping("/m-assets")
    public ResponseEntity<List<MAssetDTO>> getAllMAssets(MAssetCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MAssets by criteria: {}", criteria);
        Page<MAssetDTO> page = mAssetQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-assets/count} : count all the mAssets.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-assets/count")
    public ResponseEntity<Long> countMAssets(MAssetCriteria criteria) {
        log.debug("REST request to count MAssets by criteria: {}", criteria);
        return ResponseEntity.ok().body(mAssetQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-assets/:id} : get the "id" mAsset.
     *
     * @param id the id of the mAssetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mAssetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-assets/{id}")
    public ResponseEntity<MAssetDTO> getMAsset(@PathVariable Long id) {
        log.debug("REST request to get MAsset : {}", id);
        Optional<MAssetDTO> mAssetDTO = mAssetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mAssetDTO);
    }

    /**
     * {@code DELETE  /m-assets/:id} : delete the "id" mAsset.
     *
     * @param id the id of the mAssetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-assets/{id}")
    public ResponseEntity<Void> deleteMAsset(@PathVariable Long id) {
        log.debug("REST request to delete MAsset : {}", id);
        mAssetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
