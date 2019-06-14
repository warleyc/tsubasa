import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMWeeklyQuestStage } from 'app/shared/model/m-weekly-quest-stage.model';

@Component({
  selector: 'jhi-m-weekly-quest-stage-detail',
  templateUrl: './m-weekly-quest-stage-detail.component.html'
})
export class MWeeklyQuestStageDetailComponent implements OnInit {
  mWeeklyQuestStage: IMWeeklyQuestStage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mWeeklyQuestStage }) => {
      this.mWeeklyQuestStage = mWeeklyQuestStage;
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
