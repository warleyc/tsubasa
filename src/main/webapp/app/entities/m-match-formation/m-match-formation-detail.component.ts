import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMatchFormation } from 'app/shared/model/m-match-formation.model';

@Component({
  selector: 'jhi-m-match-formation-detail',
  templateUrl: './m-match-formation-detail.component.html'
})
export class MMatchFormationDetailComponent implements OnInit {
  mMatchFormation: IMMatchFormation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMatchFormation }) => {
      this.mMatchFormation = mMatchFormation;
    });
  }

  previousState() {
    window.history.back();
  }
}
