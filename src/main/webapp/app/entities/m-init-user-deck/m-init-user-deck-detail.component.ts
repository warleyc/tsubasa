import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMInitUserDeck } from 'app/shared/model/m-init-user-deck.model';

@Component({
  selector: 'jhi-m-init-user-deck-detail',
  templateUrl: './m-init-user-deck-detail.component.html'
})
export class MInitUserDeckDetailComponent implements OnInit {
  mInitUserDeck: IMInitUserDeck;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mInitUserDeck }) => {
      this.mInitUserDeck = mInitUserDeck;
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
