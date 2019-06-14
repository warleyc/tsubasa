import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMMasterVersion } from 'app/shared/model/m-master-version.model';

@Component({
  selector: 'jhi-m-master-version-detail',
  templateUrl: './m-master-version-detail.component.html'
})
export class MMasterVersionDetailComponent implements OnInit {
  mMasterVersion: IMMasterVersion;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMasterVersion }) => {
      this.mMasterVersion = mMasterVersion;
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
