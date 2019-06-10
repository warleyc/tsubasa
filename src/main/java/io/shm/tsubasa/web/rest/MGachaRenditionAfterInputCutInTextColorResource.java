package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionAfterInputCutInTextColorService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInTextColorDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInTextColorCriteria;
import io.shm.tsubasa.service.MGachaRenditionAfterInputCutInTextColorQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionAfterInputCutInTextColor}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionAfterInputCutInTextColorResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionAfterInputCutInTextColorResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionAfterInputCutInTextColor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionAfterInputCutInTextColorService mGachaRenditionAfterInputCutInTextColorService;

    private final MGachaRenditionAfterInputCutInTextColorQueryService mGachaRenditionAfterInputCutInTextColorQueryService;

    public MGachaRenditionAfterInputCutInTextColorResource(MGachaRenditionAfterInputCutInTextColorService mGachaRenditionAfterInputCutInTextColorService, MGachaRenditionAfterInputCutInTextColorQueryService mGachaRenditionAfterInputCutInTextColorQueryService) {
        this.mGachaRenditionAfterInputCutInTextColorService = mGachaRenditionAfterInputCutInTextColorService;
        this.mGachaRenditionAfterInputCutInTextColorQueryService = mGachaRenditionAfterInputCutInTextColorQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-after-input-cut-in-text-colors} : Create a new mGachaRenditionAfterInputCutInTextColor.
     *
     * @param mGachaRenditionAfterInputCutInTextColorDTO the mGachaRenditionAfterInputCutInTextColorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionAfterInputCutInTextColorDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionAfterInputCutInTextColor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-after-input-cut-in-text-colors")
    public ResponseEntity<MGachaRenditionAfterInputCutInTextColorDTO> createMGachaRenditionAfterInputCutInTextColor(@Valid @RequestBody MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionAfterInputCutInTextColor : {}", mGachaRenditionAfterInputCutInTextColorDTO);
        if (mGachaRenditionAfterInputCutInTextColorDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionAfterInputCutInTextColor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionAfterInputCutInTextColorDTO result = mGachaRenditionAfterInputCutInTextColorService.save(mGachaRenditionAfterInputCutInTextColorDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-after-input-cut-in-text-colors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-after-input-cut-in-text-colors} : Updates an existing mGachaRenditionAfterInputCutInTextColor.
     *
     * @param mGachaRenditionAfterInputCutInTextColorDTO the mGachaRenditionAfterInputCutInTextColorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionAfterInputCutInTextColorDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionAfterInputCutInTextColorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionAfterInputCutInTextColorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-after-input-cut-in-text-colors")
    public ResponseEntity<MGachaRenditionAfterInputCutInTextColorDTO> updateMGachaRenditionAfterInputCutInTextColor(@Valid @RequestBody MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionAfterInputCutInTextColor : {}", mGachaRenditionAfterInputCutInTextColorDTO);
        if (mGachaRenditionAfterInputCutInTextColorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionAfterInputCutInTextColorDTO result = mGachaRenditionAfterInputCutInTextColorService.save(mGachaRenditionAfterInputCutInTextColorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionAfterInputCutInTextColorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-after-input-cut-in-text-colors} : get all the mGachaRenditionAfterInputCutInTextColors.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionAfterInputCutInTextColors in body.
     */
    @GetMapping("/m-gacha-rendition-after-input-cut-in-text-colors")
    public ResponseEntity<List<MGachaRenditionAfterInputCutInTextColorDTO>> getAllMGachaRenditionAfterInputCutInTextColors(MGachaRenditionAfterInputCutInTextColorCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionAfterInputCutInTextColors by criteria: {}", criteria);
        Page<MGachaRenditionAfterInputCutInTextColorDTO> page = mGachaRenditionAfterInputCutInTextColorQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-after-input-cut-in-text-colors/count} : count all the mGachaRenditionAfterInputCutInTextColors.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-after-input-cut-in-text-colors/count")
    public ResponseEntity<Long> countMGachaRenditionAfterInputCutInTextColors(MGachaRenditionAfterInputCutInTextColorCriteria criteria) {
        log.debug("REST request to count MGachaRenditionAfterInputCutInTextColors by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionAfterInputCutInTextColorQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-after-input-cut-in-text-colors/:id} : get the "id" mGachaRenditionAfterInputCutInTextColor.
     *
     * @param id the id of the mGachaRenditionAfterInputCutInTextColorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionAfterInputCutInTextColorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-after-input-cut-in-text-colors/{id}")
    public ResponseEntity<MGachaRenditionAfterInputCutInTextColorDTO> getMGachaRenditionAfterInputCutInTextColor(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionAfterInputCutInTextColor : {}", id);
        Optional<MGachaRenditionAfterInputCutInTextColorDTO> mGachaRenditionAfterInputCutInTextColorDTO = mGachaRenditionAfterInputCutInTextColorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionAfterInputCutInTextColorDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-after-input-cut-in-text-colors/:id} : delete the "id" mGachaRenditionAfterInputCutInTextColor.
     *
     * @param id the id of the mGachaRenditionAfterInputCutInTextColorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-after-input-cut-in-text-colors/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionAfterInputCutInTextColor(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionAfterInputCutInTextColor : {}", id);
        mGachaRenditionAfterInputCutInTextColorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
