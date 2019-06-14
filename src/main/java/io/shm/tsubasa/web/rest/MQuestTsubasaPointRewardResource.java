package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestTsubasaPointRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.dto.MQuestTsubasaPointRewardCriteria;
import io.shm.tsubasa.service.MQuestTsubasaPointRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestTsubasaPointReward}.
 */
@RestController
@RequestMapping("/api")
public class MQuestTsubasaPointRewardResource {

    private final Logger log = LoggerFactory.getLogger(MQuestTsubasaPointRewardResource.class);

    private static final String ENTITY_NAME = "mQuestTsubasaPointReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestTsubasaPointRewardService mQuestTsubasaPointRewardService;

    private final MQuestTsubasaPointRewardQueryService mQuestTsubasaPointRewardQueryService;

    public MQuestTsubasaPointRewardResource(MQuestTsubasaPointRewardService mQuestTsubasaPointRewardService, MQuestTsubasaPointRewardQueryService mQuestTsubasaPointRewardQueryService) {
        this.mQuestTsubasaPointRewardService = mQuestTsubasaPointRewardService;
        this.mQuestTsubasaPointRewardQueryService = mQuestTsubasaPointRewardQueryService;
    }

    /**
     * {@code POST  /m-quest-tsubasa-point-rewards} : Create a new mQuestTsubasaPointReward.
     *
     * @param mQuestTsubasaPointRewardDTO the mQuestTsubasaPointRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestTsubasaPointRewardDTO, or with status {@code 400 (Bad Request)} if the mQuestTsubasaPointReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-tsubasa-point-rewards")
    public ResponseEntity<MQuestTsubasaPointRewardDTO> createMQuestTsubasaPointReward(@Valid @RequestBody MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestTsubasaPointReward : {}", mQuestTsubasaPointRewardDTO);
        if (mQuestTsubasaPointRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestTsubasaPointReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestTsubasaPointRewardDTO result = mQuestTsubasaPointRewardService.save(mQuestTsubasaPointRewardDTO);
        return ResponseEntity.created(new URI("/api/m-quest-tsubasa-point-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-tsubasa-point-rewards} : Updates an existing mQuestTsubasaPointReward.
     *
     * @param mQuestTsubasaPointRewardDTO the mQuestTsubasaPointRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestTsubasaPointRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestTsubasaPointRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestTsubasaPointRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-tsubasa-point-rewards")
    public ResponseEntity<MQuestTsubasaPointRewardDTO> updateMQuestTsubasaPointReward(@Valid @RequestBody MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestTsubasaPointReward : {}", mQuestTsubasaPointRewardDTO);
        if (mQuestTsubasaPointRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestTsubasaPointRewardDTO result = mQuestTsubasaPointRewardService.save(mQuestTsubasaPointRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestTsubasaPointRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-tsubasa-point-rewards} : get all the mQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestTsubasaPointRewards in body.
     */
    @GetMapping("/m-quest-tsubasa-point-rewards")
    public ResponseEntity<List<MQuestTsubasaPointRewardDTO>> getAllMQuestTsubasaPointRewards(MQuestTsubasaPointRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestTsubasaPointRewards by criteria: {}", criteria);
        Page<MQuestTsubasaPointRewardDTO> page = mQuestTsubasaPointRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-tsubasa-point-rewards/count} : count all the mQuestTsubasaPointRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-tsubasa-point-rewards/count")
    public ResponseEntity<Long> countMQuestTsubasaPointRewards(MQuestTsubasaPointRewardCriteria criteria) {
        log.debug("REST request to count MQuestTsubasaPointRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestTsubasaPointRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-tsubasa-point-rewards/:id} : get the "id" mQuestTsubasaPointReward.
     *
     * @param id the id of the mQuestTsubasaPointRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestTsubasaPointRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<MQuestTsubasaPointRewardDTO> getMQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to get MQuestTsubasaPointReward : {}", id);
        Optional<MQuestTsubasaPointRewardDTO> mQuestTsubasaPointRewardDTO = mQuestTsubasaPointRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestTsubasaPointRewardDTO);
    }

    /**
     * {@code DELETE  /m-quest-tsubasa-point-rewards/:id} : delete the "id" mQuestTsubasaPointReward.
     *
     * @param id the id of the mQuestTsubasaPointRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-tsubasa-point-rewards/{id}")
    public ResponseEntity<Void> deleteMQuestTsubasaPointReward(@PathVariable Long id) {
        log.debug("REST request to delete MQuestTsubasaPointReward : {}", id);
        mQuestTsubasaPointRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
