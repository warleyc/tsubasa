import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMLuckWeeklyQuestStage } from 'app/shared/model/m-luck-weekly-quest-stage.model';

@Component({
  selector: 'jhi-m-luck-weekly-quest-stage-detail',
  templateUrl: './m-luck-weekly-quest-stage-detail.component.html'
})
export class MLuckWeeklyQuestStageDetailComponent implements OnInit {
  mLuckWeeklyQuestStage: IMLuckWeeklyQuestStage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLuckWeeklyQuestStage }) => {
      this.mLuckWeeklyQuestStage = mLuckWeeklyQuestStage;
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
