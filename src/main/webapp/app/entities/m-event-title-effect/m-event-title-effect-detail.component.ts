import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMEventTitleEffect } from 'app/shared/model/m-event-title-effect.model';

@Component({
  selector: 'jhi-m-event-title-effect-detail',
  templateUrl: './m-event-title-effect-detail.component.html'
})
export class MEventTitleEffectDetailComponent implements OnInit {
  mEventTitleEffect: IMEventTitleEffect;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEventTitleEffect }) => {
      this.mEventTitleEffect = mEventTitleEffect;
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
