import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMMarathonQuestWorld } from 'app/shared/model/m-marathon-quest-world.model';

@Component({
  selector: 'jhi-m-marathon-quest-world-detail',
  templateUrl: './m-marathon-quest-world-detail.component.html'
})
export class MMarathonQuestWorldDetailComponent implements OnInit {
  mMarathonQuestWorld: IMMarathonQuestWorld;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonQuestWorld }) => {
      this.mMarathonQuestWorld = mMarathonQuestWorld;
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
