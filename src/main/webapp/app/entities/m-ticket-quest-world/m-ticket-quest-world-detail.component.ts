import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTicketQuestWorld } from 'app/shared/model/m-ticket-quest-world.model';

@Component({
  selector: 'jhi-m-ticket-quest-world-detail',
  templateUrl: './m-ticket-quest-world-detail.component.html'
})
export class MTicketQuestWorldDetailComponent implements OnInit {
  mTicketQuestWorld: IMTicketQuestWorld;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTicketQuestWorld }) => {
      this.mTicketQuestWorld = mTicketQuestWorld;
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
