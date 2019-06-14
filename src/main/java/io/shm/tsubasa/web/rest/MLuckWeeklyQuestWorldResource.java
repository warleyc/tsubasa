package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLuckWeeklyQuestWorldService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestWorldDTO;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestWorldCriteria;
import io.shm.tsubasa.service.MLuckWeeklyQuestWorldQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLuckWeeklyQuestWorld}.
 */
@RestController
@RequestMapping("/api")
public class MLuckWeeklyQuestWorldResource {

    private final Logger log = LoggerFactory.getLogger(MLuckWeeklyQuestWorldResource.class);

    private static final String ENTITY_NAME = "mLuckWeeklyQuestWorld";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLuckWeeklyQuestWorldService mLuckWeeklyQuestWorldService;

    private final MLuckWeeklyQuestWorldQueryService mLuckWeeklyQuestWorldQueryService;

    public MLuckWeeklyQuestWorldResource(MLuckWeeklyQuestWorldService mLuckWeeklyQuestWorldService, MLuckWeeklyQuestWorldQueryService mLuckWeeklyQuestWorldQueryService) {
        this.mLuckWeeklyQuestWorldService = mLuckWeeklyQuestWorldService;
        this.mLuckWeeklyQuestWorldQueryService = mLuckWeeklyQuestWorldQueryService;
    }

    /**
     * {@code POST  /m-luck-weekly-quest-worlds} : Create a new mLuckWeeklyQuestWorld.
     *
     * @param mLuckWeeklyQuestWorldDTO the mLuckWeeklyQuestWorldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLuckWeeklyQuestWorldDTO, or with status {@code 400 (Bad Request)} if the mLuckWeeklyQuestWorld has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-luck-weekly-quest-worlds")
    public ResponseEntity<MLuckWeeklyQuestWorldDTO> createMLuckWeeklyQuestWorld(@Valid @RequestBody MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to save MLuckWeeklyQuestWorld : {}", mLuckWeeklyQuestWorldDTO);
        if (mLuckWeeklyQuestWorldDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLuckWeeklyQuestWorld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLuckWeeklyQuestWorldDTO result = mLuckWeeklyQuestWorldService.save(mLuckWeeklyQuestWorldDTO);
        return ResponseEntity.created(new URI("/api/m-luck-weekly-quest-worlds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-luck-weekly-quest-worlds} : Updates an existing mLuckWeeklyQuestWorld.
     *
     * @param mLuckWeeklyQuestWorldDTO the mLuckWeeklyQuestWorldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLuckWeeklyQuestWorldDTO,
     * or with status {@code 400 (Bad Request)} if the mLuckWeeklyQuestWorldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLuckWeeklyQuestWorldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-luck-weekly-quest-worlds")
    public ResponseEntity<MLuckWeeklyQuestWorldDTO> updateMLuckWeeklyQuestWorld(@Valid @RequestBody MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO) throws URISyntaxException {
        log.debug("REST request to update MLuckWeeklyQuestWorld : {}", mLuckWeeklyQuestWorldDTO);
        if (mLuckWeeklyQuestWorldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLuckWeeklyQuestWorldDTO result = mLuckWeeklyQuestWorldService.save(mLuckWeeklyQuestWorldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLuckWeeklyQuestWorldDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-luck-weekly-quest-worlds} : get all the mLuckWeeklyQuestWorlds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLuckWeeklyQuestWorlds in body.
     */
    @GetMapping("/m-luck-weekly-quest-worlds")
    public ResponseEntity<List<MLuckWeeklyQuestWorldDTO>> getAllMLuckWeeklyQuestWorlds(MLuckWeeklyQuestWorldCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLuckWeeklyQuestWorlds by criteria: {}", criteria);
        Page<MLuckWeeklyQuestWorldDTO> page = mLuckWeeklyQuestWorldQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-luck-weekly-quest-worlds/count} : count all the mLuckWeeklyQuestWorlds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-luck-weekly-quest-worlds/count")
    public ResponseEntity<Long> countMLuckWeeklyQuestWorlds(MLuckWeeklyQuestWorldCriteria criteria) {
        log.debug("REST request to count MLuckWeeklyQuestWorlds by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLuckWeeklyQuestWorldQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-luck-weekly-quest-worlds/:id} : get the "id" mLuckWeeklyQuestWorld.
     *
     * @param id the id of the mLuckWeeklyQuestWorldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLuckWeeklyQuestWorldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-luck-weekly-quest-worlds/{id}")
    public ResponseEntity<MLuckWeeklyQuestWorldDTO> getMLuckWeeklyQuestWorld(@PathVariable Long id) {
        log.debug("REST request to get MLuckWeeklyQuestWorld : {}", id);
        Optional<MLuckWeeklyQuestWorldDTO> mLuckWeeklyQuestWorldDTO = mLuckWeeklyQuestWorldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLuckWeeklyQuestWorldDTO);
    }

    /**
     * {@code DELETE  /m-luck-weekly-quest-worlds/:id} : delete the "id" mLuckWeeklyQuestWorld.
     *
     * @param id the id of the mLuckWeeklyQuestWorldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-luck-weekly-quest-worlds/{id}")
    public ResponseEntity<Void> deleteMLuckWeeklyQuestWorld(@PathVariable Long id) {
        log.debug("REST request to delete MLuckWeeklyQuestWorld : {}", id);
        mLuckWeeklyQuestWorldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
