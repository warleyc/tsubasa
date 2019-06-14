import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTicketQuestStage } from 'app/shared/model/m-ticket-quest-stage.model';

@Component({
  selector: 'jhi-m-ticket-quest-stage-detail',
  templateUrl: './m-ticket-quest-stage-detail.component.html'
})
export class MTicketQuestStageDetailComponent implements OnInit {
  mTicketQuestStage: IMTicketQuestStage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTicketQuestStage }) => {
      this.mTicketQuestStage = mTicketQuestStage;
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
