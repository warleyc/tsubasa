import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMLeagueEffect } from 'app/shared/model/m-league-effect.model';

@Component({
  selector: 'jhi-m-league-effect-detail',
  templateUrl: './m-league-effect-detail.component.html'
})
export class MLeagueEffectDetailComponent implements OnInit {
  mLeagueEffect: IMLeagueEffect;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeagueEffect }) => {
      this.mLeagueEffect = mLeagueEffect;
    });
  }

  previousState() {
    window.history.back();
  }
}
