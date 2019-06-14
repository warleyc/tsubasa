import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTargetFormationGroup } from 'app/shared/model/m-target-formation-group.model';

@Component({
  selector: 'jhi-m-target-formation-group-detail',
  templateUrl: './m-target-formation-group-detail.component.html'
})
export class MTargetFormationGroupDetailComponent implements OnInit {
  mTargetFormationGroup: IMTargetFormationGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetFormationGroup }) => {
      this.mTargetFormationGroup = mTargetFormationGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
