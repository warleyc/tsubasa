package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MSoundBankService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MSoundBankDTO;
import io.shm.tsubasa.service.dto.MSoundBankCriteria;
import io.shm.tsubasa.service.MSoundBankQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MSoundBank}.
 */
@RestController
@RequestMapping("/api")
public class MSoundBankResource {

    private final Logger log = LoggerFactory.getLogger(MSoundBankResource.class);

    private static final String ENTITY_NAME = "mSoundBank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MSoundBankService mSoundBankService;

    private final MSoundBankQueryService mSoundBankQueryService;

    public MSoundBankResource(MSoundBankService mSoundBankService, MSoundBankQueryService mSoundBankQueryService) {
        this.mSoundBankService = mSoundBankService;
        this.mSoundBankQueryService = mSoundBankQueryService;
    }

    /**
     * {@code POST  /m-sound-banks} : Create a new mSoundBank.
     *
     * @param mSoundBankDTO the mSoundBankDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mSoundBankDTO, or with status {@code 400 (Bad Request)} if the mSoundBank has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-sound-banks")
    public ResponseEntity<MSoundBankDTO> createMSoundBank(@Valid @RequestBody MSoundBankDTO mSoundBankDTO) throws URISyntaxException {
        log.debug("REST request to save MSoundBank : {}", mSoundBankDTO);
        if (mSoundBankDTO.getId() != null) {
            throw new BadRequestAlertException("A new mSoundBank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MSoundBankDTO result = mSoundBankService.save(mSoundBankDTO);
        return ResponseEntity.created(new URI("/api/m-sound-banks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-sound-banks} : Updates an existing mSoundBank.
     *
     * @param mSoundBankDTO the mSoundBankDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mSoundBankDTO,
     * or with status {@code 400 (Bad Request)} if the mSoundBankDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mSoundBankDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-sound-banks")
    public ResponseEntity<MSoundBankDTO> updateMSoundBank(@Valid @RequestBody MSoundBankDTO mSoundBankDTO) throws URISyntaxException {
        log.debug("REST request to update MSoundBank : {}", mSoundBankDTO);
        if (mSoundBankDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MSoundBankDTO result = mSoundBankService.save(mSoundBankDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mSoundBankDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-sound-banks} : get all the mSoundBanks.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mSoundBanks in body.
     */
    @GetMapping("/m-sound-banks")
    public ResponseEntity<List<MSoundBankDTO>> getAllMSoundBanks(MSoundBankCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MSoundBanks by criteria: {}", criteria);
        Page<MSoundBankDTO> page = mSoundBankQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-sound-banks/count} : count all the mSoundBanks.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-sound-banks/count")
    public ResponseEntity<Long> countMSoundBanks(MSoundBankCriteria criteria) {
        log.debug("REST request to count MSoundBanks by criteria: {}", criteria);
        return ResponseEntity.ok().body(mSoundBankQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-sound-banks/:id} : get the "id" mSoundBank.
     *
     * @param id the id of the mSoundBankDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mSoundBankDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-sound-banks/{id}")
    public ResponseEntity<MSoundBankDTO> getMSoundBank(@PathVariable Long id) {
        log.debug("REST request to get MSoundBank : {}", id);
        Optional<MSoundBankDTO> mSoundBankDTO = mSoundBankService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mSoundBankDTO);
    }

    /**
     * {@code DELETE  /m-sound-banks/:id} : delete the "id" mSoundBank.
     *
     * @param id the id of the mSoundBankDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-sound-banks/{id}")
    public ResponseEntity<Void> deleteMSoundBank(@PathVariable Long id) {
        log.debug("REST request to delete MSoundBank : {}", id);
        mSoundBankService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
