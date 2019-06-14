import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMLoginBonusIncentive } from 'app/shared/model/m-login-bonus-incentive.model';

@Component({
  selector: 'jhi-m-login-bonus-incentive-detail',
  templateUrl: './m-login-bonus-incentive-detail.component.html'
})
export class MLoginBonusIncentiveDetailComponent implements OnInit {
  mLoginBonusIncentive: IMLoginBonusIncentive;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLoginBonusIncentive }) => {
      this.mLoginBonusIncentive = mLoginBonusIncentive;
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
