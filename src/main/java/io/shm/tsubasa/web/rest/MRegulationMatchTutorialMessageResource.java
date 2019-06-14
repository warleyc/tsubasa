package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MRegulationMatchTutorialMessageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MRegulationMatchTutorialMessageDTO;
import io.shm.tsubasa.service.dto.MRegulationMatchTutorialMessageCriteria;
import io.shm.tsubasa.service.MRegulationMatchTutorialMessageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MRegulationMatchTutorialMessage}.
 */
@RestController
@RequestMapping("/api")
public class MRegulationMatchTutorialMessageResource {

    private final Logger log = LoggerFactory.getLogger(MRegulationMatchTutorialMessageResource.class);

    private static final String ENTITY_NAME = "mRegulationMatchTutorialMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MRegulationMatchTutorialMessageService mRegulationMatchTutorialMessageService;

    private final MRegulationMatchTutorialMessageQueryService mRegulationMatchTutorialMessageQueryService;

    public MRegulationMatchTutorialMessageResource(MRegulationMatchTutorialMessageService mRegulationMatchTutorialMessageService, MRegulationMatchTutorialMessageQueryService mRegulationMatchTutorialMessageQueryService) {
        this.mRegulationMatchTutorialMessageService = mRegulationMatchTutorialMessageService;
        this.mRegulationMatchTutorialMessageQueryService = mRegulationMatchTutorialMessageQueryService;
    }

    /**
     * {@code POST  /m-regulation-match-tutorial-messages} : Create a new mRegulationMatchTutorialMessage.
     *
     * @param mRegulationMatchTutorialMessageDTO the mRegulationMatchTutorialMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mRegulationMatchTutorialMessageDTO, or with status {@code 400 (Bad Request)} if the mRegulationMatchTutorialMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-regulation-match-tutorial-messages")
    public ResponseEntity<MRegulationMatchTutorialMessageDTO> createMRegulationMatchTutorialMessage(@Valid @RequestBody MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO) throws URISyntaxException {
        log.debug("REST request to save MRegulationMatchTutorialMessage : {}", mRegulationMatchTutorialMessageDTO);
        if (mRegulationMatchTutorialMessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mRegulationMatchTutorialMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MRegulationMatchTutorialMessageDTO result = mRegulationMatchTutorialMessageService.save(mRegulationMatchTutorialMessageDTO);
        return ResponseEntity.created(new URI("/api/m-regulation-match-tutorial-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-regulation-match-tutorial-messages} : Updates an existing mRegulationMatchTutorialMessage.
     *
     * @param mRegulationMatchTutorialMessageDTO the mRegulationMatchTutorialMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mRegulationMatchTutorialMessageDTO,
     * or with status {@code 400 (Bad Request)} if the mRegulationMatchTutorialMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mRegulationMatchTutorialMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-regulation-match-tutorial-messages")
    public ResponseEntity<MRegulationMatchTutorialMessageDTO> updateMRegulationMatchTutorialMessage(@Valid @RequestBody MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO) throws URISyntaxException {
        log.debug("REST request to update MRegulationMatchTutorialMessage : {}", mRegulationMatchTutorialMessageDTO);
        if (mRegulationMatchTutorialMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MRegulationMatchTutorialMessageDTO result = mRegulationMatchTutorialMessageService.save(mRegulationMatchTutorialMessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mRegulationMatchTutorialMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-regulation-match-tutorial-messages} : get all the mRegulationMatchTutorialMessages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mRegulationMatchTutorialMessages in body.
     */
    @GetMapping("/m-regulation-match-tutorial-messages")
    public ResponseEntity<List<MRegulationMatchTutorialMessageDTO>> getAllMRegulationMatchTutorialMessages(MRegulationMatchTutorialMessageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MRegulationMatchTutorialMessages by criteria: {}", criteria);
        Page<MRegulationMatchTutorialMessageDTO> page = mRegulationMatchTutorialMessageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-regulation-match-tutorial-messages/count} : count all the mRegulationMatchTutorialMessages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-regulation-match-tutorial-messages/count")
    public ResponseEntity<Long> countMRegulationMatchTutorialMessages(MRegulationMatchTutorialMessageCriteria criteria) {
        log.debug("REST request to count MRegulationMatchTutorialMessages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mRegulationMatchTutorialMessageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-regulation-match-tutorial-messages/:id} : get the "id" mRegulationMatchTutorialMessage.
     *
     * @param id the id of the mRegulationMatchTutorialMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mRegulationMatchTutorialMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-regulation-match-tutorial-messages/{id}")
    public ResponseEntity<MRegulationMatchTutorialMessageDTO> getMRegulationMatchTutorialMessage(@PathVariable Long id) {
        log.debug("REST request to get MRegulationMatchTutorialMessage : {}", id);
        Optional<MRegulationMatchTutorialMessageDTO> mRegulationMatchTutorialMessageDTO = mRegulationMatchTutorialMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mRegulationMatchTutorialMessageDTO);
    }

    /**
     * {@code DELETE  /m-regulation-match-tutorial-messages/:id} : delete the "id" mRegulationMatchTutorialMessage.
     *
     * @param id the id of the mRegulationMatchTutorialMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-regulation-match-tutorial-messages/{id}")
    public ResponseEntity<Void> deleteMRegulationMatchTutorialMessage(@PathVariable Long id) {
        log.debug("REST request to delete MRegulationMatchTutorialMessage : {}", id);
        mRegulationMatchTutorialMessageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
