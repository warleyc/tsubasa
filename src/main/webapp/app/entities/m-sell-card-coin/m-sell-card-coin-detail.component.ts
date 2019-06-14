import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMSellCardCoin } from 'app/shared/model/m-sell-card-coin.model';

@Component({
  selector: 'jhi-m-sell-card-coin-detail',
  templateUrl: './m-sell-card-coin-detail.component.html'
})
export class MSellCardCoinDetailComponent implements OnInit {
  mSellCardCoin: IMSellCardCoin;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSellCardCoin }) => {
      this.mSellCardCoin = mSellCardCoin;
    });
  }

  previousState() {
    window.history.back();
  }
}
