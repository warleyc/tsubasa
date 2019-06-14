import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTargetRarityGroup } from 'app/shared/model/m-target-rarity-group.model';

@Component({
  selector: 'jhi-m-target-rarity-group-detail',
  templateUrl: './m-target-rarity-group-detail.component.html'
})
export class MTargetRarityGroupDetailComponent implements OnInit {
  mTargetRarityGroup: IMTargetRarityGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetRarityGroup }) => {
      this.mTargetRarityGroup = mTargetRarityGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
