package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCardPlayableAssetsService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCardPlayableAssetsDTO;
import io.shm.tsubasa.service.dto.MCardPlayableAssetsCriteria;
import io.shm.tsubasa.service.MCardPlayableAssetsQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCardPlayableAssets}.
 */
@RestController
@RequestMapping("/api")
public class MCardPlayableAssetsResource {

    private final Logger log = LoggerFactory.getLogger(MCardPlayableAssetsResource.class);

    private static final String ENTITY_NAME = "mCardPlayableAssets";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCardPlayableAssetsService mCardPlayableAssetsService;

    private final MCardPlayableAssetsQueryService mCardPlayableAssetsQueryService;

    public MCardPlayableAssetsResource(MCardPlayableAssetsService mCardPlayableAssetsService, MCardPlayableAssetsQueryService mCardPlayableAssetsQueryService) {
        this.mCardPlayableAssetsService = mCardPlayableAssetsService;
        this.mCardPlayableAssetsQueryService = mCardPlayableAssetsQueryService;
    }

    /**
     * {@code POST  /m-card-playable-assets} : Create a new mCardPlayableAssets.
     *
     * @param mCardPlayableAssetsDTO the mCardPlayableAssetsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCardPlayableAssetsDTO, or with status {@code 400 (Bad Request)} if the mCardPlayableAssets has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-card-playable-assets")
    public ResponseEntity<MCardPlayableAssetsDTO> createMCardPlayableAssets(@Valid @RequestBody MCardPlayableAssetsDTO mCardPlayableAssetsDTO) throws URISyntaxException {
        log.debug("REST request to save MCardPlayableAssets : {}", mCardPlayableAssetsDTO);
        if (mCardPlayableAssetsDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCardPlayableAssets cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCardPlayableAssetsDTO result = mCardPlayableAssetsService.save(mCardPlayableAssetsDTO);
        return ResponseEntity.created(new URI("/api/m-card-playable-assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-card-playable-assets} : Updates an existing mCardPlayableAssets.
     *
     * @param mCardPlayableAssetsDTO the mCardPlayableAssetsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCardPlayableAssetsDTO,
     * or with status {@code 400 (Bad Request)} if the mCardPlayableAssetsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCardPlayableAssetsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-card-playable-assets")
    public ResponseEntity<MCardPlayableAssetsDTO> updateMCardPlayableAssets(@Valid @RequestBody MCardPlayableAssetsDTO mCardPlayableAssetsDTO) throws URISyntaxException {
        log.debug("REST request to update MCardPlayableAssets : {}", mCardPlayableAssetsDTO);
        if (mCardPlayableAssetsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCardPlayableAssetsDTO result = mCardPlayableAssetsService.save(mCardPlayableAssetsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCardPlayableAssetsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-card-playable-assets} : get all the mCardPlayableAssets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCardPlayableAssets in body.
     */
    @GetMapping("/m-card-playable-assets")
    public ResponseEntity<List<MCardPlayableAssetsDTO>> getAllMCardPlayableAssets(MCardPlayableAssetsCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCardPlayableAssets by criteria: {}", criteria);
        Page<MCardPlayableAssetsDTO> page = mCardPlayableAssetsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-card-playable-assets/count} : count all the mCardPlayableAssets.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-card-playable-assets/count")
    public ResponseEntity<Long> countMCardPlayableAssets(MCardPlayableAssetsCriteria criteria) {
        log.debug("REST request to count MCardPlayableAssets by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCardPlayableAssetsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-card-playable-assets/:id} : get the "id" mCardPlayableAssets.
     *
     * @param id the id of the mCardPlayableAssetsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCardPlayableAssetsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-card-playable-assets/{id}")
    public ResponseEntity<MCardPlayableAssetsDTO> getMCardPlayableAssets(@PathVariable Long id) {
        log.debug("REST request to get MCardPlayableAssets : {}", id);
        Optional<MCardPlayableAssetsDTO> mCardPlayableAssetsDTO = mCardPlayableAssetsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCardPlayableAssetsDTO);
    }

    /**
     * {@code DELETE  /m-card-playable-assets/:id} : delete the "id" mCardPlayableAssets.
     *
     * @param id the id of the mCardPlayableAssetsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-card-playable-assets/{id}")
    public ResponseEntity<Void> deleteMCardPlayableAssets(@PathVariable Long id) {
        log.debug("REST request to delete MCardPlayableAssets : {}", id);
        mCardPlayableAssetsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
