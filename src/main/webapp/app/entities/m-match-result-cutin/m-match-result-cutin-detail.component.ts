import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMMatchResultCutin } from 'app/shared/model/m-match-result-cutin.model';

@Component({
  selector: 'jhi-m-match-result-cutin-detail',
  templateUrl: './m-match-result-cutin-detail.component.html'
})
export class MMatchResultCutinDetailComponent implements OnInit {
  mMatchResultCutin: IMMatchResultCutin;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMatchResultCutin }) => {
      this.mMatchResultCutin = mMatchResultCutin;
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
