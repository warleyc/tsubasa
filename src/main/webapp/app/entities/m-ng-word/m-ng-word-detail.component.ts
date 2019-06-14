import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMNgWord } from 'app/shared/model/m-ng-word.model';

@Component({
  selector: 'jhi-m-ng-word-detail',
  templateUrl: './m-ng-word-detail.component.html'
})
export class MNgWordDetailComponent implements OnInit {
  mNgWord: IMNgWord;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mNgWord }) => {
      this.mNgWord = mNgWord;
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
