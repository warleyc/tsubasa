import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMNationality } from 'app/shared/model/m-nationality.model';

@Component({
  selector: 'jhi-m-nationality-detail',
  templateUrl: './m-nationality-detail.component.html'
})
export class MNationalityDetailComponent implements OnInit {
  mNationality: IMNationality;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mNationality }) => {
      this.mNationality = mNationality;
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
