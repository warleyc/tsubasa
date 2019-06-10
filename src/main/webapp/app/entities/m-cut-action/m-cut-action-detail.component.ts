import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCutAction } from 'app/shared/model/m-cut-action.model';

@Component({
  selector: 'jhi-m-cut-action-detail',
  templateUrl: './m-cut-action-detail.component.html'
})
export class MCutActionDetailComponent implements OnInit {
  mCutAction: IMCutAction;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCutAction }) => {
      this.mCutAction = mCutAction;
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
