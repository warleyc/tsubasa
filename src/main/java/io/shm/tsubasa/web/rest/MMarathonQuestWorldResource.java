package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonQuestWorldService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonQuestWorldDTO;
import io.shm.tsubasa.service.dto.MMarathonQuestWorldCriteria;
import io.shm.tsubasa.service.MMarathonQuestWorldQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonQuestWorld}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonQuestWorldResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestWorldResource.class);

    private static final String ENTITY_NAME = "mMarathonQuestWorld";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonQuestWorldService mMarathonQuestWorldService;

    private final MMarathonQuestWorldQueryService mMarathonQuestWorldQueryService;

    public MMarathonQuestWorldResource(MMarathonQuestWorldService mMarathonQuestWorldService, MMarathonQuestWorldQueryService mMarathonQuestWorldQueryService) {
        this.mMarathonQuestWorldService = mMarathonQuestWorldService;
        this.mMarathonQuestWorldQueryService = mMarathonQuestWorldQueryService;
    }

    /**
     * {@code POST  /m-marathon-quest-worlds} : Create a new mMarathonQuestWorld.
     *
     * @param mMarathonQuestWorldDTO the mMarathonQuestWorldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonQuestWorldDTO, or with status {@code 400 (Bad Request)} if the mMarathonQuestWorld has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-quest-worlds")
    public ResponseEntity<MMarathonQuestWorldDTO> createMMarathonQuestWorld(@Valid @RequestBody MMarathonQuestWorldDTO mMarathonQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonQuestWorld : {}", mMarathonQuestWorldDTO);
        if (mMarathonQuestWorldDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonQuestWorld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonQuestWorldDTO result = mMarathonQuestWorldService.save(mMarathonQuestWorldDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-quest-worlds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-quest-worlds} : Updates an existing mMarathonQuestWorld.
     *
     * @param mMarathonQuestWorldDTO the mMarathonQuestWorldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonQuestWorldDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonQuestWorldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonQuestWorldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-quest-worlds")
    public ResponseEntity<MMarathonQuestWorldDTO> updateMMarathonQuestWorld(@Valid @RequestBody MMarathonQuestWorldDTO mMarathonQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonQuestWorld : {}", mMarathonQuestWorldDTO);
        if (mMarathonQuestWorldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonQuestWorldDTO result = mMarathonQuestWorldService.save(mMarathonQuestWorldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonQuestWorldDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-quest-worlds} : get all the mMarathonQuestWorlds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonQuestWorlds in body.
     */
    @GetMapping("/m-marathon-quest-worlds")
    public ResponseEntity<List<MMarathonQuestWorldDTO>> getAllMMarathonQuestWorlds(MMarathonQuestWorldCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonQuestWorlds by criteria: {}", criteria);
        Page<MMarathonQuestWorldDTO> page = mMarathonQuestWorldQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-quest-worlds/count} : count all the mMarathonQuestWorlds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-quest-worlds/count")
    public ResponseEntity<Long> countMMarathonQuestWorlds(MMarathonQuestWorldCriteria criteria) {
        log.debug("REST request to count MMarathonQuestWorlds by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonQuestWorldQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-quest-worlds/:id} : get the "id" mMarathonQuestWorld.
     *
     * @param id the id of the mMarathonQuestWorldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonQuestWorldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-quest-worlds/{id}")
    public ResponseEntity<MMarathonQuestWorldDTO> getMMarathonQuestWorld(@PathVariable Long id) {
        log.debug("REST request to get MMarathonQuestWorld : {}", id);
        Optional<MMarathonQuestWorldDTO> mMarathonQuestWorldDTO = mMarathonQuestWorldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonQuestWorldDTO);
    }

    /**
     * {@code DELETE  /m-marathon-quest-worlds/:id} : delete the "id" mMarathonQuestWorld.
     *
     * @param id the id of the mMarathonQuestWorldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-quest-worlds/{id}")
    public ResponseEntity<Void> deleteMMarathonQuestWorld(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonQuestWorld : {}", id);
        mMarathonQuestWorldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
