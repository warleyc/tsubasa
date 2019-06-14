import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTriggerEffectBase } from 'app/shared/model/m-trigger-effect-base.model';

@Component({
  selector: 'jhi-m-trigger-effect-base-detail',
  templateUrl: './m-trigger-effect-base-detail.component.html'
})
export class MTriggerEffectBaseDetailComponent implements OnInit {
  mTriggerEffectBase: IMTriggerEffectBase;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTriggerEffectBase }) => {
      this.mTriggerEffectBase = mTriggerEffectBase;
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
