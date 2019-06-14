import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMPvpGrade } from 'app/shared/model/m-pvp-grade.model';

@Component({
  selector: 'jhi-m-pvp-grade-detail',
  templateUrl: './m-pvp-grade-detail.component.html'
})
export class MPvpGradeDetailComponent implements OnInit {
  mPvpGrade: IMPvpGrade;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpGrade }) => {
      this.mPvpGrade = mPvpGrade;
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
