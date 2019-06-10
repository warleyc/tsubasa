package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionBeforeShootCutInCharacterNumService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInCharacterNumDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInCharacterNumCriteria;
import io.shm.tsubasa.service.MGachaRenditionBeforeShootCutInCharacterNumQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInCharacterNum}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionBeforeShootCutInCharacterNumResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionBeforeShootCutInCharacterNumResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionBeforeShootCutInCharacterNum";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionBeforeShootCutInCharacterNumService mGachaRenditionBeforeShootCutInCharacterNumService;

    private final MGachaRenditionBeforeShootCutInCharacterNumQueryService mGachaRenditionBeforeShootCutInCharacterNumQueryService;

    public MGachaRenditionBeforeShootCutInCharacterNumResource(MGachaRenditionBeforeShootCutInCharacterNumService mGachaRenditionBeforeShootCutInCharacterNumService, MGachaRenditionBeforeShootCutInCharacterNumQueryService mGachaRenditionBeforeShootCutInCharacterNumQueryService) {
        this.mGachaRenditionBeforeShootCutInCharacterNumService = mGachaRenditionBeforeShootCutInCharacterNumService;
        this.mGachaRenditionBeforeShootCutInCharacterNumQueryService = mGachaRenditionBeforeShootCutInCharacterNumQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-before-shoot-cut-in-character-nums} : Create a new mGachaRenditionBeforeShootCutInCharacterNum.
     *
     * @param mGachaRenditionBeforeShootCutInCharacterNumDTO the mGachaRenditionBeforeShootCutInCharacterNumDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionBeforeShootCutInCharacterNumDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionBeforeShootCutInCharacterNum has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-before-shoot-cut-in-character-nums")
    public ResponseEntity<MGachaRenditionBeforeShootCutInCharacterNumDTO> createMGachaRenditionBeforeShootCutInCharacterNum(@Valid @RequestBody MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionBeforeShootCutInCharacterNum : {}", mGachaRenditionBeforeShootCutInCharacterNumDTO);
        if (mGachaRenditionBeforeShootCutInCharacterNumDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionBeforeShootCutInCharacterNum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionBeforeShootCutInCharacterNumDTO result = mGachaRenditionBeforeShootCutInCharacterNumService.save(mGachaRenditionBeforeShootCutInCharacterNumDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-before-shoot-cut-in-character-nums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-before-shoot-cut-in-character-nums} : Updates an existing mGachaRenditionBeforeShootCutInCharacterNum.
     *
     * @param mGachaRenditionBeforeShootCutInCharacterNumDTO the mGachaRenditionBeforeShootCutInCharacterNumDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionBeforeShootCutInCharacterNumDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionBeforeShootCutInCharacterNumDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionBeforeShootCutInCharacterNumDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-before-shoot-cut-in-character-nums")
    public ResponseEntity<MGachaRenditionBeforeShootCutInCharacterNumDTO> updateMGachaRenditionBeforeShootCutInCharacterNum(@Valid @RequestBody MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionBeforeShootCutInCharacterNum : {}", mGachaRenditionBeforeShootCutInCharacterNumDTO);
        if (mGachaRenditionBeforeShootCutInCharacterNumDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionBeforeShootCutInCharacterNumDTO result = mGachaRenditionBeforeShootCutInCharacterNumService.save(mGachaRenditionBeforeShootCutInCharacterNumDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionBeforeShootCutInCharacterNumDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-before-shoot-cut-in-character-nums} : get all the mGachaRenditionBeforeShootCutInCharacterNums.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionBeforeShootCutInCharacterNums in body.
     */
    @GetMapping("/m-gacha-rendition-before-shoot-cut-in-character-nums")
    public ResponseEntity<List<MGachaRenditionBeforeShootCutInCharacterNumDTO>> getAllMGachaRenditionBeforeShootCutInCharacterNums(MGachaRenditionBeforeShootCutInCharacterNumCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionBeforeShootCutInCharacterNums by criteria: {}", criteria);
        Page<MGachaRenditionBeforeShootCutInCharacterNumDTO> page = mGachaRenditionBeforeShootCutInCharacterNumQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-before-shoot-cut-in-character-nums/count} : count all the mGachaRenditionBeforeShootCutInCharacterNums.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-before-shoot-cut-in-character-nums/count")
    public ResponseEntity<Long> countMGachaRenditionBeforeShootCutInCharacterNums(MGachaRenditionBeforeShootCutInCharacterNumCriteria criteria) {
        log.debug("REST request to count MGachaRenditionBeforeShootCutInCharacterNums by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionBeforeShootCutInCharacterNumQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-before-shoot-cut-in-character-nums/:id} : get the "id" mGachaRenditionBeforeShootCutInCharacterNum.
     *
     * @param id the id of the mGachaRenditionBeforeShootCutInCharacterNumDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionBeforeShootCutInCharacterNumDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-before-shoot-cut-in-character-nums/{id}")
    public ResponseEntity<MGachaRenditionBeforeShootCutInCharacterNumDTO> getMGachaRenditionBeforeShootCutInCharacterNum(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionBeforeShootCutInCharacterNum : {}", id);
        Optional<MGachaRenditionBeforeShootCutInCharacterNumDTO> mGachaRenditionBeforeShootCutInCharacterNumDTO = mGachaRenditionBeforeShootCutInCharacterNumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionBeforeShootCutInCharacterNumDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-before-shoot-cut-in-character-nums/:id} : delete the "id" mGachaRenditionBeforeShootCutInCharacterNum.
     *
     * @param id the id of the mGachaRenditionBeforeShootCutInCharacterNumDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-before-shoot-cut-in-character-nums/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionBeforeShootCutInCharacterNum(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionBeforeShootCutInCharacterNum : {}", id);
        mGachaRenditionBeforeShootCutInCharacterNumService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
