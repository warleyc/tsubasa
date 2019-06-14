import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTimeattackQuestWorld } from 'app/shared/model/m-timeattack-quest-world.model';

@Component({
  selector: 'jhi-m-timeattack-quest-world-detail',
  templateUrl: './m-timeattack-quest-world-detail.component.html'
})
export class MTimeattackQuestWorldDetailComponent implements OnInit {
  mTimeattackQuestWorld: IMTimeattackQuestWorld;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTimeattackQuestWorld }) => {
      this.mTimeattackQuestWorld = mTimeattackQuestWorld;
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
