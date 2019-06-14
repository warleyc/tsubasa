import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTargetActionGroup } from 'app/shared/model/m-target-action-group.model';

@Component({
  selector: 'jhi-m-target-action-group-detail',
  templateUrl: './m-target-action-group-detail.component.html'
})
export class MTargetActionGroupDetailComponent implements OnInit {
  mTargetActionGroup: IMTargetActionGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetActionGroup }) => {
      this.mTargetActionGroup = mTargetActionGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
