import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMSoundBank } from 'app/shared/model/m-sound-bank.model';

@Component({
  selector: 'jhi-m-sound-bank-detail',
  templateUrl: './m-sound-bank-detail.component.html'
})
export class MSoundBankDetailComponent implements OnInit {
  mSoundBank: IMSoundBank;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSoundBank }) => {
      this.mSoundBank = mSoundBank;
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
