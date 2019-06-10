import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMBadge } from 'app/shared/model/m-badge.model';

@Component({
  selector: 'jhi-m-badge-detail',
  templateUrl: './m-badge-detail.component.html'
})
export class MBadgeDetailComponent implements OnInit {
  mBadge: IMBadge;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mBadge }) => {
      this.mBadge = mBadge;
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
