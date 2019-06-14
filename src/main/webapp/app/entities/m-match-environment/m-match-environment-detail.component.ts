import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMMatchEnvironment } from 'app/shared/model/m-match-environment.model';

@Component({
  selector: 'jhi-m-match-environment-detail',
  templateUrl: './m-match-environment-detail.component.html'
})
export class MMatchEnvironmentDetailComponent implements OnInit {
  mMatchEnvironment: IMMatchEnvironment;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMatchEnvironment }) => {
      this.mMatchEnvironment = mMatchEnvironment;
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
