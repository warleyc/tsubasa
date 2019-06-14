package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MSceneTutorialMessageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MSceneTutorialMessageDTO;
import io.shm.tsubasa.service.dto.MSceneTutorialMessageCriteria;
import io.shm.tsubasa.service.MSceneTutorialMessageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MSceneTutorialMessage}.
 */
@RestController
@RequestMapping("/api")
public class MSceneTutorialMessageResource {

    private final Logger log = LoggerFactory.getLogger(MSceneTutorialMessageResource.class);

    private static final String ENTITY_NAME = "mSceneTutorialMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MSceneTutorialMessageService mSceneTutorialMessageService;

    private final MSceneTutorialMessageQueryService mSceneTutorialMessageQueryService;

    public MSceneTutorialMessageResource(MSceneTutorialMessageService mSceneTutorialMessageService, MSceneTutorialMessageQueryService mSceneTutorialMessageQueryService) {
        this.mSceneTutorialMessageService = mSceneTutorialMessageService;
        this.mSceneTutorialMessageQueryService = mSceneTutorialMessageQueryService;
    }

    /**
     * {@code POST  /m-scene-tutorial-messages} : Create a new mSceneTutorialMessage.
     *
     * @param mSceneTutorialMessageDTO the mSceneTutorialMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mSceneTutorialMessageDTO, or with status {@code 400 (Bad Request)} if the mSceneTutorialMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-scene-tutorial-messages")
    public ResponseEntity<MSceneTutorialMessageDTO> createMSceneTutorialMessage(@Valid @RequestBody MSceneTutorialMessageDTO mSceneTutorialMessageDTO) throws URISyntaxException {
        log.debug("REST request to save MSceneTutorialMessage : {}", mSceneTutorialMessageDTO);
        if (mSceneTutorialMessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mSceneTutorialMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MSceneTutorialMessageDTO result = mSceneTutorialMessageService.save(mSceneTutorialMessageDTO);
        return ResponseEntity.created(new URI("/api/m-scene-tutorial-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-scene-tutorial-messages} : Updates an existing mSceneTutorialMessage.
     *
     * @param mSceneTutorialMessageDTO the mSceneTutorialMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mSceneTutorialMessageDTO,
     * or with status {@code 400 (Bad Request)} if the mSceneTutorialMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mSceneTutorialMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-scene-tutorial-messages")
    public ResponseEntity<MSceneTutorialMessageDTO> updateMSceneTutorialMessage(@Valid @RequestBody MSceneTutorialMessageDTO mSceneTutorialMessageDTO) throws URISyntaxException {
        log.debug("REST request to update MSceneTutorialMessage : {}", mSceneTutorialMessageDTO);
        if (mSceneTutorialMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MSceneTutorialMessageDTO result = mSceneTutorialMessageService.save(mSceneTutorialMessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mSceneTutorialMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-scene-tutorial-messages} : get all the mSceneTutorialMessages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mSceneTutorialMessages in body.
     */
    @GetMapping("/m-scene-tutorial-messages")
    public ResponseEntity<List<MSceneTutorialMessageDTO>> getAllMSceneTutorialMessages(MSceneTutorialMessageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MSceneTutorialMessages by criteria: {}", criteria);
        Page<MSceneTutorialMessageDTO> page = mSceneTutorialMessageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-scene-tutorial-messages/count} : count all the mSceneTutorialMessages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-scene-tutorial-messages/count")
    public ResponseEntity<Long> countMSceneTutorialMessages(MSceneTutorialMessageCriteria criteria) {
        log.debug("REST request to count MSceneTutorialMessages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mSceneTutorialMessageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-scene-tutorial-messages/:id} : get the "id" mSceneTutorialMessage.
     *
     * @param id the id of the mSceneTutorialMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mSceneTutorialMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-scene-tutorial-messages/{id}")
    public ResponseEntity<MSceneTutorialMessageDTO> getMSceneTutorialMessage(@PathVariable Long id) {
        log.debug("REST request to get MSceneTutorialMessage : {}", id);
        Optional<MSceneTutorialMessageDTO> mSceneTutorialMessageDTO = mSceneTutorialMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mSceneTutorialMessageDTO);
    }

    /**
     * {@code DELETE  /m-scene-tutorial-messages/:id} : delete the "id" mSceneTutorialMessage.
     *
     * @param id the id of the mSceneTutorialMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-scene-tutorial-messages/{id}")
    public ResponseEntity<Void> deleteMSceneTutorialMessage(@PathVariable Long id) {
        log.debug("REST request to delete MSceneTutorialMessage : {}", id);
        mSceneTutorialMessageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
