package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MPowerupActionSkillCostService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MPowerupActionSkillCostDTO;
import io.shm.tsubasa.service.dto.MPowerupActionSkillCostCriteria;
import io.shm.tsubasa.service.MPowerupActionSkillCostQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MPowerupActionSkillCost}.
 */
@RestController
@RequestMapping("/api")
public class MPowerupActionSkillCostResource {

    private final Logger log = LoggerFactory.getLogger(MPowerupActionSkillCostResource.class);

    private static final String ENTITY_NAME = "mPowerupActionSkillCost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MPowerupActionSkillCostService mPowerupActionSkillCostService;

    private final MPowerupActionSkillCostQueryService mPowerupActionSkillCostQueryService;

    public MPowerupActionSkillCostResource(MPowerupActionSkillCostService mPowerupActionSkillCostService, MPowerupActionSkillCostQueryService mPowerupActionSkillCostQueryService) {
        this.mPowerupActionSkillCostService = mPowerupActionSkillCostService;
        this.mPowerupActionSkillCostQueryService = mPowerupActionSkillCostQueryService;
    }

    /**
     * {@code POST  /m-powerup-action-skill-costs} : Create a new mPowerupActionSkillCost.
     *
     * @param mPowerupActionSkillCostDTO the mPowerupActionSkillCostDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mPowerupActionSkillCostDTO, or with status {@code 400 (Bad Request)} if the mPowerupActionSkillCost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-powerup-action-skill-costs")
    public ResponseEntity<MPowerupActionSkillCostDTO> createMPowerupActionSkillCost(@Valid @RequestBody MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO) throws URISyntaxException {
        log.debug("REST request to save MPowerupActionSkillCost : {}", mPowerupActionSkillCostDTO);
        if (mPowerupActionSkillCostDTO.getId() != null) {
            throw new BadRequestAlertException("A new mPowerupActionSkillCost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MPowerupActionSkillCostDTO result = mPowerupActionSkillCostService.save(mPowerupActionSkillCostDTO);
        return ResponseEntity.created(new URI("/api/m-powerup-action-skill-costs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-powerup-action-skill-costs} : Updates an existing mPowerupActionSkillCost.
     *
     * @param mPowerupActionSkillCostDTO the mPowerupActionSkillCostDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mPowerupActionSkillCostDTO,
     * or with status {@code 400 (Bad Request)} if the mPowerupActionSkillCostDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mPowerupActionSkillCostDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-powerup-action-skill-costs")
    public ResponseEntity<MPowerupActionSkillCostDTO> updateMPowerupActionSkillCost(@Valid @RequestBody MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO) throws URISyntaxException {
        log.debug("REST request to update MPowerupActionSkillCost : {}", mPowerupActionSkillCostDTO);
        if (mPowerupActionSkillCostDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MPowerupActionSkillCostDTO result = mPowerupActionSkillCostService.save(mPowerupActionSkillCostDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mPowerupActionSkillCostDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-powerup-action-skill-costs} : get all the mPowerupActionSkillCosts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mPowerupActionSkillCosts in body.
     */
    @GetMapping("/m-powerup-action-skill-costs")
    public ResponseEntity<List<MPowerupActionSkillCostDTO>> getAllMPowerupActionSkillCosts(MPowerupActionSkillCostCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MPowerupActionSkillCosts by criteria: {}", criteria);
        Page<MPowerupActionSkillCostDTO> page = mPowerupActionSkillCostQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-powerup-action-skill-costs/count} : count all the mPowerupActionSkillCosts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-powerup-action-skill-costs/count")
    public ResponseEntity<Long> countMPowerupActionSkillCosts(MPowerupActionSkillCostCriteria criteria) {
        log.debug("REST request to count MPowerupActionSkillCosts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mPowerupActionSkillCostQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-powerup-action-skill-costs/:id} : get the "id" mPowerupActionSkillCost.
     *
     * @param id the id of the mPowerupActionSkillCostDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mPowerupActionSkillCostDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-powerup-action-skill-costs/{id}")
    public ResponseEntity<MPowerupActionSkillCostDTO> getMPowerupActionSkillCost(@PathVariable Long id) {
        log.debug("REST request to get MPowerupActionSkillCost : {}", id);
        Optional<MPowerupActionSkillCostDTO> mPowerupActionSkillCostDTO = mPowerupActionSkillCostService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mPowerupActionSkillCostDTO);
    }

    /**
     * {@code DELETE  /m-powerup-action-skill-costs/:id} : delete the "id" mPowerupActionSkillCost.
     *
     * @param id the id of the mPowerupActionSkillCostDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-powerup-action-skill-costs/{id}")
    public ResponseEntity<Void> deleteMPowerupActionSkillCost(@PathVariable Long id) {
        log.debug("REST request to delete MPowerupActionSkillCost : {}", id);
        mPowerupActionSkillCostService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
