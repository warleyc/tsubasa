import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMCombinationCutPosition } from 'app/shared/model/m-combination-cut-position.model';

@Component({
  selector: 'jhi-m-combination-cut-position-detail',
  templateUrl: './m-combination-cut-position-detail.component.html'
})
export class MCombinationCutPositionDetailComponent implements OnInit {
  mCombinationCutPosition: IMCombinationCutPosition;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCombinationCutPosition }) => {
      this.mCombinationCutPosition = mCombinationCutPosition;
    });
  }

  previousState() {
    window.history.back();
  }
}
