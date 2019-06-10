package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCardThumbnailAssetsService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCardThumbnailAssetsDTO;
import io.shm.tsubasa.service.dto.MCardThumbnailAssetsCriteria;
import io.shm.tsubasa.service.MCardThumbnailAssetsQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCardThumbnailAssets}.
 */
@RestController
@RequestMapping("/api")
public class MCardThumbnailAssetsResource {

    private final Logger log = LoggerFactory.getLogger(MCardThumbnailAssetsResource.class);

    private static final String ENTITY_NAME = "mCardThumbnailAssets";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCardThumbnailAssetsService mCardThumbnailAssetsService;

    private final MCardThumbnailAssetsQueryService mCardThumbnailAssetsQueryService;

    public MCardThumbnailAssetsResource(MCardThumbnailAssetsService mCardThumbnailAssetsService, MCardThumbnailAssetsQueryService mCardThumbnailAssetsQueryService) {
        this.mCardThumbnailAssetsService = mCardThumbnailAssetsService;
        this.mCardThumbnailAssetsQueryService = mCardThumbnailAssetsQueryService;
    }

    /**
     * {@code POST  /m-card-thumbnail-assets} : Create a new mCardThumbnailAssets.
     *
     * @param mCardThumbnailAssetsDTO the mCardThumbnailAssetsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCardThumbnailAssetsDTO, or with status {@code 400 (Bad Request)} if the mCardThumbnailAssets has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-card-thumbnail-assets")
    public ResponseEntity<MCardThumbnailAssetsDTO> createMCardThumbnailAssets(@Valid @RequestBody MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO) throws URISyntaxException {
        log.debug("REST request to save MCardThumbnailAssets : {}", mCardThumbnailAssetsDTO);
        if (mCardThumbnailAssetsDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCardThumbnailAssets cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCardThumbnailAssetsDTO result = mCardThumbnailAssetsService.save(mCardThumbnailAssetsDTO);
        return ResponseEntity.created(new URI("/api/m-card-thumbnail-assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-card-thumbnail-assets} : Updates an existing mCardThumbnailAssets.
     *
     * @param mCardThumbnailAssetsDTO the mCardThumbnailAssetsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCardThumbnailAssetsDTO,
     * or with status {@code 400 (Bad Request)} if the mCardThumbnailAssetsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCardThumbnailAssetsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-card-thumbnail-assets")
    public ResponseEntity<MCardThumbnailAssetsDTO> updateMCardThumbnailAssets(@Valid @RequestBody MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO) throws URISyntaxException {
        log.debug("REST request to update MCardThumbnailAssets : {}", mCardThumbnailAssetsDTO);
        if (mCardThumbnailAssetsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCardThumbnailAssetsDTO result = mCardThumbnailAssetsService.save(mCardThumbnailAssetsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCardThumbnailAssetsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-card-thumbnail-assets} : get all the mCardThumbnailAssets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCardThumbnailAssets in body.
     */
    @GetMapping("/m-card-thumbnail-assets")
    public ResponseEntity<List<MCardThumbnailAssetsDTO>> getAllMCardThumbnailAssets(MCardThumbnailAssetsCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCardThumbnailAssets by criteria: {}", criteria);
        Page<MCardThumbnailAssetsDTO> page = mCardThumbnailAssetsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-card-thumbnail-assets/count} : count all the mCardThumbnailAssets.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-card-thumbnail-assets/count")
    public ResponseEntity<Long> countMCardThumbnailAssets(MCardThumbnailAssetsCriteria criteria) {
        log.debug("REST request to count MCardThumbnailAssets by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCardThumbnailAssetsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-card-thumbnail-assets/:id} : get the "id" mCardThumbnailAssets.
     *
     * @param id the id of the mCardThumbnailAssetsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCardThumbnailAssetsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-card-thumbnail-assets/{id}")
    public ResponseEntity<MCardThumbnailAssetsDTO> getMCardThumbnailAssets(@PathVariable Long id) {
        log.debug("REST request to get MCardThumbnailAssets : {}", id);
        Optional<MCardThumbnailAssetsDTO> mCardThumbnailAssetsDTO = mCardThumbnailAssetsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCardThumbnailAssetsDTO);
    }

    /**
     * {@code DELETE  /m-card-thumbnail-assets/:id} : delete the "id" mCardThumbnailAssets.
     *
     * @param id the id of the mCardThumbnailAssetsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-card-thumbnail-assets/{id}")
    public ResponseEntity<Void> deleteMCardThumbnailAssets(@PathVariable Long id) {
        log.debug("REST request to delete MCardThumbnailAssets : {}", id);
        mCardThumbnailAssetsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
