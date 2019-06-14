import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMKeeperCutAction } from 'app/shared/model/m-keeper-cut-action.model';

@Component({
  selector: 'jhi-m-keeper-cut-action-detail',
  templateUrl: './m-keeper-cut-action-detail.component.html'
})
export class MKeeperCutActionDetailComponent implements OnInit {
  mKeeperCutAction: IMKeeperCutAction;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mKeeperCutAction }) => {
      this.mKeeperCutAction = mKeeperCutAction;
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
