import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGuerillaQuestWorld } from 'app/shared/model/m-guerilla-quest-world.model';

@Component({
  selector: 'jhi-m-guerilla-quest-world-detail',
  templateUrl: './m-guerilla-quest-world-detail.component.html'
})
export class MGuerillaQuestWorldDetailComponent implements OnInit {
  mGuerillaQuestWorld: IMGuerillaQuestWorld;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuerillaQuestWorld }) => {
      this.mGuerillaQuestWorld = mGuerillaQuestWorld;
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
