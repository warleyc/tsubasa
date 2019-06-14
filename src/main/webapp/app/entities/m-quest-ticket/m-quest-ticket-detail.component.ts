import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMQuestTicket } from 'app/shared/model/m-quest-ticket.model';

@Component({
  selector: 'jhi-m-quest-ticket-detail',
  templateUrl: './m-quest-ticket-detail.component.html'
})
export class MQuestTicketDetailComponent implements OnInit {
  mQuestTicket: IMQuestTicket;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestTicket }) => {
      this.mQuestTicket = mQuestTicket;
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
