import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMMission } from 'app/shared/model/m-mission.model';

@Component({
  selector: 'jhi-m-mission-detail',
  templateUrl: './m-mission-detail.component.html'
})
export class MMissionDetailComponent implements OnInit {
  mMission: IMMission;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMission }) => {
      this.mMission = mMission;
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
