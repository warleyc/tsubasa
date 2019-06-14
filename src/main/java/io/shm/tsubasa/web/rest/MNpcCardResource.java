package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MNpcCardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MNpcCardDTO;
import io.shm.tsubasa.service.dto.MNpcCardCriteria;
import io.shm.tsubasa.service.MNpcCardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MNpcCard}.
 */
@RestController
@RequestMapping("/api")
public class MNpcCardResource {

    private final Logger log = LoggerFactory.getLogger(MNpcCardResource.class);

    private static final String ENTITY_NAME = "mNpcCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MNpcCardService mNpcCardService;

    private final MNpcCardQueryService mNpcCardQueryService;

    public MNpcCardResource(MNpcCardService mNpcCardService, MNpcCardQueryService mNpcCardQueryService) {
        this.mNpcCardService = mNpcCardService;
        this.mNpcCardQueryService = mNpcCardQueryService;
    }

    /**
     * {@code POST  /m-npc-cards} : Create a new mNpcCard.
     *
     * @param mNpcCardDTO the mNpcCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mNpcCardDTO, or with status {@code 400 (Bad Request)} if the mNpcCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-npc-cards")
    public ResponseEntity<MNpcCardDTO> createMNpcCard(@Valid @RequestBody MNpcCardDTO mNpcCardDTO) throws URISyntaxException {
        log.debug("REST request to save MNpcCard : {}", mNpcCardDTO);
        if (mNpcCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mNpcCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MNpcCardDTO result = mNpcCardService.save(mNpcCardDTO);
        return ResponseEntity.created(new URI("/api/m-npc-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-npc-cards} : Updates an existing mNpcCard.
     *
     * @param mNpcCardDTO the mNpcCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mNpcCardDTO,
     * or with status {@code 400 (Bad Request)} if the mNpcCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mNpcCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-npc-cards")
    public ResponseEntity<MNpcCardDTO> updateMNpcCard(@Valid @RequestBody MNpcCardDTO mNpcCardDTO) throws URISyntaxException {
        log.debug("REST request to update MNpcCard : {}", mNpcCardDTO);
        if (mNpcCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MNpcCardDTO result = mNpcCardService.save(mNpcCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mNpcCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-npc-cards} : get all the mNpcCards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mNpcCards in body.
     */
    @GetMapping("/m-npc-cards")
    public ResponseEntity<List<MNpcCardDTO>> getAllMNpcCards(MNpcCardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MNpcCards by criteria: {}", criteria);
        Page<MNpcCardDTO> page = mNpcCardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-npc-cards/count} : count all the mNpcCards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-npc-cards/count")
    public ResponseEntity<Long> countMNpcCards(MNpcCardCriteria criteria) {
        log.debug("REST request to count MNpcCards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mNpcCardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-npc-cards/:id} : get the "id" mNpcCard.
     *
     * @param id the id of the mNpcCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mNpcCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-npc-cards/{id}")
    public ResponseEntity<MNpcCardDTO> getMNpcCard(@PathVariable Long id) {
        log.debug("REST request to get MNpcCard : {}", id);
        Optional<MNpcCardDTO> mNpcCardDTO = mNpcCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mNpcCardDTO);
    }

    /**
     * {@code DELETE  /m-npc-cards/:id} : delete the "id" mNpcCard.
     *
     * @param id the id of the mNpcCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-npc-cards/{id}")
    public ResponseEntity<Void> deleteMNpcCard(@PathVariable Long id) {
        log.debug("REST request to delete MNpcCard : {}", id);
        mNpcCardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
