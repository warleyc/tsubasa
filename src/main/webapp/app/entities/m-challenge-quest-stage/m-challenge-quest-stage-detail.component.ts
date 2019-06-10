import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMChallengeQuestStage } from 'app/shared/model/m-challenge-quest-stage.model';

@Component({
  selector: 'jhi-m-challenge-quest-stage-detail',
  templateUrl: './m-challenge-quest-stage-detail.component.html'
})
export class MChallengeQuestStageDetailComponent implements OnInit {
  mChallengeQuestStage: IMChallengeQuestStage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChallengeQuestStage }) => {
      this.mChallengeQuestStage = mChallengeQuestStage;
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
