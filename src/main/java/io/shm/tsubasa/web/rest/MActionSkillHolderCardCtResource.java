package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MActionSkillHolderCardCtService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardCtDTO;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardCtCriteria;
import io.shm.tsubasa.service.MActionSkillHolderCardCtQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MActionSkillHolderCardCt}.
 */
@RestController
@RequestMapping("/api")
public class MActionSkillHolderCardCtResource {

    private final Logger log = LoggerFactory.getLogger(MActionSkillHolderCardCtResource.class);

    private static final String ENTITY_NAME = "mActionSkillHolderCardCt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MActionSkillHolderCardCtService mActionSkillHolderCardCtService;

    private final MActionSkillHolderCardCtQueryService mActionSkillHolderCardCtQueryService;

    public MActionSkillHolderCardCtResource(MActionSkillHolderCardCtService mActionSkillHolderCardCtService, MActionSkillHolderCardCtQueryService mActionSkillHolderCardCtQueryService) {
        this.mActionSkillHolderCardCtService = mActionSkillHolderCardCtService;
        this.mActionSkillHolderCardCtQueryService = mActionSkillHolderCardCtQueryService;
    }

    /**
     * {@code POST  /m-action-skill-holder-card-cts} : Create a new mActionSkillHolderCardCt.
     *
     * @param mActionSkillHolderCardCtDTO the mActionSkillHolderCardCtDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mActionSkillHolderCardCtDTO, or with status {@code 400 (Bad Request)} if the mActionSkillHolderCardCt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-action-skill-holder-card-cts")
    public ResponseEntity<MActionSkillHolderCardCtDTO> createMActionSkillHolderCardCt(@Valid @RequestBody MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO) throws URISyntaxException {
        log.debug("REST request to save MActionSkillHolderCardCt : {}", mActionSkillHolderCardCtDTO);
        if (mActionSkillHolderCardCtDTO.getId() != null) {
            throw new BadRequestAlertException("A new mActionSkillHolderCardCt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MActionSkillHolderCardCtDTO result = mActionSkillHolderCardCtService.save(mActionSkillHolderCardCtDTO);
        return ResponseEntity.created(new URI("/api/m-action-skill-holder-card-cts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-action-skill-holder-card-cts} : Updates an existing mActionSkillHolderCardCt.
     *
     * @param mActionSkillHolderCardCtDTO the mActionSkillHolderCardCtDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mActionSkillHolderCardCtDTO,
     * or with status {@code 400 (Bad Request)} if the mActionSkillHolderCardCtDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mActionSkillHolderCardCtDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-action-skill-holder-card-cts")
    public ResponseEntity<MActionSkillHolderCardCtDTO> updateMActionSkillHolderCardCt(@Valid @RequestBody MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO) throws URISyntaxException {
        log.debug("REST request to update MActionSkillHolderCardCt : {}", mActionSkillHolderCardCtDTO);
        if (mActionSkillHolderCardCtDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MActionSkillHolderCardCtDTO result = mActionSkillHolderCardCtService.save(mActionSkillHolderCardCtDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mActionSkillHolderCardCtDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-action-skill-holder-card-cts} : get all the mActionSkillHolderCardCts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mActionSkillHolderCardCts in body.
     */
    @GetMapping("/m-action-skill-holder-card-cts")
    public ResponseEntity<List<MActionSkillHolderCardCtDTO>> getAllMActionSkillHolderCardCts(MActionSkillHolderCardCtCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MActionSkillHolderCardCts by criteria: {}", criteria);
        Page<MActionSkillHolderCardCtDTO> page = mActionSkillHolderCardCtQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-action-skill-holder-card-cts/count} : count all the mActionSkillHolderCardCts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-action-skill-holder-card-cts/count")
    public ResponseEntity<Long> countMActionSkillHolderCardCts(MActionSkillHolderCardCtCriteria criteria) {
        log.debug("REST request to count MActionSkillHolderCardCts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mActionSkillHolderCardCtQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-action-skill-holder-card-cts/:id} : get the "id" mActionSkillHolderCardCt.
     *
     * @param id the id of the mActionSkillHolderCardCtDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mActionSkillHolderCardCtDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-action-skill-holder-card-cts/{id}")
    public ResponseEntity<MActionSkillHolderCardCtDTO> getMActionSkillHolderCardCt(@PathVariable Long id) {
        log.debug("REST request to get MActionSkillHolderCardCt : {}", id);
        Optional<MActionSkillHolderCardCtDTO> mActionSkillHolderCardCtDTO = mActionSkillHolderCardCtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mActionSkillHolderCardCtDTO);
    }

    /**
     * {@code DELETE  /m-action-skill-holder-card-cts/:id} : delete the "id" mActionSkillHolderCardCt.
     *
     * @param id the id of the mActionSkillHolderCardCtDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-action-skill-holder-card-cts/{id}")
    public ResponseEntity<Void> deleteMActionSkillHolderCardCt(@PathVariable Long id) {
        log.debug("REST request to delete MActionSkillHolderCardCt : {}", id);
        mActionSkillHolderCardCtService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
