import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDummyEmblem } from 'app/shared/model/m-dummy-emblem.model';

@Component({
  selector: 'jhi-m-dummy-emblem-detail',
  templateUrl: './m-dummy-emblem-detail.component.html'
})
export class MDummyEmblemDetailComponent implements OnInit {
  mDummyEmblem: IMDummyEmblem;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDummyEmblem }) => {
      this.mDummyEmblem = mDummyEmblem;
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
