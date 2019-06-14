import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMSceneTutorialMessage } from 'app/shared/model/m-scene-tutorial-message.model';

@Component({
  selector: 'jhi-m-scene-tutorial-message-detail',
  templateUrl: './m-scene-tutorial-message-detail.component.html'
})
export class MSceneTutorialMessageDetailComponent implements OnInit {
  mSceneTutorialMessage: IMSceneTutorialMessage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSceneTutorialMessage }) => {
      this.mSceneTutorialMessage = mSceneTutorialMessage;
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
