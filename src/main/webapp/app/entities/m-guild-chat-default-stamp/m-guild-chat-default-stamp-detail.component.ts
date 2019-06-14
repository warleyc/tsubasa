import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGuildChatDefaultStamp } from 'app/shared/model/m-guild-chat-default-stamp.model';

@Component({
  selector: 'jhi-m-guild-chat-default-stamp-detail',
  templateUrl: './m-guild-chat-default-stamp-detail.component.html'
})
export class MGuildChatDefaultStampDetailComponent implements OnInit {
  mGuildChatDefaultStamp: IMGuildChatDefaultStamp;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildChatDefaultStamp }) => {
      this.mGuildChatDefaultStamp = mGuildChatDefaultStamp;
    });
  }

  previousState() {
    window.history.back();
  }
}
