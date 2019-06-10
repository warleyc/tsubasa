package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCardIllustAssetsService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCardIllustAssetsDTO;
import io.shm.tsubasa.service.dto.MCardIllustAssetsCriteria;
import io.shm.tsubasa.service.MCardIllustAssetsQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCardIllustAssets}.
 */
@RestController
@RequestMapping("/api")
public class MCardIllustAssetsResource {

    private final Logger log = LoggerFactory.getLogger(MCardIllustAssetsResource.class);

    private static final String ENTITY_NAME = "mCardIllustAssets";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCardIllustAssetsService mCardIllustAssetsService;

    private final MCardIllustAssetsQueryService mCardIllustAssetsQueryService;

    public MCardIllustAssetsResource(MCardIllustAssetsService mCardIllustAssetsService, MCardIllustAssetsQueryService mCardIllustAssetsQueryService) {
        this.mCardIllustAssetsService = mCardIllustAssetsService;
        this.mCardIllustAssetsQueryService = mCardIllustAssetsQueryService;
    }

    /**
     * {@code POST  /m-card-illust-assets} : Create a new mCardIllustAssets.
     *
     * @param mCardIllustAssetsDTO the mCardIllustAssetsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCardIllustAssetsDTO, or with status {@code 400 (Bad Request)} if the mCardIllustAssets has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-card-illust-assets")
    public ResponseEntity<MCardIllustAssetsDTO> createMCardIllustAssets(@Valid @RequestBody MCardIllustAssetsDTO mCardIllustAssetsDTO) throws URISyntaxException {
        log.debug("REST request to save MCardIllustAssets : {}", mCardIllustAssetsDTO);
        if (mCardIllustAssetsDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCardIllustAssets cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCardIllustAssetsDTO result = mCardIllustAssetsService.save(mCardIllustAssetsDTO);
        return ResponseEntity.created(new URI("/api/m-card-illust-assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-card-illust-assets} : Updates an existing mCardIllustAssets.
     *
     * @param mCardIllustAssetsDTO the mCardIllustAssetsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCardIllustAssetsDTO,
     * or with status {@code 400 (Bad Request)} if the mCardIllustAssetsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCardIllustAssetsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-card-illust-assets")
    public ResponseEntity<MCardIllustAssetsDTO> updateMCardIllustAssets(@Valid @RequestBody MCardIllustAssetsDTO mCardIllustAssetsDTO) throws URISyntaxException {
        log.debug("REST request to update MCardIllustAssets : {}", mCardIllustAssetsDTO);
        if (mCardIllustAssetsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCardIllustAssetsDTO result = mCardIllustAssetsService.save(mCardIllustAssetsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCardIllustAssetsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-card-illust-assets} : get all the mCardIllustAssets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCardIllustAssets in body.
     */
    @GetMapping("/m-card-illust-assets")
    public ResponseEntity<List<MCardIllustAssetsDTO>> getAllMCardIllustAssets(MCardIllustAssetsCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCardIllustAssets by criteria: {}", criteria);
        Page<MCardIllustAssetsDTO> page = mCardIllustAssetsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-card-illust-assets/count} : count all the mCardIllustAssets.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-card-illust-assets/count")
    public ResponseEntity<Long> countMCardIllustAssets(MCardIllustAssetsCriteria criteria) {
        log.debug("REST request to count MCardIllustAssets by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCardIllustAssetsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-card-illust-assets/:id} : get the "id" mCardIllustAssets.
     *
     * @param id the id of the mCardIllustAssetsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCardIllustAssetsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-card-illust-assets/{id}")
    public ResponseEntity<MCardIllustAssetsDTO> getMCardIllustAssets(@PathVariable Long id) {
        log.debug("REST request to get MCardIllustAssets : {}", id);
        Optional<MCardIllustAssetsDTO> mCardIllustAssetsDTO = mCardIllustAssetsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCardIllustAssetsDTO);
    }

    /**
     * {@code DELETE  /m-card-illust-assets/:id} : delete the "id" mCardIllustAssets.
     *
     * @param id the id of the mCardIllustAssetsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-card-illust-assets/{id}")
    public ResponseEntity<Void> deleteMCardIllustAssets(@PathVariable Long id) {
        log.debug("REST request to delete MCardIllustAssets : {}", id);
        mCardIllustAssetsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
