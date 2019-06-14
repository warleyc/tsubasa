import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMPenaltyKickCut } from 'app/shared/model/m-penalty-kick-cut.model';

@Component({
  selector: 'jhi-m-penalty-kick-cut-detail',
  templateUrl: './m-penalty-kick-cut-detail.component.html'
})
export class MPenaltyKickCutDetailComponent implements OnInit {
  mPenaltyKickCut: IMPenaltyKickCut;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPenaltyKickCut }) => {
      this.mPenaltyKickCut = mPenaltyKickCut;
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
