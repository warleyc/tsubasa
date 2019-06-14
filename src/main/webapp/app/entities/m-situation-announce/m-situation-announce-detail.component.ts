import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMSituationAnnounce } from 'app/shared/model/m-situation-announce.model';

@Component({
  selector: 'jhi-m-situation-announce-detail',
  templateUrl: './m-situation-announce-detail.component.html'
})
export class MSituationAnnounceDetailComponent implements OnInit {
  mSituationAnnounce: IMSituationAnnounce;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSituationAnnounce }) => {
      this.mSituationAnnounce = mSituationAnnounce;
    });
  }

  previousState() {
    window.history.back();
  }
}
