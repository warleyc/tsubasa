import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMSoundBankEvent } from 'app/shared/model/m-sound-bank-event.model';

@Component({
  selector: 'jhi-m-sound-bank-event-detail',
  templateUrl: './m-sound-bank-event-detail.component.html'
})
export class MSoundBankEventDetailComponent implements OnInit {
  mSoundBankEvent: IMSoundBankEvent;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSoundBankEvent }) => {
      this.mSoundBankEvent = mSoundBankEvent;
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
