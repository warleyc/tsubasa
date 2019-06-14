import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGuildMission } from 'app/shared/model/m-guild-mission.model';

@Component({
  selector: 'jhi-m-guild-mission-detail',
  templateUrl: './m-guild-mission-detail.component.html'
})
export class MGuildMissionDetailComponent implements OnInit {
  mGuildMission: IMGuildMission;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildMission }) => {
      this.mGuildMission = mGuildMission;
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
