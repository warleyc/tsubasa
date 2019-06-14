import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMLuckWeeklyQuestWorld } from 'app/shared/model/m-luck-weekly-quest-world.model';

@Component({
  selector: 'jhi-m-luck-weekly-quest-world-detail',
  templateUrl: './m-luck-weekly-quest-world-detail.component.html'
})
export class MLuckWeeklyQuestWorldDetailComponent implements OnInit {
  mLuckWeeklyQuestWorld: IMLuckWeeklyQuestWorld;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLuckWeeklyQuestWorld }) => {
      this.mLuckWeeklyQuestWorld = mLuckWeeklyQuestWorld;
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
