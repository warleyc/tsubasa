package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuildMissionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuildMissionDTO;
import io.shm.tsubasa.service.dto.MGuildMissionCriteria;
import io.shm.tsubasa.service.MGuildMissionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuildMission}.
 */
@RestController
@RequestMapping("/api")
public class MGuildMissionResource {

    private final Logger log = LoggerFactory.getLogger(MGuildMissionResource.class);

    private static final String ENTITY_NAME = "mGuildMission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuildMissionService mGuildMissionService;

    private final MGuildMissionQueryService mGuildMissionQueryService;

    public MGuildMissionResource(MGuildMissionService mGuildMissionService, MGuildMissionQueryService mGuildMissionQueryService) {
        this.mGuildMissionService = mGuildMissionService;
        this.mGuildMissionQueryService = mGuildMissionQueryService;
    }

    /**
     * {@code POST  /m-guild-missions} : Create a new mGuildMission.
     *
     * @param mGuildMissionDTO the mGuildMissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuildMissionDTO, or with status {@code 400 (Bad Request)} if the mGuildMission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guild-missions")
    public ResponseEntity<MGuildMissionDTO> createMGuildMission(@Valid @RequestBody MGuildMissionDTO mGuildMissionDTO) throws URISyntaxException {
        log.debug("REST request to save MGuildMission : {}", mGuildMissionDTO);
        if (mGuildMissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuildMission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuildMissionDTO result = mGuildMissionService.save(mGuildMissionDTO);
        return ResponseEntity.created(new URI("/api/m-guild-missions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guild-missions} : Updates an existing mGuildMission.
     *
     * @param mGuildMissionDTO the mGuildMissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuildMissionDTO,
     * or with status {@code 400 (Bad Request)} if the mGuildMissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuildMissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guild-missions")
    public ResponseEntity<MGuildMissionDTO> updateMGuildMission(@Valid @RequestBody MGuildMissionDTO mGuildMissionDTO) throws URISyntaxException {
        log.debug("REST request to update MGuildMission : {}", mGuildMissionDTO);
        if (mGuildMissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuildMissionDTO result = mGuildMissionService.save(mGuildMissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuildMissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guild-missions} : get all the mGuildMissions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuildMissions in body.
     */
    @GetMapping("/m-guild-missions")
    public ResponseEntity<List<MGuildMissionDTO>> getAllMGuildMissions(MGuildMissionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuildMissions by criteria: {}", criteria);
        Page<MGuildMissionDTO> page = mGuildMissionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guild-missions/count} : count all the mGuildMissions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guild-missions/count")
    public ResponseEntity<Long> countMGuildMissions(MGuildMissionCriteria criteria) {
        log.debug("REST request to count MGuildMissions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuildMissionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guild-missions/:id} : get the "id" mGuildMission.
     *
     * @param id the id of the mGuildMissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuildMissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guild-missions/{id}")
    public ResponseEntity<MGuildMissionDTO> getMGuildMission(@PathVariable Long id) {
        log.debug("REST request to get MGuildMission : {}", id);
        Optional<MGuildMissionDTO> mGuildMissionDTO = mGuildMissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuildMissionDTO);
    }

    /**
     * {@code DELETE  /m-guild-missions/:id} : delete the "id" mGuildMission.
     *
     * @param id the id of the mGuildMissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guild-missions/{id}")
    public ResponseEntity<Void> deleteMGuildMission(@PathVariable Long id) {
        log.debug("REST request to delete MGuildMission : {}", id);
        mGuildMissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
