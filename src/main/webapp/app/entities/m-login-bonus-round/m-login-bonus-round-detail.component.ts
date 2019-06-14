import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMLoginBonusRound } from 'app/shared/model/m-login-bonus-round.model';

@Component({
  selector: 'jhi-m-login-bonus-round-detail',
  templateUrl: './m-login-bonus-round-detail.component.html'
})
export class MLoginBonusRoundDetailComponent implements OnInit {
  mLoginBonusRound: IMLoginBonusRound;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLoginBonusRound }) => {
      this.mLoginBonusRound = mLoginBonusRound;
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
