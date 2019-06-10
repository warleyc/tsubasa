import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCharacterBook } from 'app/shared/model/m-character-book.model';

@Component({
  selector: 'jhi-m-character-book-detail',
  templateUrl: './m-character-book-detail.component.html'
})
export class MCharacterBookDetailComponent implements OnInit {
  mCharacterBook: IMCharacterBook;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCharacterBook }) => {
      this.mCharacterBook = mCharacterBook;
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
