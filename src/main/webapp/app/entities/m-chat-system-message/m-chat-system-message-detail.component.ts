import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMChatSystemMessage } from 'app/shared/model/m-chat-system-message.model';

@Component({
  selector: 'jhi-m-chat-system-message-detail',
  templateUrl: './m-chat-system-message-detail.component.html'
})
export class MChatSystemMessageDetailComponent implements OnInit {
  mChatSystemMessage: IMChatSystemMessage;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChatSystemMessage }) => {
      this.mChatSystemMessage = mChatSystemMessage;
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
