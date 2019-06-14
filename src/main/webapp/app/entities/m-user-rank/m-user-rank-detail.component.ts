import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMUserRank } from 'app/shared/model/m-user-rank.model';

@Component({
  selector: 'jhi-m-user-rank-detail',
  templateUrl: './m-user-rank-detail.component.html'
})
export class MUserRankDetailComponent implements OnInit {
  mUserRank: IMUserRank;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mUserRank }) => {
      this.mUserRank = mUserRank;
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
