package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMovieAssetService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMovieAssetDTO;
import io.shm.tsubasa.service.dto.MMovieAssetCriteria;
import io.shm.tsubasa.service.MMovieAssetQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMovieAsset}.
 */
@RestController
@RequestMapping("/api")
public class MMovieAssetResource {

    private final Logger log = LoggerFactory.getLogger(MMovieAssetResource.class);

    private static final String ENTITY_NAME = "mMovieAsset";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMovieAssetService mMovieAssetService;

    private final MMovieAssetQueryService mMovieAssetQueryService;

    public MMovieAssetResource(MMovieAssetService mMovieAssetService, MMovieAssetQueryService mMovieAssetQueryService) {
        this.mMovieAssetService = mMovieAssetService;
        this.mMovieAssetQueryService = mMovieAssetQueryService;
    }

    /**
     * {@code POST  /m-movie-assets} : Create a new mMovieAsset.
     *
     * @param mMovieAssetDTO the mMovieAssetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMovieAssetDTO, or with status {@code 400 (Bad Request)} if the mMovieAsset has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-movie-assets")
    public ResponseEntity<MMovieAssetDTO> createMMovieAsset(@Valid @RequestBody MMovieAssetDTO mMovieAssetDTO) throws URISyntaxException {
        log.debug("REST request to save MMovieAsset : {}", mMovieAssetDTO);
        if (mMovieAssetDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMovieAsset cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMovieAssetDTO result = mMovieAssetService.save(mMovieAssetDTO);
        return ResponseEntity.created(new URI("/api/m-movie-assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-movie-assets} : Updates an existing mMovieAsset.
     *
     * @param mMovieAssetDTO the mMovieAssetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMovieAssetDTO,
     * or with status {@code 400 (Bad Request)} if the mMovieAssetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMovieAssetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-movie-assets")
    public ResponseEntity<MMovieAssetDTO> updateMMovieAsset(@Valid @RequestBody MMovieAssetDTO mMovieAssetDTO) throws URISyntaxException {
        log.debug("REST request to update MMovieAsset : {}", mMovieAssetDTO);
        if (mMovieAssetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMovieAssetDTO result = mMovieAssetService.save(mMovieAssetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMovieAssetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-movie-assets} : get all the mMovieAssets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMovieAssets in body.
     */
    @GetMapping("/m-movie-assets")
    public ResponseEntity<List<MMovieAssetDTO>> getAllMMovieAssets(MMovieAssetCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMovieAssets by criteria: {}", criteria);
        Page<MMovieAssetDTO> page = mMovieAssetQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-movie-assets/count} : count all the mMovieAssets.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-movie-assets/count")
    public ResponseEntity<Long> countMMovieAssets(MMovieAssetCriteria criteria) {
        log.debug("REST request to count MMovieAssets by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMovieAssetQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-movie-assets/:id} : get the "id" mMovieAsset.
     *
     * @param id the id of the mMovieAssetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMovieAssetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-movie-assets/{id}")
    public ResponseEntity<MMovieAssetDTO> getMMovieAsset(@PathVariable Long id) {
        log.debug("REST request to get MMovieAsset : {}", id);
        Optional<MMovieAssetDTO> mMovieAssetDTO = mMovieAssetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMovieAssetDTO);
    }

    /**
     * {@code DELETE  /m-movie-assets/:id} : delete the "id" mMovieAsset.
     *
     * @param id the id of the mMovieAssetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-movie-assets/{id}")
    public ResponseEntity<Void> deleteMMovieAsset(@PathVariable Long id) {
        log.debug("REST request to delete MMovieAsset : {}", id);
        mMovieAssetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
