import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMLeague } from 'app/shared/model/m-league.model';

@Component({
  selector: 'jhi-m-league-detail',
  templateUrl: './m-league-detail.component.html'
})
export class MLeagueDetailComponent implements OnInit {
  mLeague: IMLeague;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeague }) => {
      this.mLeague = mLeague;
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
