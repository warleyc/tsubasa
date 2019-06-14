import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMMedal } from 'app/shared/model/m-medal.model';

@Component({
  selector: 'jhi-m-medal-detail',
  templateUrl: './m-medal-detail.component.html'
})
export class MMedalDetailComponent implements OnInit {
  mMedal: IMMedal;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMedal }) => {
      this.mMedal = mMedal;
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
