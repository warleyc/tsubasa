package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestClearRewardWeightService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestClearRewardWeightDTO;
import io.shm.tsubasa.service.dto.MQuestClearRewardWeightCriteria;
import io.shm.tsubasa.service.MQuestClearRewardWeightQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestClearRewardWeight}.
 */
@RestController
@RequestMapping("/api")
public class MQuestClearRewardWeightResource {

    private final Logger log = LoggerFactory.getLogger(MQuestClearRewardWeightResource.class);

    private static final String ENTITY_NAME = "mQuestClearRewardWeight";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestClearRewardWeightService mQuestClearRewardWeightService;

    private final MQuestClearRewardWeightQueryService mQuestClearRewardWeightQueryService;

    public MQuestClearRewardWeightResource(MQuestClearRewardWeightService mQuestClearRewardWeightService, MQuestClearRewardWeightQueryService mQuestClearRewardWeightQueryService) {
        this.mQuestClearRewardWeightService = mQuestClearRewardWeightService;
        this.mQuestClearRewardWeightQueryService = mQuestClearRewardWeightQueryService;
    }

    /**
     * {@code POST  /m-quest-clear-reward-weights} : Create a new mQuestClearRewardWeight.
     *
     * @param mQuestClearRewardWeightDTO the mQuestClearRewardWeightDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestClearRewardWeightDTO, or with status {@code 400 (Bad Request)} if the mQuestClearRewardWeight has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-clear-reward-weights")
    public ResponseEntity<MQuestClearRewardWeightDTO> createMQuestClearRewardWeight(@Valid @RequestBody MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestClearRewardWeight : {}", mQuestClearRewardWeightDTO);
        if (mQuestClearRewardWeightDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestClearRewardWeight cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestClearRewardWeightDTO result = mQuestClearRewardWeightService.save(mQuestClearRewardWeightDTO);
        return ResponseEntity.created(new URI("/api/m-quest-clear-reward-weights/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-clear-reward-weights} : Updates an existing mQuestClearRewardWeight.
     *
     * @param mQuestClearRewardWeightDTO the mQuestClearRewardWeightDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestClearRewardWeightDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestClearRewardWeightDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestClearRewardWeightDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-clear-reward-weights")
    public ResponseEntity<MQuestClearRewardWeightDTO> updateMQuestClearRewardWeight(@Valid @RequestBody MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestClearRewardWeight : {}", mQuestClearRewardWeightDTO);
        if (mQuestClearRewardWeightDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestClearRewardWeightDTO result = mQuestClearRewardWeightService.save(mQuestClearRewardWeightDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestClearRewardWeightDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-clear-reward-weights} : get all the mQuestClearRewardWeights.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestClearRewardWeights in body.
     */
    @GetMapping("/m-quest-clear-reward-weights")
    public ResponseEntity<List<MQuestClearRewardWeightDTO>> getAllMQuestClearRewardWeights(MQuestClearRewardWeightCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestClearRewardWeights by criteria: {}", criteria);
        Page<MQuestClearRewardWeightDTO> page = mQuestClearRewardWeightQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-clear-reward-weights/count} : count all the mQuestClearRewardWeights.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-clear-reward-weights/count")
    public ResponseEntity<Long> countMQuestClearRewardWeights(MQuestClearRewardWeightCriteria criteria) {
        log.debug("REST request to count MQuestClearRewardWeights by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestClearRewardWeightQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-clear-reward-weights/:id} : get the "id" mQuestClearRewardWeight.
     *
     * @param id the id of the mQuestClearRewardWeightDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestClearRewardWeightDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-clear-reward-weights/{id}")
    public ResponseEntity<MQuestClearRewardWeightDTO> getMQuestClearRewardWeight(@PathVariable Long id) {
        log.debug("REST request to get MQuestClearRewardWeight : {}", id);
        Optional<MQuestClearRewardWeightDTO> mQuestClearRewardWeightDTO = mQuestClearRewardWeightService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestClearRewardWeightDTO);
    }

    /**
     * {@code DELETE  /m-quest-clear-reward-weights/:id} : delete the "id" mQuestClearRewardWeight.
     *
     * @param id the id of the mQuestClearRewardWeightDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-clear-reward-weights/{id}")
    public ResponseEntity<Void> deleteMQuestClearRewardWeight(@PathVariable Long id) {
        log.debug("REST request to delete MQuestClearRewardWeight : {}", id);
        mQuestClearRewardWeightService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
