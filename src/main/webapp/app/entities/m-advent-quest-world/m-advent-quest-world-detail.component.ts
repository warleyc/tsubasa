import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMAdventQuestWorld } from 'app/shared/model/m-advent-quest-world.model';

@Component({
  selector: 'jhi-m-advent-quest-world-detail',
  templateUrl: './m-advent-quest-world-detail.component.html'
})
export class MAdventQuestWorldDetailComponent implements OnInit {
  mAdventQuestWorld: IMAdventQuestWorld;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAdventQuestWorld }) => {
      this.mAdventQuestWorld = mAdventQuestWorld;
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
