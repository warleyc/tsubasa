package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTrainingCardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTrainingCardDTO;
import io.shm.tsubasa.service.dto.MTrainingCardCriteria;
import io.shm.tsubasa.service.MTrainingCardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTrainingCard}.
 */
@RestController
@RequestMapping("/api")
public class MTrainingCardResource {

    private final Logger log = LoggerFactory.getLogger(MTrainingCardResource.class);

    private static final String ENTITY_NAME = "mTrainingCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTrainingCardService mTrainingCardService;

    private final MTrainingCardQueryService mTrainingCardQueryService;

    public MTrainingCardResource(MTrainingCardService mTrainingCardService, MTrainingCardQueryService mTrainingCardQueryService) {
        this.mTrainingCardService = mTrainingCardService;
        this.mTrainingCardQueryService = mTrainingCardQueryService;
    }

    /**
     * {@code POST  /m-training-cards} : Create a new mTrainingCard.
     *
     * @param mTrainingCardDTO the mTrainingCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTrainingCardDTO, or with status {@code 400 (Bad Request)} if the mTrainingCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-training-cards")
    public ResponseEntity<MTrainingCardDTO> createMTrainingCard(@Valid @RequestBody MTrainingCardDTO mTrainingCardDTO) throws URISyntaxException {
        log.debug("REST request to save MTrainingCard : {}", mTrainingCardDTO);
        if (mTrainingCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTrainingCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTrainingCardDTO result = mTrainingCardService.save(mTrainingCardDTO);
        return ResponseEntity.created(new URI("/api/m-training-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-training-cards} : Updates an existing mTrainingCard.
     *
     * @param mTrainingCardDTO the mTrainingCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTrainingCardDTO,
     * or with status {@code 400 (Bad Request)} if the mTrainingCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTrainingCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-training-cards")
    public ResponseEntity<MTrainingCardDTO> updateMTrainingCard(@Valid @RequestBody MTrainingCardDTO mTrainingCardDTO) throws URISyntaxException {
        log.debug("REST request to update MTrainingCard : {}", mTrainingCardDTO);
        if (mTrainingCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTrainingCardDTO result = mTrainingCardService.save(mTrainingCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTrainingCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-training-cards} : get all the mTrainingCards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTrainingCards in body.
     */
    @GetMapping("/m-training-cards")
    public ResponseEntity<List<MTrainingCardDTO>> getAllMTrainingCards(MTrainingCardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTrainingCards by criteria: {}", criteria);
        Page<MTrainingCardDTO> page = mTrainingCardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-training-cards/count} : count all the mTrainingCards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-training-cards/count")
    public ResponseEntity<Long> countMTrainingCards(MTrainingCardCriteria criteria) {
        log.debug("REST request to count MTrainingCards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTrainingCardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-training-cards/:id} : get the "id" mTrainingCard.
     *
     * @param id the id of the mTrainingCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTrainingCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-training-cards/{id}")
    public ResponseEntity<MTrainingCardDTO> getMTrainingCard(@PathVariable Long id) {
        log.debug("REST request to get MTrainingCard : {}", id);
        Optional<MTrainingCardDTO> mTrainingCardDTO = mTrainingCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTrainingCardDTO);
    }

    /**
     * {@code DELETE  /m-training-cards/:id} : delete the "id" mTrainingCard.
     *
     * @param id the id of the mTrainingCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-training-cards/{id}")
    public ResponseEntity<Void> deleteMTrainingCard(@PathVariable Long id) {
        log.debug("REST request to delete MTrainingCard : {}", id);
        mTrainingCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
