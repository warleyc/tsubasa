import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMRegulationMatchTutorialMessage } from 'app/shared/model/m-regulation-match-tutorial-message.model';

@Component({
  selector: 'jhi-m-regulation-match-tutorial-message-detail',
  templateUrl: './m-regulation-match-tutorial-message-detail.component.html'
})
export class MRegulationMatchTutorialMessageDetailComponent implements OnInit {
  mRegulationMatchTutorialMessage: IMRegulationMatchTutorialMessage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRegulationMatchTutorialMessage }) => {
      this.mRegulationMatchTutorialMessage = mRegulationMatchTutorialMessage;
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
