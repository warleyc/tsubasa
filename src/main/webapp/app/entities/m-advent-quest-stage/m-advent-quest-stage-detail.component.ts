import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMAdventQuestStage } from 'app/shared/model/m-advent-quest-stage.model';

@Component({
  selector: 'jhi-m-advent-quest-stage-detail',
  templateUrl: './m-advent-quest-stage-detail.component.html'
})
export class MAdventQuestStageDetailComponent implements OnInit {
  mAdventQuestStage: IMAdventQuestStage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAdventQuestStage }) => {
      this.mAdventQuestStage = mAdventQuestStage;
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
