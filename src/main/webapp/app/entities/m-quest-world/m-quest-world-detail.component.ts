import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMQuestWorld } from 'app/shared/model/m-quest-world.model';

@Component({
  selector: 'jhi-m-quest-world-detail',
  templateUrl: './m-quest-world-detail.component.html'
})
export class MQuestWorldDetailComponent implements OnInit {
  mQuestWorld: IMQuestWorld;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestWorld }) => {
      this.mQuestWorld = mQuestWorld;
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
