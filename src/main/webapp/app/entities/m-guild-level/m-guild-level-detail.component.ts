import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGuildLevel } from 'app/shared/model/m-guild-level.model';

@Component({
  selector: 'jhi-m-guild-level-detail',
  templateUrl: './m-guild-level-detail.component.html'
})
export class MGuildLevelDetailComponent implements OnInit {
  mGuildLevel: IMGuildLevel;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildLevel }) => {
      this.mGuildLevel = mGuildLevel;
    });
  }

  previousState() {
    window.history.back();
  }
}
