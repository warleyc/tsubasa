import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMFormation } from 'app/shared/model/m-formation.model';

@Component({
  selector: 'jhi-m-formation-detail',
  templateUrl: './m-formation-detail.component.html'
})
export class MFormationDetailComponent implements OnInit {
  mFormation: IMFormation;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mFormation }) => {
      this.mFormation = mFormation;
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
