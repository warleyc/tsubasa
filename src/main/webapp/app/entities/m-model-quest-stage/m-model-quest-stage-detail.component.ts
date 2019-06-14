import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMModelQuestStage } from 'app/shared/model/m-model-quest-stage.model';

@Component({
  selector: 'jhi-m-model-quest-stage-detail',
  templateUrl: './m-model-quest-stage-detail.component.html'
})
export class MModelQuestStageDetailComponent implements OnInit {
  mModelQuestStage: IMModelQuestStage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelQuestStage }) => {
      this.mModelQuestStage = mModelQuestStage;
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
