import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTutorialMessage } from 'app/shared/model/m-tutorial-message.model';

@Component({
  selector: 'jhi-m-tutorial-message-detail',
  templateUrl: './m-tutorial-message-detail.component.html'
})
export class MTutorialMessageDetailComponent implements OnInit {
  mTutorialMessage: IMTutorialMessage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTutorialMessage }) => {
      this.mTutorialMessage = mTutorialMessage;
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
