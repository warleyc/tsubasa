import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMChallengeQuestWorld } from 'app/shared/model/m-challenge-quest-world.model';

@Component({
  selector: 'jhi-m-challenge-quest-world-detail',
  templateUrl: './m-challenge-quest-world-detail.component.html'
})
export class MChallengeQuestWorldDetailComponent implements OnInit {
  mChallengeQuestWorld: IMChallengeQuestWorld;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChallengeQuestWorld }) => {
      this.mChallengeQuestWorld = mChallengeQuestWorld;
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
