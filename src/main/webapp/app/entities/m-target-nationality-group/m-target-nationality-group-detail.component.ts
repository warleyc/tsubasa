import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTargetNationalityGroup } from 'app/shared/model/m-target-nationality-group.model';

@Component({
  selector: 'jhi-m-target-nationality-group-detail',
  templateUrl: './m-target-nationality-group-detail.component.html'
})
export class MTargetNationalityGroupDetailComponent implements OnInit {
  mTargetNationalityGroup: IMTargetNationalityGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetNationalityGroup }) => {
      this.mTargetNationalityGroup = mTargetNationalityGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
