import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMEncountersBonus } from 'app/shared/model/m-encounters-bonus.model';

@Component({
  selector: 'jhi-m-encounters-bonus-detail',
  templateUrl: './m-encounters-bonus-detail.component.html'
})
export class MEncountersBonusDetailComponent implements OnInit {
  mEncountersBonus: IMEncountersBonus;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEncountersBonus }) => {
      this.mEncountersBonus = mEncountersBonus;
    });
  }

  previousState() {
    window.history.back();
  }
}
