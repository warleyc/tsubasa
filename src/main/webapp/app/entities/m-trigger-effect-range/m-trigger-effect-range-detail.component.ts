import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTriggerEffectRange } from 'app/shared/model/m-trigger-effect-range.model';

@Component({
  selector: 'jhi-m-trigger-effect-range-detail',
  templateUrl: './m-trigger-effect-range-detail.component.html'
})
export class MTriggerEffectRangeDetailComponent implements OnInit {
  mTriggerEffectRange: IMTriggerEffectRange;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTriggerEffectRange }) => {
      this.mTriggerEffectRange = mTriggerEffectRange;
    });
  }

  previousState() {
    window.history.back();
  }
}
