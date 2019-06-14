import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTargetTriggerEffectGroup } from 'app/shared/model/m-target-trigger-effect-group.model';

@Component({
  selector: 'jhi-m-target-trigger-effect-group-detail',
  templateUrl: './m-target-trigger-effect-group-detail.component.html'
})
export class MTargetTriggerEffectGroupDetailComponent implements OnInit {
  mTargetTriggerEffectGroup: IMTargetTriggerEffectGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetTriggerEffectGroup }) => {
      this.mTargetTriggerEffectGroup = mTargetTriggerEffectGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
