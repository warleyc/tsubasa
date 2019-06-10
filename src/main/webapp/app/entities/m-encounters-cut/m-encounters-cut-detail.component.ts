import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMEncountersCut } from 'app/shared/model/m-encounters-cut.model';

@Component({
  selector: 'jhi-m-encounters-cut-detail',
  templateUrl: './m-encounters-cut-detail.component.html'
})
export class MEncountersCutDetailComponent implements OnInit {
  mEncountersCut: IMEncountersCut;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEncountersCut }) => {
      this.mEncountersCut = mEncountersCut;
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
