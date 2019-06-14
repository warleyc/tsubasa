package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestDropRateCampaignContentService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestDropRateCampaignContentDTO;
import io.shm.tsubasa.service.dto.MQuestDropRateCampaignContentCriteria;
import io.shm.tsubasa.service.MQuestDropRateCampaignContentQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestDropRateCampaignContent}.
 */
@RestController
@RequestMapping("/api")
public class MQuestDropRateCampaignContentResource {

    private final Logger log = LoggerFactory.getLogger(MQuestDropRateCampaignContentResource.class);

    private static final String ENTITY_NAME = "mQuestDropRateCampaignContent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestDropRateCampaignContentService mQuestDropRateCampaignContentService;

    private final MQuestDropRateCampaignContentQueryService mQuestDropRateCampaignContentQueryService;

    public MQuestDropRateCampaignContentResource(MQuestDropRateCampaignContentService mQuestDropRateCampaignContentService, MQuestDropRateCampaignContentQueryService mQuestDropRateCampaignContentQueryService) {
        this.mQuestDropRateCampaignContentService = mQuestDropRateCampaignContentService;
        this.mQuestDropRateCampaignContentQueryService = mQuestDropRateCampaignContentQueryService;
    }

    /**
     * {@code POST  /m-quest-drop-rate-campaign-contents} : Create a new mQuestDropRateCampaignContent.
     *
     * @param mQuestDropRateCampaignContentDTO the mQuestDropRateCampaignContentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestDropRateCampaignContentDTO, or with status {@code 400 (Bad Request)} if the mQuestDropRateCampaignContent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-drop-rate-campaign-contents")
    public ResponseEntity<MQuestDropRateCampaignContentDTO> createMQuestDropRateCampaignContent(@Valid @RequestBody MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestDropRateCampaignContent : {}", mQuestDropRateCampaignContentDTO);
        if (mQuestDropRateCampaignContentDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestDropRateCampaignContent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestDropRateCampaignContentDTO result = mQuestDropRateCampaignContentService.save(mQuestDropRateCampaignContentDTO);
        return ResponseEntity.created(new URI("/api/m-quest-drop-rate-campaign-contents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-drop-rate-campaign-contents} : Updates an existing mQuestDropRateCampaignContent.
     *
     * @param mQuestDropRateCampaignContentDTO the mQuestDropRateCampaignContentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestDropRateCampaignContentDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestDropRateCampaignContentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestDropRateCampaignContentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-drop-rate-campaign-contents")
    public ResponseEntity<MQuestDropRateCampaignContentDTO> updateMQuestDropRateCampaignContent(@Valid @RequestBody MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestDropRateCampaignContent : {}", mQuestDropRateCampaignContentDTO);
        if (mQuestDropRateCampaignContentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestDropRateCampaignContentDTO result = mQuestDropRateCampaignContentService.save(mQuestDropRateCampaignContentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestDropRateCampaignContentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-drop-rate-campaign-contents} : get all the mQuestDropRateCampaignContents.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestDropRateCampaignContents in body.
     */
    @GetMapping("/m-quest-drop-rate-campaign-contents")
    public ResponseEntity<List<MQuestDropRateCampaignContentDTO>> getAllMQuestDropRateCampaignContents(MQuestDropRateCampaignContentCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestDropRateCampaignContents by criteria: {}", criteria);
        Page<MQuestDropRateCampaignContentDTO> page = mQuestDropRateCampaignContentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-drop-rate-campaign-contents/count} : count all the mQuestDropRateCampaignContents.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-drop-rate-campaign-contents/count")
    public ResponseEntity<Long> countMQuestDropRateCampaignContents(MQuestDropRateCampaignContentCriteria criteria) {
        log.debug("REST request to count MQuestDropRateCampaignContents by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestDropRateCampaignContentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-drop-rate-campaign-contents/:id} : get the "id" mQuestDropRateCampaignContent.
     *
     * @param id the id of the mQuestDropRateCampaignContentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestDropRateCampaignContentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-drop-rate-campaign-contents/{id}")
    public ResponseEntity<MQuestDropRateCampaignContentDTO> getMQuestDropRateCampaignContent(@PathVariable Long id) {
        log.debug("REST request to get MQuestDropRateCampaignContent : {}", id);
        Optional<MQuestDropRateCampaignContentDTO> mQuestDropRateCampaignContentDTO = mQuestDropRateCampaignContentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestDropRateCampaignContentDTO);
    }

    /**
     * {@code DELETE  /m-quest-drop-rate-campaign-contents/:id} : delete the "id" mQuestDropRateCampaignContent.
     *
     * @param id the id of the mQuestDropRateCampaignContentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-drop-rate-campaign-contents/{id}")
    public ResponseEntity<Void> deleteMQuestDropRateCampaignContent(@PathVariable Long id) {
        log.debug("REST request to delete MQuestDropRateCampaignContent : {}", id);
        mQuestDropRateCampaignContentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
