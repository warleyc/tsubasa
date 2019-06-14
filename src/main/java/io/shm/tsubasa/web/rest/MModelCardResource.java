package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MModelCardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MModelCardDTO;
import io.shm.tsubasa.service.dto.MModelCardCriteria;
import io.shm.tsubasa.service.MModelCardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MModelCard}.
 */
@RestController
@RequestMapping("/api")
public class MModelCardResource {

    private final Logger log = LoggerFactory.getLogger(MModelCardResource.class);

    private static final String ENTITY_NAME = "mModelCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MModelCardService mModelCardService;

    private final MModelCardQueryService mModelCardQueryService;

    public MModelCardResource(MModelCardService mModelCardService, MModelCardQueryService mModelCardQueryService) {
        this.mModelCardService = mModelCardService;
        this.mModelCardQueryService = mModelCardQueryService;
    }

    /**
     * {@code POST  /m-model-cards} : Create a new mModelCard.
     *
     * @param mModelCardDTO the mModelCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mModelCardDTO, or with status {@code 400 (Bad Request)} if the mModelCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-model-cards")
    public ResponseEntity<MModelCardDTO> createMModelCard(@Valid @RequestBody MModelCardDTO mModelCardDTO) throws URISyntaxException {
        log.debug("REST request to save MModelCard : {}", mModelCardDTO);
        if (mModelCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mModelCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MModelCardDTO result = mModelCardService.save(mModelCardDTO);
        return ResponseEntity.created(new URI("/api/m-model-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-model-cards} : Updates an existing mModelCard.
     *
     * @param mModelCardDTO the mModelCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mModelCardDTO,
     * or with status {@code 400 (Bad Request)} if the mModelCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mModelCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-model-cards")
    public ResponseEntity<MModelCardDTO> updateMModelCard(@Valid @RequestBody MModelCardDTO mModelCardDTO) throws URISyntaxException {
        log.debug("REST request to update MModelCard : {}", mModelCardDTO);
        if (mModelCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MModelCardDTO result = mModelCardService.save(mModelCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mModelCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-model-cards} : get all the mModelCards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mModelCards in body.
     */
    @GetMapping("/m-model-cards")
    public ResponseEntity<List<MModelCardDTO>> getAllMModelCards(MModelCardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MModelCards by criteria: {}", criteria);
        Page<MModelCardDTO> page = mModelCardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-model-cards/count} : count all the mModelCards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-model-cards/count")
    public ResponseEntity<Long> countMModelCards(MModelCardCriteria criteria) {
        log.debug("REST request to count MModelCards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mModelCardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-model-cards/:id} : get the "id" mModelCard.
     *
     * @param id the id of the mModelCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mModelCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-model-cards/{id}")
    public ResponseEntity<MModelCardDTO> getMModelCard(@PathVariable Long id) {
        log.debug("REST request to get MModelCard : {}", id);
        Optional<MModelCardDTO> mModelCardDTO = mModelCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mModelCardDTO);
    }

    /**
     * {@code DELETE  /m-model-cards/:id} : delete the "id" mModelCard.
     *
     * @param id the id of the mModelCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-model-cards/{id}")
    public ResponseEntity<Void> deleteMModelCard(@PathVariable Long id) {
        log.debug("REST request to delete MModelCard : {}", id);
        mModelCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
