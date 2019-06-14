import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMPvpGradeRequirement } from 'app/shared/model/m-pvp-grade-requirement.model';

@Component({
  selector: 'jhi-m-pvp-grade-requirement-detail',
  templateUrl: './m-pvp-grade-requirement-detail.component.html'
})
export class MPvpGradeRequirementDetailComponent implements OnInit {
  mPvpGradeRequirement: IMPvpGradeRequirement;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpGradeRequirement }) => {
      this.mPvpGradeRequirement = mPvpGradeRequirement;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
