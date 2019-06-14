import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMRivalEncountCutin } from 'app/shared/model/m-rival-encount-cutin.model';

@Component({
  selector: 'jhi-m-rival-encount-cutin-detail',
  templateUrl: './m-rival-encount-cutin-detail.component.html'
})
export class MRivalEncountCutinDetailComponent implements OnInit {
  mRivalEncountCutin: IMRivalEncountCutin;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRivalEncountCutin }) => {
      this.mRivalEncountCutin = mRivalEncountCutin;
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
