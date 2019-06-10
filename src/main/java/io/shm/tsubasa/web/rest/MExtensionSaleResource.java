package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MExtensionSaleService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MExtensionSaleDTO;
import io.shm.tsubasa.service.dto.MExtensionSaleCriteria;
import io.shm.tsubasa.service.MExtensionSaleQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MExtensionSale}.
 */
@RestController
@RequestMapping("/api")
public class MExtensionSaleResource {

    private final Logger log = LoggerFactory.getLogger(MExtensionSaleResource.class);

    private static final String ENTITY_NAME = "mExtensionSale";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MExtensionSaleService mExtensionSaleService;

    private final MExtensionSaleQueryService mExtensionSaleQueryService;

    public MExtensionSaleResource(MExtensionSaleService mExtensionSaleService, MExtensionSaleQueryService mExtensionSaleQueryService) {
        this.mExtensionSaleService = mExtensionSaleService;
        this.mExtensionSaleQueryService = mExtensionSaleQueryService;
    }

    /**
     * {@code POST  /m-extension-sales} : Create a new mExtensionSale.
     *
     * @param mExtensionSaleDTO the mExtensionSaleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mExtensionSaleDTO, or with status {@code 400 (Bad Request)} if the mExtensionSale has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-extension-sales")
    public ResponseEntity<MExtensionSaleDTO> createMExtensionSale(@Valid @RequestBody MExtensionSaleDTO mExtensionSaleDTO) throws URISyntaxException {
        log.debug("REST request to save MExtensionSale : {}", mExtensionSaleDTO);
        if (mExtensionSaleDTO.getId() != null) {
            throw new BadRequestAlertException("A new mExtensionSale cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MExtensionSaleDTO result = mExtensionSaleService.save(mExtensionSaleDTO);
        return ResponseEntity.created(new URI("/api/m-extension-sales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-extension-sales} : Updates an existing mExtensionSale.
     *
     * @param mExtensionSaleDTO the mExtensionSaleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mExtensionSaleDTO,
     * or with status {@code 400 (Bad Request)} if the mExtensionSaleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mExtensionSaleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-extension-sales")
    public ResponseEntity<MExtensionSaleDTO> updateMExtensionSale(@Valid @RequestBody MExtensionSaleDTO mExtensionSaleDTO) throws URISyntaxException {
        log.debug("REST request to update MExtensionSale : {}", mExtensionSaleDTO);
        if (mExtensionSaleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MExtensionSaleDTO result = mExtensionSaleService.save(mExtensionSaleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mExtensionSaleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-extension-sales} : get all the mExtensionSales.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mExtensionSales in body.
     */
    @GetMapping("/m-extension-sales")
    public ResponseEntity<List<MExtensionSaleDTO>> getAllMExtensionSales(MExtensionSaleCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MExtensionSales by criteria: {}", criteria);
        Page<MExtensionSaleDTO> page = mExtensionSaleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-extension-sales/count} : count all the mExtensionSales.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-extension-sales/count")
    public ResponseEntity<Long> countMExtensionSales(MExtensionSaleCriteria criteria) {
        log.debug("REST request to count MExtensionSales by criteria: {}", criteria);
        return ResponseEntity.ok().body(mExtensionSaleQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-extension-sales/:id} : get the "id" mExtensionSale.
     *
     * @param id the id of the mExtensionSaleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mExtensionSaleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-extension-sales/{id}")
    public ResponseEntity<MExtensionSaleDTO> getMExtensionSale(@PathVariable Long id) {
        log.debug("REST request to get MExtensionSale : {}", id);
        Optional<MExtensionSaleDTO> mExtensionSaleDTO = mExtensionSaleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mExtensionSaleDTO);
    }

    /**
     * {@code DELETE  /m-extension-sales/:id} : delete the "id" mExtensionSale.
     *
     * @param id the id of the mExtensionSaleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-extension-sales/{id}")
    public ResponseEntity<Void> deleteMExtensionSale(@PathVariable Long id) {
        log.debug("REST request to delete MExtensionSale : {}", id);
        mExtensionSaleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
