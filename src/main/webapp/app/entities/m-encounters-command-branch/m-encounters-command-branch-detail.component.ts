import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMEncountersCommandBranch } from 'app/shared/model/m-encounters-command-branch.model';

@Component({
  selector: 'jhi-m-encounters-command-branch-detail',
  templateUrl: './m-encounters-command-branch-detail.component.html'
})
export class MEncountersCommandBranchDetailComponent implements OnInit {
  mEncountersCommandBranch: IMEncountersCommandBranch;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEncountersCommandBranch }) => {
      this.mEncountersCommandBranch = mEncountersCommandBranch;
    });
  }

  previousState() {
    window.history.back();
  }
}
