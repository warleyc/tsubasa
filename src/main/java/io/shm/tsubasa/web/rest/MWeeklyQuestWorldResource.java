package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MWeeklyQuestWorldService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MWeeklyQuestWorldDTO;
import io.shm.tsubasa.service.dto.MWeeklyQuestWorldCriteria;
import io.shm.tsubasa.service.MWeeklyQuestWorldQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MWeeklyQuestWorld}.
 */
@RestController
@RequestMapping("/api")
public class MWeeklyQuestWorldResource {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestWorldResource.class);

    private static final String ENTITY_NAME = "mWeeklyQuestWorld";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MWeeklyQuestWorldService mWeeklyQuestWorldService;

    private final MWeeklyQuestWorldQueryService mWeeklyQuestWorldQueryService;

    public MWeeklyQuestWorldResource(MWeeklyQuestWorldService mWeeklyQuestWorldService, MWeeklyQuestWorldQueryService mWeeklyQuestWorldQueryService) {
        this.mWeeklyQuestWorldService = mWeeklyQuestWorldService;
        this.mWeeklyQuestWorldQueryService = mWeeklyQuestWorldQueryService;
    }

    /**
     * {@code POST  /m-weekly-quest-worlds} : Create a new mWeeklyQuestWorld.
     *
     * @param mWeeklyQuestWorldDTO the mWeeklyQuestWorldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mWeeklyQuestWorldDTO, or with status {@code 400 (Bad Request)} if the mWeeklyQuestWorld has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-weekly-quest-worlds")
    public ResponseEntity<MWeeklyQuestWorldDTO> createMWeeklyQuestWorld(@Valid @RequestBody MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to save MWeeklyQuestWorld : {}", mWeeklyQuestWorldDTO);
        if (mWeeklyQuestWorldDTO.getId() != null) {
            throw new BadRequestAlertException("A new mWeeklyQuestWorld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MWeeklyQuestWorldDTO result = mWeeklyQuestWorldService.save(mWeeklyQuestWorldDTO);
        return ResponseEntity.created(new URI("/api/m-weekly-quest-worlds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-weekly-quest-worlds} : Updates an existing mWeeklyQuestWorld.
     *
     * @param mWeeklyQuestWorldDTO the mWeeklyQuestWorldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mWeeklyQuestWorldDTO,
     * or with status {@code 400 (Bad Request)} if the mWeeklyQuestWorldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mWeeklyQuestWorldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-weekly-quest-worlds")
    public ResponseEntity<MWeeklyQuestWorldDTO> updateMWeeklyQuestWorld(@Valid @RequestBody MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to update MWeeklyQuestWorld : {}", mWeeklyQuestWorldDTO);
        if (mWeeklyQuestWorldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MWeeklyQuestWorldDTO result = mWeeklyQuestWorldService.save(mWeeklyQuestWorldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mWeeklyQuestWorldDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-weekly-quest-worlds} : get all the mWeeklyQuestWorlds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mWeeklyQuestWorlds in body.
     */
    @GetMapping("/m-weekly-quest-worlds")
    public ResponseEntity<List<MWeeklyQuestWorldDTO>> getAllMWeeklyQuestWorlds(MWeeklyQuestWorldCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MWeeklyQuestWorlds by criteria: {}", criteria);
        Page<MWeeklyQuestWorldDTO> page = mWeeklyQuestWorldQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-weekly-quest-worlds/count} : count all the mWeeklyQuestWorlds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-weekly-quest-worlds/count")
    public ResponseEntity<Long> countMWeeklyQuestWorlds(MWeeklyQuestWorldCriteria criteria) {
        log.debug("REST request to count MWeeklyQuestWorlds by criteria: {}", criteria);
        return ResponseEntity.ok().body(mWeeklyQuestWorldQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-weekly-quest-worlds/:id} : get the "id" mWeeklyQuestWorld.
     *
     * @param id the id of the mWeeklyQuestWorldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mWeeklyQuestWorldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-weekly-quest-worlds/{id}")
    public ResponseEntity<MWeeklyQuestWorldDTO> getMWeeklyQuestWorld(@PathVariable Long id) {
        log.debug("REST request to get MWeeklyQuestWorld : {}", id);
        Optional<MWeeklyQuestWorldDTO> mWeeklyQuestWorldDTO = mWeeklyQuestWorldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mWeeklyQuestWorldDTO);
    }

    /**
     * {@code DELETE  /m-weekly-quest-worlds/:id} : delete the "id" mWeeklyQuestWorld.
     *
     * @param id the id of the mWeeklyQuestWorldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-weekly-quest-worlds/{id}")
    public ResponseEntity<Void> deleteMWeeklyQuestWorld(@PathVariable Long id) {
        log.debug("REST request to delete MWeeklyQuestWorld : {}", id);
        mWeeklyQuestWorldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
