package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MSellCardMedalService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MSellCardMedalDTO;
import io.shm.tsubasa.service.dto.MSellCardMedalCriteria;
import io.shm.tsubasa.service.MSellCardMedalQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MSellCardMedal}.
 */
@RestController
@RequestMapping("/api")
public class MSellCardMedalResource {

    private final Logger log = LoggerFactory.getLogger(MSellCardMedalResource.class);

    private static final String ENTITY_NAME = "mSellCardMedal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MSellCardMedalService mSellCardMedalService;

    private final MSellCardMedalQueryService mSellCardMedalQueryService;

    public MSellCardMedalResource(MSellCardMedalService mSellCardMedalService, MSellCardMedalQueryService mSellCardMedalQueryService) {
        this.mSellCardMedalService = mSellCardMedalService;
        this.mSellCardMedalQueryService = mSellCardMedalQueryService;
    }

    /**
     * {@code POST  /m-sell-card-medals} : Create a new mSellCardMedal.
     *
     * @param mSellCardMedalDTO the mSellCardMedalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mSellCardMedalDTO, or with status {@code 400 (Bad Request)} if the mSellCardMedal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-sell-card-medals")
    public ResponseEntity<MSellCardMedalDTO> createMSellCardMedal(@Valid @RequestBody MSellCardMedalDTO mSellCardMedalDTO) throws URISyntaxException {
        log.debug("REST request to save MSellCardMedal : {}", mSellCardMedalDTO);
        if (mSellCardMedalDTO.getId() != null) {
            throw new BadRequestAlertException("A new mSellCardMedal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MSellCardMedalDTO result = mSellCardMedalService.save(mSellCardMedalDTO);
        return ResponseEntity.created(new URI("/api/m-sell-card-medals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-sell-card-medals} : Updates an existing mSellCardMedal.
     *
     * @param mSellCardMedalDTO the mSellCardMedalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mSellCardMedalDTO,
     * or with status {@code 400 (Bad Request)} if the mSellCardMedalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mSellCardMedalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-sell-card-medals")
    public ResponseEntity<MSellCardMedalDTO> updateMSellCardMedal(@Valid @RequestBody MSellCardMedalDTO mSellCardMedalDTO) throws URISyntaxException {
        log.debug("REST request to update MSellCardMedal : {}", mSellCardMedalDTO);
        if (mSellCardMedalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MSellCardMedalDTO result = mSellCardMedalService.save(mSellCardMedalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mSellCardMedalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-sell-card-medals} : get all the mSellCardMedals.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mSellCardMedals in body.
     */
    @GetMapping("/m-sell-card-medals")
    public ResponseEntity<List<MSellCardMedalDTO>> getAllMSellCardMedals(MSellCardMedalCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MSellCardMedals by criteria: {}", criteria);
        Page<MSellCardMedalDTO> page = mSellCardMedalQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-sell-card-medals/count} : count all the mSellCardMedals.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-sell-card-medals/count")
    public ResponseEntity<Long> countMSellCardMedals(MSellCardMedalCriteria criteria) {
        log.debug("REST request to count MSellCardMedals by criteria: {}", criteria);
        return ResponseEntity.ok().body(mSellCardMedalQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-sell-card-medals/:id} : get the "id" mSellCardMedal.
     *
     * @param id the id of the mSellCardMedalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mSellCardMedalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-sell-card-medals/{id}")
    public ResponseEntity<MSellCardMedalDTO> getMSellCardMedal(@PathVariable Long id) {
        log.debug("REST request to get MSellCardMedal : {}", id);
        Optional<MSellCardMedalDTO> mSellCardMedalDTO = mSellCardMedalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mSellCardMedalDTO);
    }

    /**
     * {@code DELETE  /m-sell-card-medals/:id} : delete the "id" mSellCardMedal.
     *
     * @param id the id of the mSellCardMedalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-sell-card-medals/{id}")
    public ResponseEntity<Void> deleteMSellCardMedal(@PathVariable Long id) {
        log.debug("REST request to delete MSellCardMedal : {}", id);
        mSellCardMedalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
