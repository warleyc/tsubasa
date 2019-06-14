import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTargetCharacterGroup } from 'app/shared/model/m-target-character-group.model';

@Component({
  selector: 'jhi-m-target-character-group-detail',
  templateUrl: './m-target-character-group-detail.component.html'
})
export class MTargetCharacterGroupDetailComponent implements OnInit {
  mTargetCharacterGroup: IMTargetCharacterGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetCharacterGroup }) => {
      this.mTargetCharacterGroup = mTargetCharacterGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
