import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMQuestDropRateCampaignContent, MQuestDropRateCampaignContent } from 'app/shared/model/m-quest-drop-rate-campaign-content.model';
import { MQuestDropRateCampaignContentService } from './m-quest-drop-rate-campaign-content.service';

@Component({
  selector: 'jhi-m-quest-drop-rate-campaign-content-update',
  templateUrl: './m-quest-drop-rate-campaign-content-update.component.html'
})
export class MQuestDropRateCampaignContentUpdateComponent implements OnInit {
  mQuestDropRateCampaignContent: IMQuestDropRateCampaignContent;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    groupId: [null, [Validators.required]],
    contentType: [null, [Validators.required]],
    contentId: [],
    rate: [null, [Validators.required]]
  });

  constructor(
    protected mQuestDropRateCampaignContentService: MQuestDropRateCampaignContentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestDropRateCampaignContent }) => {
      this.updateForm(mQuestDropRateCampaignContent);
      this.mQuestDropRateCampaignContent = mQuestDropRateCampaignContent;
    });
  }

  updateForm(mQuestDropRateCampaignContent: IMQuestDropRateCampaignContent) {
    this.editForm.patchValue({
      id: mQuestDropRateCampaignContent.id,
      groupId: mQuestDropRateCampaignContent.groupId,
      contentType: mQuestDropRateCampaignContent.contentType,
      contentId: mQuestDropRateCampaignContent.contentId,
      rate: mQuestDropRateCampaignContent.rate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestDropRateCampaignContent = this.createFromForm();
    if (mQuestDropRateCampaignContent.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestDropRateCampaignContentService.update(mQuestDropRateCampaignContent));
    } else {
      this.subscribeToSaveResponse(this.mQuestDropRateCampaignContentService.create(mQuestDropRateCampaignContent));
    }
  }

  private createFromForm(): IMQuestDropRateCampaignContent {
    const entity = {
      ...new MQuestDropRateCampaignContent(),
      id: this.editForm.get(['id']).value,
      groupId: this.editForm.get(['groupId']).value,
      contentType: this.editForm.get(['contentType']).value,
      contentId: this.editForm.get(['contentId']).value,
      rate: this.editForm.get(['rate']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestDropRateCampaignContent>>) {
    result.subscribe(
      (res: HttpResponse<IMQuestDropRateCampaignContent>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError()
    );
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
