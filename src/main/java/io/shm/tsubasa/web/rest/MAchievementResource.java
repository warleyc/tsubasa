package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MAchievementService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MAchievementDTO;
import io.shm.tsubasa.service.dto.MAchievementCriteria;
import io.shm.tsubasa.service.MAchievementQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MAchievement}.
 */
@RestController
@RequestMapping("/api")
public class MAchievementResource {

    private final Logger log = LoggerFactory.getLogger(MAchievementResource.class);

    private static final String ENTITY_NAME = "mAchievement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MAchievementService mAchievementService;

    private final MAchievementQueryService mAchievementQueryService;

    public MAchievementResource(MAchievementService mAchievementService, MAchievementQueryService mAchievementQueryService) {
        this.mAchievementService = mAchievementService;
        this.mAchievementQueryService = mAchievementQueryService;
    }

    /**
     * {@code POST  /m-achievements} : Create a new mAchievement.
     *
     * @param mAchievementDTO the mAchievementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mAchievementDTO, or with status {@code 400 (Bad Request)} if the mAchievement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-achievements")
    public ResponseEntity<MAchievementDTO> createMAchievement(@Valid @RequestBody MAchievementDTO mAchievementDTO) throws URISyntaxException {
        log.debug("REST request to save MAchievement : {}", mAchievementDTO);
        if (mAchievementDTO.getId() != null) {
            throw new BadRequestAlertException("A new mAchievement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MAchievementDTO result = mAchievementService.save(mAchievementDTO);
        return ResponseEntity.created(new URI("/api/m-achievements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-achievements} : Updates an existing mAchievement.
     *
     * @param mAchievementDTO the mAchievementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mAchievementDTO,
     * or with status {@code 400 (Bad Request)} if the mAchievementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mAchievementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-achievements")
    public ResponseEntity<MAchievementDTO> updateMAchievement(@Valid @RequestBody MAchievementDTO mAchievementDTO) throws URISyntaxException {
        log.debug("REST request to update MAchievement : {}", mAchievementDTO);
        if (mAchievementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MAchievementDTO result = mAchievementService.save(mAchievementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mAchievementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-achievements} : get all the mAchievements.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mAchievements in body.
     */
    @GetMapping("/m-achievements")
    public ResponseEntity<List<MAchievementDTO>> getAllMAchievements(MAchievementCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MAchievements by criteria: {}", criteria);
        Page<MAchievementDTO> page = mAchievementQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-achievements/count} : count all the mAchievements.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-achievements/count")
    public ResponseEntity<Long> countMAchievements(MAchievementCriteria criteria) {
        log.debug("REST request to count MAchievements by criteria: {}", criteria);
        return ResponseEntity.ok().body(mAchievementQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-achievements/:id} : get the "id" mAchievement.
     *
     * @param id the id of the mAchievementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mAchievementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-achievements/{id}")
    public ResponseEntity<MAchievementDTO> getMAchievement(@PathVariable Long id) {
        log.debug("REST request to get MAchievement : {}", id);
        Optional<MAchievementDTO> mAchievementDTO = mAchievementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mAchievementDTO);
    }

    /**
     * {@code DELETE  /m-achievements/:id} : delete the "id" mAchievement.
     *
     * @param id the id of the mAchievementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-achievements/{id}")
    public ResponseEntity<Void> deleteMAchievement(@PathVariable Long id) {
        log.debug("REST request to delete MAchievement : {}", id);
        mAchievementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
