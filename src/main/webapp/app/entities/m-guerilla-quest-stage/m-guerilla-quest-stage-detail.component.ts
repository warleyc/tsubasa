import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGuerillaQuestStage } from 'app/shared/model/m-guerilla-quest-stage.model';

@Component({
  selector: 'jhi-m-guerilla-quest-stage-detail',
  templateUrl: './m-guerilla-quest-stage-detail.component.html'
})
export class MGuerillaQuestStageDetailComponent implements OnInit {
  mGuerillaQuestStage: IMGuerillaQuestStage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuerillaQuestStage }) => {
      this.mGuerillaQuestStage = mGuerillaQuestStage;
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
