package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTeamService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTeamDTO;
import io.shm.tsubasa.service.dto.MTeamCriteria;
import io.shm.tsubasa.service.MTeamQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTeam}.
 */
@RestController
@RequestMapping("/api")
public class MTeamResource {

    private final Logger log = LoggerFactory.getLogger(MTeamResource.class);

    private static final String ENTITY_NAME = "mTeam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTeamService mTeamService;

    private final MTeamQueryService mTeamQueryService;

    public MTeamResource(MTeamService mTeamService, MTeamQueryService mTeamQueryService) {
        this.mTeamService = mTeamService;
        this.mTeamQueryService = mTeamQueryService;
    }

    /**
     * {@code POST  /m-teams} : Create a new mTeam.
     *
     * @param mTeamDTO the mTeamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTeamDTO, or with status {@code 400 (Bad Request)} if the mTeam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-teams")
    public ResponseEntity<MTeamDTO> createMTeam(@Valid @RequestBody MTeamDTO mTeamDTO) throws URISyntaxException {
        log.debug("REST request to save MTeam : {}", mTeamDTO);
        if (mTeamDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTeam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTeamDTO result = mTeamService.save(mTeamDTO);
        return ResponseEntity.created(new URI("/api/m-teams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-teams} : Updates an existing mTeam.
     *
     * @param mTeamDTO the mTeamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTeamDTO,
     * or with status {@code 400 (Bad Request)} if the mTeamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTeamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-teams")
    public ResponseEntity<MTeamDTO> updateMTeam(@Valid @RequestBody MTeamDTO mTeamDTO) throws URISyntaxException {
        log.debug("REST request to update MTeam : {}", mTeamDTO);
        if (mTeamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTeamDTO result = mTeamService.save(mTeamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTeamDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-teams} : get all the mTeams.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTeams in body.
     */
    @GetMapping("/m-teams")
    public ResponseEntity<List<MTeamDTO>> getAllMTeams(MTeamCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTeams by criteria: {}", criteria);
        Page<MTeamDTO> page = mTeamQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-teams/count} : count all the mTeams.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-teams/count")
    public ResponseEntity<Long> countMTeams(MTeamCriteria criteria) {
        log.debug("REST request to count MTeams by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTeamQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-teams/:id} : get the "id" mTeam.
     *
     * @param id the id of the mTeamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTeamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-teams/{id}")
    public ResponseEntity<MTeamDTO> getMTeam(@PathVariable Long id) {
        log.debug("REST request to get MTeam : {}", id);
        Optional<MTeamDTO> mTeamDTO = mTeamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTeamDTO);
    }

    /**
     * {@code DELETE  /m-teams/:id} : delete the "id" mTeam.
     *
     * @param id the id of the mTeamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-teams/{id}")
    public ResponseEntity<Void> deleteMTeam(@PathVariable Long id) {
        log.debug("REST request to delete MTeam : {}", id);
        mTeamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
