import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMWeeklyQuestWorld } from 'app/shared/model/m-weekly-quest-world.model';

@Component({
  selector: 'jhi-m-weekly-quest-world-detail',
  templateUrl: './m-weekly-quest-world-detail.component.html'
})
export class MWeeklyQuestWorldDetailComponent implements OnInit {
  mWeeklyQuestWorld: IMWeeklyQuestWorld;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mWeeklyQuestWorld }) => {
      this.mWeeklyQuestWorld = mWeeklyQuestWorld;
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
