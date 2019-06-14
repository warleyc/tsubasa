package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MWeeklyQuestTsubasaPointRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MWeeklyQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.dto.MWeeklyQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MWeeklyQuestTsubasaPointRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MWeeklyQuestTsubasaPointReward}.
 */
@RestController
@RequestMapping("/api")
public class MWeeklyQuestTsubasaPointRewardResource {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestTsubasaPointRewardResource.class);

    private static final String ENTITY_NAME = "mWeeklyQuestTsubasaPointReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MWeeklyQuestTsubasaPointRewardService mWeeklyQuestTsubasaPointRewardService;

    private final MWeeklyQuestTsubasaPointRewardQueryService mWeeklyQuestTsubasaPointRewardQueryService;

    public MWeeklyQuestTsubasaPointRewardResource(MWeeklyQuestTsubasaPointRewardService mWeeklyQuestTsubasaPointRewardService, MWeeklyQuestTsubasaPointRewardQueryService mWeeklyQuestTsubasaPointRewardQueryService) {
        this.mWeeklyQuestTsubasaPointRewardService = mWeeklyQuestTsubasaPointRewardService;
        this.mWeeklyQuestTsubasaPointRewardQueryService = mWeeklyQuestTsubasaPointRewardQueryService;
    }

    /**
     * {@code POST  /m-weekly-quest-tsubasa-point-rewards} : Create a new mWeeklyQuestTsubasaPointReward.
     *
     * @param mWeeklyQuestTsubasaPointRewardDTO the mWeeklyQuestTsubasaPointRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mWeeklyQuestTsubasaPointRewardDTO, or with status {@code 400 (Bad Request)} if the mWeeklyQuestTsubasaPointReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-weekly-quest-tsubasa-point-rewards")
    public ResponseEntity<MWeeklyQuestTsubasaPointRewardDTO> createMWeeklyQuestTsubasaPointReward(@Valid @RequestBody MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MWeeklyQuestTsubasaPointReward : {}", mWeeklyQuestTsubasaPointRewardDTO);
        if (mWeeklyQuestTsubasaPointRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mWeeklyQuestTsubasaPointReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MWeeklyQuestTsubasaPointRewardDTO result = mWeeklyQuestTsubasaPointRewardService.save(mWeeklyQuestTsubasaPointRewardDTO);
        return ResponseEntity.created(new URI("/api/m-weekly-quest-tsubasa-point-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-weekly-quest-tsubasa-point-rewards} : Updates an existing mWeeklyQuestTsubasaPointReward.
     *
     * @param mWeeklyQuestTsubasaPointRewardDTO the mWeeklyQuestTsubasaPointRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mWeeklyQuestTsubasaPointRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mWeeklyQuestTsubasaPointRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mWeeklyQuestTsubasaPointRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-weekly-quest-tsubasa-point-rewards")
    public ResponseEntity<MWeeklyQuestTsubasaPointRewardDTO> updateMWeeklyQuestTsubasaPointReward(@Valid @RequestBody MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MWeeklyQuestTsubasaPointReward : {}", mWeeklyQuestTsubasaPointRewardDTO);
        if (mWeeklyQuestTsubasaPointRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MWeeklyQuestTsubasaPointRewardDTO result = mWeeklyQuestTsubasaPointRewardService.save(mWeeklyQuestTsubasaPointRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mWeeklyQuestTsubasaPointRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-weekly-quest-tsubasa-point-rewards} : get all the mWeeklyQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mWeeklyQuestTsubasaPointRewards in body.
     */
    @GetMapping("/m-weekly-quest-tsubasa-point-rewards")
    public ResponseEntity<List<MWeeklyQuestTsubasaPointRewardDTO>> getAllMWeeklyQuestTsubasaPointRewards(MWeeklyQuestTsubasaPointRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MWeeklyQuestTsubasaPointRewards by criteria: {}", criteria);
        Page<MWeeklyQuestTsubasaPointRewardDTO> page = mWeeklyQuestTsubasaPointRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-weekly-quest-tsubasa-point-rewards/count} : count all the mWeeklyQuestTsubasaPointRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-weekly-quest-tsubasa-point-rewards/count")
    public ResponseEntity<Long> countMWeeklyQuestTsubasaPointRewards(MWeeklyQuestTsubasaPointRewardCriteria criteria) {
        log.debug("REST request to count MWeeklyQuestTsubasaPointRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mWeeklyQuestTsubasaPointRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-weekly-quest-tsubasa-point-rewards/:id} : get the "id" mWeeklyQuestTsubasaPointReward.
     *
     * @param id the id of the mWeeklyQuestTsubasaPointRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mWeeklyQuestTsubasaPointRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-weekly-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<MWeeklyQuestTsubasaPointRewardDTO> getMWeeklyQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to get MWeeklyQuestTsubasaPointReward : {}", id);
        Optional<MWeeklyQuestTsubasaPointRewardDTO> mWeeklyQuestTsubasaPointRewardDTO = mWeeklyQuestTsubasaPointRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mWeeklyQuestTsubasaPointRewardDTO);
    }

    /**
     * {@code DELETE  /m-weekly-quest-tsubasa-point-rewards/:id} : delete the "id" mWeeklyQuestTsubasaPointReward.
     *
     * @param id the id of the mWeeklyQuestTsubasaPointRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-weekly-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<Void> deleteMWeeklyQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to delete MWeeklyQuestTsubasaPointReward : {}", id);
        mWeeklyQuestTsubasaPointRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
