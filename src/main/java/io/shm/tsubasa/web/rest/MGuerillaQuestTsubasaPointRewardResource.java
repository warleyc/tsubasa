package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuerillaQuestTsubasaPointRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuerillaQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.dto.MGuerillaQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MGuerillaQuestTsubasaPointRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuerillaQuestTsubasaPointReward}.
 */
@RestController
@RequestMapping("/api")
public class MGuerillaQuestTsubasaPointRewardResource {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestTsubasaPointRewardResource.class);

    private static final String ENTITY_NAME = "mGuerillaQuestTsubasaPointReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuerillaQuestTsubasaPointRewardService mGuerillaQuestTsubasaPointRewardService;

    private final MGuerillaQuestTsubasaPointRewardQueryService mGuerillaQuestTsubasaPointRewardQueryService;

    public MGuerillaQuestTsubasaPointRewardResource(MGuerillaQuestTsubasaPointRewardService mGuerillaQuestTsubasaPointRewardService, MGuerillaQuestTsubasaPointRewardQueryService mGuerillaQuestTsubasaPointRewardQueryService) {
        this.mGuerillaQuestTsubasaPointRewardService = mGuerillaQuestTsubasaPointRewardService;
        this.mGuerillaQuestTsubasaPointRewardQueryService = mGuerillaQuestTsubasaPointRewardQueryService;
    }

    /**
     * {@code POST  /m-guerilla-quest-tsubasa-point-rewards} : Create a new mGuerillaQuestTsubasaPointReward.
     *
     * @param mGuerillaQuestTsubasaPointRewardDTO the mGuerillaQuestTsubasaPointRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuerillaQuestTsubasaPointRewardDTO, or with status {@code 400 (Bad Request)} if the mGuerillaQuestTsubasaPointReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guerilla-quest-tsubasa-point-rewards")
    public ResponseEntity<MGuerillaQuestTsubasaPointRewardDTO> createMGuerillaQuestTsubasaPointReward(@Valid @RequestBody MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MGuerillaQuestTsubasaPointReward : {}", mGuerillaQuestTsubasaPointRewardDTO);
        if (mGuerillaQuestTsubasaPointRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuerillaQuestTsubasaPointReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuerillaQuestTsubasaPointRewardDTO result = mGuerillaQuestTsubasaPointRewardService.save(mGuerillaQuestTsubasaPointRewardDTO);
        return ResponseEntity.created(new URI("/api/m-guerilla-quest-tsubasa-point-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guerilla-quest-tsubasa-point-rewards} : Updates an existing mGuerillaQuestTsubasaPointReward.
     *
     * @param mGuerillaQuestTsubasaPointRewardDTO the mGuerillaQuestTsubasaPointRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuerillaQuestTsubasaPointRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mGuerillaQuestTsubasaPointRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuerillaQuestTsubasaPointRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guerilla-quest-tsubasa-point-rewards")
    public ResponseEntity<MGuerillaQuestTsubasaPointRewardDTO> updateMGuerillaQuestTsubasaPointReward(@Valid @RequestBody MGuerillaQuestTsubasaPointRewardDTO mGuerillaQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MGuerillaQuestTsubasaPointReward : {}", mGuerillaQuestTsubasaPointRewardDTO);
        if (mGuerillaQuestTsubasaPointRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuerillaQuestTsubasaPointRewardDTO result = mGuerillaQuestTsubasaPointRewardService.save(mGuerillaQuestTsubasaPointRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuerillaQuestTsubasaPointRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guerilla-quest-tsubasa-point-rewards} : get all the mGuerillaQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuerillaQuestTsubasaPointRewards in body.
     */
    @GetMapping("/m-guerilla-quest-tsubasa-point-rewards")
    public ResponseEntity<List<MGuerillaQuestTsubasaPointRewardDTO>> getAllMGuerillaQuestTsubasaPointRewards(MGuerillaQuestTsubasaPointRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuerillaQuestTsubasaPointRewards by criteria: {}", criteria);
        Page<MGuerillaQuestTsubasaPointRewardDTO> page = mGuerillaQuestTsubasaPointRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guerilla-quest-tsubasa-point-rewards/count} : count all the mGuerillaQuestTsubasaPointRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guerilla-quest-tsubasa-point-rewards/count")
    public ResponseEntity<Long> countMGuerillaQuestTsubasaPointRewards(MGuerillaQuestTsubasaPointRewardCriteria criteria) {
        log.debug("REST request to count MGuerillaQuestTsubasaPointRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuerillaQuestTsubasaPointRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guerilla-quest-tsubasa-point-rewards/:id} : get the "id" mGuerillaQuestTsubasaPointReward.
     *
     * @param id the id of the mGuerillaQuestTsubasaPointRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuerillaQuestTsubasaPointRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guerilla-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<MGuerillaQuestTsubasaPointRewardDTO> getMGuerillaQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to get MGuerillaQuestTsubasaPointReward : {}", id);
        Optional<MGuerillaQuestTsubasaPointRewardDTO> mGuerillaQuestTsubasaPointRewardDTO = mGuerillaQuestTsubasaPointRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuerillaQuestTsubasaPointRewardDTO);
    }

    /**
     * {@code DELETE  /m-guerilla-quest-tsubasa-point-rewards/:id} : delete the "id" mGuerillaQuestTsubasaPointReward.
     *
     * @param id the id of the mGuerillaQuestTsubasaPointRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guerilla-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<Void> deleteMGuerillaQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to delete MGuerillaQuestTsubasaPointReward : {}", id);
        mGuerillaQuestTsubasaPointRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
