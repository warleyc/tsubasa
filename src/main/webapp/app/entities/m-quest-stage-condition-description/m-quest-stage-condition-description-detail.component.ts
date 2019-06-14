import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMQuestStageConditionDescription } from 'app/shared/model/m-quest-stage-condition-description.model';

@Component({
  selector: 'jhi-m-quest-stage-condition-description-detail',
  templateUrl: './m-quest-stage-condition-description-detail.component.html'
})
export class MQuestStageConditionDescriptionDetailComponent implements OnInit {
  mQuestStageConditionDescription: IMQuestStageConditionDescription;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestStageConditionDescription }) => {
      this.mQuestStageConditionDescription = mQuestStageConditionDescription;
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
