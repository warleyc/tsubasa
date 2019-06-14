package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTutorialMessageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTutorialMessageDTO;
import io.shm.tsubasa.service.dto.MTutorialMessageCriteria;
import io.shm.tsubasa.service.MTutorialMessageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTutorialMessage}.
 */
@RestController
@RequestMapping("/api")
public class MTutorialMessageResource {

    private final Logger log = LoggerFactory.getLogger(MTutorialMessageResource.class);

    private static final String ENTITY_NAME = "mTutorialMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTutorialMessageService mTutorialMessageService;

    private final MTutorialMessageQueryService mTutorialMessageQueryService;

    public MTutorialMessageResource(MTutorialMessageService mTutorialMessageService, MTutorialMessageQueryService mTutorialMessageQueryService) {
        this.mTutorialMessageService = mTutorialMessageService;
        this.mTutorialMessageQueryService = mTutorialMessageQueryService;
    }

    /**
     * {@code POST  /m-tutorial-messages} : Create a new mTutorialMessage.
     *
     * @param mTutorialMessageDTO the mTutorialMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTutorialMessageDTO, or with status {@code 400 (Bad Request)} if the mTutorialMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-tutorial-messages")
    public ResponseEntity<MTutorialMessageDTO> createMTutorialMessage(@Valid @RequestBody MTutorialMessageDTO mTutorialMessageDTO) throws URISyntaxException {
        log.debug("REST request to save MTutorialMessage : {}", mTutorialMessageDTO);
        if (mTutorialMessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTutorialMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTutorialMessageDTO result = mTutorialMessageService.save(mTutorialMessageDTO);
        return ResponseEntity.created(new URI("/api/m-tutorial-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-tutorial-messages} : Updates an existing mTutorialMessage.
     *
     * @param mTutorialMessageDTO the mTutorialMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTutorialMessageDTO,
     * or with status {@code 400 (Bad Request)} if the mTutorialMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTutorialMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-tutorial-messages")
    public ResponseEntity<MTutorialMessageDTO> updateMTutorialMessage(@Valid @RequestBody MTutorialMessageDTO mTutorialMessageDTO) throws URISyntaxException {
        log.debug("REST request to update MTutorialMessage : {}", mTutorialMessageDTO);
        if (mTutorialMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTutorialMessageDTO result = mTutorialMessageService.save(mTutorialMessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTutorialMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-tutorial-messages} : get all the mTutorialMessages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTutorialMessages in body.
     */
    @GetMapping("/m-tutorial-messages")
    public ResponseEntity<List<MTutorialMessageDTO>> getAllMTutorialMessages(MTutorialMessageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTutorialMessages by criteria: {}", criteria);
        Page<MTutorialMessageDTO> page = mTutorialMessageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-tutorial-messages/count} : count all the mTutorialMessages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-tutorial-messages/count")
    public ResponseEntity<Long> countMTutorialMessages(MTutorialMessageCriteria criteria) {
        log.debug("REST request to count MTutorialMessages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTutorialMessageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-tutorial-messages/:id} : get the "id" mTutorialMessage.
     *
     * @param id the id of the mTutorialMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTutorialMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-tutorial-messages/{id}")
    public ResponseEntity<MTutorialMessageDTO> getMTutorialMessage(@PathVariable Long id) {
        log.debug("REST request to get MTutorialMessage : {}", id);
        Optional<MTutorialMessageDTO> mTutorialMessageDTO = mTutorialMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTutorialMessageDTO);
    }

    /**
     * {@code DELETE  /m-tutorial-messages/:id} : delete the "id" mTutorialMessage.
     *
     * @param id the id of the mTutorialMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-tutorial-messages/{id}")
    public ResponseEntity<Void> deleteMTutorialMessage(@PathVariable Long id) {
        log.debug("REST request to delete MTutorialMessage : {}", id);
        mTutorialMessageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
