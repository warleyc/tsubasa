import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaTicket } from 'app/shared/model/m-gacha-ticket.model';

@Component({
  selector: 'jhi-m-gacha-ticket-detail',
  templateUrl: './m-gacha-ticket-detail.component.html'
})
export class MGachaTicketDetailComponent implements OnInit {
  mGachaTicket: IMGachaTicket;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaTicket }) => {
      this.mGachaTicket = mGachaTicket;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
