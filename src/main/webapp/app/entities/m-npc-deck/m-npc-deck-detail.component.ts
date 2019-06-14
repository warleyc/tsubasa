import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMNpcDeck } from 'app/shared/model/m-npc-deck.model';

@Component({
  selector: 'jhi-m-npc-deck-detail',
  templateUrl: './m-npc-deck-detail.component.html'
})
export class MNpcDeckDetailComponent implements OnInit {
  mNpcDeck: IMNpcDeck;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mNpcDeck }) => {
      this.mNpcDeck = mNpcDeck;
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
