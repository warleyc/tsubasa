import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMLoginBonusSerif } from 'app/shared/model/m-login-bonus-serif.model';

@Component({
  selector: 'jhi-m-login-bonus-serif-detail',
  templateUrl: './m-login-bonus-serif-detail.component.html'
})
export class MLoginBonusSerifDetailComponent implements OnInit {
  mLoginBonusSerif: IMLoginBonusSerif;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLoginBonusSerif }) => {
      this.mLoginBonusSerif = mLoginBonusSerif;
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
