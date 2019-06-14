import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTargetActionTypeGroup } from 'app/shared/model/m-target-action-type-group.model';

@Component({
  selector: 'jhi-m-target-action-type-group-detail',
  templateUrl: './m-target-action-type-group-detail.component.html'
})
export class MTargetActionTypeGroupDetailComponent implements OnInit {
  mTargetActionTypeGroup: IMTargetActionTypeGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetActionTypeGroup }) => {
      this.mTargetActionTypeGroup = mTargetActionTypeGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
