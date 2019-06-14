import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMQuestStage } from 'app/shared/model/m-quest-stage.model';

@Component({
  selector: 'jhi-m-quest-stage-detail',
  templateUrl: './m-quest-stage-detail.component.html'
})
export class MQuestStageDetailComponent implements OnInit {
  mQuestStage: IMQuestStage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestStage }) => {
      this.mQuestStage = mQuestStage;
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
