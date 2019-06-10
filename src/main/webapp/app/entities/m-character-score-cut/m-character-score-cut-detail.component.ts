import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMCharacterScoreCut } from 'app/shared/model/m-character-score-cut.model';

@Component({
  selector: 'jhi-m-character-score-cut-detail',
  templateUrl: './m-character-score-cut-detail.component.html'
})
export class MCharacterScoreCutDetailComponent implements OnInit {
  mCharacterScoreCut: IMCharacterScoreCut;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCharacterScoreCut }) => {
      this.mCharacterScoreCut = mCharacterScoreCut;
    });
  }

  previousState() {
    window.history.back();
  }
}
