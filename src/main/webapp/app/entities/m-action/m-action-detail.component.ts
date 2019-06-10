import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMAction } from 'app/shared/model/m-action.model';

@Component({
  selector: 'jhi-m-action-detail',
  templateUrl: './m-action-detail.component.html'
})
export class MActionDetailComponent implements OnInit {
  mAction: IMAction;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAction }) => {
      this.mAction = mAction;
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
