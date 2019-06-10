package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MAdventQuestTsubasaPointRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MAdventQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.dto.MAdventQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MAdventQuestTsubasaPointRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MAdventQuestTsubasaPointReward}.
 */
@RestController
@RequestMapping("/api")
public class MAdventQuestTsubasaPointRewardResource {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestTsubasaPointRewardResource.class);

    private static final String ENTITY_NAME = "mAdventQuestTsubasaPointReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MAdventQuestTsubasaPointRewardService mAdventQuestTsubasaPointRewardService;

    private final MAdventQuestTsubasaPointRewardQueryService mAdventQuestTsubasaPointRewardQueryService;

    public MAdventQuestTsubasaPointRewardResource(MAdventQuestTsubasaPointRewardService mAdventQuestTsubasaPointRewardService, MAdventQuestTsubasaPointRewardQueryService mAdventQuestTsubasaPointRewardQueryService) {
        this.mAdventQuestTsubasaPointRewardService = mAdventQuestTsubasaPointRewardService;
        this.mAdventQuestTsubasaPointRewardQueryService = mAdventQuestTsubasaPointRewardQueryService;
    }

    /**
     * {@code POST  /m-advent-quest-tsubasa-point-rewards} : Create a new mAdventQuestTsubasaPointReward.
     *
     * @param mAdventQuestTsubasaPointRewardDTO the mAdventQuestTsubasaPointRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mAdventQuestTsubasaPointRewardDTO, or with status {@code 400 (Bad Request)} if the mAdventQuestTsubasaPointReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-advent-quest-tsubasa-point-rewards")
    public ResponseEntity<MAdventQuestTsubasaPointRewardDTO> createMAdventQuestTsubasaPointReward(@Valid @RequestBody MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MAdventQuestTsubasaPointReward : {}", mAdventQuestTsubasaPointRewardDTO);
        if (mAdventQuestTsubasaPointRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mAdventQuestTsubasaPointReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MAdventQuestTsubasaPointRewardDTO result = mAdventQuestTsubasaPointRewardService.save(mAdventQuestTsubasaPointRewardDTO);
        return ResponseEntity.created(new URI("/api/m-advent-quest-tsubasa-point-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-advent-quest-tsubasa-point-rewards} : Updates an existing mAdventQuestTsubasaPointReward.
     *
     * @param mAdventQuestTsubasaPointRewardDTO the mAdventQuestTsubasaPointRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mAdventQuestTsubasaPointRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mAdventQuestTsubasaPointRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mAdventQuestTsubasaPointRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-advent-quest-tsubasa-point-rewards")
    public ResponseEntity<MAdventQuestTsubasaPointRewardDTO> updateMAdventQuestTsubasaPointReward(@Valid @RequestBody MAdventQuestTsubasaPointRewardDTO mAdventQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MAdventQuestTsubasaPointReward : {}", mAdventQuestTsubasaPointRewardDTO);
        if (mAdventQuestTsubasaPointRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MAdventQuestTsubasaPointRewardDTO result = mAdventQuestTsubasaPointRewardService.save(mAdventQuestTsubasaPointRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mAdventQuestTsubasaPointRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-advent-quest-tsubasa-point-rewards} : get all the mAdventQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mAdventQuestTsubasaPointRewards in body.
     */
    @GetMapping("/m-advent-quest-tsubasa-point-rewards")
    public ResponseEntity<List<MAdventQuestTsubasaPointRewardDTO>> getAllMAdventQuestTsubasaPointRewards(MAdventQuestTsubasaPointRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MAdventQuestTsubasaPointRewards by criteria: {}", criteria);
        Page<MAdventQuestTsubasaPointRewardDTO> page = mAdventQuestTsubasaPointRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-advent-quest-tsubasa-point-rewards/count} : count all the mAdventQuestTsubasaPointRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-advent-quest-tsubasa-point-rewards/count")
    public ResponseEntity<Long> countMAdventQuestTsubasaPointRewards(MAdventQuestTsubasaPointRewardCriteria criteria) {
        log.debug("REST request to count MAdventQuestTsubasaPointRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mAdventQuestTsubasaPointRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-advent-quest-tsubasa-point-rewards/:id} : get the "id" mAdventQuestTsubasaPointReward.
     *
     * @param id the id of the mAdventQuestTsubasaPointRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mAdventQuestTsubasaPointRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-advent-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<MAdventQuestTsubasaPointRewardDTO> getMAdventQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to get MAdventQuestTsubasaPointReward : {}", id);
        Optional<MAdventQuestTsubasaPointRewardDTO> mAdventQuestTsubasaPointRewardDTO = mAdventQuestTsubasaPointRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mAdventQuestTsubasaPointRewardDTO);
    }

    /**
     * {@code DELETE  /m-advent-quest-tsubasa-point-rewards/:id} : delete the "id" mAdventQuestTsubasaPointReward.
     *
     * @param id the id of the mAdventQuestTsubasaPointRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-advent-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<Void> deleteMAdventQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to delete MAdventQuestTsubasaPointReward : {}", id);
        mAdventQuestTsubasaPointRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
