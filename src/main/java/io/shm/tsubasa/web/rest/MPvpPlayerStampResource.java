package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPvpPlayerStampService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPvpPlayerStampDTO;
import io.shm.tsubasa.service.dto.MPvpPlayerStampCriteria;
import io.shm.tsubasa.service.MPvpPlayerStampQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPvpPlayerStamp}.
 */
@RestController
@RequestMapping("/api")
public class MPvpPlayerStampResource {

    private final Logger log = LoggerFactory.getLogger(MPvpPlayerStampResource.class);

    private static final String ENTITY_NAME = "mPvpPlayerStamp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPvpPlayerStampService mPvpPlayerStampService;

    private final MPvpPlayerStampQueryService mPvpPlayerStampQueryService;

    public MPvpPlayerStampResource(MPvpPlayerStampService mPvpPlayerStampService, MPvpPlayerStampQueryService mPvpPlayerStampQueryService) {
        this.mPvpPlayerStampService = mPvpPlayerStampService;
        this.mPvpPlayerStampQueryService = mPvpPlayerStampQueryService;
    }

    /**
     * {@code POST  /m-pvp-player-stamps} : Create a new mPvpPlayerStamp.
     *
     * @param mPvpPlayerStampDTO the mPvpPlayerStampDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPvpPlayerStampDTO, or with status {@code 400 (Bad Request)} if the mPvpPlayerStamp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-pvp-player-stamps")
    public ResponseEntity<MPvpPlayerStampDTO> createMPvpPlayerStamp(@Valid @RequestBody MPvpPlayerStampDTO mPvpPlayerStampDTO) throws URISyntaxException {
        log.debug("REST request to save MPvpPlayerStamp : {}", mPvpPlayerStampDTO);
        if (mPvpPlayerStampDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPvpPlayerStamp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPvpPlayerStampDTO result = mPvpPlayerStampService.save(mPvpPlayerStampDTO);
        return ResponseEntity.created(new URI("/api/m-pvp-player-stamps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-pvp-player-stamps} : Updates an existing mPvpPlayerStamp.
     *
     * @param mPvpPlayerStampDTO the mPvpPlayerStampDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPvpPlayerStampDTO,
     * or with status {@code 400 (Bad Request)} if the mPvpPlayerStampDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPvpPlayerStampDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-pvp-player-stamps")
    public ResponseEntity<MPvpPlayerStampDTO> updateMPvpPlayerStamp(@Valid @RequestBody MPvpPlayerStampDTO mPvpPlayerStampDTO) throws URISyntaxException {
        log.debug("REST request to update MPvpPlayerStamp : {}", mPvpPlayerStampDTO);
        if (mPvpPlayerStampDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPvpPlayerStampDTO result = mPvpPlayerStampService.save(mPvpPlayerStampDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPvpPlayerStampDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-pvp-player-stamps} : get all the mPvpPlayerStamps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPvpPlayerStamps in body.
     */
    @GetMapping("/m-pvp-player-stamps")
    public ResponseEntity<List<MPvpPlayerStampDTO>> getAllMPvpPlayerStamps(MPvpPlayerStampCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPvpPlayerStamps by criteria: {}", criteria);
        Page<MPvpPlayerStampDTO> page = mPvpPlayerStampQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-pvp-player-stamps/count} : count all the mPvpPlayerStamps.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-pvp-player-stamps/count")
    public ResponseEntity<Long> countMPvpPlayerStamps(MPvpPlayerStampCriteria criteria) {
        log.debug("REST request to count MPvpPlayerStamps by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPvpPlayerStampQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-pvp-player-stamps/:id} : get the "id" mPvpPlayerStamp.
     *
     * @param id the id of the mPvpPlayerStampDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPvpPlayerStampDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-pvp-player-stamps/{id}")
    public ResponseEntity<MPvpPlayerStampDTO> getMPvpPlayerStamp(@PathVariable Long id) {
        log.debug("REST request to get MPvpPlayerStamp : {}", id);
        Optional<MPvpPlayerStampDTO> mPvpPlayerStampDTO = mPvpPlayerStampService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPvpPlayerStampDTO);
    }

    /**
     * {@code DELETE  /m-pvp-player-stamps/:id} : delete the "id" mPvpPlayerStamp.
     *
     * @param id the id of the mPvpPlayerStampDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-pvp-player-stamps/{id}")
    public ResponseEntity<Void> deleteMPvpPlayerStamp(@PathVariable Long id) {
        log.debug("REST request to delete MPvpPlayerStamp : {}", id);
        mPvpPlayerStampService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
