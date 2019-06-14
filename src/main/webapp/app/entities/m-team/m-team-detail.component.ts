import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMTeam } from 'app/shared/model/m-team.model';

@Component({
  selector: 'jhi-m-team-detail',
  templateUrl: './m-team-detail.component.html'
})
export class MTeamDetailComponent implements OnInit {
  mTeam: IMTeam;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTeam }) => {
      this.mTeam = mTeam;
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
