package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMissionService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMissionDTO;
import io.shm.tsubasa.service.dto.MMissionCriteria;
import io.shm.tsubasa.service.MMissionQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMission}.
 */
@RestController
@RequestMapping("/api")
public class MMissionResource {

    private final Logger log = LoggerFactory.getLogger(MMissionResource.class);

    private static final String ENTITY_NAME = "mMission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMissionService mMissionService;

    private final MMissionQueryService mMissionQueryService;

    public MMissionResource(MMissionService mMissionService, MMissionQueryService mMissionQueryService) {
        this.mMissionService = mMissionService;
        this.mMissionQueryService = mMissionQueryService;
    }

    /**
     * {@code POST  /m-missions} : Create a new mMission.
     *
     * @param mMissionDTO the mMissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMissionDTO, or with status {@code 400 (Bad Request)} if the mMission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-missions")
    public ResponseEntity<MMissionDTO> createMMission(@Valid @RequestBody MMissionDTO mMissionDTO) throws URISyntaxException {
        log.debug("REST request to save MMission : {}", mMissionDTO);
        if (mMissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMissionDTO result = mMissionService.save(mMissionDTO);
        return ResponseEntity.created(new URI("/api/m-missions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-missions} : Updates an existing mMission.
     *
     * @param mMissionDTO the mMissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMissionDTO,
     * or with status {@code 400 (Bad Request)} if the mMissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-missions")
    public ResponseEntity<MMissionDTO> updateMMission(@Valid @RequestBody MMissionDTO mMissionDTO) throws URISyntaxException {
        log.debug("REST request to update MMission : {}", mMissionDTO);
        if (mMissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMissionDTO result = mMissionService.save(mMissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-missions} : get all the mMissions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMissions in body.
     */
    @GetMapping("/m-missions")
    public ResponseEntity<List<MMissionDTO>> getAllMMissions(MMissionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMissions by criteria: {}", criteria);
        Page<MMissionDTO> page = mMissionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-missions/count} : count all the mMissions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-missions/count")
    public ResponseEntity<Long> countMMissions(MMissionCriteria criteria) {
        log.debug("REST request to count MMissions by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMissionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-missions/:id} : get the "id" mMission.
     *
     * @param id the id of the mMissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-missions/{id}")
    public ResponseEntity<MMissionDTO> getMMission(@PathVariable Long id) {
        log.debug("REST request to get MMission : {}", id);
        Optional<MMissionDTO> mMissionDTO = mMissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMissionDTO);
    }

    /**
     * {@code DELETE  /m-missions/:id} : delete the "id" mMission.
     *
     * @param id the id of the mMissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-missions/{id}")
    public ResponseEntity<Void> deleteMMission(@PathVariable Long id) {
        log.debug("REST request to delete MMission : {}", id);
        mMissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
