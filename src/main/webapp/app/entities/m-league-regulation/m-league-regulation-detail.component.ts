import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMLeagueRegulation } from 'app/shared/model/m-league-regulation.model';

@Component({
  selector: 'jhi-m-league-regulation-detail',
  templateUrl: './m-league-regulation-detail.component.html'
})
export class MLeagueRegulationDetailComponent implements OnInit {
  mLeagueRegulation: IMLeagueRegulation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeagueRegulation }) => {
      this.mLeagueRegulation = mLeagueRegulation;
    });
  }

  previousState() {
    window.history.back();
  }
}
