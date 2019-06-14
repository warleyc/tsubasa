import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMSellCardMedal } from 'app/shared/model/m-sell-card-medal.model';

@Component({
  selector: 'jhi-m-sell-card-medal-detail',
  templateUrl: './m-sell-card-medal-detail.component.html'
})
export class MSellCardMedalDetailComponent implements OnInit {
  mSellCardMedal: IMSellCardMedal;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSellCardMedal }) => {
      this.mSellCardMedal = mSellCardMedal;
    });
  }

  previousState() {
    window.history.back();
  }
}
