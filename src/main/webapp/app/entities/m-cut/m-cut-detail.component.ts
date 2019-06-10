import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCut } from 'app/shared/model/m-cut.model';

@Component({
  selector: 'jhi-m-cut-detail',
  templateUrl: './m-cut-detail.component.html'
})
export class MCutDetailComponent implements OnInit {
  mCut: IMCut;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCut }) => {
      this.mCut = mCut;
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
