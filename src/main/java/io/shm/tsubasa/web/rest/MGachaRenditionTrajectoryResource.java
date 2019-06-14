package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionTrajectoryService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCriteria;
import io.shm.tsubasa.service.MGachaRenditionTrajectoryQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionTrajectory}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionTrajectoryResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTrajectoryResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionTrajectory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionTrajectoryService mGachaRenditionTrajectoryService;

    private final MGachaRenditionTrajectoryQueryService mGachaRenditionTrajectoryQueryService;

    public MGachaRenditionTrajectoryResource(MGachaRenditionTrajectoryService mGachaRenditionTrajectoryService, MGachaRenditionTrajectoryQueryService mGachaRenditionTrajectoryQueryService) {
        this.mGachaRenditionTrajectoryService = mGachaRenditionTrajectoryService;
        this.mGachaRenditionTrajectoryQueryService = mGachaRenditionTrajectoryQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-trajectories} : Create a new mGachaRenditionTrajectory.
     *
     * @param mGachaRenditionTrajectoryDTO the mGachaRenditionTrajectoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionTrajectoryDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionTrajectory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-trajectories")
    public ResponseEntity<MGachaRenditionTrajectoryDTO> createMGachaRenditionTrajectory(@Valid @RequestBody MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionTrajectory : {}", mGachaRenditionTrajectoryDTO);
        if (mGachaRenditionTrajectoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionTrajectory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionTrajectoryDTO result = mGachaRenditionTrajectoryService.save(mGachaRenditionTrajectoryDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-trajectories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-trajectories} : Updates an existing mGachaRenditionTrajectory.
     *
     * @param mGachaRenditionTrajectoryDTO the mGachaRenditionTrajectoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionTrajectoryDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionTrajectoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionTrajectoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-trajectories")
    public ResponseEntity<MGachaRenditionTrajectoryDTO> updateMGachaRenditionTrajectory(@Valid @RequestBody MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionTrajectory : {}", mGachaRenditionTrajectoryDTO);
        if (mGachaRenditionTrajectoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionTrajectoryDTO result = mGachaRenditionTrajectoryService.save(mGachaRenditionTrajectoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionTrajectoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-trajectories} : get all the mGachaRenditionTrajectories.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionTrajectories in body.
     */
    @GetMapping("/m-gacha-rendition-trajectories")
    public ResponseEntity<List<MGachaRenditionTrajectoryDTO>> getAllMGachaRenditionTrajectories(MGachaRenditionTrajectoryCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionTrajectories by criteria: {}", criteria);
        Page<MGachaRenditionTrajectoryDTO> page = mGachaRenditionTrajectoryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-trajectories/count} : count all the mGachaRenditionTrajectories.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-trajectories/count")
    public ResponseEntity<Long> countMGachaRenditionTrajectories(MGachaRenditionTrajectoryCriteria criteria) {
        log.debug("REST request to count MGachaRenditionTrajectories by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionTrajectoryQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-trajectories/:id} : get the "id" mGachaRenditionTrajectory.
     *
     * @param id the id of the mGachaRenditionTrajectoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionTrajectoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-trajectories/{id}")
    public ResponseEntity<MGachaRenditionTrajectoryDTO> getMGachaRenditionTrajectory(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionTrajectory : {}", id);
        Optional<MGachaRenditionTrajectoryDTO> mGachaRenditionTrajectoryDTO = mGachaRenditionTrajectoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionTrajectoryDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-trajectories/:id} : delete the "id" mGachaRenditionTrajectory.
     *
     * @param id the id of the mGachaRenditionTrajectoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-trajectories/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionTrajectory(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionTrajectory : {}", id);
        mGachaRenditionTrajectoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
