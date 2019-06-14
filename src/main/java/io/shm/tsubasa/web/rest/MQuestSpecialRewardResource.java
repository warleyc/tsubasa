package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestSpecialRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestSpecialRewardDTO;
import io.shm.tsubasa.service.dto.MQuestSpecialRewardCriteria;
import io.shm.tsubasa.service.MQuestSpecialRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestSpecialReward}.
 */
@RestController
@RequestMapping("/api")
public class MQuestSpecialRewardResource {

    private final Logger log = LoggerFactory.getLogger(MQuestSpecialRewardResource.class);

    private static final String ENTITY_NAME = "mQuestSpecialReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestSpecialRewardService mQuestSpecialRewardService;

    private final MQuestSpecialRewardQueryService mQuestSpecialRewardQueryService;

    public MQuestSpecialRewardResource(MQuestSpecialRewardService mQuestSpecialRewardService, MQuestSpecialRewardQueryService mQuestSpecialRewardQueryService) {
        this.mQuestSpecialRewardService = mQuestSpecialRewardService;
        this.mQuestSpecialRewardQueryService = mQuestSpecialRewardQueryService;
    }

    /**
     * {@code POST  /m-quest-special-rewards} : Create a new mQuestSpecialReward.
     *
     * @param mQuestSpecialRewardDTO the mQuestSpecialRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestSpecialRewardDTO, or with status {@code 400 (Bad Request)} if the mQuestSpecialReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-special-rewards")
    public ResponseEntity<MQuestSpecialRewardDTO> createMQuestSpecialReward(@Valid @RequestBody MQuestSpecialRewardDTO mQuestSpecialRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestSpecialReward : {}", mQuestSpecialRewardDTO);
        if (mQuestSpecialRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestSpecialReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestSpecialRewardDTO result = mQuestSpecialRewardService.save(mQuestSpecialRewardDTO);
        return ResponseEntity.created(new URI("/api/m-quest-special-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-special-rewards} : Updates an existing mQuestSpecialReward.
     *
     * @param mQuestSpecialRewardDTO the mQuestSpecialRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestSpecialRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestSpecialRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestSpecialRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-special-rewards")
    public ResponseEntity<MQuestSpecialRewardDTO> updateMQuestSpecialReward(@Valid @RequestBody MQuestSpecialRewardDTO mQuestSpecialRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestSpecialReward : {}", mQuestSpecialRewardDTO);
        if (mQuestSpecialRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestSpecialRewardDTO result = mQuestSpecialRewardService.save(mQuestSpecialRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestSpecialRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-special-rewards} : get all the mQuestSpecialRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestSpecialRewards in body.
     */
    @GetMapping("/m-quest-special-rewards")
    public ResponseEntity<List<MQuestSpecialRewardDTO>> getAllMQuestSpecialRewards(MQuestSpecialRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestSpecialRewards by criteria: {}", criteria);
        Page<MQuestSpecialRewardDTO> page = mQuestSpecialRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-special-rewards/count} : count all the mQuestSpecialRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-special-rewards/count")
    public ResponseEntity<Long> countMQuestSpecialRewards(MQuestSpecialRewardCriteria criteria) {
        log.debug("REST request to count MQuestSpecialRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestSpecialRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-special-rewards/:id} : get the "id" mQuestSpecialReward.
     *
     * @param id the id of the mQuestSpecialRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestSpecialRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-special-rewards/{id}")
    public ResponseEntity<MQuestSpecialRewardDTO> getMQuestSpecialReward(@PathVariable Long id) {
        log.debug("REST request to get MQuestSpecialReward : {}", id);
        Optional<MQuestSpecialRewardDTO> mQuestSpecialRewardDTO = mQuestSpecialRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestSpecialRewardDTO);
    }

    /**
     * {@code DELETE  /m-quest-special-rewards/:id} : delete the "id" mQuestSpecialReward.
     *
     * @param id the id of the mQuestSpecialRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-special-rewards/{id}")
    public ResponseEntity<Void> deleteMQuestSpecialReward(@PathVariable Long id) {
        log.debug("REST request to delete MQuestSpecialReward : {}", id);
        mQuestSpecialRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
