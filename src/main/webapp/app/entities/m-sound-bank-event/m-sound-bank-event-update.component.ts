import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMSoundBankEvent, MSoundBankEvent } from 'app/shared/model/m-sound-bank-event.model';
import { MSoundBankEventService } from './m-sound-bank-event.service';

@Component({
  selector: 'jhi-m-sound-bank-event-update',
  templateUrl: './m-sound-bank-event-update.component.html'
})
export class MSoundBankEventUpdateComponent implements OnInit {
  mSoundBankEvent: IMSoundBankEvent;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    path: [null, [Validators.required]],
    name: [null, [Validators.required]],
    eventId: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected mSoundBankEventService: MSoundBankEventService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mSoundBankEvent }) => {
      this.updateForm(mSoundBankEvent);
      this.mSoundBankEvent = mSoundBankEvent;
    });
  }

  updateForm(mSoundBankEvent: IMSoundBankEvent) {
    this.editForm.patchValue({
      id: mSoundBankEvent.id,
      path: mSoundBankEvent.path,
      name: mSoundBankEvent.name,
      eventId: mSoundBankEvent.eventId
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
    const mSoundBankEvent = this.createFromForm();
    if (mSoundBankEvent.id !== undefined) {
      this.subscribeToSaveResponse(this.mSoundBankEventService.update(mSoundBankEvent));
    } else {
      this.subscribeToSaveResponse(this.mSoundBankEventService.create(mSoundBankEvent));
    }
  }

  private createFromForm(): IMSoundBankEvent {
    const entity = {
      ...new MSoundBankEvent(),
      id: this.editForm.get(['id']).value,
      path: this.editForm.get(['path']).value,
      name: this.editForm.get(['name']).value,
      eventId: this.editForm.get(['eventId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMSoundBankEvent>>) {
    result.subscribe((res: HttpResponse<IMSoundBankEvent>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
