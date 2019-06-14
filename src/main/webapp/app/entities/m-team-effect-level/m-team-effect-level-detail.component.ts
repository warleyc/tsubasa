import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTeamEffectLevel } from 'app/shared/model/m-team-effect-level.model';

@Component({
  selector: 'jhi-m-team-effect-level-detail',
  templateUrl: './m-team-effect-level-detail.component.html'
})
export class MTeamEffectLevelDetailComponent implements OnInit {
  mTeamEffectLevel: IMTeamEffectLevel;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTeamEffectLevel }) => {
      this.mTeamEffectLevel = mTeamEffectLevel;
    });
  }

  previousState() {
    window.history.back();
  }
}
