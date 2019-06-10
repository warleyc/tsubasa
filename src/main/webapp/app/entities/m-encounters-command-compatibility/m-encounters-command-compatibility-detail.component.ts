import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMEncountersCommandCompatibility } from 'app/shared/model/m-encounters-command-compatibility.model';

@Component({
  selector: 'jhi-m-encounters-command-compatibility-detail',
  templateUrl: './m-encounters-command-compatibility-detail.component.html'
})
export class MEncountersCommandCompatibilityDetailComponent implements OnInit {
  mEncountersCommandCompatibility: IMEncountersCommandCompatibility;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEncountersCommandCompatibility }) => {
      this.mEncountersCommandCompatibility = mEncountersCommandCompatibility;
    });
  }

  previousState() {
    window.history.back();
  }
}
