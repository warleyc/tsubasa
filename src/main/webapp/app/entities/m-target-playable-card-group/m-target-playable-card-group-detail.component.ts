import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTargetPlayableCardGroup } from 'app/shared/model/m-target-playable-card-group.model';

@Component({
  selector: 'jhi-m-target-playable-card-group-detail',
  templateUrl: './m-target-playable-card-group-detail.component.html'
})
export class MTargetPlayableCardGroupDetailComponent implements OnInit {
  mTargetPlayableCardGroup: IMTargetPlayableCardGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetPlayableCardGroup }) => {
      this.mTargetPlayableCardGroup = mTargetPlayableCardGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
