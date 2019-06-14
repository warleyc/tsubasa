import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMQuestTicket, MQuestTicket } from 'app/shared/model/m-quest-ticket.model';
import { MQuestTicketService } from './m-quest-ticket.service';

@Component({
  selector: 'jhi-m-quest-ticket-update',
  templateUrl: './m-quest-ticket-update.component.html'
})
export class MQuestTicketUpdateComponent implements OnInit {
  mQuestTicket: IMQuestTicket;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    shortName: [null, [Validators.required]],
    description: [null, [Validators.required]],
    thumbnailAsset: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mQuestTicketService: MQuestTicketService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mQuestTicket }) => {
      this.updateForm(mQuestTicket);
      this.mQuestTicket = mQuestTicket;
    });
  }

  updateForm(mQuestTicket: IMQuestTicket) {
    this.editForm.patchValue({
      id: mQuestTicket.id,
      name: mQuestTicket.name,
      shortName: mQuestTicket.shortName,
      description: mQuestTicket.description,
      thumbnailAsset: mQuestTicket.thumbnailAsset
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mQuestTicket = this.createFromForm();
    if (mQuestTicket.id !== undefined) {
      this.subscribeToSaveResponse(this.mQuestTicketService.update(mQuestTicket));
    } else {
      this.subscribeToSaveResponse(this.mQuestTicketService.create(mQuestTicket));
    }
  }

  private createFromForm(): IMQuestTicket {
    const entity = {
      ...new MQuestTicket(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      shortName: this.editForm.get(['shortName']).value,
      description: this.editForm.get(['description']).value,
      thumbnailAsset: this.editForm.get(['thumbnailAsset']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMQuestTicket>>) {
    result.subscribe((res: HttpResponse<IMQuestTicket>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
