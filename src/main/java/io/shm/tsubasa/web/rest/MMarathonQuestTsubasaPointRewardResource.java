package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonQuestTsubasaPointRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.dto.MMarathonQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MMarathonQuestTsubasaPointRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonQuestTsubasaPointReward}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonQuestTsubasaPointRewardResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestTsubasaPointRewardResource.class);

    private static final String ENTITY_NAME = "mMarathonQuestTsubasaPointReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonQuestTsubasaPointRewardService mMarathonQuestTsubasaPointRewardService;

    private final MMarathonQuestTsubasaPointRewardQueryService mMarathonQuestTsubasaPointRewardQueryService;

    public MMarathonQuestTsubasaPointRewardResource(MMarathonQuestTsubasaPointRewardService mMarathonQuestTsubasaPointRewardService, MMarathonQuestTsubasaPointRewardQueryService mMarathonQuestTsubasaPointRewardQueryService) {
        this.mMarathonQuestTsubasaPointRewardService = mMarathonQuestTsubasaPointRewardService;
        this.mMarathonQuestTsubasaPointRewardQueryService = mMarathonQuestTsubasaPointRewardQueryService;
    }

    /**
     * {@code POST  /m-marathon-quest-tsubasa-point-rewards} : Create a new mMarathonQuestTsubasaPointReward.
     *
     * @param mMarathonQuestTsubasaPointRewardDTO the mMarathonQuestTsubasaPointRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonQuestTsubasaPointRewardDTO, or with status {@code 400 (Bad Request)} if the mMarathonQuestTsubasaPointReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-quest-tsubasa-point-rewards")
    public ResponseEntity<MMarathonQuestTsubasaPointRewardDTO> createMMarathonQuestTsubasaPointReward(@Valid @RequestBody MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonQuestTsubasaPointReward : {}", mMarathonQuestTsubasaPointRewardDTO);
        if (mMarathonQuestTsubasaPointRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonQuestTsubasaPointReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonQuestTsubasaPointRewardDTO result = mMarathonQuestTsubasaPointRewardService.save(mMarathonQuestTsubasaPointRewardDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-quest-tsubasa-point-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-quest-tsubasa-point-rewards} : Updates an existing mMarathonQuestTsubasaPointReward.
     *
     * @param mMarathonQuestTsubasaPointRewardDTO the mMarathonQuestTsubasaPointRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonQuestTsubasaPointRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonQuestTsubasaPointRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonQuestTsubasaPointRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-quest-tsubasa-point-rewards")
    public ResponseEntity<MMarathonQuestTsubasaPointRewardDTO> updateMMarathonQuestTsubasaPointReward(@Valid @RequestBody MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonQuestTsubasaPointReward : {}", mMarathonQuestTsubasaPointRewardDTO);
        if (mMarathonQuestTsubasaPointRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonQuestTsubasaPointRewardDTO result = mMarathonQuestTsubasaPointRewardService.save(mMarathonQuestTsubasaPointRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonQuestTsubasaPointRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-quest-tsubasa-point-rewards} : get all the mMarathonQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonQuestTsubasaPointRewards in body.
     */
    @GetMapping("/m-marathon-quest-tsubasa-point-rewards")
    public ResponseEntity<List<MMarathonQuestTsubasaPointRewardDTO>> getAllMMarathonQuestTsubasaPointRewards(MMarathonQuestTsubasaPointRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonQuestTsubasaPointRewards by criteria: {}", criteria);
        Page<MMarathonQuestTsubasaPointRewardDTO> page = mMarathonQuestTsubasaPointRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-quest-tsubasa-point-rewards/count} : count all the mMarathonQuestTsubasaPointRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-quest-tsubasa-point-rewards/count")
    public ResponseEntity<Long> countMMarathonQuestTsubasaPointRewards(MMarathonQuestTsubasaPointRewardCriteria criteria) {
        log.debug("REST request to count MMarathonQuestTsubasaPointRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonQuestTsubasaPointRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-quest-tsubasa-point-rewards/:id} : get the "id" mMarathonQuestTsubasaPointReward.
     *
     * @param id the id of the mMarathonQuestTsubasaPointRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonQuestTsubasaPointRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<MMarathonQuestTsubasaPointRewardDTO> getMMarathonQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to get MMarathonQuestTsubasaPointReward : {}", id);
        Optional<MMarathonQuestTsubasaPointRewardDTO> mMarathonQuestTsubasaPointRewardDTO = mMarathonQuestTsubasaPointRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonQuestTsubasaPointRewardDTO);
    }

    /**
     * {@code DELETE  /m-marathon-quest-tsubasa-point-rewards/:id} : delete the "id" mMarathonQuestTsubasaPointReward.
     *
     * @param id the id of the mMarathonQuestTsubasaPointRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<Void> deleteMMarathonQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonQuestTsubasaPointReward : {}", id);
        mMarathonQuestTsubasaPointRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
