import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTimeattackQuestStage } from 'app/shared/model/m-timeattack-quest-stage.model';

@Component({
  selector: 'jhi-m-timeattack-quest-stage-detail',
  templateUrl: './m-timeattack-quest-stage-detail.component.html'
})
export class MTimeattackQuestStageDetailComponent implements OnInit {
  mTimeattackQuestStage: IMTimeattackQuestStage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTimeattackQuestStage }) => {
      this.mTimeattackQuestStage = mTimeattackQuestStage;
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
