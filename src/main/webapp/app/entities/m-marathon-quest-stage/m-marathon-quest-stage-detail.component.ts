import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMMarathonQuestStage } from 'app/shared/model/m-marathon-quest-stage.model';

@Component({
  selector: 'jhi-m-marathon-quest-stage-detail',
  templateUrl: './m-marathon-quest-stage-detail.component.html'
})
export class MMarathonQuestStageDetailComponent implements OnInit {
  mMarathonQuestStage: IMMarathonQuestStage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonQuestStage }) => {
      this.mMarathonQuestStage = mMarathonQuestStage;
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
