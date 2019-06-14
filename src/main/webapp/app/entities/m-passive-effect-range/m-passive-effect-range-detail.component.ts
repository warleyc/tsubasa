import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';

@Component({
  selector: 'jhi-m-passive-effect-range-detail',
  templateUrl: './m-passive-effect-range-detail.component.html'
})
export class MPassiveEffectRangeDetailComponent implements OnInit {
  mPassiveEffectRange: IMPassiveEffectRange;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPassiveEffectRange }) => {
      this.mPassiveEffectRange = mPassiveEffectRange;
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
