package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaTicketService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaTicketDTO;
import io.shm.tsubasa.service.dto.MGachaTicketCriteria;
import io.shm.tsubasa.service.MGachaTicketQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaTicket}.
 */
@RestController
@RequestMapping("/api")
public class MGachaTicketResource {

    private final Logger log = LoggerFactory.getLogger(MGachaTicketResource.class);

    private static final String ENTITY_NAME = "mGachaTicket";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaTicketService mGachaTicketService;

    private final MGachaTicketQueryService mGachaTicketQueryService;

    public MGachaTicketResource(MGachaTicketService mGachaTicketService, MGachaTicketQueryService mGachaTicketQueryService) {
        this.mGachaTicketService = mGachaTicketService;
        this.mGachaTicketQueryService = mGachaTicketQueryService;
    }

    /**
     * {@code POST  /m-gacha-tickets} : Create a new mGachaTicket.
     *
     * @param mGachaTicketDTO the mGachaTicketDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaTicketDTO, or with status {@code 400 (Bad Request)} if the mGachaTicket has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-tickets")
    public ResponseEntity<MGachaTicketDTO> createMGachaTicket(@Valid @RequestBody MGachaTicketDTO mGachaTicketDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaTicket : {}", mGachaTicketDTO);
        if (mGachaTicketDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaTicket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaTicketDTO result = mGachaTicketService.save(mGachaTicketDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-tickets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-tickets} : Updates an existing mGachaTicket.
     *
     * @param mGachaTicketDTO the mGachaTicketDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaTicketDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaTicketDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaTicketDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-tickets")
    public ResponseEntity<MGachaTicketDTO> updateMGachaTicket(@Valid @RequestBody MGachaTicketDTO mGachaTicketDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaTicket : {}", mGachaTicketDTO);
        if (mGachaTicketDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaTicketDTO result = mGachaTicketService.save(mGachaTicketDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaTicketDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-tickets} : get all the mGachaTickets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaTickets in body.
     */
    @GetMapping("/m-gacha-tickets")
    public ResponseEntity<List<MGachaTicketDTO>> getAllMGachaTickets(MGachaTicketCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaTickets by criteria: {}", criteria);
        Page<MGachaTicketDTO> page = mGachaTicketQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-tickets/count} : count all the mGachaTickets.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-tickets/count")
    public ResponseEntity<Long> countMGachaTickets(MGachaTicketCriteria criteria) {
        log.debug("REST request to count MGachaTickets by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaTicketQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-tickets/:id} : get the "id" mGachaTicket.
     *
     * @param id the id of the mGachaTicketDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaTicketDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-tickets/{id}")
    public ResponseEntity<MGachaTicketDTO> getMGachaTicket(@PathVariable Long id) {
        log.debug("REST request to get MGachaTicket : {}", id);
        Optional<MGachaTicketDTO> mGachaTicketDTO = mGachaTicketService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaTicketDTO);
    }

    /**
     * {@code DELETE  /m-gacha-tickets/:id} : delete the "id" mGachaTicket.
     *
     * @param id the id of the mGachaTicketDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-tickets/{id}")
    public ResponseEntity<Void> deleteMGachaTicket(@PathVariable Long id) {
        log.debug("REST request to delete MGachaTicket : {}", id);
        mGachaTicketService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
