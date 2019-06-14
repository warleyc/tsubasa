import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTargetTeamGroup } from 'app/shared/model/m-target-team-group.model';

@Component({
  selector: 'jhi-m-target-team-group-detail',
  templateUrl: './m-target-team-group-detail.component.html'
})
export class MTargetTeamGroupDetailComponent implements OnInit {
  mTargetTeamGroup: IMTargetTeamGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetTeamGroup }) => {
      this.mTargetTeamGroup = mTargetTeamGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
