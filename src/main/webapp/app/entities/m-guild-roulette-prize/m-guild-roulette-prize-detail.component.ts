import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGuildRoulettePrize } from 'app/shared/model/m-guild-roulette-prize.model';

@Component({
  selector: 'jhi-m-guild-roulette-prize-detail',
  templateUrl: './m-guild-roulette-prize-detail.component.html'
})
export class MGuildRoulettePrizeDetailComponent implements OnInit {
  mGuildRoulettePrize: IMGuildRoulettePrize;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildRoulettePrize }) => {
      this.mGuildRoulettePrize = mGuildRoulettePrize;
    });
  }

  previousState() {
    window.history.back();
  }
}
